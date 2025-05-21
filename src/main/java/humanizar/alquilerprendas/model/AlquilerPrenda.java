package humanizar.alquilerprendas.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Andres Santos
 */
@Entity
@Table(name = "alquilerprenda")
@Getter
@Setter
public class AlquilerPrenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenda")
    private Prenda prenda;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio")
    private ServicioAlquiler servicioAlquiler;

    public AlquilerPrenda() {
    }

    public AlquilerPrenda(ServicioAlquiler servicio, Prenda prenda) {
        this.servicioAlquiler = servicio;
        this.prenda = prenda;
    }
}
