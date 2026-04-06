package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
public class ItemApiController {

    @Autowired
    private ItemRepository repository;

    // GET /api/items  →  lista todos los ítems en JSON
    @GetMapping
    public List<Item> getAll() {
        return repository.findAll();
    }

    // GET /api/items/{id}  →  obtiene un ítem por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return repository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Ítem con id " + id + " no encontrado")));
    }

    // POST /api/items  →  crea un nuevo ítem vía JSON
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Item item) {
        Item saved = repository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
