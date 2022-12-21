package models;

public class Test {
    private long id;
    private  long subjectId;
    private String name;
    private int difficult;
    private int duration; //DateTime
    private int amountQuestions;


    public Test() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String text) {
        this.name = text;
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

    public int getAmountQuestions() {
        return amountQuestions;
    }

    public void setAmountQuestions(int amountQuestions) {
        this.amountQuestions = amountQuestions;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", text='" + name + '\'' +
                ", difficult=" + difficult +
                ", duration=" + duration +
                ", amountQuestions=" + amountQuestions +
                '}';
    }
}
