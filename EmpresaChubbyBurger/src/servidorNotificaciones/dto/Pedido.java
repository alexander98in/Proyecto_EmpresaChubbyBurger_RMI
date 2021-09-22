package servidorNotificaciones.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase Pedido, contiene la informacion de un pedido en el servidor de Notificaciones
 * @author Alexander Inagan, Jeison Ortiz
 */
public class Pedido implements Serializable{
    
    //Declaración de atributos
    private int numeroMesa;
    private final ArrayList<HamburguesaNotificacionDTO> listaHamburguesas;

    /**
     * Constructor de la clase Pedido
     */
    public Pedido() {
        this.listaHamburguesas = new ArrayList<>();
    }
    
    /**
     * Permite agregar una hamburguesa
     * @param parHamburguesa 
     */
    public void agregarHamburguesa(HamburguesaNotificacionDTO parHamburguesa){
        this.getListaHamburguesas().add(parHamburguesa);
    }

    /**
     * Obtiene la lista de Hamburguesas
     * @return 
     */
    public ArrayList<HamburguesaNotificacionDTO> getListaHamburguesas() {
        return listaHamburguesas;
    }

    /**
     * Metodo que permite cambiar el numero de la mesa.
     * @param numeroMesa 
     */
    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
    /**
     * Metodo que retorna el numero de la mesa.
     * @return 
     */
    public int getNumeroMesa() {
        return numeroMesa;
    }   
}