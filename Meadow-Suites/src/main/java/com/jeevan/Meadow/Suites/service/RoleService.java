package com.jeevan.Meadow.Suites.service;

import java.util.List;

import com.jeevan.Meadow.Suites.model.Role;
import com.jeevan.Meadow.Suites.model.User;


public interface RoleService {
	List<Role> getRoles();
	Role createRole(Role theRole);
    void deleteRole(Long id);
    Role findByName(String name);
    User removeUserFromRole(Long userId, Long roleId);
    User assignRoleToUser(Long userId, Long roleId);
    Role removeAllUsersFromRole(Long roleId);
}
