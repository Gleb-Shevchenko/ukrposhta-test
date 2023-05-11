package com.example.ukrposhtatest.controller;

import com.example.ukrposhtatest.dto.mapper.RequestDtoMapper;
import com.example.ukrposhtatest.dto.mapper.ResponseDtoMapper;
import com.example.ukrposhtatest.dto.request.ManagerRequestDto;
import com.example.ukrposhtatest.dto.response.ManagerResponseDto;
import com.example.ukrposhtatest.model.Manager;
import com.example.ukrposhtatest.service.ManagerService;
import com.example.ukrposhtatest.service.TeamService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerService managerService;
    private final TeamService teamService;
    private final RequestDtoMapper<ManagerRequestDto, Manager>
            managerRequestDtoMapper;
    private final ResponseDtoMapper<ManagerResponseDto, Manager>
            managerResponseDtoMapper;

    @PostMapping
    public ManagerResponseDto save(@RequestBody ManagerRequestDto managerRequestDto) {
        Manager manager = managerService.save(managerRequestDtoMapper.mapToModel(managerRequestDto));
        return managerResponseDtoMapper.mapToDto(manager);
    }

    @GetMapping("/{id}")
    public ManagerResponseDto findById(@PathVariable Long id) {
        return managerResponseDtoMapper.mapToDto(managerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        Manager manager = managerService.findById(id);
        manager.getTeams().stream()
                        .forEach(team -> {
                            team.getManagers().remove(manager);
                            teamService.save(team);
                        });
        managerService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ManagerResponseDto update(@PathVariable Long id,
                                     @RequestBody ManagerRequestDto managerRequestDto) {
        Manager manager = managerService.findById(id);
        manager.setName(managerRequestDto.getName());
        manager.setProjects(managerRequestDto.getProjects());
        manager.setTeams(managerRequestDto.getTeams());
        managerService.save(manager);
        return managerResponseDtoMapper.mapToDto(manager);
    }

    @GetMapping("/all")
    public List<ManagerResponseDto> findAll() {
        return managerService.findAll()
                .stream()
                .map(managerResponseDtoMapper::mapToDto)
                .toList();
    }
}
