package edu.course.lab05;

import java.util.Objects;

public final class Task {
    private final String id;
    private final String title;
    private final String category;
    private final int estimatedHours;
    private final TaskStatus status;

    public Task(String id, String title, String category,
                int estimatedHours, TaskStatus status) {
        this.id = Objects.requireNonNull(id);
        this.title = Objects.requireNonNull(title);
        this.category = Objects.requireNonNull(category);
        this.estimatedHours = estimatedHours;
        this.status = Objects.requireNonNull(status);
    }

    public String id() { return id; }
    public String title() { return title; }
    public String category() { return category; }
    public int estimatedHours() { return estimatedHours; }
    public TaskStatus status() { return status; }

    @Override
    public String toString() {
        return "Task[" + id + ", " + title + ", " + category
                + ", " + estimatedHours + ", " + status + "]";
    }
}