package com.example.ukrposhtatest.controller;

import com.example.ukrposhtatest.dto.mapper.RequestDtoMapper;
import com.example.ukrposhtatest.dto.mapper.ResponseDtoMapper;
import com.example.ukrposhtatest.dto.request.ProjectRequestDto;
import com.example.ukrposhtatest.dto.response.ProjectResponseDto;
import com.example.ukrposhtatest.model.Project;
import com.example.ukrposhtatest.service.ProjectService;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final RequestDtoMapper<ProjectRequestDto, Project>
            projectRequestDtoMapper;
    private final ResponseDtoMapper<ProjectResponseDto, Project>
            projectResponseDtoMapper;

    @PostMapping
    public ProjectResponseDto save(@RequestBody ProjectRequestDto projectRequestDto) {
        Project project = projectService.save(projectRequestDtoMapper.mapToModel(projectRequestDto));
        return projectResponseDtoMapper.mapToDto(project);
    }

    @GetMapping("/{id}")
    public ProjectResponseDto findById(@PathVariable Long id) {
        return projectResponseDtoMapper.mapToDto(projectService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        projectService.deleteById(id);
    }

    @GetMapping("/all")
    public List<ProjectResponseDto> findAll(@RequestBody boolean isDeleted) {
        return projectService.findAll(isDeleted)
                .stream()
                .map(projectResponseDtoMapper::mapToDto)
                .toList();
    }

    @PatchMapping("/{id}/name")
    public ProjectResponseDto updateName(@PathVariable Long id, @RequestBody String name) {
        Project project = projectService.findById(id);
        project.setName(name);
        projectService.save(project);
        return projectResponseDtoMapper.mapToDto(project);
    }

    @PatchMapping("/{id}/description")
    public ProjectResponseDto updateDescription(@PathVariable Long id, @RequestBody String description) {
        Project project = projectService.findById(id);
        project.setDescription(description);
        projectService.save(project);
        return projectResponseDtoMapper.mapToDto(project);
    }

    @PatchMapping("/{id}/deadline")
    public ProjectResponseDto updateDeadline(@PathVariable Long id, @RequestBody LocalDate deadline) {
        Project project = projectService.findById(id);
        project.setDeadline(deadline);
        projectService.save(project);
        return projectResponseDtoMapper.mapToDto(project);
    }

    @PatchMapping("/{id}/stage")
    public ProjectResponseDto updateStage(@PathVariable Long id, @RequestBody String stage) {
        Project project = projectService.findById(id);
        project.setStage(Project.Stage.valueOf(stage.toUpperCase()));
        projectService.save(project);
        return projectResponseDtoMapper.mapToDto(project);
    }
}
