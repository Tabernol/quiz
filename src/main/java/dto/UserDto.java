package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.User;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String name;
    private String role;
    private String repeatPassword;
    private boolean isBlocked;

    public UserDto(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public UserDto(String login, String name) {
        this.login = login;
        this.name = name;
    }
}
