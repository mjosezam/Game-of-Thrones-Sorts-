package Estados;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import Logica.Game;
import graficos.Assets;

public class Estado_GameOver extends Estado {
	private Estado estadoJuego;
	private Estado estadoMenu;

	/**
	 * Constructor del estado de Game Over
	 * 
	 * @param juego
	 */
	public Estado_GameOver(Game juego) {
		super(juego);
	}

	@Override
	/**
	 * Crea los rectangulos para salir o reintentar Verifica las posiciones del
	 * mouse
	 */
	public void update() {
		Rectangle retry = new Rectangle(0, 900, 200, 200);
		Rectangle quit = new Rectangle(1525, 900, 400, 200);
		Point punto = new Point(juego.getMouseManager().getMouseX(), juego.getMouseManager().getMouseY());
		if (juego.getMouseManager().isLeftPressed()) {
			if (retry.contains(punto)) {// reinicia el juego
				estadoJuego = new Estado_Juego(juego);
				Estado.setEstado(estadoJuego);
			}
			if (quit.contains(punto)) {// regresa al menu
				estadoMenu = new Estado_Menu(juego);
				Estado.setEstado(estadoMenu);
			}
		}
	}

	@Override
	/**
	 * dibuja en la pantalla
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.gameOver, 0, 0, null);
	}
}
