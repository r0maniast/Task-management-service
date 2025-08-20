package com.romankrivtsov.tms.dto.request.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartmentRequest {
    @NotBlank(groups = CreateValidate.class)
    @Size(min = 1)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public interface CreateValidate{}
    public interface UpdateValidate{}

}
