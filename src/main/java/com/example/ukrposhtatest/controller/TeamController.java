package com.example.ukrposhtatest.controller;

import com.example.ukrposhtatest.dto.mapper.RequestDtoMapper;
import com.example.ukrposhtatest.dto.mapper.ResponseDtoMapper;
import com.example.ukrposhtatest.dto.request.TeamRequestDto;
import com.example.ukrposhtatest.dto.response.ManagerResponseDto;
import com.example.ukrposhtatest.dto.response.ProgrammerResponseDto;
import com.example.ukrposhtatest.dto.response.TeamResponseDto;
import com.example.ukrposhtatest.model.Manager;
import com.example.ukrposhtatest.model.Programmer;
import com.example.ukrposhtatest.model.Project;
import com.example.ukrposhtatest.model.Team;
import com.example.ukrposhtatest.service.ManagerService;
import com.example.ukrposhtatest.service.ProgrammerService;
import com.example.ukrposhtatest.service.TeamService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final ProgrammerService programmerService;
    private final ManagerService managerService;
    private final RequestDtoMapper<TeamRequestDto, Team>
            teamRequestDtoMapper;
    private final ResponseDtoMapper<TeamResponseDto, Team>
            teamResponseDtoMapper;
    private final ResponseDtoMapper<ManagerResponseDto, Manager>
            managerResponseDtoMapper;
    private final ResponseDtoMapper<ProgrammerResponseDto, Programmer>
            programmerResponseDtoMapper;

    @PostMapping
    public TeamResponseDto save(@RequestBody TeamRequestDto teamRequestDto) {
        Team team = teamService.save(teamRequestDtoMapper.mapToModel(teamRequestDto));
        return teamResponseDtoMapper.mapToDto(team);
    }

    @GetMapping("/{id}")
    public TeamResponseDto findById(@PathVariable Long id) {
        return teamResponseDtoMapper.mapToDto(teamService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        Team team = teamService.findById(id);
        team.getProgrammers().stream().forEach(programmer -> {
            programmer.getTeams().remove(team);
            programmerService.save(programmer);
        });
        team.getManagers().stream().forEach(manager -> {
            manager.getTeams().remove(team);
            managerService.save(manager);
        });
        teamService.deleteById(id);
    }

    @PatchMapping("/{id}/name")
    public TeamResponseDto updateName(@PathVariable Long id, @RequestBody String name) {
        Team team = teamService.findById(id);
        team.setName(name);
        teamService.save(team);
        return teamResponseDtoMapper.mapToDto(team);
    }

    @PatchMapping("/{id}/project")
    public TeamResponseDto updateProject(@PathVariable Long id, @RequestBody Project project) {
        Team team = teamService.findById(id);
        team.setProject(project);
        teamService.save(team);
        return teamResponseDtoMapper.mapToDto(team);
    }

    @PatchMapping("/{id}/manager")
    public TeamResponseDto addManager(@PathVariable Long id, @RequestBody Long managerId) {
        Team team = teamService.findById(id);
        Manager manager = managerService.findById(managerId);
        manager.getTeams().add(team);
        manager.getProjects().add(team.getProject());
        managerService.save(manager);
        team.getManagers().add(manager);
        teamService.save(team);
        return teamResponseDtoMapper.mapToDto(team);
    }

    @PatchMapping("/{id}/programmer")
    public TeamResponseDto addProgrammer(@PathVariable Long id, @RequestBody Long programmerId) {
        Team team = teamService.findById(id);
        Programmer programmer = programmerService.findById(programmerId);
        programmer.getTeams().add(team);
        programmer.getProjects().add(team.getProject());
        programmerService.save(programmer);
        team.getProgrammers().add(programmer);
        teamService.save(team);
        return teamResponseDtoMapper.mapToDto(team);
    }

    @PatchMapping("/{id}/manager/delete")
    public TeamResponseDto deleteManager(@PathVariable Long id, @RequestBody Long managerId) {
        Team team = teamService.findById(id);
        Manager manager = managerService.findById(managerId);
        manager.getTeams().remove(team);
        manager.getProjects().remove(team.getProject());
        managerService.save(manager);
        team.getManagers().remove(manager);
        teamService.save(team);
        return teamResponseDtoMapper.mapToDto(team);
    }

    @PatchMapping("/{id}/programmer/delete")
    public TeamResponseDto deleteProgrammer(@PathVariable Long id, @RequestBody Long programmerId) {
        Team team = teamService.findById(id);
        Programmer programmer = programmerService.findById(programmerId);
        programmer.getTeams().remove(team);
        programmer.getProjects().remove(team.getProject());
        programmerService.save(programmer);
        team.getProgrammers().remove(programmer);
        teamService.save(team);
        return teamResponseDtoMapper.mapToDto(team);
    }

    @GetMapping("/all")
    public List<TeamResponseDto> findAll() {
        return teamService.findAll()
                .stream()
                .map(teamResponseDtoMapper::mapToDto)
                .toList();
    }

    @GetMapping("/all/managers")
    public List<ManagerResponseDto> findAllManagersById(@PathVariable Long id) {
        return teamService.findAllManagersById(id)
                .stream()
                .map(managerResponseDtoMapper::mapToDto)
                .toList();
    }

    @GetMapping("/all/programmers")
    public List<ProgrammerResponseDto> findAllProgrammersById(@PathVariable Long id) {
        return teamService.findAllProgrammersById(id)
                .stream()
                .map(programmerResponseDtoMapper::mapToDto)
                .toList();
    }
}
