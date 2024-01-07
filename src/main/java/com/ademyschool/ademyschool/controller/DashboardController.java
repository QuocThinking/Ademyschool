package com.ademyschool.ademyschool.controller;

import com.ademyschool.ademyschool.model.Person;
import com.ademyschool.ademyschool.service.PersonService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    private PersonService personService;
    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session){
        Person person = personService.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("LoggedInPerson", person);
        return "dashboard.html";
//        throw new RuntimeException("It's been a bad day");
    }
}
