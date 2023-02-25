package models;

import java.util.List;

public class Test {
    private long id;
    private String name;
    private String subject;
    private int difficult;
    private int duration; //DateTime???
    private int popularity;

    private Status status;

    public Test() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", difficult=" + difficult +
                ", duration=" + duration +
                '}';
    }

    public enum Status {
        BLOCKED("blocked"),
        FREE("free"),
        PAID("paid");

        private String status;

        Status(String status) {
            this.status = status;
        }
    }
}
