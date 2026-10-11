package com.example.minilearn.Controller;

import com.example.minilearn.model.UserModel;
import com.example.minilearn.response.ApiResponse;
import com.example.minilearn.service.Notifications;
import com.example.minilearn.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class LoginController {

    private final UserService userService;
    private final Notifications notifications;

    public LoginController(UserService userService, Notifications notifications){
        this.userService = userService;
        this.notifications = notifications;
    }

    @GetMapping
    public ApiResponse<List<UserModel>> getUsers(){
        List<UserModel> users = userService.getAllUsers();
        boolean status = !users.isEmpty();
        String msg = status ? "User get successfully!" : "No user found!";

        return new ApiResponse<>(
                status,
                msg,
                users
        );
    }

    /*
    @GetMapping
    public List<UserModel> getUsers(){
        return userService.getAllUsers();
    }
    */

    /*
    @GetMapping("/{id}")
    public UserModel getUser(@PathVariable Long id){

        return userService.getUserById(id);
    }
    */

    @GetMapping("/{id}")
    public ApiResponse<UserModel> getUser(@PathVariable Long id){

//        UserModel getUser = userService.getUserById(id);
        UserModel getUser = userService.getUserById(id);

        boolean status = (getUser != null ? true : false);
        String msg = (status ? "get user successfully!" : "Not user is Exits!!");

        return new ApiResponse<>(
                status,
                msg,
                getUser);
    }

    // JSON
    @PostMapping  //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) also create like
    public ApiResponse<UserModel> createUser(@RequestBody UserModel user){;
        notifications.sentNotification("User created successfull!");

        UserModel getUser =  userService.createUser(user);

        return new ApiResponse<UserModel>(true, "User created successfull!", getUser);
    }

    // FORM-DATA
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<UserModel> createUser(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String email
    ){
        UserModel userObj = new UserModel(id,name,email);
        notifications.sentNotification("User created successfull!");
        UserModel getUser = userService.createUser(userObj);
        return new ApiResponse<UserModel>(true, "User created successfull!", getUser);
    }


    @PostMapping("/student")
    public UserModel createStudent(@RequestBody UserModel userStd){
        notifications.sentNotification("Student created successfull!");
        return userService.createUser(userStd);
    }

    @PostMapping("teacher")
    public UserModel createTeacher(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam(required = false) String email
    ){
        UserModel userTeacher = new UserModel(id, name, email);
        notifications.sentNotification("Teacher created successfull!");
        return userService.createUser(userTeacher);
    }



    @PostMapping("delete")
    public ApiResponse<UserModel> removeUser(@RequestParam Long user_Id){

        UserModel removeData = userService.removeUser(user_Id, "User not found with id: " + user_Id);
        boolean status = removeData != null;
        String msg = status ? "Delete successfully!" : "User id does not exist";

        return new ApiResponse<>(
                status,
                msg,
                removeData
        );
    }

    @PutMapping("/update/{user_id}")
    public ApiResponse<UserModel> updateUser(
            @PathVariable Long user_id,
            @RequestBody UserModel userData
    ){
        UserModel updateUser = userService.updateUser(user_id, userData);
        boolean getStatus = updateUser != null;
        String msg = getStatus ? "Update successfully!" : "User not exist!";

        return new ApiResponse<>(
                getStatus,
                msg,
                updateUser
        );
    }

    @PatchMapping("change/{user_id}")
    public ApiResponse<UserModel> patchUser(
            @PathVariable Long user_id,
            @RequestBody UserModel userData
    ){
        UserModel updateUserData = userService.patchUser(user_id, userData);

        boolean getStatus = updateUserData != null;

        if (updateUserData == null) {
            return new ApiResponse<>(
                    false,
                    "User not found!",
                    null
            );
        }

        boolean hasName = userData.getName() != null && !userData.getName().isEmpty();
        boolean hasEmail = userData.getEmail() != null && !userData.getEmail().isEmpty();

        String msg;

        if (hasName && hasEmail) {
            msg = "Name and email updated successfully!";
        } else if (hasName) {
            msg = "Name updated successfully!";
        } else if (hasEmail) {
            msg = "Email updated successfully!";
        } else {
            msg = "No fields provided for update!";
        }

        return new ApiResponse<>(
                getStatus,
                msg,
                updateUserData
        );
    }

    @DeleteMapping("delete/{user_id}")
    public ApiResponse<Object> deleteUser(
            @PathVariable Long user_id
    ){
        boolean isDelete = userService.deleteUser(user_id);
        String msg = isDelete ? "Delete successfully!" : "User id does not exist";

        return new ApiResponse<>(
                isDelete,
                msg,
                Map.of()
        );

    }



    /*
    @PutMapping("teacher/update")
    public UserModel updateTeacher(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String email
    ){

        UserModel user = new UserModel(name, email);

        return userService.createUser(user);
    }
    */
}
