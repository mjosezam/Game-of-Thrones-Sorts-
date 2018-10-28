/*
 * Parte de este codigo fue recopilado de:
 * D. (2018, January 25). Logs en Java con Java.util.logging. 
 * Retrieved from https://hashblogeando.wordpress.com/2015/08/22/logs-en-java-con-java-util-logging/
 */
package Logica;
import bitacora.subnivel.LoggerDragon;
import bitacora.subnivel.LoggerSorts;
import bitacora.subnivel.under.InternalSys;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Launcher {
    private final static Logger LOG_RAIZ = Logger.getLogger("bitacora");
    private final static Logger LOG_SUBNIVEL = Logger.getLogger("bitacora.subnivel");
    private final static Logger LOG_UNDER = Logger.getLogger("bitacora.subnivel.under");

    // El log para ESTA clase en particular
    public final static Logger LOGGER = Logger.getLogger("bitacora.Bitacora");
	public static final int ANCHO = 1920, ALTO = 1080;
	public static int cont = 0;

	public static void main (String[]args) {
		
        try {
            // Los handler (manejadores) indican a donde mandar la salida ya sea consola o archivo
        	// En este caso ConsoleHandler envia los logs a la consola
            Handler consoleHandler = new ConsoleHandler();
            // Con el manejador de archivo, indicamos el archivo donde se mandaran los logs
            // El segundo argumento controla si se sobre escribe el archivo o se agregan los logs al final
            // Para sobre escribir pase un true para agregar al final, false para sobre escribir
            // todo el archivo
            Handler fileHandler = new FileHandler("./bitacora.log", true);

            // El formateador indica como presentar los datos, en este caso usaremos el formaro sencillo
            // el cual es mas facil de leer, si no usamos esto el log estara en formato xml por defecto
            SimpleFormatter simpleFormatter = new SimpleFormatter();


            // Asignamos los handlers previamente declarados al log *raiz* esto es muy importante ya que
            // permitira que los logs de todas y cada una de las clases del programa que esten en ese paquete
            // o sus subpaquetes se almacenen en el archivo y aparezcan en consola
            LOG_RAIZ.addHandler(consoleHandler);
            LOG_RAIZ.addHandler(fileHandler);

            // Indicamos a partir de que nivel deseamos mostrar los logs, podemos especificar un nivel en especifico
            // para ignorar informacion que no necesitemos
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);

            LOGGER.log(Level.INFO, "Inicia partida");

            // Creamos los objetos de las otras clases
            LoggerDragon loggerDragon = new LoggerDragon();
            LoggerSorts loggerSorts = new LoggerSorts();
            InternalSys internalSys = new InternalSys();

            // Estas llamadas se registraran en el log
            LOGGER.log(Level.INFO, "Llamadas a los componentes del sistema");
            
              /**
               * loggerDragon.dragonNuevo();
               *loggerSorts.tipoSort();
               *internalSys.mensajeSecreto();
              */
            LOGGER.log(Level.INFO, "Probando manejo de excepciones");
            try{
                     throw new Exception("ERROR DE CONTROL DE FLUJO DE PROGRAMA"); 
            }catch(Exception e){
            // Mediante el metodo getStack obtenemos el stackTrace de la excepcion en forma de un objecto String
            // de modo que podamos almacenarlo en bitacora para su analisis posterior
            LOGGER.log(Level.SEVERE, Launcher.getStackTrace(e) ); 
    }
} catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error de IO");
} catch (SecurityException ex) {
            LOGGER.log(Level.SEVERE, "Error de Seguridad");
}
		
		Game juego = new Game("Game of Sorts",ANCHO,ALTO);
		juego.iniciar();
	}
	public static String getStackTrace(Exception e) {
	    StringWriter sWriter = new StringWriter();
	    PrintWriter pWriter = new PrintWriter(sWriter);
	    e.printStackTrace(pWriter);
	    return sWriter.toString();
	}
}
