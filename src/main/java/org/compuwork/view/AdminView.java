package org.compuwork.view;

import org.compuwork.controllers.GestorEmpleados;
import org.compuwork.models.Administrador;

import javax.swing.*;
import java.awt.*;

public class AdminView extends JFrame {
    private Administrador admin;
    private GestorEmpleados gestor;

    public AdminView(Administrador admin, GestorEmpleados gestor) {
        this.admin = admin;
        this.gestor = gestor;

        setTitle("Panel Administrador - " + admin.getNombre());
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnCrearEmpleado = new JButton("Crear Empleado");
        JButton btnListarUsuarios = new JButton("Listar Usuarios");
        JButton btnSalir = new JButton("Salir");

        panel.add(btnCrearEmpleado);
        panel.add(btnListarUsuarios);
        panel.add(btnSalir);

        add(panel);

        // ✅ Acciones
        btnCrearEmpleado.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Aquí iría la lógica para crear empleados");
        });

        btnListarUsuarios.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Usuarios registrados:\n\n");
            for (var u : gestor.listarTodos()) {
                sb.append(u.getIdUsuario())
                        .append(" - ")
                        .append(u.getNombre())
                        .append(" [").append(u.getRol()).append("]\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        });

        btnSalir.addActionListener(e -> {
            dispose();
            new LoginMain(gestor).setVisible(true);
        });
    }
}
