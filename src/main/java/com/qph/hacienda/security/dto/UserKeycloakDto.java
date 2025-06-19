/*
 * Copyright (c) 2024 QPH, todos los derechos
 * reservados. Este software es confidencial y su informacion es propiedad de
 * QPH. No debe revelar tal informacion y debe usarla
 * unicamente de acorde con los terminos de uso.
 */
package com.qph.hacienda.security.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * Dto usuario base
 * 
 * @FechaCreacion: 20/01/2024
 * @UsuarioCreacion: marlene.saransig@qph.com.ec
 * @FechaModificacion:
 * @UsuarioModificacion:
 * @Version: 1.0
 */
@Getter
@Setter
public class UserKeycloakDto {
	private String name;
	private String lastName;
	private String email;
	private String key;
	private String completeName;
	public UserKeycloakDto() {}
	public UserKeycloakDto(String nombres, String apellidos, String correo, String nombreCompleto) {
		this.name = nombres;
		this.lastName = apellidos;
		this.email = correo;
		this.completeName = nombreCompleto;
	}
}
