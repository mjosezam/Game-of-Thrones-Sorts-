package graficos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Estados.Estado;
import Estados.Estado_Juego;
import Estados.Estado_Menu;
import Logica.Game;

public class Fondo extends Thread{
private int xImagen=0,cont;
private Thread hiloFondo;
private BufferedImage cielo,Hud;
private boolean ejecutando = true;
private Graphics g;


Display display;

public Fondo (Display display) {
	this.g = display.getCanvas().getGraphics();
}
@Override
public void run() {
	while (ejecutando) {
		update();
		}
	}
private void update() {
	g.drawImage(Assets.cielo,xImagen,0,null);
	g.drawImage(Assets.Hud,0,0,null);
	
}

}
