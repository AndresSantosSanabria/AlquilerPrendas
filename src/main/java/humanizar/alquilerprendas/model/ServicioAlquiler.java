package humanizar.alquilerprendas.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Andres Santos
 */
@Entity
@Table(name = "servicioalquiler")
@Getter
@Setter
public class ServicioAlquiler {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;
    
    @Column(name = "id_cliente")
    private Integer idCliente;
    
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    
    @Column(name = "fecha_solicitud")
    private Date fechaSolicitud;
    
    @Column(name = "fecha_retiro")
    private Date fechaRetiro;
    
    @Column(name = "fecha_devolucion")
    private Date fechaDevolucion;

    // Constructor vacío
    public ServicioAlquiler() {
    }
}
