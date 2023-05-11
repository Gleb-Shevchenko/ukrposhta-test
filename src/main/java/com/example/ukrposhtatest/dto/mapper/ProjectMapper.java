package com.example.ukrposhtatest.dto.mapper;

import com.example.ukrposhtatest.dto.request.ProjectRequestDto;
import com.example.ukrposhtatest.dto.response.ProjectResponseDto;
import com.example.ukrposhtatest.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements RequestDtoMapper<ProjectRequestDto, Project>,
        ResponseDtoMapper<ProjectResponseDto, Project> {
    @Override
    public Project mapToModel(ProjectRequestDto dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setDeadline(dto.getDeadline());
        project.setStage(dto.getStage());
        return project;
    }

    @Override
    public ProjectResponseDto mapToDto(Project project) {
        ProjectResponseDto projectResponseDto = new ProjectResponseDto();
        projectResponseDto.setId(project.getId());
        projectResponseDto.setName(project.getName());
        projectResponseDto.setDescription(projectResponseDto.getDescription());
        projectResponseDto.setDeadline(project.getDeadline());
        projectResponseDto.setStage(project.getStage());
        return projectResponseDto;
    }
}
