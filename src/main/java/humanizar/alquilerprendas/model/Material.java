
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
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Integer id;

    @Column(name = "material_prenda", length = 50)
    private String materialPrenda;

    // Getters y Setters
    public Integer getIdMaterial() { return id; }
    public void setIdMaterial(Integer idMaterial) { this.id = idMaterial; }

    public String getMaterialPrenda() { return materialPrenda; }
    public void setMaterialPrenda(String materialPrenda) { this.materialPrenda = materialPrenda; }
}
