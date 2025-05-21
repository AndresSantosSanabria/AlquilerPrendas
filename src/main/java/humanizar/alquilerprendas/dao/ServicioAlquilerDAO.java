package humanizar.alquilerprendas.dao;

import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.model.ServicioAlquiler;
import humanizar.alquilerprendas.model.ServicioLavanderia;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import java.util.Date;
import org.hibernate.Session;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Andres Santos
 */
public class ServicioAlquilerDAO {

    /**
     * Busca servicios de alquiler con fecha de devolución menor o igual a la
     * especificada
     *
     * @param fecha Fecha límite para la búsqueda
     * @return Lista de servicios de alquiler
     */
    public List<ServicioAlquiler> buscarPorFechaDevolucionMenorOIgual(Date fecha) {
        Session session = HibernateSessionManager.abrirSesion();
        try {
            Query<ServicioAlquiler> query = session.createQuery(
                    "FROM ServicioAlquiler WHERE fechaDevolucion <= :fecha",
                    ServicioAlquiler.class
            );
            query.setParameter("fecha", fecha);
            return query.getResultList();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }

    /**
     * Elimina un servicio de alquiler de la base de datos
     *
     * @param servicioAlquiler Servicio a eliminar
     */
    public void eliminar(ServicioAlquiler servicioAlquiler) {
        Session session = HibernateSessionManager.abrirSesion();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.remove(servicioAlquiler);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }


}
