package com.romankrivtsov.tms.service.employeeService;

import com.romankrivtsov.tms.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(int id);

    Employee getEmployee(int id);

    List<Employee> getAllEmployees();


}
