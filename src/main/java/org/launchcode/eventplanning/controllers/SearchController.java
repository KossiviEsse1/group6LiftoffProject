package org.launchcode.eventplanning.controllers;

import org.launchcode.eventplanning.models.Event;
import org.launchcode.eventplanning.models.EventData;
import org.launchcode.eventplanning.models.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
    @RequestMapping("search")
    public class SearchController {

        @Autowired
        private EventRepository eventRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public SearchController () {

        columnChoices.put("all", "All");
        columnChoices.put("location", "Location");
        columnChoices.put("description", "Description");

    }

    @RequestMapping("")
        public String search(Model model) {
            model.addAttribute("columns", columnChoices);
            return "search";
        }

        @PostMapping("results")
        public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
            Iterable<Event> events;

            if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
                events = eventRepository.findAll();
            } else {
                events = EventData.findByColumnAndValue(searchType, searchTerm, eventRepository.findAll());
            }
            model.addAttribute("columns", columnChoices);
            model.addAttribute("title", "Events with " + columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("events", events);

            return "search";
        }
    }

