package com.sintad.prueba.controller;

import com.sintad.prueba.dto.request.TipoContribuyenteRequest;
import com.sintad.prueba.dto.response.MessageResponse;
import com.sintad.prueba.model.TipoContribuyente;
import com.sintad.prueba.security.service.TipoContribuyenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.sintad.prueba.security.SecurityConfig.SECURITY_CONFIG_NAME;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tipoContribuyente")
@SecurityRequirement(name = SECURITY_CONFIG_NAME)

public class TipoContribuyenteController {
    @Autowired
    private TipoContribuyenteService tipoContribuyenteService;

    @Operation(summary = "Obtener los tipos de contribuyentes", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa",content = {
                    @Content(schema = @Schema(implementation = TipoContribuyente.class), mediaType = "application/json",
                            examples =@ExampleObject(value = "[\n" +
                                    "  {\n" +
                                    "    \"id\": 1,\n" +
                                    "    \"nombre\": \"Natural Sin Negocio\",\n" +
                                    "    \"estado\": true\n" +
                                    "  },\n" +
                                    "  {\n" +
                                    "    \"id\": 2,\n" +
                                    "    \"nombre\": \"Juridica\",\n" +
                                    "    \"estado\": true\n" +
                                    "  },\n" +
                                    "  {\n" +
                                    "    \"id\": 3,\n" +
                                    "    \"nombre\": \"Natural Con Negocio\",\n" +
                                    "    \"estado\": true\n" +
                                    "  },\n" +
                                    "  {\n" +
                                    "    \"id\": 4,\n" +
                                    "    \"nombre\": \"No Domiciliado\",\n" +
                                    "    \"estado\": true\n" +
                                    "  }\n" +
                                    "]"))
            })
    })
    @GetMapping
    public List<TipoContribuyenteRequest> index(){
        return tipoContribuyenteService.allTipoContribuyente();
    }

    @Operation(summary = "Obtener tipo de contribuyente por id", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa",content = {
                    @Content(schema = @Schema(implementation = TipoContribuyente.class), mediaType = "application/json",
                            examples =@ExampleObject(value = "{\n" +
                                    "  \"id\": 1,\n" +
                                    "  \"nombre\": \"Natural Sin Negocio\",\n" +
                                    "  \"estado\": true\n" +
                                    "}"))
            })
    })
    @GetMapping("{id}")

    public ResponseEntity<TipoContribuyenteRequest> show(@PathVariable(name = "id") long id){
        try{
            return ResponseEntity.ok(tipoContribuyenteService.oneTipoContribuyente(id));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Registrar tipo de contribuyente", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de contribuyente registrado",content = {
                    @Content( mediaType = "application/json",
                            examples =@ExampleObject(value = "{\n" +
                                    "  \"message\": \"Tipo de contribuyente creado con exito\"\n" +
                                    "}"))
            })
    })
    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody TipoContribuyenteRequest tipoContribuyenteRequest){
        try{
            tipoContribuyenteService.createTipoContribuyente(tipoContribuyenteRequest);
            return ResponseEntity.ok(new MessageResponse("Tipo de contribuyente creado con exito"));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Actualizar tipo de contribuyente", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de contribuyente actualizado",content = {
                    @Content( mediaType = "application/json",
                            examples =@ExampleObject(value = "{\n" +
                            "  \"message\": \"Tipo de contribuyente actualizado con exito\"\n" +
                            "}"))

            })
    })
    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody TipoContribuyenteRequest tipoContribuyenteRequest,
                                         @PathVariable(name = "id") long id){
        try{
            tipoContribuyenteService.updateTipoContribuyente(tipoContribuyenteRequest,id);
            return ResponseEntity.ok(new MessageResponse("Tipo de contribuyente actualizado con exito"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Eliminar tipo de contribuyente", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de contribuyente eliminado",content = {
                    @Content( mediaType = "application/json",
                            examples =@ExampleObject(value = "{\n" +
                                    "  \"message\": \"Tipo de contribuyente eliminado con exito\"\n" +
                                    "}"))
            })
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> destroy(@PathVariable(name = "id") long id){
        try{
            tipoContribuyenteService.deleteTipoContribuyente(id);
            return ResponseEntity.ok(new MessageResponse("Tipo de contribuyente eliminado con exito"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
