package view.Mantenimientos;

import dataAcces.model.Detalle_Pedido;
import dataAcces.model.Pedido;
import dataAcces.model.Producto;
import dataAcces.repository.Detalle_pedido_Repository;
import dataAcces.repository.Pedido_Repository;
import dataAcces.repository.Producto_Repository;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import view.InterfazPrincipal;
import javax.swing.JOptionPane;

public class MantenimientoPedidos extends javax.swing.JPanel {

    private Pedido_Repository PedidoRep = new Pedido_Repository();
    private Detalle_pedido_Repository PedidoDEtalleRep = new Detalle_pedido_Repository();
    private Producto_Repository ProductoRep = new Producto_Repository();

    private DefaultTableModel modeloPedidos;
    private DefaultTableModel ModeloDetalle;

    private List<Pedido> listapedido;
    private List<Detalle_Pedido> detallesActuales = new ArrayList<>();

    // UI variables
    private javax.swing.JButton BotonActualizar;
    private javax.swing.JButton BotonBuscarId;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonInsertar;
    private javax.swing.JButton BotonModificar;
    private javax.swing.JButton BotonAgregarDetalle;

    private javax.swing.JTextField Buscarprecio;
    private javax.swing.JTextField ClienteIdTxt;
    private javax.swing.JTextField EmpleadoIdTxt;

    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<Producto> ProductoCombo;
    private javax.swing.JSpinner CantidadSpinner;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;

    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;

    public MantenimientoPedidos(InterfazPrincipal ventana) {
        modeloPedidos = new DefaultTableModel();
        modeloPedidos.addColumn("ID_Pedido");
        modeloPedidos.addColumn("Fecha");
        modeloPedidos.addColumn("Estado");
        modeloPedidos.addColumn("Total");
        modeloPedidos.addColumn("Cliente_ID");
        modeloPedidos.addColumn("Empleado_ID");

        ModeloDetalle = new DefaultTableModel();
        ModeloDetalle.addColumn("ID");
        ModeloDetalle.addColumn("Cantidad");
        ModeloDetalle.addColumn("Subtotal");
        ModeloDetalle.addColumn("Pedidos_ID");
        ModeloDetalle.addColumn("Productos_ID");

        initComponents();
        cargarProductos();
        cargarTabla();
        setVisible(true);
    }

    private void cargarProductos() {
        ProductoCombo.removeAllItems();
        List<Producto> productos = ProductoRep.GetAll();
        for (Producto p : productos) {
            ProductoCombo.addItem(p);
        }
    }

    public void cargarTabla() {
        modeloPedidos.setRowCount(0);
        listapedido = PedidoRep.GetAll();
        for (Pedido pedido : listapedido) {
            Object[] fila = new Object[6];
            fila[0] = pedido.getID();
            fila[1] = pedido.getFecha();
            fila[2] = pedido.getEstado();
            fila[3] = pedido.getTotal();
            fila[4] = pedido.getCliente_ID();
            fila[5] = pedido.getEmpleado_ID();
            modeloPedidos.addRow(fila);
        }
        jTable1.setModel(modeloPedidos);
    }

    private void actualizarTablaDetalles() {
        ModeloDetalle.setRowCount(0);
        for (Detalle_Pedido detalle : detallesActuales) {
            Object[] fila = new Object[5];
            fila[0] = detalle.getID();
            fila[1] = detalle.getCantidad();
            fila[2] = detalle.getSubtotal();
            fila[3] = detalle.getPedidos_ID();
            fila[4] = detalle.getProductos_ID();
            ModeloDetalle.addRow(fila);
        }
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1 = new javax.swing.JLabel("ID Cliente:");
        jLabel2 = new javax.swing.JLabel("ID Empleado:");
        jLabel3 = new javax.swing.JLabel("Estado:");

        ClienteIdTxt = new javax.swing.JTextField(10);
        EmpleadoIdTxt = new javax.swing.JTextField(10);
        jComboBox1 = new javax.swing.JComboBox<>(new String[] { "Pendiente", "Completado" });

        BotonInsertar = new javax.swing.JButton("Insertar Pedido");
        BotonModificar = new javax.swing.JButton("Modificar");
        BotonEliminar = new javax.swing.JButton("Eliminar");
        BotonActualizar = new javax.swing.JButton("Actualizar");

        jLabel4 = new javax.swing.JLabel("Detalles del Pedido (Productos):");
        ProductoCombo = new javax.swing.JComboBox<>();
        CantidadSpinner = new javax.swing.JSpinner(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        BotonAgregarDetalle = new javax.swing.JButton("Agregar Producto");

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jLabel5 = new javax.swing.JLabel("Buscar por ID:");
        Buscarprecio = new javax.swing.JTextField(10);
        BotonBuscarId = new javax.swing.JButton("Buscar");

        jTable1.setModel(modeloPedidos);
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(ModeloDetalle);
        jScrollPane2.setViewportView(jTable2);

        // Action Listeners
        BotonInsertar.addActionListener(evt -> BotonInsertarActionPerformed(evt));
        BotonModificar.addActionListener(evt -> BotonModificarActionPerformed(evt));
        BotonEliminar.addActionListener(evt -> BotonEliminarActionPerformed(evt));
        BotonActualizar.addActionListener(evt -> BotonActualizarActionPerformed(evt));
        BotonBuscarId.addActionListener(evt -> BotonBuscarIdActionPerformed(evt));

        // ADD PRODUCT ACTION
        BotonAgregarDetalle.addActionListener(evt -> {
            Producto p = (Producto) ProductoCombo.getSelectedItem();
            if (p != null) {
                int cantidad = (int) CantidadSpinner.getValue();
                double subtotal = cantidad * p.getPrecio();
                Detalle_Pedido dp = new Detalle_Pedido(0, (double) cantidad, subtotal, 0, p.getID());
                detallesActuales.add(dp);
                actualizarTablaDetalles();
            }
        });

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.getSelectedRow();
                if (row >= 0) {
                    ClienteIdTxt.setText(modeloPedidos.getValueAt(row, 4).toString());
                    EmpleadoIdTxt.setText(modeloPedidos.getValueAt(row, 5).toString());
                    String status = modeloPedidos.getValueAt(row, 2).toString();
                    jComboBox1.setSelectedItem(status);

                    int pedidoId = Integer.parseInt(modeloPedidos.getValueAt(row, 0).toString());

                    // Fetch existing details for this Pedido
                    detallesActuales.clear();
                    List<Detalle_Pedido> todos = PedidoDEtalleRep.GetAll();
                    for (Detalle_Pedido d : todos) {
                        if (d.getPedidos_ID() == pedidoId) {
                            detallesActuales.add(d);
                        }
                    }
                    actualizarTablaDetalles();
                }
            }
        });

        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400,
                                                Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Buscarprecio, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BotonBuscarId)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(ClienteIdTxt)
                                                        .addComponent(EmpleadoIdTxt)
                                                        .addComponent(jComboBox1, 0,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(jLabel4)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(ProductoCombo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(CantidadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BotonAgregarDetalle, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(BotonInsertar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BotonModificar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BotonEliminar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BotonActualizar)))
                                .addContainerGap()));

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(ClienteIdTxt,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(EmpleadoIdTxt,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jComboBox1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(ProductoCombo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(CantidadSpinner,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(BotonAgregarDetalle))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 200,
                                                        Short.MAX_VALUE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0,
                                                Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(Buscarprecio, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BotonBuscarId)
                                        .addComponent(BotonInsertar)
                                        .addComponent(BotonModificar)
                                        .addComponent(BotonEliminar)
                                        .addComponent(BotonActualizar))
                                .addContainerGap()));
    }

    private void BotonInsertarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int clienteId = Integer.parseInt(ClienteIdTxt.getText());
            int empId = Integer.parseInt(EmpleadoIdTxt.getText());
            dataAcces.model.Estado_Pedido estado = dataAcces.model.Estado_Pedido
                    .valueOf(jComboBox1.getSelectedItem().toString());

            // Calc Total
            double total = 0.0;
            for (Detalle_Pedido d : detallesActuales) {
                total += d.getSubtotal();
            }

            Pedido p = new Pedido(0, new java.util.Date(), estado, total, clienteId, empId);
            PedidoRep.Insert(p);

            JOptionPane.showMessageDialog(this,
                    "Pedido insertado. Asegurese de guardar los detalles si implementa el guardado multiple.");

            detallesActuales.clear();
            actualizarTablaDetalles();
            cargarTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Verifique los datos de entrada.");
        }
    }

    private void BotonModificarActionPerformed(java.awt.event.ActionEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row < 0)
            return;
        try {
            int id = Integer.parseInt(modeloPedidos.getValueAt(row, 0).toString());
            int clienteId = Integer.parseInt(ClienteIdTxt.getText());
            int empId = Integer.parseInt(EmpleadoIdTxt.getText());
            dataAcces.model.Estado_Pedido estado = dataAcces.model.Estado_Pedido
                    .valueOf(jComboBox1.getSelectedItem().toString());

            double total = Double.parseDouble(modeloPedidos.getValueAt(row, 3).toString());

            Pedido p = new Pedido(id, new java.util.Date(), estado, total, clienteId, empId);
            PedidoRep.Update(p);
            cargarTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar.");
        }
    }

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row < 0)
            return;
        int id = Integer.parseInt(modeloPedidos.getValueAt(row, 0).toString());
        PedidoRep.Delete(id);
        cargarTabla();
    }

    private void BotonActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        cargarTabla();
    }

    private void BotonBuscarIdActionPerformed(java.awt.event.ActionEvent evt) {
        String textoBusqueda = Buscarprecio.getText().trim();

        if (textoBusqueda.isEmpty()) {
            cargarTabla();
            return;
        }

        try {
            int idPedido = Integer.parseInt(textoBusqueda);
            Pedido pedido = PedidoRep.GetById(idPedido);

            modeloPedidos.setRowCount(0);

            if (pedido != null) {
                Object[] fila = new Object[6];
                fila[0] = pedido.getID();
                fila[1] = pedido.getFecha();
                fila[2] = pedido.getEstado();
                fila[3] = pedido.getTotal();
                fila[4] = pedido.getCliente_ID();
                fila[5] = pedido.getEmpleado_ID();
                modeloPedidos.addRow(fila);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontro un pedido con ese ID.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID numerico valido.");
        }
    }
}
