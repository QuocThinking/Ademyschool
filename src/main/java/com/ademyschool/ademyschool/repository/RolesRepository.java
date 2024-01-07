package com.ademyschool.ademyschool.repository;

import com.ademyschool.ademyschool.model.Address;
import com.ademyschool.ademyschool.model.Roles;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Roles getByRoleName(String name);
}
