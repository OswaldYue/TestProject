package com.mgw.testJavaClassInit;

public class Dog extends Anni{



	{
		a = "aaaaa";
	}


	Dog(String aaa) {
		a = "aaaaaa";

		//对于类中对成员变量的初始化和构造代码块中的代码全部都挪到了构造函数中，并且是按照java源文件的初始化顺序(即如果成员变量初始化在构造代码块前面就先执行成员变量的初始化,如果构造代码块在成员变量初始化前面就先执行构造代码块),
		// 依次对成员变量进行初始化的，而原构造函数中的代码则移到了构造函数的最后执行
		//a = "aaaaa" -> a = "aaaa" -> a = "aaaaaa"
		System.out.println(a);
		System.out.println(Dog.class);
	}
	private String a = "aaaa";


	Dog() {

		this("aa");
	}

}
