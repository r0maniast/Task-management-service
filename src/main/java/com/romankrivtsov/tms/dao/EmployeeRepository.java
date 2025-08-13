package com.romankrivtsov.tms.dao;

import com.romankrivtsov.tms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
