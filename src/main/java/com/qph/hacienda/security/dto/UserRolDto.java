package com.qph.hacienda.security.dto;

import com.qph.hacienda.security.model.Rol;
import com.qph.hacienda.security.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserRolDto {
    private Long idUser;
    private String name;
    private String lastName;
    private String email;
    private Boolean state;
    private final List<Rol> roles;

    public UserRolDto(User user, List<Rol> roles) {
        this.idUser = user.getIdUser();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roles = roles;
        this.state = user.getState();
    }
}
