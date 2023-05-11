package com.example.ukrposhtatest.dto.response;

import com.example.ukrposhtatest.model.Project;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private Project.Stage stage;
}
