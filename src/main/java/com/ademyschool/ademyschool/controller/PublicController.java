package com.ademyschool.ademyschool.controller;

import com.ademyschool.ademyschool.model.Person;
import com.ademyschool.ademyschool.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {

    private final PersonService personService;

    @Autowired
    public PublicController(PersonService personService){
        this.personService = personService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegisterPage(Model model){
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors){
        if(errors.hasErrors()){
            return "register.html";
        }
        boolean isValue = personService.createNewPerson(person);
        if(isValue){
            return "redirect:/login?register=true";
        }else{
            return "register.html";
        }

    }


}
