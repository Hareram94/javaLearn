package com.example.minilearn.Controller;

import com.example.minilearn.model.UserModel;
import com.example.minilearn.response.ApiResponse;
import com.example.minilearn.service.Notifications;
import com.example.minilearn.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<UserModel> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getUser(@PathVariable Long id){

        return userService.getUserById(id);
    }

    // JSON
    @PostMapping  //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) also create like
    public ApiResponse<UserModel> createUser(@RequestBody UserModel user){
//        Notifications createTeacher = new TeacherNotification();
//        System.out.println(createTeacher.sentNotification("Teacher created successfull!"));
//        System.out.println(notifications.sentNotification("Teacher created successfull!"));
        notifications.sentNotification("Teacher created successfull!");
//        return userService.createUser(user);

        UserModel getUser = userService.createUser(user);

        return new ApiResponse<>(
                true,
                "User created successfull!",
                getUser
        );
    }

    // FORM-DATA
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<UserModel> createUser(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String email
    ){
//        UserModel userObj = new UserModel(id,name,email);
//        System.out.println(new TeacherNotification().sentNotification("User created successfull!"));
//        System.out.println(notifications.sentNotification("User created successfull!"));
        notifications.sentNotification("User created successfull!");

        UserModel userObj = new UserModel(name, email);
//        return userService.createUser(userObj);

        return new ApiResponse<>(
                true,
                "User created successfull!",
                userObj
        );
    }

    @PostMapping("/student")
    public UserModel createStudent(@RequestBody UserModel userStd){
//        System.out.println(new TeacherNotification().sentNotification("Student created successfull!"));
//        System.out.println(notifications.sentNotification("Student created successfull!"));
        notifications.sentNotification("Student created successfull!");
        return userService.createUser(userStd);
    }

    @PostMapping("teacher")
    public UserModel createTeacher(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam(required = false) String email
    ){
//        UserModel userTeacher = new UserModel(id, name, email);
        UserModel userTeacher = new UserModel(name, email);
//        System.out.println(new TeacherNotification().sentNotification("Teacher created successfull!"));
//        System.out.println(notifications.sentNotification("Teacher created successfull!"));
        notifications.sentNotification("Teacher created successfull!");

        return userService.createUser(userTeacher);
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
