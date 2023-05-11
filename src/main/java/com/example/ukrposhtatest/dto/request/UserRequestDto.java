package com.example.ukrposhtatest.dto.request;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UserRequestDto {
    @Pattern(regexp = "admin|manager|user", message = "Wrong role name")
    private String roleName;
}
