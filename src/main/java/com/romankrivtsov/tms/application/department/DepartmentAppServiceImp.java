package com.romankrivtsov.tms.application.department;

import com.romankrivtsov.tms.dto.request.department.DepartmentRequest;
import com.romankrivtsov.tms.dto.response.department.DepartmentDetailDto;
import com.romankrivtsov.tms.dto.response.department.DepartmentSummaryDto;
import com.romankrivtsov.tms.entity.Department;
import com.romankrivtsov.tms.service.departmentService.DepartmentService;
import com.romankrivtsov.tms.service.employeeService.EmployeeService;
import com.romankrivtsov.tms.service.taskService.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentAppServiceImp implements DepartmentAppService{
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final TaskService taskService;

    @Autowired
    public DepartmentAppServiceImp(EmployeeService employeeService, DepartmentService departmentService,
                                   TaskService taskService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.taskService = taskService;
    }

    @Override
    public List<DepartmentSummaryDto> getAllDepartmentsSummary() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        return allDepartments.stream()
                .map(DepartmentSummaryDto::from)
                .toList();
    }

    @Override
    public DepartmentDetailDto getDepartmentWithEmployee(int departmentId) {
        Department department = departmentService.getDepartment(departmentId);
        return DepartmentDetailDto.from(department);
    }

    @Override
    public DepartmentDetailDto saveDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setTitle(departmentRequest.getTitle());
        Department savedDepartment = departmentService.saveDepartment(department);
        return DepartmentDetailDto.from(savedDepartment);
    }

    @Override
    public DepartmentDetailDto updateDepartment(int id, DepartmentRequest departmentRequest) {
        Department department = departmentService.getDepartment(id);
        String title = departmentRequest.getTitle();
        if (title != null)
            department.setTitle(title);
        Department updatedDepartment = departmentService.updateDepartment(department);
        return DepartmentDetailDto.from(updatedDepartment);
    }

    @Override
    public void deleteDepartment(int idDepartment) {
        departmentService.deleteDepartment(idDepartment);
    }
}
