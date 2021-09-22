package cliente;

import cliente.vista.GUIConectar;
import javax.swing.JFrame;

/**
 *
 * @author Leyder Alexander Inagan
 */
public class ClienteDeObjeos {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUIConectar vistaConectar = new GUIConectar();
        vistaConectar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaConectar.setSize(680, 380);
        vistaConectar.setVisible(true);
        vistaConectar.setLocationRelativeTo(null);     
    }
}
