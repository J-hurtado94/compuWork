package org.compuwork.view;

import org.compuwork.controllers.GestorDepartamentos;
import org.compuwork.controllers.GestorEmpleados;

import javax.swing.*;
import java.awt.*;

public class LoginMain extends JFrame {

    private GestorEmpleados gestorEmpleados;
    private GestorDepartamentos gestorDepartamentos;

    public LoginMain(GestorEmpleados gestorEmpleados, GestorDepartamentos gestorDepartamentos) {
        this.gestorEmpleados = gestorEmpleados;
        this.gestorDepartamentos = gestorDepartamentos;

        setTitle("Seleccionar Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblLogo = new JLabel();
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon icon = new ImageIcon("src/main/java/org/compuwork/assets/images/compuwork_logo.jpeg");
        Image img = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(img));
        mainPanel.add(lblLogo);


        mainPanel.add(Box.createVerticalStrut(50));

        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        JButton btnAdminLogin = new JButton("Login Administrador");
        JButton btnEmpleadoLogin = new JButton("Login Empleado");
        buttonPanel.add(btnAdminLogin);
        buttonPanel.add(btnEmpleadoLogin);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(buttonPanel);

        add(mainPanel);

        // Acción para login de administrador
        btnAdminLogin.addActionListener(e -> {
            new LoginAdminView(gestorEmpleados, gestorDepartamentos).setVisible(true);
            dispose();
        });

        // Acción para login de empleado
        btnEmpleadoLogin.addActionListener(e -> {
            new LoginEmpleadoView(gestorEmpleados,gestorDepartamentos).setVisible(true);
            dispose();
        });
    }
}
