package com.example.ukrposhtatest.dto.response;

import com.example.ukrposhtatest.model.Project;
import lombok.Data;

@Data
public class TeamResponseDto {
    private Long id;
    private String name;
    private Long projectId;
}
