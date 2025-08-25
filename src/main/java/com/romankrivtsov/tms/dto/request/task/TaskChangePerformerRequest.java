package com.romankrivtsov.tms.dto.request.task;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class TaskChangePerformerRequest {

    @NotNull
    private List<Integer> performersId;

    public List<Integer> getPerformersId() {
        return performersId;
    }

    public void setPerformersId(List<Integer> performersId) {
        this.performersId = performersId;
    }
}
