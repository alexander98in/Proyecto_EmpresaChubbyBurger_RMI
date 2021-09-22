package servidorPedidos.dto;

import java.io.Serializable;
import servidorPedidos.dao.ClsFacturaDAO;

/**
 *
 * @author Jeison
 */
public class ClsInvoice implements Serializable{
    
    private int invoiceNumber;
    private int tableNumber;
    private int numberSmallBurguer;
    private int numberMediumBurguer;
    private int numberBigBurguer;
    private float costWithoutIVA;
    private float costIVA;
    private float costWithIVA;

    public ClsInvoice() {
    }

    public ClsInvoice(int tableNumber, int numberSmallBurguer, int numberMediumBurguer, int numberBigBurguer, float costWithoutIVA, float costIVA, float costWithIVA) {        
        this.invoiceNumber = ClsFacturaDAO.ultimaFactura()+1;
        this.tableNumber = tableNumber;
        this.numberSmallBurguer = numberSmallBurguer;
        this.numberMediumBurguer = numberMediumBurguer;
        this.numberBigBurguer = numberBigBurguer;
        this.costWithoutIVA = costWithoutIVA;
        this.costIVA = costIVA;
        this.costWithIVA = costWithIVA;
    }

    /**
     * @return the invoiceNumber
     */
    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * @param invoiceNumber the invoiceNumber to set
     */
    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * @return the tableNumber
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * @param tableNumber the tableNumber to set
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     * @return the numberSmallBurguer
     */
    public int getNumberSmallBurguer() {
        return numberSmallBurguer;
    }

    /**
     * @param numberSmallBurguer the numberSmallBurguer to set
     */
    public void setNumberSmallBurguer(int numberSmallBurguer) {
        this.numberSmallBurguer = numberSmallBurguer;
    }

    /**
     * @return the numberMediumBurguer
     */
    public int getNumberMediumBurguer() {
        return numberMediumBurguer;
    }

    /**
     * @param numberMediumBurguer the numberMediumBurguer to set
     */
    public void setNumberMediumBurguer(int numberMediumBurguer) {
        this.numberMediumBurguer = numberMediumBurguer;
    }

    /**
     * @return the numberBigBurguer
     */
    public int getNumberBigBurguer() {
        return numberBigBurguer;
    }

    /**
     * @param numberBigBurguer the numberBigBurguer to set
     */
    public void setNumberBigBurguer(int numberBigBurguer) {
        this.numberBigBurguer = numberBigBurguer;
    }

    /**
     * @return the costWithoutIVA
     */
    public float getCostWithoutIVA() {
        return costWithoutIVA;
    }

    /**
     * @param costWithoutIVA the costWithoutIVA to set
     */
    public void setCostWithoutIVA(float costWithoutIVA) {
        this.costWithoutIVA = costWithoutIVA;
    }

    /**
     * @return the costIVA
     */
    public float getCostIVA() {
        return costIVA;
    }

    /**
     * @param costIVA the costIVA to set
     */
    public void setCostIVA(float costIVA) {
        this.costIVA = costIVA;
    }

    /**
     * @return the costWithIVA
     */
    public float getCostWithIVA() {
        return costWithIVA;
    }

    /**
     * @param costWithIVA the costWithIVA to set
     */
    public void setCostWithIVA(float costWithIVA) {
        this.costWithIVA = costWithIVA;
    }

    @Override
    public String toString() {
        return "Numero de factura: "+this.invoiceNumber+"\nNumero de la mesa: "+this.tableNumber+
                "\nHamburguesas Pequeñas: "+this.numberSmallBurguer+"\nHamburguesas Medianas: "+this.numberMediumBurguer+
                "\nHamburguesas Grandes: "+this.numberBigBurguer+"\nCosto sin IVA: "+this.costWithoutIVA+
                "\nCosto del IVA: "+this.costIVA+"\nCosto total con IVA: "+this.costWithIVA;
    }
    
}
