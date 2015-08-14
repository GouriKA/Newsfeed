package com.moback.newsfeed.repository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.moback.newsfeed.auth.PasswordService;
import com.moback.newsfeed.http.UserJSON;
import com.moback.newsfeed.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private MongoTemplate mongoTemplate;

	@Autowired
	private PasswordService passwordEncryptionService;

	@Autowired
	UserRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<User> getAllUsers() {
		Query query = new Query();
		query.fields().include("firstName").include("lastName").include("dateOfBirth").include("gender").include("tagLine");
		return mongoTemplate.find(query, User.class, "members");
		
	}

	public void addNewUser(UserJSON userJSON) {
		User user = new User();
		user.setUsername(userJSON.getUsername());
		user.setFirstName(userJSON.getFirstName());
		user.setLastName(userJSON.getLastName());
		user.setDateOfBirth(userJSON.getDateOfBirth());
		user.setGender(userJSON.getGender());
		user.setTagLine(userJSON.getTagLine());
		byte[] encryptedPassword = null;
		byte[] salt = null;
		try {
			salt = passwordEncryptionService.generateSalt();
			encryptedPassword = passwordEncryptionService.getEncryptedPassword(
					userJSON.getPassword(), salt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		user.setEncryptedPassword(encryptedPassword);
		user.setSalt(salt);
		try {
			mongoTemplate.save(user, "members");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserJSON getUserInfo(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(userId));
		query.fields().include("firstName").include("lastName").include("dateOfBirth").include("gender").include("tagLine");
		List<User> user = mongoTemplate.find(query, User.class, "members");
		UserJSON userJSON = new UserJSON();
		userJSON.setFirstName(user.get(0).getFirstName());
		userJSON.setLastName(user.get(0).getLastName());
		userJSON.setGender(user.get(0).getGender());
		userJSON.setDateOfBirth(user.get(0).getDateOfBirth());
		userJSON.setTagLine(user.get(0).getTagLine());
		return userJSON;
	}
	
	public List<User> getUserInfoByName(String firstName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("firstName").is(firstName));
		query.fields().include("firstName").include("lastName").include("dateOfBirth").include("gender").include("tagLine");
		List<User> user = mongoTemplate.find(query, User.class, "members");
		return user;
	}

	public boolean isUserAvailable(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		return !mongoTemplate.exists(query, User.class, "members");
	}

	public UserJSON authenticate(UserJSON userJSON) {
		UserJSON userJSONResponse = new UserJSON();
		String username = userJSON.getUsername();
		String password = userJSON.getPassword();
		boolean authenticated = false;
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		List<User> user = mongoTemplate.find(query,
				User.class, "members");
		if (!user.isEmpty()) {
			try {
				authenticated = passwordEncryptionService.authenticate(
						password, user.get(0).getEncryptedPassword(),
						user.get(0).getSalt());
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
				authenticated = false;
			}
			if (authenticated == true) {
				userJSONResponse.setId(user.get(0).getId());
				userJSONResponse.setFirstName(user.get(0).getFirstName());
				userJSONResponse.setLastName(user.get(0).getLastName());
			}
		}
		return userJSONResponse;
	}

}
