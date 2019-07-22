package com.atguigu.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atguigu.bean.Department;
import com.atguigu.bean.Employee;


/**
 * EmployeeDao：操作员工
 * @author lfy
 *
 */
@Repository
public class EmployeeDao {

	private static Map<Integer, Employee> employees = null;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	static{
		employees = new HashMap<Integer, Employee>();

		employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101, "D-AA")));
		employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1, new Department(102, "D-BB")));
		employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0, new Department(103, "D-CC")));
		employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0, new Department(104, "D-DD")));
		employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1, new Department(105, "D-EE")));
	}
	
	//初始id
	private static Integer initId = 1006;
	
	/**
	 * 员工保存/更新二合一方法；
	 * @param employee
	 */
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		
		//根据部门id单独查出部门信息设置到员工对象中，页面提交的只需要提交部门的id
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
	}
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public Collection<Employee> getAll(){
		return employees.values();
	}
	
	/**
	 * 按照id查询某个员工
	 * @param id
	 * @return
	 */
	public Employee get(Integer id){
		return employees.get(id);
	}
	
	/**
	 * 删除某个员工
	 * @param id
	 */
	public void delete(Integer id){
		employees.remove(id);
	}
}
