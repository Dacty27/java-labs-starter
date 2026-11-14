package edu.course.lab05;

public enum TaskStatus {
    NEW, READY, IN_PROGRESS, DONE;

    public static TaskStatus parse(String raw) {
        return TaskStatus.valueOf(raw.trim().toUpperCase());
    }
}