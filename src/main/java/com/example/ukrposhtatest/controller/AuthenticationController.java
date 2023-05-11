package com.example.ukrposhtatest.controller;

import com.example.ukrposhtatest.dto.mapper.RequestDtoMapper;
import com.example.ukrposhtatest.dto.request.ManagerRequestDto;
import com.example.ukrposhtatest.dto.request.ProgrammerRequestDto;
import com.example.ukrposhtatest.dto.request.UserRequestDto;
import com.example.ukrposhtatest.exception.RegistrationException;
import com.example.ukrposhtatest.model.Manager;
import com.example.ukrposhtatest.model.Programmer;
import com.example.ukrposhtatest.model.Role;
import com.example.ukrposhtatest.service.ManagerService;
import com.example.ukrposhtatest.service.ProgrammerService;
import com.example.ukrposhtatest.service.RoleService;
import java.util.Set;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthenticationController {
    private final ManagerService managerService;
    private final ProgrammerService programmerService;
    private final RoleService roleService;
    private final RequestDtoMapper<ManagerRequestDto, Manager>
            managerRequestDtoMapper;
    private final RequestDtoMapper<ProgrammerRequestDto, Programmer>
            programmerRequestDtoMapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequestDto userRequestDto) {
        String roleName = userRequestDto.getRoleName();
        if (roleName.equals(Role.RoleName.ADMIN.toString().toLowerCase())) {
            throw new RegistrationException("Can't register user with role admin");
        } else if (roleName.equals(Role.RoleName.MANAGER.toString().toLowerCase())) {
            ManagerRequestDto managerRequestDto = (ManagerRequestDto) userRequestDto;
            Manager manager = managerRequestDtoMapper.mapToModel(managerRequestDto);
            manager.setRoles(Set.of(roleService.findByName(roleName)));
            managerService.save(manager);
        } else if (roleName.equals(Role.RoleName.PROGRAMMER.toString().toLowerCase())) {
            ProgrammerRequestDto programmerRequestDto = (ProgrammerRequestDto) userRequestDto;
            Programmer programmer = programmerRequestDtoMapper.mapToModel(programmerRequestDto);
            programmer.setRoles(Set.of(roleService.findByName(roleName)));
            programmerService.save(programmer);
        } else {
            return ResponseEntity.badRequest().body("Invalid role name");
        }
        return ResponseEntity.ok("User registered successfully");
    }
}
