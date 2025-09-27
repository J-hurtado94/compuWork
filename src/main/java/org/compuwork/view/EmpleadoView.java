package org.compuwork.view;

import org.compuwork.models.Empleado;
import org.compuwork.models.Metrica;

import javax.swing.*;
import java.awt.*;

public class EmpleadoView extends JFrame {
    private Empleado empleado;

    public EmpleadoView(Empleado empleado) {
        this.empleado = empleado;

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

        // ✅ Acciones
        btnVerMetricas.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Métricas de " + empleado.getNombre() + ":\n\n");
            for (var entry : empleado.getMetricas().entrySet()) {
                Metrica m = entry.getKey();
                Integer valor = entry.getValue();
                sb.append(Metrica.PRODUCTIVIDAD).append(": ").append(valor).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        });

        btnSalir.addActionListener(e -> {
            dispose();
            // Podrías regresar al login
        });
    }
}
