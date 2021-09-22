package servidorPedidos.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase Pedido, contiene los atributos para un pedido.
 * @author Alexander Inagan, Jeison Ortiz
 */
public class Pedido implements Serializable{
    
    //Declaracion de atributos
    private int numeroMesa;
    private ArrayList<HamburguesaPedidoDTO> listaHamburguesas;
    
    /**
     * Constructor de la clase Pedido
     */
    public Pedido() {
        this.listaHamburguesas = new ArrayList<>();
    }
    
    /**
     * Permite ingresar una hamburguesa.
     * @param parHamburguesa 
     */
    public void agregarHamburguesa(HamburguesaPedidoDTO parHamburguesa){
        this.getListaHamburguesas().add(parHamburguesa);
    }
    
    /**
     * Elimina una hamburguesa de la lista
     * @param posicion 
     */
    public void eliminarHamburguesa(int posicion) {
        this.listaHamburguesas.remove(posicion);
    }

    /**
     * Permite obtener la lista de hamburguesas
     * @return 
     */
    public ArrayList<HamburguesaPedidoDTO> getListaHamburguesas() {
        return listaHamburguesas;
    }
    
    /**
     * Método que vacia la lista de hamburguesas.
     */
    public void vaciarListaHamburguesas() {
        this.listaHamburguesas.clear();
    }

    /**
     * Metodo que permite obtener el numero de la mesa.
     * @return 
     */
    public int getNumeroMesa() {
        return numeroMesa;
    }

    /**
     * Metodo que permite cambiar el numero de la mesa.
     * @param numeroMesa 
     */
    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
}