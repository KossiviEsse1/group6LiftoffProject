package org.launchcode.eventplanning.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

    @Entity
    public class Organization {


        @Id
        @GeneratedValue
        private int id;

        @NotBlank(message = "organization is required")
        @Size(min = 2, message = "organization must be at least 2 characters long")
        private String organization;


        @OneToMany
        @JoinColumn(name = "employer_id")
        private final List<Event> events = new ArrayList<>();

        public Organization(@NotBlank(message = "organization is required")
                            @Size(min = 2, message = "organization must be at least 2 characters long")
                                    String organization) {
            this.organization = organization;
        }

        public Organization() {
        }

        public String getorganization() {
            return organization;
        }

        public void setorganization(String organization) {
            this.organization = organization;
        }

        public List<Event> getEvents() {
            return events;
        }
    }

