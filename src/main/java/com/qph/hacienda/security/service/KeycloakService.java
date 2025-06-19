/*
 * Copyright (c) 2024 QPH, todos los derechos
 * reservados. Este software es confidencial y su informacion es propiedad de
 * QPH. No debe revelar tal informacion y debe usarla
 * unicamente de acorde con los terminos de uso.
 */
package com.qph.hacienda.security.service;

import com.qph.hacienda.security.dto.UserKeycloakDto;
import java.util.List;


/**
 * Interfaz servicios keycloak
 * 
 * @FechaCreacion: 16/03/2024
 * @UsuarioCreacion: marlene.saransig@qph.com.ec
 * @FechaModificacion: 
 * @UsuarioModificacion: 
 * @Version: 1.0
 */
public interface KeycloakService {
	List<UserKeycloakDto> getLisUser(String parametro);
	
}
