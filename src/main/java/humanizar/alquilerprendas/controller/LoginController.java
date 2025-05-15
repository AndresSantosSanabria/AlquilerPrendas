/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanizar.alquilerprendas.controller;

import humanizar.alquilerprendas.model.Cliente;
import humanizar.alquilerprendas.model.Empleado;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import humanizar.alquilerprendas.model.Persona;
import humanizar.alquilerprendas.model.Role;
import humanizar.alquilerprendas.model.UsuarioRol;
import java.util.Date;
import java.util.HashSet;
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

public boolean Register(String nombre, String direccion, String cedula, String telefono, String email, String contrasenia, int roleId, String cargo, double salario) {
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
            JOptionPane.showMessageDialog(null, "El correo electrónico ya está registrado.", "Error de registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        transaction = session.beginTransaction();

        Persona persona = null;

        if (roleId == 1) {//asignacion de rol como cliente 
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

        } else if (roleId == 2) {//asignacion de rol como empleado
            Empleado empleado = new Empleado();
            empleado.setNombre(nombre);
            empleado.setDireccion(direccion);
            empleado.setTelefono(telefono);
            empleado.setEmail(email);
            empleado.setContraseniaHash(contrasenia);
            empleado.setCargo(cargo);
            empleado.setSalario(salario);

            session.save(empleado);
            persona = empleado;
        }

        if (persona != null && addRole(session, persona.getIdPersona(), roleId)) {
            transaction.commit();
            registrado = true;
            JOptionPane.showMessageDialog(null, "Registro exitoso.");
        } else {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, "Error al asignar el rol.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        HibernateSessionManager.cerrarSesion(session);
    }

    return registrado;
}


    public boolean addRole(Session session, int idPersona, int roleId) {
    try {
        System.out.println(roleId);
        System.out.println(idPersona);
        
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



}
