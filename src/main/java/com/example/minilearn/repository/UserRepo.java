package com.example.minilearn.repository;

import com.example.minilearn.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public interface UserRepo extends JpaRepository<UserModel, Long> {

}

/*
@Repository
public class UserRepo {

    private final List<UserModel> uesrObj = new ArrayList<>(
            List.of(
                    new UserModel(1L, "HR", "hr@yopmail.com"),
                    new UserModel(2L, "Rajan", "rajan@yopmail.com"),
                    new UserModel(3L,"Anil","anil@yopmail.com"),
                    new UserModel(4L, "Nitesh","nitesh@yopmail.co"),
                    new UserModel(5L,"Rohit", "rohit@maail.com")
            )
    );

    public List<UserModel> findAll(){
        return uesrObj;
    }

    public UserModel findById(Long id){
        return uesrObj.stream()
                .filter( userModel -> userModel.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public UserModel save(UserModel user){
        uesrObj.add(user);
        return user;
    }
}
*/
