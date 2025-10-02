package org.compuwork;

import org.compuwork.controllers.GestorDepartamentos;
import org.compuwork.controllers.GestorEmpleados;
import org.compuwork.models.*;
import org.compuwork.view.AdminView;
import org.compuwork.view.LoginAdminView;
import org.compuwork.view.LoginMain;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            GestorDepartamentos gestorDepartamentos = new GestorDepartamentos();
            GestorEmpleados gestorEmpleados = new GestorEmpleados();

            // administrador de prueba
            Administrador admin = new Administrador("admin");
            gestorEmpleados.agregarUsuario(admin);

            // LoginMain
            LoginMain loginMain = new LoginMain(gestorEmpleados, gestorDepartamentos);
            loginMain.setVisible(true);
        });
    }

    }

