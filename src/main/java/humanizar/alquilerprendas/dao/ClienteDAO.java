package humanizar.alquilerprendas.dao;

import humanizar.alquilerprendas.model.Cliente;
import org.hibernate.Session;

/**
 *
 * @author Andres Santos
 */
public class ClienteDAO {

    private Session session;

    public ClienteDAO(Session session) {
        this.session = session;
    }

    public ClienteDAO() {

    }

    public Cliente obtenerClientePorId(int id) {
        return session.get(Cliente.class, id);
    }
}
