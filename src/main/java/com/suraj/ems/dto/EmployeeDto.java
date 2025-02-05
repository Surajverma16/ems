package com.suraj.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // lombok getter setter auto
@NoArgsConstructor
@AllArgsConstructor

// DTO - Data transfer object (for transfering data from one part to another or for apis) like models in android
public class EmployeeDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;

}
