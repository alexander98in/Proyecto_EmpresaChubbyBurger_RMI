package servidorPedidos.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorPedidos.dto.ClsDatosEmpresa;

/**
 *
 * @author Leyder Alexander Inagan, Jeison Ortiz
 */
public class ClsDatosEmpresaDAO {
    
    private ClsDatosEmpresa objEmpresa;

    public ClsDatosEmpresaDAO() {
        this.objEmpresa = new ClsDatosEmpresa();
    }
    
    public ClsDatosEmpresa obtenerEmpresa()
    {
        try {
            this.objEmpresa = obtenerEmp();
        } catch (IOException ex) {
            Logger.getLogger(ClsDatosEmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.objEmpresa;
    }
    
    private ClsDatosEmpresa obtenerEmp() throws FileNotFoundException, IOException
    {
        String rutaArchivo = "Empresa.txt";
        FileReader lector = new FileReader("empresa/"+rutaArchivo);
        BufferedReader contenido = new BufferedReader(lector);
     
        String cadena1 = contenido.readLine();
        String cadena2 = contenido.readLine();
        ClsDatosEmpresa objDatosEmp = new ClsDatosEmpresa();
        objDatosEmp.setNit(cadena1);
        objDatosEmp.setNombreEmpresa(cadena2);
        
        return objDatosEmp;
    }
}
