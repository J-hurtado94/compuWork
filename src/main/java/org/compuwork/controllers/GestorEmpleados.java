package org.compuwork.controllers;

import org.compuwork.exceptions.AsignacionInvalidaException;
import org.compuwork.exceptions.EmpleadoNoEncontradoException;
import org.compuwork.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorEmpleados {
    private final List<Empleado> repositorio = new ArrayList<>();

    public Empleado crearEmpleado(Empleado empleado) {
        repositorio.add(empleado);
        return empleado;
    }

    public Empleado actualizarEmpleado(String id, Empleado nuevosDatos) {
        Optional<Empleado> opt = repositorio.stream().filter(e -> e.getIdEmpleado().equals(id)).findFirst();
        if (opt.isPresent()) {
            Empleado actualEm = opt.get();
            actualEm.setCorreo(nuevosDatos.getCorreo());
            actualEm.setSalario(nuevosDatos.getSalario());
            return actualEm;
        }
        throw new EmpleadoNoEncontradoException(id);
    }

    public void eliminarEmpleado(String id) {
        repositorio.removeIf(e -> e.getIdEmpleado().equals(id));
    }

    public void asignarEmpleadoADepartamento(String empleadoId, Departamento departamento) {
        Empleado e = repositorio.stream().filter(x -> x.getIdEmpleado().equals(empleadoId)).findFirst()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(empleadoId));
        if (departamento == null) throw new AsignacionInvalidaException("Departamento nulo");
        departamento.agregarEmpleado(e);
    }

    public Empleado buscarPorId(String id) {
        return repositorio.stream().filter(e -> e.getIdEmpleado().equals(id)).findFirst()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
    }

    public List<Empleado> listarTodos() {
        return new ArrayList<>(repositorio);
    }
    public void asignarMetrica(Empleado empleado, Metrica metrica, int valor) {
        empleado.getMetricas().put(metrica, valor);
    }

    public void actualizarMetrica(Empleado empleado, Metrica metrica, int valor) {
        empleado.getMetricas().put(metrica, valor);
    }
}
