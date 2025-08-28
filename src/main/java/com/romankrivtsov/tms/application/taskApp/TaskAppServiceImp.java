package com.romankrivtsov.tms.application.taskApp;

import com.romankrivtsov.tms.dto.request.taskRequest.TaskChangePerformerRequest;
import com.romankrivtsov.tms.dto.request.taskRequest.TaskRequest;
import com.romankrivtsov.tms.dto.response.taskResponse.TaskDetailDto;
import com.romankrivtsov.tms.dto.response.taskResponse.TaskSummaryDto;
import com.romankrivtsov.tms.entity.Employee;
import com.romankrivtsov.tms.entity.Task;
import com.romankrivtsov.tms.entity.enums.TaskStatus;
import com.romankrivtsov.tms.service.departmentService.DepartmentService;
import com.romankrivtsov.tms.service.employeeService.EmployeeService;
import com.romankrivtsov.tms.service.taskService.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskAppServiceImp implements TaskAppService{
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final TaskService taskService;

    @Autowired
    public TaskAppServiceImp(EmployeeService employeeService, DepartmentService departmentService,
                             TaskService serviceImp) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.taskService = serviceImp;
    }

    @Override
    public List<TaskSummaryDto> getAllTasksSummary() {
        List<Task> allTasks = taskService.getAllTasks();
        return allTasks.stream()
                .map(TaskSummaryDto::from)
                .toList();
    }

    @Override
    public TaskDetailDto getTaskWithPerformers(int taskId) {
        Task task = taskService.getTask(taskId);
        return TaskDetailDto.from(task);
    }

    @Override
    public TaskDetailDto saveTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        Task savedTask = taskService.saveTask(task);
        return TaskDetailDto.from(savedTask);
    }

    @Override
    public TaskDetailDto updateTask(int id, TaskRequest taskRequest) {
        Task task = taskService.getTask(id);
        String title = taskRequest.getTitle();
        String description = taskRequest.getDescription();
        TaskStatus status = taskRequest.getStatus();
        if (title != null)
            task.setTitle(title);
        if (description != null)
            task.setDescription(description);
        if (status != null)
            task.setStatus(status);
        Task updatedTask = taskService.updateTask(task);
        return TaskDetailDto.from(updatedTask);
    }

    @Override
    public TaskDetailDto setPerformers(int taskId, TaskChangePerformerRequest taskChangePerformerRequest) {
        Task task = taskService.getTask(taskId);
        Set<Employee> performers = new HashSet<>();
        List<Integer> performersId = taskChangePerformerRequest.getPerformersId();
        for(int performerId : performersId) {
            performers.add(employeeService.getEmployee(performerId));
        }
        task.setPerformers(performers);
        Task updatedTask = taskService.updateTask(task);
        return TaskDetailDto.from(updatedTask);
    }

    @Override
    public TaskDetailDto addPerformer(int idTask, int idEmployee) {
        Task task = taskService.getTask(idTask);
        task.addPerformer(employeeService.getEmployee(idEmployee));
        Task updatedTask = taskService.updateTask(task);
        return TaskDetailDto.from(updatedTask);
    }

    @Override
    public TaskDetailDto removePerformers(int idTask, int idEmployee) {
        Task task = taskService.getTask(idTask);
        task.removePerformer(employeeService.getEmployee(idEmployee));
        Task updatedTask = taskService.updateTask(task);
        return TaskDetailDto.from(updatedTask);
    }

    @Override
    public void deleteTask(int idTask) {
        taskService.deleteTask(idTask);
    }
}
