package humanizar.alquilerprendas.facade;

import humanizar.alquilerprendas.dao.PersonaDAO;
import humanizar.alquilerprendas.dto.*;
import humanizar.alquilerprendas.model.Role;
import humanizar.alquilerprendas.util.HibernateUtil;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Andres Santos
 */
@Getter
@Setter
public class UsuarioFacade {

    private final PersonaDAO personaDAO;

    public UsuarioFacade() {
        this.personaDAO = new PersonaDAO();
    }

    public String obtenerCedulaUsuario(int idUsuario) {
        return personaDAO.obtenerCedulaPorId(idUsuario);
    }

    public RolDTO obtenerRolUsuario(int idPersona) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    RolDTO rolDTO = null;
    
    try {
        // Consulta HQL corregida para obtener directamente la entidad Role
        String hql = "SELECT ur.rol FROM UsuarioRol ur WHERE ur.persona.idPersona = :idPersona";
        
        Query<Role> query = session.createQuery(hql, Role.class);
        query.setParameter("idPersona", idPersona);
        
        List<Role> roles = query.list();
        
        if (roles != null && !roles.isEmpty()) {
            Role rol = roles.get(0);
            
            rolDTO = new RolDTO();
            
            rolDTO.setIdRol(rol.getId()); 
            rolDTO.setNombreRol(rol.getNombreRol()); 
        }
    } catch (HibernateException e) {
        e.printStackTrace();
    } finally {
        session.close();
    }
    
    return rolDTO;
}
}
