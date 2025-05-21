/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanizar.alquilerprendas.controller;

import humanizar.alquilerprendas.dto.RolDTO;
import humanizar.alquilerprendas.facade.UsuarioFacade;
import humanizar.alquilerprendas.model.Cliente;
import humanizar.alquilerprendas.model.Empleado;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import humanizar.alquilerprendas.model.Persona;
import humanizar.alquilerprendas.model.Role;
import humanizar.alquilerprendas.model.UsuarioRol;
import humanizar.alquilerprendas.view.Login;
import humanizar.alquilerprendas.view.home;
import humanizar.alquilerprendas.view.homeEmpleadoAdmin;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Transaction;

/**
 *
 * @author Andres Santos
 */
public class LoginController {

    public int login(String correo, String contraseña) {
        System.out.println(correo);
        System.out.println(contraseña);
        Session session = null;
        int acceso = 0;
        try {
            session = HibernateSessionManager.abrirSesion();
            String hql = "FROM Persona WHERE email = :username AND contrasenia_hash = :password";
            Query<Persona> query = session.createQuery(hql, Persona.class);
            query.setParameter("username", correo);
            query.setParameter("password", contraseña);
            Persona usuario = query.uniqueResult();
            if (usuario != null) {
                acceso = usuario.getIdPersona();
                System.out.println("Acceso concedido, ID: " + acceso);
                System.out.println("Email: " + usuario.getEmail());
            } else {
                System.out.println("Credenciales inválidas.");
                acceso = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }
        return acceso;
    }

public boolean Register(String nombre, String direccion, String cedula, String telefono, String email,
                            String contrasenia, int roleId, String cargo, double salario) {
        Session session = null;
        Transaction transaction = null;
        boolean registrado = false;

        try {
            session = HibernateSessionManager.abrirSesion();
            if (session == null) {
                throw new IllegalStateException("No se pudo abrir una sesión con la base de datos.");
            }

            // Verificar si el email ya existe
            String hqlCheck = "FROM Persona WHERE email = :email";
            Query<Persona> query = session.createQuery(hqlCheck, Persona.class);
            query.setParameter("email", email);
            Persona existente = query.uniqueResult();

            if (existente != null) {
                System.out.println("El correo electrónico ya está registrado.");
                return false;
            }

            transaction = session.beginTransaction();

            Persona persona = null;


            if (roleId == 1) { // Cliente
                Cliente cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setDireccion(direccion);
                cliente.setTelefono(telefono);
                cliente.setCedula(cedula);
                cliente.setEmail(email);
                cliente.setContraseniaHash(contrasenia);
                cliente.setEstadoCliente(true);
                cliente.setFechaRegistro(new Date());

                session.save(cliente);
                persona = cliente;

            } else if (roleId == 2) { // Empleado
                Empleado empleado = new Empleado();
                empleado.setNombre(nombre);
                empleado.setDireccion(direccion);
                empleado.setTelefono(telefono);
                empleado.setCedula(cedula); 
                empleado.setEmail(email);
                empleado.setContraseniaHash(contrasenia);
                empleado.setCargo(cargo);
                empleado.setSalario(salario);

                session.save(empleado);
                persona = empleado;

            } else {
                System.out.println("Rol no válido.");
                return false;
            }

            if (persona != null && addRole(session, persona.getIdPersona(), roleId)) {
                transaction.commit();
                registrado = true;
                System.out.println("Registro exitoso.");
            } else {
                if (transaction != null) transaction.rollback();
                System.out.println("Error al asignar el rol.");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionManager.cerrarSesion(session);
        }

        return registrado;
    }

    public boolean addRole(Session session, int idPersona, int roleId) {
        try {
            System.out.println("Asignando rol ID " + roleId + " a persona ID " + idPersona);

            Persona persona = session.get(Persona.class, idPersona);
            Role rol = session.get(Role.class, roleId);

            if (persona == null || rol == null) {
                System.out.println("Persona o Rol no encontrados.");
                return false;
            }

            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setPersona(persona);
            usuarioRol.setRol(rol);

            session.save(usuarioRol);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public void verificarRolYRedirigir(int idPersona) {
            
        // Utilizando el patrón Facade para obtener el rol
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        RolDTO rol = usuarioFacade.obtenerRolUsuario(idPersona);
        
        if (rol != null) {
            // Redirección según el rol
            switch (rol.getNombreRol().toLowerCase()) {
                case "admin":
                    // Redirigir a pantalla de administrador
                    abrirPantallaAdministrador(idPersona);
                    break;
                case "cliente":
                    // Redirigir a pantalla de cliente
                    abrirPantallaCliente(idPersona);
                    break;
                case "empleado":
                    // Redirigir a pantalla de empleado
                    abrirPantallaEmpleado(idPersona);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, 
                        "Rol no reconocido: " + rol.getNombreRol(),
                        "Error de acceso", 
                        JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, 
                "No se encontró un rol asignado para este usuario",
                "Error de acceso", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void abrirPantallaAdministrador(int idPersona) {
        homeEmpleadoAdmin vAdmin = new homeEmpleadoAdmin(idPersona);
        vAdmin.setVisible(true);
    }
    
    private void abrirPantallaCliente(int idPersona) {
        home h = new home(idPersona);
               h.setVisible(true);
    }
    
    private void abrirPantallaEmpleado(int idPersona) {
        homeEmpleadoAdmin vAdmin = new homeEmpleadoAdmin(idPersona);
        vAdmin.setVisible(true);
    }
}