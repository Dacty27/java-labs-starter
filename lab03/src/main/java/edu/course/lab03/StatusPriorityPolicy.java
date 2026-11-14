package edu.course.lab03;

public class StatusPriorityPolicy implements PriorityPolicy {
    @Override
    public int calculatePriority(ProjectTask task) {
        switch (task.getStatus()) {
            case "NEW":         return 3;
            case "IN_PROGRESS": return 2;
            case "DONE":        return 1;
            default:            return 0;
        }
    }
}