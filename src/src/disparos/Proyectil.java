package disparos;

import java.awt.Graphics;

import Entidades.Entidad;
import Entidades.Jugador;
import Estructuras.ListaSimple;
import Logica.Game;
import graficos.Assets;
import java.awt.Rectangle;

public class Proyectil extends Entidad{

	protected float xMove;
	@Override
	/**
	 *Se establece el alto de la imagen del proyectil
	 */
	public void setAlto(int alto) {
		super.setAlto(20);
	}
	@Override
	/**
	 * Se establece el ancho de la imagen del proyectil
	 */
	public void setAncho(int ancho) {
		super.setAncho(20);
	}
	private Rectangle hitbox;
	private boolean jugador;
	/**
	 * Crea una nueva bola de fuego
	 * @param game Juego
	 * @param x Posicion en x 
	 * @param y Posicion en y
	 * @param jugador, indica si el misil es del jugador o el enemigo
	 */
	public Proyectil(Game game, float x, float y,boolean jugador) { //Si jugador es true es disparo del jugador, si no es del enemigo
		super(game, x, y);
		this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho()-70,getAlto()-80);//establece el hitbox del proyectil
		this.jugador = jugador;

	}
	/**
	 * Actualiza el misil, su posicion y el hitbox de acuerdo a esta
	 */
	@Override
	public void update() {
		this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho()-70,getAlto()-80);
		xMove = 0;
		move();
	} 

	/**
	 * Dibuja el proyectil
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.fireball, (int)x, (int)y, null);
		g.drawRect((int)hitbox.getX(),(int)hitbox.getY(),(int)hitbox.getWidth(),(int)hitbox.getHeight());
	}
	/**
	 * Mueve la el proyectil
	 */
	public void move() {
		if (jugador) {//Movimiento a la derecha si es el jugador
			x+=Jugador.velocidadDisp;
		}
		else {//Moviento al a izquierda si es el enemigo
			x-=Jugador.velocidadDisp;
		}	
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
	
}