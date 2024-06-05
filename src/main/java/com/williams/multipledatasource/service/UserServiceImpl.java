package com.williams.multipledatasource.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williams.multipledatasource.exception.RecordExistException;
import com.williams.multipledatasource.model.entity.User;
import com.williams.multipledatasource.model.request.UserRequest;
import com.williams.multipledatasource.model.response.UserResponse;
import com.williams.multipledatasource.repository.UserRepository;
import com.williams.multipledatasource.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest){
        new User();
        User user;
        user = userRepository.findUserByUsername(userRequest.username());
        if(user != null){
         throw new RecordExistException("Username " + userRequest.username() + " already exists");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        User mappedUser = objectMapper.convertValue(userRequest, User.class);
        User savedUser =userRepository.save(mappedUser);
        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getPassword(),
                savedUser.getRole()
        );
    }
}
