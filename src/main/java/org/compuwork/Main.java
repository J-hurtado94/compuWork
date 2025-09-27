package org.compuwork;

import org.compuwork.controllers.GestorEmpleados;
import org.compuwork.models.*;
import org.compuwork.view.LoginAdminView;
import org.compuwork.view.LoginMain;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        GestorEmpleados gestor = new GestorEmpleados();

        // Crear administrador inicial
        Administrador admin = new Administrador("admin");
        gestor.agregarUsuario(admin);

        SwingUtilities.invokeLater(() -> new LoginMain(gestor).setVisible(true));
    }



}