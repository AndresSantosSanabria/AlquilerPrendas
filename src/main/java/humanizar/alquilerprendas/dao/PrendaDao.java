package humanizar.alquilerprendas.dao;

import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.dto.PrendaDTO;
import humanizar.alquilerprendas.util.HibernateUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Andres Santos
 */
public class PrendaDao {

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
                    + "CASE WHEN p.pedreria = true THEN 'Sí' ELSE 'No' END, "
                    + "p.precio, "
                    + "talla.tallaPrenda) "
                    + "FROM Prenda p "
                    + "LEFT JOIN p.categoria cat "
                    + "LEFT JOIN p.genero gen "
                    + "LEFT JOIN p.material mat "
                    + "LEFT JOIN p.tipo tipo "
                    + "LEFT JOIN p.talla talla "
                    + "WHERE 1=1 "
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

            for (Map.Entry<String, Object> entry : parametros.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }

            resultado = query.getResultList();

        } finally {
            session.close();
        }

        return resultado;
    }

}
