package servidorNotificaciones.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidorNotificaciones.dto.Pedido;

/**
 * Interface del servidor de Notificaciones, donde se declaran los metodos remotos.
 * Hereda de la clase Remote, la cual la convierte en una interfaz remota.
 * @author Alexander Inagan, Jeison Ortiz
 */
public interface GestionNotificacionInt extends Remote{
    
    /**
     * Metodo remoto, que notifica un registro de un pedido
     * @param objNotificacion
     * @throws RemoteException 
     */
    public void notificarRegistro(Pedido objNotificacion) throws RemoteException;
    
}