package org.launchcode.eventplanning.controllers;

import org.launchcode.eventplanning.models.DTO.EventUserDTO;
import org.launchcode.eventplanning.models.Event;
import org.launchcode.eventplanning.models.User;
import org.launchcode.eventplanning.models.data.EventRepository;
import org.launchcode.eventplanning.models.data.EventUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
/*@Repository(value="signup")*/
@RequestMapping("users")
public class EventUserController {
    @Autowired
    private EventUserRepository eventUserRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String createSignUpPage(Model model) {
        model.addAttribute("title", "Sign Up");
        model.addAttribute("signup", eventUserRepository.findAll());
        return "/signup";
    }
    @GetMapping("signup/{eventId}")
    public String displaySignUpForm(Model model){
        model.addAttribute("title", "Sign Up");
        model.addAttribute(new Event());
        return "/signup";
    }

    @PostMapping
    public String processAddingEventForm(@ModelAttribute @Valid )
}
