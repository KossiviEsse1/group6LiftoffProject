package org.launchcode.eventplanning.models.DTO;


import org.launchcode.eventplanning.models.Event;
import org.launchcode.eventplanning.models.User;

import javax.validation.constraints.NotNull;

public class EventUserDTO {
    @NotNull
    private Event event;

    @NotNull
    private User user;

    public EventUserDTO(){}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
