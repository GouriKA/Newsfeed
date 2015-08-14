package com.moback.newsfeed.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.moback.newsfeed.model.TextFeed;
import com.moback.newsfeed.model.User;

@Repository
public class TextFeedRepositoryImpl implements TextFeedRepository {
	
	private MongoTemplate mongoTemplate;

	@Autowired
	TextFeedRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<TextFeed> getAllTextFeeds() {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC,"timeStamp"));
		/*PageRequest pageRequest = new PageRequest(0,1, new Sort(Sort.Direction.DESC,"timeStamp"));
		return mongoTemplate.findOne(pageRequest, TextFeed.class, "textfeeds");*/
		/*query.skip(5*pageNo);
		query.limit(5);*/
		return mongoTemplate.find(query, TextFeed.class, "textfeeds");
		
	}

	public List<TextFeed> getTextFeedsOfUser(String userId) {
		Query query = new Query();
		Criteria criteria = Criteria.where("userId").is(userId);
		query.with(new Sort(Sort.Direction.DESC,"timeStamp")) ;
		return mongoTemplate.find(query.addCriteria(criteria), TextFeed.class, "textfeeds");	
	}

	public void addNewPost(TextFeed textFeed) {
		Query query = new Query();
		Criteria criteria = Criteria.where("id").is(textFeed.getUserId());
		List<User> user = mongoTemplate.find(query.addCriteria(criteria), User.class, "members");
		textFeed.setFirstName(user.get(0).getFirstName());
		textFeed.setLastName(user.get(0).getLastName());
		mongoTemplate.save(textFeed, "textfeeds");	
	}
	
	public boolean isTextAvailable(String textId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(textId));
		return mongoTemplate.exists(query, TextFeed.class, "textfeeds");
		
	}

	public void deletePost(String userId, String textId) {
		Query query = new Query();
		Criteria criteria = (Criteria.where("id").is(textId));
		List<TextFeed> feeds = mongoTemplate.find(query.addCriteria(criteria), TextFeed.class, "textfeeds");
		if(feeds.get(0).getUserId().equals(userId)){
			mongoTemplate.remove(query, TextFeed.class, "textfeeds");
		}
	}
	
	public void updatePost(TextFeed textFeed) {
		Query query = new Query();
		Criteria criteria = Criteria.where("id").is(textFeed.getId());
		List<TextFeed> feeds = mongoTemplate.find(query.addCriteria(criteria), TextFeed.class, "textfeeds");
		if(feeds.get(0).getUserId().equals(textFeed.getUserId())){
			Update update = new Update();
			update.set("msg" , textFeed.getMsg());
			update.set("timeStamp", textFeed.getTimeStamp());
			mongoTemplate.updateFirst(query, update, TextFeed.class);
		}
	}	
}