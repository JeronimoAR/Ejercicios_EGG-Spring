package com.Ejercicio_1.controller;

import com.Ejercicio_1.Entity.Noticia;
import com.Ejercicio_1.Exceptions.NoticiaException;
import com.Ejercicio_1.Service.NoticiaServicio;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia")
public class NoticiaController {
    @Autowired
    private NoticiaServicio notServ;
    
    @GetMapping("")
    public String show(Model model){
        model.addAttribute("noticias", notServ.findall());
        return "/noticias/noticias.html";
    }
    
    @GetMapping("/registrar")
    public String registrar(){
        return "/noticias/noticia_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String titulo,@RequestParam String cuerpo, ModelMap modelo){
        try {
            notServ.crearNoticia(titulo, cuerpo);
            modelo.put("exito","La noticia fue Creada Correctamente");
        } catch (NoticiaException ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_form.html";
        }
        return "/noticias/noticia_form.html";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Noticia noticia = new Noticia();
        Optional<Noticia> optionalNots = notServ.get(id);
        noticia = optionalNots.get();
        model.addAttribute("noticia", noticia);
        return "noticias/noticia_edit.html";
    }
    
    @PostMapping("update")
    public String update(Noticia noticia){
        notServ.save(noticia);
        return "redirect:/noticia";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        notServ.delete(id);
        return "redirect:/noticia";
    }
    
}
