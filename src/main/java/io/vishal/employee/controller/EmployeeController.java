package io.vishal.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.vishal.employee.model.Employee;
import io.vishal.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> fetchEmployees() {
		return employeeService.findAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee fetchEmployee(@PathVariable("id") long employeeId) {
		return employeeService.findById(employeeId);
	}
	
	@GetMapping("/search/{firstName}")
	public List<Employee> fetchEmployeeByFirstName(@PathVariable("firstName") String firstName) {
		return employeeService.findByFirstName(firstName);
	}
	
	@GetMapping("/sort")
	public List<Employee> fetchEmployeeByOrder(@RequestParam("order") String order) {
		return employeeService.findAllEmployeesByOrder(order);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee saveStudent(@RequestBody Employee employee) {
		return employeeService.saveStudent(employee);
	}
	
	@PutMapping("/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") long employeeId) {
		return employeeService.updateEmployee(employee, employeeId);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable("id") long employeeId) {
		employeeService.deleteEmployee(employeeId);
	}
	
}
