package Entidades;

import java.awt.Graphics;
import Logica.Game;
import disparos.ControlDisparo;
import graficos.Assets;
import graficos.Animacion;

public class Jugador extends Creature{
	public static final int velocidadDisp = 7;
	private Game juego;
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
	}

	@Override
	public void update() {//mueve la imagen
		this.contadorIteracion++;
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
		g.drawImage(Assets.jugadorVolando[this.contador],(int)x,(int)y,ancho,alto,null);
		controlDisp.update(g);
	}
	
	private void getInput() {
		movimientoX = 0;
		movimientoY = 0;
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
				controlDisp.addProyectil(this.x+100, this.y+25);
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
}
