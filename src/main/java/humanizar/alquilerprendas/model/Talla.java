
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
@Table(name = "talla")
public class Talla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_talla")
    private Integer id;

    @Column(name = "talla_prenda", length = 10)
    private String tallaPrenda;

    
}

