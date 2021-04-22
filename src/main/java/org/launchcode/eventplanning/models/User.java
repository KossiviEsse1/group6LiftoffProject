package org.launchcode.eventplanning.models;

import com.sun.istack.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

        @Id
        @GeneratedValue
        private int id;@NotNull

        @Size(min=3, max=15)
        private String username;

        @NotNull
        private String pwHash;

        public User() {}

        public User(String username, String password) {
            this.username = username;
            this.pwHash = encoder.encode(password);
        }

    public int getId() {
        return id;
    }

    public String getUsername() {
            return username;
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}

