package humanizar.alquilerprendas.dao;

import humanizar.alquilerprendas.model.AlquilerPrenda;
import humanizar.alquilerprendas.model.Persona;
import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.model.ServicioAlquiler;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Andres Santos
 */
public class AlquilerPrendaDAO {
    
    /**
     * Busca alquileres por servicio de alquiler
     * @param servicioAlquiler Servicio a buscar
     * @return Lista de alquileres de prendas
     */
    public List<AlquilerPrenda> buscarPorServicioAlquiler(ServicioAlquiler servicio) {
    Session session = HibernateSessionManager.abrirSesion();
    List<AlquilerPrenda> resultado = session.createQuery(
        "SELECT a FROM AlquilerPrenda a JOIN FETCH a.prenda WHERE a.servicioAlquiler = :servicio", AlquilerPrenda.class)
        .setParameter("servicio", servicio)
        .getResultList();
    session.close();
    return resultado;
}
    
    /**
     * Busca alquileres por prenda
     * @param prenda Prenda a buscar
     * @return Lista de alquileres de prendas
     */
    public List<AlquilerPrenda> buscarPorPrenda(Prenda prenda) {
        Session session = HibernateSessionManager.abrirSesion();
        try {
            Query<AlquilerPrenda> query = session.createQuery(
                "FROM AlquilerPrenda WHERE prenda = :prenda", 
                AlquilerPrenda.class
            );
            query.setParameter("prenda", prenda);
            return query.getResultList();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }
    
    /**
     * Elimina un alquiler de prenda de la base de datos
     * @param alquilerPrenda Alquiler a eliminar
     */
    public void eliminar(AlquilerPrenda alquilerPrenda) {
        Session session = HibernateSessionManager.abrirSesion();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.remove(alquilerPrenda);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }
    
    /**
     * Actualiza el estado de una prenda en la base de datos
     * @param prenda Prenda a actualizar
     */
    public void actualizarPrenda(Prenda prenda) {
        Session session = HibernateSessionManager.abrirSesion();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.merge(prenda);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }

}
