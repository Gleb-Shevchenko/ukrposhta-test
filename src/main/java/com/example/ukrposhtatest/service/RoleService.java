package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Role;

public interface RoleService {
    Role save(Role role);

    Role findByName(String roleName);
}
