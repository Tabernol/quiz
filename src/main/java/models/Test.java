package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    private Long id;
    private String name;
    private String subject;
    private Integer difficult;
    private Integer duration; //DateTime???
    private Integer popularity;

    private Status status;

    public enum Status {
        BLOCKED("blocked"),
        FREE("free");

        private String status;

        private Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return this.status;
        }
    }

    public Test(String name, String subject, Integer difficult, Integer duration) {
        this.name = name;
        this.subject = subject;
        this.difficult = difficult;
        this.duration = duration;
    }
}
