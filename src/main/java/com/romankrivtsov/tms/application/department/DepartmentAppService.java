package com.romankrivtsov.tms.application.department;

import com.romankrivtsov.tms.dto.request.department.DepartmentRequest;
import com.romankrivtsov.tms.dto.response.department.DepartmentDetailDto;
import com.romankrivtsov.tms.dto.response.department.DepartmentSummaryDto;

import java.util.List;

public interface DepartmentAppService {

    List<DepartmentSummaryDto> getAllDepartmentsSummary();
    DepartmentDetailDto getDepartmentWithEmployee(int departmentId);

    DepartmentDetailDto saveDepartment(DepartmentRequest departmentRequest);

    DepartmentDetailDto updateDepartment(int id, DepartmentRequest departmentRequest);

    void deleteDepartment(int idDepartment);
}
