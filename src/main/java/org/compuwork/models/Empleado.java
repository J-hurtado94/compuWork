package org.compuwork.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Empleado extends Usuario{
    private String nombre;
    private String apellido;
    private String correo;
    private double salario;
    private LocalDateTime fechaIngreso;
    private Departamento departamento;

    private Map<Metrica, Integer> metricas = new HashMap<>();

    public Empleado( String nombre, String apellido, String correo, double salario) {
        super(nombre,Rol.EMPLEADO);
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.salario = salario;
        this.fechaIngreso = LocalDateTime.now();

    }
   protected void setDepartamentoInterno(Departamento departamento) { this.departamento = departamento; }

    public abstract String tipoEmpleado();




    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public double getSalario() {
        return salario;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }


    public Departamento getDepartamento() {
        return departamento;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }



    public Map<Metrica, Integer> getMetricas() {
        return metricas;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", salario=" + salario +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}
