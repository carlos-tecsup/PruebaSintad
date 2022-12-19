package com.sintad.prueba.controller;

import com.sintad.prueba.dto.request.EntidadRequest;
import com.sintad.prueba.dto.response.MessageResponse;
import com.sintad.prueba.security.service.EntidadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/entidad")
public class EntidadController {
    @Autowired
    private EntidadService entidadService;

    @Operation(summary = "Obtener todas las entidades", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa",content = {
                    @Content( mediaType = "application/json")
            })
    })
    @GetMapping
    public List<EntidadRequest> index(){
        return entidadService.allEntidad();
    }

    @Operation(summary = "Obtener entidad por id", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa",content = {
                    @Content( mediaType = "application/json")
            })
    })
    @GetMapping("{id}")
    public ResponseEntity<EntidadRequest> show(@PathVariable(name = "id") long id){
        try{
            return ResponseEntity.ok(entidadService.oneEntidad(id));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }


    @Operation(summary = "Registrar entidad", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entidad registrada",content = {
                    @Content( mediaType = "application/json",
                            examples =@ExampleObject(value = "{\n" +
                                    "  \"message\": \"Entidad registrada con exito\"\n" +
                                    "}"))
            })
    })
    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody EntidadRequest entidadDTO){
        try{
            entidadService.createEntidad(entidadDTO);
            return ResponseEntity.ok(new MessageResponse("Entidad creada"));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Actualizar entidad", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entidad actualizada",content = {
                    @Content( mediaType = "application/json")
            })
    })
    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody EntidadRequest entidadDTO,
                                         @PathVariable(name = "id") long id){
        try{
            entidadService.updateEntidad(entidadDTO, id);
            return ResponseEntity.ok(new MessageResponse("Entidad actualizada con exito"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Eliminar entidad", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entidad eliminada",content = {
                    @Content( mediaType = "application/json")
            })
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> destroy(@PathVariable(name = "id") long id){
        try{
            entidadService.deleteEntidad(id);
            return ResponseEntity.ok(new MessageResponse("Entidad eliminada con exito"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
