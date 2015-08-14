package com.moback.newsfeed.repository;

import java.util.List;

import com.moback.newsfeed.http.UserJSON;
import com.moback.newsfeed.model.User;

public interface UserRepository {
	
	public List<User> getAllUsers();
	public void addNewUser(UserJSON user);
	public boolean isUserAvailable(String username);
	public UserJSON authenticate(UserJSON user);
	public UserJSON getUserInfo(String firstName);
	public List<User> getUserInfoByName(String firstName);
}
