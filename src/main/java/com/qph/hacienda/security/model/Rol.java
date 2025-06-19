package com.qph.hacienda.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qph.hacienda.configuration.security.EntityAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tbl_rol",schema = "sc_security")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rol extends EntityAudit {
   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private Long idRol;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Transient
    private Long idUserRol;

    @Transient
    private List<Menu> menus;
    public Rol(Rol rol) {
        this.idRol = rol.getIdRol();
        this.name = rol.getName();
        this.description = rol.getDescription();
    }

}
