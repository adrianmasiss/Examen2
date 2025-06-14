package examen2.backend.data;

import examen2.backend.logic.Platillo;
import examen2.backend.logic.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatilloRepository extends JpaRepository<Platillo, Integer> {
    List<Platillo> findByCategoria(Categoria categoria);
}
