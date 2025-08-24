package com.romankrivtsov.tms.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TaskStatus {
    @JsonProperty("REGISTERED") REGISTERED,
    @JsonProperty("ANALYSIS") ANALYSIS,
    @JsonProperty("IN_PROGRESS") IN_PROGRESS,
    @JsonProperty("TESTING") TESTING,
    @JsonProperty("CLARIFICATION") CLARIFICATION,
    @JsonProperty("PROD_DEPLOY") PROD_DEPLOY,
    @JsonProperty("DONE") DONE
}
