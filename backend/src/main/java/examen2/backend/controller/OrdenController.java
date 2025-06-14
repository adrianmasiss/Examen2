package examen2.backend.controller;

import examen2.backend.data.OrdenRepository;
import examen2.backend.data.PlatilloRepository;
import examen2.backend.data.UserRepository;
import examen2.backend.logic.DetalleOrden;
import examen2.backend.logic.Orden;
import examen2.backend.logic.Platillo;
import examen2.backend.logic.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenRepository ordenRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PlatilloRepository platilloRepo;

    @PostMapping
    public OrdenResponse crearOrden(@RequestBody OrdenRequest req) {
        User usuario = userRepo.findByUsername(req.getUsuario());
        List<DetalleOrden> detalles = new ArrayList<>();
        int total = 0;
        for (DetalleRequest d : req.getDetalles()) {
            Platillo platillo = platilloRepo.findById(d.getPlatilloId()).orElseThrow();
            int precioUnit = platillo.getPrecio();
            if ("Grande".equalsIgnoreCase(d.getTamano())) {
                precioUnit = (int) Math.round(precioUnit * 1.2);
            }
            int subtotal = precioUnit * d.getCantidad();
            DetalleOrden det = new DetalleOrden(platillo, d.getCantidad(), d.getTamano(), subtotal);
            detalles.add(det);
            total += subtotal;
        }
        Orden orden = new Orden(usuario, detalles, total);
        ordenRepo.save(orden);
        // Imprimir en consola
        System.out.println("ORDEN #" + orden.getId() + " Usuario: " + usuario.getNombre() + " Total: " + total);
        return new OrdenResponse(orden.getId());
    }

    // DTO para la solicitud de orden
    public static class OrdenRequest {
        private String usuario;
        private List<DetalleRequest> detalles;

        public OrdenRequest() {}

        public String getUsuario() { return usuario; }
        public void setUsuario(String usuario) { this.usuario = usuario; }

        public List<DetalleRequest> getDetalles() { return detalles; }
        public void setDetalles(List<DetalleRequest> detalles) { this.detalles = detalles; }
    }

    // DTO para cada detalle
    public static class DetalleRequest {
        private Integer platilloId;
        private int cantidad;
        private String tamano;

        public DetalleRequest() {}

        public Integer getPlatilloId() { return platilloId; }
        public void setPlatilloId(Integer platilloId) { this.platilloId = platilloId; }

        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }

        public String getTamano() { return tamano; }
        public void setTamano(String tamano) { this.tamano = tamano; }
    }

    // DTO para la respuesta
    public static class OrdenResponse {
        private Integer numero;

        public OrdenResponse() {}

        public OrdenResponse(Integer numero) {
            this.numero = numero;
        }

        public Integer getNumero() { return numero; }
        public void setNumero(Integer numero) { this.numero = numero; }
    }
}
