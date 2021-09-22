package servidorPedidos.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leyder Alexander Inagan
 */
public class ClsPreciosDAO {
    
    private Hashtable<String, Float> tablaPrecios;
    
    public ClsPreciosDAO() {
        this.tablaPrecios = new Hashtable();
    }
    
    public Hashtable obtenerTablaPrecios() { 
        try {
            this.tablaPrecios = obtenerTlbPrecios();
        } catch (IOException ex) {
            Logger.getLogger(ClsDatosEmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.tablaPrecios;
    }
    
    private Hashtable obtenerTlbPrecios() throws FileNotFoundException, IOException
    {
        String rutaArchivo = "Precios.txt";
        FileReader lector = new FileReader("precios/"+rutaArchivo);
        BufferedReader contenido = new BufferedReader(lector);
        
        String precio1 = contenido.readLine();
        String precio2 = contenido.readLine();
        String precio3 = contenido.readLine();
        String precio4 = contenido.readLine();
        
        Hashtable<String, Float> tblPrecios = new Hashtable<>();
        tblPrecios.put("p", Float.parseFloat(precio1));
        tblPrecios.put("m", Float.parseFloat(precio2));
        tblPrecios.put("g", Float.parseFloat(precio3));
        tblPrecios.put("e", Float.parseFloat(precio4));
        
        return tblPrecios;
    }
}