package com.mgw.testJavaClassInit;

public class Test2 {

	static {
		_i = 20;
	}
	public static int _i = 10;

	public static void main(String[] args) {

		//输出10
		/*
		静态变量如果需要初始化,则自动移入静态代码块中进行初始化,就算你没有写静态代码块,java编译时也会自动加上
		由于静态变量的声明在编译时已经明确，所以静态变量的声明与初始化在编码顺序上可以颠倒。也就是说可以先编写初始化的代码，再编写声明代码。如：
		public static int _i = 10;
		相当于
		// 静态变量的声明
    	public static int _i;
    	// 静态变量的初始化
    	static {
        	_i = 10;
    	}
		而
		static {
			_i = 20;
		}
		public static int _i = 10;
		相当于
		static {
			_i = 20;
		}
		public static int _i;
		static {
			_i = 10;
		}
		* */
		System.out.println(_i);
	}

}
