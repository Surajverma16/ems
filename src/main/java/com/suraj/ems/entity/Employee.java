package com.suraj.ems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter  // Getter and setter are auto in lombok dependency
@Setter
@NoArgsConstructor  // constructor with no parameter 
@AllArgsConstructor  // constructor with parmeter

@Entity
public class Employee {

    @Id  // To generate unique identification to the user
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto Genrate the identity
    private long id;

    @Column(name = "First Name") // by default the colum is the variable name to change the name the column keyword is used
    private String firstName;

    @Column(name = "Last Name")
    private String lastName;

    @Column(name = "Email", nullable = false, unique = true)  // User should add email and email should not be repeated
    private String email;

}
