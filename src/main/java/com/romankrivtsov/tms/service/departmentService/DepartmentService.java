package com.romankrivtsov.tms.service.departmentService;

import com.romankrivtsov.tms.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    Department updateDepartment(Department department);

    void deleteDepartment(int id);

    Department getDepartment(int id);

    List<Department> getAllDepartments();


}
