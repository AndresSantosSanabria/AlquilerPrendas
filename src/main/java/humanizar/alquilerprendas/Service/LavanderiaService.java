package humanizar.alquilerprendas.Service;

import humanizar.alquilerprendas.dao.AlquilerPrendaDAO;
import humanizar.alquilerprendas.dao.ServicioAlquilerDAO;
import humanizar.alquilerprendas.dao.ServicioLavanderiaDAO;
import humanizar.alquilerprendas.model.AlquilerPrenda;
import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.model.ServicioAlquiler;
import humanizar.alquilerprendas.model.ServicioLavanderia;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import javax.transaction.Transactional;

/**
 *
 * @author Andres Santos
 */
@Transactional
public class LavanderiaService {

    private final ServicioAlquilerDAO servicioAlquilerDAO;
    private final AlquilerPrendaDAO alquilerPrendaDAO;
    private final ServicioLavanderiaDAO servicioLavanderiaDAO;

    public LavanderiaService() {
        // Inicializar los DAOs
        this.servicioAlquilerDAO = new ServicioAlquilerDAO();
        this.alquilerPrendaDAO = new AlquilerPrendaDAO();
        this.servicioLavanderiaDAO = new ServicioLavanderiaDAO();
    }

    /**
     * Procesa las prendas para envío a lavandería (fecha de devolución es hoy o
     * anterior) Este método debe ser llamado cuando se inicia la aplicación
     */
    public void procesarPrendasParaLavanderia() {
        Date fechaActual = eliminarTiempoDeFecha(new Date());

        // Buscar todos los servicios de alquiler donde la fecha de devolución es hoy o anterior
        List<ServicioAlquiler> serviciosVencidos = servicioAlquilerDAO.buscarPorFechaDevolucionMenorOIgual(fechaActual);

        List<AlquilerPrenda> alquileresProcesados = new ArrayList<>();
        List<Prenda> prendasParaLavanderia = new ArrayList<>();

        // Procesar cada servicio de alquiler vencido
        for (ServicioAlquiler servicio : serviciosVencidos) {
            // Buscar todas las prendas alquiladas para este servicio
            List<AlquilerPrenda> alquileresPrenda = alquilerPrendaDAO.buscarPorServicioAlquiler(servicio);

            for (AlquilerPrenda alquiler : alquileresPrenda) {
                Prenda prenda = alquiler.getPrenda();

                // Actualizar estado de la prenda
                prenda.setAlquilada(false);
                prenda.setParaLavanderia(true);
                alquilerPrendaDAO.actualizarPrenda(prenda);

                // Añadir a servicio de lavandería
                ServicioLavanderia servicioLavanderia = new ServicioLavanderia(prenda, false);

                // Establecer fecha de envío (hoy)
                servicioLavanderia.setFechaEnvio(new Date());

                // Establecer fecha de devolución (2 días después)
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, 2);
                servicioLavanderia.setFechaDevolucion(calendar.getTime());

                servicioLavanderiaDAO.guardar(servicioLavanderia);

                // Añadir a listas para seguimiento
                prendasParaLavanderia.add(prenda);
                alquileresProcesados.add(alquiler);
            }
        }

        // Eliminar registros de AlquilerPrenda
        for (AlquilerPrenda alquiler : alquileresProcesados) {
            alquilerPrendaDAO.eliminar(alquiler);
        }

        // Eliminar registros de ServicioAlquiler
        for (ServicioAlquiler servicio : serviciosVencidos) {
            servicioAlquilerDAO.eliminar(servicio);
        }

        System.out.println("Procesadas " + prendasParaLavanderia.size() + " prendas para lavandería");
    }

    /**
     * Elimina el componente de tiempo de una fecha para comparar solo fechas
     * (no tiempo)
     */
    private Date eliminarTiempoDeFecha(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);
        return calendario.getTime();
    }
    
    
   public void finLavanderia(){
        servicioLavanderiaDAO.procesarLavanderiaFinalizada();
   }
}
