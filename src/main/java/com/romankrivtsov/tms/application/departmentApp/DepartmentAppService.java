package com.romankrivtsov.tms.application.departmentApp;

import com.romankrivtsov.tms.dto.request.departmentRequest.DepartmentRequest;
import com.romankrivtsov.tms.dto.response.departmentResponse.DepartmentDetailDto;
import com.romankrivtsov.tms.dto.response.departmentResponse.DepartmentSummaryDto;

import java.util.List;

public interface DepartmentAppService {

    List<DepartmentSummaryDto> getAllDepartmentsSummary();
    DepartmentDetailDto getDepartmentWithEmployee(int departmentId);

    DepartmentDetailDto saveDepartment(DepartmentRequest departmentRequest);

    DepartmentDetailDto updateDepartment(int id, DepartmentRequest departmentRequest);

    void deleteDepartment(int idDepartment);
}
