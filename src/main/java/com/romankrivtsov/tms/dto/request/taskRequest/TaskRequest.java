package com.romankrivtsov.tms.dto.request.taskRequest;

import com.romankrivtsov.tms.entity.enums.TaskStatus;
import com.romankrivtsov.tms.util.validate.CreateValidate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskRequest {

    @NotBlank(groups = CreateValidate.class)
    @Size(min = 1, max = 50, groups = CreateValidate.class)
    private String title;

    @Size(min = 1, max = 50, groups = CreateValidate.class)
    private String description;

    @NotNull(groups = CreateValidate.class)
    private TaskStatus status;

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
}
