package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    private String name;
    private Role role;
    private boolean isBlocked;

    public enum Role {
        ADMIN("admin"),
        STUDENT("student");

        private final String role;

        private Role(String role) {
            this.role = role;
        }

        public String getRole(){
            return this.role;
        }
    }

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }
}
