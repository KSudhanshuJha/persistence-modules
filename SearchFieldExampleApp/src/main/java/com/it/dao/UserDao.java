package com.it.dao;

import java.util.List;

import com.it.model.User;


public interface UserDao {
	
public int saveUser(User user);
public List<User> getFilteredData(User user) throws Exception;

}
