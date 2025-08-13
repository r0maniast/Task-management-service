package com.romankrivtsov.tms.dao;

import com.romankrivtsov.tms.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
