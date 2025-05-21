package humanizar.alquilerprendas.facade;

import humanizar.alquilerprendas.dao.ClienteDAO;
import humanizar.alquilerprendas.dao.EmpleadoDAO;
import humanizar.alquilerprendas.dao.PrendaDao;
import humanizar.alquilerprendas.dto.AlquilerDTO;
import humanizar.alquilerprendas.model.AlquilerPrenda;
import humanizar.alquilerprendas.model.Cliente;
import humanizar.alquilerprendas.model.Empleado;
import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.model.ServicioAlquiler;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Andres Santos
 */
public class AlquilerServiceFacade {

    private PrendaDao alquilerPrendaDAO = new PrendaDao();

    public boolean registrarAlquilerCompleto(AlquilerDTO dto) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateSessionManager.abrirSesion();
            tx = session.beginTransaction();

            Cliente cliente = obtenerCliente(session, dto.getIdCliente());
            Empleado empleado = obtenerEmpleado(session, dto.getCedulaEmpleado());
            ServicioAlquiler alquiler = registrarServicioAlquiler(session, dto, cliente, empleado);
            registrarPrendasAlquiler(session, dto.getIdsPrendas(), alquiler);

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }

    private Cliente obtenerCliente(Session session, Integer idCliente) {
        ClienteDAO clienteDAO = new ClienteDAO(session);
        Cliente cliente = clienteDAO.obtenerClientePorId(idCliente);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado con ID: " + idCliente);
        }
        return cliente;
    }

    private Empleado obtenerEmpleado(Session session, String cedulaEmpleado) {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO(session);
        Empleado empleado = empleadoDAO.obtenerEmpleadoPorCedula(cedulaEmpleado);
        if (empleado == null) {
            throw new RuntimeException("Empleado no encontrado con cédula: " + cedulaEmpleado);
        }
        return empleado;
    }

    private ServicioAlquiler registrarServicioAlquiler(Session session, AlquilerDTO dto, Cliente cliente, Empleado empleado) {
        ServicioAlquiler alquiler = new ServicioAlquiler();
        alquiler.setIdCliente(cliente.getIdPersona());
        alquiler.setIdEmpleado(empleado.getIdPersona());
        alquiler.setFechaSolicitud(dto.getFechaAlquiler());
        alquiler.setFechaDevolucion(dto.getFechaDevolucion());
        alquiler.setFechaRetiro(dto.getFechaAlquiler());

        Integer id = (Integer) session.save(alquiler);
        alquiler.setIdServicio(id); // Aseguramos que el objeto tenga el ID generado
        session.flush();
        System.out.println("ID del alquiler es: " + id);

        return alquiler;
    }

    private void registrarPrendasAlquiler(Session session, List<Integer> idsPrendas, ServicioAlquiler alquiler) {
        if (idsPrendas == null || idsPrendas.isEmpty()) {
            throw new RuntimeException("Debe incluir al menos una prenda para alquilar.");
        }

        PrendaDao prendaDao = new PrendaDao(session);
        for (Integer idPrenda : idsPrendas) {
            Prenda prendaObtenida = prendaDao.obtenerPrendaPorId(idPrenda.longValue());

            if (prendaObtenida == null) {
                throw new RuntimeException("Prenda no encontrada con ID: " + idPrenda);
            }

            prendaObtenida.setAlquilada(Boolean.TRUE);
            session.update(prendaObtenida);
            AlquilerPrenda alquilerPrenda = new AlquilerPrenda();
            alquilerPrenda.setServicioAlquiler(alquiler);
            alquilerPrenda.setPrenda(prendaObtenida);

            // Para depurar
            if (alquiler.getIdServicio() == null) {
                System.out.println("ERROR: El ID del servicio de alquiler es nulo");
            } else {
                System.out.println("Guardando AlquilerPrenda con ID de servicio: " + alquiler.getIdServicio());

            }

            session.save(alquilerPrenda);
        }
        JOptionPane.showMessageDialog(null, "Se a generado el alquiler de manera exitosa \n número del servicio = " + alquiler.getIdServicio(), "Muchas gracias", JOptionPane.WARNING_MESSAGE);
    }

    public AlquilerPrenda buscarAlquilerPorNumero(Integer idServicio) {
        return alquilerPrendaDAO.buscarAlquilerPorId(idServicio);
    }

    public List<AlquilerPrenda> buscarAlquileresPorServicio(Integer idServicio) {
        return alquilerPrendaDAO.buscarAlquileresPorServicioId(idServicio);
    }

    public boolean existeAlquiler(Integer idServicio) {
        AlquilerPrenda alquiler = buscarAlquilerPorNumero(idServicio);
        return alquiler != null;
    }

    public List<AlquilerPrenda> buscarAlquileresPorFecha(Date fecha) {
        return alquilerPrendaDAO.buscarAlquileresPorFecha(fecha);
    }
}
