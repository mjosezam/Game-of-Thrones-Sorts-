package Logica;

public class Launcher {
	public static final int ANCHO = 1920, ALTO = 1080;

	public static void main (String[]args) {
		
		Game juego = new Game("Game of Sorts",1920,1080);
		juego.iniciar();
	}
}
