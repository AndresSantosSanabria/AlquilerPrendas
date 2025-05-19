package humanizar.alquilerprendas.model;

import javax.persistence.*;
/**
 *
 * @author Andres Santos
 */
@Entity
@Table(name = "alquilerprenda")
public class AlquilerPrenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @MapsId("idServicio")
    @JoinColumn(name = "id_servicio")
    private ServicioAlquiler servicioAlquiler;

    @ManyToOne
    @MapsId("idPrenda")
    @JoinColumn(name = "id_prenda")
    private Prenda prenda;

    // constructores, getters y setters
    public AlquilerPrenda() {
    }

    public AlquilerPrenda(ServicioAlquiler servicioAlquiler, Prenda prenda) {
        this.servicioAlquiler = servicioAlquiler;
        this.prenda = prenda;
    }
}
