package servidorPedidos.dto;

import java.io.Serializable;

/**
 * Clase que contiene la información sobre una Hamburguesa en el servidor de pedidos
 * @author Alexander Inagan, Jeison Ortiz
 */
public class HamburguesaPedidoDTO implements Serializable{
    
    //Declaracion de atributos
    private String identificador;
    private char tipo;
    private int cantidadIngredientesExtra;
    private float precio;

    /**
     * Constructor de la clase HamburguesaPedidoDTO
     * @param parIdentificador
     * @param parTipo
     * @param parCantidadIngredientesExtra
     * @param parCosto 
     */
    public HamburguesaPedidoDTO(String parIdentificador, char parTipo, int parCantidadIngredientesExtra, float parCosto) {
        this.identificador = parIdentificador;
        this.tipo = parTipo;
        this.cantidadIngredientesExtra = parCantidadIngredientesExtra;
        this.precio = parCosto;
    }

    /**
     * Metodo que permite obtener el identificador
     * @return 
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Método que permite cambiar el identificador
     * @param identificador 
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * Metodo que permite obtener el tipo
     * @return 
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * Metodo que permite cambiar el tipo
     * @param tipo 
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que permite obtener la cantidad de ingredientes extra
     * @return 
     */
    public int getCantidadIngredientesExtra() {
        return cantidadIngredientesExtra;
    }

    /**
     * Método que permite cambiar la cantidad de ingredientes extra
     * @param cantidadIngredientesExtra 
     */
    public void setCantidadIngredientesExtra(int cantidadIngredientesExtra) {
        this.cantidadIngredientesExtra = cantidadIngredientesExtra;
    }

    /**
     * Método que permite obtener el precio
     * @return 
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Método que permite cambiar el precio
     * @param precio 
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }
}