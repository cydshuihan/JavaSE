package com.cyd.controller;

import java.util.List;

import com.cyd.model.User;
import com.cyd.service.UserService;

/**
 * 控制层
 * @author cyd
 *
 */
public class UserController {

	public static void main(String[] args) throws Exception {
		UserService userService = new UserService();
		List<User> usersList = userService.getUsers();
		
		for(User user : usersList) {
			System.out.println(user.getUser_name() + "," + user.getEmail() + "," + user.getAge() );
		}
	}
}
