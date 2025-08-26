package com.romankrivtsov.tms.controller;

import com.romankrivtsov.tms.application.EmployeeAppService;
import com.romankrivtsov.tms.dto.request.employee.EmployeeChangeTaskRequest;
import com.romankrivtsov.tms.dto.request.employee.EmployeeRequest;
import com.romankrivtsov.tms.dto.response.employee.EmployeeDetailDto;
import com.romankrivtsov.tms.dto.response.employee.EmployeeSummaryDto;
import com.romankrivtsov.tms.dto.response.employee.EmployeeTasksDto;
import com.romankrivtsov.tms.util.validate.CreateValidate;
import com.romankrivtsov.tms.util.validate.UpdateValidate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
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
        return employeeAppService.getEmployee(id);
    }

    @PostMapping
    public ResponseEntity<EmployeeDetailDto> saveEmployee(@Validated(CreateValidate.class)
                                                          @RequestBody EmployeeRequest employeeRequest) {
        EmployeeDetailDto employeeDetailDto = employeeAppService.saveEmployee(employeeRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employeeDetailDto.getIdEmployee())
                .toUri();

        return ResponseEntity.created(location).body(employeeDetailDto);
    }

    @PutMapping("/{id}")
    public EmployeeDetailDto updateEmployee(@PathVariable int id,
                                            @Validated(UpdateValidate.class)
                                            @RequestBody EmployeeRequest employeeRequest) {
        return employeeAppService.updateEmployee(id, employeeRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeAppService.deleteEmployee(id);
    }

    @GetMapping("/{id}/tasks")
    public EmployeeTasksDto getTasks(@PathVariable int id) {
        return employeeAppService.getTasks(id);
    }

    @PutMapping("/{id}/tasks")
    public EmployeeTasksDto setTasks(@PathVariable int id,
                                     @Valid @RequestBody EmployeeChangeTaskRequest employeeChangeTaskRequest) {
        return employeeAppService.setTasks(id, employeeChangeTaskRequest);
    }

    @PutMapping("/{employeeId}/tasks/{taskId}")
    public EmployeeTasksDto addTask(@PathVariable int employeeId, @PathVariable int taskId) {
        return employeeAppService.addTask(employeeId, taskId);
    }

    @DeleteMapping("/{employeeId}/tasks/{taskId}")
    public EmployeeTasksDto removeTask(@PathVariable int employeeId, @PathVariable int taskId) {
        return employeeAppService.removeTask(employeeId, taskId);
    }
}
