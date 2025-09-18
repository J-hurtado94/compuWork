package org.compuwork.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoPermanente extends Empleado{

    private List<String> benificios;
    private double salario;

    public EmpleadoPermanente(String nombre, String apellido, String correo, double salario, List<String> benificios) {
        super(nombre, apellido, correo, salario);
        this.benificios = benificios;
        this.salario = salario;
    }

    public List<String> getBenificios() {
        return benificios;
    }

    public void setBenificios(List<String> benificios) {
        this.benificios = benificios;
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
                "benificios=" + benificios +
                ", salario=" + salario +
                '}';
    }
}
