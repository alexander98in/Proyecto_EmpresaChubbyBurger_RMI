package servidorPedidos.sop_rmi;

import administrador.sop_rmi.AdministradorNotificacionInt;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;
import servidorPedidos.dto.Pedido;
import servidorPedidos.dto.ClsDatosEmpresa;
import servidorPedidos.dto.ClsInvoice;

/**
 * Interface del servidor de pedidos donde se declaran los metodos remotos.
 * Hereda de la clase Remote, la cual la convierte en una interfaz remota.
 * @author Alexander Inagan, Jeison Ortiz
 */
public interface GestionPedidosInt extends Remote{
    
    public ClsDatosEmpresa obtenerDatosEmpresa() throws RemoteException;
    
    public Hashtable obtenerPrecios() throws RemoteException;
    
    public ClsInvoice registrarPedidoSistema(Pedido objPedido) throws RemoteException;
    
    public ClsInvoice consultarFacturas(int parNumfactura)throws RemoteException;
    
    public void registrarObjetoRemotoAdministradorNotificacion(AdministradorNotificacionInt objRemotoAdmin) throws RemoteException;
    
}