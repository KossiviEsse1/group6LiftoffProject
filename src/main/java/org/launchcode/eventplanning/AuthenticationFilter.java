package org.launchcode.eventplanning;

import org.launchcode.eventplanning.controllers.AuthenticationController;
import org.launchcode.eventplanning.models.User;
import org.launchcode.eventplanning.models.data.UserRepository;
import org.launchcode.eventplanning.models.dto.LoginFormDTO;
import org.launchcode.eventplanning.models.dto.RegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        // The user is logged in
        if (user != null && user.getRole().equals("organization")){
            return true;
        //if (user != null && user.getRole().equals("organization")) {
                //if(user.getRole().equals("organization")){
                   // return true;
               //}//else{
                   // response.sendRedirect("/login");
               // }
            }
            // The user is NOT logged in
            response.sendRedirect("/login");
            return false;
        }
        private static boolean isWhitelisted (String path){
            for (String pathRoot : whitelist) {
                if (path.startsWith(pathRoot)) {
                    return true;
                }
            }
            return false;
        }
    }

