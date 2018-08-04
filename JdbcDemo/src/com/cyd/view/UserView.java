package com.cyd.view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cyd.action.UserAction;
import com.cyd.model.User;

/**
 * 视图层，控制台展示
 * @author cyd
 *
 */
public class UserView {
	private static final String CONTEXT = "欢迎来到JDBC测试Demo（用户管理系统）：\n" 
			+ "下面是用户管理系统的功能列表：\n" 
			+ "【MAIN/M】：主菜单\n"
			+ "【QUERY/Q】：查询全部的用户信息\n" 
			+ "【GET/G】:查询某位用户的详细信息\n" 
			+ "【ADD/A】:添加用户信息\n" 
			+ "【UPDATE/U】:更新用户信息\n"
			+ "【DELETE/D】:删除用户信息\n" 
			+ "【SEARCH/S】:查询用户信息（根据姓名、手机号来查找）\n" 
			+ "【EXIT/E】：退出用户管理系统\n"
			+ "【BREAK/B】:退出当前功能，返回主菜单\n";

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
		List<Map<String, Object>> params = new ArrayList<>();
		while (scan.hasNext()) {
			String in = scan.next().toString();
			if (OPERATION_EXIT.equalsIgnoreCase(in) || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("您已成功退出用户管理系统");
				break;
			} else if (OPERATION_MAIN.equalsIgnoreCase(in)
					|| OPERATION_MAIN.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println(CONTEXT);
				previousOperation = null;
			} else if (OPERATION_BREAK.equalsIgnoreCase(in)
					|| OPERATION_BREAK.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("您已成功退出当前操作");
				previousOperation = null;
				step = 1;
			} else if (OPERATION_ADD.equalsIgnoreCase(in) || OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_ADD.equals(previousOperation)) {
				previousOperation = OPERATION_ADD;
				// 新增用户
				if (1 == step) {
					System.out.println("请输入用户的【姓名】");
				} else if (2 == step) {
					user.setUser_name(in);
					System.out.println("请输入用户的【年龄】");
				} else if (3 == step) {
					try {
						user.setAge(Integer.valueOf(in));
						System.out.println("请输入用户的【生日】，格式如：yyyy-MM-dd");
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.out.println("年龄格式不正确，只能包含数字");
						step = 2;
					}
				} else if (4 == step) {
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
				} else if (5 == step) {
					user.setEmail(in);
					System.out.println("请输入用户的【性别】");
				} else if (6 == step) {
					try {
						user.setSex(Integer.valueOf(in));
						System.out.println("请输入用户的【手机号】");
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.out.println("性别格式不正确，只能包含数字");
						step = 5;
					}
				} else if (7 == step) {
					user.setMobile(in);
					try {
						userAction.add(user);
						System.out.println("新增用户成功");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("新增用户失败");
					}
					step = 1;
					user = new User();
					previousOperation = null;
				}
				if (OPERATION_ADD.equals(previousOperation)) {
					step++; // step全局用
				}
			} else if (OPERATION_UPDATE.equalsIgnoreCase(in) || OPERATION_UPDATE.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_UPDATE.equals(previousOperation)) {
				previousOperation = OPERATION_UPDATE;
				if(1 == step) {
					System.out.println("请输入用户【姓名】");
				} else if(2 == step) {
					user.setUser_name(in);
					System.out.println("请输入用户【性别】");
				} else if(3 == step) {
					try {
						user.setSex(Integer.valueOf(in));
						System.out.println("请输入用户【年龄】");
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.out.println("性别格式不正确，只能包含数字");
						step = 2;
					}
				} else if(4 == step) {
					try {
						user.setAge(Integer.valueOf(in));
						System.out.println("请输入用户【生日】，格式为：yyyy-MM-dd");
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.out.println("年龄格式不正确，只能包含数字");
						step = 3;
					}
				} else if(5 == step) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date birth = sdf.parse(in);
						user.setBirthday(birth);
						System.out.println("请输入用户的【邮箱】");
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("您输入的日期格式有误，请重新输入");
						step=4;
					}
				} else if(6==step) {
					user.setEmail(in);
					System.out.println("请输入用户的【手机号】");
				} else if(7==step) {
					user.setMobile(in);
					System.out.println("请输入【更新人名字】");
				} else if(8==step) {
					user.setUpdate_user(in);
					System.out.println("请输入是否删除用户【0--删除，1--不删除】");
				} else if(9==step) {
					try {
						user.setIsdel(Integer.valueOf(in));
						System.out.println("请输入要更新的用户的【Id】");
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.out.println("是否删除格式不正确，只能包含数字");
						step = 8;
					}
				} else if(10==step) {
					try {
						user.setId(Integer.valueOf(in));
						userAction.update(user);
						System.out.println("更新用户成功】");
						previousOperation = null;
						step = 1;
						user = new User();
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.out.println("ID格式不正确，只能包含数字");
						step = 9;
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("更新用户失败】");
					}
				}
				if(OPERATION_UPDATE.equals(previousOperation)) {
					step++;
				}
				
			} else if (OPERATION_DELETE.equalsIgnoreCase(in) || OPERATION_DELETE.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_DELETE.equals(previousOperation)) {
				previousOperation = OPERATION_DELETE;
				if(1 == step) {
					System.out.println("请输入用户【ID】");
				} else if (2 == step) {
					try {
						userAction.delete(Integer.valueOf(in));
						System.out.println("用户id：" + in + "删除成功");
						step = 1;
						previousOperation = null;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.out.println("ID格式不正确，只能包含数字");
						step = 1;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(OPERATION_DELETE.equals(previousOperation)) {
					step++;
				}
			} else if (OPERATION_QUERY.equalsIgnoreCase(in)
					|| OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase())) {
				try {
					List<User> users = userAction.get();
					for (User userTmp : users) {
						System.out.println(userTmp.getId() + ",姓名：" + userTmp.getUser_name());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				previousOperation = null;
				step = 1;
			} else if (OPERATION_GET.equalsIgnoreCase(in) || OPERATION_GET.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_GET.equals(previousOperation)) {
				previousOperation = OPERATION_GET;
				if (1 == step) {
					System.out.println("请输入用户的【ID】");
				} else if (2 == step) {
					try {
						user = userAction.get(Integer.valueOf(in));
						System.out.println(user.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					step = 1;
					previousOperation = null;
				}
				if (OPERATION_GET.equals(previousOperation)) {
					step++; // step全局用
				}
			} else if (OPERATION_SEARCH.equalsIgnoreCase(in) || OPERATION_SEARCH.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_SEARCH.equals(previousOperation)) {
				previousOperation = OPERATION_SEARCH;
				if (1 == step) {
					System.out.println("请输入用户的【姓名】");
				} else if (2 == step) {
					Map<String, Object> nameMap = new HashMap<>();
					nameMap.put("name", "user_name");
					nameMap.put("relation", "=");
					nameMap.put("value", "'" + in + "'");
					params.add(nameMap);
					System.out.println("请输入用户的【手机号】");
				} else if (3 == step) {
					Map<String, Object> mobileMap = new HashMap<>();
					mobileMap.put("name", "mobile");
					mobileMap.put("relation", "=");
					mobileMap.put("value", "'" + in + "'");
					params.add(mobileMap);
					try {
						List<User> userList = userAction.get(params);
						for(User user1 : userList) {
							System.out.println(user1.toString());
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					step = 1;
					previousOperation = null;
					params = new ArrayList<>();
				}
				if (OPERATION_SEARCH.equals(previousOperation)) {
					step++; 
				}
			} else {
				System.out.println("您输入的值为：" + in);
			}
		}
	}
}
