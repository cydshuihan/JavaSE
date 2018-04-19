package com.cyd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		getUsers(userService);
		
		User user = userService.getUser(3);
		System.out.println(user.toString());
		//测试修改接口
		User updateUser = new User();
		updateUser.setUser_name("李明");
		updateUser.setAge(30);
		updateUser.setBirthday(new Date());
		updateUser.setEmail("110119@imooc.com");
		updateUser.setMobile("18700873049");
		updateUser.setSex(0);
		updateUser.setUpdate_user("admin");
		updateUser.setIsdel(1);//不删除
		updateUser.setId(3);
		userService.updateUser(updateUser);
		
		//测试删除接口
//		userService.deleteUser(1);
		//测试获取全部user表接口
		System.out.println("测试获取全部user表接口");
		List<User> userList = userService.getUserList();
		for(int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).toString());
		}
		//测试根据名字获取用户列表
		System.out.println("测试根据名字获取用户列表");
		String userName = "李明";
		List<User> userList2 = userService.getUserList(userName);
		if (userList2 == null || userList2.isEmpty()) {
			System.out.println("未找到用户：" + userList2);
		} else {
			for(int i = 0; i < userList2.size(); i++) {
				System.out.println(userList2.get(i).toString());
			}
		}
		
		//测试根据多个条件查询用户列表
		List<Map<String,Object>> params = new ArrayList<Map<String,Object>>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", "email");
		param.put("relation", "like");
		param.put("value", "\'%qq.com%\'"); //注意这里不能缺了引号
		params.add(param);
		System.out.println("测试根据多个条件查询用户列表");
		List<User> userList3 = userService.getUserList(params);
		if (userList3 == null || userList3.isEmpty()) {
			System.out.println("未找到用户：" + userList3);
		} else {
			for(int i = 0; i < userList3.size(); i++) {
				System.out.println(userList3.get(i).toString());
			}
		}
	}
	
	//查询数据表信息
	public static void getUsers(UserService service) {
		try {
			List<User> usersList = service.getUsers();
			for (User user : usersList) {
				System.out.println(user.getUser_name() + "," + user.getEmail() + "," + user.getAge());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//添加用户信息
	public static void addUser(UserService service) {
		User addUser = new User();
		addUser.setUser_name("李明");
		addUser.setAge(20);
		addUser.setBirthday(new Date());
		addUser.setEmail("123456@imooc.com");
		addUser.setMobile("10086111234");
		addUser.setSex(0);
		addUser.setCreate_user("admin");
		addUser.setUpdate_user("admin");
		addUser.setIsdel(1);//不删除
		try {
			service.addUser(addUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
