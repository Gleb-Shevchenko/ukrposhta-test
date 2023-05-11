package com.example.ukrposhtatest.controller;

import com.example.ukrposhtatest.dto.mapper.RequestDtoMapper;
import com.example.ukrposhtatest.dto.mapper.ResponseDtoMapper;
import com.example.ukrposhtatest.dto.request.SprintRequestDto;
import com.example.ukrposhtatest.dto.response.SprintResponseDto;
import com.example.ukrposhtatest.model.Programmer;
import com.example.ukrposhtatest.model.Sprint;
import com.example.ukrposhtatest.service.ProgrammerService;
import com.example.ukrposhtatest.service.SprintService;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/sprints")
public class SprintController {
    private final SprintService sprintService;
    private final ProgrammerService programmerService;
    private final RequestDtoMapper<SprintRequestDto, Sprint>
            sprintRequestDtoMapper;
    private final ResponseDtoMapper<SprintResponseDto, Sprint>
            sprintResponseDtoMapper;

    @PostMapping
    public SprintResponseDto save(@RequestBody SprintRequestDto sprintRequestDto) {
        Sprint sprint = sprintService.save(sprintRequestDtoMapper.mapToModel(sprintRequestDto));
        Programmer programmer = sprint.getProgrammer();
        programmer.getSprints().add(sprint);
        programmerService.save(programmer);
        return sprintResponseDtoMapper.mapToDto(sprint);
    }

    @GetMapping("/{id}")
    public SprintResponseDto findById(@PathVariable Long id) {
        return sprintResponseDtoMapper.mapToDto(sprintService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        Sprint sprint = sprintService.findById(id);
        Programmer programmer = sprint.getProgrammer();
        programmer.getSprints().remove(sprint);
        programmerService.save(programmer);
        sprintService.deleteById(id);
    }

    @PatchMapping("/{id}/name")
    public SprintResponseDto updateName(@PathVariable Long id, @RequestBody String name) {
        Sprint sprint = sprintService.findById(id);
        sprint.setName(name);
        sprintService.save(sprint);
        return sprintResponseDtoMapper.mapToDto(sprint);
    }

    @PatchMapping("/{id}/description")
    public SprintResponseDto updateDescription(@PathVariable Long id, @RequestBody String description) {
        Sprint sprint = sprintService.findById(id);
        sprint.setDescription(description);
        sprintService.save(sprint);
        return sprintResponseDtoMapper.mapToDto(sprint);
    }

    @PatchMapping("/{id}/deadline")
    public SprintResponseDto updateDeadline(@PathVariable Long id, @RequestBody LocalDate deadline) {
        Sprint sprint = sprintService.findById(id);
        sprint.setDeadline(deadline);
        sprintService.save(sprint);
        return sprintResponseDtoMapper.mapToDto(sprint);
    }

    @PatchMapping("/{id}/programmer")
    public SprintResponseDto updateProgrammer(@PathVariable Long id, @RequestBody Programmer programmer) {
        Sprint sprint = sprintService.findById(id);
        Programmer currentProgrammer = sprint.getProgrammer();
        currentProgrammer.getSprints().remove(sprint);
        programmerService.save(currentProgrammer);
        sprint.setProgrammer(programmer);
        sprintService.save(sprint);
        programmer.getSprints().add(sprint);
        programmerService.save(programmer);
        return sprintResponseDtoMapper.mapToDto(sprint);
    }

    @PatchMapping("/{id}/status")
    public SprintResponseDto updateStatus(@PathVariable Long id, @RequestBody Sprint.Status status) {
        Sprint sprint = sprintService.findById(id);
        sprint.setStatus(status);
        sprintService.save(sprint);
        return sprintResponseDtoMapper.mapToDto(sprint);
    }
}
