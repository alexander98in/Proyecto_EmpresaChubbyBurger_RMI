package administrador.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface remota del Administrador
 * @author Leyder Alexander Inagan
 */
public interface AdministradorNotificacionInt extends Remote{
    /**
     * Método remoto del Administrador, que muestra la notificación
     * @throws RemoteException 
     */
    public void mostrarNotificacion(int numFactura)throws RemoteException;
}
