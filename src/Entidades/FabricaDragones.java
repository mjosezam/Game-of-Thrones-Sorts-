package Entidades;
import Estructuras.ListaSimple;
import Estructuras.Nodo;
import Logica.Game;
import disparos.ControlDisparo;
import graficos.Assets;

import java.util.Random;

import Entidades.Dragon;


public class FabricaDragones {

	private Game game;
	public ListaSimple<Dragon> lista = new ListaSimple<Dragon>();
	
	public FabricaDragones(Game game) {
		this.game = game;
		
	}
	
	public Dragon nuevoDragon (int tipo,String id,ControlDisparo cd, float x,float y) {
		Dragon dragon = new Dragon(game, id, tipo,cd, x, y);
		if (tipo == 0) {
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(3);
			dragon.setContadorDisparo(250);
			dragon.setSprites(Assets.dragon1);
			dragon.setEdad(generarEdad());
			dragon.setVelocidadDisp(generarVelocidadDisp());
			dragon.setClase("Comandante");
			lista.add(dragon);
			return dragon;
		}
		else if (tipo == 2) {
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(2);
			dragon.setContadorDisparo(520);
			dragon.setSprites(Assets.dragon2);
			dragon.setEdad(generarEdad());
			dragon.setVelocidadDisp(generarVelocidadDisp());
			dragon.setClase("Capitan");
			lista.add(dragon);
			return dragon;
		}
		else if (tipo == 1) {
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(1);
			dragon.setContadorDisparo(140);
			dragon.setSprites(Assets.dragon3);
			dragon.setEdad(generarEdad());
			dragon.setVelocidadDisp(generarVelocidadDisp());
			dragon.setClase("Infanteria");
			lista.add(dragon);
			return dragon;
		}
		return null;
	}
	public int generarEdad() {
		Random random = new Random();
		int edad = random.nextInt(1000)+1;
		for (Nodo <Dragon> d = lista.getPrimero(); d != null; d = d.getSiguiente()) {
			if (edad == d.getValor().getEdad()) {
				generarEdad();
			}
		}
		return edad;
	}
	
	public int generarVelocidadDisp() {
		Random random = new Random();
		int VelocidadDisp = random.nextInt(100)+1;
		for (Nodo <Dragon> d = lista.getPrimero(); d != null; d = d.getSiguiente()) {
			if (VelocidadDisp == d.getValor().getVelocidadDisp()) {
				generarVelocidadDisp();
			}
		}
		return VelocidadDisp;
	}
	
	public void removeDragon(Dragon dragon) {
		lista.delete(dragon);
	} 

}