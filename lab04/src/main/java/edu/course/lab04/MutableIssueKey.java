package edu.course.lab04;

public final class MutableIssueKey {

    private String projectCode;
    private int number;

    public MutableIssueKey(String projectCode, int number) {
        this.projectCode = projectCode;
        this.number = number;
    }

    public void setProjectCode(String projectCode) { this.projectCode = projectCode; }
    public void setNumber(int number)              { this.number = number; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutableIssueKey that = (MutableIssueKey) o;
        return number == that.number && projectCode.equals(that.projectCode);
    }

    @Override
    public int hashCode() {
        return 31 * projectCode.hashCode() + number;
    }
}