/*
 * Copyright (c) 2024 QPH, todos los derechos
 * reservados. Este software es confidencial y su informacion es propiedad de
 * QPH. No debe revelar tal informacion y debe usarla
 * unicamente de acorde con los terminos de uso.
 */
package com.qph.hacienda.security.controller;

import com.qph.hacienda.security.dto.UserKeycloakDto;
import com.qph.hacienda.security.service.KeycloakService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Servicios para keycloak
 * 
 * @FechaCreacion: 18/01/2024
 * @UsuarioCreacion: marlene.saransig@qph.com.ec
 * @FechaModificacion: 
 * @UsuarioModificacion: 
 * @Version: 1.0
 */
@Tag(name = "KeycloakController", description = "Keycloak service")
@RestController
@RequestMapping("/keycloak")
@RequiredArgsConstructor
public class KeycloakControlador {
	@Autowired
	private KeycloakService keycloakService;
	
	/**
	 * Obtener listado usuarios
	 * @return
	 */
	@GetMapping("/getListUser/{parametro}")
	@Operation(summary = "Obtener lista de usuarios dado nombre o apellido")
	public List<UserKeycloakDto> getLisUser(@PathVariable String parametro) {
		return keycloakService.getLisUser(parametro);
	}
	
}
