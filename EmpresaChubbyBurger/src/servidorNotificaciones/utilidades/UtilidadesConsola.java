package servidorNotificaciones.utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase de Utilidades de Consola
 * @author Alexander Inagan, Jeison Ortiz
 */
public class UtilidadesConsola {
    
    /**
     * Metodo estatico que permite leer un entero desde consola
     * @return int
     */
    public static int leerEntero()
    {
        String linea;
    	int opcion = 0;
    	boolean valido;
    	do
    	{
            try
            {
                //System.out.println("Ingrese la opcion: ");
                BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
                linea = br.readLine();
                opcion = Integer.parseInt(linea);
                valido = true;
            }
            catch(IOException | NumberFormatException e)
            {
    		System.out.println("Error intente nuevamente..." + e.getMessage());
    		valido = false;
            }
    	}while(!valido);
    	
    	return opcion;
    }
    
    /**
     * Método que permite leer un float desde consola
     * @return Float
     */
    public static float leerFloat()
    {
    	String linea;
    	float opcion = 0;
    	boolean valido;
    	do
    	{
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                linea = br.readLine();
                opcion = Float.parseFloat(linea);
                valido = true;
            }
    	    catch(IOException | NumberFormatException e)
    	    {
    		System.out.println("Error intente nuevamente..." + e.getMessage());
    		valido = false;
    	    }
    	}while(!valido);
    	
    	return opcion;
    }
    
    /**
     * Método estatico que permite leer una cadena desde consola
     * @return 
     */
    public static String leerCadena()
    {
    	String linea = "";
    	boolean valido;
    	do
    	{
            try
    	    {
                BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
                linea = br.readLine();
                valido = true;
    	    }
            catch(IOException e)
    	    {
    		System.out.println("Error intente nuevamente..." + e.getMessage());
    		valido = false;
    	    }
    	}while(!valido);	
    	return linea;
    }
}