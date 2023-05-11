package com.example.ukrposhtatest.dto.response;

import com.example.ukrposhtatest.model.Sprint;
import java.time.LocalDate;
import lombok.Data;

@Data
public class SprintResponseDto {
    private Long id;
    private String name;
    private String description;
    private Long projectId;
    private LocalDate deadline;
    private Long programmerId;
    private Sprint.Status status;
}
