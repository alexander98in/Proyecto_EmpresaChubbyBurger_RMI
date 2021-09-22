package administrador.vista;

import administrador.sop_rmi.AdministradorNotificacionImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import servidorPedidos.dto.ClsInvoice;
import servidorPedidos.sop_rmi.GestionPedidosInt;

/**
 * GUI del Administrador
 * @author Leyder Alexander Inagan
 */
public class GUIAdministrador extends JFrame {
    
    //Esquema principal de distribucion
    private final BorderLayout esquemaPrincipal;
    //Paneles o contenedores de componentes
    private final JPanel panelNorte;
    private final JPanel panelCentral;
    private final JPanel panelSur;
    private final JPanel panelEste;
    //Esquemas de distribucion
    private final GridLayout esquemaPanelNorte;
    private final FlowLayout esquemaPanelSur;
    //Etiqueta
    private final JLabel etiquetaTitulo;
    //Areas de Texto
    private final JTextArea areaTextoInformacion;
    private final JTextArea areaTextoNotificacion;
    //Modelo de la lista
    private final DefaultListModel<String> modeloLista;
    //JList o lista para las hamburguesas
    private final JList<String> listaListFacturas;
    //Boton
    private final JButton botonSalir;
    
    private GestionPedidosInt objRemotoServidor;
    private AdministradorNotificacionImpl objRemotoNotificacionAdmin;
        
    public GUIAdministrador(GestionPedidosInt objRemotePedidos) {

        super("Administrador");
        
        this.objRemotoServidor = objRemotePedidos;
        registrarObjRemotoNotificacionAdmin();

        esquemaPrincipal = new BorderLayout(5, 5);
        setLayout(esquemaPrincipal);

        //Panel que se ubica en la parte norte
        esquemaPanelNorte = new GridLayout(1, 1, 5, 5);
        panelNorte = new JPanel();
        panelNorte.setLayout(esquemaPanelNorte);
        //Etiqueta titulo
        etiquetaTitulo = new JLabel("Administrador", SwingConstants.CENTER);
        etiquetaTitulo.setToolTipText("Titulo");
        etiquetaTitulo.setBorder(BorderFactory.createLineBorder(new Color(249, 168, 37), 3));
        etiquetaTitulo.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 50));
        etiquetaTitulo.setBackground(new Color(76, 175, 80));
        etiquetaTitulo.setOpaque(true);
        //Agregamos la etiqueta al panel Norte
        panelNorte.add(etiquetaTitulo);
        //Agreamos el panel al JFrame
        add(panelNorte, BorderLayout.NORTH);

        panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(2, 1, 5, 5));
        //Area donde se mostrara la informacion de las facturas
        areaTextoInformacion = new JTextArea();
        areaTextoInformacion.setEditable(false);
        areaTextoInformacion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(52, 73, 94), 3),
                "Informacion: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        areaTextoInformacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
        areaTextoInformacion.setBackground(new Color(224, 224, 224 ));
        //Agreamos la area de texto de informacion de las facturas al panel central
        panelCentral.add(new JScrollPane(areaTextoInformacion));
        
        //Area donde se mostraran las notificaciones
        areaTextoNotificacion = new JTextArea();
        areaTextoNotificacion.setEditable(false);
        areaTextoNotificacion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(40, 53, 147 ), 3),
                "Notificacion: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        areaTextoNotificacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
        areaTextoNotificacion.setBackground(new Color(144, 202, 249));
        //Agregamos la area de notificaiones al panel central
        panelCentral.add(new JScrollPane(areaTextoNotificacion));
        //Agregamos el panel central al JFrame
        add(panelCentral, BorderLayout.CENTER);
        
        panelEste = new JPanel();
        panelEste.setLayout(new BorderLayout(5, 5));
        
        modeloLista = new DefaultListModel<>();
        //Lista de facturas
        listaListFacturas = new JList();
        listaListFacturas.setModel(modeloLista);
        listaListFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaListFacturas.setBackground(new Color(254, 249, 231));
        listaListFacturas.setFont(new Font("Tahoma", Font.PLAIN, 15));
        listaListFacturas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(62, 39, 35), 3),
                "Lista de Facturas: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        
        listaListFacturas.addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent evento) {
                    buscarYMostrarFactura();
                }
            }
        );
        
        //Agregamos la lista al panel Este
        panelEste.add(new JScrollPane(listaListFacturas), BorderLayout.CENTER);
        //Agregamos el panel este al JFrame
        add(panelEste, BorderLayout.EAST);

        esquemaPanelSur = new FlowLayout();
        panelSur = new JPanel();
        panelSur.setLayout(esquemaPanelSur);
        panelSur.setBackground(new Color(255, 171, 145));
        panelSur.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(191, 54, 12), 3),
                "Opciones: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        
        //BotonSalir
        Icon salir = new ImageIcon(getClass().getResource("/recursos/salir_opt.png"));
        botonSalir = new JButton(" Salir ", salir);
        botonSalir.setFont(new Font("Tahoma", Font.BOLD, 20));
        botonSalir.setBackground(new Color(249, 231, 159));
        botonSalir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        botonSalir.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salir();
                }
            }   
        );
        //Agreamos el boton salir al panel Sur
        panelSur.add(botonSalir);
        //Agreamos el panel Sur al JFrame
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
    
    private void registrarObjRemotoNotificacionAdmin(){       
        try {
            objRemotoNotificacionAdmin = new AdministradorNotificacionImpl(this);
            objRemotoServidor.registrarObjetoRemotoAdministradorNotificacion(objRemotoNotificacionAdmin);
            System.out.println("\nEsperando Notificacion...");
        } catch (RemoteException ex) {
            System.out.println("Error al registrar el objeto remoto de notificaciones.");
            Logger.getLogger(GUIAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
  
    private void salir(){
        System.exit(0);
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
    
    private void buscarYMostrarFactura(){
        try {
            String seleccionLista = modeloLista.getElementAt(listaListFacturas.getSelectedIndex());
    
            String[] cadenaAux = seleccionLista.split("_");
            int numeroFacura = Integer.parseInt(cadenaAux[1]);
            
            ClsInvoice factura = objRemotoServidor.consultarFacturas(numeroFacura);
            
            areaTextoInformacion.setText("");
            if(factura != null) {
                areaTextoInformacion.append("\n\nFactura No: " + factura.getInvoiceNumber());
                areaTextoInformacion.append("\nNumero de la mesa: " + factura.getTableNumber());
                areaTextoInformacion.append("\n\nHamburguesas de tipo pequeño: " + factura.getNumberSmallBurguer());
                areaTextoInformacion.append("\nHamburguesas de tipo Mediado: " + factura.getNumberMediumBurguer());
                areaTextoInformacion.append("\nHamburguesas de tipo Grande: " + factura.getNumberBigBurguer());
                areaTextoInformacion.append("\n\nCosto sin IVA del pedido: " + factura.getCostWithoutIVA());
                areaTextoInformacion.append("\nIVA del pedido: " + factura.getCostIVA());
                areaTextoInformacion.append("\nCosto con IVA del pedido: " + factura.getCostWithIVA());
                areaTextoInformacion.append("\n* * * ");
            }
            else{
                areaTextoInformacion.setText("\nNo se encontro su factura.");
            }
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Se presento algun problema inesperado", "Error", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(GUIAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarNotificacionAdmin(int numFactura) {
        String numeroFactura = "factura_" + numFactura;
        areaTextoNotificacion.setText("");
        areaTextoNotificacion.setText("\nSe realizo un nuevo pedido y su factura es: \n" + numeroFactura);
        modeloLista.addElement(numeroFactura);
    } 
}
