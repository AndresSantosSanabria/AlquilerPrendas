package humanizar.alquilerprendas.Service;

import humanizar.alquilerprendas.model.Categoria;
import humanizar.alquilerprendas.model.Genero;
import humanizar.alquilerprendas.model.Material;
import humanizar.alquilerprendas.model.Talla;
import humanizar.alquilerprendas.model.Tipo;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

/**
 *
 * @author Andres Santos
 */
public class EntityService {

@PersistenceContext
    Session session = HibernateSessionManager.abrirSesion();
    
    /**
     * Busca un objeto Tipo por su nombre
     */
    public Tipo buscarTipoPorNombre(String nombre) {
        try {
            TypedQuery<Tipo> query = session.createQuery(
                "SELECT t FROM Tipo t WHERE t.tipoPrenda = :nombre", Tipo.class);
            query.setParameter("nombre", nombre);
            List<Tipo> resultados = query.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Busca un objeto Talla por su nombre
     */
    public Talla buscarTallaPorNombre(String nombre) {
        try {
            TypedQuery<Talla> query = session.createQuery(
                "SELECT t FROM Talla t WHERE t.tallaPrenda = :nombre", Talla.class);
            query.setParameter("nombre", nombre);
            List<Talla> resultados = query.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Busca un objeto Material por su nombre
     */
    public Material buscarMaterialPorNombre(String nombre) {
        try {
            TypedQuery<Material> query = session.createQuery(
                "SELECT m FROM Material m WHERE m.materialPrenda = :nombre", Material.class);
            query.setParameter("nombre", nombre);
            List<Material> resultados = query.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Busca un objeto Genero por su nombre
     */
    public Genero buscarGeneroPorNombre(String nombre) {
        try {
            TypedQuery<Genero> query = session.createQuery(
                "SELECT g FROM Genero g WHERE g.generoPrenda = :nombre", Genero.class);
            query.setParameter("nombre", nombre);
            List<Genero> resultados = query.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Busca un objeto Categoria por su nombre
     */
    public Categoria buscarCategoriaPorNombre(String nombre) {
        try {
            TypedQuery<Categoria> query = session.createQuery(
                "SELECT c FROM Categoria c WHERE c.categoriaPrenda = :nombre", Categoria.class);
            query.setParameter("nombre", nombre);
            List<Categoria> resultados = query.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
