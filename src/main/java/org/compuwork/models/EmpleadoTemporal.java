package org.compuwork.models;

import java.time.LocalDate;

public class EmpleadoTemporal extends Empleado{
    private LocalDate fechaFinContrato;

    public EmpleadoTemporal(String nombre, String apellido, String correo, double salario, LocalDate fechaFinContrato) {
        super(nombre, apellido, correo, salario);
        this.fechaFinContrato = fechaFinContrato;
    }

    public LocalDate getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(LocalDate fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    @Override
    public String tipoEmpleado() {
        return "Temporal";
    }

    @Override
    public String toString() {
        return super.toString() +
                "EmpleadoTemporal{" +
                "fechaFinContrato=" + fechaFinContrato +
                '}';
    }
}
