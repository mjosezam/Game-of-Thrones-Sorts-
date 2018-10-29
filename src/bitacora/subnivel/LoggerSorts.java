package bitacora.subnivel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerSorts {
	private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Utilidades");

	public void tipoSort() {
		LOGGER.log(Level.SEVERE, "Ordenamiento por:");
	}
}