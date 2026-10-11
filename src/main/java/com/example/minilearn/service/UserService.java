package com.example.minilearn.service;

import com.example.minilearn.exception.UserNotFoundException;
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
//        return userRepo.findById(id).orElse(null);

        return userRepo
                .findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "User not found with id: " + id
                        )
                );
    }

    public UserModel createUser(UserModel user){
        return userRepo.save(user);
    }

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

    //PATCH
    public UserModel patchUser(Long id, UserModel userData){

        /*
        UserModel existingUser = userRepo.findById(id).orElse(null);

        if (existingUser == null){
            return null;
        }
        */

//        if (userData.getName() != null || !userData.getName().isBlank()){
//           existingUser.setName(userData.getName());
//        }
//
//        if (userData.getEmail() != null || !userData.getEmail().isBlank()){
//            existingUser.setEmail(userData.getEmail());
//        }

        UserModel existingUser = userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException(
                        "User not found with id: " + id
                )
        );

        if (userData.getName() != null){
            existingUser.setName(userData.getName());
        }

        if (userData.getEmail() != null){
            existingUser.setEmail(userData.getEmail());
        }

        return userRepo.save(existingUser);
    }

    //MAKE IT FOR CUSTOM MESSAGE
    public UserModel getUserById(Long id, String message){
        return userRepo
                .findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                message
                        )
                );
    }

    public UserModel removeUser(Long id, String message){
        /*
        return userRepo.findById(id)
                .map( userModel -> {
                    userRepo.delete(userModel);
                    return userModel;
                })
                .orElse(null);
        */

        return userRepo.findById(id)
                .map( userModel -> {
                    userRepo.delete(userModel);
                    return userModel;
                })
                .orElseThrow(
                        () -> new UserNotFoundException(
                                message
//                                "User not found with id: " + id
                        )
                );
    }

    //DELETE
    public boolean deleteUser(Long id){
        if (!userRepo.existsById(id)){
            return false;
        }

        userRepo.deleteById(id);
        return true;
    }


    public void deleteUserEXp(Long id, String messge){

        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException(messge);
        }

        userRepo.deleteById(id);
    }


}
