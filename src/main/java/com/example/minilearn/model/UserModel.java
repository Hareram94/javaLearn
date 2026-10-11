package com.example.minilearn.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

    public UserModel(){

    }

    public UserModel(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public  String getEmail(){
        return email;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
