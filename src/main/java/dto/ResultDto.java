package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDto {
    private String testName;
    private String subject;
    private Integer difficult;
    private Integer duration;
    private Integer grade;
    private LocalDate date;
}
