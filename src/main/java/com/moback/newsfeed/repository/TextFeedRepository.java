package com.moback.newsfeed.repository;

import java.util.List;

import com.moback.newsfeed.model.TextFeed;

public interface TextFeedRepository {
	
	public List<TextFeed> getAllTextFeeds();
	public List<TextFeed> getTextFeedsOfUser(String username);
	public void addNewPost(TextFeed textFeed);
	public boolean isTextAvailable(String textId);
	public void deletePost(String userId, String textId);
	public void updatePost(TextFeed textFeed);

}
