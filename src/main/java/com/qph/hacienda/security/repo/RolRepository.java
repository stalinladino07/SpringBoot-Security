package com.qph.hacienda.security.repo;

import com.qph.hacienda.security.model.Rol;
import com.qph.hacienda.security.model.RolMenu;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Rol tbl SET state = :status WHERE tbl.idRol = :id")
    void changeStatus(Long id, Boolean status);

    @Query("SELECT rolmenu FROM Rol rol, RolMenu rolmenu " +
            "WHERE rol.idRol = rolmenu.rol.idRol " +
            "AND (:status IS NULL OR rol.state=:status)" +
            "AND (:idRol IS NULL OR rol.idRol=:idRol)"
        )
    List<RolMenu> getRolMenu(Boolean status,Long idRol);

}
