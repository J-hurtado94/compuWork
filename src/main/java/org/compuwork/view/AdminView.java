package org.compuwork.view;

import org.compuwork.controllers.GestorDepartamentos;
import org.compuwork.controllers.GestorEmpleados;
import org.compuwork.exceptions.CreacionDepartamentoInvalido;
import org.compuwork.exceptions.EmpleadoNoEncontradoException;
import org.compuwork.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class AdminView extends JFrame {
    private final Administrador admin;
    private final GestorEmpleados gestorEmpleados;
    private final GestorDepartamentos gestorDepartamentos;

    private JTable tablaEmpleados;
    private DefaultTableModel modeloTablaEmpleados;

    public AdminView(Administrador admin, GestorEmpleados gestorEmpleados, GestorDepartamentos gestorDepartamentos) {
        this.admin = admin;
        this.gestorEmpleados = gestorEmpleados;
        this.gestorDepartamentos = gestorDepartamentos;

        setTitle("Panel Administrador - CompuWork");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // ---- Botones ----
        JPanel panelBotones = new JPanel(new GridLayout(4, 3, 10, 10));

        JButton btnCrearPermanente = new JButton("Crear Empleado Permanente");
        JButton btnCrearTemporal = new JButton("Crear Empleado Temporal");
        JButton btnActualizar = new JButton("Actualizar Empleado");
        JButton btnEliminar = new JButton("Eliminar Empleado");
        JButton btnAsignarDepto = new JButton("Asignar a Dpto");
        JButton btnListarEmpleados = new JButton("Listar Empleados");
        JButton btnGestionDepartamentos = new JButton("Gestionar Departamentos");
        JButton btnAsignarMetrica = new JButton("Asignar Métrica");
        JButton btnReportes = new JButton("Reportes");
        JButton btnVolverLogin = new JButton("Volver a Login");


        panelBotones.add(btnCrearPermanente);
        panelBotones.add(btnCrearTemporal);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnAsignarDepto);
        panelBotones.add(btnListarEmpleados);
        panelBotones.add(btnGestionDepartamentos);
        panelBotones.add(btnAsignarMetrica);
        panelBotones.add(btnReportes);
        panelBotones.add(btnVolverLogin);

        // ---- Tabla empleados ----
        String[] columnas = {"ID", "Nombre", "Apellido", "Correo", "Salario", "Tipo", "Departamento"};
        modeloTablaEmpleados = new DefaultTableModel(columnas, 0);
        tablaEmpleados = new JTable(modeloTablaEmpleados);

        mainPanel.add(new JScrollPane(tablaEmpleados), BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.SOUTH);

        add(mainPanel);

        // ================== ACCIONES ==================

        // Crear Permanente
        btnCrearPermanente.addActionListener(e -> {
            try {
                String nombre = JOptionPane.showInputDialog(this, "Nombre:");
                String apellido = JOptionPane.showInputDialog(this, "Apellido:");
                String correo = JOptionPane.showInputDialog(this, "Correo:");
                double salario = Double.parseDouble(JOptionPane.showInputDialog(this, "Salario:"));

                Empleado nuevo = gestorEmpleados.crearEmpleadoPermanente(nombre, apellido, correo, salario, null);
                mostrarIdCopiable(nuevo.getIdUsuario());
                refrescarTablaEmpleados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // Crear Temporal
        btnCrearTemporal.addActionListener(e -> {
            try {
                String nombre = JOptionPane.showInputDialog(this, "Nombre:");
                String apellido = JOptionPane.showInputDialog(this, "Apellido:");
                String correo = JOptionPane.showInputDialog(this, "Correo:");
                double salario = Double.parseDouble(JOptionPane.showInputDialog(this, "Salario:"));
                String fechaStr = JOptionPane.showInputDialog(this, "Fecha fin contrato (YYYY-MM-DD):");
                LocalDate fechaFin = LocalDate.parse(fechaStr);

                Empleado nuevo = gestorEmpleados.crearEmpleadoTemporal(nombre, apellido, correo, salario, fechaFin, null);
                mostrarIdCopiable(nuevo.getIdUsuario());
                refrescarTablaEmpleados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // Actualizar empleado (modificar todos los campos menos ID)
        btnActualizar.addActionListener(e -> {
            int fila = tablaEmpleados.getSelectedRow();
            if (fila >= 0) {
                String id = (String) modeloTablaEmpleados.getValueAt(fila, 0);
                Empleado emp = (Empleado) gestorEmpleados.buscarPorId(id);

                JTextField txtNombre = new JTextField(emp.getNombre());
                JTextField txtApellido = new JTextField(emp.getApellido());
                JTextField txtCorreo = new JTextField(emp.getCorreo());
                JTextField txtSalario = new JTextField(String.valueOf(emp.getSalario()));

                JPanel form = new JPanel(new GridLayout(0, 2));
                form.add(new JLabel("ID (no editable):"));
                form.add(new JLabel(emp.getIdUsuario()));
                form.add(new JLabel("Nombre:")); form.add(txtNombre);
                form.add(new JLabel("Apellido:")); form.add(txtApellido);
                form.add(new JLabel("Correo:")); form.add(txtCorreo);
                form.add(new JLabel("Salario:")); form.add(txtSalario);

                int result = JOptionPane.showConfirmDialog(this, form, "Modificar empleado", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    emp.setNombre(txtNombre.getText());
                    emp.setApellido(txtApellido.getText());
                    emp.setCorreo(txtCorreo.getText());
                    emp.setSalario(Double.parseDouble(txtSalario.getText()));
                    refrescarTablaEmpleados();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado.");
            }
        });

        // Eliminar
        btnEliminar.addActionListener(e -> {
            int fila = tablaEmpleados.getSelectedRow();
            if (fila >= 0) {
                String id = (String) modeloTablaEmpleados.getValueAt(fila, 0);
                gestorEmpleados.eliminarEmpleadoPorId(id);
                refrescarTablaEmpleados();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado.");
            }
        });

        // Asignar a departamento
        btnAsignarDepto.addActionListener(e -> {
            int fila = tablaEmpleados.getSelectedRow();
            if (fila >= 0) {
                String id = (String) modeloTablaEmpleados.getValueAt(fila, 0);
                String nombreDepto = JOptionPane.showInputDialog(this, "Nombre del departamento:");
                Departamento d;
                try {
                    d = gestorDepartamentos.buscarODefault(nombreDepto);
                } catch (Exception ex) {
                    d = gestorDepartamentos.crearDepartamento(nombreDepto);
                }
                gestorEmpleados.asignarEmpleadoADepartamento(id, d);
                refrescarTablaEmpleados();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado.");
            }
        });

        // Listar empleados
        btnListarEmpleados.addActionListener(e -> refrescarTablaEmpleados());

        // Gestionar departamentos
        btnGestionDepartamentos.addActionListener(e -> {
            String[] opciones = {"Crear", "Eliminar", "Listar"};
            String opcion = (String) JOptionPane.showInputDialog(this, "Seleccione acción", "Departamentos",
                    JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if ("Crear".equals(opcion)) {
                String nombre = JOptionPane.showInputDialog(this, "Nombre del nuevo departamento:");
                gestorDepartamentos.crearDepartamento(nombre);
            } else if ("Eliminar".equals(opcion)) {
                String nombre = JOptionPane.showInputDialog(this, "Nombre del departamento a eliminar:");
                gestorDepartamentos.eliminarDepartamento(nombre);
            } else if ("Listar".equals(opcion)) {
                StringBuilder sb = new StringBuilder("Departamentos:\n");
                for (Departamento d : gestorDepartamentos.listarTodos()) {
                    sb.append("- ").append(d.getNombre()).append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString());
            }
        });

        // Asignar Métrica
        btnAsignarMetrica.addActionListener(e -> {
            int fila = tablaEmpleados.getSelectedRow();
            if (fila >= 0) {
                String id = (String) modeloTablaEmpleados.getValueAt(fila, 0);
                Empleado emp = (Empleado) gestorEmpleados.buscarPorId(id);

                Metrica[] metricas = Metrica.values();
                Metrica seleccionada = (Metrica) JOptionPane.showInputDialog(
                        this,
                        "Seleccione una métrica:" +
                                "Se califica de 1 a 10",
                        "Asignar Métrica",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        metricas,
                        metricas[0]
                );

                if (seleccionada != null) {
                    String valorStr = JOptionPane.showInputDialog(this, "Ingrese valor numérico para " + seleccionada + ":");
                    try {
                        int valor = Integer.parseInt(valorStr);
                        gestorEmpleados.asignarMetrica(emp, seleccionada, valor);
                        JOptionPane.showMessageDialog(this, "Métrica asignada correctamente.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "El valor debe ser un número entero.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado primero.");
            }
        });

        // Reportes
        btnReportes.addActionListener(e -> {
            int fila = tablaEmpleados.getSelectedRow();
            if (fila >= 0) {
                String id = (String) modeloTablaEmpleados.getValueAt(fila, 0);
                Empleado emp = (Empleado) gestorEmpleados.buscarPorId(id);
                StringBuilder sb = new StringBuilder("Reporte de métricas de " + emp.getNombre() + ":\n\n");
                if (emp.getMetricas().isEmpty()) {
                    sb.append("No tiene métricas asignadas.");
                } else {
                    emp.getMetricas().forEach((metrica, valor) ->
                            sb.append(metrica.name()).append(": ").append(valor).append("\n"));
                }
                JOptionPane.showMessageDialog(this, sb.toString(), "Reporte de Desempeño", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado.");
            }
        });

        btnVolverLogin.addActionListener(e -> {

            this.dispose();
            LoginMain login = new LoginMain(gestorEmpleados,gestorDepartamentos);
            login.setVisible(true);
        });
    }



    private void refrescarTablaEmpleados() {
        modeloTablaEmpleados.setRowCount(0);
        for (Usuario u : gestorEmpleados.listarTodos()) {
            if (u instanceof Empleado emp) {
                modeloTablaEmpleados.addRow(new Object[]{
                        emp.getIdUsuario(),
                        emp.getNombre(),
                        emp.getApellido(),
                        emp.getCorreo(),
                        emp.getSalario(),
                        emp.tipoEmpleado(),
                        emp.getDepartamento() != null ? emp.getDepartamento().getNombre() : "N/A"
                });
            }
        }
    }

    private void mostrarIdCopiable(String id) {
        JTextField txtId = new JTextField(id);
        txtId.setEditable(false);
        txtId.setFocusable(true);
        txtId.selectAll();
        JOptionPane.showMessageDialog(this, txtId,
                "ID del Empleado Creado (Ctrl+C para copiar)",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
