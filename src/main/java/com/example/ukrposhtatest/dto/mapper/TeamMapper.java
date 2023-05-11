package com.example.ukrposhtatest.dto.mapper;

import com.example.ukrposhtatest.dto.request.TeamRequestDto;
import com.example.ukrposhtatest.dto.response.TeamResponseDto;
import com.example.ukrposhtatest.model.Team;
import com.example.ukrposhtatest.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TeamMapper implements RequestDtoMapper<TeamRequestDto, Team>,
        ResponseDtoMapper<TeamResponseDto, Team> {
    private final ProjectService projectService;

    @Override
    public Team mapToModel(TeamRequestDto dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setProject(projectService.findById(dto.getProjectId()));
        return team;
    }

    @Override
    public TeamResponseDto mapToDto(Team team) {
        TeamResponseDto teamResponseDto = new TeamResponseDto();
        teamResponseDto.setId(team.getId());
        teamResponseDto.setName(team.getName());
        teamResponseDto.setProjectId(team.getProject().getId());
        return teamResponseDto;
    }
}
