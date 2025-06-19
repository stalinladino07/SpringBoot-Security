/*
 * Copyright (c) 2024 QPH, todos los derechos
 * reservados. Este software es confidencial y su informacion es propiedad de
 * QPH. No debe revelar tal informacion y debe usarla
 * unicamente de acorde con los terminos de uso.
 */
package com.qph.hacienda.security.service.impl;

import com.qph.hacienda.configuration.util.Constants;
import com.qph.hacienda.configuration.util.KeycloakProvider;
import com.qph.hacienda.configuration.util.UserKeycloakMapper;
import com.qph.hacienda.security.dto.UserKeycloakDto;
import com.qph.hacienda.security.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Servicios keycloak
 * 
 * @FechaCreacion: 16/03/2024
 * @UsuarioCreacion: marlene.saransig@qph.com.ec
 * @FechaModificacion: 
 * @UsuarioModificacion: 
 * @Version: 1.0
 */
@Service
public class KeycloakServiceImpl implements KeycloakService {

	@Autowired
	private KeycloakProvider keycloakProvider;
	@Autowired
	private UserKeycloakMapper userKeycloakMapper;
	public List<UserRepresentation> getListUserByParam(String param) {
		List<UserRepresentation> userRepresentations = keycloakProvider.getRealmResource().users().search(param,
				Constants.NUMBER_MIN_USERS_KEYCLOAK, Constants.NUMBER_MAX_USERS_KEYCLOAK);
		return userRepresentations.stream()
				.filter(x -> x.getEmail() != null && x.getFirstName() != null && x.getLastName() != null)
				.collect(Collectors.toList());
	}
	
	public List<UserKeycloakDto> getLisUser(String param) {
		List<UserRepresentation> listado = getListUserByParam(param);
		return listado.stream().map(userKeycloakMapper::getUserKeycloackDTO)
				.collect(Collectors.toList());
	}
	

}
