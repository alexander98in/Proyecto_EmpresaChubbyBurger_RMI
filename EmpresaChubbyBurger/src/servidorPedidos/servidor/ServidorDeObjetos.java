package servidorPedidos.servidor;

import java.rmi.RemoteException;
import servidorPedidos.sop_rmi.GestionPedidosImpl;
import servidorPedidos.utilidades.UtilidadesConsola;
import servidorPedidos.utilidades.UtilidadesRegistroS;

/**
 * Clase por donde inicia la ejecucion del servidor de pedidos
 * @author Alexander Inagan, Jeison Ortiz
 */
public class ServidorDeObjetos {

    /**
     * Método, donde inicia la ejecucion del servidor de pedidos
     * @param args
     * @throws RemoteException 
     */
    public static void main(String[] args) throws RemoteException{
        
        int varNumPuertoNS;
        String varDireccionIpNS;
        
        System.out.println("\n\t>>> Bienvenido al servidor de pedidos <<<\n");
        
        System.out.println("Cual es la direccion ip donde se encuentra el N_S de alertas: ");
        varDireccionIpNS = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puesto por el cual escucha el N_S de alertas: ");
        varNumPuertoNS = UtilidadesConsola.leerEntero();
        
        GestionPedidosImpl objRemotoPedidosServidor = new GestionPedidosImpl();
        objRemotoPedidosServidor.consultarReferenciaRemotaDeNotificacion(varDireccionIpNS, varNumPuertoNS);
        
        try{
            UtilidadesRegistroS.arrancarNS(varNumPuertoNS);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoPedidosServidor, varDireccionIpNS, varNumPuertoNS, "gestionPedidos");          
        } catch (RemoteException e) {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrio algo inesperado. " + e.getMessage());
        }
    }
}
