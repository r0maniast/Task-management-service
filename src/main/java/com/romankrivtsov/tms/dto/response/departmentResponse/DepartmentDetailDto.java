package com.romankrivtsov.tms.dto.response.departmentResponse;

import com.romankrivtsov.tms.dto.response.employeeResponse.EmployeeSummaryDto;
import com.romankrivtsov.tms.entity.Department;
import com.romankrivtsov.tms.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentDetailDto {
    private int id;
    private String title;
    private List<EmployeeSummaryDto> employees;

    public static DepartmentDetailDto from(Department department){
        DepartmentDetailDto departmentDetailDto = new DepartmentDetailDto();
        departmentDetailDto.setId(department.getId());
        departmentDetailDto.setTitle(department.getTitle());
        List<Employee> departmentEmployees = department.getEmployees();
        if(departmentEmployees != null){
            departmentDetailDto.setEmployees(departmentEmployees.stream()
                    .map(EmployeeSummaryDto::from)
                    .collect(Collectors.toList()));
        } else {
            departmentDetailDto.setEmployees(List.of());
        }
        return departmentDetailDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<EmployeeSummaryDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeSummaryDto> employees) {
        this.employees = employees;
    }
}
