package humanizar.alquilerprendas.facade;

import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Andres Santos
 */
@Getter
@Setter
public class PrendaFacade {

    public Prenda guardarPrenda(Prenda prenda) {
        Session session = null;
        Transaction transaction = null;
        session = HibernateSessionManager.abrirSesion();
        if (prenda.getAlquilada() == null) {
            prenda.setAlquilada(false);
        }

        if (prenda.getParaLavanderia() == null) {
            prenda.setParaLavanderia(false);
        }

        if (prenda.getFechaIngreso() == null) {
            prenda.setFechaIngreso(new Date());
        }
        session.save(prenda);
        transaction = session.beginTransaction();
        transaction.commit();
        return prenda;
    }

}
