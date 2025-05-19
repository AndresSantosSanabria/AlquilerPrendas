/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanizar.alquilerprendas.model;

import lombok.*;
import javax.persistence.*;

/**
 *
 * @author Andres Santos
 */
@Entity
@Table(name = "empleado")
@PrimaryKeyJoinColumn(name = "id_persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado extends Persona {

    @Column(name = "salario")
    private Double salario;

    @Column(name = "cargo", length = 50)
    private String cargo;

    @ManyToOne
    private Persona persona;
}
