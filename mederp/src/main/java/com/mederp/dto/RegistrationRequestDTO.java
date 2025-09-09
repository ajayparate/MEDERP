package com.mederp.dto;

import com.mederp.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RegistrationRequestDTO {

    private String firstName;

    private String lastName;

    // @NotBlank(message = "Username is mandatory.")
    private String username;
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Role is mandatory")
    private Role role;

}
