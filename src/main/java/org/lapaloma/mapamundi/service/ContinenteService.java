/**
 * 
 */
package org.lapaloma.mapamundi.service;

import java.util.List;

import org.lapaloma.mapamundi.dao.IContinenteDAO;
import org.lapaloma.mapamundi.excepcion.ContinenteNoEncontradoException;
import org.lapaloma.mapamundi.vo.Continente;
import org.springframework.stereotype.Service;

@Service
public class ContinenteService {

    private final IContinenteDAO continenteDAO;

    // Spring inyecta el DAO automáticamente
    public ContinenteService(IContinenteDAO continenteDAO) {
        this.continenteDAO = continenteDAO;
    }

    public Continente obtenerContinentePorClave(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código inválido");
        }

        Continente continente = continenteDAO.obtenerContinentePorClave(codigo);

        if (continente == null) {
            throw new ContinenteNoEncontradoException(
                    "No existe el continente con código: " + codigo);
        }

        return continente;
    }

    public List<Continente> obtenerListaContinentes() {

        List<Continente> lista = continenteDAO.obtenerListaContinentes();

        // Esto provoca error
        // lista = null;

        if (lista == null || lista.isEmpty()) {
            throw new RuntimeException("No hay continentes disponibles");
        }

        return lista;
    }

    public List<Continente> obtenerContinentePorNombre(String nombre) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre inválido");
        }

        List<Continente> lista = continenteDAO.obtenerContinentePorNombre(nombre);

        // Al comentar este código desaparece el error
        // lista =null;

        if (lista == null || lista.isEmpty()) {
            throw new ContinenteNoEncontradoException(
                    "No existen continentes con nombre: " + nombre);
        }

        return lista;
    }
}