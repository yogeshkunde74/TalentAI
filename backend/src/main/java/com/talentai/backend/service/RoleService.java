package com.talentai.backend.service;

import java.util.List;
import java.util.Optional;

import com.talentai.backend.entity.Role;

public interface RoleService {

    Role saveRole(Role role);

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Long id);

    Optional<Role> getRoleByName(String roleName);

    void deleteRole(Long id);

}
