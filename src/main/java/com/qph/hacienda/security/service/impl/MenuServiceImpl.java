package com.qph.hacienda.security.service.impl;

import com.qph.hacienda.configuration.util.AppUtils;
import com.qph.hacienda.configuration.util.Constants;
import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.model.Menu;
import com.qph.hacienda.security.model.UserRol;
import com.qph.hacienda.security.repo.MenuRepository;
import com.qph.hacienda.security.repo.UserRepository;
import com.qph.hacienda.security.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseDto findAll() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Menu> list = menuRepository.findAll();
            responseDto.setMessage(Constants.MESSAGE_CONSULT);
            responseDto.setData(list);
        } catch (Exception e) {
            String detailError = e.getMessage();
            responseDto.setMessage(detailError);
        }
        return responseDto;
    }
    @Override
    public ResponseDto findMenuWithChildrens() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Menu> list = menuRepository.findAll();
            responseDto.setData(AppUtils.buildMenuTree(list));
            responseDto.setMessage(Constants.MESSAGE_CONSULT);
        } catch (Exception e) {
            String detailError = e.getMessage();
            responseDto.setMessage(detailError);
        }
        return responseDto;
    }

    @Override
    public ResponseDto findMenuFather() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Menu> list = menuRepository.findByIsMainTrueAndStateTrue();
            responseDto.setData(AppUtils.buildMenuTree(list));
            responseDto.setMessage(Constants.MESSAGE_CONSULT);
        } catch (Exception e) {
            String detailError = e.getMessage();
            responseDto.setMessage(detailError);
        }
        return responseDto;
    }
    @Override
    public ResponseDto getById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<Menu> resp = menuRepository.findById(id);
            responseDto.setData(resp);
            responseDto.setMessage(Constants.MESSAGE_CONSULT);
        } catch (Exception e) {
            String detailError = e.getMessage();
            responseDto.setMessage(detailError);
        }
        return responseDto;
    }
    @Override
    public ResponseDto save(Menu dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            Menu obj = menuRepository.save(dtoRequest);
            resp.setMessage(Constants.MESSAGE_CREATE);
            resp.setData(obj);
        } catch (Exception e) {
            String detalleError = e.getMessage();
            resp.setMessage(detalleError);
        }
        return resp;
    }

    @Override
    public ResponseDto update(Menu dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            Menu obj = menuRepository.save(dtoRequest);
            resp.setData(obj);
            resp.setMessage(Constants.MESSAGE_UPDATE);
        } catch (Exception e) {
            String detalleError = e.getMessage();
            resp.setMessage(detalleError);
            if(e.getCause().getCause().toString().contains("ConstraintViolationException")) {
                resp.setMessage(Constants.RECORD_NOT_FOUND);
            }
        }
        return resp;
    }

    @Override
    public ResponseDto delete(Long id) {
        ResponseDto resp = new ResponseDto();
        try {
            menuRepository.deleteById(id);
            resp.setData(id);
            resp.setMessage(Constants.MESSAGE_DELETE);
        } catch (Exception e) {
            String detalleError = e.getMessage();
            resp.setMessage(detalleError);
            if(e.getCause().getCause().toString().contains("ConstraintViolationException")) {
                resp.setMessage(Constants.RECORD_NOT_FOUND);
            }
        }
        return resp;
    }

    @Override
    public ResponseDto updateStatus(Menu dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            menuRepository.changeStatus(dtoRequest.getIdMenu(),dtoRequest.getState());
            resp.setData(dtoRequest);
            resp.setMessage(Constants.MESSAGE_UPDATE);
        } catch (Exception e) {
            String detalleError = e.getMessage();
            resp.setMessage(detalleError);
            if(e.getCause().getCause().toString().contains("ConstraintViolationException")) {
                resp.setMessage(Constants.RECORD_NOT_FOUND);
            }
        }
        return resp;
    }

    @Override
    public ResponseDto findMenuByRol(String email) {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<UserRol> roles = userRepository.getRolByEmail(email);
            List<Long> idsRol = new ArrayList<>();
            for(UserRol objUserRol: roles){
                idsRol.add(objUserRol.getRol().getIdRol());
            }
            List<Menu> list = menuRepository.findMenuByRol(idsRol);
            responseDto.setData(AppUtils.buildMenuTree(list));
            responseDto.setMessage(Constants.MESSAGE_CONSULT);
        } catch (Exception e) {
            String detailError = e.getMessage();
            responseDto.setMessage(detailError);
        }
        return responseDto;
    }



}
