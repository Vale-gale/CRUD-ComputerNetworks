package com.valeg.crudredes.controler;

import com.valeg.crudredes.interfacesService.IPersonaService;
import com.valeg.crudredes.modelo.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class Controlador{

    private IPersonaService service;

    public Controlador(IPersonaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String listar(Model model){
        List<Persona> personas=service.listar();
        model.addAttribute("personas",personas);
        return "index";
    }

    @GetMapping("/nuevo")
    public String agregar(Model model){
        model.addAttribute("persona", new Persona());
        return "form";
    }

    @PostMapping("/guardar")
    public String save(@Validated Persona p, Model model){
        service.save(p);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        Optional<Persona> persona=service.listarId(id);
        model.addAttribute("persona", persona);
        return "form";
    }
    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable int id, Model model){
        service.delete(id);
        return "redirect:/";
    }

}
