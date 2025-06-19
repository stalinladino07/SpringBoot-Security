package com.qph.hacienda.security.service;

import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.model.Rol;

public interface RolService {
     ResponseDto findAll();
     ResponseDto findRolWithMenu(Boolean status);
     ResponseDto getById(Long id);
     ResponseDto save(Rol dtoRequest);
     ResponseDto update(Rol dtoRequest);
     ResponseDto delete(Long id);
     ResponseDto updateStatus(Rol dtoRequest);
}
