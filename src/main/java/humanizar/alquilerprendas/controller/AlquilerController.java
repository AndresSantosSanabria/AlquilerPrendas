/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanizar.alquilerprendas.controller;

import humanizar.alquilerprendas.facade.AlquilerServiceFacade;
import humanizar.alquilerprendas.model.AlquilerPrenda;
import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.model.ServicioAlquiler;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres Santos
 */
public class AlquilerController {

    private final AlquilerServiceFacade alquilerService;

    public AlquilerController() {
        this.alquilerService = new AlquilerServiceFacade();
    }

    public boolean mostrarAlquilerEnTabla(Integer idServicio, JTable tablaPrendas) {

        // Buscar alquileres por el número de servicio
        List<AlquilerPrenda> alquileres = alquilerService.buscarAlquileresPorServicio(idServicio);

        if (alquileres == null || alquileres.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No se encontró ningún alquiler con el número: " + idServicio,
                    "Alquiler no encontrado",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        // Obtener el servicio de alquiler (común para todas las prendas)
        ServicioAlquiler servicio = alquileres.get(0).getServicioAlquiler();

        // Crear el modelo para la tabla
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        // Definir las columnas
        modelo.addColumn("Descripcion");
        modelo.addColumn("Material");
        modelo.addColumn("Categoria");
        modelo.addColumn("Talla");
        modelo.addColumn("Género");
        modelo.addColumn("Fecha de alquiler");
        modelo.addColumn("Fecha de devolución");
        modelo.addColumn("Precio Alquiler");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        // Agregar filas con datos de cada prenda
        double total = 0;
        for (AlquilerPrenda alquiler : alquileres) {
            Prenda prenda = alquiler.getPrenda();

            String fechaAlquiler = formatoFecha.format(servicio.getFechaSolicitud());
            String fechaDevolucion = formatoFecha.format(servicio.getFechaDevolucion());

            Object[] fila = new Object[]{
                prenda.getDescripcion(),
                prenda.getMaterial().getMaterialPrenda(),
                prenda.getCategoria().getCategoriaPrenda(),
                prenda.getTalla().getTallaPrenda(),
                prenda.getGenero().getGeneroPrenda(),
                fechaAlquiler,
                fechaDevolucion,
                String.format("$%.2f", prenda.getPrecio())
            };

            modelo.addRow(fila);
        }
        tablaPrendas.setModel(modelo);
        tablaPrendas.getColumnModel().getColumn(0).setPreferredWidth(70); // Descripción
        tablaPrendas.getColumnModel().getColumn(1).setPreferredWidth(10); // Material
        tablaPrendas.getColumnModel().getColumn(2).setPreferredWidth(10);  // Talla
        tablaPrendas.getColumnModel().getColumn(3).setPreferredWidth(10);  // Género
        tablaPrendas.getColumnModel().getColumn(4).setPreferredWidth(40); // Fecha de alquiler
        tablaPrendas.getColumnModel().getColumn(5).setPreferredWidth(50); // Fecha de devolución
        tablaPrendas.getColumnModel().getColumn(6).setPreferredWidth(5); // Precio alquiler

        return true;
    }

    public boolean mostrarAlquileresPorFecha(Date fecha, JTable tablaPrendas) {

        List<AlquilerPrenda> 
        alquileres = alquilerService.buscarAlquileresPorFecha(fecha);

        if (alquileres == null || alquileres.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No se encontró ningún alquiler para la fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(fecha),
                    "Alquiler no encontrado",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        ServicioAlquiler servicio = alquileres.get(0).getServicioAlquiler();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("Descripcion");
        modelo.addColumn("Material");
        modelo.addColumn("Categoria");
        modelo.addColumn("Talla");
        modelo.addColumn("Género");
        modelo.addColumn("Fecha de alquiler");
        modelo.addColumn("Fecha de devolución");
        modelo.addColumn("Precio Alquiler");

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        for (AlquilerPrenda alquiler : alquileres) {
            Prenda prenda = alquiler.getPrenda();

            String fechaAlquiler = formatoFecha.format(servicio.getFechaSolicitud());
            String fechaDevolucion = formatoFecha.format(servicio.getFechaDevolucion());

            Object[] fila = new Object[]{
                prenda.getDescripcion(),
                prenda.getMaterial().getMaterialPrenda(),
                prenda.getCategoria().getCategoriaPrenda(),
                prenda.getTalla().getTallaPrenda(),
                prenda.getGenero().getGeneroPrenda(),
                fechaAlquiler,
                fechaDevolucion,
                String.format("$%.2f", prenda.getPrecio())
            };

            modelo.addRow(fila);
        }

        tablaPrendas.setModel(modelo);
        tablaPrendas.getColumnModel().getColumn(0).setPreferredWidth(70);
        tablaPrendas.getColumnModel().getColumn(1).setPreferredWidth(10);
        tablaPrendas.getColumnModel().getColumn(2).setPreferredWidth(10);
        tablaPrendas.getColumnModel().getColumn(3).setPreferredWidth(10);
        tablaPrendas.getColumnModel().getColumn(4).setPreferredWidth(40);
        tablaPrendas.getColumnModel().getColumn(5).setPreferredWidth(50);
        tablaPrendas.getColumnModel().getColumn(6).setPreferredWidth(5);

        return true;
    }

}
