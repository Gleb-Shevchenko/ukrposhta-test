package com.example.ukrposhtatest.service.impl;

import com.example.ukrposhtatest.model.Role;
import com.example.ukrposhtatest.repository.RoleRepository;
import com.example.ukrposhtatest.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName(Role.RoleName.valueOf(roleName));
    }
}
