package com.example.user_service.services.user;

import com.example.user_service.dtos.UserDto;
import com.example.user_service.exceptions.AlreadyExistException;
import com.example.user_service.exceptions.ResourceNotFoundException;
import com.example.user_service.models.User;
import com.example.user_service.repositories.UserRepository;
import com.example.user_service.requests.CreateUserRequest;
import com.example.user_service.requests.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User createUser(CreateUserRequest request){
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(this::assignUserData)
                .orElseThrow(() -> new AlreadyExistException(request.getEmail() + " already exists "));
    }

    private User assignUserData(CreateUserRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userid) {
        return userRepository.findById(userid)
                .orElseThrow(()-> new ResourceNotFoundException(userid + " not found"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId)
                .map(existingUser -> updateUserData(existingUser,request))
                .orElseThrow(() -> new ResourceNotFoundException("User not Found"));
    }

    private User updateUserData(User user, UpdateUserRequest request) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete,()->{
                    throw new ResourceNotFoundException("User not Found");
                });
    }

    @Override
    public UserDto convertUserToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
