package cichecki.kacper.jsonflattener.dto;

import cichecki.kacper.jsonflattener.validators.PasswordMatches;
import cichecki.kacper.jsonflattener.validators.ValidEmail;
import cichecki.kacper.jsonflattener.validators.ValidPassword;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordMatches
@Data
public class UserDto {

    @NotBlank
    @Size(min=5)
    private String firstName;

    @NotBlank
    @Size(min=5)
    private String lastName;

    @NotBlank
    @ValidPassword
    private String password;

    @NotBlank
    private String matchingPassword;

    @ValidEmail
    @NotBlank
    private String email;

}
