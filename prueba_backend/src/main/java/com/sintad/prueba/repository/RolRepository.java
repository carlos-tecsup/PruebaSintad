package com.sintad.prueba.repository;


import com.sintad.prueba.model.Rol;
import com.sintad.prueba.model.enums.ERol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByName(ERol name);

}
