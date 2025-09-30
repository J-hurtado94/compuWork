package org.compuwork.controllers;

import org.compuwork.models.Departamento;
import org.compuwork.models.Empleado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorEmpleadosTest {

    @Test
    void crearEmpleadoPermanente() {

        // 1️⃣ Preparar
        GestorEmpleados gestor = new GestorEmpleados();
        String nombre = "Juan";
        String apellido = "Pérez";
        String correo = "juan@example.com";
        double salario = 2500.0;
        Departamento dpto = new Departamento("TI");


        Empleado emp = gestor.crearEmpleadoPermanente(nombre, apellido, correo, salario, dpto);


        assertNotNull(emp, "El empleado no debe ser null");
        assertEquals(nombre, emp.getNombre());
        assertEquals(apellido, emp.getApellido());
        assertEquals(correo, emp.getCorreo());
        assertEquals(salario, emp.getSalario());
        assertEquals(dpto, emp.getDepartamento());


        assertTrue(gestor.listarTodos().contains(emp), "El empleado debe estar en la lista de usuarios");
    }

}