package examen2.backend.data;

import examen2.backend.logic.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Integer> {
}
