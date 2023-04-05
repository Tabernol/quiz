package dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"text", "questionId"})
public class AnswerDto {
    private long id;
    private long questionId;
    private String text;
    private boolean result;
}
