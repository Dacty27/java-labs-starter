package edu.course.lab03;

public class TaskPrioritizer {
    private final PriorityPolicy policy;

    public TaskPrioritizer(PriorityPolicy policy) {
        if (policy == null) {
            throw new IllegalArgumentException("policy must not be null");
        }
        this.policy = policy;
    }

    public ProjectTask pickHigher(ProjectTask a, ProjectTask b) {
        int pa = policy.calculatePriority(a);
        int pb = policy.calculatePriority(b);
        return pa >= pb ? a : b;
    }
}