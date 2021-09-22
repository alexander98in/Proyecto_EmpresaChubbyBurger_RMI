package servidorPedidos.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import servidorPedidos.dto.ClsInvoice;

/**
 *
 * @author Leyder Alexander Inagan
 */
public class ClsFacturaDAO implements Serializable{
    
    public ClsFacturaDAO() {
        super();
    }
    
    public static int ultimaFactura(){
        File fichero = new File("facturas");
        String[] nombresArchivos = fichero.list();
        return nombresArchivos.length;
    }

    public boolean registrarFactura(ClsInvoice refFactura) {
        boolean bandera = false;
        try {
            File archivo = new File("facturas/factura_" + refFactura.getInvoiceNumber() + ".txt");
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(refFactura.toString());
            escribir.close();
            bandera = true;
        } catch (IOException e) {
            System.out.println("ERROR: No se pudo registrar la factura... " + e.getMessage());
        }
        return bandera;
    }

    public ClsInvoice consultarFactura(int parNumeroFctura) {
        ClsInvoice facturaRetornar;
        try {
            FileReader leer = new FileReader("facturas/factura_" + parNumeroFctura + ".txt");
            BufferedReader contenido = new BufferedReader(leer);
            String texto;

            texto = contenido.readLine();
            String[] atributos = (texto.replace(" ", "")).split(":");
            facturaRetornar = new ClsInvoice();
            facturaRetornar.setInvoiceNumber(Integer.parseInt(atributos[1]));
            texto = contenido.readLine();
            atributos = (texto.replace(" ", "")).split(":");
            facturaRetornar.setTableNumber(Integer.parseInt(atributos[1]));
            texto = contenido.readLine();
            atributos = (texto.replace(" ", "")).split(":");
            facturaRetornar.setNumberSmallBurguer(Integer.parseInt(atributos[1]));
            texto = contenido.readLine();
            atributos = (texto.replace(" ", "")).split(":");
            facturaRetornar.setNumberMediumBurguer(Integer.parseInt(atributos[1]));
            texto = contenido.readLine();
            atributos = (texto.replace(" ", "")).split(":");
            facturaRetornar.setNumberBigBurguer(Integer.parseInt(atributos[1]));
            texto = contenido.readLine();
            atributos = (texto.replace(" ", "")).split(":");
            facturaRetornar.setCostWithoutIVA(Float.parseFloat(atributos[1]));
            texto = contenido.readLine();
            atributos = (texto.replace(" ", "")).split(":");
            facturaRetornar.setCostIVA(Float.parseFloat(atributos[1]));
            texto = contenido.readLine();
            atributos = (texto.replace(" ", "")).split(":");
            facturaRetornar.setCostWithIVA(Float.parseFloat(atributos[1]));
            return facturaRetornar;
        } catch (IOException | NumberFormatException e) {
            System.out.println("EROOR al consultar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        return null;
    }
}
