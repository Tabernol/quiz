package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDto {
    private Long id;
    private String name;
    private String subject;
    private Integer difficult;
    private Integer duration;
    private Integer popularity;
    private String status;

    public TestDto(String name, String subject, int difficult, int duration) {
        this.name = name;
        this.subject = subject;
        this.difficult = difficult;
        this.duration = duration;
    }

    public TestDto(long id, String name, String subject, int difficult, int duration) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.difficult = difficult;
        this.duration = duration;
    }
}
