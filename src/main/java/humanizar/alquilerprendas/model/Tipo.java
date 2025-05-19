
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
@Getter
@Setter
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer id;

    @Column(name = "tipo_prenda", length = 50)
    private String tipoPrenda;

    // Getters y Setters
    public Integer getIdTipo() { return id; }
    public void setIdTipo(Integer idTipo) { this.id = idTipo; }

    public String getTipoPrenda() { return tipoPrenda; }
    public void setTipoPrenda(String tipoPrenda) { this.tipoPrenda = tipoPrenda; }
}
