package org.compuwork.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoPermanente extends Empleado{


    private double salario;

    public EmpleadoPermanente(String nombre, String apellido, String correo, double salario) {
        super(nombre, apellido, correo, salario);
        this.salario = salario;
    }

    @Override
    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public double getSalario() {
        return salario;
    }

    @Override
    public String tipoEmpleado() {
        return "Permanente";
    }

    @Override
    public String toString() {
        return super.toString() +
         "EmpleadoPermanente{" +
                ", salario=" + salario +
                '}';
    }
}
