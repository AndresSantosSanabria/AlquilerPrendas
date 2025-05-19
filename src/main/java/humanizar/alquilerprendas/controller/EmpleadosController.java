/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanizar.alquilerprendas.controller;

import humanizar.alquilerprendas.dao.PrendaDao;
import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.dto.PrendaDTO;
import humanizar.alquilerprendas.model.Persona;
import humanizar.alquilerprendas.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Andres Santos
 */
public class EmpleadosController {

    public List<Object[]> obtenerNombreYCargo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> resultados = null;

        try {
            resultados = session.createQuery(
                    "SELECT e.nombre, e.cedula, e.cargo FROM humanizar.alquilerprendas.model.Empleado e", Object[].class)
                    .getResultList();
        } finally {
            session.close();
        }

        return resultados;
    }

}
