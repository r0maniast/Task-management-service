package com.romankrivtsov.tms.application;

import com.romankrivtsov.tms.dto.request.task.TaskChangePerformerRequest;
import com.romankrivtsov.tms.dto.request.task.TaskRequest;
import com.romankrivtsov.tms.dto.response.task.TaskDetailDto;
import com.romankrivtsov.tms.dto.response.task.TaskSummaryDto;
import com.romankrivtsov.tms.entity.Task;
import com.romankrivtsov.tms.entity.enums.TaskStatus;
import com.romankrivtsov.tms.service.departmentService.DepartmentServiceImp;
import com.romankrivtsov.tms.service.employeeService.EmployeeServiceImp;
import com.romankrivtsov.tms.service.taskService.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskAppService {
    private final EmployeeServiceImp employeeServiceImp;
    private final DepartmentServiceImp departmentServiceImp;
    private final TaskServiceImp taskServiceImp;

    @Autowired
    public TaskAppService(EmployeeServiceImp employeeServiceImp, DepartmentServiceImp departmentServiceImp,
                          TaskServiceImp taskServiceImp) {
        this.employeeServiceImp = employeeServiceImp;
        this.departmentServiceImp = departmentServiceImp;
        this.taskServiceImp = taskServiceImp;
    }

    public List<TaskSummaryDto> getAllTasksSummary() {
        List<Task> allTasks = taskServiceImp.getAllTasks();
        return allTasks.stream()
                .map(TaskSummaryDto::from)
                .toList();
    }

    public TaskDetailDto getTaskWithPerformers(int taskId) {
        Task task = taskServiceImp.getTask(taskId);
        return TaskDetailDto.from(task);
    }

    public TaskDetailDto saveTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        Task savedTask = taskServiceImp.saveTask(task);
        return TaskDetailDto.from(savedTask);
    }

    public TaskDetailDto updateTask(int id, TaskRequest taskRequest) {
        Task task = taskServiceImp.getTask(id);
        String title = taskRequest.getTitle();
        String description = taskRequest.getDescription();
        TaskStatus status = taskRequest.getStatus();
        if (title != null)
            task.setTitle(title);
        if (description != null)
            task.setDescription(description);
        if (status != null)
            task.setStatus(status);
        Task updatedTask = taskServiceImp.updateTask(task);
        return TaskDetailDto.from(updatedTask);
    }

    public TaskDetailDto addPerformers(int id, TaskChangePerformerRequest taskChangePerformerRequest) {
        Task task = taskServiceImp.getTask(id);
        task.addPerformer(employeeServiceImp.getEmployee(taskChangePerformerRequest.getIdPerformers()));
        Task updatedTask = taskServiceImp.updateTask(task);
        return TaskDetailDto.from(updatedTask);
    }

    public TaskDetailDto removePerformers(int id, TaskChangePerformerRequest taskChangePerformerRequest) {
        Task task = taskServiceImp.getTask(id);
        task.removePerformer(employeeServiceImp.getEmployee(taskChangePerformerRequest.getIdPerformers()));
        Task updatedTask = taskServiceImp.updateTask(task);
        return TaskDetailDto.from(updatedTask);
    }

    public void deleteTask(int idTask) {
        taskServiceImp.deleteTask(idTask);
    }
}
