/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author roscr
 */
public class AltaProducto extends JFrame {
    private JTextField txtNombre, txtDescripcion, txtPrecio, txtCantidad;
    private JButton btnGuardarProducto;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4;
    private JTable tabla;
    private DefaultTableModel tableModel;

    public AltaProducto() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Alta de productos");
        setSize(600, 400);

        // Initialize components
        txtNombre = new JTextField(20);
        txtDescripcion = new JTextField(20);
        txtPrecio = new JTextField(10);
        txtCantidad = new JTextField(10);
        btnGuardarProducto = new JButton("Guardar Producto");
        jLabel1 = new JLabel("Nombre del producto");
        jLabel2 = new JLabel("Descripción");
        jLabel3 = new JLabel("Introduce el precio");
        jLabel4 = new JLabel("Cantidad");

        // Set up table
        String[] columnNames = {"Nombre", "Descripción", "Precio", "Cantidad"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Layout
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to input panel
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(jLabel1, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(jLabel2, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtDescripcion, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(jLabel3, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(jLabel4, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtCantidad, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        inputPanel.add(btnGuardarProducto, gbc);

        // Add panels to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listener
        btnGuardarProducto.addActionListener(new ActionListener() {
            private Component AltaProducto;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProductoDAO dao = new ProductoDAO();

                    String nombre = txtNombre.getText();
                    String descripcion = txtDescripcion.getText();
                    double p = Double.parseDouble(txtPrecio.getText());
                    int cant= Integer.parseInt(txtCantidad.getText());


                    Producto  producto = new Producto(nombre, descripcion, p, cant);
                    producto.setNombre(txtNombre.getText());
                    producto.setDescripcion(txtDescripcion.getText());
                    producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    producto.setCantidad(Integer.parseInt(txtCantidad.getText()));

                    if (dao.insertarProducto(producto)) {
                        javax.swing.JOptionPane.showMessageDialog(this.AltaProducto, "Producto guardado correctamente");
                        limpiarCampos();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this.AltaProducto, "Error al guardar el producto");
                    }
                } catch (Exception error) {
                    javax.swing.JOptionPane.showMessageDialog(this.AltaProducto, "Error: " + error.getMessage());
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
    }


    private void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AltaProducto().setVisible(true);
            }
        });
    }
}
