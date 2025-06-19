package com.qph.hacienda.security.service;

import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.model.Rol;

public interface RolMenuService {
     ResponseDto updateListRolMenu(Rol dtoRequest);
}
