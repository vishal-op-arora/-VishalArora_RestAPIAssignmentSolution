package io.vishal.employee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.vishal.employee.model.Employee;
import io.vishal.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	@Transactional
	public Employee saveStudent(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@Transactional
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@Transactional
	public List<Employee> findAllEmployeesByOrder(String order) {
		
		if(order.equals("asc")) {
			return employeeRepository.findAllWithCustomOrderBy(Sort.by(Sort.Direction.ASC, "firstName"));
		}
		else if(order.equals("desc")) {
			return employeeRepository.findAllWithCustomOrderBy(Sort.by(Sort.Direction.DESC, "firstName"));
		}
		
		return null;
	}

	@Transactional
	public Employee findById(long id) {
		return employeeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No employee found."));
	}

	@Transactional
	public void deleteEmployee(long id) {
		employeeRepository.deleteById(id);
	}
	
	@Transactional
	public Employee updateEmployee(Employee employee, long employeeId) {
		
		Employee newEmployee;
		
		if(employeeRepository.findById(employeeId).isPresent()) {
			newEmployee = employeeRepository.getById(employeeId);
			newEmployee.setFirstName(employee.getFirstName());
			newEmployee.setLastName(employee.getLastName());
			newEmployee.setEmail(employee.getEmail());
		}
		else {
			newEmployee = employee;
		}		
		
		return employeeRepository.save(newEmployee);
	}
	
	@Transactional
	public List<Employee> findByFirstName(String firstName) {
		return employeeRepository.findByFirstName(firstName);
	}
	
}
