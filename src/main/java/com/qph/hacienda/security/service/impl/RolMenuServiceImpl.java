package com.qph.hacienda.security.service.impl;

import com.qph.hacienda.configuration.util.Constants;
import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.model.*;
import com.qph.hacienda.security.repo.RolMenuRepository;
import com.qph.hacienda.security.service.RolMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolMenuServiceImpl implements RolMenuService {
    @Autowired
    private RolMenuRepository rolMenuRepository;

    @Override
    public ResponseDto updateListRolMenu(Rol dtoRequest) {
        ResponseDto responseDto = new ResponseDto();
        try {
            for (Menu objMenu: dtoRequest.getMenus()){
                if(!objMenu.getState()) {
                    rolMenuRepository.deleteById(objMenu.getIdRolMenu());
                } else if(objMenu.getIdRolMenu() == null) {
                    RolMenu rolMenuObj = new RolMenu();
                    Rol rol= new Rol();
                    Menu menu = new Menu();
                    menu.setIdMenu(objMenu.getIdMenu());
                    rol.setIdRol(dtoRequest.getIdRol());
                    rolMenuObj.setRol(rol);
                    rolMenuObj.setMenu(menu);
                    rolMenuRepository.save(rolMenuObj);
                }
            }
            responseDto.setMessage(Constants.MESSAGE_CONSULT);
            responseDto.setData(dtoRequest.getMenus());
        } catch (Exception e) {
            String detailError = e.getMessage();
            responseDto.setMessage(detailError);
        }
        return responseDto;
    }
}
