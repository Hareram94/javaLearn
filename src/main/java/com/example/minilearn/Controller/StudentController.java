package com.example.minilearn.Controller;

import com.example.minilearn.dto.CreateUserRequest;
import com.example.minilearn.dto.PatchRequest;
import com.example.minilearn.exception.UserNotFoundException;
import com.example.minilearn.model.UserModel;
import com.example.minilearn.response.ApiResponse;
import com.example.minilearn.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/student")
public class StudentController {

    final private UserService userService;

    public StudentController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity<UserModel> createStudent(
            @Valid @RequestBody CreateUserRequest request
    ){
        UserModel stdObj = new UserModel();

        stdObj.setName(request.getName());
        stdObj.setEmail(request.getEmail());

        UserModel saveStudent = userService.createUser(stdObj);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saveStudent);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserModel>>> getAllStudent(){

        List<UserModel> students = userService.getAllUsers();

        ApiResponse<List<UserModel>> response = new ApiResponse<>(
                true,
                "Students get successfully!",
                students
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{student_id}")
    public ResponseEntity<ApiResponse<UserModel>> getStudent(
            @PathVariable Long student_id
    ){

        UserModel getStudent = userService.getUserById(student_id, "Student with id " + student_id + " is not found");

        ApiResponse<UserModel> resp = new ApiResponse<>(
                true,
                "Get student successfully!",
                getStudent
        );

        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{student_id}")
    public ResponseEntity<UserModel> updateStudent(
            @PathVariable Long student_id,
            @Valid @RequestBody CreateUserRequest request
    ){
        UserModel stdObj = new UserModel();

        stdObj.setName(request.getName());
        stdObj.setEmail(request.getEmail());

        UserModel updateStd = userService.updateUser(student_id, stdObj);

        if (updateStd == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updateStd);
    }

    @PatchMapping("/{student_id}")
    public ResponseEntity<ApiResponse<UserModel>> patchStudent(
            @PathVariable Long student_id,
            @Valid @RequestBody PatchRequest patchRequest
    ){
        UserModel obj = new UserModel();

        obj.setName(patchRequest.getName());
        obj.setEmail(patchRequest.getEmail());

        boolean hasName = patchRequest.getName() != null && !patchRequest.getName().isEmpty();
        boolean hasEmail = patchRequest.getEmail() != null && !patchRequest.getEmail().isEmpty();

        if (!hasName && !hasEmail) {
            //InvalidPatchException
            throw new UserNotFoundException(
                    "No fields provided for update!"
            );
        }

        UserModel updateStd = userService.patchUser(
                student_id,
                obj
        );

        if (updateStd == null) {
            throw new UserNotFoundException(
                    "User with id " + student_id + " not found"
            );
        }

        String message;

        if (hasName && hasEmail) {
            message = "Name and email updated successfully!";
        } else if (hasName) {
            message = "Name updated successfully!";
        } else {
            message = "Email updated successfully!";
        }

        ApiResponse<UserModel> response = new ApiResponse<>(
                true,
                message,
                updateStd
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{student_id}")
    public ResponseEntity<UserModel> deteleStudent(
            @PathVariable Long student_id
    ){

        userService.deleteUserEXp(student_id, "Student with id " + student_id + " is not found");

        return ResponseEntity.noContent().build();
    }

}
