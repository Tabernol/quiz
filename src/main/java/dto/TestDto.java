package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
    private long id;
    private String name;
    private String subject;
    private int difficult;
    private int duration;
    private int popularity;

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
