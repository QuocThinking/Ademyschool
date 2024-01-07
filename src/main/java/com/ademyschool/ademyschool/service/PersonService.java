package com.ademyschool.ademyschool.service;

import com.ademyschool.ademyschool.constants.AdemyschoolConstant;
import com.ademyschool.ademyschool.model.Person;
import com.ademyschool.ademyschool.model.Roles;
import com.ademyschool.ademyschool.repository.PersonRepository;
import com.ademyschool.ademyschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class PersonService {

    private final PersonRepository personRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, RolesRepository rolesRepository){
        this.personRepository = personRepository;
        this.rolesRepository = rolesRepository;
    }


    public boolean createNewPerson(Person person){
        boolean isValue = false;
        Roles roles = rolesRepository.getByRoleName(AdemyschoolConstant.STUDENT_ROLE);
        person.setRoles(roles);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if(person != null && person.getPersonId() > 0){
            isValue = true;
        }
        return isValue;

    }

    public  Person readByEmail(String email){
        return personRepository.readByEmail(email);
    }
}
