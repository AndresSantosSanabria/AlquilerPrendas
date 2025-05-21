
package humanizar.alquilerprendas.model;

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
@Table(name = "prenda")
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenda")
    private Long idPrenda;

    @ManyToOne
    @JoinColumn(name = "tipo_prenda", referencedColumnName = "id_tipo")
    private Tipo tipo;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "talla", referencedColumnName = "id_talla")
    private Talla talla;

    @Column(name = "color", length = 30)
    private String color;

    @ManyToOne
    @JoinColumn(name = "id_material", referencedColumnName = "id_material")
    private Material material;

    @Column(name = "precio")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;

    @Column(name = "pedreria")
    private Boolean pedreria;
    
    @Column (name = "esta_alquilada")
    private Boolean alquilada;

    @ManyToOne
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    private Genero genero;

    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @Column(name = "para_lavanderia")
    private Boolean paraLavanderia;
}