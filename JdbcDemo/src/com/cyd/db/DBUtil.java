package com.cyd.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库工具类
 * @author cyd
 *
 */
public class DBUtil {
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://127.0.0.1:3306/imooc";
	private static String USER = "root";
	private static String PASSWORD = "root";
	
	private static Connection conn = null;
	
	static { //静态代码块，程序运行时最先被加载
		// 加载驱动程序，利用反射机制，通过类名将这个类反向的加载到我们获得环境中
		try {
			Class.forName(DRIVER);
			// 获得数据库连接
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//加载驱动程序，利用反射机制，通过类名将这个类反向的加载到我们获得环境中
		Class.forName(DRIVER);
		//获得数据库连接
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		//通过数据库连接操作数据库，实现对数据库的增删改查
		Statement stmt= conn.createStatement();
		String sql = "select user_name, age, birthday from imooc_users;";
		ResultSet res = stmt.executeQuery(sql);
		
		while(res.next()) {
			System.out.println(res.getString("user_name") + "," + res.getInt("age")
			+ "," + res.getDate("birthday"));
		}
	}
}
