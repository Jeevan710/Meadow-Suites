package com.jeevan.Meadow.Suites.service;

import java.util.List;
import com.jeevan.Meadow.Suites.model.User;

public interface UserService {
    User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);
}
