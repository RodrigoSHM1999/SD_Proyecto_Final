/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.Combo;
import Modelo.Config;
import Modelo.Detalle;
import Modelo.Eventos;
import Modelo.LoginDAO;
import Modelo.Productos;
import Modelo.ProductosDao;
import Modelo.Proveedor;
import Modelo.ProveedorDao;
import Modelo.Venta;
import Modelo.VentaDao;
import Modelo.login;
import Reportes.Grafico;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public final class Sistema extends javax.swing.JFrame {
    Date fechaVenta = new Date();
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);
    Cliente cl = new Cliente();
    ClienteDao client = new ClienteDao();
    Proveedor pr = new Proveedor();
    ProveedorDao PrDao = new ProveedorDao();
    Productos pro = new Productos();
    ProductosDao proDao = new ProductosDao();
    Venta v = new Venta();
    VentaDao Vdao = new VentaDao();
    Detalle Dv = new Detalle();
    Config conf = new Config();
    Eventos event = new Eventos();
    login lg = new login();
    LoginDAO login = new LoginDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    double Totalpagar = 0.00;
    
    int xMouse, yMouse;
    

    public Sistema() {
        initComponents();
    }
    public Sistema (login priv){
        initComponents();
        this.setLocationRelativeTo(null);
        Midate.setDate(fechaVenta);
        txtIdCliente.setVisible(false);
        txtIdVenta.setVisible(false);
        txtIdPro.setVisible(false);
        txtIdproducto.setVisible(false);
        txtIdProveedor.setVisible(false);
        txtIdConfig.setVisible(false);
        txtIdCV.setVisible(false);
        ListarConfig();
        if (priv.getRol().equals("Asistente")) {
            btnProductos.setEnabled(false);
            btnProveedor.setEnabled(false);
            LabelVendedor.setText(priv.getNombre());
        }else{
            LabelVendedor.setText(priv.getNombre());
        }
    }
    public void ListarCliente() {
        List<Cliente> ListarCl = client.ListarCliente();
        modelo = (DefaultTableModel) TableCliente.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getId();
            ob[1] = ListarCl.get(i).getDni();
            ob[2] = ListarCl.get(i).getNombre();
            ob[3] = ListarCl.get(i).getTelefono();
            ob[4] = ListarCl.get(i).getDireccion();
            modelo.addRow(ob);
        }
        TableCliente.setModel(modelo);

    }

    public void ListarProveedor() {
        List<Proveedor> ListarPr = PrDao.ListarProveedor();
        modelo = (DefaultTableModel) TableProveedor.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getId();
            ob[1] = ListarPr.get(i).getRuc();
            ob[2] = ListarPr.get(i).getNombre();
            ob[3] = ListarPr.get(i).getTelefono();
            ob[4] = ListarPr.get(i).getDireccion();
            modelo.addRow(ob);
        }
        TableProveedor.setModel(modelo);

    }
    public void ListarUsuarios() {
        List<login> Listar = login.ListarUsuarios();
        modelo = (DefaultTableModel) TableUsuarios.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < Listar.size(); i++) {
            ob[0] = Listar.get(i).getId();
            ob[1] = Listar.get(i).getNombre();
            ob[2] = Listar.get(i).getCorreo();
            ob[3] = Listar.get(i).getRol();
            modelo.addRow(ob);
        }
        TableUsuarios.setModel(modelo);

    }
    public void ListarProductos() {
        List<Productos> ListarPro = proDao.ListarProductos();
        modelo = (DefaultTableModel) TableProducto.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getId();
            ob[1] = ListarPro.get(i).getCodigo();
            ob[2] = ListarPro.get(i).getNombre();
            ob[3] = ListarPro.get(i).getProveedorPro();
            ob[4] = ListarPro.get(i).getStock();
            ob[5] = ListarPro.get(i).getPrecio();
            modelo.addRow(ob);
        }
        TableProducto.setModel(modelo);

    }

    public void ListarConfig() {
        conf = proDao.BuscarDatos();
        txtIdConfig.setText("" + conf.getId());
        txtRucConfig.setText("" + conf.getRuc());
        txtNombreConfig.setText("" + conf.getNombre());
        txtTelefonoConfig.setText("" + conf.getTelefono());
        txtDireccionConfig.setText("" + conf.getDireccion());
        txtMensaje.setText("" + conf.getMensaje());
    }

    public void ListarVentas() {
        List<Venta> ListarVenta = Vdao.Listarventas();
        modelo = (DefaultTableModel) TableVentas.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListarVenta.size(); i++) {
            ob[0] = ListarVenta.get(i).getId();
            ob[1] = ListarVenta.get(i).getNombre_cli();
            ob[2] = ListarVenta.get(i).getVendedor();
            ob[3] = ListarVenta.get(i).getTotal();
            modelo.addRow(ob);
        }
        TableVentas.setModel(modelo);

    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevaVenta = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        LabelVendedor = new javax.swing.JLabel();
        tipo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoVenta = new javax.swing.JTextField();
        txtDescripcionVenta = new javax.swing.JTextField();
        txtCantidadVenta = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        txtStockDisponible = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableVenta = new javax.swing.JTable();
        btnEliminarventa = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtRucVenta = new javax.swing.JTextField();
        txtNombreClienteventa = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        LabelTotal = new javax.swing.JLabel();
        txtIdCV = new javax.swing.JTextField();
        txtIdPro = new javax.swing.JTextField();
        btnGraficar = new javax.swing.JButton();
        Midate = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableCliente = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtDniCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDirecionCliente = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        btnGuardarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableProveedor = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtRucProveedor = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtNombreproveedor = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTelefonoProveedor = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtDireccionProveedor = new javax.swing.JTextField();
        btnguardarProveedor = new javax.swing.JButton();
        btnEditarProveedor = new javax.swing.JButton();
        btnNuevoProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        txtIdProveedor = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableProducto = new javax.swing.JTable();
        txtIdproducto = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtCodigoPro = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtDesPro = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtCantPro = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtPrecioPro = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cbxProveedorPro = new javax.swing.JComboBox<>();
        btnGuardarpro = new javax.swing.JButton();
        btnEditarpro = new javax.swing.JButton();
        btnEliminarPro = new javax.swing.JButton();
        btnNuevoPro = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableVentas = new javax.swing.JTable();
        btnPdfVentas = new javax.swing.JButton();
        txtIdVenta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtRucConfig = new javax.swing.JTextField();
        txtNombreConfig = new javax.swing.JTextField();
        txtTelefonoConfig = new javax.swing.JTextField();
        txtDireccionConfig = new javax.swing.JTextField();
        txtMensaje = new javax.swing.JTextField();
        btnActualizarConfig = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtIdConfig = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        btnIniciar = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        cbxRol = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableUsuarios = new javax.swing.JTable();
        jLabel42 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevaVenta.setBackground(new java.awt.Color(153, 153, 153));
        btnNuevaVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        btnNuevaVenta.setText("Nueva Venta");
        btnNuevaVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevaVenta.setFocusable(false);
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 182, 159, 34));

        btnClientes.setBackground(new java.awt.Color(153, 153, 153));
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClientes.setFocusable(false);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel1.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 222, 159, 34));

        btnProveedor.setBackground(new java.awt.Color(153, 153, 153));
        btnProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        btnProveedor.setText("Proveedor");
        btnProveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProveedor.setFocusable(false);
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 262, 159, -1));

        btnProductos.setBackground(new java.awt.Color(153, 153, 153));
        btnProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProductos.setFocusable(false);
        btnProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductosMouseClicked(evt);
            }
        });
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        jPanel1.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 302, 159, 34));

        btnVentas.setBackground(new java.awt.Color(153, 153, 153));
        btnVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/compras.png"))); // NOI18N
        btnVentas.setText("Ventas");
        btnVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVentas.setFocusable(false);
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        jPanel1.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 342, 159, 34));

        btnConfig.setBackground(new java.awt.Color(153, 153, 153));
        btnConfig.setForeground(new java.awt.Color(255, 255, 255));
        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/config.png"))); // NOI18N
        btnConfig.setText("Config");
        btnConfig.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnConfig.setFocusable(false);
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });
        jPanel1.add(btnConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 382, 159, -1));

        LabelVendedor.setForeground(new java.awt.Color(255, 255, 255));
        LabelVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelVendedor.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://i0.wp.com/dacsolutions.co/wp-content/uploads/2021/08/logo-ventas-large.png?resize=150%2C150&ssl=1")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel1.add(LabelVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 130));

        tipo.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 168, -1, -1));

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Usuarios");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 448, 135, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 560));

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel14MouseDragged(evt);
            }
        });
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel14MousePressed(evt);
            }
        });
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("MingLiU-ExtB", 1, 48)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("SISTEMA DE VENTAS");
        jPanel14.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, 50));

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cerca (1).png"))); // NOI18N
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });
        jPanel14.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 50, 40));

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 130));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(102, 255, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Código");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Descripción");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Cant");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Precio");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("Stock Disponible");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        txtCodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyTyped(evt);
            }
        });
        jPanel2.add(txtCodigoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 102, 30));

        txtDescripcionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionVentaKeyTyped(evt);
            }
        });
        jPanel2.add(txtDescripcionVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 191, 30));

        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyTyped(evt);
            }
        });
        jPanel2.add(txtCantidadVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 40, 30));

        txtPrecioVenta.setEditable(false);
        jPanel2.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 80, 30));

        txtStockDisponible.setEditable(false);
        jPanel2.add(txtStockDisponible, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 79, 30));

        TableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DESCRIPCIÓN", "CANTIDAD", "PRECIO U.", "PRECIO TOTAL"
            }
        ));
        jScrollPane1.setViewportView(TableVenta);
        if (TableVenta.getColumnModel().getColumnCount() > 0) {
            TableVenta.getColumnModel().getColumn(0).setPreferredWidth(60);
            TableVenta.getColumnModel().getColumn(1).setPreferredWidth(100);
            TableVenta.getColumnModel().getColumn(2).setPreferredWidth(40);
            TableVenta.getColumnModel().getColumn(3).setPreferredWidth(50);
            TableVenta.getColumnModel().getColumn(4).setPreferredWidth(60);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 843, 191));

        btnEliminarventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarventaActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminarventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, -1, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Dni/Ruc");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 352, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("Nombre:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 352, -1, -1));

        txtRucVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucVentaKeyTyped(evt);
            }
        });
        jPanel2.add(txtRucVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 375, 116, 30));

        txtNombreClienteventa.setEditable(false);
        jPanel2.add(txtNombreClienteventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 375, 169, 30));

        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/print.png"))); // NOI18N
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });
        jPanel2.add(btnGenerarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 373, -1, 45));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/money.png"))); // NOI18N
        jLabel10.setText("Total a Pagar:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 377, -1, -1));

        LabelTotal.setText("-----");
        jPanel2.add(LabelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 381, -1, -1));
        jPanel2.add(txtIdCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 375, -1, -1));
        jPanel2.add(txtIdPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(678, 126, -1, -1));

        btnGraficar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/torta.png"))); // NOI18N
        btnGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGraficar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));
        jPanel2.add(Midate, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 210, 30));

        jLabel11.setText("Seleccionar:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://marketplace.canva.com/EAEKqa_fvcM/1/0/1600w/canva-rosa-azul-pastel-arte-l%C3%ADquido-tendencia-fondo-zoom-MNKLP6Imvd4.jpg")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 430));

        jTabbedPane1.addTab("1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI/RUC", "NOMBRE", "TELÉFONO", "DIRECCIÓN"
            }
        ));
        TableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableCliente);
        if (TableCliente.getColumnModel().getColumnCount() > 0) {
            TableCliente.getColumnModel().getColumn(0).setPreferredWidth(10);
            TableCliente.getColumnModel().getColumn(1).setPreferredWidth(50);
            TableCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableCliente.getColumnModel().getColumn(3).setPreferredWidth(50);
            TableCliente.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 555, 330));

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro Cliente"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Dni/Ruc:");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 28, -1, -1));

        txtDniCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniClienteKeyTyped(evt);
            }
        });
        jPanel9.add(txtDniCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 24, 150, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Nombre:");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 68, -1, -1));
        jPanel9.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 64, 148, 30));
        jPanel9.add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 104, 148, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Télefono:");
        jPanel9.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 108, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Dirección:");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 148, -1, -1));
        jPanel9.add(txtDirecionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 144, 148, 30));
        jPanel9.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 198, 16, -1));

        btnGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnGuardarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnEditarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnEliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, 33));

        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnNuevoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, -1, -1));

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://img.freepik.com/foto-gratis/textura-holografica-pastel-abstracta_53876-94629.jpg?w=2000")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel9.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 330));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 270, 330));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://fondosmil.com/fondo/1806.jpg")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 430));

        jTabbedPane1.addTab("2", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUC", "NOMBRE", "TELÉFONO", "DIRECCIÓN"
            }
        ));
        TableProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProveedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableProveedor);
        if (TableProveedor.getColumnModel().getColumnCount() > 0) {
            TableProveedor.getColumnModel().getColumn(0).setPreferredWidth(20);
            TableProveedor.getColumnModel().getColumn(1).setPreferredWidth(40);
            TableProveedor.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableProveedor.getColumnModel().getColumn(3).setPreferredWidth(50);
            TableProveedor.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 57, 558, 310));

        jPanel10.setBackground(new java.awt.Color(255, 204, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Proveedor"));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Ruc:");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 31, -1, -1));
        jPanel10.add(txtRucProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 24, 138, 30));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Nombre:");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 71, -1, -1));
        jPanel10.add(txtNombreproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 64, 138, 30));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Teléfono:");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 111, -1, -1));
        jPanel10.add(txtTelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 104, 110, 30));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Dirección:");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 156, -1, -1));
        jPanel10.add(txtDireccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 152, 110, 30));

        btnguardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnguardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarProveedorActionPerformed(evt);
            }
        });
        jPanel10.add(btnguardarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        btnEditarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProveedorActionPerformed(evt);
            }
        });
        jPanel10.add(btnEditarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedorActionPerformed(evt);
            }
        });
        jPanel10.add(btnNuevoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));

        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });
        jPanel10.add(btnEliminarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, 33));
        jPanel10.add(txtIdProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 200, 24, -1));

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://img.freepik.com/foto-gratis/textura-holografica-pastel-abstracta_53876-94629.jpg?w=2000")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jLabel44.setText("jLabel44");
        jPanel10.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 260, 320));

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 260, 320));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://fondosmil.com/fondo/1819.jpg")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 430));

        jTabbedPane1.addTab("3", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "DESCRIPCIÓN", "PROVEEDOR", "STOCK", "PRECIO"
            }
        ));
        TableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableProducto);
        if (TableProducto.getColumnModel().getColumnCount() > 0) {
            TableProducto.getColumnModel().getColumn(0).setPreferredWidth(20);
            TableProducto.getColumnModel().getColumn(1).setPreferredWidth(50);
            TableProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableProducto.getColumnModel().getColumn(3).setPreferredWidth(60);
            TableProducto.getColumnModel().getColumn(4).setPreferredWidth(40);
            TableProducto.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 570, 330));
        jPanel5.add(txtIdproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 25, -1, -1));

        jPanel11.setBackground(new java.awt.Color(255, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Producto"));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Código:");
        jPanel11.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 38, -1, -1));
        jPanel11.add(txtCodigoPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 34, 119, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Descripción:");
        jPanel11.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 80, -1, -1));
        jPanel11.add(txtDesPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 76, 146, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Cantidad:");
        jPanel11.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 124, -1, -1));
        jPanel11.add(txtCantPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 109, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Precio:");
        jPanel11.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 168, -1, -1));

        txtPrecioPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProKeyTyped(evt);
            }
        });
        jPanel11.add(txtPrecioPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 109, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Proveedor:");
        jPanel11.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 214, -1, -1));

        cbxProveedorPro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProveedorProItemStateChanged(evt);
            }
        });
        cbxProveedorPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProveedorProActionPerformed(evt);
            }
        });
        jPanel11.add(cbxProveedorPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 210, 143, 30));

        btnGuardarpro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarproActionPerformed(evt);
            }
        });
        jPanel11.add(btnGuardarpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 269, -1, -1));

        btnEditarpro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnEditarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarproActionPerformed(evt);
            }
        });
        jPanel11.add(btnEditarpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        btnEliminarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProActionPerformed(evt);
            }
        });
        jPanel11.add(btnEliminarPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, 33));

        btnNuevoPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProActionPerformed(evt);
            }
        });
        jPanel11.add(btnNuevoPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, -1));

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://img.freepik.com/foto-gratis/textura-holografica-pastel-abstracta_53876-94629.jpg?w=2000")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel11.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 330));

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 260, 330));

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://i0.wp.com/hipertextual.com/wp-content/uploads/2017/03/color-degradado-fondos-degradados-multicolor-51200.jpg?fit=1920%2C1200&ssl=1")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel5.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 430));

        jTabbedPane1.addTab("4", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 102));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "TOTAL"
            }
        ));
        TableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TableVentas);
        if (TableVentas.getColumnModel().getColumnCount() > 0) {
            TableVentas.getColumnModel().getColumn(0).setPreferredWidth(20);
            TableVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
            TableVentas.getColumnModel().getColumn(2).setPreferredWidth(60);
            TableVentas.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        jPanel6.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 766, 310));

        btnPdfVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btnPdfVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPdfVentasActionPerformed(evt);
            }
        });
        jPanel6.add(btnPdfVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));
        jPanel6.add(txtIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 46, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Historial Ventas");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 280, -1));

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://marketplace.canva.com/EAEKqa_fvcM/1/0/1600w/canva-rosa-azul-pastel-arte-l%C3%ADquido-tendencia-fondo-zoom-MNKLP6Imvd4.jpg")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel6.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 430));

        jTabbedPane1.addTab("5", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("RUC");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("NOMBRE");
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setText("TELÉFONO");
        jPanel7.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, -1, -1));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setText("DIRECCIÓN");
        jPanel7.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setText("MENSAJE");
        jPanel7.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));
        jPanel7.add(txtRucConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 147, 30));
        jPanel7.add(txtNombreConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 220, 30));
        jPanel7.add(txtTelefonoConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 218, 30));
        jPanel7.add(txtDireccionConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 147, 30));
        jPanel7.add(txtMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 400, 30));

        btnActualizarConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarConfig.setText("ACTUALIZAR");
        btnActualizarConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarConfigActionPerformed(evt);
            }
        });
        jPanel7.add(btnActualizarConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, -1, 35));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel32.setText("DATOS DE LA EMPRESA");
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, -1));

        jPanel8.setBackground(new java.awt.Color(153, 255, 204));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel8.add(txtIdConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 263, 24, -1));

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://img.freepik.com/foto-gratis/textura-holografica-pastel-abstracta_53876-94629.jpg?w=2000")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel8.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 340));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 420, 340));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://static.vecteezy.com/system/resources/thumbnails/007/609/435/small_2x/the-insider-woman-is-telling-the-secret-information-to-the-man-the-woman-is-telling-the-business-is-decreasing-trend-of-her-company-to-investor-corporate-data-protection-campaigns-concept-vector.jpg")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 410, 340));

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://marketplace.canva.com/EAEK4dC2olw/1/0/1600w/canva-arco%C3%ADris-gradiente-rosa-y-naranja-fondo-virtual-para-zoom-c3PYha1JI9M.jpg")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel7.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 430));

        jTabbedPane1.addTab("6", jPanel7);

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(255, 204, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Gotham", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 255));
        jLabel34.setText("Correo Electrónico");
        jPanel13.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 255));
        jLabel35.setText("Password");
        jPanel13.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        jPanel13.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 226, 30));
        jPanel13.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 226, 30));

        btnIniciar.setBackground(new java.awt.Color(0, 0, 204));
        btnIniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciar.setText("Registrar");
        btnIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        jPanel13.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 93, 34));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 255));
        jLabel36.setText("Nombre:");
        jPanel13.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));
        jPanel13.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 226, 30));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 255));
        jLabel37.setText("Rol:");
        jPanel13.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        cbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Asistente" }));
        jPanel13.add(cbxRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 226, 30));

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://img.freepik.com/foto-gratis/textura-holografica-pastel-abstracta_53876-94629.jpg?w=2000")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel13.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 380));

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 280, 380));

        TableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Correo", "Rol"
            }
        ));
        jScrollPane6.setViewportView(TableUsuarios);

        jPanel12.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 540, 380));

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://cdn.shopify.com/s/files/1/0451/1085/3787/files/pastel-watercolor-abstract-background_1_1800x.jpg?v=1652980535")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        jPanel12.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 430));

        jTabbedPane1.addTab("7", jPanel12);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 95, 860, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
        LimpiarTable();
        ListarCliente();
        btnEditarCliente.setEnabled(false);
        btnEliminarCliente.setEnabled(false);
        LimpiarCliente();
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        // TODO add your handling code here:
        LimpiarTable();
        ListarProveedor();
        jTabbedPane1.setSelectedIndex(2);
        btnEditarProveedor.setEnabled(true);
        btnEliminarProveedor.setEnabled(true);
        LimpiarProveedor();
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        // TODO add your handling code here:
        LimpiarTable();
        ListarProductos();
        jTabbedPane1.setSelectedIndex(3);
        btnEditarpro.setEnabled(false);
        btnEliminarPro.setEnabled(false);
        btnGuardarpro.setEnabled(true);
        LimpiarProductos();
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevaVentaActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_btnConfigActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
        LimpiarTable();
        ListarVentas();
    }//GEN-LAST:event_btnVentasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
        LimpiarTable();
        ListarUsuarios();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseClicked
        // TODO add your handling code here:
        cbxProveedorPro.removeAllItems();
        llenarProveedor();
        
    }//GEN-LAST:event_btnProductosMouseClicked

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        if(txtNombre.getText().equals("") || txtCorreo.getText().equals("") || txtPass.getPassword().equals("")){
            JOptionPane.showMessageDialog(null, "Todo los campos son requeridos");
        }else{
            String correo = txtCorreo.getText();
            String pass = String.valueOf(txtPass.getPassword());
            String nom = txtNombre.getText();
            String rol = cbxRol.getSelectedItem().toString();
            lg.setNombre(nom);
            lg.setCorreo(correo);
            lg.setPass(pass);
            lg.setRol(rol);
            login.Registrar(lg);
            JOptionPane.showMessageDialog(null, "Usuario Registrado");
            LimpiarTable();
            ListarUsuarios();
            nuevoUsuario();
        }
    }//GEN-LAST:event_btnIniciarActionPerformed
    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void btnActualizarConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarConfigActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtRucConfig.getText()) || !"".equals(txtNombreConfig.getText()) || !"".equals(txtTelefonoConfig.getText()) || !"".equals(txtDireccionConfig.getText())) {
            conf.setRuc(txtRucConfig.getText());
            conf.setNombre(txtNombreConfig.getText());
            conf.setTelefono(txtTelefonoConfig.getText());
            conf.setDireccion(txtDireccionConfig.getText());
            conf.setMensaje(txtMensaje.getText());
            conf.setId(Integer.parseInt(txtIdConfig.getText()));
            proDao.ModificarDatos(conf);
            JOptionPane.showMessageDialog(null, "Datos de la empresa modificado");
            ListarConfig();
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnActualizarConfigActionPerformed

    private void btnPdfVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfVentasActionPerformed

        if(txtIdVenta.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }else{
            v = Vdao.BuscarVenta(Integer.parseInt(txtIdVenta.getText()));
            Vdao.pdfV(v.getId(), v.getCliente(), v.getTotal(), v.getVendedor());
        }
    }//GEN-LAST:event_btnPdfVentasActionPerformed

    private void TableVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVentasMouseClicked
        // TODO add your handling code here:
        int fila = TableVentas.rowAtPoint(evt.getPoint());
        txtIdVenta.setText(TableVentas.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_TableVentasMouseClicked

    private void btnNuevoProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProActionPerformed
        // TODO add your handling code here:
        LimpiarProductos();
    }//GEN-LAST:event_btnNuevoProActionPerformed

    private void btnEliminarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdproducto.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdproducto.getText());
                proDao.EliminarProductos(id);
                LimpiarTable();
                LimpiarProductos();
                ListarProductos();
                btnEditarpro.setEnabled(false);
                btnEliminarPro.setEnabled(false);
                btnGuardarpro.setEnabled(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }
    }//GEN-LAST:event_btnEliminarProActionPerformed

    private void btnEditarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarproActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdproducto.getText())) {
            JOptionPane.showMessageDialog(null, "Seleecione una fila");
        } else {
            if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtDesPro.getText()) || !"".equals(txtCantPro.getText()) || !"".equals(txtPrecioPro.getText())) {
                pro.setCodigo(txtCodigoPro.getText());
                pro.setNombre(txtDesPro.getText());
                Combo itemP = (Combo) cbxProveedorPro.getSelectedItem();
                pro.setProveedor(itemP.getId());
                pro.setStock(Integer.parseInt(txtCantPro.getText()));
                pro.setPrecio(Double.parseDouble(txtPrecioPro.getText()));
                pro.setId(Integer.parseInt(txtIdproducto.getText()));
                proDao.ModificarProductos(pro);
                JOptionPane.showMessageDialog(null, "Producto Modificado");
                LimpiarTable();
                ListarProductos();
                LimpiarProductos();
                cbxProveedorPro.removeAllItems();
                llenarProveedor();
                btnEditarpro.setEnabled(false);
                btnEliminarPro.setEnabled(false);
                btnGuardarpro.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnEditarproActionPerformed

    private void btnGuardarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarproActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtDesPro.getText()) || !"".equals(cbxProveedorPro.getSelectedItem()) || !"".equals(txtCantPro.getText()) || !"".equals(txtPrecioPro.getText())) {
            pro.setCodigo(txtCodigoPro.getText());
            pro.setNombre(txtDesPro.getText());
            Combo itemP = (Combo) cbxProveedorPro.getSelectedItem();
            pro.setProveedor(itemP.getId());
            pro.setStock(Integer.parseInt(txtCantPro.getText()));
            pro.setPrecio(Double.parseDouble(txtPrecioPro.getText()));
            proDao.RegistrarProductos(pro);
            JOptionPane.showMessageDialog(null, "Productos Registrado");
            LimpiarTable();
            ListarProductos();
            LimpiarProductos();
            cbxProveedorPro.removeAllItems();
            llenarProveedor();
            btnEditarpro.setEnabled(false);
            btnEliminarPro.setEnabled(false);
            btnGuardarpro.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarproActionPerformed

    private void cbxProveedorProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProveedorProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxProveedorProActionPerformed

    private void cbxProveedorProItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProveedorProItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbxProveedorProItemStateChanged

    private void txtPrecioProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProKeyTyped
        // TODO add your handling code here:
        event.numberDecimalKeyPress(evt, txtPrecioPro);
    }//GEN-LAST:event_txtPrecioProKeyTyped

    private void TableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProductoMouseClicked
        // TODO add your handling code here:
        btnEditarpro.setEnabled(true);
        btnEliminarPro.setEnabled(true);
        btnGuardarpro.setEnabled(true);
        int fila = TableProducto.rowAtPoint(evt.getPoint());
        txtIdproducto.setText(TableProducto.getValueAt(fila, 0).toString());
        pro = proDao.BuscarId(Integer.parseInt(txtIdproducto.getText()));
        txtCodigoPro.setText(pro.getCodigo());
        txtDesPro.setText(pro.getNombre());
        txtCantPro.setText("" + pro.getStock());
        txtPrecioPro.setText("" + pro.getPrecio());
        cbxProveedorPro.setSelectedItem(new Combo(pro.getProveedor(), pro.getProveedorPro()));
    }//GEN-LAST:event_TableProductoMouseClicked

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdProveedor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdProveedor.getText());
                PrDao.EliminarProveedor(id);
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed
        // TODO add your handling code here:
        LimpiarProveedor();
        btnEditarProveedor.setEnabled(false);
        btnEliminarProveedor.setEnabled(false);
        btnguardarProveedor.setEnabled(true);
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

    private void btnEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProveedorActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdProveedor.getText())) {
            JOptionPane.showMessageDialog(null, "Seleecione una fila");
        } else {
            if (!"".equals(txtRucProveedor.getText()) || !"".equals(txtNombreproveedor.getText()) || !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())) {
                pr.setRuc(txtRucProveedor.getText());
                pr.setNombre(txtNombreproveedor.getText());
                pr.setTelefono(txtTelefonoProveedor.getText());
                pr.setDireccion(txtDireccionProveedor.getText());
                pr.setId(Integer.parseInt(txtIdProveedor.getText()));
                PrDao.ModificarProveedor(pr);
                JOptionPane.showMessageDialog(null, "Proveedor Modificado");
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
                btnEditarProveedor.setEnabled(false);
                btnEliminarProveedor.setEnabled(false);
                btnguardarProveedor.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnEditarProveedorActionPerformed

    private void btnguardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarProveedorActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtRucProveedor.getText()) || !"".equals(txtNombreproveedor.getText()) || !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())) {
            pr.setRuc(txtRucProveedor.getText());
            pr.setNombre(txtNombreproveedor.getText());
            pr.setTelefono(txtTelefonoProveedor.getText());
            pr.setDireccion(txtDireccionProveedor.getText());
            PrDao.RegistrarProveedor(pr);
            JOptionPane.showMessageDialog(null, "Proveedor Registrado");
            LimpiarTable();
            ListarProveedor();
            LimpiarProveedor();
            btnEditarProveedor.setEnabled(false);
            btnEliminarProveedor.setEnabled(false);
            btnguardarProveedor.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Los campos esta vacios");
        }
    }//GEN-LAST:event_btnguardarProveedorActionPerformed

    private void TableProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProveedorMouseClicked
        // TODO add your handling code here:
        btnEditarProveedor.setEnabled(true);
        btnEliminarProveedor.setEnabled(true);
        btnguardarProveedor.setEnabled(false);
        int fila = TableProveedor.rowAtPoint(evt.getPoint());
        txtIdProveedor.setText(TableProveedor.getValueAt(fila, 0).toString());
        txtRucProveedor.setText(TableProveedor.getValueAt(fila, 1).toString());
        txtNombreproveedor.setText(TableProveedor.getValueAt(fila, 2).toString());
        txtTelefonoProveedor.setText(TableProveedor.getValueAt(fila, 3).toString());
        txtDireccionProveedor.setText(TableProveedor.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableProveedorMouseClicked

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        // TODO add your handling code here:
        LimpiarCliente();
        btnEditarCliente.setEnabled(false);
        btnEliminarCliente.setEnabled(false);
        btnGuardarCliente.setEnabled(true);
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdCliente.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdCliente.getText());
                client.EliminarCliente(id);
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
            }
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdCliente.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtTelefonoCliente.getText())) {
                cl.setDni(txtDniCliente.getText());
                cl.setNombre(txtNombreCliente.getText());
                cl.setTelefono(txtTelefonoCliente.getText());
                cl.setDireccion(txtDirecionCliente.getText());
                cl.setId(Integer.parseInt(txtIdCliente.getText()));
                client.ModificarCliente(cl);
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtDirecionCliente.getText())) {
            cl.setDni(txtDniCliente.getText());
            cl.setNombre(txtNombreCliente.getText());
            cl.setTelefono(txtTelefonoCliente.getText());
            cl.setDireccion(txtDirecionCliente.getText());
            client.RegistrarCliente(cl);
            JOptionPane.showMessageDialog(null, "Cliente Registrado");
            LimpiarTable();
            LimpiarCliente();
            ListarCliente();
            btnEditarCliente.setEnabled(false);
            btnEliminarCliente.setEnabled(false);
            btnGuardarCliente.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void txtDniClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniClienteKeyTyped

    private void TableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableClienteMouseClicked
        // TODO add your handling code here:
        btnEditarCliente.setEnabled(true);
        btnEliminarCliente.setEnabled(true);
        btnGuardarCliente.setEnabled(false);
        int fila = TableCliente.rowAtPoint(evt.getPoint());
        txtIdCliente.setText(TableCliente.getValueAt(fila, 0).toString());
        txtDniCliente.setText(TableCliente.getValueAt(fila, 1).toString());
        txtNombreCliente.setText(TableCliente.getValueAt(fila, 2).toString());
        txtTelefonoCliente.setText(TableCliente.getValueAt(fila, 3).toString());
        txtDirecionCliente.setText(TableCliente.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableClienteMouseClicked

    private void btnGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarActionPerformed
        // TODO add your handling code here:

        String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(Midate.getDate());
        Grafico.Graficar(fechaReporte);

    }//GEN-LAST:event_btnGraficarActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        // TODO add your handling code here:
        if (TableVenta.getRowCount() > 0) {
            if (!"".equals(txtNombreClienteventa.getText())) {
                RegistrarVenta();
                RegistrarDetalle();
                ActualizarStock();
                LimpiarTableVenta();
                LimpiarClienteventa();
            } else {
                JOptionPane.showMessageDialog(null, "Debes buscar un cliente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Noy productos en la venta");
        }

    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void txtRucVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucVentaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucVentaKeyTyped

    private void txtRucVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucVentaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtRucVenta.getText())) {
                int dni = Integer.parseInt(txtRucVenta.getText());
                cl = client.Buscarcliente(dni);
                if (cl.getNombre() != null) {
                    txtNombreClienteventa.setText("" + cl.getNombre());
                    txtIdCV.setText("" + cl.getId());
                } else {
                    txtRucVenta.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                }
            }
        }
    }//GEN-LAST:event_txtRucVentaKeyPressed

    private void btnEliminarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarventaActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) TableVenta.getModel();
        modelo.removeRow(TableVenta.getSelectedRow());
        TotalPagar();
        txtCodigoVenta.requestFocus();
    }//GEN-LAST:event_btnEliminarventaActionPerformed

    private void txtCantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtCantidadVentaKeyTyped

    private void txtCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCantidadVenta.getText())) {
                int id = Integer.parseInt(txtIdPro.getText());
                String descripcion = txtDescripcionVenta.getText();
                int cant = Integer.parseInt(txtCantidadVenta.getText());
                double precio = Double.parseDouble(txtPrecioVenta.getText());
                double total = cant * precio;
                int stock = Integer.parseInt(txtStockDisponible.getText());
                if (stock >= cant) {
                    item = item + 1;
                    tmp = (DefaultTableModel) TableVenta.getModel();
                    for (int i = 0; i < TableVenta.getRowCount(); i++) {
                        if (TableVenta.getValueAt(i, 1).equals(txtDescripcionVenta.getText())) {
                            JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(id);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(total);
                    Object[] O = new Object[5];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    tmp.addRow(O);
                    TableVenta.setModel(tmp);
                    TotalPagar();
                    LimparVenta();
                    txtCodigoVenta.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Stock no disponible");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
            }
        }
    }//GEN-LAST:event_txtCantidadVentaKeyPressed

    private void txtDescripcionVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionVentaKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtDescripcionVentaKeyTyped

    private void txtCodigoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtCodigoVentaKeyTyped

    private void txtCodigoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoVenta.getText())) {
                String cod = txtCodigoVenta.getText();
                pro = proDao.BuscarPro(cod);
                if (pro.getNombre() != null) {
                    txtIdPro.setText("" + pro.getId());
                    txtDescripcionVenta.setText("" + pro.getNombre());
                    txtPrecioVenta.setText("" + pro.getPrecio());
                    txtStockDisponible.setText("" + pro.getStock());
                    txtCantidadVenta.requestFocus();
                } else {
                    LimparVenta();
                    txtCodigoVenta.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del productos");
                txtCodigoVenta.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoVentaKeyPressed

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jPanel14MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xMouse,y-yMouse);
    }//GEN-LAST:event_jPanel14MouseDragged

    private void jPanel14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanel14MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JLabel LabelVendedor;
    private com.toedter.calendar.JDateChooser Midate;
    private javax.swing.JTable TableCliente;
    private javax.swing.JTable TableProducto;
    private javax.swing.JTable TableProveedor;
    private javax.swing.JTable TableUsuarios;
    private javax.swing.JTable TableVenta;
    private javax.swing.JTable TableVentas;
    private javax.swing.JButton btnActualizarConfig;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarProveedor;
    private javax.swing.JButton btnEditarpro;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarPro;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEliminarventa;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnGraficar;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarpro;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoPro;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton btnPdfVentas;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btnguardarProveedor;
    private javax.swing.JComboBox<Object> cbxProveedorPro;
    private javax.swing.JComboBox<String> cbxRol;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel tipo;
    private javax.swing.JTextField txtCantPro;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCodigoPro;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDesPro;
    private javax.swing.JTextField txtDescripcionVenta;
    private javax.swing.JTextField txtDireccionConfig;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtDirecionCliente;
    private javax.swing.JTextField txtDniCliente;
    private javax.swing.JTextField txtIdCV;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdConfig;
    private javax.swing.JTextField txtIdPro;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtIdproducto;
    private javax.swing.JTextField txtMensaje;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreClienteventa;
    private javax.swing.JTextField txtNombreConfig;
    private javax.swing.JTextField txtNombreproveedor;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtPrecioPro;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtRucConfig;
    private javax.swing.JTextField txtRucProveedor;
    private javax.swing.JTextField txtRucVenta;
    private javax.swing.JTextField txtStockDisponible;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoConfig;
    private javax.swing.JTextField txtTelefonoProveedor;
    // End of variables declaration//GEN-END:variables
    private void LimpiarCliente() {
        txtIdCliente.setText("");
        txtDniCliente.setText("");
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
        txtDirecionCliente.setText("");
    }

    private void LimpiarProveedor() {
        txtIdProveedor.setText("");
        txtRucProveedor.setText("");
        txtNombreproveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtDireccionProveedor.setText("");
    }

    private void LimpiarProductos() {
        txtIdPro.setText("");
        txtCodigoPro.setText("");
        cbxProveedorPro.setSelectedItem(null);
        txtDesPro.setText("");
        txtCantPro.setText("");
        txtPrecioPro.setText("");
    }

    private void TotalPagar() {
        Totalpagar = 0.00;
        int numFila = TableVenta.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(TableVenta.getModel().getValueAt(i, 4)));
            Totalpagar = Totalpagar + cal;
        }
        LabelTotal.setText(String.format("%.2f", Totalpagar));
    }

    private void LimparVenta() {
        txtCodigoVenta.setText("");
        txtDescripcionVenta.setText("");
        txtCantidadVenta.setText("");
        txtStockDisponible.setText("");
        txtPrecioVenta.setText("");
        txtIdVenta.setText("");
    }

    private void RegistrarVenta() {
        int cliente = Integer.parseInt(txtIdCV.getText());
        String vendedor = LabelVendedor.getText();
        double monto = Totalpagar;
        v.setCliente(cliente);
        v.setVendedor(vendedor);
        v.setTotal(monto);
        v.setFecha(fechaActual);
        Vdao.RegistrarVenta(v);
    }

    private void RegistrarDetalle() {
        int id = Vdao.IdVenta();
        for (int i = 0; i < TableVenta.getRowCount(); i++) {
            int id_pro = Integer.parseInt(TableVenta.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(TableVenta.getValueAt(i, 3).toString());
            Dv.setId_pro(id_pro);
            Dv.setCantidad(cant);
            Dv.setPrecio(precio);
            Dv.setId(id);
            Vdao.RegistrarDetalle(Dv);

        }
        int cliente = Integer.parseInt(txtIdCV.getText());
        Vdao.pdfV(id, cliente, Totalpagar, LabelVendedor.getText());
    }

    private void ActualizarStock() {
        for (int i = 0; i < TableVenta.getRowCount(); i++) {
            int id = Integer.parseInt(TableVenta.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
            pro = proDao.BuscarId(id);
            int StockActual = pro.getStock() - cant;
            Vdao.ActualizarStock(StockActual, id);

        }
    }

    private void LimpiarTableVenta() {
        tmp = (DefaultTableModel) TableVenta.getModel();
        int fila = TableVenta.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }

    private void LimpiarClienteventa() {
        txtRucVenta.setText("");
        txtNombreClienteventa.setText("");
        txtIdCV.setText("");
    }
    private void nuevoUsuario(){
        txtNombre.setText("");
        txtCorreo.setText("");
        txtPass.setText("");
    }
    private void llenarProveedor(){
        List<Proveedor> lista = PrDao.ListarProveedor();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            cbxProveedorPro.addItem(new Combo(id, nombre));
        }
    }
}
