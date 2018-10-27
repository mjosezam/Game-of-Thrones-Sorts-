package bitacora.subnivel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerJugador {
    private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Utilidades");

    public void Muerte(){
        LOGGER.log(Level.SEVERE, "Murió el jugador, ha finalizado la partida");
    }

}
