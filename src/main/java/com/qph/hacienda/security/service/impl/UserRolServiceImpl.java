package com.qph.hacienda.security.service.impl;

import com.qph.hacienda.configuration.util.Constants;
import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.model.Rol;
import com.qph.hacienda.security.model.User;
import com.qph.hacienda.security.model.UserRol;
import com.qph.hacienda.security.repo.UserRolRepository;
import com.qph.hacienda.security.service.UserRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRolServiceImpl implements UserRolService {
    @Autowired
    private UserRolRepository userRolRepository;

    @Override
    public ResponseDto updateListUserRol(User dtoRequest) {
        ResponseDto responseDto = new ResponseDto();
        try {
            for (Rol objRol: dtoRequest.getRoles()){
                if(!objRol.getState()) {
                    userRolRepository.deleteById(objRol.getIdUserRol());
                } else if(objRol.getIdUserRol() == null) {
                    UserRol userRolObj = new UserRol();
                    Rol rol= new Rol();
                    User user = new User();
                    rol.setIdRol(objRol.getIdRol());
                    user.setIdUser(dtoRequest.getIdUser());
                    userRolObj.setRol(rol);
                    userRolObj.setUser(user);
                    userRolRepository.save(userRolObj);
                }
            }
            responseDto.setMessage(Constants.MESSAGE_CONSULT);
            responseDto.setData(dtoRequest.getRoles());
        } catch (Exception e) {
            String detailError = e.getMessage();
            responseDto.setMessage(detailError);
        }
        return responseDto;
    }
}
