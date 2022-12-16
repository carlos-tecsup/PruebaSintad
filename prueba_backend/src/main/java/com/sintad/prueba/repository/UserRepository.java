package com.sintad.prueba.repository;

import com.sintad.prueba.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByEmail(String email);

    public Boolean existsByEmail(String email);
}
