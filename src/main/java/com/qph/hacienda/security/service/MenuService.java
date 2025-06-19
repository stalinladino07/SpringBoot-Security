package com.qph.hacienda.security.service;

import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.model.Menu;

public interface MenuService {
     ResponseDto findAll();
     ResponseDto findMenuWithChildrens();
     ResponseDto findMenuFather();
     ResponseDto getById(Long id);
     ResponseDto save(Menu dtoRequest);
     ResponseDto update(Menu dtoRequest);
     ResponseDto delete(Long id);
     ResponseDto updateStatus(Menu dtoRequest);
     ResponseDto findMenuByRol(String email);

}
