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
import Entidades.Dragon;
import Entidades.FabricaDragones;
import graficos.Fondo;
import java.awt.Rectangle;



public class Estado_Juego extends Estado {
	private Jugador jugador;
	private Dragon dragon1,dragon2,dragon3;
	private ControlDisparo cd;
	private FabricaDragones fabDrag;
	private int contadorFondo;
	
	public Estado_Juego(Game juego) {
		super(juego);
		this.cd = new ControlDisparo(juego);
		this.jugador = new Jugador(juego,100,100, cd);
		this.fabDrag = new FabricaDragones(juego);
		this.dragon1 = fabDrag.nuevoDragon(1, "A", 300,300 );
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
				if(p.getValor().getHitbox().intersects(d.getValor().getHitbox())) {
					d.getValor().setSalud(d.getValor().getSalud()-1);
					System.out.println("colision");	
				}
				else {
					System.out.println(p.getValor().getHitbox()+"proyectil");
					System.out.print(d.getValor().getHitbox()+"dragon");	
				}
				if (d.getValor().getSalud()==0) {
					fabDrag.removeDragon(d.getValor());
				}
			}
		}
				
	}
	@Override
	public void update() {
		colisionProyectil();
		dragon1.update();
		jugador.update();
		colision();
		contadorFondo++;
	}

	@Override
	public void render(Graphics g) {
		//g.drawImage(Assets.cielo,0,0,null);
		dragon1.render(g);
		jugador.render(g);
		cd.update(g);
	}
}
