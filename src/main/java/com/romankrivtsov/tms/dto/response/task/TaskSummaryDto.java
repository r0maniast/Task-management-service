package com.romankrivtsov.tms.dto.response.task;

import com.romankrivtsov.tms.entity.Task;
import com.romankrivtsov.tms.entity.enums.TaskStatus;

public class TaskSummaryDto {
    private int idTask;
    private String title;
    private TaskStatus status;

    public static TaskSummaryDto from(Task task){
        TaskSummaryDto taskSummaryDto = new TaskSummaryDto();
        taskSummaryDto.setIdTask(task.getId());
        taskSummaryDto.setTitle(task.getTitle());
        taskSummaryDto.setStatus(task.getStatus());
        return taskSummaryDto;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
