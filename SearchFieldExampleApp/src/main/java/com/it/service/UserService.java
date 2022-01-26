package com.it.service;

import java.util.List;

import com.it.model.User;

public interface UserService {

public int saveUser(User user);
public List<User> getFilteredData(User user) throws Exception;
}
