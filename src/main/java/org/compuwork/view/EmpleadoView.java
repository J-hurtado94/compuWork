package org.compuwork.view;

import org.compuwork.controllers.GestorDepartamentos;
import org.compuwork.controllers.GestorEmpleados;
import org.compuwork.models.Empleado;
import org.compuwork.models.Metrica;

import javax.swing.*;
import java.awt.*;

public class EmpleadoView extends JFrame {
    private Empleado empleado;
    private GestorEmpleados gestorEmpleados;
    private GestorDepartamentos gestorDepartamentos;

    public EmpleadoView(Empleado empleado, GestorEmpleados gestorEmpleados, GestorDepartamentos gestorDepartamentos) {
        this.empleado = empleado;
        this.gestorEmpleados = gestorEmpleados;
        this.gestorDepartamentos = gestorDepartamentos;

        setTitle("Panel Empleado - " + empleado.getNombre());
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnVerMetricas = new JButton("Ver Mis Métricas");
        JButton btnSalir = new JButton("Salir");

        panel.add(btnVerMetricas);
        panel.add(btnSalir);

        add(panel);

        // Acción para ver métricas
        btnVerMetricas.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Métricas de " + empleado.getNombre() + ":\n\n");
            for (var entry : empleado.getMetricas().entrySet()) {
                Metrica m = entry.getKey();
                Integer valor = entry.getValue();
                sb.append(m).append(": ").append(valor).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        });

        btnSalir.addActionListener(e -> {
            this.dispose();
            new LoginMain(gestorEmpleados, gestorDepartamentos).setVisible(true);
        });
    }
}
