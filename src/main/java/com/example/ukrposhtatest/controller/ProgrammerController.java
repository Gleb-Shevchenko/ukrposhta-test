package com.example.ukrposhtatest.controller;

import com.example.ukrposhtatest.dto.mapper.ResponseDtoMapper;
import com.example.ukrposhtatest.dto.request.ProgrammerRequestDto;
import com.example.ukrposhtatest.dto.response.ProgrammerResponseDto;
import com.example.ukrposhtatest.dto.response.ProjectResponseDto;
import com.example.ukrposhtatest.dto.response.SprintResponseDto;
import com.example.ukrposhtatest.dto.response.TeamResponseDto;
import com.example.ukrposhtatest.model.Programmer;
import com.example.ukrposhtatest.model.Project;
import com.example.ukrposhtatest.model.Sprint;
import com.example.ukrposhtatest.model.Team;
import com.example.ukrposhtatest.service.ProgrammerService;
import com.example.ukrposhtatest.service.SprintService;
import com.example.ukrposhtatest.service.TeamService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/programmers")
public class ProgrammerController {
    private final ProgrammerService programmerService;
    private final SprintService sprintService;
    private final TeamService teamService;
    private final ResponseDtoMapper<ProgrammerResponseDto, Programmer>
            programmerResponseDtoMapper;
    private final ResponseDtoMapper<ProjectResponseDto, Project>
            projectResponseDtoMapper;
    private final ResponseDtoMapper<TeamResponseDto, Team>
            teamResponseDtoMapper;
    private final ResponseDtoMapper<SprintResponseDto, Sprint>
            sprintResponseDtoMapper;

    @GetMapping("/{id}")
    public ProgrammerResponseDto findById(@PathVariable Long id) {
        return programmerResponseDtoMapper.mapToDto(programmerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        Programmer programmer = programmerService.findById(id);
        programmer.getTeams().forEach(team -> {
            team.getProgrammers().remove(programmer);
            teamService.save(team);
        });
        programmer.getSprints().forEach(sprint -> {
            sprint.setProgrammer(null);
            sprintService.save(sprint);
        });
        programmerService.deleteById(id);
    }
    
    @PutMapping("/id")
    public ProgrammerResponseDto update(@PathVariable Long id,
                                        @RequestBody ProgrammerRequestDto programmerRequestDto) {
        Programmer programmer = programmerService.findById(id);
        programmer.setName(programmerRequestDto.getName());
        programmer.setLevel(programmerRequestDto.getLevel());
        programmer.setType(programmerRequestDto.getType());
        programmerService.save(programmer);
        return programmerResponseDtoMapper.mapToDto(programmer);
    }

    @GetMapping("/all")
    public List<ProgrammerResponseDto> findAll() {
        return programmerService.findAll()
                .stream()
                .map(programmerResponseDtoMapper::mapToDto)
                .toList();
    }

    @GetMapping("/all/projects")
    public List<ProjectResponseDto> findAllProjectsById(@PathVariable Long id) {
        return programmerService.findAllProjectsById(id)
                .stream()
                .map(projectResponseDtoMapper::mapToDto)
                .toList();
    }

    @GetMapping("/all/teams")
    public List<TeamResponseDto> findAllTeamsById(@PathVariable Long id) {
        return programmerService.findAllTeamsById(id)
                .stream()
                .map(teamResponseDtoMapper::mapToDto)
                .toList();
    }
    
    @GetMapping("/all/sprints")
    public List<SprintResponseDto> findAllSprintsById(@PathVariable Long id) {
        return programmerService.findAllSprintsById(id)
                .stream()
                .map(sprintResponseDtoMapper::mapToDto)
                .toList();
    }
}
