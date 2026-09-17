package com.example.minilearn.service;

import com.example.minilearn.model.UserModel;
import com.example.minilearn.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo =userRepo;
    }

    public List<UserModel> getAllUsers(){
        return userRepo.findAll();
    }

    public UserModel getUserById(Long id){
//        return userRepo.findById(id);
        return userRepo.findById(id).orElse(null);
    }

    public UserModel createUser(UserModel user){
        return userRepo.save(user);
    }
}
