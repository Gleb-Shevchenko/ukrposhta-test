package com.example.ukrposhtatest.dto.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
