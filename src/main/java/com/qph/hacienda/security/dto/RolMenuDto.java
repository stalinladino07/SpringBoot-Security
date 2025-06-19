package com.qph.hacienda.security.dto;

import com.qph.hacienda.security.model.Menu;
import com.qph.hacienda.security.model.Rol;
import lombok.Data;

import java.util.List;

@Data
public class RolMenuDto {

    private Long idRol;
    private String name;
    private String description;
    private List<Menu> menus;

    public RolMenuDto(Rol rol, List<Menu> menus) {
        this.idRol = rol.getIdRol();
        this.name = rol.getName();
        this.description = rol.getDescription();
        this.menus = menus;
    }
}
