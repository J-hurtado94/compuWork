package org.compuwork.view;

import org.compuwork.controllers.GestorEmpleados;
import org.compuwork.exceptions.EmpleadoNoEncontradoException;
import org.compuwork.models.Administrador;
import org.compuwork.models.Empleado;
import org.compuwork.models.Rol;
import org.compuwork.models.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginAdminView extends JFrame {
    private GestorEmpleados gestor;
    private JTextField txtNombre;
    private JButton btnLogin;

    public LoginAdminView(GestorEmpleados gestor) {
        this.gestor = gestor;

        setTitle("Login Administrador");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        btnLogin = new JButton("Ingresar");
        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(e -> login());
    }

    private void login() {
        String nombre = txtNombre.getText().trim();
        try {
            Usuario usuario = gestor.buscarPorNombre(nombre);
            if (usuario.getRol() == Rol.ADMIN) {
                JOptionPane.showMessageDialog(this, "Bienvenido Administrador");
                new AdminView((Administrador) usuario, gestor).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Este usuario no es administrador");
            }
        } catch (EmpleadoNoEncontradoException ex) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado: " + nombre);
        }
    }
}
