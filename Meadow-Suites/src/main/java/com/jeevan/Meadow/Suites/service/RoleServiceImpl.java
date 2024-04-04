package com.jeevan.Meadow.Suites.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.jeevan.Meadow.Suites.DAO.RoleDAO;
import com.jeevan.Meadow.Suites.DAO.UserDAO;
import com.jeevan.Meadow.Suites.exception.RoleAlreadyExistException;
import com.jeevan.Meadow.Suites.exception.UserAlreadyExistsException;
import com.jeevan.Meadow.Suites.model.Role;
import com.jeevan.Meadow.Suites.model.User;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
    private RoleDAO roleDAO;
	@Autowired
    private UserDAO userDAO;

    @Override
    public List<Role> getRoles() {
        return roleDAO.findAll();
    }

    @Override
    public Role createRole(Role theRole) {
        String roleName = "ROLE_"+theRole.getName().toUpperCase();
        Role role = new Role(roleName);
        if (roleDAO.existsByName(roleName)){
            throw new RoleAlreadyExistException(theRole.getName()+" role already exists");
        }
        return roleDAO.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removeAllUsersFromRole(roleId);
        roleDAO.deleteById(roleId);
    }

    @Override
    public Role findByName(String name) {
        return roleDAO.findByName(name).get();
    }

    @Override
    public User removeUserFromRole(Long userId, Long roleId) {
        Optional<User> user = userDAO.findById(userId);
        Optional<Role>  role = roleDAO.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())){
            role.get().removeUserFromRole(user.get());
            roleDAO.save(role.get());
            return user.get();
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        Optional<User> user = userDAO.findById(userId);
        Optional<Role>  role = roleDAO.findById(roleId);
        if (user.isPresent() && user.get().getRoles().contains(role.get())){
            throw new UserAlreadyExistsException(
                    user.get().getFirstName()+ " is already assigned to the" + role.get().getName()+ " role");
        }
        if (role.isPresent()){
            role.get().assignRoleToUser(user.get());
            roleDAO.save(role.get());
        }
        return user.get();
    }

    @Override
    public Role removeAllUsersFromRole(Long roleId) {
        Optional<Role> role = roleDAO.findById(roleId);
        role.ifPresent(Role::removeAllUsersFromRole);
        return roleDAO.save(role.get());
    }
}
