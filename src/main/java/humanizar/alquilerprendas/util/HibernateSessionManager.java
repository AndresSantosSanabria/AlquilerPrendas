/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanizar.alquilerprendas.util;

import org.hibernate.Session;

/**
 *
 * @author Andres Santos
 */
public class HibernateSessionManager {

    public static Session abrirSesion() {
        System.out.println("Abriendo conexión...");
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("Conexión establecida");
        return session;
    }

    public static void cerrarSesion(Session session) {
        if (session != null) {
            session.close();
            System.out.println("Conexión cerrada con éxito");
        }
    }
}
