/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanizar.alquilerprendas.controller;

import humanizar.alquilerprendas.dao.PrendaDao;
import humanizar.alquilerprendas.model.Prenda;
import humanizar.alquilerprendas.dto.PrendaDTO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres Santos
 */
public class ProductoController {

    public DefaultTableModel cargarProductos(String categoria, String genero, String material, String tipo, String talla) {
        // Añadimos la columna "Seleccionar" al principio
        String[] columnNames = {"Seleccionar", "Codigo", "Color", "Descripcion", "Categoria", "Genero", "Material", "Prenda", "Pedreria", "Precio", "Talla"};

        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                // Primera columna (checkbox) es Boolean
                return column == 0 ? Boolean.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo la primera columna (checkbox) es editable
                return column == 0;
            }
        };

        PrendaDao dao = new PrendaDao();
        List<PrendaDTO> prendas = dao.obtenerResumenPrendasConFiltros(categoria, genero, material, tipo, talla);

        for (PrendaDTO p : prendas) {
            Object[] fila = {
                false, // Valor por defecto del checkbox
                p.getId(),
                p.getColor(),
                p.getDescripcion(),
                p.getCategoria(),
                p.getGenero(),
                p.getMaterial(),
                p.getTipoPrenda(),
                Boolean.TRUE.equals(p.getPedreria()) ? "Sí" : "No",
                p.getPrecio(),
                p.getTalla()
            };
            model.addRow(fila);
        }

        return model;
    }

}
