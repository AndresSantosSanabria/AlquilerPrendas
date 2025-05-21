package humanizar.alquilerprendas.dao;

import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import org.hibernate.Session;
import humanizar.alquilerprendas.model.ServicioLavanderia;
import java.util.Date;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 *
 * @author Andres Santos
 */
public class ServicioLavanderiaDAO {

     /**
     * Guarda un servicio de lavandería en la base de datos
     * @param servicioLavanderia Servicio a guardar
     * @return El servicio guardado
     */
    public ServicioLavanderia guardar(ServicioLavanderia servicioLavanderia) {
        Session session = HibernateSessionManager.abrirSesion();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.persist(servicioLavanderia);
            tx.commit();
            return servicioLavanderia;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }
    
    /**
     * Busca servicios de lavandería por prenda
     * @param prenda Prenda a buscar
     * @return Lista de servicios de lavandería
     */
    public List<ServicioLavanderia> buscarPorPrenda(Prenda prenda) {
        Session session = HibernateSessionManager.abrirSesion();
        try {
            Query<ServicioLavanderia> query = session.createQuery(
                "FROM ServicioLavanderia WHERE prenda = :prenda", 
                ServicioLavanderia.class
            );
            query.setParameter("prenda", prenda);
            return query.getResultList();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }
    
    
     public void procesarLavanderiaFinalizada() {
    Session session = HibernateSessionManager.abrirSesion();
    Transaction tx = null;

    try {
        tx = session.beginTransaction();

        Date hoy = new Date();

        // 1. Buscar todos los servicios de lavandería con fechaDevolucion <= hoy
        List<ServicioLavanderia> serviciosFinalizados = session.createQuery(
                "FROM ServicioLavanderia s WHERE s.fechaDevolucion <= :hoy", ServicioLavanderia.class)
                .setParameter("hoy", hoy)
                .getResultList();

        // 2. Procesar cada uno
        for (ServicioLavanderia servicio : serviciosFinalizados) {
            Prenda prenda = servicio.getPrenda();

            // Actualizar estado de la prenda
            prenda.setParaLavanderia(false);
            session.update(prenda); // Persistir el cambio en la prenda

            // Eliminar el registro de lavandería
            session.delete(servicio);
        }

        tx.commit();
        System.out.println("Lavandería procesada correctamente.");

    } catch (Exception e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        HibernateSessionManager.cerrarSesion(session);
    }
}
}
