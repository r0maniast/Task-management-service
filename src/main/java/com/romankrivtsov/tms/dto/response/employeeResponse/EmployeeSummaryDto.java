package com.romankrivtsov.tms.dto.response.employeeResponse;

import com.romankrivtsov.tms.entity.Employee;

public class EmployeeSummaryDto {
    private int idEmployee;
    private String name;
    private String surname;
    private String position;

    public static EmployeeSummaryDto from(Employee employee){
        EmployeeSummaryDto employeeSummaryDto = new EmployeeSummaryDto();
        employeeSummaryDto.setIdEmployee(employee.getId());
        employeeSummaryDto.setName(employee.getName());
        employeeSummaryDto.setSurname(employee.getSurname());
        employeeSummaryDto.setPosition(employee.getPosition());
        return employeeSummaryDto;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
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
