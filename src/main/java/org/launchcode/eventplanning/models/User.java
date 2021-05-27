package org.launchcode.eventplanning.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public class User /*extends AbstractEntity*/{

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String username;

    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private String role;

    @ManyToMany(mappedBy = "volunteers", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private final List<Event> events = new ArrayList<>();

   public User(String username, String password, String role) {
            this.username = username;
            this.pwHash = encoder.encode(password);
            this.role = role;
        }

    public User() { }

    public int getId() {
        return id;
    }

    public String getRole() {
                return role;
        }

    public void setRole(String role) {
                this.role = role;
        }

    public String getUsername() {
                return username;
        }

    public boolean isMatchingPassword(String password) {
            return encoder.matches(password, pwHash);
        }

    public List<Event> getEvents() {
        return events;
    }

}



