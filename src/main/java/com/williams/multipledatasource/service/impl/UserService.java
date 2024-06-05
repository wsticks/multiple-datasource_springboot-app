package com.williams.multipledatasource.service.impl;

import com.williams.multipledatasource.model.request.UserRequest;
import com.williams.multipledatasource.model.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    }
