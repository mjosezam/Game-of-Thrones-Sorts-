package Entidades;

import Logica.Game;

public abstract class Creature extends Entidad {
	
	public static int saludBase = 1;
	public static final int velocidadBase = 5;
	
	protected int salud;
	protected float velocidad;
	protected float movimientoX;
	protected float movimientoY;
	protected int tipo;
	/**
	 * Constructor de criatura
	 * @param game
	 * @param x posicion X
	 * @param y posicion Y
	 */
	public Creature(Game game,float x, float y) {
		super(game,x, y);
		//Tiene salud, velocidad de movimiento
		saludBase = salud;
		velocidad = velocidadBase;
		movimientoX=0;
		movimientoY=0;
	}
	/**
	 * movimiento de la criatura
	 */
	public void move() {
		x += movimientoX;
		y += movimientoY;
	}
	//Gets y Sets
	public float getMovimientoX() {
		return movimientoX;
	}

	public void setMovimientoX(float movimientoX) {
		this.movimientoX = movimientoX;
	}

	public float getMovimientoY() {
		return movimientoY;
	}

	public void setMovimientoY(float movimientoY) {
		this.movimientoY = movimientoY;
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public float getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}
}
	

