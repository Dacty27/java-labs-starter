package edu.course.lab03;

public class ProjectTask {
    private final String title;
    private final int effort;
    private final String status;

    public ProjectTask(String title, int effort, String status) {
        if (effort < 0) {
            throw new IllegalArgumentException("effort must be >= 0");
        }
        this.title = title;
        this.effort = effort;
        this.status = status;
    }

    public String getTitle() { return title; }
    public int getEffort()   { return effort; }
    public String getStatus() { return status; }
}
