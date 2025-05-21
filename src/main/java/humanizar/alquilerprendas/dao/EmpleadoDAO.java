package humanizar.alquilerprendas.dao;

import humanizar.alquilerprendas.model.Empleado;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Andres Santos
 */
public class EmpleadoDAO {

    private Session session;

    public EmpleadoDAO(Session session) {
        this.session = session;
    }
    public EmpleadoDAO() {
        
    }

    public Empleado obtenerEmpleadoPorCedula(String cedula) {
        String hql = "FROM Empleado e WHERE e.cedula = :cedula";
        Query<Empleado> query = session.createQuery(hql, Empleado.class);
        query.setParameter("cedula", cedula);
        return query.uniqueResult(); // Devuelve null si no encuentra ninguno
    }
}