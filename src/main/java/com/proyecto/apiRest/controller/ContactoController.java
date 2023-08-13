package com.proyecto.apiRest.controller;

import com.proyecto.apiRest.modelo.Contacto;
import com.proyecto.apiRest.repositorio.ContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactoController {

    @Autowired
    private ContactoRepositorio contactoRepositorio;

    @GetMapping("/")
    public String verPgna(Model model){
        List<Contacto> listarContactos = contactoRepositorio.findAll();
        model.addAttribute("contactos",listarContactos);
        return "index";
    }
    @GetMapping("/nuevo")
    public String MostrarFormularioDeRegistro(Model model){
        model.addAttribute("contacto", new Contacto());
        return "nuevo";
    }
    @PostMapping("/nuevo")
    public String guardarContacto(Contacto contacto, RedirectAttributes redirect){
        contactoRepositorio.save(contacto);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido agregado con éxito");
        return "redirect:/";
    }











}
