package com.romankrivtsov.tms.application.employeeApp;

import com.romankrivtsov.tms.dto.request.employeeRequest.EmployeeChangeTaskRequest;
import com.romankrivtsov.tms.dto.request.employeeRequest.EmployeeRequest;
import com.romankrivtsov.tms.dto.response.employeeResponse.EmployeeDetailDto;
import com.romankrivtsov.tms.dto.response.employeeResponse.EmployeeSummaryDto;
import com.romankrivtsov.tms.dto.response.employeeResponse.EmployeeTasksDto;

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
