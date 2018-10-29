package Estados;

import Logica.Game;

import java.awt.*;

public abstract class Estado {
	
	private static Estado estadoActual = null;//guarda el estado que se quiere actualizar
	/**
	 * @param estado estado al que se quiere cambiar
	 */
	public static void setEstado (Estado estado) {
		estadoActual = estado;
	}
	
	public static Estado getEstado() {
		return estadoActual;
	}
	//Class
	protected Game juego;
	public Estado(Game juego) {
		this.juego = juego;
	}
	public abstract void update();
	public abstract void render(Graphics g);

}
