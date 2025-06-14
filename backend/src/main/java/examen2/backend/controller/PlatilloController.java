package examen2.backend.controller;

import examen2.backend.data.CategoriaRepository;
import examen2.backend.data.PlatilloRepository;
import examen2.backend.logic.Categoria;
import examen2.backend.logic.Platillo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platillos")
public class PlatilloController {

    @Autowired
    private CategoriaRepository categoriaRepo;
    @Autowired
    private PlatilloRepository platilloRepo;

    @GetMapping("/{categoriaNombre}")
    public List<Platillo> getPlatillosPorCategoria(@PathVariable String categoriaNombre) {
        Categoria categoria = categoriaRepo.findByNombre(categoriaNombre);
        return platilloRepo.findByCategoria(categoria);
    }
}
