package com.romankrivtsov.tms.dto.response.employee;

import com.romankrivtsov.tms.entity.Employee;

public class EmployeeSummaryDto {
    private int id;
    private String name;
    private String surname;
    private String position;

    public static EmployeeSummaryDto from(Employee employee){
        EmployeeSummaryDto employeeSummaryDto = new EmployeeSummaryDto();
        employeeSummaryDto.setId(employee.getId());
        employeeSummaryDto.setName(employee.getName());
        employeeSummaryDto.setSurname(employee.getSurname());
        employeeSummaryDto.setPosition(employee.getPosition());
        return employeeSummaryDto;
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
}
