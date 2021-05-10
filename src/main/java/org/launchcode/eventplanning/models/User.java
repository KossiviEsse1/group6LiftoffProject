package org.launchcode.eventplanning.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

        @Id
        @GeneratedValue
        private int id;

        @NotNull
        @Size(min=3, max=15)
        private String username;

        @NotNull
        private String pwHash;

        @ManyToMany(mappedBy = "users")
        private List<Event> events = new ArrayList<>();

        private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        private String role;

        public User() {}

        public User(String username, String password, String role) {
            this.username = username;
            this.pwHash = encoder.encode(password);
            this.role = role;
        }

        public String getRole() {
                return role;
        }

        public void setRole(String role) {
                this.role = role;
        }

        public int getId() {
            return id;
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


