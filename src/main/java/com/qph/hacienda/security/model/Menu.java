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
@Table(name = "tbl_menu",schema = "sc_security")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu extends EntityAudit {
   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private Long idMenu;

    @Column(name="id_father")
    private Long idFather;

    @Column(name="name")
    private String label;

    @Column(name="url")
    private String routerLink;

    @Column(name="icon")
    private String icon;

    @Column(name="order_index")
    private Integer order;

    @Column(name="is_main")
    private Boolean isMain;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Transient
    private Long idRolMenu;

    @Transient
    private List<Menu> items;

    public Menu(Menu menu) {
        this.idMenu = menu.getIdMenu();
        this.idFather = menu.getIdFather();
        this.label = menu.getLabel();
        this.routerLink = menu.getRouterLink();
        this.icon = menu.getIcon();
        this.order = menu.getOrder();
        this.isMain = menu.getIsMain();
    }
}
