package org.launchcode.eventplanning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FragmentsController {

   @Autowired
   private AuthenticationController authenticationController;
   @RequestMapping("fragments")
        public String index(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        model.addAttribute("user", authenticationController.getUserFromSession(session));
        return "fragments.html";
    }
}
