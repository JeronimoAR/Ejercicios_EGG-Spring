package com.Ejercicio_1.Service;

import com.Ejercicio_1.Entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UserCRUD {
    public Usuario save(Usuario usuario);
    public void delete(Integer id);
    public void update(Usuario usuario);
    public Optional<Usuario> get(Integer id);
    public Optional<Usuario> findByEmail(String email);
    public List<Usuario> findAll();
}
