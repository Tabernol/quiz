package models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@NoArgsConstructor
public class Answer {
    private long id;
    private long questionId;
    private String text;
    private boolean result;

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
//                ", questionId=" + questionId +
//                ", text='" + text + '\'' +
                ", result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id && result == answer.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result);
    }
}
