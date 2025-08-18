package com.romankrivtsov.tms.dto.response.task;

import com.romankrivtsov.tms.dto.response.employee.EmployeeSummaryDto;
import com.romankrivtsov.tms.entity.Task;
import com.romankrivtsov.tms.entity.enums.TaskStatus;

import java.util.List;
import java.util.stream.Collectors;

public class TaskDetailDto {
    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private List<EmployeeSummaryDto> performers;

    public static TaskDetailDto from(Task task){
        TaskDetailDto taskDetailDto = new TaskDetailDto();
        taskDetailDto.setId(task.getId());
        taskDetailDto.setTitle(task.getTitle());
        taskDetailDto.setDescription(task.getDescription());
        taskDetailDto.setStatus(task.getStatus());

        taskDetailDto.setPerformers(task.getPerformers().stream()
                .map(EmployeeSummaryDto::from)
                .collect(Collectors.toList()));
        return taskDetailDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<EmployeeSummaryDto> getPerformers() {
        return performers;
    }

    public void setPerformers(List<EmployeeSummaryDto> performers) {
        this.performers = performers;
    }
}
