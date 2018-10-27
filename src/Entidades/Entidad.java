package Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

import Logica.Game;


public abstract class Entidad {
	protected Game game;
	protected float x,y;
	protected static int ancho = 100,alto = 100;
	
	public Entidad(Game game,float x,float y) {
		this.game = game;
		this.x = x;
		this.y = y;
	}
	public abstract void update();
	public abstract void render(Graphics g);
	
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	
}
