package servidorPedidos.utilidades;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Clase que permite obtener un objeto remoto desde el cliente.
 * @author Alexander Inagan, Jeison Ortiz
 */
public class UtilidadesRegistroC {
    
    /**
     * Método que permite obtener un objeto remoto.
     * @param dirIP
     * @param puerto
     * @param nameObjReg
     * @return Remote, objeto remoto
     */
    public static Remote obtenerObjRemoto(String dirIP,int puerto, String nameObjReg)
    {
        String URLRegistro;
        URLRegistro  = "rmi://" + dirIP + ":" + puerto + "/"+nameObjReg;
        try
        {
            return Naming.lookup(URLRegistro);
        }
        catch (MalformedURLException | NotBoundException | RemoteException e)
        {
            System.out.println("Excepcion en obtencion del objeto remoto" + e.getMessage());
            return null;
        }
    } 
}
