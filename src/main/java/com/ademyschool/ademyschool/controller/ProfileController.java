package com.ademyschool.ademyschool.controller;

import com.ademyschool.ademyschool.model.Person;
import com.ademyschool.ademyschool.model.Profile;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class ProfileController {

    @RequestMapping(value = "/displayProfile",method = RequestMethod.GET)
    public ModelAndView displayProfile(Model model, HttpSession httpSession){
        Person person = (Person) httpSession.getAttribute("LoggedInPerson");
        ModelAndView modelAndView = new ModelAndView();
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());
        if(person.getAddress() != null && person.getAddress().getAddressId() > 0){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setZipCode(person.getAddress().getZipCode());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
        }
        modelAndView.setViewName("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }
}
