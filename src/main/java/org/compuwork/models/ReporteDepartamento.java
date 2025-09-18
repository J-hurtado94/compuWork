package org.compuwork.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ReporteDepartamento extends ReporteDesempeno {
private Departamento departamento;


    public ReporteDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    @Override
    public String generarReporte() {

        StringBuilder sb = new StringBuilder();
        sb.append("=== Reporte del Departamento ===\n");
        sb.append("Departamento: ").append(departamento.getNombre()).append("\n");
        sb.append("ID: ").append(departamento.getId()).append("\n");
        sb.append("Nº empleados: ").append(departamento.getEmpleados().size()).append("\n\n");

        Map<Metrica, Integer> acumulado = new EnumMap<>(Metrica.class);
        for (Metrica m : Metrica.values()) acumulado.put(m, 0);

        for (Empleado e : departamento.getEmpleados()) {
            for (Map.Entry<Metrica, Integer> entry : e.getMetricas().entrySet()) {
                acumulado.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        sb.append("Métricas totales:\n");
        for (Metrica m : Metrica.values()) {
            sb.append(" - ").append(m.name()).append(": ").append(acumulado.getOrDefault(m, 0)).append("\n");
        }

        sb.append("\nGenerado el: ").append(getFechaGeneracion()).append("\n");
        return sb.toString();
    }

    public Map<Metrica, Integer> obtenerResultadosAgregados() {
        Map<Metrica, Integer> acumulado = new EnumMap<>(Metrica.class);
        for (Metrica m : Metrica.values()) acumulado.put(m, 0);
        for (Empleado e : departamento.getEmpleados()) {
            for (Map.Entry<Metrica, Integer> entry : e.getMetricas().entrySet()) {
                acumulado.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }
        return acumulado;

    }
}
