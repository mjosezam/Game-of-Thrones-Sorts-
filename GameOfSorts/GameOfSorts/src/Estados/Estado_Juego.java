package Estados;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;

import Entidades.Jugador;
import Estructuras.Nodo;
import Logica.Game;
import Logica.Launcher;
import disparos.ControlDisparo;
import disparos.Proyectil;
import graficos.Assets;
import graficos.Fondo;
import Entidades.Creature;
import Entidades.Dragon;
import Entidades.FabricaDragones;
import java.awt.Rectangle;



public class Estado_Juego extends Estado {
	private Jugador jugador;
	private ControlDisparo cd;
	private FabricaDragones fabDrag;
	private int contadorFondo,xFondo=0;
	private boolean choque = true;
	public Estado_Juego(Game juego) {
		super(juego);
		this.cd = new ControlDisparo(juego);
		this.jugador = new Jugador(juego,100,100, cd);
		this.fabDrag = new FabricaDragones(juego);
		for (int i=500;i<1500;i+=200) {
			for (int j=200; j<201;j+=100) {
			fabDrag.nuevoDragon(i%3, "A", i,j ); 
		}
	}
}
	
	public void colisionProyectil() {//cuando el proyectil se sale de la pantalla se borra
		for(Nodo<Proyectil> p = cd.listaProyectil.getPrimero(); p != null; p = p.getSiguiente()) {
			if(p.getValor().getX()>Launcher.ANCHO) {
				cd.removeProyectil(p.getValor());
			} 
		}
	}
	public void colisionJugador() {
		for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
			Rectangle hitboxDragon = d.getValor().getHitbox();
			Rectangle hitboxJugador = jugador.getHitbox();
				if(hitboxJugador.intersects(hitboxDragon)&&choque) {
					choque=false;
					jugador.setSalud(jugador.getSalud()-1);
					}
				if(d.getValor().getX()<=0) {
					jugador.setSalud(jugador.getSalud()-1);
					fabDrag.removeDragon(d.getValor());
				}
			}
		}
	
	/**
	 * Revisa las colisiones entre misiles y dragones 
	 */
	public void colision() {//CAMBIAR COLISIONES DEL JUGADOR CON LOS DRAGONES
		for (Nodo <Proyectil>p = cd.listaProyectil.getPrimero(); p != null; p = p.getSiguiente()){
		for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
			Rectangle hitboxDragon = d.getValor().getHitbox();
			Rectangle hitboxProyectil = p.getValor().getHitbox();
			if(hitboxProyectil.intersects(hitboxDragon)) {
				d.getValor().setSalud(d.getValor().getSalud()-1);
				cd.removeProyectil(p.getValor());
				System.out.println("colision");
				}
				if (d.getValor().getSalud()==0) {
					fabDrag.removeDragon(d.getValor());//arreglar para borrar el dragon de la pantalla
				}
			}
		}			
	}
	//Mueve el fondo
	public void updateFondo(Graphics g) {
		if (contadorFondo % 1== 0) {
		xFondo -=2 ;
		}
		if (xFondo <= -1920) {
			xFondo=0;
		}
	}
	//Dibuja corazones de acuerdo a la vida del jugador
	public void updateVida(Graphics g) {
		int j = 200;
		if (jugador.getSalud()==2) {
			j = 150;
		}
		if (jugador.getSalud()==1) {
			j = 75;
		}
		if (jugador.getSalud()==0) {
			j = 0;
		}
		for (int i=0;i<j;i+=75) {
			g.drawImage(Assets.heart,i,0,null);
		}
	}
	
	@Override // actualiza las imagenes en pantalla
	public void update() {
		colisionProyectil();
		colisionJugador();
		colision();
		for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
			d.getValor().update();
		}
		jugador.update();
		contadorFondo++;
		if (contadorFondo % 150==0) {
			choque = true;
		}
	}

	@Override // dibuja las imagenes en pantalla
	public void render(Graphics g) {
		updateFondo(g);
		g.drawImage(Assets.cielo,xFondo,0,null);
		updateVida(g);
		for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
			d.getValor().render(g);
		}
		jugador.render(g);
		cd.update(g);
	}
}
