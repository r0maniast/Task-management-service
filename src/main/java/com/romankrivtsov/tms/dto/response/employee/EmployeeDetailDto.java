package com.romankrivtsov.tms.dto.response.employee;

import com.romankrivtsov.tms.dto.response.department.DepartmentSummaryDto;
import com.romankrivtsov.tms.dto.response.task.TaskSummaryDto;
import com.romankrivtsov.tms.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDetailDto {
    private int id;
    private String name;
    private String patronymic;
    private String surname;
    private String position;
    private DepartmentSummaryDto department;
    private List<TaskSummaryDto> tasks;

    public static EmployeeDetailDto from(Employee employee){
        EmployeeDetailDto employeeDetailDto = new EmployeeDetailDto();
        employeeDetailDto.setId(employee.getId());
        employeeDetailDto.setName(employee.getName());
        employeeDetailDto.setPatronymic(employee.getPatronymic());
        employeeDetailDto.setSurname(employee.getSurname());
        employeeDetailDto.setPosition(employee.getPosition());

        employeeDetailDto.setDepartment(DepartmentSummaryDto.from(employee.getDepartment()));
        employeeDetailDto.setTasks(employee.getTasks().stream()
                .map(TaskSummaryDto::from)
                .collect(Collectors.toList()));
        return employeeDetailDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public DepartmentSummaryDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentSummaryDto department) {
        this.department = department;
    }

    public List<TaskSummaryDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskSummaryDto> tasks) {
        this.tasks = tasks;
    }
}
