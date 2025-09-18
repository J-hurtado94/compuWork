package org.compuwork.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Departamento {
    private  String idDepartamento;
    private String nombre;
    private  List<Empleado> empleados = new ArrayList<>();

    public Departamento(String nombre) {
        this.idDepartamento = UUID.randomUUID().toString();
        this.nombre = Objects.requireNonNull(nombre);
    }

    public String getId() {
        return idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setNombre(String nombre) {
        this.nombre = Objects.requireNonNull(nombre);;
    }

    public void agregarEmpleado(Empleado empleado) {
        if (empleado == null) throw new IllegalArgumentException("Empleado nulo");
        if (!empleados.contains(empleado)) {
            empleados.add(empleado);
            empleado.setDepartamentoInterno(this);
        }
    }

    public void removerEmpleado(Empleado empleado) {
        if (empleado == null) return;
        empleados.remove(empleado);
        if (empleado.getDepartamento() == this)empleado.setDepartamento(null);

    }



    @Override
    public String toString() {
        return "Departamento{" +
                "idDepartamento='" + idDepartamento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", empleados=" + empleados +
                '}';
    }
}
