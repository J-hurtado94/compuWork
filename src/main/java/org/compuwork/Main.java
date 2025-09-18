package org.compuwork;

import org.compuwork.controllers.GestorDepartamentos;
import org.compuwork.controllers.GestorEmpleados;
import org.compuwork.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.compuwork.utils.BeneficiosEmpleadosPermanentes.BENEFICIOS_EMPLEADOS_PERMANENTES;

public class Main {
    public static void main(String[] args) {
        //Se crea gestor de departamentos
        GestorDepartamentos gestorDepartamentos = new GestorDepartamentos();
        // Se crea gestor de empleados
        GestorEmpleados gestorEmpleados = new GestorEmpleados();

        //se crean los departamentos por medio del gestor de departamentos
        Departamento dev = gestorDepartamentos.crearDepartamento("Desarrollo");
        Departamento rrhh = gestorDepartamentos.crearDepartamento("Recursos Humanos");

        //Se crean los objetos de los empleados, permanente y temporal con sus atributos
        Empleado e1 = new EmpleadoPermanente("Juan", "Perez", "juan@c.com", 3500,BENEFICIOS_EMPLEADOS_PERMANENTES);
        Empleado e2 = new EmpleadoTemporal("Ana", "Gomez", "ana@c.com", 1500, LocalDate.of(2025,12,31));
        Empleado e3 = new EmpleadoPermanente("Carlos", "Lopez", "carlos@c.com", 4200,BENEFICIOS_EMPLEADOS_PERMANENTES);

        // Se crean los empleados por medio del gestor de empleados
     gestorEmpleados.crearEmpleado(e1);
     gestorEmpleados.crearEmpleado(e2);
     gestorEmpleados.crearEmpleado(e3);

        // Se realizan las asignacion a los departamentos
     gestorEmpleados.asignarEmpleadoADepartamento(e1.getIdEmpleado(), dev);
     gestorEmpleados.asignarEmpleadoADepartamento(e2.getIdEmpleado(), dev);
     gestorEmpleados.asignarEmpleadoADepartamento(e3.getIdEmpleado(), rrhh);

        // Se cargan metricas ya predefinidas a los empleados
        gestorEmpleados.asignarMetrica(e1,Metrica.PRODUCTIVIDAD, Metrica.PRODUCTIVIDAD.getValorMetrica());
        gestorEmpleados.asignarMetrica(e1,Metrica.PUNTUALIDAD, Metrica.PUNTUALIDAD.getValorMetrica());

        gestorEmpleados.asignarMetrica(e2,Metrica.PRODUCTIVIDAD, Metrica.PRODUCTIVIDAD.getValorMetrica());
        gestorEmpleados.asignarMetrica(e2,Metrica.PUNTUALIDAD, Metrica.PUNTUALIDAD.getValorMetrica());

        gestorEmpleados.asignarMetrica(e3,Metrica.PRODUCTIVIDAD, Metrica.PRODUCTIVIDAD.getValorMetrica());
        gestorEmpleados.asignarMetrica(e3,Metrica.PUNTUALIDAD, Metrica.PUNTUALIDAD.getValorMetrica());

        // reporte individual
        ReporteEmpleado reporteEmpleadoUno = new ReporteEmpleado(e1);
        System.out.println(reporteEmpleadoUno.generarReporte());

        ReporteEmpleado reporteEmpleadoDos = new ReporteEmpleado(e2);
        System.out.println(reporteEmpleadoDos.generarReporte());

        ReporteEmpleado reporteEmpleadoTres = new ReporteEmpleado(e3);
        System.out.println(reporteEmpleadoTres.generarReporte());

        // reporte por departamento
        ReporteDepartamento reportePorDepartamentoDev = new ReporteDepartamento(dev);
        System.out.println(reportePorDepartamentoDev.generarReporte());

        ReporteDepartamento reportePorDepartamentoRh = new ReporteDepartamento(rrhh);
        System.out.println(reportePorDepartamentoRh.generarReporte());





    }
}