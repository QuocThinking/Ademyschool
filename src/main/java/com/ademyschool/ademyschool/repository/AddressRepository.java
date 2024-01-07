package com.ademyschool.ademyschool.repository;

import com.ademyschool.ademyschool.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
