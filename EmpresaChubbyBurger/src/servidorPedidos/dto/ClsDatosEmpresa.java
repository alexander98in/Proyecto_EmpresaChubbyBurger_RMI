package servidorPedidos.dto;

import java.io.Serializable;

/**
 *
 * @author Leyder Alexander Inagan
 */
public class ClsDatosEmpresa implements Serializable {
    
    private String nit;
    private String nombreEmpresa;
    
    public ClsDatosEmpresa() {
        this("", "");
    }
    
    public ClsDatosEmpresa(String nit, String nombre) {
        this.nit = nit;
        this.nombreEmpresa = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }  
}
