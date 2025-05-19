package humanizar.alquilerprendas.dao;

import humanizar.alquilerprendas.model.Persona;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import org.hibernate.Session;

/**
 *
 * @author Andres Santos
 */
public class PersonaDAO {

     public String obtenerCedulaPorId(int idPersona) {
        Session session = null;
        String cedula = null;

        try {
            session = HibernateSessionManager.abrirSesion();
            Persona persona = session.get(Persona.class, idPersona);

            if (persona != null) {
                cedula = persona.getCedula();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
        return cedula;
    }
}
