package Entidades;

import Logica.Game;

import java.awt.*;


public abstract class Entidad {
	protected Game game;
	protected float x,y;
	protected static int ancho = 100,alto = 100;
	/**
	 * Constructor de una entidad
	 * @param game
	 * @param x posicion en x
	 * @param y posicion en y
	 */
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
