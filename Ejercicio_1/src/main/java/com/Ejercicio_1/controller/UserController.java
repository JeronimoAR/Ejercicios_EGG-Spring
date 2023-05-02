package com.Ejercicio_1.controller;

import com.Ejercicio_1.Entity.Usuario;
import com.Ejercicio_1.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
    public Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserService userServ;
    @GetMapping("")
    public String users(Model model){
        model.addAttribute("usuarios", userServ.findAll());
        return "user/users";
    }
    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "user/login";
    }

    @GetMapping("/register")
    public String register(Model model, HttpSession session) {
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "user/register";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){
        log.info("Usuario {}", usuario);
        usuario.setRol("USER");
        usuario.setPassword(passEncode.encode(usuario.getPassword()));
        userServ.save(usuario);
        return "redirect:/user/login";
    }

    @GetMapping("/edit/{id}")
    public String save(@PathVariable Integer id, Model model){
        Usuario user = userServ.get(id).get();
        model.addAttribute("usuario", user);
        return "user/user_edit";
    }

    @GetMapping("/acces")
    public String acces(HttpSession session){
        Integer id = (Integer) session.getAttribute("idusuario");
        Optional<Usuario> usuarioOptional = userServ.get(id);
        log.info("USER: {}", usuarioOptional);
        if(usuarioOptional.isPresent()){
            if(usuarioOptional.get().getRol().equals("ADMIN")){
                return "redirect:/noticia";
            }else{
                return "redirect:/noticias";
            }
        }else{
            log.info("Usuario no encotrado");
        }
        return "redirect:/";
    }

    @GetMapping("/cerrar")
    public String cerrar(HttpSession session){
        session.removeAttribute("idusuario");
        return "redirect:/";
    }
}
