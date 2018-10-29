package Estados;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import Logica.Game;
import graficos.Assets;

public class Estado_Menu extends Estado {
	private Estado estadoJuego;

	/**
	 * constructor del estado de menu
	 * 
	 * @param juego
	 */
	public Estado_Menu(Game juego) {
		super(juego);
		/*
		 * AudioPlayer audioPlayer = new AudioPlayer(
		 * "C:\\Users\\Mario\\Desktop\\GameOfSorts\\GameOfSorts\\resources\\sonidos\\musicaFondo.mp3"
		 * ); audioPlayer.start();
		 */
	}

	@Override
	/**
	 * Crea los rectangulos para jugar y cerrar la aplicacion Revisa la posicion del
	 * mouse Cambia el estado a estado de juego
	 */
	public void update() {
		Rectangle play = new Rectangle(675, 350, 465, 125);
		Rectangle exit = new Rectangle(675, 610, 465, 125);
		Point punto = new Point(juego.getMouseManager().getMouseX(), juego.getMouseManager().getMouseY());
		if (juego.getMouseManager().isLeftPressed()) {
			if (play.contains(punto)) {
				estadoJuego = new Estado_Juego(juego);
				Estado.setEstado(estadoJuego);
			}
			if (exit.contains(punto)) {
				System.exit(0);
			}
		}
	}

	@Override
	/**
	 * Dibuja en pantalla
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.menu, 0, 0, null);
	}

}