package com.sintad.prueba.controller;

import com.sintad.prueba.dto.request.TipoDocumentoRequest;
import com.sintad.prueba.dto.response.MessageResponse;
import com.sintad.prueba.security.service.TipoDocumentoService;
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
@RequestMapping("/api/tipoDocumento")
public class TipoDocumentoController {
    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @Operation(summary = "Obtener todos los tipos de documento", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa",content = {
                    @Content( mediaType = "application/json")
            })
    })
    @GetMapping
    public List<TipoDocumentoRequest> index(){
        return tipoDocumentoService.allTipoDocumento();
    }

    @Operation(summary = "Obtener  tipo de documento por id", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa",content = {
                    @Content( mediaType = "application/json")
            })
    })
    @GetMapping("{id}")
    public ResponseEntity<TipoDocumentoRequest> show(@PathVariable(name = "id") long id){
        try{
            return ResponseEntity.ok(tipoDocumentoService.oneTipoDocumento(id));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Registrar tipo de documento", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de documento registrado",content = {
                    @Content( mediaType = "application/json")
            })
    })
    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody TipoDocumentoRequest tipoDocumentoRequest){
        try{
            tipoDocumentoService.createTipoDocumento(tipoDocumentoRequest);
            return ResponseEntity.ok(new MessageResponse("Tipo de documento creado con exito"));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Actualizar tipo de documento", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de documento actualizado",content = {
                    @Content( mediaType = "application/json")
            })
    })
    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody TipoDocumentoRequest tipoDocumentoRequest,
                                         @PathVariable(name = "id") long id){
        try{
            tipoDocumentoService.updateTipoDocumento(tipoDocumentoRequest,id);
            return ResponseEntity.ok(new MessageResponse("Tipo de documento actualizado con exito"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Eliminar tipo de documento", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de documento eliminado",content = {
                    @Content( mediaType = "application/json")
            })
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> destroy(@PathVariable(name = "id") long id){
        try{
            tipoDocumentoService.deleteTipoDocumento(id);
            return ResponseEntity.ok(new MessageResponse("Tipo de documento eliminado con exito"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
