package com.cyd.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cyd.db.DbUtil;
import com.cyd.model.User;

/**
 * User增刪改查业务逻辑
 * 
 * @author cyd
 *
 */
public class UserService {

	public void addUser(User user) throws Exception {
		Connection conn = DbUtil.getConnection();
		//Id自增，不需要插入
		String sql = "insert into imooc_users " + 
				"(user_name,sex,age,birthday,email,mobile,create_user" + 
				",create_date,update_user,update_date,isdel)" + 
				" values(" + 
				"?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
		//采用预编译sql语句,其会将sql语句加载到驱动程序的执行程序中，但是并不直接执行，
		//要PreparedStatement对象调用execute方法后，才真正执行。
		PreparedStatement preStmt = conn.prepareStatement(sql);
		
		//往sql语句插入参数,数字序号要按顺序
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

	public void updateUser(User user) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = " update imooc_users " + 
				" set user_name=?, sex=?, age=?, birthday=? , email=?, mobile=? " + 
				" , update_user=? , update_date=current_date() , isdel=? " + 
				" where id=?"; //加空格
		PreparedStatement preStmt = conn.prepareStatement(sql);
		//将参数通过对象传入
		preStmt.setString(1, user.getUser_name());
		preStmt.setInt(2, user.getSex());
		preStmt.setInt(3, user.getAge());
		preStmt.setDate(4, new Date(user.getBirthday().getTime()));
		preStmt.setString(5, user.getEmail());
		preStmt.setString(6, user.getMobile());
		preStmt.setString(7, user.getUpdate_user());
		preStmt.setInt(8, user.getIsdel());
		preStmt.setInt(9, user.getId()); //传入id作为过滤条件
		preStmt.execute();
	}

	public void deleteUser(Integer id) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = " delete from imooc_users " + 
				" where id=?"; //加空格
		PreparedStatement preStmt = conn.prepareStatement(sql);
		//将参数通过对象传入
		preStmt.setInt(1, id);
		preStmt.execute();
	}

	public List<User> getUsers() throws Exception {
		Connection conn = DbUtil.getConnection();
		Statement stmt= conn.createStatement();
		String sql = "select * from imooc_users;";
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

	/**
	 * 得到某个用户的详细信息
	 * @return
	 * @throws SQLException 
	 */
	public User getUser(Integer id)  throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = " select *  from imooc_users " + 
				" where id=?"; //加空格
		PreparedStatement preStmt = conn.prepareStatement(sql);
		//将参数通过对象传入
		preStmt.setInt(1, id);
		ResultSet res = preStmt.executeQuery(); //execute()方法用于修改数据库操作，如增加，删除，修改
		User user = new User();
		while(res.next()) {
			user.setId(res.getInt("id"));
			user.setAge(res.getInt("age"));
			user.setSex(res.getInt("sex"));
			user.setBirthday(res.getDate("birthday")); 
			user.setUser_name(res.getString("user_name"));
			//注意：通过数据库查询得到的java.sql.Date类型不需要回转为java.util.Date类型,因为sql的Date类型是util的Date类型的子集。
			user.setEmail(res.getString("email"));
			user.setMobile(res.getString("mobile"));
			user.setCreate_date(res.getDate("create_date"));
			user.setCreate_user(res.getString("create_user"));
			user.setUpdate_date(res.getDate("update_date"));
			user.setUpdate_user(res.getString("update_user"));
			user.setIsdel(res.getInt("isdel"));
		}
		return user;
	}
	
	/**
	 * 查询所有用户信息
	 * @return
	 * @throws SQLException
	 */
	public List<User> getUserList() throws SQLException {
		List<User> userList = new ArrayList<User>();
		Connection conn = DbUtil.getConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from imooc_users");
		PreparedStatement preStmt = conn.prepareStatement(sb.toString());
		ResultSet res = preStmt.executeQuery();
		
		while(res.next()) {
			User user = new User();
			user.setUser_name(res.getString("user_name"));
			user.setId(res.getInt("id"));
			user.setAge(res.getInt("age"));
			user.setSex(res.getInt("sex"));
			user.setBirthday(res.getDate("birthday")); 
			user.setUser_name(res.getString("user_name"));
			//注意：通过数据库查询得到的java.sql.Date类型不需要回转为java.util.Date类型,因为sql的Date类型是util的Date类型的子集。
			user.setEmail(res.getString("email"));
			user.setMobile(res.getString("mobile"));
			user.setCreate_date(res.getDate("create_date"));
			user.setCreate_user(res.getString("create_user"));
			user.setUpdate_date(res.getDate("update_date"));
			user.setUpdate_user(res.getString("update_user"));
			user.setIsdel(res.getInt("isdel"));
			userList.add(user);
		}
		return userList;
	}
	
	/**
	 * 根据名字进行单个条件查询
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public List<User> getUserList(String name) throws SQLException {
		List<User> userList = new ArrayList<User>();
		Connection conn = DbUtil.getConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from imooc_users ");
		sb.append(" where user_name = ? ");
//		sb.append(" where user_name like ? ");
		System.out.println(sb.toString());
		PreparedStatement preStmt = conn.prepareStatement(sb.toString());
		preStmt.setString(1, name);
//		preStmt.setString(1, "%" + name + "%"); // 模糊查询时的设置
		ResultSet res = preStmt.executeQuery();
		
		while(res.next()) {
			User user = new User();
			user.setUser_name(res.getString("user_name"));
			user.setId(res.getInt("id"));
			user.setAge(res.getInt("age"));
			user.setSex(res.getInt("sex"));
			user.setBirthday(res.getDate("birthday")); 
			user.setUser_name(res.getString("user_name"));
			user.setEmail(res.getString("email"));
			user.setMobile(res.getString("mobile"));
			user.setCreate_date(res.getDate("create_date"));
			user.setCreate_user(res.getString("create_user"));
			user.setUpdate_date(res.getDate("update_date"));
			user.setUpdate_user(res.getString("update_user"));
			user.setIsdel(res.getInt("isdel"));
			userList.add(user);
		}
		return userList;
	}
	
	/**
	 * 根据多个条件进行查询
	 * @param params
	 * 结构：[{"name":"user_name", "relation":"like", "value":"李明"},{"name":"email", "relation":"=", "value":"1234@126.com"}]
	 * @return
	 * @throws SQLException
	 */
	public List<User> getUserList(List<Map<String, Object>> params) throws SQLException {
		List<User> userList = new ArrayList<User>();
		Connection conn = DbUtil.getConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from imooc_users where 1=1 "); //利用1=1来进行连接
		
		if (params != null && params.size() > 0) {
			for (Map<String, Object> param : params) {
				sb.append(" and " + param.get("name") + " "+ param.get("relation")
				+ " " + param.get("value"));
			}
		}
		System.out.println(sb.toString());
		PreparedStatement preStmt = conn.prepareStatement(sb.toString());
		ResultSet res = preStmt.executeQuery();
		
		while(res.next()) {
			User user = new User();
			user.setUser_name(res.getString("user_name"));
			user.setId(res.getInt("id"));
			user.setAge(res.getInt("age"));
			user.setSex(res.getInt("sex"));
			user.setBirthday(res.getDate("birthday")); 
			user.setUser_name(res.getString("user_name"));
			user.setEmail(res.getString("email"));
			user.setMobile(res.getString("mobile"));
			user.setCreate_date(res.getDate("create_date"));
			user.setCreate_user(res.getString("create_user"));
			user.setUpdate_date(res.getDate("update_date"));
			user.setUpdate_user(res.getString("update_user"));
			user.setIsdel(res.getInt("isdel"));
			userList.add(user);
		}
		return userList;
	}
}
