/*
 * Copyright (c) 2024 QPH, todos los derechos
 * reservados. Este software es confidencial y su informacion es propiedad de
 * QPH. No debe revelar tal informacion y debe usarla
 * unicamente de acorde con los terminos de uso.
 */
package com.qph.hacienda.configuration.util;

import com.qph.hacienda.security.dto.UserKeycloakDto;
import org.springframework.stereotype.Component;
import org.keycloak.representations.idm.UserRepresentation;

/**
 * Mapper de usuario
 * 
 * @FechaCreacion: 18/01/2024
 * @UsuarioCreacion: marlene.saransig@qph.com.ec
 * @FechaModificacion: 
 * @UsuarioModificacion: 
 * @Version: 1.0
 */
@Component
public class UserKeycloakMapper {
	
	public UserKeycloakDto getUserKeycloackDTO(UserRepresentation userRepresentation) {
		return new UserKeycloakDto(
				userRepresentation.getFirstName() ,userRepresentation.getLastName(),
				userRepresentation.getEmail(),userRepresentation.getFirstName() +" " +userRepresentation.getLastName());
	}

}
