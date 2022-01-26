package com.it.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.model.User;
import com.it.service.UserService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@PostMapping("/saveUser")
	public int saveAdminDetail(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping("/filterData")
	public List<User> getFilteredData(@RequestBody User user) {
		try {
		return userService.getFilteredData(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
