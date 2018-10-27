package Estados;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import Logica.Game;
import graficos.Assets;
public class Estado_GameOver extends Estado {
	private Estado estadoJuego;
	private Estado estadoMenu;
	
	public Estado_GameOver(Game juego) {
		super(juego);
		estadoJuego = new Estado_Juego(juego);
		estadoMenu = new Estado_Menu(juego);
	}
	
	@Override
	public void update() {
		Rectangle retry = new Rectangle(0,900,200,200);
		Rectangle quit = new Rectangle(1525,900,400,200);	
		Point punto = new Point(juego.getMouseManager().getMouseX(),juego.getMouseManager().getMouseY());
		if(juego.getMouseManager().isLeftPressed()) {
			if (retry.contains(punto)) {
				Estado.setEstado(estadoJuego);
			}
			if(quit.contains(punto)) {
				Estado.setEstado(estadoMenu);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.gameOver,0,0,null);
	}

}
