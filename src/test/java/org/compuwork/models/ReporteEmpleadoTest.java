package org.compuwork.models;

import org.compuwork.exceptions.ReporteInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReporteEmpleadoTest {
    private EmpleadoPermanente empleado;

    @BeforeEach
    void setUp() {
        empleado = new EmpleadoPermanente("Juan", "Pérez", "juan@test.com", 2500.0);
    }

    @Test
    void generarReporte_conMetricas_deberiaIncluirDatosEmpleado() {
        empleado.asignarMetrica(Metrica.PUNTUALIDAD);
        empleado.asignarMetrica(Metrica.PRODUCTIVIDAD);

        ReporteEmpleado reporte = new ReporteEmpleado(empleado);

        String resultado = reporte.generarReporte();

        assertTrue(resultado.contains("=== Reporte de Empleado ==="));
        assertTrue(resultado.contains("Juan Pérez"));
        assertTrue(resultado.contains("PUNTUALIDAD: 3"));
        assertTrue(resultado.contains("PRODUCTIVIDAD: 5"));
    }

    @Test
    void generarReporte_sinMetricas_deberiaLanzarExcepcion() {
        ReporteEmpleado reporte = new ReporteEmpleado(empleado);

        assertThrows(ReporteInvalidoException.class, reporte::generarReporte);
    }

    @Test
    void obtenerResultados_deberiaRetornarMetricasAsignadas() {
        empleado.asignarMetrica(Metrica.CAPACITACION);

        ReporteEmpleado reporte = new ReporteEmpleado(empleado);

        Map<Metrica, Integer> metricas = reporte.obtenerResultados();
        assertEquals(1, metricas.size());
        assertEquals(1, metricas.get(Metrica.CAPACITACION));
    }

}