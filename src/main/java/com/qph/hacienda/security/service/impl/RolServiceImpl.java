package com.qph.hacienda.security.service.impl;

import com.qph.hacienda.configuration.util.AppUtils;
import com.qph.hacienda.configuration.util.Constants;
import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.dto.RolMenuDto;
import com.qph.hacienda.security.model.*;
import com.qph.hacienda.security.repo.RolMenuRepository;
import com.qph.hacienda.security.repo.RolRepository;
import com.qph.hacienda.security.service.RolMenuService;
import com.qph.hacienda.security.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository repository;
    @Autowired
    private RolMenuRepository rolMenuRepository;

    @Autowired
    private RolMenuService rolMenuService;

    @Override
    public ResponseDto findAll() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Rol> list = repository.findAll();
            responseDto.setMessage(Constants.MESSAGE_CONSULT);
            responseDto.setData(list);
        } catch (Exception e) {
            String detailError = e.getMessage();
            responseDto.setMessage(detailError);
        }
        return responseDto;
    }

    @Override
    public ResponseDto findRolWithMenu(Boolean status) {
        try {
            List<RolMenu> list = repository.getRolMenu(status,null);
            // Agrupar por usuario
            Map<Rol, List<Menu>> grouped = list.stream()
                    .collect(Collectors.groupingBy(
                            RolMenu::getRol,
                            Collectors.mapping(
                                    ur -> {
                                        Menu modified = new Menu(ur.getMenu());
                                        modified.setIdRolMenu(ur.getId()); // extra campo
                                        return modified;
                                    },
                                    Collectors.toList()
                            )
                    ));

            // Convertir a lista de DTOs
            List<RolMenuDto> result = grouped.entrySet().stream()
                    .map(entry -> new RolMenuDto(entry.getKey(), entry.getValue()))
                    .toList();

            return new ResponseDto(Constants.MESSAGE_CONSULT, result);
        } catch (Exception e) {
            return new ResponseDto(e.getMessage(), null);
        }
    }

    @Override
    public ResponseDto getById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<RolMenu> list = repository.getRolMenu(null,id);
            // Agrupar por usuario
            Map<Rol, List<Menu>> grouped = list.stream()
                    .collect(Collectors.groupingBy(
                            RolMenu::getRol,
                            Collectors.mapping(
                                    ur -> {
                                        Menu modified = new Menu(ur.getMenu());
                                        modified.setIdRolMenu(ur.getId()); // extra campo
                                        return modified;
                                    },
                                    Collectors.toList()
                            )
                    )); // Convertir a lista de DTOs
            RolMenuDto result = grouped.entrySet().stream()
                    .map(entry -> new RolMenuDto(entry.getKey(), AppUtils.buildMenuTree(entry.getValue())))
                    .findFirst()
                    .orElse(null);


            return new ResponseDto(Constants.MESSAGE_CONSULT, result);
        } catch (Exception e) {
            String detailError = e.getMessage();
            responseDto.setMessage(detailError);
        }
        return responseDto;
    }

    @Override
    public ResponseDto save(Rol dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            Rol saveRol = repository.save(dtoRequest);
            // Crear entidades UserRol y guardarlas
            List<RolMenu> menuRoles = dtoRequest.getMenus().stream()
                    .map(menu -> {
                        RolMenu menuRol = new RolMenu();
                        menuRol.setRol(saveRol);
                        menuRol.setMenu(menu);
                        return menuRol;
                    })
                    .toList();
            rolMenuRepository.saveAll(menuRoles);

            resp.setMessage(Constants.MESSAGE_CREATE);
            resp.setData(menuRoles);
        } catch (Exception e) {
            String detalleError = e.getMessage();
            resp.setMessage(detalleError);
        }
        return resp;
    }

    @Override
    public ResponseDto update(Rol dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            Rol obj = repository.save(dtoRequest);
            rolMenuService.updateListRolMenu(dtoRequest);
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
            repository.deleteById(id);
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
    public ResponseDto updateStatus(Rol dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            repository.changeStatus(dtoRequest.getIdRol(),dtoRequest.getState());
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

}
