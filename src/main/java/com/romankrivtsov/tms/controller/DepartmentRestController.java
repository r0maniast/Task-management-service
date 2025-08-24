package com.romankrivtsov.tms.controller;

import com.romankrivtsov.tms.application.DepartmentAppService;
import com.romankrivtsov.tms.dto.request.department.DepartmentRequest;
import com.romankrivtsov.tms.dto.response.department.DepartmentDetailDto;
import com.romankrivtsov.tms.dto.response.department.DepartmentSummaryDto;
import com.romankrivtsov.tms.util.validate.CreateValidate;
import com.romankrivtsov.tms.util.validate.UpdateValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {
    private final DepartmentAppService departmentAppService;

    @Autowired
    public DepartmentRestController(DepartmentAppService departmentAppService) {
        this.departmentAppService = departmentAppService;
    }

    @GetMapping
    public List<DepartmentSummaryDto> getAllDepartments() {
        return departmentAppService.getAllDepartmentsSummary();
    }

    @GetMapping("/{id}")
    public DepartmentDetailDto getDepartment(@PathVariable int id) {
        return departmentAppService.getDepartmentWithEmployee(id);
    }

    @PostMapping
    public ResponseEntity<DepartmentDetailDto> saveDepartment(@Validated(CreateValidate.class)
                                                              @RequestBody DepartmentRequest departmentRequest) {
        DepartmentDetailDto departmentDetailDto = departmentAppService.saveDepartment(departmentRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(departmentDetailDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(departmentDetailDto);
    }

    @PutMapping("/{id}")
    public DepartmentDetailDto updateDepartment(@PathVariable int id,
                                                @Validated(UpdateValidate.class)
                                                @RequestBody DepartmentRequest departmentRequest) {
        return departmentAppService.updateDepartment(id, departmentRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable int id){
        departmentAppService.deleteDepartment(id);
    }
}
