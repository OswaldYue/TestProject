package com.atguigu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	public BookController(){
		System.out.println("BookController...");
	}
	
	@RequestMapping("/hello")
	public String hello(){
		System.out.println(bookService);
		return "forward:/index.jsp";
	}

}
