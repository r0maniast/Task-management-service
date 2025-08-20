package com.romankrivtsov.tms.controller;

import com.romankrivtsov.tms.application.EmployeeAppService;
import com.romankrivtsov.tms.dto.request.employee.EmployeeRequest;
import com.romankrivtsov.tms.dto.response.employee.EmployeeDetailDto;
import com.romankrivtsov.tms.dto.response.employee.EmployeeSummaryDto;
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
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private final EmployeeAppService employeeAppService;

    @Autowired
    public EmployeeRestController(EmployeeAppService employeeAppService) {
        this.employeeAppService = employeeAppService;
    }

    @GetMapping
    public List<EmployeeSummaryDto> getAllEmployees() {
        return employeeAppService.getAllEmployeesSummary();
    }

    @GetMapping("/{id}")
    public EmployeeDetailDto getEmployee(@PathVariable int id) {
        return employeeAppService.getEmployeeWithTasks(id);
    }

    @PutMapping("/{id}")
    public EmployeeDetailDto updateEmployee(@PathVariable int id,
                                            @Validated(UpdateValidate.class)
                                                @RequestBody EmployeeRequest employeeRequest) {
        return employeeAppService.updateEmployee(id, employeeRequest);
    }

    @PostMapping
    public ResponseEntity<EmployeeDetailDto> saveEmployee(@Validated(CreateValidate.class)
                                                              @RequestBody EmployeeRequest employeeRequest) {
        EmployeeDetailDto employeeDetailDto = employeeAppService.saveEmployee(employeeRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employeeDetailDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(employeeDetailDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id){
        employeeAppService.deleteEmployee(id);
    }
}
