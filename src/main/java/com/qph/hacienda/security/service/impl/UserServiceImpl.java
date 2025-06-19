package com.qph.hacienda.security.service.impl;

import com.qph.hacienda.configuration.util.Constants;
import com.qph.hacienda.configuration.util.ResponseDto;
import com.qph.hacienda.security.dto.UserRolDto;
import com.qph.hacienda.security.model.*;
import com.qph.hacienda.security.repo.UserRepository;
import com.qph.hacienda.security.repo.UserRolRepository;
import com.qph.hacienda.security.service.UserRolService;
import com.qph.hacienda.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserRolRepository userRolRepository;
    @Autowired
    private UserRolService userRolService;

    @Override
    public ResponseDto findAll() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<User> list = repository.findAll();
            responseDto.setMessage(Constants.MESSAGE_CONSULT);
            responseDto.setData(list);
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
            List<UserRol> list = repository.getUserRol(null,id);
            // Agrupar por usuario
            Map<User, List<Rol>> grouped = list.stream()
                    .collect(Collectors.groupingBy(
                            UserRol::getUser,
                            Collectors.mapping(
                                    ur -> {
                                        Rol modifiedRol = new Rol(ur.getRol());
                                        modifiedRol.setIdUserRol(ur.getId()); // extra campo
                                        return modifiedRol;
                                    },
                                    Collectors.toList()
                            )
                    ));

            // Convertir a lista de DTOs
            UserRolDto result = grouped.entrySet().stream()
                    .map(entry -> new UserRolDto(entry.getKey(), entry.getValue()))
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
    public ResponseDto findUserWithRol(Boolean status) {
        try {
            List<UserRol> list = repository.getUserRol(status,null);
            // Agrupar por usuario
            Map<User, List<Rol>> grouped = list.stream()
                    .collect(Collectors.groupingBy(
                            UserRol::getUser,
                            Collectors.mapping(
                                    ur -> {
                                        Rol modifiedRol = new Rol(ur.getRol());
                                        modifiedRol.setIdUserRol(ur.getId()); // extra campo
                                        return modifiedRol;
                                    },
                                    Collectors.toList()
                            )
                    ));

            // Convertir a lista de DTOs
            List<UserRolDto> result = grouped.entrySet().stream()
                    .map(entry -> new UserRolDto(entry.getKey(), entry.getValue()))
                    .toList();

            return new ResponseDto(Constants.MESSAGE_CONSULT, result);
        } catch (Exception e) {
            return new ResponseDto(e.getMessage(), null);
        }
    }

    @Override
    public ResponseDto save(User dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            // Guardar el usuario
            dtoRequest.setState(true);
            User savedUser = repository.save(dtoRequest);
            // Crear entidades UserRol y guardarlas
            List<UserRol> userRoles = dtoRequest.getRoles().stream()
                    .map(rol -> {
                        UserRol userRol = new UserRol();
                        userRol.setUser(savedUser);
                        userRol.setRol(rol);
                        return userRol;
                    })
                    .toList();
            userRolRepository.saveAll(userRoles);
            resp.setMessage(Constants.MESSAGE_CREATE);
            resp.setData(savedUser);

        } catch (Exception e) {
            String detalleError = e.getMessage();
            resp.setMessage(detalleError);
        }
        return resp;
    }

    @Override
    public ResponseDto update(User dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            User obj = repository.save(dtoRequest);
            userRolService.updateListUserRol(dtoRequest);
            resp.setData(obj);
            resp.setMessage(Constants.MESSAGE_UPDATE);
        } catch (Exception e) {
            String detalleError = e.getMessage();
            resp.setMessage(detalleError);
            if (e.getCause().getCause().toString().contains("ConstraintViolationException")) {
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
            if (e.getCause().getCause().toString().contains("ConstraintViolationException")) {
                resp.setMessage(Constants.RECORD_NOT_FOUND);
            }
        }
        return resp;
    }

    @Override
    public ResponseDto updateStatus(User dtoRequest) {
        ResponseDto resp = new ResponseDto();
        try {
            repository.changeStatus(dtoRequest.getIdUser(), dtoRequest.getState());
            resp.setData(dtoRequest);
            resp.setMessage(Constants.MESSAGE_UPDATE);
        } catch (Exception e) {
            String detalleError = e.getMessage();
            resp.setMessage(detalleError);
            if (e.getCause().getCause().toString().contains("ConstraintViolationException")) {
                resp.setMessage(Constants.RECORD_NOT_FOUND);
            }
        }
        return resp;
    }

}
