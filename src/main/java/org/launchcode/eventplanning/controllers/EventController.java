package org.launchcode.eventplanning.controllers;

import org.launchcode.eventplanning.models.Event;
import org.launchcode.eventplanning.models.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping("events")
    public String index(Model model){
        model.addAttribute("events", eventRepository.findAll());
        return "events";
    }

    @GetMapping("add")
    public String displayAddEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        return "add";
    }

    @PostMapping("add")
    public String processAddEventForm(@ModelAttribute Event newEvent,
                                      Errors errors, Model model) {
        if (errors.hasErrors()){
            model.addAttribute("title","Create Event");
            return "add";
        }
        eventRepository.save(newEvent);
        return "redirect:";
    }

}
