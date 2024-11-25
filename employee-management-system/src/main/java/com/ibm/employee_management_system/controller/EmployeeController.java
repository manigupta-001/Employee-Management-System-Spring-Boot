package com.ibm.employee_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ibm.employee_management_system.entity.Employee;
import com.ibm.employee_management_system.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empservice;
	
	@GetMapping("/")
	public String home(Model m) {
		
//		List<Employee> emp = empservice.getAllEmployee();
//		m.addAttribute("emp",emp);
//		return "index";
		
		return findPaginated(0, m);
		
	}
	
	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";
	}
	
	@PostMapping("/register")
	public String employeeRegister(@ModelAttribute Employee emp) {
		empservice.addEmployee(emp);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editEmployee(@PathVariable int id,Model model) {
		Employee emp = empservice.getEmployeeById(id);
		model.addAttribute("emp",emp);
		return "edit";
	}
	
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee emp) {
		empservice.addEmployee(emp);
		return "redirect:/";
	}
	
	@GetMapping("delete/{id}")
	public String deleteEmployee(@PathVariable int id) {
		empservice.deleteEmployee(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageno}")
	public String findPaginated(@PathVariable int pageno,Model model) {
		
		Page<Employee> emplist = empservice.getEmpByPage(pageno, 3);
		model.addAttribute("emp",emplist);
		model.addAttribute("currentPage", pageno);
		model.addAttribute("totalPages", emplist.getTotalPages());
		model.addAttribute("totalItem", emplist.getTotalElements());
		return "index";
	}
}
