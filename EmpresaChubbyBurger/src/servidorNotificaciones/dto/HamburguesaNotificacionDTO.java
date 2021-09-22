package servidorNotificaciones.dto;

import java.io.Serializable;

/**
 * Clase que contiene la información sobre la notificacion de un pedido.
 * @author Alexander Inagan, Jeison Ortiz
 */
public class HamburguesaNotificacionDTO implements Serializable {

    private char atrTipo;
    private int atrCantidadIngredientesExtra;

    public HamburguesaNotificacionDTO() {
        super();
    }
    
    /**
     * Constructor de la clase HamburguesaNotificacion
     * @param parTipo
     * @param parCantidadIngredientesExtra 
     */
    public HamburguesaNotificacionDTO(char parTipo, int parCantidadIngredientesExtra) {
        this.atrTipo = parTipo;
        this.atrCantidadIngredientesExtra = parCantidadIngredientesExtra;
    }

    /**
     * @return the atrTipo
     */
    public char getAtrTipo() {
        return atrTipo;
    }

    /**
     * @param atrTipo the atrTipo to set
     */
    public void setAtrTipo(char atrTipo) {
        this.atrTipo = atrTipo;
    }

    /**
     * @return the atrCantidadIngredientesExtra
     */
    public int getAtrCantidadIngredientesExtra() {
        return atrCantidadIngredientesExtra;
    }

    /**
     * @param atrCantidadIngredientesExtra the atrCantidadIngredientesExtra to
     * set
     */
    public void setAtrCantidadIngredientesExtra(int atrCantidadIngredientesExtra) {
        this.atrCantidadIngredientesExtra = atrCantidadIngredientesExtra;
    }
}
