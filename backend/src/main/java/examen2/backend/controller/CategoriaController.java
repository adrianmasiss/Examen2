package examen2.backend.controller;

import examen2.backend.data.CategoriaRepository;
import examen2.backend.logic.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepo;

    @GetMapping
    public List<Categoria> getCategorias() {
        return categoriaRepo.findAll();
    }
}
