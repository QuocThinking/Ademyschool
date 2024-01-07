package com.ademyschool.ademyschool.repository;

import com.ademyschool.ademyschool.model.Person;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person readByEmail(String email);
}
