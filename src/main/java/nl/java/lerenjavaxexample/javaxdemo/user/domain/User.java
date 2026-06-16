package nl.java.lerenjavaxexample.javaxdemo.user.domain;

import javax.persistence.*;

@Entity
@Table(name="expense_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;


    public User() {
    }

    public User(Long id, String username, String password, String email) {
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

    public void updateUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
