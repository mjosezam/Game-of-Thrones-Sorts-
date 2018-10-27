package disparos;

import java.awt.Graphics;

import Entidades.Entidad;
import Entidades.Jugador;
import Logica.Game;
import graficos.Assets;
import java.awt.Rectangle;

public class Proyectil extends Entidad{

	protected float xMove;
	@Override
	public void setAlto(int alto) {
		super.setAlto(20);
	}
	@Override
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
	 * @param width Grosor
	 * @param height Altura
	 */
	public Proyectil(Game game, float x, float y,boolean jugador) { //Si jugador es true es disparo del jugador, si no es del enemigo
		super(game, x, y);
		this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho()-70,getAlto()-80);
		this.jugador = jugador;

	}
	/**
	 * Actualiza sus variables
	 */
	@Override
	public void update() {
		this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho()-70,getAlto()-80);
		xMove = 0;
		move();
	}

	/**
	 * Renderiza la bala
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.fireball, (int)x, (int)y, null);
		g.drawRect((int)hitbox.getX(),(int)hitbox.getY(),(int)hitbox.getWidth(),(int)hitbox.getHeight());
	}
	/**
	 * Mueve la bala
	 */
	public void move() {
		if (jugador) {
			x+=Jugador.velocidadDisp;
		}
		else {
			x-=Jugador.velocidadDisp;
		}
	}
	public Rectangle getHitbox() {
		return hitbox;
	}

}