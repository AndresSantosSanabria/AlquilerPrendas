package humanizar.alquilerprendas.dto;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Andres Santos
 */
@Getter
@Setter
public class AlquilerDTO {
    private int idCliente;
    private String cedulaEmpleado;
    private List<Integer> idsPrendas;
    private Date fechaAlquiler;
    private Date fechaDevolucion;

    // Constructor
    public AlquilerDTO(int idCliente, String cedulaEmpleado, List<Integer> idsPrendas, Date fechaAlquiler, Date fechaDevolucion) {
        this.idCliente = idCliente;
        this.cedulaEmpleado = cedulaEmpleado;
        this.idsPrendas = idsPrendas;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
    }


}