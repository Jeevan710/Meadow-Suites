package com.jeevan.Meadow.Suites.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jeevan.Meadow.Suites.DAO.RoleDAO;
import com.jeevan.Meadow.Suites.DAO.UserDAO;
import com.jeevan.Meadow.Suites.exception.UserAlreadyExistsException;
import com.jeevan.Meadow.Suites.model.Role;
import com.jeevan.Meadow.Suites.model.User;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDAO userDAO;
	@Autowired
    private RoleDAO roleDAO;
	
	private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        if (userDAO.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleDAO.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        return userDAO.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userDAO.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if (theUser != null){
        	userDAO.deleteByEmail(email);
        }
    }

    @Override
    public User getUser(String email) {
        return userDAO.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

