package dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionDto {
    private long id;
    private long testId;
    private String text;
    private String urlImage;
}
