package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private String testName;
    private String subject;
    private Integer difficult;
    private Integer duration;
    private Integer grade;
}
