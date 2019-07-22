package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.controller.HahaController;

@Service
public class HahaService {
	
	@Autowired
	HahaController hahaController;

}
