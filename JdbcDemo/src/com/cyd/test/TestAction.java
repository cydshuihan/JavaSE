package com.cyd.test;

import java.sql.SQLException;
import java.util.List;

import com.cyd.action.UserAction;
import com.cyd.model.User;

/**
 * 控制层测试类
 * @author cyd
 *
 */
public class TestAction {
	public static void main(String args[]) throws SQLException {
		UserAction action = new UserAction();
		
		/**
		 * 查询
		 */
		List<User> userList = action.get();
		for(User user : userList) {
			System.out.println(user.getId() + ":" + user.getUser_name());
		}
	}
}
