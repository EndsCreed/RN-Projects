package com.renatusnetwork;

public class Enums {

    public enum TaskStatus {
        PENDING,
        APPROVED,
        REJECTED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED,
        STALLED
    }

    public enum TaskPriority {
        CRITICAL,
        HIGH,
        MEDIUM,
        LOW
    }

    public enum TaskType {
        FIX,
        UPDATE,
        REQUEST,
        PROJECT
    }

    public enum Division {
        BUILDER,
        MODERATION,
        DEVELOPER,
        PR,
        MANAGEMENT
    }
}