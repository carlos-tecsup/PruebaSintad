package com.sintad.prueba.security.service.impl;

import com.sintad.prueba.model.Rol;
import com.sintad.prueba.model.enums.ERol;
import com.sintad.prueba.repository.RolRepository;
import com.sintad.prueba.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    RolRepository rolRepository;
    @Override
    public void createRol() {
        Rol roldefault=new Rol();
        roldefault.setId(1);
        roldefault.setName(ERol.ADMIN);
        rolRepository.save(roldefault);
    }
}
