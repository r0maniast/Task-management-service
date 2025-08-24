package com.romankrivtsov.tms.application;

import com.romankrivtsov.tms.dto.request.department.DepartmentRequest;
import com.romankrivtsov.tms.dto.response.department.DepartmentDetailDto;
import com.romankrivtsov.tms.dto.response.department.DepartmentSummaryDto;
import com.romankrivtsov.tms.entity.Department;
import com.romankrivtsov.tms.service.departmentService.DepartmentServiceImp;
import com.romankrivtsov.tms.service.employeeService.EmployeeServiceImp;
import com.romankrivtsov.tms.service.taskService.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentAppService {
    private final EmployeeServiceImp employeeServiceImp;
    private final DepartmentServiceImp departmentServiceImp;
    private final TaskServiceImp taskServiceImp;

    @Autowired
    public DepartmentAppService(EmployeeServiceImp employeeServiceImp, DepartmentServiceImp departmentServiceImp,
                                TaskServiceImp taskServiceImp) {
        this.employeeServiceImp = employeeServiceImp;
        this.departmentServiceImp = departmentServiceImp;
        this.taskServiceImp = taskServiceImp;
    }

    public List<DepartmentSummaryDto> getAllDepartmentsSummary() {
        List<Department> allDepartments = departmentServiceImp.getAllDepartments();
        return allDepartments.stream()
                .map(DepartmentSummaryDto::from)
                .toList();
    }

    public DepartmentDetailDto getDepartmentWithEmployee(int departmentId) {
        Department department = departmentServiceImp.getDepartment(departmentId);
        return DepartmentDetailDto.from(department);
    }

    public DepartmentDetailDto saveDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setTitle(departmentRequest.getTitle());
        Department savedDepartment = departmentServiceImp.saveDepartment(department);
        return DepartmentDetailDto.from(savedDepartment);
    }

    public DepartmentDetailDto updateDepartment(int id, DepartmentRequest departmentRequest) {
        Department department = departmentServiceImp.getDepartment(id);
        String title = departmentRequest.getTitle();
        if (title != null)
            department.setTitle(title);
        Department updatedDepartment = departmentServiceImp.updateDepartment(department);
        return DepartmentDetailDto.from(updatedDepartment);
    }

    public void deleteDepartment(int idDepartment) {
        departmentServiceImp.deleteDepartment(idDepartment);
    }
}
