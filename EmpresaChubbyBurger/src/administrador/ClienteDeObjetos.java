package administrador;

import administrador.vista.GUIConectarAdmin;
import javax.swing.JFrame;

/**
 *
 * @author Leyder Alexander Inagan
 */
public class ClienteDeObjetos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUIConectarAdmin vistaConectarAdmin = new GUIConectarAdmin();
        vistaConectarAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaConectarAdmin.setSize(650, 320);
        vistaConectarAdmin.setVisible(true);
        vistaConectarAdmin.setLocationRelativeTo(null);
    }
    
}
