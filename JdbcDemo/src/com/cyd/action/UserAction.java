package com.cyd.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cyd.dao.UserDao;
import com.cyd.model.User;

/**
 * 控制层
 * @author cyd
 *
 */
public class UserAction {
	private UserDao userDao = new UserDao();
	
	public void add(User user) throws Exception {
		userDao.addUser(user);
	}
	
	public void update(User user) throws SQLException {
		userDao.updateUser(user);
	}
	
	public void delete(Integer id) throws SQLException {
		userDao.deleteUser(id);
	}
	
	public List<User> get() throws SQLException {
		return userDao.getUserList();
	}
	
	public List<User> get(List<Map<String, Object>> params) throws SQLException {
		return userDao.getUserList(params);
	}
	
	public User get(Integer id) throws SQLException {
		return userDao.getUser(id);
	}
}
