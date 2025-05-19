package humanizar.alquilerprendas.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Andres Santos
 */
@Entity
@Getter
@Setter
@Table(name = "servicioalquiler")
public class ServicioAlquiler implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_servicio")
    private Integer idServicio;

    @Column(name = "fecha_devolucion")
    private Date fechaDevolucion;

    @Column(name = "fecha_retiro")
    private Date fechaRetiro;

    @Column(name = "fecha_solicitud")
    private Date fechaSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

}
