package com.proyecto.apiRest.controller;

import com.proyecto.apiRest.modelo.Contacto;
import com.proyecto.apiRest.repositorio.ContactoRepositorio;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String guardarContacto(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "nuevo";
        }
        contactoRepositorio.save(contacto);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido agregado con éxito");
        return "redirect:/";

    }
    @GetMapping("editar/{id}")
    public String MostrarFormularioDeEditarContacto(@PathVariable Long id, Model model){
        Contacto contacto = contactoRepositorio.getReferenceById(id);
        model.addAttribute("contacto", contacto);
        return "nuevo";
    }
    @PostMapping("editar/{id}")
    public String actualizarContacto(@PathVariable Long id, @Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirect, Model model) {
        Contacto contactoDB = contactoRepositorio.getReferenceById(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("contacto",contacto);
            return "nuevo";
        }
        contactoDB.setNombre(contacto.getNombre());
        contactoDB.setEmail(contacto.getEmail());
        contactoDB.setTelefono(contacto.getTelefono());
        contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());

        contactoRepositorio.save(contactoDB);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido actualizado con éxito");
        return "redirect:/";

    }
    @PostMapping("eliminar/{id}")
    public String eliminarContacto(@PathVariable Long id, RedirectAttributes redirect){
        Contacto contacto = contactoRepositorio.getReferenceById(id);
        contactoRepositorio.delete(contacto);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido eliminado con éxito");
        return "redirect:/";
    }






}
