package humanizar.alquilerprendas.facade;

import humanizar.alquilerprendas.model.AlquilerPrenda;
import humanizar.alquilerprendas.model.Cliente;
import humanizar.alquilerprendas.model.Empleado;
import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.model.ServicioAlquiler;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import java.util.Date;
import java.util.List;
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
public class AlquilerServiceFacade {

public Integer registrarAlquilerCompleto(
        int idCliente,
        String cedulaEmpleado,
        List<Integer> idsPrendas,
        Date fechaAlquiler,
        Date fechaDevolucion) {

    Session session = HibernateSessionManager.abrirSesion();
    Transaction tx = null;

    try {
        tx = session.beginTransaction();
        System.out.println("✅ Transacción iniciada");

        // Obtener cliente
        System.out.println("🔎 Buscando cliente con id: " + idCliente);
        Cliente cliente = (Cliente) session.createQuery("FROM Cliente c WHERE c.id = :id")
                .setParameter("id", idCliente)
                .uniqueResult();

        if (cliente == null) {
            System.out.println("❌ Cliente no encontrado.");
            throw new RuntimeException("Cliente no encontrado.");
        }
        System.out.println("✅ Cliente encontrado: " + cliente.getIdPersona());

        // Obtener empleado
        System.out.println("🔎 Buscando empleado con cédula: " + cedulaEmpleado);
        Empleado empleado = (Empleado) session.createQuery("FROM Empleado e WHERE e.persona.cedula = :cedula")
                .setParameter("cedula", cedulaEmpleado)
                .uniqueResult();

        if (empleado == null) {
            System.out.println("❌ Empleado no encontrado.");
            throw new RuntimeException("Empleado no encontrado.");
        }
        System.out.println("✅ Empleado encontrado: ID = " + empleado.getIdPersona());

        // Crear y persistir servicio alquiler
        ServicioAlquiler servicio = new ServicioAlquiler();
        servicio.setCliente(cliente);
        servicio.setEmpleado(empleado);
        servicio.setFechaSolicitud(new Date());
        servicio.setFechaRetiro(fechaAlquiler);
        servicio.setFechaDevolucion(fechaDevolucion);

        System.out.println("📝 Persistiendo servicio de alquiler...");
        session.persist(servicio);
        session.flush(); // Forzar la ejecución para generar idServicio
        System.out.println("✅ Servicio persistido con ID: " + servicio.getIdServicio());

        // Asociar prendas al alquiler
        for (Integer idPrenda : idsPrendas) {
            System.out.println("🔎 Buscando prenda con ID: " + idPrenda);
            Prenda prenda = session.get(Prenda.class, idPrenda.longValue());

            if (prenda != null) {
                AlquilerPrenda alquilerPrenda = new AlquilerPrenda(servicio, prenda);
                System.out.println("📝 Asociando prenda al servicio...");
                session.persist(alquilerPrenda);
                System.out.println("✅ Prenda asociada: " + prenda.getIdPrenda());
            } else {
                System.out.println("⚠️ Prenda no encontrada con ID: " + idPrenda);
            }
        }

        tx.commit();
        System.out.println("✅ Transacción confirmada exitosamente.");
        return servicio.getIdServicio();

    } catch (Exception e) {
        if (tx != null) tx.rollback();
        System.out.println("❌ Error durante la transacción: " + e.getMessage());
        e.printStackTrace();
        return null;

    } finally {
        HibernateSessionManager.cerrarSesion(session);
        System.out.println("🔒 Sesión cerrada.");
    }
}

}
