package com.ademyschool.ademyschool.service;

import com.ademyschool.ademyschool.constants.AdemyschoolConstant;
import com.ademyschool.ademyschool.model.Contact;
import com.ademyschool.ademyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    private final ContactRepository contactRepository;


    @Autowired
    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }
    public boolean saveContactMessage(Contact contact){
        boolean isSaved = false;
        contact.setStatus(AdemyschoolConstant.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(savedContact != null && savedContact.getContactId() > 0){
            isSaved = true;
        }
        return  isSaved;
    }

    public List<Contact> findMessageWithOpenStatus(){
        List<Contact> contacts  = contactRepository.findByStatus(AdemyschoolConstant.OPEN);
        return contacts;
    }


    public boolean updateMsgStatus(int contactId){
        boolean isValue = false;
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(AdemyschoolConstant.CLOSE);
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if(updatedContact != null && updatedContact.getUpdatedBy() != null){
            isValue = true;
        }
        return isValue;
    }
}
