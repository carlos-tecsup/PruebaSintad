package com.sintad.prueba.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TipoDocumentoRequest {

    private long id;

    @NotBlank
    @Size(min=1, max=20)
    private String codigo;

    @NotBlank
    @Size(max=100)
    private String nombre;

    @Size(max=200)
    private String descripcion;

    private boolean estado = true;
}
