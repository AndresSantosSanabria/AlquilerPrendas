package humanizar.alquilerprendas.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase base para Cliente y Empleado
 */
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "cedula", unique = true, nullable = false)
    private String cedula;

    @Column(name = "contrasenia_hash", length = 255, nullable = false)
    private String contraseniaHash;

    // Constructor vacío requerido por JPA
    public Persona() {}

    // Constructor opcional
    public Persona(String nombre, String telefono, String email, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
