package com.example.ukrposhtatest.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class TeamRequestDto {
    @NotNull(message = "Name can't be null")
    private String name;
    @Positive
    private Long projectId;
}
