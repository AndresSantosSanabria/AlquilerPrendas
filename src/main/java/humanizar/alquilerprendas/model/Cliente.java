
package humanizar.alquilerprendas.model;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Andres Santos
 */
@Entity
@Table(name = "Cliente")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Cliente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "estado_cliente")
    private Boolean estadoCliente;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    // Constructor
    public Cliente() {}

    // Getters y setters
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Boolean getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(Boolean estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}