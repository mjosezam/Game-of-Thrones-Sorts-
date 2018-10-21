package Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

import Logica.Game;
import disparos.ControlDisparo;
import graficos.Assets;
import graficos.Animacion;

public class Jugador extends Creature{
	public static final int velocidadDisp = 4;
	private Game juego;
	private Rectangle hitbox;
	private ControlDisparo controlDisp;
	private int contador,contadorIteracion;
	private boolean puedeDisparar=true;
	
	public Jugador(Game juego,float x, float y,ControlDisparo cd) {
		super(juego, x, y);
		this.ancho = 100;
		this.alto = 100;
		this.juego = juego;
		this.salud = 3;
		this.velocidad = 5;
		this.controlDisp = cd;
		this.ancho = 96;
		this.alto = 96;
		this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho(),getAlto());
	}

	@Override
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
	public void render(Graphics g) {
		g.drawImage(Assets.jugadorVolando[this.contador],(int)x,(int)y,getAncho(),getAlto(),null);
		g.drawRect((int)hitbox.getX(),(int)hitbox.getY(),(int)hitbox.getWidth(),(int)hitbox.getHeight());
		controlDisp.update(g);
	}
	public void updateHitbox() {
		this.hitbox = new Rectangle((int)getX(),(int)getY()+25,100,50);
		}	
	private void getInput() {
		movimientoX = 0;
		movimientoY = 0;
		//Limitacion del movimiento en la pantalla
		if(this.x<1) {
			x = 2;
		}
		else if(this.x>1700) {
			x = 1699;
		}
		if (this.y<1) {
			y = 2;
		}
		else if(this.y > 900) {
			y = 899;
		}
		//Desplazamiento del jugador
		if (juego.getControles().arriba) {
			movimientoY = -velocidad;
		}
		if (juego.getControles().abajo) {
			movimientoY = velocidad;
		}
		if (juego.getControles().der) {
			movimientoX = velocidad;
		}
		if (juego.getControles().izq) {
			movimientoX = -velocidad;
		}
		if(juego.getControles().space) {
				if(puedeDisparar==true){
				controlDisp.addProyectil(this.x+75, this.y+70);
				puedeDisparar=false ;
				}
			}
		if(!juego.getControles().space) {
			puedeDisparar=true;
		}
	}
	public int getVelocidadDisp() {
		return velocidadDisp;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
}
