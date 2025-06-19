/*
 * Copyright (c) 2024 QPH, todos los derechos
 * reservados. Este software es confidencial y su informacion es propiedad de
 * QPH. No debe revelar tal informacion y debe usarla
 * unicamente de acorde con los terminos de uso.
 */
package com.qph.hacienda.configuration.util;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuración keycloak
 * 
 * @FechaCreacion: 18/01/2024
 * @UsuarioCreacion: marlene.saransig@qph.com.ec
 * @FechaModificacion:
 * @UsuarioModificacion:
 * @Version: 1.0
 */
@Component
public class KeycloakProvider {
	
	@Value("${keycloak.auth-server-url}")
	private String urlKeycloak;
	
	@Value("${keycloak.realm.master}")
	private String masterKeycloak;
	
	@Value("${keycloak.client.admin}")
	private String clienteAdmin;
	
	@Value("${keycloak.usuario.admin}")
	private String usuarioAdmin;
	
	@Value("${keycloak.usuario.admin.clave}")
	private String claveUsuarioAdmin;
	
	@Value("${keycloak.realm}")
	private String realm;

	public RealmResource getRealmResource() {
		Keycloak keycloak = KeycloakBuilder.builder().serverUrl(urlKeycloak).realm(masterKeycloak).grantType("password").clientId(clienteAdmin)
				.username(usuarioAdmin).password(claveUsuarioAdmin).build();
		return keycloak.realm(realm);
	}

	public Keycloak getKeycloak() {
		Keycloak keycloak = KeycloakBuilder.builder().serverUrl(urlKeycloak).realm(masterKeycloak).grantType("password").clientId(clienteAdmin)
				.username(usuarioAdmin).password(claveUsuarioAdmin).build();

		return keycloak;
	}

	public UsersResource getUserResource() {
		RealmResource realmResource = getRealmResource();
		return realmResource.users();
	}

}
