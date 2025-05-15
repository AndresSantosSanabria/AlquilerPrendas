/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package humanizar.alquilerprendas;

import humanizar.alquilerprendas.view.Login;
import javax.swing.SwingUtilities;

/**
 *
 * @author Andres Santos
 */
public class AlquilerPrendas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
        /*Session session = null;
        Transaction tx = null;

        try {
            session = HibernateSessionManager.abrirSesion();
            tx = session.beginTransaction();

            List<Cliente> clientes = session.createQuery("FROM Cliente", Cliente.class).list();

            // Mostrar la lista de clientes
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionManager.cerrarSesion(session);

        }*/
        
        
    }
        
}
