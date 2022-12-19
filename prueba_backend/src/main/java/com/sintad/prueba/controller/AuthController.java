package com.sintad.prueba.controller;


import com.sintad.prueba.dto.request.LoginRequest;
import com.sintad.prueba.dto.request.RegisterRequest;
import com.sintad.prueba.dto.response.JWTResponse;
import com.sintad.prueba.dto.response.MessageResponse;
import com.sintad.prueba.model.Rol;
import com.sintad.prueba.model.TipoContribuyente;
import com.sintad.prueba.model.User;
import com.sintad.prueba.model.enums.ERol;
import com.sintad.prueba.repository.RolRepository;
import com.sintad.prueba.repository.UserRepository;
import com.sintad.prueba.security.jwt.JwtUtils;
import com.sintad.prueba.security.service.RolService;
import com.sintad.prueba.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RolService rolService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Operation(summary = "Login de usuario", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario logueado", content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = JWTResponse.class),
                            examples =@ExampleObject(value = "{\n" +
                            "  \"token\": \"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MkBnbWFpbC5jb20iLCJpYXQiOjE2NzEzNzk4MjksImV4cCI6MTY3MTM4MzQyOX0.YKXuA2WNLGFwbKlXhX5tUXc1PFbLFAXmlSsPi6BstBg-hqB448q9JklLnT5_-1XUxvAp03lmqHTpeKKfcTd7Mw\",\n" +
                            "  \"type\": \"Bearer\",\n" +
                            "  \"email\": \"test2@gmail.com\",\n" +
                            "  \"roles\": [\n" +
                            "    \"ADMIN\"\n" +
                            "  ]\n" +
                            "}"))
            })
    })
    @PostMapping("login")
    public ResponseEntity<JWTResponse> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserService userDetails = (UserService) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JWTResponse(
                jwt,
                userDetails.getEmail(),
                roles)
        );
    }

    @Operation(summary = "Registro de usuario", security = { @SecurityRequirement(name = "BEARER KEY") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario registrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "        \"message\": \"Registrado\"\n" +
                                    "    }"))
            })
    })

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        if(Boolean.TRUE.equals(userRepository.existsByEmail(registerRequest.getEmail()))){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("El email ya esta registrado"));
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        rolService.createRol();
        Rol roles = rolRepository.findByName(ERol.ADMIN).orElse(rolService.createRol());
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Registrado"));
    }
}
