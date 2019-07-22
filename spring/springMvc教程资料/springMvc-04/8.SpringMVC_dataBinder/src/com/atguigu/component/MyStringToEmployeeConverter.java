package com.atguigu.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.atguigu.bean.Employee;
import com.atguigu.dao.DepartmentDao;

/**
 * 两个返回
 * 
 * S：Source T：Target 将s转为t
 * 
 * @author lfy
 * 
 */
public class MyStringToEmployeeConverter implements Converter<String, Employee> {

	@Autowired
	DepartmentDao departmentDao;
	
	/**
	 * 自定义的转换规则
	 */
	@Override
	public Employee convert(String source) {
		// TODO Auto-generated method stub
		// empAdmin-admin@qq.com-1-101
		System.out.println("页面提交的将要转换的字符串" + source);
		Employee employee = new Employee();
		if (source.contains("-")) {
			String[] split = source.split("-");
			employee.setLastName(split[0]);
			employee.setEmail(split[1]);
			employee.setGender(Integer.parseInt(split[2]));
			employee.setDepartment(departmentDao.getDepartment(Integer.parseInt(split[3])));
		}
		return employee;
	}

}
