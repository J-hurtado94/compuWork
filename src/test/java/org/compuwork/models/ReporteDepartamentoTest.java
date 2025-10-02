package org.compuwork.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReporteDepartamentoTest {

    private Departamento departamento;
    private EmpleadoPermanente empleado1;
    private EmpleadoTemporal empleado2;

    @BeforeEach
    void setUp() {
        departamento = new Departamento("Tecnología");

        empleado1 = new EmpleadoPermanente("Ana", "Gómez", "ana@test.com", 3000.0);
        empleado2 = new EmpleadoTemporal("Luis", "Torres", "luis@test.com", 1500.0,
                java.time.LocalDate.now().plusMonths(6));

        empleado1.asignarMetrica(Metrica.PRODUCTIVIDAD);
        empleado1.asignarMetrica(Metrica.PUNTUALIDAD);
        empleado2.asignarMetrica(Metrica.CAPACITACION);

        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);
    }

    @Test
    void generarReporte_deberiaIncluirInformacionDepartamento() {
        ReporteDepartamento reporte = new ReporteDepartamento(departamento);

        String resultado = reporte.generarReporte();

        assertTrue(resultado.contains("=== Reporte del Departamento ==="));
        assertTrue(resultado.contains("Departamento: Tecnología"));
        assertTrue(resultado.contains("Nº empleados: 2"));
        assertTrue(resultado.contains("PRODUCTIVIDAD: 5"));
        assertTrue(resultado.contains("PUNTUALIDAD: 3"));
        assertTrue(resultado.contains("CAPACITACION: 1"));
    }

    @Test
    void obtenerResultadosAgregados_deberiaSumarMetricasDeEmpleados() {
        ReporteDepartamento reporte = new ReporteDepartamento(departamento);

        Map<Metrica, Integer> resultados = reporte.obtenerResultadosAgregados();

        assertEquals(5, resultados.get(Metrica.PRODUCTIVIDAD));
        assertEquals(3, resultados.get(Metrica.PUNTUALIDAD));
        assertEquals(1, resultados.get(Metrica.CAPACITACION));
        assertEquals(0, resultados.get(Metrica.CUMPLIMIENTO_METAS));
    }

}