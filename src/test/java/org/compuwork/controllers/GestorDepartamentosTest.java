package org.compuwork.controllers;

import org.compuwork.exceptions.CreacionDepartamentoInvalido;
import org.compuwork.exceptions.DepartamentoNoEncontradoException;
import org.compuwork.models.Departamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorDepartamentosTest {
    private GestorDepartamentos gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorDepartamentos();
    }

    @Test
    void crearDepartamento_exitoso() {
        Departamento d = gestor.crearDepartamento("Recursos Humanos");

        assertNotNull(d.getId());
        assertEquals("Recursos Humanos", d.getNombre());
        assertEquals(1, gestor.listarTodos().size());
    }

    @Test
    void crearDepartamento_duplicadoDebeFallar() {
        gestor.crearDepartamento("IT");

        assertThrows(CreacionDepartamentoInvalido.class, () -> {
            gestor.crearDepartamento("IT");
        });
    }

    @Test
    void modificarNombre_existenteDebeActualizar() {
        Departamento d = gestor.crearDepartamento("Ventas");

        boolean resultado = gestor.modificarNombre(d.getId(), "Comercial");

        assertTrue(resultado);
        assertEquals("Comercial", gestor.buscarPorId(d.getId()).getNombre());
    }

    @Test
    void modificarNombre_inexistenteDebeRetornarFalse() {
        boolean resultado = gestor.modificarNombre("id-falso", "Otro");
        assertFalse(resultado);
    }

    @Test
    void eliminarDepartamento_existenteDebeEliminar() {
        Departamento d = gestor.crearDepartamento("Soporte");

        boolean resultado = gestor.eliminarDepartamento(d.getId());

        assertTrue(resultado);
        assertTrue(gestor.listarTodos().isEmpty());
    }

    @Test
    void eliminarDepartamento_inexistenteDebeRetornarFalse() {
        boolean resultado = gestor.eliminarDepartamento("id-falso");
        assertFalse(resultado);
    }

    @Test
    void buscarPorId_existenteDebeRetornarDepartamento() {
        Departamento d = gestor.crearDepartamento("Marketing");

        Departamento encontrado = gestor.buscarPorId(d.getId());

        assertEquals(d.getId(), encontrado.getId());
    }

    @Test
    void buscarPorId_inexistenteDebeLanzarExcepcion() {
        assertThrows(DepartamentoNoEncontradoException.class, () -> {
            gestor.buscarPorId("id-falso");
        });
    }

    @Test
    void listarTodos_debeRetornarListaConDepartamentos() {
        gestor.crearDepartamento("Logística");
        gestor.crearDepartamento("Compras");

        List<Departamento> lista = gestor.listarTodos();

        assertEquals(2, lista.size());
    }

    @Test
    void buscarODefault_nombreExistenteDebeRetornarEseDepartamento() {
        gestor.crearDepartamento("Calidad");

        Departamento d = gestor.buscarODefault("Calidad");

        assertEquals("Calidad", d.getNombre());
    }

    @Test
    void buscarODefault_nombreNuloDebeCrearGeneral() {
        Departamento d = gestor.buscarODefault(null);

        assertEquals("General", d.getNombre());
        assertEquals(1, gestor.listarTodos().size());
    }

    @Test
    void buscarODefault_nombreVacioDebeCrearGeneral() {
        Departamento d = gestor.buscarODefault("   ");

        assertEquals("General", d.getNombre());
    }

}