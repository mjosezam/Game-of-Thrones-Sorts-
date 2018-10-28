package bitacora.subnivel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerDragon {
    private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
    
    public void dragonNuevo(String tipo){
        LOGGER.log(Level.INFO, "Creación Dragón de tipo: " + tipo);
    }
    public void dragonEliminado(String id){
        LOGGER.log(Level.INFO, "Se eliminó el Dragón: " + id);
    }

}