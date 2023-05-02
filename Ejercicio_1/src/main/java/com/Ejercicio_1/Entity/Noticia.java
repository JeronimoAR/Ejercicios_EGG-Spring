package com.Ejercicio_1.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String titulo,cuerpo;

    @ManyToOne
    private Usuario usuario;

    public Noticia() {
    }

    public Noticia(Integer id, String titulo, String cuerpo, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUser() {
        return usuario;
    }

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }
}
