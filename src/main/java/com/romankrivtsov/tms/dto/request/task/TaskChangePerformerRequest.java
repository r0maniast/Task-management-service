package com.romankrivtsov.tms.dto.request.task;

import jakarta.validation.constraints.NotNull;

public class TaskChangePerformerRequest {

    @NotNull
    private int idPerformers;

    public int getIdPerformers() {
        return idPerformers;
    }

    public void setIdPerformers(int idPerformers) {
        this.idPerformers = idPerformers;
    }
}
