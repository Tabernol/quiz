package models;

public class Answer {
    private long id;
    private long questionId;
    private String text;
    private boolean result;

    public Answer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", text='" + text + '\'' +
                ", result=" + result +
                '}';
    }
}
