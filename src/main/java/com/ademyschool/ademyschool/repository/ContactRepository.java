package com.ademyschool.ademyschool.repository;

import com.ademyschool.ademyschool.model.Contact;
import com.ademyschool.ademyschool.rommappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    List<Contact> findByStatus(String status);
}
