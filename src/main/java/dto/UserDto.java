package dto;

import models.User;

public class UserDto {
    private long id;
    private String login;
    private String password;
    private String name;
    private User.Role role;
    private boolean isBlocked;
}
