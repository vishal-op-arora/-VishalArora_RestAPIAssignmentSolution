package io.vishal.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.vishal.employee.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
