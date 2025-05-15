
package humanizar.alquilerprendas.model;

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
@Table(name = "usuario_rol")
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Role rol;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
}