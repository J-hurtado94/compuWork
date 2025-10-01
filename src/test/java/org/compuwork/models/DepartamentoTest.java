package org.compuwork.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartamentoTest {
    private Departamento dpto;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        dpto = new Departamento("Recursos Humanos");
        empleado = new EmpleadoPermanente("Laura", "Perez", "laura@mail.com", 1800);
    }

    @Test
    void constructorDebeAsignarNombre() {
        assertEquals("Recursos Humanos", dpto.getNombre());
        assertNotNull(dpto.getId());
    }

    @Test
    void agregarEmpleado_debeAgregarYAsignarDepartamento() {
        dpto.agregarEmpleado(empleado);

        assertTrue(dpto.getEmpleados().contains(empleado));
        assertEquals(dpto, empleado.getDepartamento());
    }

    @Test
    void agregarEmpleadoNuloDebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> dpto.agregarEmpleado(null));
    }

    @Test
    void removerEmpleado_debeEliminarRelacion() {
        dpto.agregarEmpleado(empleado);
        dpto.removerEmpleado(empleado);

        assertFalse(dpto.getEmpleados().contains(empleado));
        assertNull(empleado.getDepartamento());
    }

    @Test
    void toStringDebeRetornarNombre() {
        assertEquals("Recursos Humanos", dpto.toString());
    }

}