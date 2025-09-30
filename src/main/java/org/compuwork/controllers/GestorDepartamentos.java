package org.compuwork.controllers;

import org.compuwork.exceptions.CreacionDepartamentoInvalido;
import org.compuwork.models.Departamento;
import org.compuwork.exceptions.DepartamentoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorDepartamentos {

    private final List<Departamento> repositorio = new ArrayList<>();

    public Departamento crearDepartamento(String nombre) {
        for (Departamento d : repositorio) {
            if (d.getNombre().equalsIgnoreCase(nombre)) {
                throw new CreacionDepartamentoInvalido("Ya existe un departamento con el nombre: " + nombre);
            }
        }

        Departamento departamento = new Departamento(nombre);
        repositorio.add(departamento);
        return departamento;
    }

    public boolean modificarNombre(String id, String nuevoNombre) {
        Optional<Departamento> opt = repositorio.stream().filter(d -> d.getId().equals(id)).findFirst();
        if (opt.isPresent()) {
            opt.get().setNombre(nuevoNombre);
            return true;
        }
        return false;
    }

    public boolean eliminarDepartamento(String id) {
        return repositorio.removeIf(d -> d.getId().equals(id));
    }

    public Departamento buscarPorId(String id) {
        return repositorio.stream().filter(d -> d.getId().equals(id)).findFirst()
                .orElseThrow(() -> new DepartamentoNoEncontradoException(id));
    }

    public List<Departamento> listarTodos() {
        return new ArrayList<>(repositorio);
    }

    public Departamento buscarODefault(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            nombre = "General";
        }


        for (Departamento d : getRepositorio()) {
            if (d.getNombre().equalsIgnoreCase(nombre)) {
                return d;
            }
        }

        return crearDepartamento(nombre);
    }

    public List<Departamento> getRepositorio() {
        return repositorio;
    }
}
