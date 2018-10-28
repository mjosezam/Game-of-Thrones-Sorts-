package Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

import Logica.Game;
import disparos.ControlDisparo;
import graficos.Assets;


public class Jugador extends Creature{
	public static final int velocidadDisp = 4;
	private Game juego;
	private Rectangle hitbox;
	private ControlDisparo controlDisp;
	private int contador,contadorIteracion,puntaje;
	private boolean puedeDisparar=true;
	/**
	 * Constructor del jugador 
	 * @param juego
	 * @param x, posicion en x
	 * @param y, posicion en y
	 * @param cd, control de disparo para el manejo de los proyectiles
	 */
	public Jugador(Game juego,float x, float y,ControlDisparo cd) {
		super(juego, x, y);
		this.ancho = 100;
		this.alto = 100;
		this.juego = juego;
		this.salud = 3;
		this.velocidad = 5;
		this.controlDisp = cd;
		this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho(),getAlto());//hitbox del jugador segun su posicion
	}

	@Override
	/**
	 * actualiza constantemente la posicion del jugador
	 */
	public void update() {//mueve la imagen
		this.contadorIteracion++;
		updateHitbox();
		if(contadorIteracion%10==0) {//velocidad cambio de imagen jugador
			contador++;
			contadorIteracion = 0;
			if (contador == 4) {
				contador = 0;
			}
		}
		
		getInput();
		move();
	}
	
	@Override
	/**
	 * dibuja el jugador en pantalla
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.jugadorVolando[this.contador],(int)x,(int)y,getAncho(),getAlto(),null);
		//g.drawRect((int)hitbox.getX(),(int)hitbox.getY(),(int)hitbox.getWidth(),(int)hitbox.getHeight());
		controlDisp.update(g);
	}
	/**
	 * actualiza el hitbox del jugador segun su posicion actual
	 */
	public void updateHitbox() {
		this.hitbox = new Rectangle((int)getX(),(int)getY()+25,100,50);
		}	
	/**
	 * Cambia los valores de (x,y) del jugador y dispara de acuerdo a los controles pulsados.
	 */
	private void getInput() {
		movimientoX = 0;
		movimientoY = 0;
		//Limitacion del movimiento en la pantalla
		if(this.x<1) {
			x = 2;
		}
		else if(this.x>1385) {
			x = 1384;
		}
		if (this.y<1) {
			y = 2;
		}
		else if(this.y > 900) {
			y = 899;
		}
		//Desplazamiento del jugador
		if (juego.getControles().arriba || juego.getControles().yJoyStick()<-100) {
			movimientoY = -velocidad;
		}
		if (juego.getControles().abajo || juego.getControles().yJoyStick()>100) {
			movimientoY = velocidad;
		}
		if (juego.getControles().der || juego.getControles().xJoyStick()>100) {
			movimientoX = velocidad;
		}
		if (juego.getControles().izq || juego.getControles().xJoyStick()<-100) {
			System.out.println(juego.getControles().xJoyStick());
			movimientoX = -velocidad;

		}
		//Disparo del jugador
		if(juego.getControles().space || juego.getControles().botJoyStick()==0 ) {
				if(puedeDisparar==true){
				controlDisp.addProyectil(this.x+75, this.y+70);
				puedeDisparar=false ;
				}
			}
		if(!juego.getControles().space && juego.getControles().botJoyStick()==1 ) {
			puedeDisparar=true;
		}
	}
	public int getVelocidadDisp() {
		return velocidadDisp;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
}
