package org.compuwork.models;

import org.compuwork.controllers.GestorEmpleados;

import java.time.LocalDate;

public class Administrador extends Usuario{


    public Administrador(String nombre) {
        super(nombre, Rol.ADMIN);
    }

    public Empleado crearEmpleadoPermanente(GestorEmpleados gestor, String nombre, String apellido,
                                            String correo, double salario, Departamento dpto) {
        return gestor.crearEmpleadoPermanente(nombre, apellido, correo, salario, dpto);
    }

    public Empleado crearEmpleadoTemporal(GestorEmpleados gestor, String nombre, String apellido,
                                          String correo, double salario, LocalDate fechaFin, Departamento dpto) {
        return gestor.crearEmpleadoTemporal(nombre, apellido, correo, salario, fechaFin, dpto);
    }

    public void eliminarEmpleado(GestorEmpleados gestor, String idUsuario) {
        gestor.eliminarEmpleadoPorId(idUsuario);
    }
}
