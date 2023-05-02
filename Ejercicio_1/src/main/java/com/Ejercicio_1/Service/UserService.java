package com.Ejercicio_1.Service;

import com.Ejercicio_1.Entity.Usuario;
import com.Ejercicio_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserCRUD{
    @Autowired
    public UserRepository userRep;

    @Override
    public Usuario save(Usuario usuario) {
        return userRep.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        userRep.deleteById(id);
    }

    @Override
    public void update(Usuario usuario) {
        userRep.save(usuario);
    }

    @Override
    public Optional<Usuario> get(Integer id) {
        return userRep.findById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return userRep.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return userRep.findAll();
    }
}
