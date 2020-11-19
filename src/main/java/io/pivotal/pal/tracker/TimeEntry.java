package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public TimeEntry(long timeEntryId, long projectId, long userId, LocalDate parse, int hours) {
        this.id = timeEntryId;
        this.projectId = projectId;
        this.date = parse;
        this.userId = userId;
        this.hours = hours;
    }

    public TimeEntry(long projectId, long userId, LocalDate parse, int hours) {
        this.projectId = projectId;
        this.date = parse;
        this.userId = userId;
        this.hours = hours;
    }
}
