package com.cyd.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.cyd.action.UserAction;
import com.cyd.model.User;

/**
 * 视图层，控制台展示
 * @author cyd
 *
 */
public class UserView {
	private static final String CONTEXT = "欢迎来到JDBC测试Demo（用户管理系统）：\n" + 
			"下面是用户管理系统的功能列表：\n" + 
			"【MAIN/M】：主菜单\n" + 
			"【QUERY/Q】：查询全部的用户信息\n" + 
			"【GET/G】:查询某位用户的详细信息\n" + 
			"【ADD/A】:添加用户信息\n" + 
			"【UPDATE/U】:更新用户信息\n" + 
			"【DELETE/D】:删除用户信息\n" + 
			"【SEARCH/S】:查询用户信息（根据姓名、手机号来查找）\n" + 
			"【EXIT/E】：退出用户管理系统\n" + 
			"【BREAK/B】:退出当前功能，返回主菜单\n";
			
	private static final String OPERATION_MAIN = "MAIN";
	private static final String OPERATION_QUERY = "QUERY";
	private static final String OPERATION_GET = "GET";
	private static final String OPERATION_ADD = "ADD";
	private static final String OPERATION_UPDATE = "UPDATE";
	private static final String OPERATION_DELETE = "DELETE";
	private static final String OPERATION_SEARCH = "SEARCH";
	private static final String OPERATION_EXIT = "EXIT";
	private static final String OPERATION_BREAK = "BREAK";
	
	public static void main(String args[]) {
		System.out.println(CONTEXT);
		Scanner scan = new Scanner(System.in);
		// 输入是由scan.next()触发的。
		String previousOperation = null; // 记录前次操作，用于保持当前操作不切换到别的操作
		Integer step = 1; // 判断是否是第一次进入某操作
		User user = new User();
		UserAction userAction = new UserAction();
		while(scan.hasNext()) {
			String in = scan.next().toString();
			if (OPERATION_EXIT.equalsIgnoreCase(in) || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("您已成功退出用户管理系统");
				break;
			} else if (OPERATION_ADD.equalsIgnoreCase(in) || OPERATION_ADD.substring(0, 1).equals(in.toUpperCase()) || 
					OPERATION_ADD.equals(previousOperation)) {
				previousOperation = OPERATION_ADD;
				// 新增用户
				if (1 == step) {
					System.out.println("请输入用户的【姓名】");
				} else if (2 == step){
					user.setUser_name(in);
					System.out.println("请输入用户的【年龄】");
				} else if (3 == step){
					user.setAge(Integer.valueOf(in));
					System.out.println("请输入用户的【生日】，格式如：yyyy-MM-dd");
				} else if (4 == step){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date birthday = null;
					try {
						birthday = sdf.parse(in);
						user.setBirthday(birthday);
						System.out.println("请输入用户的【邮箱】");
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("您输入的生日格式有误，请重新输入");
						step = 3; // 标记，用于重新输入，本来等于4，末尾有自增，故减一
					}
				} else if (5 == step){
					user.setEmail(in);
					System.out.println("请输入用户的【性别】");
				} else if (6 == step){
					user.setSex(Integer.valueOf(in));
					System.out.println("请输入用户的【手机号】");
				} else if (7 == step){
					user.setMobile(in);
					try {
						userAction.add(user);
						System.out.println("新增用户成功");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("新增用户失败");
					}
				}
				if(OPERATION_ADD.equals(previousOperation)) {
					step++; // step全局用
				}
			} else {
				System.out.println("您输入的值为：" + in);
			}
		}
	}
}
