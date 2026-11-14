package edu.course.lab04;

import java.util.Objects;

public final class ProjectTask {

    private final IssueId id;
    private final String title;
    private final String assignee;
    private String status;

    public ProjectTask(IssueId id, String title, String assignee) {
        this.id = Objects.requireNonNull(id, "id");
        this.title = Objects.requireNonNull(title, "title");
        this.assignee = Objects.requireNonNull(assignee, "assignee");
        this.status = "OPEN";
    }

    public IssueId id()       { return id; }
    public String title()     { return title; }
    public String assignee()  { return assignee; }
    public String status()    { return status; }

    public void setStatus(String status) {
        this.status = Objects.requireNonNull(status, "status");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTask that = (ProjectTask) o;
        return id.equals(that.id)
                && title.equals(that.title)
                && assignee.equals(that.assignee)
                && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, assignee, status);
    }

    @Override
    public String toString() {
        return "ProjectTask{" + id + ", '" + title + "', assignee='" + assignee
                + "', status='" + status + "'}";
    }
}