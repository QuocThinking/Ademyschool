package com.ademyschool.ademyschool.model;

import com.ademyschool.ademyschool.annotation.FieldsValueMatch;
import com.ademyschool.ademyschool.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import javax.management.relation.Role;

@Data
@Entity
@FieldsValueMatch.List(
        {
                @FieldsValueMatch(
                        field = "pwd",
                        fieldMatch = "confirmPwd",
                        message = "Passwords do not match"
                ),
                @FieldsValueMatch(
                        field = "email",
                        fieldMatch = "confirmEmail",
                        message = "Email address do not match!"
                )
        }
)

public class Person extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int personId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address")
    @Transient
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @PasswordValidator
    private String pwd;

    @Transient
    @NotBlank(message = "Confirm password must not be blank")
    @Size(min = 5, message = "Confirm password must be at least 5 characters long")
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;


}
