package examen2.backend.data;

import examen2.backend.logic.Categoria;
import examen2.backend.logic.Platillo;
import examen2.backend.logic.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {

    @Autowired
    public DataLoader(UserRepository userRepository, CategoriaRepository categoriaRepository, PlatilloRepository platilloRepository) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userRepository.save(new User("JPerez", bCryptPasswordEncoder.encode("1"), "CLI", "Juan Perez"));
        userRepository.save(new User("MMata", bCryptPasswordEncoder.encode("1"), "CLI", "Maria Mata"));

        Categoria cat;  List<Platillo> platillos;

        cat = new Categoria("Entradas");
        categoriaRepository.save(cat);
        platillos = Arrays.asList(
                new Platillo("Ensalada Capresse", "Tomate, mozzarella y hojas de albahaca fresca", 5000, cat),
                new Platillo("Crema Espinaca", "Crema a base caldo de pollo, leche, queso parmesano y abundante espinaca", 4000, cat),
                new Platillo("Patacones", "Plátanos verdes fritos y majados, con guacamole dip a elección", 3500, cat),
                new Platillo("Papas al Romero", "Papas horneadas con una mezcla de romero fresco orgánico y sal himalaya", 3000, cat)
        );
        platilloRepository.saveAll(platillos);

        cat =new Categoria("Carnes");
        categoriaRepository.save(cat);
        platillos = Arrays.asList(
                new Platillo("Lomito a la Parrilla", "Lomito prime con papas baby, chipotle y cebolla chalota", 15000, cat),
                new Platillo("Picaña", "Picaña al horno con papas y pimientos", 17000, cat),
                new Platillo("Fajitas de Pollo", "Tiras de pollo a la parrilla mezcladas con verduras y tortillas", 14000, cat),
                new Platillo("Filete de Pescado", "Filete empanizado, con guarnición de arroz y ensalada", 16500, cat)
        );
        platilloRepository.saveAll(platillos);

        categoriaRepository.save(new Categoria("Sopas"));
        categoriaRepository.save(new Categoria("Arroces"));
        categoriaRepository.save(new Categoria("Bebidas"));
        categoriaRepository.save(new Categoria("Postres"));
    }
}