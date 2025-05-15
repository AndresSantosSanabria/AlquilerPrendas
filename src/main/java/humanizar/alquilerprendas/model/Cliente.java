
package humanizar.alquilerprendas.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author Andres Santos
 */
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id_persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Persona {

    @Column(name = "estado_cliente")
    private Boolean estadoCliente;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
}