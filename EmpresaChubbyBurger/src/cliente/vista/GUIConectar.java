package cliente.vista;

import cliente.utilidades.UtilidadesRegistroC;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import servidorPedidos.sop_rmi.GestionPedidosInt;

/**
 *
 * @author Leyder Alexander Inagan
 */
public class GUIConectar extends JFrame {
    
    private GestionPedidosInt objRemotoServidor;
    
    private final BorderLayout esquemaBorderLayout;
    private final GridLayout esquemaGridLayout;
    private final FlowLayout esquemaFlowLayout;
    private final JPanel panelCentral;
    private final JPanel panelNorte;
    private final JPanel panelSur;
    
    private final JTextField campoTextoNumeroMesa;
    private final JTextField campoTextoDirIP;
    private final JTextField campoTextoPuerto;
    
    private final JTextArea areaInformacion;
    
    private final JLabel etiquetaTitulo;
    private final JLabel etiquetaNumeroMesa;
    private final JLabel etiquetaDirIP;
    private final JLabel etiquetaPuerto;
    
    private final JButton botonIngresar;
    private final JButton botonSalir; 
  
    
    public GUIConectar() {
        super("Conectar RMI");
        
        esquemaBorderLayout = new BorderLayout(5,5);
        setLayout(esquemaBorderLayout);
        
        //Panel que se ubica en la parte norte.
        panelNorte = new JPanel();
        panelNorte.setLayout(new GridLayout(1, 2, 5, 5));
        
        String informacion = " La siguiente aplicación le permitira realizar pedidos\n"
                + " de hamburguesas, modificar su pedido, listar y realizar\n"
                + " el pago respectivo \n";
        
        areaInformacion = new JTextArea(informacion, 6, 10);
        areaInformacion.setEditable(false);
        areaInformacion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        areaInformacion.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        areaInformacion.setBackground(new Color(167, 217, 217));
        
        etiquetaTitulo = new JLabel("Chubby Burger", SwingConstants.CENTER);
        etiquetaTitulo.setToolTipText("Titulo");
        etiquetaTitulo.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        etiquetaTitulo.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 38));
        etiquetaTitulo.setBackground(new Color(235, 235, 183));
        etiquetaTitulo.setOpaque(true);
 
        panelNorte.add(etiquetaTitulo);
        panelNorte.add(areaInformacion);
      
        this.add(this.panelNorte, BorderLayout.NORTH);
    
        //Panel que se ubica en la parte central.
        panelCentral = new JPanel();
        esquemaGridLayout = new GridLayout(3, 3, 5, 5);
        panelCentral.setLayout(esquemaGridLayout);
           
        campoTextoNumeroMesa = new JTextField();
        campoTextoNumeroMesa.setFont(new Font("Tahoma", Font.PLAIN, 20));
        campoTextoDirIP = new JTextField();
        campoTextoDirIP.setFont(new Font("Tahoma", Font.PLAIN, 20));
        campoTextoPuerto = new JTextField();
        campoTextoPuerto.setFont(new Font("Tahoma", Font.PLAIN, 20));
    
        etiquetaNumeroMesa = new JLabel("Numero de la Mesa:  ", SwingConstants.RIGHT);
        etiquetaNumeroMesa.setToolTipText("Ingrese el nickName del usuario");
        etiquetaNumeroMesa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        etiquetaNumeroMesa.setFont(new Font("Tahoma", Font.BOLD, 16));
        etiquetaNumeroMesa.setBackground(new Color(164, 202, 164));
        etiquetaNumeroMesa.setOpaque(true);
                
        etiquetaDirIP = new JLabel("Dirección IP del rmiRegistry:  ", SwingConstants.RIGHT);
        etiquetaDirIP.setToolTipText("Ingrese la direccion IP del rmiRegistry");
        etiquetaDirIP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        etiquetaDirIP.setFont(new Font("Tahoma", Font.BOLD, 16));
        etiquetaDirIP.setBackground(new Color(164, 202, 164));
        etiquetaDirIP.setOpaque(true);
        
        etiquetaPuerto = new JLabel("Puerto del rmiRegistry:  ", SwingConstants.RIGHT);
        etiquetaPuerto.setToolTipText("Ingrese el puerto del rmiRegistry");
        etiquetaPuerto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        etiquetaPuerto.setFont(new Font("Tahoma", Font.BOLD, 16));
        etiquetaPuerto.setBackground(new Color(164, 202, 164));
        etiquetaPuerto.setOpaque(true);

        panelCentral.add(etiquetaNumeroMesa);
        panelCentral.add(campoTextoNumeroMesa);
        panelCentral.add(etiquetaDirIP);
        panelCentral.add(campoTextoDirIP);
        panelCentral.add(etiquetaPuerto);
        panelCentral.add(campoTextoPuerto);
        
        this.add(this.panelCentral, BorderLayout.CENTER);
        
        //Panel que se ubica en la parte sur
        panelSur = new JPanel();
        esquemaFlowLayout = new FlowLayout();
        panelSur.setLayout(esquemaFlowLayout);
        panelSur.setBackground(new Color(151, 187, 187));
        
        Icon conectar = new ImageIcon(getClass().getResource("/recursos/conectar.png"));
        botonIngresar = new JButton(" Ingresar ", conectar);
        botonIngresar.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonIngresar.setBackground(new Color(159, 198, 238));
        botonIngresar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        botonIngresar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    conectar();
                }
            }
        );
        panelSur.add(this.botonIngresar);
       
        Icon Salir = new ImageIcon(getClass().getResource("/recursos/salir_opt.png"));
        botonSalir = new JButton("  Salir  ", Salir);
        botonSalir.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonSalir.setBackground(new Color(212, 147, 147));
        botonSalir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        botonSalir.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Procesa evento de BotonSalir
                @Override
                public void actionPerformed(ActionEvent evento) {
                    cerrarAplicacion();
                }
            }
        
        );
        panelSur.add(this.botonSalir);
        
        this.add(this.panelSur, BorderLayout.SOUTH); 
        
    }
     
    private void cerrarAplicacion() {
        System.exit(0);
    }
    
    private void conectar() {
        boolean seObtuvoObjRemoto;
        int camposValidos = 0;
        String direccionIpRMIRegistry;
        String numeroPuertoString;
        int numeroMesaIngresado;
        int numPuertoRMIRegistry;
        
        if(campoTextoNumeroMesa.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un numero de la mesa", "Error numero mesa", JOptionPane.INFORMATION_MESSAGE);
            campoTextoDirIP.requestFocus();
        }
        else {
            camposValidos += 1;
        }
        
        if(campoTextoDirIP.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una direccion IP para el rmiRegistry", "Error Direccion IP", JOptionPane.INFORMATION_MESSAGE);
            campoTextoDirIP.requestFocus();
        }
        else {
            camposValidos += 1;
        }
      
        if(campoTextoPuerto.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un puerto para el rmiRegistry", "Error Puerto", JOptionPane.INFORMATION_MESSAGE);
            campoTextoPuerto.requestFocus();
        }
        else {
            camposValidos += 1;
        }
        
        if(camposValidos == 3) {
           
            direccionIpRMIRegistry = this.campoTextoDirIP.getText();
            numeroPuertoString = this.campoTextoPuerto.getText();
            numPuertoRMIRegistry = Integer.parseInt(numeroPuertoString);
            numeroMesaIngresado = Integer.parseInt(this.campoTextoNumeroMesa.getText());
            
            System.out.println("Numero Mesa: " + numeroMesaIngresado);
            System.out.println("DIR: " + direccionIpRMIRegistry);
            System.out.println("Puerto: " + numPuertoRMIRegistry);
             
            objRemotoServidor = (GestionPedidosInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry, "gestionPedidos");
        
            if(objRemotoServidor != null) {
               
                seObtuvoObjRemoto = true;
                this.campoTextoDirIP.setEditable(false);
                this.campoTextoPuerto.setEditable(false);
                           
                if(seObtuvoObjRemoto) {
                    GUICliente vistaCliente = new GUICliente(objRemotoServidor, numeroMesaIngresado);
                    vistaCliente.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    vistaCliente.setSize(850, 620);
                    vistaCliente.setVisible(true);
                    vistaCliente.setLocationRelativeTo(null);
                    this.setVisible(false);
                }
                
            }
            else {
                JOptionPane.showMessageDialog(this, "Ocurrio un problema con la obtención del objeto remoto, vuelve a ingresar los datos",
                    "Error Objeto Remoto", JOptionPane.ERROR_MESSAGE);
                campoTextoDirIP.setText("");
                campoTextoPuerto.setText("");
                campoTextoDirIP.requestFocus();
            }       
        }
    }
}
