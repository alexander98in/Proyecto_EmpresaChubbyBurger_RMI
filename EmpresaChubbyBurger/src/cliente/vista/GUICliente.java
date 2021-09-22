package cliente.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import servidorPedidos.dto.ClsDatosEmpresa;
import servidorPedidos.dto.ClsInvoice;
import servidorPedidos.sop_rmi.GestionPedidosInt;
import servidorPedidos.dto.HamburguesaPedidoDTO;
import servidorPedidos.dto.Pedido;

/**
 * Interfaz Grafica de Usuario para el cliente
 * @author Leyder Alexander Inagan
 */
public class GUICliente extends JFrame {
    
    private GestionPedidosInt objRemotoServidor;
    private final int numeroMesa;
    private Pedido objPedido;
    private int numeroHamburguesas;
    
    //Paneles de la GUICliente
    private final JPanel panelNorte;
    private final JPanel panelSur;
    private final JPanel panelCentral;
    private final JPanel panelEste;
    private final JPanel panelInternoIngresoDatos;
    private final JPanel panelInternoListaHam;
    private final JPanel panelNorteDelPanelLista;
    private final JPanel panelCentroDelPanelLista;
     
    //Esquemas de la GUICliente
    private final BorderLayout esquemaPrincipal;
    private final GridLayout esquemaPanelNorte;
    private final GridLayout esquemaPanelSur;
    private final GridLayout esquemaPanelCentral;
    private final BorderLayout esquemaPanelEste;
    
    //Etiquetas para la GUICliente
    private final JLabel etiquetaTitulo;
    private final JLabel etiquetaIdentificador;
    private final JLabel etiquetaTipoHamburguesa;
    private final JLabel etiquetaCantIngreExtra;
    private final JLabel etiquetaPrecio;
    
    //Campos de Texto
    private final JTextField campoTextoIdentificador;
    private final JTextField campoTextoCantIngExt;
    private final JTextField campoTextoPrecio;
    
    //JComboBox para el tipo de la Hamburguesa
    private static final String[] tiposHamburguesa = {"Pequeña", "Mediana", "Grande"};
    private final JComboBox<String> listaDesplTipoHam;
    
    //JList o lista para las hamburguesas
    private final JList<String> listaListHamburguesas;
    
    //Modelo de la lista para hamburguesas
    private final DefaultListModel<String> modeloLista;
    //Areas de Texto
    private final JTextArea areaTextoInformacion;
    
    //Botones
    private final JButton botonRealizarCompra;
    private final JButton botonActualizar;
    private final JButton botonModificarPedido;
    private final JButton botonListarPedidos;
    private final JButton botonEliminar;
    private final JButton botonDatosEmpresa;
    private final JButton botonEfectuarPedido;
    private final JButton botonSalir;    
    
    public GUICliente(GestionPedidosInt objRemoteServidor, int numeroMesa) {
        super("Cliente Usuario");
        
        this.objRemotoServidor = objRemoteServidor;
        this.numeroMesa = numeroMesa;
        this.objPedido = new Pedido();
        this.objPedido.setNumeroMesa(this.numeroMesa);
        this.numeroHamburguesas = 0;
        
        this.esquemaPrincipal = new BorderLayout(5, 5);
        this.setLayout(esquemaPrincipal);
        
        //Panel Norte de la GUICliente
        this.esquemaPanelNorte = new GridLayout(1, 1, 5, 5);
        this.panelNorte = new JPanel();
        panelNorte.setLayout(esquemaPanelNorte);
        
        etiquetaTitulo = new JLabel("Chubby Burger", SwingConstants.CENTER);
        etiquetaTitulo.setToolTipText("Titulo");
        etiquetaTitulo.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        etiquetaTitulo.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 50));
        etiquetaTitulo.setBackground(new Color(249, 231, 159));
        etiquetaTitulo.setOpaque(true);
        
        panelNorte.add(etiquetaTitulo);
        
        this.add(panelNorte, BorderLayout.NORTH);
         
        //Panel Central de la GUICliente
        esquemaPanelCentral = new GridLayout(2, 1, 5, 5);
        panelCentral = new JPanel();
        panelCentral.setLayout(esquemaPanelCentral);
        
        //Panel Interno que se adhiere al penel central
        panelInternoIngresoDatos = new JPanel();
        panelInternoIngresoDatos.setLayout(new GridLayout(4, 2, 5, 5));
        
        //Etiqueta del Identificador
        etiquetaIdentificador = new JLabel("Identificador:  ", SwingConstants.RIGHT);
        etiquetaIdentificador.setToolTipText("Ingrese el identificador o nombre de su hamburguesa");
        etiquetaIdentificador.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        etiquetaIdentificador.setFont(new Font("Tahoma", Font.BOLD, 15));
        etiquetaIdentificador.setBackground(new Color(163, 228, 215));
        etiquetaIdentificador.setOpaque(true);
        
        //Etiqueta tipo de Hamburguesa
        etiquetaTipoHamburguesa = new JLabel("Tipo de Hamburguesa:  ", SwingConstants.RIGHT);
        etiquetaTipoHamburguesa.setToolTipText("Seleccione el tipo de la hamburguesa que desea");
        etiquetaTipoHamburguesa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        etiquetaTipoHamburguesa.setFont(new Font("Tahoma", Font.BOLD, 15));
        etiquetaTipoHamburguesa.setBackground(new Color(163, 228, 215));
        etiquetaTipoHamburguesa.setOpaque(true);
        
        //Etiqueta cantidad de ingredientes extra
        etiquetaCantIngreExtra = new JLabel("Ingredientes Extra:  ", SwingConstants.RIGHT);
        etiquetaCantIngreExtra.setToolTipText("Ingrese la cantidad de ingredientes extra");
        etiquetaCantIngreExtra.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        etiquetaCantIngreExtra.setFont(new Font("Tahoma", Font.BOLD, 15));
        etiquetaCantIngreExtra.setBackground(new Color(163, 228, 215));
        etiquetaCantIngreExtra.setOpaque(true);
        
        //Etiqueta Precio
        etiquetaPrecio = new JLabel("Precio:  ", SwingConstants.RIGHT);
        etiquetaPrecio.setToolTipText("Precio de su Hamburguesa");
        etiquetaPrecio.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        etiquetaPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
        etiquetaPrecio.setBackground(new Color(163, 228, 215));
        etiquetaPrecio.setOpaque(true);
        
        //Campo de texto del identificador
        campoTextoIdentificador = new JTextField();
        campoTextoIdentificador.setFont(new Font("Tahoma", Font.PLAIN, 16));
        campoTextoIdentificador.setBackground(new Color(240, 243, 244));
            
        campoTextoIdentificador.addFocusListener(
            new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent evento) {
                    validarIdHamburguesa();
                } 
            }
        );
        
        //Campo de texto cantidad de ingredientes Extra
        campoTextoCantIngExt = new JTextField();
        campoTextoCantIngExt.setFont(new Font("Tahoma", Font.PLAIN, 16));
        campoTextoCantIngExt.setBackground(new Color(240, 243, 244));
     
        campoTextoCantIngExt.addFocusListener(
            new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent evento) {
                    comprobarIngredientesExtra();
                } 
            }
        );

        //Campo de texto del identificador
        campoTextoPrecio = new JTextField("6.0");
        campoTextoPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
        campoTextoPrecio.setBackground(new Color(240, 243, 244));
        campoTextoPrecio.setEditable(false);
        
        //Lista desplegable de tipos de hamburguesa
        listaDesplTipoHam = new JComboBox<String>(tiposHamburguesa);
        listaDesplTipoHam.setMaximumRowCount(3);
        listaDesplTipoHam.setBackground(new Color(213, 219, 219));
        
        listaDesplTipoHam.addItemListener(
            new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent evento) {
                    colocarPrecio();
                }
            }
        
        );
        
        panelInternoIngresoDatos.add(etiquetaIdentificador);
        panelInternoIngresoDatos.add(campoTextoIdentificador);
        panelInternoIngresoDatos.add(etiquetaTipoHamburguesa);
        panelInternoIngresoDatos.add(listaDesplTipoHam);
        panelInternoIngresoDatos.add(etiquetaCantIngreExtra);
        panelInternoIngresoDatos.add(campoTextoCantIngExt);
        panelInternoIngresoDatos.add(etiquetaPrecio);
        panelInternoIngresoDatos.add(campoTextoPrecio);
        
        panelCentral.add(panelInternoIngresoDatos);
       
        //Panel Interno Extra
        panelInternoListaHam = new JPanel();
        panelInternoListaHam.setLayout(new BorderLayout(5, 5));
        
        //Panel Norte Extra
        panelNorteDelPanelLista = new JPanel();
        panelNorteDelPanelLista.setLayout(new GridLayout(1, 2, 5, 5));
        
        //Boton Modificar Pedido
        Icon compra = new ImageIcon(getClass().getResource("/recursos/compra_opt.png"));
        botonRealizarCompra = new JButton(" Realizar Compra ", compra);
        botonRealizarCompra.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonRealizarCompra.setBackground(new Color(169, 223, 191));
        botonRealizarCompra.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega un evento al boton Realizar COMPRA
        botonRealizarCompra.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Procesa evento de BotonSalir
                @Override
                public void actionPerformed(ActionEvent evento) {
                    realizarCompra();
                }
            }     
        );  
        
        //Boton Actualizar
        Icon actulizar = new ImageIcon(getClass().getResource("/recursos/actualizar_opt.jpg"));
        botonActualizar = new JButton(" Actualizar ", actulizar);
        botonActualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonActualizar.setBackground(new Color(174, 214, 241));
        botonActualizar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonActualizar.setEnabled(false);
        //Agrega un evento al boton Actualizar
        botonActualizar.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Procesa evento de BotonSalir
                @Override
                public void actionPerformed(ActionEvent evento) {
                    actualizarPedidoHamburguesa();
                }
            }     
        );
        
        panelNorteDelPanelLista.add(botonRealizarCompra);
        panelNorteDelPanelLista.add(botonActualizar);
        
        panelInternoListaHam.add(panelNorteDelPanelLista, BorderLayout.NORTH);
        
        //Panel Centro Extra
        panelCentroDelPanelLista = new JPanel();
        panelCentroDelPanelLista.setLayout(new BorderLayout(5, 5));
        panelCentroDelPanelLista.setBackground(new Color(254, 249, 231));
        panelCentroDelPanelLista.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 2), 
            "Lista de Hamburguesas: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
            new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
   
        //JList
        modeloLista = new DefaultListModel<>();
        listaListHamburguesas = new JList();
        listaListHamburguesas.setModel(modeloLista);
        listaListHamburguesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaListHamburguesas.setBackground(new Color(254, 249, 231));
        listaListHamburguesas.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        listaListHamburguesas.addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent evento) {
                    habilitarModificarYEliminar();
                }
            }
        );
        
        panelCentroDelPanelLista.add(new JScrollPane(listaListHamburguesas), BorderLayout.CENTER);
        
        panelInternoListaHam.add(panelCentroDelPanelLista);
        
        panelCentral.add(panelInternoListaHam);
        
        add(panelCentral, BorderLayout.CENTER);
        
        //Panel Este de la GUICliente
        esquemaPanelEste = new BorderLayout(5, 5);
        panelEste = new JPanel();
        panelEste.setLayout(esquemaPanelEste);
        
        //Area de texto Informacion
        areaTextoInformacion = new JTextArea("..............................................................");
        areaTextoInformacion.setEditable(false);
        areaTextoInformacion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 2), 
            "Información: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
            new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        areaTextoInformacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        areaTextoInformacion.setBackground(new Color(229, 232, 232));
        
        panelEste.add(new JScrollPane(areaTextoInformacion), BorderLayout.CENTER);
        
        add(panelEste, BorderLayout.EAST);
        
        //Panel Sur de la GUICliente
        esquemaPanelSur = new GridLayout(2, 3, 5, 5);
        panelSur = new JPanel();
        panelSur.setLayout(esquemaPanelSur);
        
        //Boton Modificar Pedido
        Icon modificarPedido = new ImageIcon(getClass().getResource("/recursos/modificar_opt.png"));
        botonModificarPedido = new JButton("  Modificar Pedido  ", modificarPedido);
        botonModificarPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonModificarPedido.setBackground(new Color(133, 193, 233));
        botonModificarPedido.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonModificarPedido.setEnabled(false);
        //Agrega una accion al boton Modificar Pedido
        botonModificarPedido.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                @Override
                public void actionPerformed(ActionEvent evento) {
                    ModificarPedido();
                }
            }     
        );
        panelSur.add(botonModificarPedido);
        
        //Boton Listar Pedidos
        Icon listar = new ImageIcon(getClass().getResource("/recursos/listar_opt.jpg"));
        botonListarPedidos = new JButton("  Listar Pedidos  ", listar);
        botonListarPedidos.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonListarPedidos.setBackground(new Color(215, 189, 226));
        botonListarPedidos.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Listar
        botonListarPedidos.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                @Override
                public void actionPerformed(ActionEvent evento) {
                    listarPedidos();
                }
            }     
        );
        panelSur.add(botonListarPedidos);
        
        //Boton Eliminar Pedido
        Icon eliminar = new ImageIcon(getClass().getResource("/recursos/eliminar_opt.jpg"));
        botonEliminar = new JButton(" Eliminar Pedido ", eliminar);
        botonEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonEliminar.setBackground(new Color(240, 178, 122));
        botonEliminar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonEliminar.setEnabled(false);
        //Agrega una accion al boto Eliminar
        botonEliminar.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                @Override
                public void actionPerformed(ActionEvent evento) {
                    EliminarHamburguesaPedido();
                }
            }     
        );
        panelSur.add(botonEliminar);
        
        //Boton Mostrar Datos Empresa
        Icon datosEmpresa = new ImageIcon(getClass().getResource("/recursos/burger_opt.png"));
        botonDatosEmpresa = new JButton("    Datos Empresa  ", datosEmpresa);
        botonDatosEmpresa.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonDatosEmpresa.setBackground(new Color(208, 211, 212));
        botonDatosEmpresa.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Datos Empresa
        botonDatosEmpresa.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                @Override
                public void actionPerformed(ActionEvent evento) {
                    mostrarDatosEmpresaYPrecios();
                }
            }     
        );
        panelSur.add(botonDatosEmpresa);
        
        //Boton Efectuar Pago
        Icon Pagar = new ImageIcon(getClass().getResource("/recursos/pagar_opt.png"));
        botonEfectuarPedido = new JButton(" Pagar-Efectuar Pedido", Pagar);
        botonEfectuarPedido.setFont(new Font("Tahoma", Font.BOLD, 12));
        botonEfectuarPedido.setBackground(new Color(115, 198, 182));
        botonEfectuarPedido.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Salir
        botonEfectuarPedido.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                @Override
                public void actionPerformed(ActionEvent evento) {
                   efectuarPagoPedido();
                }
            }     
        );
        panelSur.add(botonEfectuarPedido);
        
        //Boton Salir
        Icon salir = new ImageIcon(getClass().getResource("/recursos/salir_opt.png"));
        botonSalir = new JButton("Cerrar Programa ", salir);
        botonSalir.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonSalir.setBackground(new Color(241, 148, 138));
        botonSalir.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Salir
        botonSalir.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                @Override
                public void actionPerformed(ActionEvent evento) {
                    cerrarAplicacion();
                }
            }     
        );
        panelSur.add(botonSalir);
        
        add(panelSur, BorderLayout.SOUTH); 
        this.addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    cerrar();
                }
            }
        );
    }
    
    private void cerrar() {
        String[] opciones = {"Cerrar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Cerrar la Aplicacion?", "Cerrar", 0, 0, null, opciones, this);
        if(eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        else if(eleccion == JOptionPane.NO_OPTION) {
            System.out.println("Se cancelo el cierre");
        }
    }
    private void habilitarModificarYEliminar() {
        this.botonModificarPedido.setEnabled(true);
        this.botonEliminar.setEnabled(true);
    }
    
    private void deshabilitarModificarYEliminar() {
        this.botonModificarPedido.setEnabled(false);
        this.botonEliminar.setEnabled(false);
    }
    
      private void habilitarCampos(){
        this.campoTextoIdentificador.setEditable(true);
        this.campoTextoCantIngExt.setEditable(true);
        this.listaDesplTipoHam.setEnabled(true);
    }
    
    private void limpiarCampos() {
        this.campoTextoIdentificador.setText("");
        this.campoTextoCantIngExt.setText("");
    }
    
    private void colocarPrecio() {
        Hashtable<String, Float> tablaPrecios = new Hashtable<>();
        char tipoHamburguesa = obtenerTipoHamburguesa();
        String tipoHam = Character.toString(tipoHamburguesa);
        ArrayList<String> listaTipoHamburguesas = new ArrayList<>();
        try {
            tablaPrecios = objRemotoServidor.obtenerPrecios();
            if(!tablaPrecios.isEmpty()) {
                Enumeration<String> enumerationKeys = tablaPrecios.keys();
                while(enumerationKeys.hasMoreElements()) {
                    listaTipoHamburguesas.add(enumerationKeys.nextElement());
                }
                
                for(int i=0; i<listaTipoHamburguesas.size(); i++) {
                    if(tipoHam.equals(listaTipoHamburguesas.get(i))) {
                        campoTextoPrecio.setText("" + tablaPrecios.get(tipoHam));
                        break;
                    }
                }
            }
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Se presento algun inconvenientes", "Error Precios", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(GUICliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void comprobarIngredientesExtra() {
        String cadena;
        int cantidadIngredientes = 0;
        if(campoTextoCantIngExt.getText().length() != 0) {
            cadena = this.campoTextoCantIngExt.getText();
            try 
            {
                cantidadIngredientes = Integer.parseInt(cadena);
                if(cantidadIngredientes < 0)
                {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un numero mayor que cero", "Error numero mesa", JOptionPane.INFORMATION_MESSAGE);
                    campoTextoCantIngExt.setText("");
                }
            }
            catch(NumberFormatException ex)
            {
                System.out.println("Entrada no Valida, debe ingresar un numero entero..." + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Debes ingresar un numero", "Error Cantidad Ingredientes extra", JOptionPane.INFORMATION_MESSAGE);
                campoTextoCantIngExt.setText("");
            }
        }
    }
    
    private void validarIdHamburguesa() {
        String idIngresado = this.campoTextoIdentificador.getText();
        boolean existeIdentificador = existeHamburguesa(idIngresado, objPedido);
        if(existeIdentificador) {
            JOptionPane.showMessageDialog(this, "El identificador ya esta registrado", "Error Identificador", JOptionPane.INFORMATION_MESSAGE);
            this.campoTextoIdentificador.setText("");
            this.campoTextoIdentificador.requestFocus();
        }
    }
    
    private void realizarCompra() {
        boolean bandera = false;
        
        if(numeroHamburguesas >= 0 && numeroHamburguesas < 12) {
            
            if(campoTextoIdentificador.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un Identificador.", "Error Identificador", JOptionPane.INFORMATION_MESSAGE);
                campoTextoIdentificador.requestFocus();
            }

            if(campoTextoCantIngExt.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad de ingredientes extra.", "Error Cantidad ingredientes extra", JOptionPane.INFORMATION_MESSAGE);
                campoTextoCantIngExt.requestFocus();
            }

            String identificador = campoTextoIdentificador.getText();
            if(existeHamburguesa(identificador, objPedido)) {
                campoTextoIdentificador.setText("");
                campoTextoIdentificador.requestFocus();
            }
            else
            {
                char tipoHamburguesa = obtenerTipoHamburguesa();
                int ingredientesExtra = 0;
                float precio = Float.parseFloat(campoTextoPrecio.getText());

                try 
                {
                    ingredientesExtra = Integer.parseInt(campoTextoCantIngExt.getText());
                    if(ingredientesExtra < 0) {
                        this.campoTextoCantIngExt.setText("");
                        this.campoTextoCantIngExt.requestFocus();
                    }
                    else {
                        bandera = true;
                    }   
                }
                catch(NumberFormatException ex)
                {
                    System.out.println("Entrada no Valida. " + ex.getMessage());
                    campoTextoCantIngExt.setText("");
                    campoTextoCantIngExt.requestFocus();
                }
                
                if(bandera) {
                    HamburguesaPedidoDTO objHamburguesa = new HamburguesaPedidoDTO(identificador, tipoHamburguesa, ingredientesExtra, precio);
                    objPedido.agregarHamburguesa(objHamburguesa);
                    numeroHamburguesas += 1;
                    limpiarCampos();
                    modeloLista.addElement(identificador);
                }
            } 
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No puedes pedir mas Hamburguesas.", "No puedes comprar mas", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            inhabilitarCampos();
        }
    }
    
    private void actualizarPedidoHamburguesa() {
        boolean bandera = false;
        modeloLista.remove(listaListHamburguesas.getSelectedIndex());
        
        if(campoTextoIdentificador.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un Identificador.", "Error Identificador", JOptionPane.INFORMATION_MESSAGE);
            campoTextoIdentificador.requestFocus();
        }

        if(campoTextoCantIngExt.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad de ingredientes extra.", "Error Cantidad ingredientes extra", JOptionPane.INFORMATION_MESSAGE);
            campoTextoCantIngExt.requestFocus();
        }

        String identificador = campoTextoIdentificador.getText();
        if(existeHamburguesa(identificador, objPedido)) {
            campoTextoIdentificador.setText("");
            campoTextoIdentificador.requestFocus();
        }
        else
        {
            char tipoHamburguesa = obtenerTipoHamburguesa();
            int ingredientesExtra = 0;
            float precio = Float.parseFloat(campoTextoPrecio.getText());

            try 
            {
                ingredientesExtra = Integer.parseInt(campoTextoCantIngExt.getText());
                if(ingredientesExtra < 0) {
                    this.campoTextoCantIngExt.setText("");
                    this.campoTextoCantIngExt.requestFocus();
                }
                else {
                    bandera = true;
                }   
            }
            catch(NumberFormatException ex)
            {
                System.out.println("Entrada no Valida. " + ex.getMessage());
                campoTextoCantIngExt.setText("");
                campoTextoCantIngExt.requestFocus();
            }
                
            if(bandera) {
                HamburguesaPedidoDTO objHamburguesa = new HamburguesaPedidoDTO(identificador, tipoHamburguesa, ingredientesExtra, precio);
                objPedido.agregarHamburguesa(objHamburguesa);
                limpiarCampos();
                modeloLista.addElement(identificador);
                this.botonRealizarCompra.setEnabled(true);
                this.botonActualizar.setEnabled(false);  
            }
        }  
    }
    
    private void ModificarPedido(){
        
        String identificador = modeloLista.getElementAt(listaListHamburguesas.getSelectedIndex());
        HamburguesaPedidoDTO hamburguesa = null;
        int pos = 0;
        for(HamburguesaPedidoDTO burger: objPedido.getListaHamburguesas()) {
            if(identificador.equals(burger.getIdentificador())) {
                hamburguesa = burger;
                objPedido.eliminarHamburguesa(pos);
                break;
            }
            pos++;
        }
        
        if(hamburguesa != null) {
            campoTextoIdentificador.setText(hamburguesa.getIdentificador());
            campoTextoCantIngExt.setText(String.valueOf(hamburguesa.getCantidadIngredientesExtra()));
            campoTextoPrecio.setText(String.valueOf(hamburguesa.getPrecio()));
            String item = "";
            
            if(hamburguesa.getTipo() == 'p') 
                item = "Pequeña";
            if(hamburguesa.getTipo() == 'm')
                item = "Medianda";
            if(hamburguesa.getTipo() == 'g')
                item = "Grande";
            
            listaDesplTipoHam.setSelectedItem(item);
        }
        
        habilitarCampos();
        deshabilitarModificarYEliminar();
        botonRealizarCompra.setEnabled(false);
        botonActualizar.setEnabled(true);
    }

    private void listarPedidos() {
        areaTextoInformacion.setText("");
        int num = 0;
        if(objPedido.getListaHamburguesas().isEmpty()) {
            areaTextoInformacion.append("\nNo hay pedidos registrados...");
        }
        else{
            areaTextoInformacion.append("\n=== Listado de Hamburguesas Pedidos ===\n");
            for(HamburguesaPedidoDTO burger: objPedido.getListaHamburguesas()){
                areaTextoInformacion.append("\nHamburguesa No: " + (num+1));
                areaTextoInformacion.append("\nIdentificador: " + burger.getIdentificador());
                areaTextoInformacion.append("\nTipo: " + obtenerTipoHamburguesa(burger.getTipo()));
                areaTextoInformacion.append("\nCantidad de Ingredientes extra: " + burger.getCantidadIngredientesExtra());
                areaTextoInformacion.append("\nPrecio: " + burger.getPrecio());
                areaTextoInformacion.append("\n------------------------------");
                num++;
            }
            
            areaTextoInformacion.append("\n\nEl precio con IVA del pedido es: " + obtenerPrecioConIVA());
        }
    }
    
    private float obtenerPrecioConIVA() {
        try {
            int numP = 0, numM = 0, numG = 0, ingExtra = 0;
            for (int i = 0; i < objPedido.getListaHamburguesas().size(); i++){
                if (objPedido.getListaHamburguesas().get(i).getTipo() == 'p')
                    numP ++;
                if (objPedido.getListaHamburguesas().get(i).getTipo() == 'm')
                    numM ++;
                if (objPedido.getListaHamburguesas().get(i).getTipo() == 'g')
                    numG ++;
                ingExtra += objPedido.getListaHamburguesas().get(i).getCantidadIngredientesExtra();
            }   
            
            Hashtable<String, Float> tablaPrecio = objRemotoServidor.obtenerPrecios();
            float costoSinIVA = 0, IVA = 0, costoConIVA = 0;
            costoSinIVA = (numP * tablaPrecio.get("p")) + (numM * tablaPrecio.get("m")) + (numG * tablaPrecio.get("g")) + (ingExtra * tablaPrecio.get("e"));
            IVA = generarIVA(costoSinIVA, (numP + numM + numG));
            costoConIVA = costoSinIVA + IVA;
            return costoConIVA;
            
        } catch (RemoteException ex) {      
            Logger.getLogger(GUICliente.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    private float generarIVA(float parCosto, int parNumHamburguesas){
        float IVA = 0;
        if (parNumHamburguesas >= 1 && parNumHamburguesas <= 3) 
            IVA = ((parCosto * 5)/100);
        
        if (parNumHamburguesas >= 4 && parNumHamburguesas <= 7)
            IVA = ((parCosto * 8)/100);
        
        if (parNumHamburguesas >= 8 ){
            
            if (parCosto > 120)
                return ((parCosto * 18)/100);
            
            IVA = ((parCosto * 15) /100);
        }
        return IVA;
    }
    
    private String obtenerTipoHamburguesa(char tipo) {
        String tipoHam = "";
        
        if(tipo == 'p') 
            tipoHam = "Pequeña";
        
        if(tipo == 'm')
            tipoHam = "Mediana";
        
        if(tipo == 'g')
            tipoHam = "Grande";
        
        return tipoHam;
    }
    
    private void EliminarHamburguesaPedido() {
        
        String[] opciones = {"Eliminar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Eliminar la Hamburguesa?", "Eliminar", 0, 0, null, opciones, this);
        if(eleccion == JOptionPane.YES_OPTION) {
           
            String identificador = modeloLista.getElementAt(listaListHamburguesas.getSelectedIndex());
        
            for(int i=0; i<objPedido.getListaHamburguesas().size(); i++) {
                if(identificador.equals(objPedido.getListaHamburguesas().get(i).getIdentificador())){
                    objPedido.eliminarHamburguesa(i);
                    break;
                }
            }
        
            modeloLista.remove(listaListHamburguesas.getSelectedIndex());
            numeroHamburguesas--;
            deshabilitarModificarYEliminar();
            
        }
        else if(eleccion == JOptionPane.NO_OPTION) {
            System.out.println("Se cancelo la eliminacion");
        }
        habilitarCampos();
    }
    
    private void mostrarDatosEmpresaYPrecios(){
        try {
      
            ClsDatosEmpresa datosEmpresa = objRemotoServidor.obtenerDatosEmpresa();
            Hashtable<String, Float> tablaPrecios = objRemotoServidor.obtenerPrecios();
            areaTextoInformacion.setText("");
            if(datosEmpresa != null){
                areaTextoInformacion.append("\nInformacion de la Empresa:");
                areaTextoInformacion.append("\nNombre : " + datosEmpresa.getNombreEmpresa());
                areaTextoInformacion.append("\nNit: " + datosEmpresa.getNit());
                areaTextoInformacion.append("\n_______________________________");
            }
            if(!tablaPrecios.isEmpty()) {
               
                areaTextoInformacion.append("\nPara hamburguesas pequeñas: " + tablaPrecios.get("p"));
                areaTextoInformacion.append("\nPara hamburguesas Medianas: " + tablaPrecios.get("m"));
                areaTextoInformacion.append("\nPara hamburguesas Grandes: " + tablaPrecios.get("g"));
                areaTextoInformacion.append("\nEl precio por ingrediente extra es de: " + tablaPrecios.get("e"));
           
            }
      
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Se presento algun inconveniente", "Error Inesperado", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(GUICliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void efectuarPagoPedido() {
        if(!objPedido.getListaHamburguesas().isEmpty()){
            try {
                
                ClsInvoice factura = objRemotoServidor.registrarPedidoSistema(objPedido);
                this.objPedido.vaciarListaHamburguesas();

                if(factura != null) {
                    areaTextoInformacion.setText("");
                    areaTextoInformacion.append("\nFactura No: " + factura.getInvoiceNumber());
                    areaTextoInformacion.append("\nNumero de la mesa: " + factura.getTableNumber());
                    areaTextoInformacion.append("\n\nHamburguesas de tipo pequeño: " + factura.getNumberSmallBurguer());
                    areaTextoInformacion.append("\nHamburguesas de tipo Mediado: " + factura.getNumberMediumBurguer());
                    areaTextoInformacion.append("\nHamburguesas de tipo Grande: " + factura.getNumberBigBurguer());
                    areaTextoInformacion.append("\n\nCosto sin IVA del pedido: " + factura.getCostWithoutIVA());
                    areaTextoInformacion.append("\nIVA del pedido: " + factura.getCostIVA());
                    areaTextoInformacion.append("\nCosto con IVA del pedido: " + factura.getCostWithIVA());
                    areaTextoInformacion.append("\n* * * ");
                }
                vaciarListaDeLaVista();
                numeroHamburguesas = 0;
                habilitarCampos();
            
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(this, "Se presento algun inconveniente", "Error Inesperado", JOptionPane.INFORMATION_MESSAGE);
                Logger.getLogger(GUICliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            areaTextoInformacion.setText("");
            areaTextoInformacion.append("\nNo hay hamburguesas registradas.");
        }     
    }
    
    private void vaciarListaDeLaVista() {
        modeloLista.clear();
        deshabilitarModificarYEliminar();
    }    
    private void cerrarAplicacion() {
        System.exit(0);
    }
    
    private void inhabilitarCampos(){
        this.campoTextoIdentificador.setEditable(false);
        this.campoTextoCantIngExt.setEditable(false);
        this.listaDesplTipoHam.setEnabled(false);
    }
   
    private char obtenerTipoHamburguesa() {
        String seleccionListaDesplTipo = (String) listaDesplTipoHam.getSelectedItem();
        char tipoHamburguesa = 's';
        
        if(seleccionListaDesplTipo.equals("Pequeña")) 
            tipoHamburguesa = 'p';
        
        if(seleccionListaDesplTipo.equals("Mediana"))
            tipoHamburguesa = 'm';
        
        if(seleccionListaDesplTipo.equals("Grande"))
            tipoHamburguesa = 'g';
        
        return tipoHamburguesa;
    }
    
    private static boolean existeHamburguesa(String identificador, Pedido pedido)
    {
        boolean existeHamburguesa = false;
        for(int i=0; i < pedido.getListaHamburguesas().size(); i++)
        {
            if(identificador.equals(pedido.getListaHamburguesas().get(i).getIdentificador())){
                existeHamburguesa = true;
                break;
            }
        }
        return existeHamburguesa;
    }
}
