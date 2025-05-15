/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanizar.alquilerprendas.util;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author Andres Santos
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Dotenv dotenv = Dotenv.load();

                String url = dotenv.get("DB_URL");
                String user = dotenv.get("DB_USER");
                String password = dotenv.get("DB_PASSWORD");

                Properties settings = new Properties();
                settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.put("hibernate.connection.url", url);
                settings.put("hibernate.connection.username", user);
                settings.put("hibernate.connection.password", password);
                settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                settings.put("hibernate.show_sql", "true");
                settings.put("hibernate.format_sql", "true");
                settings.put("hibernate.hbm2ddl.auto", "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(humanizar.alquilerprendas.model.Persona.class);
                configuration.addAnnotatedClass(humanizar.alquilerprendas.model.Cliente.class);
                configuration.addAnnotatedClass(humanizar.alquilerprendas.model.Role.class);
                configuration.addAnnotatedClass(humanizar.alquilerprendas.model.UsuarioRol.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

