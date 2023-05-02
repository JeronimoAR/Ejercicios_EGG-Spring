package com.Ejercicio_1.Service;

import com.Ejercicio_1.repository.NoticiaRepository;
import com.Ejercicio_1.Entity.Noticia;
import com.Ejercicio_1.Exceptions.NoticiaException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepository notRep;
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws NoticiaException{
        Noticia not = new Noticia();
        not.setTitulo(titulo);
        not.setCuerpo(cuerpo);
        
        notRep.save(not);
    }
    
    public List<Noticia> listarNoticias(){
        List<Noticia> nots = new ArrayList();
        
        nots = notRep.findAll();
        return nots;
    }
    
    public void modificarNoticia(Integer id, String titulo, String cuerpo) throws NoticiaException{
        Optional<Noticia> nots = notRep.findByTitulo(titulo);
        
        if(nots.isPresent()){
             Noticia not = nots.get();
             not.setTitulo(titulo);
             not.setCuerpo(cuerpo);
             notRep.save(not);
        }else{
            throw new NoticiaException("La noticia ingresada no existe");
        }
    }
    
    public List<Noticia> findall(){
        return notRep.findAll();
    }
    
    public Optional<Noticia> get(Integer id){
        return notRep.findById(id);
    }
    
    public Noticia save(Noticia noticia){
        return notRep.save(noticia);
    }
    
    public void delete(Integer id){
        notRep.deleteById(id);
    }
}
