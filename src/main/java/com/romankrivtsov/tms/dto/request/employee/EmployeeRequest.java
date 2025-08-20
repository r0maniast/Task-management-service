package com.romankrivtsov.tms.dto.request.employee;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {
    @NotBlank(groups = CreateValidate.class)
    @Size(min = 1, max = 15, groups = CreateValidate.class)
    private String name;

    @Size(min = 1, max = 15, groups = CreateValidate.class)
    private String patronymic;

    @NotBlank(groups = CreateValidate.class)
    @Size(min = 1, max = 20, groups = CreateValidate.class)
    private String surname;

    @NotBlank(groups = CreateValidate.class)
    @Size(max = 30, groups = CreateValidate.class)
    private String position;

    @NotNull(groups = CreateValidate.class)
    @Min(value = 1, groups = CreateValidate.class)
    private int departmentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public interface CreateValidate{}
    public interface UpdateValidate{}
}
