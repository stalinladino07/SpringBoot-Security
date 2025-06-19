package com.qph.hacienda.security.controller;

import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.model.Menu;
import com.qph.hacienda.security.service.MenuService;
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
@Tag(name = "MenuController", description = "Menu service")
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/findAll")
    @Operation(summary = "Obtener información del menú")
    public ResponseEntity<ResponseDto> findAllActivity() {
        ResponseDto resp = new ResponseDto();
        try {
            resp = menuService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping
    @Operation(summary = "Obtener información del menú")
    public ResponseEntity<ResponseDto> findMenuWithChildrens() {
        ResponseDto resp = new ResponseDto();
        try {
            resp = menuService.findMenuWithChildrens();
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping("/isMain")
    @Operation(summary = "Obtener información del menú")
    public ResponseEntity<ResponseDto> isMain() {
        ResponseDto resp = new ResponseDto();
        try {
            resp = menuService.findMenuFather();
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
            resp = menuService.getById(id);
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
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody Menu dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            dtoRequest.setIdMenu(null);
            resp = menuService.save(dtoRequest);
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
    public ResponseEntity<ResponseDto> update(@Parameter(description = "Datos a modificar", required = true) @RequestBody Menu dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            resp = menuService.update(dtoRequest);
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
            resp = menuService.delete(id);
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
    public ResponseEntity<ResponseDto> updateStatus(@Parameter(description = "Datos a modificar", required = true) @RequestBody Menu dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            resp = menuService.updateStatus(dtoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping("/byEmail/{email}")
    @Operation(summary = "Obtener información del menú")
    public ResponseEntity<ResponseDto> findMenuByRol(@PathVariable("email") String email) {
        ResponseDto resp = new ResponseDto();
        try {
            resp = menuService.findMenuByRol(email);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }
}
