package com.Ejercicio_1.repository;

import com.Ejercicio_1.Entity.Noticia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
    public Optional<Noticia> findByTitulo(String titulo);
}
