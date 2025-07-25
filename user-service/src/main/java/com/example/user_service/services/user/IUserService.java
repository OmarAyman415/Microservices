package com.example.user_service.services.user;

import com.example.user_service.dtos.UserDto;
import com.example.user_service.models.User;
import com.example.user_service.requests.CreateUserRequest;
import com.example.user_service.requests.UpdateUserRequest;

public interface IUserService {
    User getUserById(Long userid);

    User createUser(CreateUserRequest request);

    User updateUser(UpdateUserRequest request, Long userId);

    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

}
