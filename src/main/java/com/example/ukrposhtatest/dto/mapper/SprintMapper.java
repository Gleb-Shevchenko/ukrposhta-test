package com.example.ukrposhtatest.dto.mapper;

import com.example.ukrposhtatest.dto.request.SprintRequestDto;
import com.example.ukrposhtatest.dto.response.SprintResponseDto;
import com.example.ukrposhtatest.model.Sprint;
import com.example.ukrposhtatest.service.ProgrammerService;
import com.example.ukrposhtatest.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SprintMapper implements RequestDtoMapper<SprintRequestDto, Sprint>,
        ResponseDtoMapper<SprintResponseDto, Sprint> {
    private final ProjectService projectService;
    private final ProgrammerService programmerService;

    @Override
    public Sprint mapToModel(SprintRequestDto dto) {
        Sprint sprint = new Sprint();
        sprint.setName(dto.getName());
        sprint.setDescription(dto.getDescription());
        sprint.setProject(projectService.findById(dto.getProjectId()));
        sprint.setProgrammer(programmerService.findById(dto.getProgrammerId()));
        sprint.setDeadline(dto.getDeadline());
        sprint.setStatus(dto.getStatus());
        return sprint;
    }

    @Override
    public SprintResponseDto mapToDto(Sprint sprint) {
        SprintResponseDto sprintResponseDto = new SprintResponseDto();
        sprintResponseDto.setId(sprint.getId());
        sprintResponseDto.setName(sprint.getName());
        sprintResponseDto.setDescription(sprint.getDescription());
        sprintResponseDto.setProjectId(sprint.getProject().getId());
        sprintResponseDto.setProgrammerId(sprint.getProgrammer().getId());
        sprintResponseDto.setDeadline(sprint.getDeadline());
        sprintResponseDto.setStatus(sprint.getStatus());
        return sprintResponseDto;
    }
}
