package com.example.ukrposhtatest.dto.request;

import com.example.ukrposhtatest.model.Project;
import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectRequestDto {
    @NotNull(message = "Name can't be null")
    private String name;
    private String description;
    @FutureOrPresent(message = "Deadline can't be in the past")
    private LocalDate deadline;
    private Project.Stage stage;
}
