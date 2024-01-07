package com.ademyschool.ademyschool.controller;

import com.ademyschool.ademyschool.model.Contact;
import com.ademyschool.ademyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
    public String saveMessage(@Valid  @ModelAttribute("contact") Contact contact, Errors errors){
        if(errors.hasErrors()){
            log.info("Contact form validation failed due to : " + contact.toString());
            return "contact.html";
        }
        contactService.saveContactMessage(contact);
        return  "redirect:/contact";
    }

    @RequestMapping(value = "/displayMessages")
    public ModelAndView displayMessage(Model model ){
        List<Contact> contactMsgs = contactService.findMessageWithOpenStatus();
        ModelAndView messPage = new ModelAndView();
        messPage.setViewName("messages");
        messPage.addObject("contactMsgs", contactMsgs);
        return messPage;
    }

    @RequestMapping(value = "/closeMsg", method = RequestMethod.GET)
    public String closeMsg(@RequestParam int id ){
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages";
    }
}
