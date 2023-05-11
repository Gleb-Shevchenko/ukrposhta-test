package com.example.ukrposhtatest.dto.response;

import com.example.ukrposhtatest.model.*;
import lombok.Data;

@Data
public class ProgrammerResponseDto {
    private Long id;
    private String name;
    private Programmer.Level level;
    private Programmer.Type type;
}
