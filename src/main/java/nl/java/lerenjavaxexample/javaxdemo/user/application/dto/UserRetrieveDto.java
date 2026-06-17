package nl.java.lerenjavaxexample.javaxdemo.user.application.dto;

import jakarta.validation.constraints.NotNull;

public class UserRetrieveDto {

    private final Long id;

    @NotNull
    private final String username;

    @NotNull
    private final String email;

    public UserRetrieveDto(Long id,
                           String username,
                           String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}