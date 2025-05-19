package humanizar.alquilerprendas.facade;

import humanizar.alquilerprendas.dao.PersonaDAO;
import humanizar.alquilerprendas.dto.*;
import lombok.Getter;
import lombok.Setter;

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
}
