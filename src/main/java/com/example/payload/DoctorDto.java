package com.example.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDto {
    private Long id;
    @Size(min=3,max = 100, message = "Please enter name above than 2 characters")
    private String name;
    private String department;
    @Email(message = "Please enter valid Email address")
    private String email;
    private String phone;
    private String message;

}