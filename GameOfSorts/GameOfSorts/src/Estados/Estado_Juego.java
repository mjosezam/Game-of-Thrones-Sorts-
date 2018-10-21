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
	private Creature fondoJuego;
	private ControlDisparo cd;
	private FabricaDragones fabDrag;
	private int contadorFondo,xFondo=0;
	private Fondo fondo;
	public Estado_Juego(Game juego) {
		super(juego);
		this.cd = new ControlDisparo(juego);
		this.jugador = new Jugador(juego,100,100, cd);
		this.fabDrag = new FabricaDragones(juego);
		this.fondo = new Fondo(juego, 0, 0);
		for (int i=500;i<1500;i+=200) {
			for (int j=200; j<1000;j+=100) {
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
	
	public void colision() {
		for (Nodo <Proyectil>p = cd.listaProyectil.getPrimero(); p != null; p = p.getSiguiente()){
			for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
				Rectangle hitboxProyectil = p.getValor().getHitbox();
				Rectangle hitboxDragon = d.getValor().getHitbox();
				Rectangle hitboxJugador = jugador.getHitbox();
				if(hitboxProyectil.intersects(hitboxDragon)) {
					d.getValor().setSalud(d.getValor().getSalud()-1);
					cd.removeProyectil(p.getValor());
					System.out.println("colision");
				}
				if(hitboxJugador.intersects(hitboxDragon)) {
					d.getValor().setSalud(d.getValor().getSalud()-1);
					jugador.setSalud(jugador.getSalud()-1);
					System.out.println("colision2");
						
				}
				if (d.getValor().getSalud()==0) {
					fabDrag.removeDragon(d.getValor());//arreglar para borrar el dragon de la pantalla
				}
			}
		}			
	}
	public void updateFondo(Graphics g) {
		if (contadorFondo % 1== 0) {
		xFondo -=2 ;
		}
		if (xFondo <= -1920) {
			xFondo=0;
		}
		
	}
	
	@Override
	public void update() {
		colisionProyectil();
		colision();
		for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
			d.getValor().update();
		}		
		jugador.update();
		contadorFondo++;
	}

	@Override
	public void render(Graphics g) {
		updateFondo(g);
		g.drawImage(Assets.cielo,xFondo,0,null);
		g.drawImage(Assets.heart,100,100,null);
		for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
			d.getValor().render(g);
		}
		jugador.render(g);
		cd.update(g);
	}
}
