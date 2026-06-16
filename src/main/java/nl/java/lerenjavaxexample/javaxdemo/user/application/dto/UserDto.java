package nl.java.lerenjavaxexample.javaxdemo.user.application.dto;

import javax.validation.constraints.NotNull;

public class UserDto {

    private final Long id;

    @NotNull
    private final String username;

    @NotNull
    private final String password;

    @NotNull
    private final String email;

    public UserDto(Long id,
                   String username,
                   String password,
                   String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}