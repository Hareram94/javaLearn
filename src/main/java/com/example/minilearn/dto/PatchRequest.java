package com.example.minilearn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/*
 {
 "name": "xyz",
 "email": "xyz@gmail.com"
 */

public class PatchRequest {

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Email(message = "Email must be valid")
    private String email;

    public  PatchRequest(){

    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

}
