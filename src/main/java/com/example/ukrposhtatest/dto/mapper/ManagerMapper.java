package com.example.ukrposhtatest.dto.mapper;

import com.example.ukrposhtatest.dto.request.ManagerRequestDto;
import com.example.ukrposhtatest.dto.response.ManagerResponseDto;
import com.example.ukrposhtatest.model.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper implements RequestDtoMapper<ManagerRequestDto, Manager>,
        ResponseDtoMapper<ManagerResponseDto, Manager> {
    @Override
    public Manager mapToModel(ManagerRequestDto dto) {
        Manager manager = new Manager();
        manager.setName(dto.getName());
        return manager;
    }

    @Override
    public ManagerResponseDto mapToDto(Manager manager) {
        ManagerResponseDto managerResponseDto = new ManagerResponseDto();
        managerResponseDto.setId(manager.getId());
        managerResponseDto.setName(manager.getName());
        return managerResponseDto;
    }
}
