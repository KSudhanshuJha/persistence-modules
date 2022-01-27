package com.example.dao;

import com.example.model.UserDetail;

public interface UserDAO {
	
	public int saveUser(UserDetail userDetail);
	public UserDetail getUserDetail(int userId);
	public int updateProfileImage(String profileImage , int userID);

}