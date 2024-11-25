package com.ibm.employee_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ibm.employee_management_system.entity.Employee;
import com.ibm.employee_management_system.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository rep;
	
	public void addEmployee(Employee emp) {
		rep.save(emp);
	}
	
	public List<Employee> getAllEmployee(){
		return rep.findAll();
	}
	
	public Employee getEmployeeById(int id) {
		return rep.findById(id).get();
	}
	
	public void deleteEmployee(int id) {
		 rep.deleteById(id);
	}
	
	public Page<Employee> getEmpByPage(int currentPage,int size){
		return  rep.findAll(PageRequest.of(currentPage, size));
	}
}
