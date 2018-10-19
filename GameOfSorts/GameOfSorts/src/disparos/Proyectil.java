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
	/**
	 * Crea una nueva bola de fuego
	 * @param game Juego
	 * @param x Posicion en x
	 * @param y Posicion en y
	 * @param width Grosor
	 * @param height Altura
	 */
	public Proyectil(Game game, float x, float y) {
		super(game, x, y);
		this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho(),getAlto());
	}
	/**
	 * Actualiza sus variables
	 */
	@Override
	public void update() {
		this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho(),getAlto());
		xMove = 0;
		move();
	} 

	/**
	 * Renderiza la bala
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.fireball, (int)x, (int)y, null);
	}
	/**
	 * Mueve la bala
	 */
	public void move() {
		x+=Jugador.velocidadDisp;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
	
}