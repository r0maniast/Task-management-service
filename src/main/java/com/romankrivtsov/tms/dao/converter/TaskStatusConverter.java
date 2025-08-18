package com.romankrivtsov.tms.dao.converter;

import com.romankrivtsov.tms.entity.enums.TaskStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TaskStatusConverter implements AttributeConverter<TaskStatus, String> {
    @Override
    public String convertToDatabaseColumn(TaskStatus attribute) {
        return attribute == null ? null : attribute.getDbValue();
    }

    @Override
    public TaskStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : TaskStatus.fromDb(dbData);
    }
}
