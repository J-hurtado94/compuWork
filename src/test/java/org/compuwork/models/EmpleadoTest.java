package org.compuwork.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoTest {

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleado = new EmpleadoPermanente("Juan", "Diaz", "juan@mail.com", 2500);
    }

    @Test
    void constructorDebeInicializarCorrectamente() {
        assertEquals("Juan", empleado.getNombre());
        assertEquals("Diaz", empleado.getApellido());
        assertEquals("juan@mail.com", empleado.getCorreo());
        assertEquals(2500, empleado.getSalario());
        assertNotNull(empleado.getFechaIngreso());
        assertNull(empleado.getDepartamento());
    }

    @Test
    void asignarMetricaDebeAgregarConValorPredefinido() {
        empleado.asignarMetrica(Metrica.PRODUCTIVIDAD);

        assertTrue(empleado.getMetricas().containsKey(Metrica.PRODUCTIVIDAD));
        assertEquals(5, empleado.getMetricas().get(Metrica.PRODUCTIVIDAD));
    }

    @Test
    void settersDebenActualizarCampos() {
        empleado.setNombre("Carlos");
        empleado.setApellido("Torres");
        empleado.setCorreo("carlos@mail.com");
        empleado.setSalario(3000);

        assertEquals("Carlos", empleado.getNombre());
        assertEquals("Torres", empleado.getApellido());
        assertEquals("carlos@mail.com", empleado.getCorreo());
        assertEquals(3000, empleado.getSalario());
    }

}