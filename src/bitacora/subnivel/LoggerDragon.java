package bitacora.subnivel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerDragon {
    private final static Logger LOGGER = Logger.getLogger("bitacora.bitacora.subnivel.Control");
    
    public void dragonNuevo(String tipo){
        LOGGER.log(Level.INFO, "Creaci�n Drag�n de tipo: " + tipo);
    }
    public void dragonEliminado(String id){
        LOGGER.log(Level.INFO, "Se elimin� el Drag�n: " + id);
    }

}