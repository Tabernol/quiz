package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    private Long testId;
    private String text;
    private String urlImage;

    public QuestionDto(Long testId, String text) {
        this.testId = testId;
        this.text = text;
    }
}
