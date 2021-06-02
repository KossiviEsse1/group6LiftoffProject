package org.launchcode.eventplanning.controllers;

import org.launchcode.eventplanning.models.DTO.EventUserDTO;
import org.launchcode.eventplanning.models.Event;
import org.launchcode.eventplanning.models.User;
import org.launchcode.eventplanning.models.data.EventRepository;
import org.launchcode.eventplanning.models.data.EventUserRepository;
import org.launchcode.eventplanning.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;


@Controller
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationController authenticationController;
    @Autowired
    private EventUserRepository eventUserRepository;


    @RequestMapping("events")
    public String index(Model model, HttpServletRequest request){
        model.addAttribute("events", eventRepository.findAll());
        HttpSession session = request.getSession();
        model.addAttribute("title", "Events");
        return "events";
    }

    @GetMapping("add")
    public String displayAddEventForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        User user = authenticationController.getUserFromSession(session);
        if(user.getRole().equals("organization")) {
            model.addAttribute("title", "Create Event");
            model.addAttribute(new Event());
            return "add";
        }
        return "redirect:";
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
    @GetMapping("update/{eventID}")
    public String displayUpdateEventForm(Model model, @PathVariable int eventID){
        Optional optEvent = eventRepository.findById(eventID);
        if (optEvent.isPresent()) {
            Event event = (Event) optEvent.get();
        model.addAttribute("event", event);
            model.addAttribute("title", "Update");
        return "update";
        } else {
            return "redirect:../";
        }
    }
    @PostMapping("update/{eventID}")
    public String processUpdateEventForm(@ModelAttribute Event currentEvent,
                                      Errors errors, Model model, @PathVariable int eventID) {
        if (errors.hasErrors()){
            model.addAttribute("title","Update Event");
            return "update";
        }
        Event currentEvent1 = eventRepository.findById(eventID).orElse(new Event());
        currentEvent1.setDescription(currentEvent.getDescription());
        currentEvent1.setLocation(currentEvent.getLocation());
        currentEvent1.setName(currentEvent.getName());
        eventRepository.save(currentEvent1);
        return "redirect:/";
    }
    @GetMapping("delete/{eventID}")
    public String displayDeleteEventForm(Model model, @PathVariable int eventID){
        model.addAttribute("title", "Delete Events");
        Event event = eventRepository.findById(eventID).orElse(new Event());
        model.addAttribute("event", event);
        return "delete";
    }
    @PostMapping("delete/{eventID}")
    public String processDeleteEventForm(@RequestParam String yesOrNo, @RequestParam int eventID){
        if (yesOrNo.equals("yes")) {
            eventRepository.deleteById(eventID);
        }
        return "redirect:/";
    }
    @GetMapping("view/{eventID}")
    public String displayViewEventForm(Model model, @PathVariable int eventID) {
        Optional optEvent = eventRepository.findById(eventID);
        if (optEvent.isPresent()) {
            Event event = (Event) optEvent.get();
            model.addAttribute("title", "View Event Details");
            model.addAttribute("event", event);
            return "view";
        }else{
            model.addAttribute("events", eventRepository.findAll());
            return "redirect:../";
        }
    }
    
    @PostMapping("view/{eventID}")
    public String processViewEventForm(@ModelAttribute Event currentEvent,
                                         Errors errors, Model model, @PathVariable int eventID) {
        if (errors.hasErrors()){
            model.addAttribute("title","View Event");
            return "view";
        }
        Optional<Event> currentEvent2 = eventRepository.findById(eventID);
        return "redirect:/";
    }

/*    @GetMapping("signup")
    public String signingUp(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Event: " + event.getUsers());
        model.addAttribute("users", eventUserRepository.findAll());
        EventUserDTO eventUser = new EventUserDTO();
        eventUser.setEvent(event);
        model.addAttribute("eventUser", eventUser);
        return "signup";
    }*/

    @GetMapping("signup/{eventId}")
    public String signingUp(@PathVariable int eventId,
                            Model model,
                            HttpServletRequest request){

        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        HttpSession session = request.getSession();
        model.addAttribute("user", authenticationController.getUserFromSession(session));
        User user = authenticationController.getUserFromSession(session);
        if(user.getRole().equals("volunteer")){
            model.addAttribute("title", "Add Event: " + event.getUsers());
            EventUserDTO eventUser = new EventUserDTO();
            eventUser.setEvent(event);
            model.addAttribute("eventUser", eventUser);
        }
        return "redirect:";
    }

    @PostMapping("signup/{eventId}")
    public String signingUp2(@ModelAttribute @Valid EventUserDTO eventUser,
                                    Errors errors,
                                    Model model){
        if (!errors.hasErrors()) {
            Event event = eventUser.getEvent();
            User user = eventUser.getUser();
            if (!event.getUsers().contains(user)){
                event.addUser(user);
                eventRepository.save(event);
            }
            return "redirect:detail?eventId=" + event.getId();
        }
        return "redirect:/";
    }
}

