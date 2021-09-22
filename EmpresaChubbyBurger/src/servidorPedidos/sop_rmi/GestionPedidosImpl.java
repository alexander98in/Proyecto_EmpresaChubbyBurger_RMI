package servidorPedidos.sop_rmi;

import administrador.sop_rmi.AdministradorNotificacionInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import servidorNotificaciones.dto.HamburguesaNotificacionDTO;
import servidorNotificaciones.sop_rmi.GestionNotificacionInt;
import servidorPedidos.dao.ClsDatosEmpresaDAO;
import servidorPedidos.dao.ClsFacturaDAO;
import servidorPedidos.dao.ClsPreciosDAO;
import servidorPedidos.dto.ClsDatosEmpresa;
import servidorPedidos.dto.ClsInvoice;
import servidorPedidos.dto.HamburguesaPedidoDTO;
import servidorPedidos.dto.Pedido;
import servidorPedidos.utilidades.UtilidadesRegistroC;

/**
 *
 * @author Jeison
 */
public class GestionPedidosImpl extends UnicastRemoteObject implements GestionPedidosInt{
    
    private GestionNotificacionInt objReferenciaRemotaNotificacion;
    private ClsDatosEmpresaDAO objEmpresaDAO;
    private ClsPreciosDAO objPreciosDAO;
    private ClsFacturaDAO objFacturaDAO;
    private Hashtable<String, Float> atrPrecios;
    
    private ArrayList<AdministradorNotificacionInt> listaObjRemotosNotificacionAdmin;

    public GestionPedidosImpl() throws RemoteException
    {
        this.objEmpresaDAO = new ClsDatosEmpresaDAO();
        this.objPreciosDAO = new ClsPreciosDAO();
        this.objFacturaDAO = new ClsFacturaDAO();
        this.atrPrecios = new Hashtable<>();
        this.listaObjRemotosNotificacionAdmin = new ArrayList<>();
    }

    @Override
    public ClsDatosEmpresa obtenerDatosEmpresa() throws RemoteException {
        System.out.println("\nSe ingreso al metodo obtener datos de la empresa...");
        return this.objEmpresaDAO.obtenerEmpresa();
    }

    @Override
    public Hashtable<String, Float> obtenerPrecios() throws RemoteException {
        System.out.println("\nSe ingreso al metodo obtener precios...");
        this.atrPrecios = this.objPreciosDAO.obtenerTablaPrecios();
        return this.atrPrecios;
    }

    @Override
    public ClsInvoice consultarFacturas(int parNumfactura) throws RemoteException {
        System.out.println("\nSe ingreso al metodo consultar factura...");
        return this.objFacturaDAO.consultarFactura(parNumfactura);
    }

    @Override
    public ClsInvoice registrarPedidoSistema(Pedido objPedido) throws RemoteException {
        System.out.println("Se ingreso al metodo registrar pedido en el sistema...");
        //Generamos una factura del pedido
        ClsInvoice objInvoice = generarFactura(objPedido);
        //Guardamos una factura en un archvo txt
        this.objFacturaDAO.registrarFactura(objInvoice);
        servidorNotificaciones.dto.Pedido objPedidoNot = llenarPedidoNotificaciones(objPedido);
        this.objReferenciaRemotaNotificacion.notificarRegistro(objPedidoNot);
        
        for(int i=0; i<listaObjRemotosNotificacionAdmin.size(); i++) {
            listaObjRemotosNotificacionAdmin.get(i).mostrarNotificacion(objInvoice.getInvoiceNumber());
        }
               
        return objInvoice;
    }
    
    @Override
    public void registrarObjetoRemotoAdministradorNotificacion(AdministradorNotificacionInt objRemotoAdmin) throws RemoteException {
        System.out.println("\nSe ingreso al metodo registrar objeto remoto Administrador...");
        this.listaObjRemotosNotificacionAdmin.add(objRemotoAdmin);
    }
    
    /**
     * Metodo que construye el objeto pedido que se manda al servidor de notificaciones.
     * @param parPedido
     * @return Pedido, para el servidor de notificaciones.
     */
    private servidorNotificaciones.dto.Pedido llenarPedidoNotificaciones(Pedido parPedido)
    {
        servidorNotificaciones.dto.Pedido objNuevoPedido = new servidorNotificaciones.dto.Pedido();
        servidorNotificaciones.dto.HamburguesaNotificacionDTO objHamburguesa;
        //Obtenemos el numero de la mesa
        objNuevoPedido.setNumeroMesa(parPedido.getNumeroMesa());
        //Llenamos la lista de hamburguesa del pedido de notificaciones.
        for(HamburguesaPedidoDTO ped : parPedido.getListaHamburguesas()){
            objHamburguesa = new HamburguesaNotificacionDTO(ped.getTipo(), ped.getCantidadIngredientesExtra());
            objNuevoPedido.agregarHamburguesa(objHamburguesa);
        }
        return objNuevoPedido;
    }
    
    private ClsInvoice generarFactura(Pedido parPedido) throws RemoteException{
        int numP = 0, numM = 0, numG = 0, ingExtra = 0;
        for (int i = 0; i < parPedido.getListaHamburguesas().size(); i++){
            if (parPedido.getListaHamburguesas().get(i).getTipo() == 'p')
                numP ++;
            if (parPedido.getListaHamburguesas().get(i).getTipo() == 'm')
                numM ++;
            if (parPedido.getListaHamburguesas().get(i).getTipo() == 'g')
                numG ++;
            ingExtra += parPedido.getListaHamburguesas().get(i).getCantidadIngredientesExtra();
        }
        float costoSinIVA = 0, IVA = 0, costoConIVA = 0;
        costoSinIVA = (numP * this.atrPrecios.get("p")) + (numM * this.atrPrecios.get("m")) + (numG * this.atrPrecios.get("g")) + (ingExtra * this.atrPrecios.get("e"));
        IVA = generarIVA(costoSinIVA, (numP + numM + numG));
        costoConIVA = costoSinIVA + IVA;
        ClsInvoice returnInvoice = new ClsInvoice(parPedido.getNumeroMesa(), numP, numM, numG, costoSinIVA, IVA, costoConIVA);
        return returnInvoice;
    }
    
    private float generarIVA(float parCosto, int parNumHamburguesas){
        float IVA = 0;
        if (parNumHamburguesas >= 1 && parNumHamburguesas <= 3) 
            IVA = ((parCosto * 5)/100);
        
        if (parNumHamburguesas >= 4 && parNumHamburguesas <= 7)
            IVA = ((parCosto * 8)/100);
        
        if (parNumHamburguesas >= 8 ){
            
            if (parCosto > 120)
                return ((parCosto * 18)/100);
            
            IVA = ((parCosto * 15) /100);
        }
        return IVA;
    }
    
    /**
     * Obtiene una referencia remota del objeto remoto en el servidor de notificaciones.
     * @param direccionIpRMIRegistry
     * @param numPuertoRMIRegistry 
     */
    public void consultarReferenciaRemotaDeNotificacion(String direccionIpRMIRegistry, int numPuertoRMIRegistry){
        this.objReferenciaRemotaNotificacion = (GestionNotificacionInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "gestionNotificaciones");
    }
}
