package com.qph.hacienda.security.repo;

import com.qph.hacienda.security.model.User;
import com.qph.hacienda.security.model.UserRol;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE User tbl SET state = :status WHERE tbl.idUser = :id")
    void changeStatus(Long id, Boolean status);

    @Query("SELECT usrol FROM User us, UserRol usrol " +
            "WHERE us.idUser = usrol.user.idUser " +
            "AND (:status IS NULL OR us.state=:status)" +
            "AND (:idUser IS NULL OR us.idUser=:idUser)")
    List<UserRol> getUserRol(Boolean status,Long idUser);

    @Query("SELECT usrol FROM User us, UserRol usrol " +
            "WHERE us.idUser = usrol.user.idUser " +
            "AND  usrol.rol.state = true " +
            "AND (:email IS NULL OR us.email=:email)" )
    List<UserRol> getRolByEmail(String email);

}
