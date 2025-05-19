/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanizar.alquilerprendas.controller;

import humanizar.alquilerprendas.dto.PrendaDTO;


/**
 *
 * @author Andres Santos
 */

public class FiltroPrendaBuilder {
    
 public String construirConsultaSQL(PrendaDTO filtro) {
        StringBuilder query = new StringBuilder("SELECT * FROM prenda WHERE 1=1");

        if (filtro.getTipoPrenda()!= null && !filtro.getTipoPrenda().startsWith("--")) {
            query.append(" AND tipo = '").append(filtro.getTipoPrenda()).append("'");
        }

        if (filtro.getTalla() != null && !filtro.getTalla().startsWith("--")) {
            query.append(" AND talla = '").append(filtro.getTalla()).append("'");
        }

        if (filtro.getMaterial() != null && !filtro.getMaterial().startsWith("--")) {
            query.append(" AND material = '").append(filtro.getMaterial()).append("'");
        }

        if (filtro.getGenero() != null && !filtro.getGenero().startsWith("--")) {
            query.append(" AND genero = '").append(filtro.getGenero()).append("'");
        }

        if (filtro.getCategoria() != null && !filtro.getCategoria().startsWith("--")) {
            query.append(" AND categoria = '").append(filtro.getCategoria()).append("'");
        }

        return query.toString();
    }

}
