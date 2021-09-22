package administrador.sop_rmi;

import administrador.vista.GUIAdministrador;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Leyder Alexander Inagan
 */
public class AdministradorNotificacionImpl extends UnicastRemoteObject implements AdministradorNotificacionInt {
    
    GUIAdministrador vistaAdministrador;
    
    public AdministradorNotificacionImpl(GUIAdministrador vistaAdmin) throws RemoteException {
        super();
        vistaAdministrador = vistaAdmin;
    }
    
    @Override
    public void mostrarNotificacion(int numFactura) throws RemoteException {
        System.out.println("\nInvocando al metodo mostrar notificacion.");
        vistaAdministrador.mostrarNotificacionAdmin(numFactura);
    }
    
}
