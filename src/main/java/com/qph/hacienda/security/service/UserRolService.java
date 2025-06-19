package com.qph.hacienda.security.service;

import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.model.User;

public interface UserRolService {
     ResponseDto updateListUserRol(User dtoRequest);
}
