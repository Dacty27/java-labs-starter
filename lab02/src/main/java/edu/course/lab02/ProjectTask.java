package edu.course.lab02;

public class ProjectTask {

    private final String id;
    private final String title;
    private TaskStatus status;
    private int estimatedHours;

    public ProjectTask(String id, String title, int estimatedHours) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Идентификатор не может быть null или пустым");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название не может быть null или пустым");
        }
        if (estimatedHours <= 0) {
            throw new IllegalArgumentException("Оценка трудоёмкости должна быть положительной");
        }
        this.id = id;
        this.title = title;
        this.estimatedHours = estimatedHours;
        this.status = TaskStatus.NEW;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public TaskStatus getStatus() {
        return status;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void changeStatus(TaskStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Статус не может быть null");
        }
        this.status = newStatus;
    }

    public boolean isDone() {
        return status == TaskStatus.DONE;
    }

    public void increaseEstimate(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("Добавляемое количество часов должно быть положительным");
        }
        this.estimatedHours += hours;
    }
}