package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
@Data
@NoArgsConstructor
public class Question {
    private long id;
    private long testId;
    private String text;
    private String urlImage;
}
