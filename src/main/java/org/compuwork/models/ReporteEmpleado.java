package org.compuwork.models;

import org.compuwork.exceptions.ReporteInvalidoException;

import java.util.Map;

public class ReporteEmpleado extends ReporteDesempeno {

private final Empleado empleado;


    public ReporteEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }


    @Override
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Reporte de Empleado ===\n");
        sb.append("Nombre: ").append(empleado.getNombre()).append(" ").append(empleado.getApellido()).append("\n");
        sb.append("ID: ").append(empleado.getIdEmpleado()).append("\n");
        sb.append("Departamento: ").append(empleado.getDepartamento() != null ? empleado.getDepartamento().getNombre() : "Sin asignar").append("\n");
        sb.append("Fecha ingreso: ").append(empleado.getFechaIngreso()).append("\n\n");
        sb.append("Métricas:\n");

        Map<Metrica, Integer> metricasEmpleado = empleado.getMetricas();
        if (metricasEmpleado.isEmpty()){
            throw new ReporteInvalidoException("No tiene metricas asignadas el empleado");
        }
        for (Metrica m : Metrica.values()) {
            sb.append(" - ").append(m.name()).append(": ").append(metricasEmpleado.getOrDefault(m, 0)).append("\n");
        }

        sb.append("\nGenerado el: ").append(getFechaGeneracion()).append("\n");
        return sb.toString();
    }

    public Map<Metrica, Integer> obtenerResultados() {
        return empleado.getMetricas();

    }
}
