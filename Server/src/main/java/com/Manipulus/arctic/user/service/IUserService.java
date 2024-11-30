package com.Manipulus.arctic.user.service;

import com.Manipulus.arctic.user.model.User;
import com.Manipulus.arctic.user.model.UserRequest;
import com.Manipulus.arctic.user.model.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    User loadUserByEmail(String email);
    User loadUserByUsername(String username);

    List<User> getUsers();

    User findUserById(Long id);

    UserResponse addUser(UserRequest user);

    void deleteUserById(int id);
}
