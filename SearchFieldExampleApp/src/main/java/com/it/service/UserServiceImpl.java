package com.it.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.dao.UserDao;
import com.it.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Transactional
	public int saveUser(User user) {
		return userDao.saveUser(user) ;
	}

	@Transactional
	public List<User> getFilteredData(User user) throws Exception{
		return userDao.getFilteredData(user);
	}


}
