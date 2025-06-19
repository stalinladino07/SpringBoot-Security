package com.qph.hacienda.security.controller;

import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.model.User;
import com.qph.hacienda.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "UserController", description = "User service")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/findAll")
    @Operation(summary = "Obtener información del menú")
    public ResponseEntity<ResponseDto> findAllActivity() {
        ResponseDto resp = new ResponseDto();
        try {
            resp = userService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping
    @Operation(summary = "Obtener información del menú")
    public ResponseEntity<ResponseDto> findUserWithRol(@RequestParam(required = false) Boolean status) {
        ResponseDto resp = new ResponseDto();
        try {
            resp = userService.findUserWithRol(status);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener información del menú")
    public ResponseEntity<ResponseDto> getById(@PathVariable("id") Long id) {
        ResponseDto resp = new ResponseDto();
        try {
            resp = userService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo elemento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody User dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            dtoRequest.setIdUser(null);
            resp = userService.save(dtoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @PutMapping
    @Operation(summary = "Modificar elemento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro modificado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ResponseDto> update(@Parameter(description = "Datos a modificar", required = true) @RequestBody User dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            resp = userService.update(dtoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminación lógica de un logo empresa por id")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long id) {
        ResponseDto resp = new ResponseDto();
        try {
            resp = userService.delete(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @PutMapping("/changeStatus")
    @Operation(summary = "Modificar elemento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro modificado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ResponseDto> updateStatus(@Parameter(description = "Datos a modificar", required = true) @RequestBody User dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            resp = userService.updateStatus(dtoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

}
