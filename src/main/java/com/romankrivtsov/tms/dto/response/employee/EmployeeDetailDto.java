package com.romankrivtsov.tms.dto.response.employee;

import com.romankrivtsov.tms.dto.response.department.DepartmentSummaryDto;
import com.romankrivtsov.tms.entity.Employee;

public class EmployeeDetailDto {
    private int idEmployee;
    private String name;
    private String patronymic;
    private String surname;
    private String position;
    private DepartmentSummaryDto departmentId;

    public static EmployeeDetailDto from(Employee employee){
        EmployeeDetailDto employeeDetailDto = new EmployeeDetailDto();
        employeeDetailDto.setIdEmployee(employee.getId());
        employeeDetailDto.setName(employee.getName());
        employeeDetailDto.setPatronymic(employee.getPatronymic());
        employeeDetailDto.setSurname(employee.getSurname());
        employeeDetailDto.setPosition(employee.getPosition());

        employeeDetailDto.setDepartmentId(DepartmentSummaryDto.from(employee.getDepartment()));
        return employeeDetailDto;
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

    public DepartmentSummaryDto getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(DepartmentSummaryDto departmentId) {
        this.departmentId = departmentId;
    }

}
