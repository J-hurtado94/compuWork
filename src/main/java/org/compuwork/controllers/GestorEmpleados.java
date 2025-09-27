package org.compuwork.controllers;

import org.compuwork.exceptions.AsignacionInvalidaException;
import org.compuwork.exceptions.EmpleadoNoEncontradoException;
import org.compuwork.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorEmpleados {
    private final List<Usuario> usuarios = new ArrayList<>();

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Empleado crearEmpleadoPermanente(String nombre, String apellido, String correo, double salario, Departamento dpto) {
        Empleado nuevo = new EmpleadoPermanente(nombre, apellido, correo, salario);
        nuevo.setDepartamento(dpto);
        usuarios.add(nuevo);
        return nuevo;
    }

    public Empleado crearEmpleadoTemporal(String nombre, String apellido, String correo, double salario, LocalDate fechaFinContrato, Departamento dpto) {
        Empleado nuevo = new EmpleadoTemporal(nombre, apellido, correo, salario, fechaFinContrato);
        nuevo.setDepartamento(dpto);
        usuarios.add(nuevo);
        return nuevo;
    }



    public Empleado actualizarEmpleadoPorId(String idUsuario, Empleado nuevosDatos) {
        Optional<Usuario> opt = usuarios.stream()
                .filter(u -> u.getIdUsuario().equals(idUsuario))
                .findFirst();

        if (opt.isPresent() && opt.get() instanceof Empleado) {
            Empleado actualEm = (Empleado) opt.get();
            actualEm.setCorreo(nuevosDatos.getCorreo());
            actualEm.setSalario(nuevosDatos.getSalario());
            return actualEm;
        }
        throw new EmpleadoNoEncontradoException(idUsuario);
    }

    public void eliminarEmpleadoPorId(String idUsuario) {
        boolean removed = usuarios.removeIf(u -> u.getIdUsuario().equals(idUsuario));
        if (!removed) throw new EmpleadoNoEncontradoException(idUsuario);
    }

    public void asignarEmpleadoADepartamento(String idUsuario, Departamento departamento) {
        Usuario u = buscarPorId(idUsuario);
        if (!(u instanceof Empleado)) throw new AsignacionInvalidaException("El usuario no es un empleado");
        if (departamento == null) throw new AsignacionInvalidaException("Departamento nulo");

        Empleado emp = (Empleado) u;
        departamento.agregarEmpleado(emp);
    }

    public Usuario buscarPorId(String idUsuario) {
        return usuarios.stream()
                .filter(u -> u.getIdUsuario().equals(idUsuario))
                .findFirst()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(idUsuario));
    }
    public Usuario buscarPorNombre(String nombre) {
        return usuarios.stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(nombre));
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }
    public void asignarMetrica(Empleado empleado, Metrica metrica, int valor) {
        empleado.getMetricas().put(metrica, valor);
    }

    public void actualizarMetrica(Empleado empleado, Metrica metrica, int valor) {
        empleado.getMetricas().put(metrica, valor);
    }
}
