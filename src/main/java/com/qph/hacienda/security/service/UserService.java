package com.qph.hacienda.security.service;

import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.model.User;

public interface UserService {
     ResponseDto findAll();
     ResponseDto findUserWithRol(Boolean status);
     ResponseDto getById(Long id);
     ResponseDto save(User dtoRequest);
     ResponseDto update(User dtoRequest);
     ResponseDto delete(Long id);
     ResponseDto updateStatus(User dtoRequest);
}
