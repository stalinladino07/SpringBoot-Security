package com.qph.hacienda.security.model;

import com.qph.hacienda.configuration.security.EntityAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tbl_user",schema = "sc_security")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends EntityAudit {
   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private Long idUser;

    @Column(name="name")
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Transient
    private List<Rol> roles;

}
