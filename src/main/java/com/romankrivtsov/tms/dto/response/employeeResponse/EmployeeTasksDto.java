package com.romankrivtsov.tms.dto.response.employeeResponse;

import com.romankrivtsov.tms.dto.response.taskResponse.TaskSummaryDto;
import com.romankrivtsov.tms.entity.Employee;

import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeTasksDto {
    private int idEmployee;
    private Set<TaskSummaryDto> tasks;

    public static EmployeeTasksDto from(Employee employee) {
        EmployeeTasksDto employeeTasksDto = new EmployeeTasksDto();
        employeeTasksDto.setIdEmployee(employee.getId());
        employeeTasksDto.setTasks(employee.getTasks().stream()
                .map(TaskSummaryDto::from)
                .collect(Collectors.toSet()));
        return employeeTasksDto;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Set<TaskSummaryDto> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskSummaryDto> tasks) {
        this.tasks = tasks;
    }
}
