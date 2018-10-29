package Estados;

import Logica.Game;
import graficos.Assets;

import java.awt.*;

public class Estado_GameOver extends Estado {
	private Estado estadoJuego;
	private Estado estadoMenu;
	/**
	 * Constructor del estado de Game Over
	 * @param juego
	 */
	public Estado_GameOver(Game juego) {
		super(juego);
		estadoJuego = new Estado_Juego(juego);
		estadoMenu = new Estado_Menu(juego);
	}
	@Override
	/**
	 * Crea los rectangulos para salir o reintentar
	 * Verifica las posiciones del mouse
	 */
	public void update() {
		Rectangle retry = new Rectangle(0,900,200,200);
		Rectangle quit = new Rectangle(1525,900,400,200);	
		Point punto = new Point(juego.getMouseManager().getMouseX(),juego.getMouseManager().getMouseY());
		if(juego.getMouseManager().isLeftPressed()) {
			if (retry.contains(punto)) {//reinicia el juego
				Estado.setEstado(estadoJuego);
			}
			if(quit.contains(punto)) {//regresa al menu
				Estado.setEstado(estadoMenu);
			}
		}
	}

	@Override
	/**
	 * dibuja en la pantalla
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.gameOver,0,0,null);
	}
}
