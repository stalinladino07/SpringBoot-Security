package com.qph.hacienda.security.repo;

import com.qph.hacienda.security.model.Menu;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Menu tbl SET state = :status WHERE tbl.idMenu = :id")
    void changeStatus(Long id, Boolean status);
    List<Menu> findByIsMainTrueAndStateTrue();

    @Query("SELECT menu FROM Menu menu,Rol rol, RolMenu rolmenu " +
            "WHERE rol.idRol = rolmenu.rol.idRol " +
            "AND menu.idMenu = rolmenu.menu.idMenu " +
            "AND menu.state = true " +
            "AND rol.idRol IN :idRol " +
            "ORDER BY menu.order ASC"
    )
    List<Menu> findMenuByRol(List<Long> idRol);

}
