package com.example.ukrposhtatest.dto.request;

import com.example.ukrposhtatest.lib.FieldsValueMatch;
import com.example.ukrposhtatest.lib.ValidEmail;
import com.example.ukrposhtatest.model.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Data
public class ProgrammerRequestDto extends UserRequestDto {
    @NotNull(message = "Name can't be null")
    private String name;
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;
    private Programmer.Level level;
    private Programmer.Type type;
}
