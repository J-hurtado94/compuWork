package org.compuwork.view;

import org.compuwork.controllers.GestorDepartamentos;
import org.compuwork.controllers.GestorEmpleados;
import org.compuwork.exceptions.EmpleadoNoEncontradoException;
import org.compuwork.models.Administrador;
import org.compuwork.models.Empleado;
import org.compuwork.models.Rol;
import org.compuwork.models.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginAdminView extends JFrame {
    private GestorEmpleados gestorEmpleados;
    private GestorDepartamentos gestorDepartamentos;
    private JTextField txtNombre;
    private JButton btnLogin;
    private JButton btnVolver;

    public LoginAdminView(GestorEmpleados gestorEmpleados, GestorDepartamentos gestorDepartamentos) {
        this.gestorEmpleados = gestorEmpleados;
        this.gestorDepartamentos = gestorDepartamentos;

        setTitle("Login Administrador");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        btnLogin = new JButton("Ingresar");
        panel.add(new JLabel());
        panel.add(btnLogin);

        btnVolver = new JButton("Volver al Menú");
        panel.add(new JLabel());
        panel.add(btnVolver);

        add(panel);

        btnLogin.addActionListener(e -> login());
        btnVolver.addActionListener(e -> {
            new LoginMain(gestorEmpleados, gestorDepartamentos).setVisible(true);
            dispose();
        });
    }

    private void login() {
        String nombre = txtNombre.getText().trim();
        try {
            Usuario usuario = gestorEmpleados.buscarPorNombre(nombre);
            if (usuario.getRol() == Rol.ADMIN) {
                ImageIcon icon = new ImageIcon("src/main/java/org/compuwork/assets/icons/icons8-configuración-del-administrador-48.png");
                JOptionPane.showMessageDialog(this, "Bienvenido Administrador","Aviso",JOptionPane.INFORMATION_MESSAGE,icon);
                new AdminView((Administrador) usuario, gestorEmpleados, gestorDepartamentos).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Este usuario no es administrador");
            }
        } catch (EmpleadoNoEncontradoException ex) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado: " + nombre);
        }
    }
}
