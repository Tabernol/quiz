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

}
