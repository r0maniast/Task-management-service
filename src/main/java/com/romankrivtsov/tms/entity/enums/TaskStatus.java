package com.romankrivtsov.tms.entity.enums;

public enum TaskStatus {
    REGISTERED("зарегистрирована"),
    ANALYSIS("анализ"),
    IN_PROGRESS("в работе"),
    TESTING("тестирование"),
    CLARIFICATION("на уточнении"),
    PROD_DEPLOY("перенос на прод"),
    DONE("выполнена");

    private final String dbValue;

    TaskStatus(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static TaskStatus fromDb(String value) {
        for (TaskStatus s : values()) if (s.dbValue.equals(value)) return s;
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
