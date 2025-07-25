package com.example.user_service.controllers;

import com.example.user_service.dtos.UserDto;
import com.example.user_service.exceptions.ResourceNotFoundException;
import com.example.user_service.models.User;
import com.example.user_service.requests.CreateUserRequest;
import com.example.user_service.requests.UpdateUserRequest;
import com.example.user_service.respones.ApiResponse;
import com.example.user_service.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final IUserService userService;

    @RequestMapping("/{userId}/user")
    public ResponseEntity<ApiResponse> getUser(@PathVariable long userId) {
        try{
            User user = userService.getUserById(userId);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("User:("+ user.getFirstName() + ") retrieved Successfully",userDto));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request) {
        try {
            User user = userService.createUser(request);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("User:("+ user.getFirstName() + ") created Successfully",userDto));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable long userId, @RequestBody UpdateUserRequest request) {
        try{
            User user = userService.updateUser(request,userId);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("User:("+ user.getFirstName() + ") updated Successfully",userDto));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable long userId) {
        try{
            User user = userService.getUserById(userId);
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("User:("+ user.getFirstName()+") deleted successfully",null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

}
