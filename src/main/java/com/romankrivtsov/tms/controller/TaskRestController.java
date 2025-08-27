package com.romankrivtsov.tms.controller;

import com.romankrivtsov.tms.application.task.TaskAppService;
import com.romankrivtsov.tms.dto.request.task.TaskChangePerformerRequest;
import com.romankrivtsov.tms.dto.request.task.TaskRequest;
import com.romankrivtsov.tms.dto.response.task.TaskDetailDto;
import com.romankrivtsov.tms.dto.response.task.TaskSummaryDto;
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
@RequestMapping("/tasks")
public class TaskRestController {
    private final TaskAppService taskAppService;

    @Autowired
    public TaskRestController(TaskAppService taskAppService) {
        this.taskAppService = taskAppService;
    }

    @GetMapping
    public List<TaskSummaryDto> getAllTasks() {
        return taskAppService.getAllTasksSummary();
    }

    @GetMapping("/{id}")
    public TaskDetailDto getTaskWithPerformers(@PathVariable int id) {
        return taskAppService.getTaskWithPerformers(id);
    }

    @PostMapping
    public ResponseEntity<TaskDetailDto> saveTask(@Validated(CreateValidate.class)
                                                              @RequestBody TaskRequest taskRequest) {
        TaskDetailDto taskDetailDto = taskAppService.saveTask(taskRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskDetailDto.getIdTask())
                .toUri();

        return ResponseEntity.created(location).body(taskDetailDto);
    }

    @PutMapping("/{id}")
    public TaskDetailDto updateTask(@PathVariable int id,
                                    @Validated(UpdateValidate.class)
                                    @RequestBody TaskRequest taskRequest) {
        return taskAppService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int id){
        taskAppService.deleteTask(id);
    }

    @PutMapping("/{id}/performers")
    public TaskDetailDto setPerformers(@PathVariable int id,
                                      @Valid @RequestBody TaskChangePerformerRequest taskChangePerformerRequest) {
        return taskAppService.setPerformers(id, taskChangePerformerRequest);
    }

    @PutMapping("/{taskId}/performers/{employeeId}")
    public TaskDetailDto addPerformer(@PathVariable int taskId,
                                      @PathVariable int employeeId) {
        return taskAppService.addPerformer(taskId, employeeId);
    }


    @DeleteMapping("/{taskId}/performers/{employeeId}")
    public TaskDetailDto removePerformer(@PathVariable int taskId,
                                         @PathVariable int employeeId) {
        return taskAppService.removePerformers(taskId, employeeId);
    }
}
