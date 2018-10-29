package Entidades; /**package Entidades;

import Estructuras.ListaSimple;
import Estructuras.Nodo;
import Logica.Game;
import Trees.LinkedlistIS;
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
	 * Eliminar sets del dragon Solo asignar imagen y posicion x,y
	 */
	/**
	 * Se crea un dragon segun el tipo y se le asignan sus stats e imagen
	 * 
	 * @param tipo tipo de dragon a crear
	 * @param      id, id unico
	 * @param      cd, control de disparo
	 * @param x    posicion en X
	 * @param y    posicion en Y
	 * @return devuelve un dragon y lo agrega a la lista
	 **/
	/**
	public Dragon nuevoDragon(String clase, String id, ControlDisparo cd, Dragon parent, float x, float y, int edad) {
		Dragon dragon = new Dragon(game, id, clase, cd, parent, x, y, edad);
		if (tipo == 0) {
			Random random = new Random();
			int VelocidadDisp = random.nextInt(100) + 45;
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(3);
			dragon.setContadorDisparo(VelocidadDisp * 4);
			dragon.setVelocidadDisp(VelocidadDisp);
			dragon.setSprites(Assets.dragon1);
			dragon.setEdad(generarEdad());
			dragon.setClase("Comandante");
			loggerDragon.dragonNuevo(dragon.getClase());
			return dragon;
		}

		else if (tipo == 2) {
			Random random = new Random();
			int VelocidadDisp = random.nextInt(100) + 45;
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(2);
			dragon.setContadorDisparo(VelocidadDisp * 3);
			dragon.setVelocidadDisp(VelocidadDisp);
			dragon.setSprites(Assets.dragon2);
			dragon.setEdad(generarEdad());
			dragon.setClase("Capitan");
			loggerDragon.dragonNuevo(dragon.getClase());
			lista.add(dragon);
			return dragon;
		} else if (tipo == 1) {
			Random random = new Random();
			int VelocidadDisp = random.nextInt(100) + 45;
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(1);
			dragon.setContadorDisparo(VelocidadDisp * 2);
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

	/**
	 * Asigna edad a los dragones del 1 al 1000 y verifica que no se repita
	 * 
	 * @return edad para asignar al dragon
	 */
	/**public int generarEdad() {
		Random random = new Random();
		int edad = random.nextInt(1000) + 15;
		for (Nodo<Dragon> d = lista.getPrimero(); d != null; d = d.getSiguiente()) {
			if (edad == d.getValor().getEdad()) {
				generarEdad();
			}
		}
		return edad;
	}

	/**
	 * Elimina un dragon de la lista
	 * 
	 * @param dragon, dragon a eliminar
	 */
	/**public void removeDragon(Dragon dragon) {
		lista.delete(dragon);
		loggerDragon.dragonEliminado(dragon.getID());
	}

}**/