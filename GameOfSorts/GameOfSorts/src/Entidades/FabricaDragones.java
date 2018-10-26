package Entidades;
import Estructuras.ListaSimple;
import Estructuras.Nodo;
import Logica.Game;
import bitacora.subnivel.LoggerDragon;
import disparos.ControlDisparo;
import graficos.Assets;

import java.util.Random;
import java.util.logging.Logger;

import Entidades.Dragon;


public class FabricaDragones {
	LoggerDragon loggerDragon = new LoggerDragon();
	public final static Logger LOGGER = Logger.getLogger("bitacora.Bitacora");
	private Game game;
	public ListaSimple<Dragon> lista = new ListaSimple<Dragon>();
	
	public FabricaDragones(Game game) {
		this.game = game;
		
	}
	/*
	 * Eliminar sets del dragon
	 * Solo asignar imagen y posicion x,y 
	 */
	public Dragon nuevoDragon (int tipo,String id,ControlDisparo cd, float x,float y) {
		Dragon dragon = new Dragon(game, id, tipo,cd, x, y);
		if (tipo == 0) {
			Random random = new Random();
			int VelocidadDisp = random.nextInt(100)+5;
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(3);
			dragon.setContadorDisparo(VelocidadDisp*4);
			dragon.setVelocidadDisp(VelocidadDisp);
			dragon.setSprites(Assets.dragon1);
			dragon.setEdad(generarEdad());
			dragon.setClase("Comandante");
			loggerDragon.dragonNuevo(dragon.getClase());
			lista.add(dragon);
			return dragon;
		}
		
		else if (tipo == 2) {
			Random random = new Random();
			int VelocidadDisp = random.nextInt(100)+5;
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(2);
			dragon.setContadorDisparo(VelocidadDisp*3);
			dragon.setVelocidadDisp(VelocidadDisp);
			dragon.setSprites(Assets.dragon2);
			dragon.setEdad(generarEdad());
			dragon.setClase("Capitan");
			loggerDragon.dragonNuevo(dragon.getClase());
			lista.add(dragon);
			return dragon;
		}
		else if (tipo == 1) {
			Random random = new Random();
			int VelocidadDisp = random.nextInt(100)+5;
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(1);
			dragon.setContadorDisparo(VelocidadDisp*2);
			dragon.setVelocidadDisp(VelocidadDisp);
			dragon.setSprites(Assets.dragon3);
			dragon.setEdad(generarEdad());
			dragon.setClase("Infanteria");
			loggerDragon.dragonNuevo(dragon.getClase());
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
	/*public void graficarArbol(ListaSimple D) {
		for (Nodo <Dragon> d = D.getPrimero(); d != null; d = d.getSiguiente()) {
			if (d.getLeft()!= null) {
				if(d.getValor().getY()>0 && d.getValor().getY()<980) {
					d.getLeft().setX(d.getValor().getX()+100);
					d.getLeft().setY(d.getValor().getY()+100);
				}
				else {
					d.getLeft().setX(d.getValor().getX()+100);
					
				}
			}
			if (d.getRight()!= null) {
				if(d.getValor().getY():0 && d.getValor().getY()<980) {
					d.getRight().setX(d.getValor().getX()+100);
					d.getRight().setY(d.getValor().getY()-100);
					
				}
			}
		}
		
	}*/
	
	public void removeDragon(Dragon dragon) {
		lista.delete(dragon);
		loggerDragon.dragonEliminado(dragon.getID());
	} 

}