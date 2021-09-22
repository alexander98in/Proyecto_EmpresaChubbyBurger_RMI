package servidorNotificaciones.servidor;

import java.rmi.RemoteException;
import servidorNotificaciones.sop_rmi.GestionNotificacionImpl;
import servidorNotificaciones.utilidades.UtilidadesConsola;
import servidorNotificaciones.utilidades.UtilidadesRegistroS;

/**
 * Clase ServidorDeObjetos, donde se ejecuta el servidor de Notificaciones y se 
 * registra el objeto remoto en el NS
 * @author Alexander Inagan, Jeison Ortiz
 */
public class ServidorDeObjetos {
    
    /**
     * Metodo por donde inicia la ejecución
     * @param args
     * @throws RemoteException 
     */
    public static void main(String[] args) throws RemoteException{
        int varNumPuertoNS;
        String varDireccionIpNS;
        
        System.out.println("\n\t>>> Bienvenido al servidor de notificaciones <<<\n");
        
        System.out.println("Cual es la direccion ip donde se encuentra el N_S: ");
        varDireccionIpNS = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puesto por el cual escucha el N_S: ");
        varNumPuertoNS = UtilidadesConsola.leerEntero();
        
        GestionNotificacionImpl objRemotoNotificaciones = new GestionNotificacionImpl();
        
        try {
            UtilidadesRegistroS.arrancarNS(varNumPuertoNS);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoNotificaciones, varDireccionIpNS, varNumPuertoNS, "gestionNotificaciones");
        } catch (RemoteException e) {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrio algo inesperado. " + e.getMessage());
        }  
    }   
}
