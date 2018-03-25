package com.cyd.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyd.db.DBUtil;
import com.cyd.model.User;

/**
 * User增刪改查业务逻辑
 * 
 * @author cyd
 *
 */
public class UserService {

	public void addUser(User user) throws Exception {
		Connection conn = DBUtil.getConnection();
		//Id自增，不需要插入
		String sql = "insert into imooc_users " + 
				"(user_name,sex,age,birthday,email,mobile,create_user" + 
				",create_date,update_user,update_date,isdel)" + 
				"values(" + 
				"?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
		//采用预编译sql语句,其会将sql语句加载到驱动程序的执行程序中，但是并不直接执行，
		//要PreparedStatement对象调用execute方法后，才真正执行。
		PreparedStatement preStmt = conn.prepareStatement(sql);
		
		//往sql语句插入参数
		preStmt.setString(1, user.getUser_name());
		preStmt.setInt(2, user.getSex());
		preStmt.setInt(3, user.getAge());
//		preStmt.setDate(4, user.getBirthday()); //这样会报错，因为这个方法需要一个java.sql.Date的对象，但是传进去的是java.util.Date的对象，需要做以下转换
		preStmt.setDate(4, new Date(user.getBirthday().getTime()));
		preStmt.setString(5, user.getEmail());
		preStmt.setString(6, user.getMobile());
		preStmt.setString(7, user.getCreate_user());
//		preStmt.setDate(8, new Date(user.getCreate_date().getTime()));
		preStmt.setString(8, user.getUpdate_user());
//		preStmt.setDate(10, new Date(user.getUpdate_date().getTime()));
		preStmt.setInt(9, user.getIsdel());
		
		preStmt.execute();
	}

	public void updateUser() {

	}

	public void deleteUser() {

	}

	public List<User> getUsers() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt= conn.createStatement();
		String sql = "select user_name, age ,email from imooc_users;";
		ResultSet res = stmt.executeQuery(sql);
		
		List<User> userList = new ArrayList<User>();
		while (res.next()) {
			User user = new User();
			user.setUser_name(res.getString("user_name"));
			user.setAge(res.getInt("age"));
			user.setEmail(res.getString("email"));
			userList.add(user);
		}
		return userList;
	}

	public User getUser() {
		return null;
	}
}
