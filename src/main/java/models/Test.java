package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
@Data
@NoArgsConstructor
public class Test {
    private long id;
    private String name;
    private String subject;
    private int difficult;
    private int duration; //DateTime???
    private int popularity;

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
}
