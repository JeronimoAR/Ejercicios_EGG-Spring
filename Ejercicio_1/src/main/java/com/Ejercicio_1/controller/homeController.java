package com.Ejercicio_1.controller;

import com.Ejercicio_1.Entity.Noticia;
import com.Ejercicio_1.Service.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class homeController {
    @Autowired
    public NoticiaServicio notServ;

    @GetMapping("")
    public String home(Model model, HttpSession session){
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "principal/Principal";
    }

    @GetMapping("/noticias")
    public String showAll(Model model, HttpSession session){
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        model.addAttribute("noticias", notServ.findall());
        return "principal/show_noticias";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model, HttpSession session){
        Noticia noticia = new Noticia();
        Optional<Noticia> optionalNots = notServ.get(id);
        noticia = optionalNots.get();
        model.addAttribute("noticia", noticia);
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "principal/noticia_show";
    }

    @PostMapping("/search")
    public String search(@RequestParam String titulo, Model model, HttpSession session){
        List<Noticia> nots = notServ.listarNoticias().stream().filter(p -> p.getTitulo().contains(titulo)).collect(Collectors.toList());
        model.addAttribute("noticias", nots);
        model.addAttribute("sesion", session);
        return "principal/show_noticias";
    }
}