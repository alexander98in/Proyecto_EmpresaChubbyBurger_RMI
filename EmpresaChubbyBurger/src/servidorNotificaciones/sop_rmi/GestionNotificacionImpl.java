package servidorNotificaciones.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidorNotificaciones.dto.Pedido;

/**
 * Clase que implementa la interfaz remota y donde se implementas los metodos remotos.
 * @author Alexander Inagan, Jeison Ortiz
 */
public class GestionNotificacionImpl extends UnicastRemoteObject implements GestionNotificacionInt {

    /**
     * Constructor de la clase NotificacionImpl
     * @throws RemoteException 
     */
    public GestionNotificacionImpl() throws RemoteException{
        super();
    }

    /**
     * Metodo remoto, permite notificar un registro de un pedido.
     * @param objNotificacion
     * @throws RemoteException 
     */
    @Override
    public void notificarRegistro(Pedido objNotificacion) throws RemoteException {
        System.out.println("\nInvocando al metodo de notificar registro\n\n");
//        vistaNotificacion.mostrarNotificacionAdmin(objNotificacion);
        mostrarPedido(objNotificacion);  
    }
    
    /**
     * Metodo local, que permite mostrar el pedido que llega en la notificacion.
     * @param objNotificacion 
     */
    private void mostrarPedido(Pedido objNotificacion) {
        System.out.println("\t\t>>>>>> NUEVO PEDIDO <<<<<<\n");
        System.out.println("No de mesa: " + objNotificacion.getNumeroMesa());
        System.out.println("Las hamburguesas pedidas fueron: \n");
        
        for (int i = 0; i < objNotificacion.getListaHamburguesas().size(); i++){
            System.out.println("Hamburguesa No: " + (i+1));
            System.out.println("Tipo: " + mostrarTipoHamburguesa(objNotificacion.getListaHamburguesas().get(i).getAtrTipo()));
            System.out.println("Cantidad: " + objNotificacion.getListaHamburguesas().get(i).getAtrCantidadIngredientesExtra());
        }
    }
    
    /**
     * Muestra el tipo de la Hamburguesa.
     * @param parTipo
     * @return 
     */
    private String mostrarTipoHamburguesa(char parTipo){
        switch(parTipo){
            case 'p':
                return "PequeÃ±a.";
            case 'm':
                return "Mediana.";
            case 'g':
                return "Grande.";
            default:
                return "Ninguna.";
        }
    }
}
