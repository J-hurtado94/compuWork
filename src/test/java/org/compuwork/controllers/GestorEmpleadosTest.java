package org.compuwork.controllers;

import org.compuwork.exceptions.AsignacionInvalidaException;
import org.compuwork.exceptions.EmpleadoNoEncontradoException;
import org.compuwork.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GestorEmpleadosTest {
    private GestorEmpleados gestor;
    private Departamento dpto;

    @BeforeEach
    void setUp() {
        gestor = new GestorEmpleados();
        dpto = new Departamento("TI");
    }

    @Test
    void agregarUsuario_debeAgregarUsuarioALaLista() {
        Usuario usuario = new Administrador("Carlos");

        gestor.agregarUsuario(usuario);

        assertTrue(gestor.listarTodos().contains(usuario));
    }

    @Test
    void crearEmpleadoPermanente_debeCrearYAsignarDepartamento() {
        String nombre = "Ana";
        String apellido = "Ruiz";
        String correo = "ana@test.com";
        double salario = 2500.0;

        Empleado emp = gestor.crearEmpleadoPermanente(nombre, apellido, correo, salario, dpto);

        assertNotNull(emp);
        assertEquals(nombre, emp.getNombre());
        assertEquals(apellido, emp.getApellido());
        assertEquals(correo, emp.getCorreo());
        assertEquals(salario, emp.getSalario());
        assertEquals(dpto, emp.getDepartamento());
        assertTrue(gestor.listarTodos().contains(emp));
    }
    @Test
    void crearEmpleadoTemporal_debeCrearYAsignarDepartamento() {
        // Preparar
        String nombre = "Carlos";
        String apellido = "Gómez";
        String correo = "carlos@example.com";
        double salario = 2000.0;
        LocalDate finContrato = LocalDate.of(2026, 12, 31);

        // Ejecutar
        Empleado emp = gestor.crearEmpleadoTemporal(nombre, apellido, correo, salario, finContrato, dpto);

        // Verificar
        assertNotNull(emp);
        assertEquals(nombre, emp.getNombre());
        assertEquals(apellido, emp.getApellido());
        assertEquals(correo, emp.getCorreo());
        assertEquals(salario, emp.getSalario());
        assertEquals(dpto, emp.getDepartamento());
        assertTrue(gestor.listarTodos().contains(emp));
    }

    @Test
    void actualizarEmpleadoPorId_debeActualizarTodosLosCamposMenosId() {
        // Preparar
        Empleado emp = gestor.crearEmpleadoPermanente("Ana", "Ruiz", "ana@old.com", 1500, dpto);
        String idOriginal = emp.getIdUsuario();

        Empleado nuevosDatos = new EmpleadoPermanente("AnaMaria", "RuizLopez", "ana@new.com", 2500);
        nuevosDatos.setDepartamento(new Departamento("Marketing"));

        // Ejecutar
        Empleado actualizado = gestor.actualizarEmpleadoPorId(idOriginal, nuevosDatos);

        // Verificar
        assertEquals(idOriginal, actualizado.getIdUsuario()); // el id no cambia
        assertEquals("AnaMaria", actualizado.getNombre());
        assertEquals("RuizLopez", actualizado.getApellido());
        assertEquals("ana@new.com", actualizado.getCorreo());
        assertEquals(2500, actualizado.getSalario());
        assertEquals("Marketing", actualizado.getDepartamento().getNombre());
    }


    @Test
    void actualizarEmpleadoPorId_debeLanzarExcepcionSiNoExiste() {
        Empleado nuevosDatos = new EmpleadoPermanente("Luis", "Rojas", "luis@test.com", 2000);
        assertThrows(EmpleadoNoEncontradoException.class,
                () -> gestor.actualizarEmpleadoPorId("ID_NO_EXISTE", nuevosDatos));
    }

    @Test
    void eliminarEmpleadoPorId_debeEliminarEmpleadoExistente() {
        Empleado emp = gestor.crearEmpleadoPermanente("Pedro", "López", "pedro@test.com", 1800, dpto);

        gestor.eliminarEmpleadoPorId(emp.getIdUsuario());

        assertFalse(gestor.listarTodos().contains(emp));
    }

    @Test
    void eliminarEmpleadoPorId_debeLanzarExcepcionSiNoExiste() {
        assertThrows(EmpleadoNoEncontradoException.class,
                () -> gestor.eliminarEmpleadoPorId("ID_NO_EXISTE"));
    }

    @Test
    void asignarEmpleadoADepartamento_debeAsignarCorrectamente() {
        Empleado emp = gestor.crearEmpleadoPermanente("Laura", "Martínez", "laura@test.com", 2500, dpto);

        Departamento nuevoDpto = new Departamento("Finanzas");
        gestor.asignarEmpleadoADepartamento(emp.getIdUsuario(), nuevoDpto);

        assertTrue(nuevoDpto.getEmpleados().contains(emp));
    }

    @Test
    void asignarEmpleadoADepartamento_debeLanzarExcepcionSiDepartamentoEsNulo() {
        Empleado emp = gestor.crearEmpleadoPermanente("Jose", "Mora", "jose@test.com", 2500, dpto);

        assertThrows(AsignacionInvalidaException.class,
                () -> gestor.asignarEmpleadoADepartamento(emp.getIdUsuario(), null));
    }

    @Test
    void buscarPorId_debeRetornarEmpleadoCorrecto() {
        Empleado emp = gestor.crearEmpleadoPermanente("Sofia", "Mejía", "sofia@test.com", 3000, dpto);

        Usuario encontrado = gestor.buscarPorId(emp.getIdUsuario());

        assertEquals(emp, encontrado);
    }

    @Test
    void buscarPorId_debeLanzarExcepcionSiNoExiste() {
        assertThrows(EmpleadoNoEncontradoException.class,
                () -> gestor.buscarPorId("ID_NO_EXISTE"));
    }

    @Test
    void buscarPorNombre_debeEncontrarEmpleadoPorNombre() {
        Empleado emp = gestor.crearEmpleadoPermanente("Andres", "García", "andres@test.com", 2800, dpto);

        Usuario encontrado = gestor.buscarPorNombre("Andres");

        assertEquals(emp, encontrado);
    }

    @Test
    void buscarPorNombre_debeLanzarExcepcionSiNoExiste() {
        assertThrows(EmpleadoNoEncontradoException.class,
                () -> gestor.buscarPorNombre("NombreNoExiste"));
    }

    @Test
    void asignarMetrica_debeGuardarMetricaEnEmpleado() {
        Empleado emp = gestor.crearEmpleadoPermanente("Laura", "Martínez", "laura@test.com", 2000.0, dpto);

        gestor.asignarMetrica(emp, Metrica.PRODUCTIVIDAD);


        assertTrue(emp.getMetricas().containsKey(Metrica.PRODUCTIVIDAD));
        assertEquals(8, emp.getMetricas().get(Metrica.PRODUCTIVIDAD));
    }

}