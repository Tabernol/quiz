package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    private Long id;
    private Long questionId;
    private String text;
    private Boolean result;

    public AnswerDto(Long questionId, String text, Boolean result) {
        this.questionId = questionId;
        this.text = text;
        this.result = result;
    }
}
