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

    /*
    //PUT
    public UserModel updateUser(Long id, UserModel updateUser){

        //        UserModel existingUser = userRepo.findById(id).orElse(null);

        UserModel existingUser = userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException(
                        "User not found with id: " + id
                )
        );
    }
    */



    /*


     //PUT
    public UserModel updateUser(Long id, UserModel updateUser){
//        UserModel existingUser = userRepo.findById(id).orElse(null);

        UserModel existingUser = userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException(
                        "User not found with id: " + id
                )
        );

//        if (existingUser == null){
//            return null;
//        }

        existingUser.setName(updateUser.getName());
        existingUser.setEmail(updateUser.getEmail());

        return userRepo.save(existingUser);
    }

     */


}
