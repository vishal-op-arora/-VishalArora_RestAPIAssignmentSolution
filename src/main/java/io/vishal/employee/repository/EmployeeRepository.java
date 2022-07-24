package io.vishal.employee.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.vishal.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>   {

	List<Employee> findByFirstName(String firstName);

	List<Employee> findAllWithCustomOrderBy(Sort by);

}
