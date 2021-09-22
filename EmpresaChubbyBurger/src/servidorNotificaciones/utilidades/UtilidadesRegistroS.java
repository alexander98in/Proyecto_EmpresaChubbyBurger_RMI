package servidorNotificaciones.utilidades;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Clase que permite registrar objetos remotos desde el servidor.
 * @author Alexander Inagan, Jeison Ortiz
 */
public class UtilidadesRegistroS
{
    /**
     * Metodo que arranca el ns
     * @param numPuertoRMI
     * @throws RemoteException 
     */
    public static void arrancarNS(int numPuertoRMI) throws RemoteException 
    {
        try
        {
            Registry registro = LocateRegistry.getRegistry(numPuertoRMI);  
            String[] vector=registro.list();
            for (String idNS : vector) {
                System.out.println("ns : "+idNS );
            }
            System.out.println("El rmiRegistry se ha obtenido y se encuentra escuchando en el puerto: " + numPuertoRMI); 
        }
        catch(RemoteException e)
        {
            System.out.println("El rmiRegistry no se localiza en el puerto: " + numPuertoRMI);
            Registry registro = LocateRegistry.createRegistry(numPuertoRMI);
            System.out.println("El registro se ha creado en el puerto: " + numPuertoRMI);
        }	
    }
        
    /**
     * Metodo que registra un objeto remoto en el ns
     * @param objetoRemoto
     * @param dirIPNS
     * @param numPuertoNS
     * @param identificadorObjetoRemoto 
     */   	
    public static void RegistrarObjetoRemoto(Remote objetoRemoto, String dirIPNS, int numPuertoNS, String identificadorObjetoRemoto)
    {
        String UrlRegistro = "rmi://"+dirIPNS+":"+numPuertoNS+"/"+identificadorObjetoRemoto;
        try
        {
            Naming.rebind(UrlRegistro, objetoRemoto);
            System.out.println("Se realizo el registro del objeto remoto en el ns ubicado en la direccion: " +dirIPNS+" y "+ "puerto"+numPuertoNS);
        } catch (RemoteException e)
        {
            System.out.println("Error en el registro del objeto remoto");
            e.printStackTrace();
        } catch (MalformedURLException e)
        {
            System.out.println("Error url invalida");
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }		
}