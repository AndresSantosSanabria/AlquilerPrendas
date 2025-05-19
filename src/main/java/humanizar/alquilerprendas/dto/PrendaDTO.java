package humanizar.alquilerprendas.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Andres Santos
 */
@Getter
@Setter
public class PrendaDTO {

    private Long id;
    private String color;
    private String descripcion;
    private String categoria;
    private String genero;
    private String material;
    private String tipoPrenda;
    private String pedreria;
    private Double precio;
    private String talla;

    public PrendaDTO(Long id, String color, String descripcion, String categoria, String genero,
                     String material, String tipoPrenda, String pedreria, Double precio, String talla) {
        this.id = id;
        this.color = color;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.genero = genero;
        this.material = material;
        this.tipoPrenda = tipoPrenda;
        this.pedreria = pedreria;
        this.precio = precio;
        this.talla = talla;
    }
}