package humanizar.alquilerprendas.model;


import java.io.Serializable;
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
@Table(name = "roles")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer id;

    @Column(name = "nombre_rol", length = 50, nullable = false)
    private String nombreRol;
}
