package com.example.ukrposhtatest.dto.request;

import com.example.ukrposhtatest.model.Sprint;
import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class SprintRequestDto {
    @NotNull(message = "Name can't be null")
    private String name;
    private String description;
    @Positive
    private Long projectId;
    @FutureOrPresent
    private LocalDate deadline;
    @Positive
    private Long programmerId;
    private Sprint.Status status;
}
