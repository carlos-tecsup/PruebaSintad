package com.sintad.prueba.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class RegisterRequest {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> rol;
}
