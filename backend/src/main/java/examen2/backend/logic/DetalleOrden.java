package examen2.backend.logic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DetalleOrden {
    @Id @GeneratedValue
    private Integer id;

    @ManyToOne
    private Platillo platillo;

    private int cantidad;
    private String tamano; // "Regular" o "Grande"
    private int subtotal;

    public DetalleOrden() {}

    public DetalleOrden(Platillo platillo, int cantidad, String tamano, int subtotal) {
        this.platillo = platillo;
        this.cantidad = cantidad;
        this.tamano = tamano;
        this.subtotal = subtotal;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Platillo getPlatillo() { return platillo; }
    public void setPlatillo(Platillo platillo) { this.platillo = platillo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }

    public int getSubtotal() { return subtotal; }
    public void setSubtotal(int subtotal) { this.subtotal = subtotal; }
}
