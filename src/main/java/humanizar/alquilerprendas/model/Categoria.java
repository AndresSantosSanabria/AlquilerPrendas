
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
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id;

    @Column(name = "categoria_prenda", length = 50)
    private String categoriaPrenda;

    // Getters y Setters
    public Integer getIdCategoria() { return id; }
    public void setIdCategoria(Integer idCategoria) { this.id = idCategoria; }

    public String getCategoriaPrenda() { return categoriaPrenda; }
    public void setCategoriaPrenda(String categoriaPrenda) { this.categoriaPrenda = categoriaPrenda; }
}
