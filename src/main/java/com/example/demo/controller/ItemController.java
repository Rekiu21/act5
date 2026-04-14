package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", repository.findAll());
        model.addAttribute("item", new Item());
        return "home";
    }

    @PostMapping("/agregar")
    public String agregar(@Valid @ModelAttribute Item item,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("items", repository.findAll());
            return "home";
        }
        repository.save(item);
        flash.addFlashAttribute("success", "¡Tarea \"" + item.getNombre() + "\" creada correctamente!");
        return "redirect:/";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         @Valid @ModelAttribute Item item,
                         BindingResult result,
                         RedirectAttributes flash) {
        if (result.hasErrors()) {
            flash.addFlashAttribute("error", "El nombre debe tener entre 2 y 100 caracteres.");
            return "redirect:/";
        }
        repository.findById(id).ifPresentOrElse(existing -> {
            existing.setNombre(item.getNombre());
            repository.save(existing);
            flash.addFlashAttribute("success", "¡Tarea \"" + item.getNombre() + "\" actualizada correctamente!");
        }, () -> flash.addFlashAttribute("error", "Tarea no encontrada."));
        return "redirect:/";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        repository.findById(id).ifPresentOrElse(item -> {
            String nombre = item.getNombre();
            repository.deleteById(id);
            flash.addFlashAttribute("success", "Tarea \"" + nombre + "\" eliminada correctamente.");
        }, () -> flash.addFlashAttribute("error", "Tarea no encontrada."));
        return "redirect:/";
    }
}   