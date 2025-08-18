package com.romankrivtsov.tms.dto.response.department;

import com.romankrivtsov.tms.entity.Department;

public class DepartmentSummaryDto {
    private int id;
    private String title;

    public static DepartmentSummaryDto from(Department department){
        DepartmentSummaryDto departmentSummaryDto = new DepartmentSummaryDto();
        departmentSummaryDto.setId(department.getId());
        departmentSummaryDto.setTitle(department.getTitle());
        return departmentSummaryDto;
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
}
