
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
@Table(name = "genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Integer id;

    @Column(name = "genero_prenda", length = 20)
    private String generoPrenda;

    // Getters y Setters
    public Integer getIdGenero() { return id; }
    public void setIdGenero(Integer idGenero) { this.id = idGenero; }

    public String getGeneroPrenda() { return generoPrenda; }
    public void setGeneroPrenda(String generoPrenda) { this.generoPrenda = generoPrenda; }
}

