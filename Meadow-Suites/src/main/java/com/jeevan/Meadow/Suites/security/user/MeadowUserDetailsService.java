package com.jeevan.Meadow.Suites.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jeevan.Meadow.Suites.DAO.UserDAO;
import com.jeevan.Meadow.Suites.model.User;

@Service
public class MeadowUserDetailsService implements UserDetailsService {
	@Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return MeadowUserDetails.buildUserDetails(user);
    }
}
