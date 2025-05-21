package humanizar.alquilerprendas.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Andres Santos
 */
@Getter
@Setter
public class RolDTO {
    private int idRol;
    private String nombreRol;

    public RolDTO() {
    }

    public RolDTO(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }  

    @Override
    public String toString() {
        return "RolDTO{" +
                "idRol=" + idRol +
                ", nombreRol='" + nombreRol + '\'' +
                '}';
    }
}