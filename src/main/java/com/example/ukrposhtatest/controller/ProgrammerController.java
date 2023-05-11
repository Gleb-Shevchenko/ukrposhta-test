package com.example.ukrposhtatest.controller;

import com.example.ukrposhtatest.dto.mapper.RequestDtoMapper;
import com.example.ukrposhtatest.dto.mapper.ResponseDtoMapper;
import com.example.ukrposhtatest.dto.request.ProgrammerRequestDto;
import com.example.ukrposhtatest.dto.response.ProgrammerResponseDto;
import com.example.ukrposhtatest.model.Programmer;
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
    private final RequestDtoMapper<ProgrammerRequestDto, Programmer>
            programmerRequestDtoMapper;
    private final ResponseDtoMapper<ProgrammerResponseDto, Programmer>
            programmerResponseDtoMapper;

    @PostMapping
    public ProgrammerResponseDto save(@RequestBody ProgrammerRequestDto programmerRequestDto) {
        Programmer programmer = programmerService
                .save(programmerRequestDtoMapper.mapToModel(programmerRequestDto));
        return programmerResponseDtoMapper.mapToDto(programmer);
    }

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
        programmer.setTeams(programmerRequestDto.getTeams());
        programmer.setProjects(programmerRequestDto.getProjects());
        programmer.setLevel(programmerRequestDto.getLevel());
        programmer.setType(programmerRequestDto.getType());
        programmer.setSprints(programmerRequestDto.getSprints());
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
}
