package org.launchcode.eventplanning.controllers;

import org.launchcode.eventplanning.models.Event;
import org.launchcode.eventplanning.models.EventData;
import org.launchcode.eventplanning.models.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private EventRepository eventRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public SearchController() {
        columnChoices.put("all", "All");
        columnChoices.put("name", "Name");
        columnChoices.put("location", "Location");
        //columnChoices.put("description", "Description");

    }

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        Iterable<Event> events;

        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            events = eventRepository.findAll();
        } else {
            events = EventData.findByColumnAndValue(searchType, searchTerm, eventRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        //model.addAttribute("title", "Events with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("title", "Search Results: ");
        List<Event> result = StreamSupport.stream(events.spliterator(), false)
                .collect(Collectors.toList());

        if (result.isEmpty()){
            model.addAttribute("events", events);
            model.addAttribute("message", "No Results Found");
        }else{
            model.addAttribute("events", result);
            model.addAttribute("message", null);
        }
        return "search";

    }
}





