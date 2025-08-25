package com.romankrivtsov.tms.dto.response.task;

import com.romankrivtsov.tms.dto.response.employee.EmployeeSummaryDto;
import com.romankrivtsov.tms.entity.Employee;
import com.romankrivtsov.tms.entity.Task;
import com.romankrivtsov.tms.entity.enums.TaskStatus;

import java.util.Set;
import java.util.stream.Collectors;

public class TaskDetailDto {
    private int idTask;
    private String title;
    private String description;
    private TaskStatus status;
    private Set<EmployeeSummaryDto> performers;

    public static TaskDetailDto from(Task task) {
        TaskDetailDto taskDetailDto = new TaskDetailDto();
        taskDetailDto.setIdTask(task.getId());
        taskDetailDto.setTitle(task.getTitle());
        taskDetailDto.setDescription(task.getDescription());
        taskDetailDto.setStatus(task.getStatus());

        Set<Employee> performers = task.getPerformers();
        if (performers != null) {
            taskDetailDto.setPerformers(performers.stream()
                    .map(EmployeeSummaryDto::from)
                    .collect(Collectors.toSet()));
        }
        return taskDetailDto;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Set<EmployeeSummaryDto> getPerformers() {
        return performers;
    }

    public void setPerformers(Set<EmployeeSummaryDto> performers) {
        this.performers = performers;
    }
}
