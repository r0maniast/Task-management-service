package com.romankrivtsov.tms.dto.request.employeeRequest;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class EmployeeChangeTaskRequest {

    @NotNull
    private List<Integer> tasksId;

    public List<Integer> getTasksId() {
        return tasksId;
    }

    public void setTasksId(List<Integer> tasksId) {
        this.tasksId = tasksId;
    }
}
