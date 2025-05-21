/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanizar.alquilerprendas.controller;

import humanizar.alquilerprendas.facade.PrendaFacade;
import humanizar.alquilerprendas.model.Categoria;
import humanizar.alquilerprendas.model.Genero;
import humanizar.alquilerprendas.model.Material;
import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.model.Talla;
import humanizar.alquilerprendas.model.Tipo;
import humanizar.alquilerprendas.util.HibernateSessionManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author Andres Santos
 */
public class PrendaController {
 private final PrendaFacade prendaFacade;
    
    // Constructor que recibe la implementación del Facade
    public PrendaController(PrendaFacade prendaFacade) {
        this.prendaFacade = prendaFacade;
    }
    
    /**
     * Método para validar los datos de entrada de una prenda
     * @param parametros Los parámetros a validar
     * @return Mapa con los errores encontrados (vacío si todo está bien)
     */
    public Map<String, String> validarDatosPrenda(Map<String, Object> parametros) {
        Map<String, String> errores = new HashMap<>();
        
        // Validación de campos obligatorios
        String[] camposObligatorios = {"tipo", "talla", "material", "genero", "categoria", "pedreria", "color", "precio", "descripcion"};
        for (String campo : camposObligatorios) {
            Object valor = parametros.get(campo);
            if (valor == null || (valor instanceof String && 
                    (((String)valor).trim().isEmpty() || ((String)valor).equals(" ")))) {
                errores.put(campo, "El campo " + campo + " es obligatorio");
            }
        }
        
        // Validación específica para precio (debe ser un número válido)
        if (!errores.containsKey("precio")) {
            try {
                String precioStr = (String) parametros.get("precio");
                double precio = Double.parseDouble(precioStr);
                if (precio <= 0) {
                    errores.put("precio", "El precio debe ser mayor a 0");
                }
            } catch (NumberFormatException e) {
                errores.put("precio", "El precio debe ser un número válido");
            }
        }
        
        return errores;
    }
    
    /**
     * Método para crear y persistir una prenda
     * @param parametros Los parámetros de la prenda
     * @return true si se guardó correctamente, false si hubo errores
     */
    public boolean crearPrenda(Map<String, Object> parametros) {
        System.out.println("Entra a crear prenda");
        
        try {
            Session session = null;
             session = HibernateSessionManager.abrirSesion();
            // Crear el objeto Prenda
            Prenda prenda = new Prenda();
            
            // Asignar valores
            prenda.setTipo((Tipo) parametros.get("tipo"));
            prenda.setTalla((Talla) parametros.get("talla"));
            prenda.setMaterial((Material) parametros.get("material"));
            prenda.setGenero((Genero) parametros.get("genero"));
            prenda.setCategoria((Categoria) parametros.get("categoria"));
            prenda.setPedreria((Boolean) parametros.get("pedreria"));
            prenda.setColor((String) parametros.get("color"));
            prenda.setPrecio((Double) parametros.get("precio"));
            prenda.setDescripcion((String) parametros.get("descripcion"));
            
            // Valores por defecto
            prenda.setAlquilada(false);
            prenda.setParaLavanderia(false);
            prenda.setFechaIngreso(new Date());
            
            // Persistir usando el Facade
            prendaFacade.guardarPrenda(prenda);
            
            JOptionPane.showMessageDialog(null, "Prenda guardada exitosamente", 
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            System.out.println( "Error al guardar la prenda: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al guardar la prenda: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
}
