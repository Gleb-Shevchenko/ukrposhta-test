package com.example.ukrposhtatest.dto.mapper;

import com.example.ukrposhtatest.dto.request.ProgrammerRequestDto;
import com.example.ukrposhtatest.dto.response.ProgrammerResponseDto;
import com.example.ukrposhtatest.model.Programmer;
import org.springframework.stereotype.Component;

@Component
public class ProgrammerMapper implements RequestDtoMapper<ProgrammerRequestDto, Programmer>,
        ResponseDtoMapper<ProgrammerResponseDto, Programmer> {
    @Override
    public Programmer mapToModel(ProgrammerRequestDto dto) {
        Programmer programmer = new Programmer();
        programmer.setName(dto.getName());
        programmer.setLevel(dto.getLevel());
        programmer.setType(dto.getType());
        return programmer;
    }

    @Override
    public ProgrammerResponseDto mapToDto(Programmer programmer) {
        ProgrammerResponseDto programmerResponseDto = new ProgrammerResponseDto();
        programmerResponseDto.setId(programmer.getId());
        programmerResponseDto.setName(programmer.getName());
        programmerResponseDto.setLevel(programmer.getLevel());
        programmerResponseDto.setType(programmer.getType());
        return programmerResponseDto;
    }
}
