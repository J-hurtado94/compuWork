package org.compuwork.models;

import org.compuwork.controllers.GestorEmpleados;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {
    private Administrador admin;
    private GestorEmpleados gestor;
    private Departamento dpto;

    @BeforeEach
    void setUp() {
        admin = new Administrador("Carlos");
        gestor = new GestorEmpleados();
        dpto = new Departamento("Sistemas");
    }

    @Test
    void crearEmpleadoPermanente_debeCrearloCorrectamente() {
        Empleado emp = admin.crearEmpleadoPermanente(gestor, "Ana", "Lopez", "ana@mail.com", 2000, dpto);

        assertNotNull(emp);
        assertEquals("Ana", emp.getNombre());
        assertEquals("Lopez", emp.getApellido());
        assertEquals("ana@mail.com", emp.getCorreo());
        assertEquals(2000, emp.getSalario());
        assertEquals(dpto, emp.getDepartamento());
    }

    @Test
    void crearEmpleadoTemporal_debeCrearloCorrectamente() {
        LocalDate fechaFin = LocalDate.now().plusMonths(6);
        Empleado emp = admin.crearEmpleadoTemporal(gestor, "Pedro", "Gomez", "pedro@mail.com", 1500, fechaFin, dpto);

        assertNotNull(emp);
        assertEquals("Pedro", emp.getNombre());
        assertEquals(dpto, emp.getDepartamento());
    }

    @Test
    void eliminarEmpleado_debeQuitarEmpleadoDelGestor() {
        Empleado emp = admin.crearEmpleadoPermanente(gestor, "Ana", "Lopez", "ana@mail.com", 2000, dpto);
        assertTrue(gestor.listarTodos().contains(emp));

        admin.eliminarEmpleado(gestor, emp.getIdUsuario());

        assertFalse(gestor.listarTodos().contains(emp));
    }

}