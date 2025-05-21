package humanizar.alquilerprendas.dao;

import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.dto.PrendaDTO;
import humanizar.alquilerprendas.model.AlquilerPrenda;
import humanizar.alquilerprendas.model.ServicioAlquiler;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import humanizar.alquilerprendas.util.HibernateUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Andres Santos
 */
public class PrendaDao {

    private Session session;

    public PrendaDao(Session session) {
        this.session = session;
    }

    public PrendaDao() {

    }

    public List<Prenda> obtenerTodasLasPrendasConRelaciones() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Prenda> prendas = null;

        try {
            prendas = session.createQuery("SELECT p FROM Prenda p "
                    + "JOIN FETCH p.categoria "
                    + "JOIN FETCH p.genero "
                    + "JOIN FETCH p.material "
                    + "JOIN FETCH p.tipo "
                    + "JOIN FETCH p.talla", Prenda.class)
                    .getResultList();
        } finally {
            session.close();
        }

        return prendas;
    }

    public List<PrendaDTO> obtenerResumenPrendasConFiltros(
            String categoria,
            String genero,
            String material,
            String tipo,
            String talla
    ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PrendaDTO> resultado = null;
        try {
            StringBuilder hql = new StringBuilder(
                    "SELECT new humanizar.alquilerprendas.dto.PrendaDTO("
                    + "p.idPrenda, "
                    + "p.color, "
                    + "p.descripcion, "
                    + "cat.categoriaPrenda, "
                    + "gen.generoPrenda, "
                    + "mat.materialPrenda, "
                    + "tipo.tipoPrenda, "
                    + "p.pedreria, "
                    + "p.precio, "
                    + "talla.tallaPrenda) "
                    + "FROM Prenda p "
                    + "LEFT JOIN p.categoria cat "
                    + "LEFT JOIN p.genero gen "
                    + "LEFT JOIN p.material mat "
                    + "LEFT JOIN p.tipo tipo "
                    + "LEFT JOIN p.talla talla "
                    + "WHERE p.alquilada = false and p.paraLavanderia = false"
            );

            Map<String, Object> parametros = new HashMap<>();
            if (categoria != null && !categoria.isEmpty()) {
                hql.append("AND cat.categoriaPrenda = :categoria ");
                parametros.put("categoria", categoria);
            }
            if (genero != null && !genero.isEmpty()) {
                hql.append("AND gen.generoPrenda = :genero ");
                parametros.put("genero", genero);
            }
            if (material != null && !material.isEmpty()) {
                hql.append("AND mat.materialPrenda = :material ");
                parametros.put("material", material);
            }
            if (tipo != null && !tipo.isEmpty()) {
                hql.append("AND tipo.tipoPrenda = :tipo ");
                parametros.put("tipo", tipo);
            }
            if (talla != null && !talla.isEmpty()) {
                hql.append("AND talla.tallaPrenda = :talla ");
                parametros.put("talla", talla);
            }

            Query<PrendaDTO> query = session.createQuery(hql.toString(), PrendaDTO.class);

            // Establecer parámetros ANTES de ejecutar la consulta
            for (Map.Entry<String, Object> entry : parametros.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }

            // Ejecutar la consulta UNA SOLA VEZ después de establecer los parámetros
            resultado = query.getResultList();

            // Opcional: mantener logs para depuración
            for (PrendaDTO dto : resultado) {
                System.out.println("Prenda ID: " + dto.getId()
                        + ", Categoria: " + dto.getCategoria()
                        + ", Genero: " + dto.getGenero()
                        + ", Material: " + dto.getMaterial()
                        + ", Tipo: " + dto.getTipoPrenda());
            }
        } finally {
            session.close();
        }
        return resultado;
    }

    public Prenda obtenerPrendaPorId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Prenda prenda = null;
        try {
            prenda = session.get(Prenda.class, id);
        } finally {
            session.close();
        }
        return prenda;
    }

    // Método para buscar un alquiler por ID
    public AlquilerPrenda buscarAlquilerPorId(Integer idServicio) {
        Session session = HibernateSessionManager.abrirSesion();
        AlquilerPrenda alquilerPrenda = null;

        try {
            // Utilizamos HQL para hacer un join con todas las entidades necesarias
            String hql = "SELECT a FROM AlquilerPrenda a "
                    + "JOIN FETCH a.servicioAlquiler "
                    + "JOIN FETCH a.prenda "
                    + "WHERE a.servicioAlquiler.idServicio = :idServicio";

            Query<AlquilerPrenda> query = session.createQuery(hql, AlquilerPrenda.class);
            query.setParameter("idServicio", idServicio);

            alquilerPrenda = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }

        return alquilerPrenda;
    }

    // Método para buscar todos los alquileres relacionados con un servicio
    public List<AlquilerPrenda> buscarAlquileresPorServicioId(Integer idServicio) {
        Session session = HibernateSessionManager.abrirSesion();
        List<AlquilerPrenda> alquileres = null;

        try {
            String hql = "SELECT a FROM AlquilerPrenda a "
                    + "JOIN FETCH a.servicioAlquiler "
                    + "JOIN FETCH a.prenda "
                    + "WHERE a.servicioAlquiler.idServicio = :idServicio";

            Query<AlquilerPrenda> query = session.createQuery(hql, AlquilerPrenda.class);
            query.setParameter("idServicio", idServicio);

            alquileres = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }

        return alquileres;
    }

    public List<AlquilerPrenda> buscarAlquileresPorFecha(Date fecha) {
        Session session = HibernateSessionManager.abrirSesion();
        List<AlquilerPrenda> alquileres = null;
        try {
            String hql = "SELECT a FROM AlquilerPrenda a "
                    + "JOIN FETCH a.servicioAlquiler s "
                    + "JOIN FETCH a.prenda p "
                    + "WHERE DATE(s.fechaSolicitud) = :fecha";

            Query<AlquilerPrenda> query = session.createQuery(hql, AlquilerPrenda.class);
            query.setParameter("fecha", fecha);
            alquileres = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }

        return alquileres;
    }
/**
     * Busca alquileres por servicio de alquiler
     * @param servicioAlquiler Servicio a buscar
     * @return Lista de alquileres de prendas
     */
    public List<AlquilerPrenda> buscarPorServicioAlquiler(ServicioAlquiler servicioAlquiler) {
        Session session = HibernateSessionManager.abrirSesion();
        try {
            Query<AlquilerPrenda> query = session.createQuery(
                "FROM AlquilerPrenda WHERE servicioAlquiler = :servicioAlquiler", 
                AlquilerPrenda.class
            );
            query.setParameter("servicioAlquiler", servicioAlquiler);
            return query.getResultList();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }
    
    /**
     * Busca alquileres por prenda
     * @param prenda Prenda a buscar
     * @return Lista de alquileres de prendas
     */
    public List<AlquilerPrenda> buscarPorPrenda(Prenda prenda) {
        Session session = HibernateSessionManager.abrirSesion();
        try {
            Query<AlquilerPrenda> query = session.createQuery(
                "FROM AlquilerPrenda WHERE prenda = :prenda", 
                AlquilerPrenda.class
            );
            query.setParameter("prenda", prenda);
            return query.getResultList();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }
    
    /**
     * Elimina un alquiler de prenda de la base de datos
     * @param alquilerPrenda Alquiler a eliminar
     */
    public void eliminar(AlquilerPrenda alquilerPrenda) {
        Session session = HibernateSessionManager.abrirSesion();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.remove(alquilerPrenda);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }
    
    /**
     * Actualiza el estado de una prenda en la base de datos
     * @param prenda Prenda a actualizar
     */
    public void actualizarPrenda(Prenda prenda) {
        Session session = HibernateSessionManager.abrirSesion();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.merge(prenda);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
    }
}
