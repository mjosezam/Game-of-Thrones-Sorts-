package bitacora.subnivel.under;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InternalSys {
    private final static Logger LOGGER = Logger.getLogger("bitacora.bitacora.subnivel.under.InternalSys");

    public void mensajeSecreto(){
        LOGGER.log(Level.WARNING, "maria ve coco");
    }
}