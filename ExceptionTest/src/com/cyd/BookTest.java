package com.cyd;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookTest {

	Book[] books = { new Book(1, "Java开发"), new Book(2, "C++开发"), new Book(3, "JavaScript开发") };
	int chose;// 查询方式，书名或序号
	int bookNum; // 图书号
	String bookName;
	
	public static void main(String[] args) {
		BookTest bookTest = new BookTest();
		bookTest.run();
		
	}
	public void chose() {
		try {
			System.out.println("输入命令：1、按照名称来查找图书；2、按照序号来查找图书");
			Scanner input = new Scanner(System.in);
			chose = input.nextInt();
//			input.close();
			if (chose != 1 && chose != 2) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("命令输入错误，请根据提示输入数字命令");
			run();
		} 
	}
	
	public void findByName() {
		System.out.println("输入图书名称：");
		try {
			Scanner input1 = new Scanner(System.in);
			bookName = input1.nextLine();
//			input1.close();
			boolean isExist = false;
			for (Book book : books) {
				if (bookName.equals(book.getName())) {
					isExist = true;
					break;
				}
			}
			if (isExist) {
				System.out.println("book: " + bookName);
				
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("图书不存在，未找到对应名称图书");
		} finally {
			run();
		}
	}
	
	public void findById() {
		System.out.println("输入图书编号：");
		try {
			Scanner input2 = new Scanner(System.in);
			bookNum = input2.nextInt();
//			input2.close();
			boolean isEqual = false;
			String bookName = null;
			for (Book book : books) {
				if (bookNum == book.getId()) {
					isEqual = true;
					bookName = book.getName();
					break;
				}
			}
			if (isEqual) {
				System.out.println("book:" + bookName);
			} else {
				throw new Exception();
			}
		} catch(InputMismatchException e) {
			System.out.println("命令输入错误，图书编号为数字");
		} catch(Exception e) {
			System.out.println("图书不存在，图书序号不在范围内");
		} finally {
			run();
		}
	}
	
	public void run() {
		chose();
		switch(chose) {
		case 1 :
			findByName();
			break;
		case 2 :
			findById();
			break;
		default:
			break;
		}
	}
}
