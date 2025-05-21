/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package humanizar.alquilerprendas;

import humanizar.alquilerprendas.Service.LavanderiaService;
import humanizar.alquilerprendas.view.Login;
import javax.swing.SwingUtilities;

/**
 *
 * @author Andres Santos
 */
public class AlquilerPrendas {

    public static void main(String[] args) {

        try {
            System.out.println("Iniciando aplicación de alquiler de prendas...");

            // Crear e inicializar el servicio de lavandería
            LavanderiaService lavanderiaService = new LavanderiaService();

            // Ejecutar el procesamiento de prendas para lavandería
            System.out.println("Iniciando procesamiento de prendas para lavandería...");
            lavanderiaService.procesarPrendasParaLavanderia();
            System.out.println("Procesamiento de prendas para lavandería completado.");
            
            
            lavanderiaService.finLavanderia();

            SwingUtilities.invokeLater(() -> {
                new Login().setVisible(true);
            });

        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
