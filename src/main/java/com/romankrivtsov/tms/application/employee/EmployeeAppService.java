package com.romankrivtsov.tms.application.employee;

import com.romankrivtsov.tms.dto.request.employee.EmployeeChangeTaskRequest;
import com.romankrivtsov.tms.dto.request.employee.EmployeeRequest;
import com.romankrivtsov.tms.dto.response.employee.EmployeeDetailDto;
import com.romankrivtsov.tms.dto.response.employee.EmployeeSummaryDto;
import com.romankrivtsov.tms.dto.response.employee.EmployeeTasksDto;

import java.util.List;

public interface EmployeeAppService {

    List<EmployeeSummaryDto> getAllEmployeesSummary();

    EmployeeDetailDto getEmployee(int employeeId);

    EmployeeDetailDto saveEmployee(EmployeeRequest employeeRequest);

    EmployeeDetailDto updateEmployee(int id, EmployeeRequest employeeRequest);

    void deleteEmployee(int idEmployee);

    EmployeeTasksDto getTasks(int id);

    EmployeeTasksDto setTasks(int employeeId, EmployeeChangeTaskRequest employeeChangeTaskRequest);

    EmployeeTasksDto addTask(int idEmployee, int idTask);


    EmployeeTasksDto removeTask(int idEmployee, int idTask);
}
