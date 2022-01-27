package com.example.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.UserDAO;
import com.example.model.UserDetail;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Transactional
	public int saveUser(UserDetail userDetail) {
		return userDAO.saveUser(userDetail);
	}

	@Transactional
	public UserDetail getUserDetail(int userId) {
		return userDAO.getUserDetail(userId);
	}

	@Transactional
	public int store(MultipartFile file, int userID, HttpSession session) {
		Path rootLocation = Paths.get(session.getServletContext().getRealPath("/resources/images"));
		UserDetail userDetail = this.getUserDetail(userID);
		String nameExtension[] = file.getContentType().split("/");
		String profileImage = userID + "." + nameExtension[1];
		if (userDetail.getUserId() > 0) {
			if (userDetail.getProfileImage() == null || userDetail.getProfileImage() == " "
					|| userDetail.getProfileImage() == "") {
				try {
					Files.copy(file.getInputStream(), rootLocation.resolve(profileImage));
					int result = userDAO.updateProfileImage(profileImage, userID);
					if (result > 0)
						return result;
					else
						return -5;
				} catch (Exception exception) {
					return -5;
				}
			} else {
				try {
					Files.delete(rootLocation.resolve(userDetail.getProfileImage()));
					Files.copy(file.getInputStream(), rootLocation.resolve(profileImage));
					int result = userDAO.updateProfileImage(profileImage, userID);
					if (result > 0)
						return result;
					else
						return -5;
				} catch (Exception exception) {
					return -5;
				}
			}
		} else {
			return 0;
		}
	}

}