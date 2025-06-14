package examen2.backend.data;

import  examen2.backend.logic.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByNombre(String nombre);
}
