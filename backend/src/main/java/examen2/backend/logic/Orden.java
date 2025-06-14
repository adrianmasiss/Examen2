package examen2.backend.logic;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Orden {
    @Id @GeneratedValue
    private Integer id;

    @ManyToOne
    private User usuario;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DetalleOrden> detalles;

    private int total;

    public Orden() {}

    public Orden(User usuario, List<DetalleOrden> detalles, int total) {
        this.usuario = usuario;
        this.detalles = detalles;
        this.total = total;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUsuario() { return usuario; }
    public void setUsuario(User usuario) { this.usuario = usuario; }

    public List<DetalleOrden> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleOrden> detalles) { this.detalles = detalles; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
}
