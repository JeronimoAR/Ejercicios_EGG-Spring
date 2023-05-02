package com.Ejercicio_1.repository;

import com.Ejercicio_1.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Integer> {
    public Optional<Usuario> findByEmail(String email);
}
