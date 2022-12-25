package models;

import java.util.List;

public class Question {
    private long id;
    private long testId;
    private String text;
    private List<Answer> answerOptions;

    public Question() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<Answer> answerOptions) {
        this.answerOptions = answerOptions;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", testId=" + testId +
                ", text='" + text + '\'' +
                ", answerOptions=" + answerOptions +
                '}';
    }
}
