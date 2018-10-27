package disparos;

import java.awt.Graphics;

import Estructuras.ListaSimple;
import Estructuras.Nodo;
import Logica.Game;


public class ControlDisparo {
	public ListaSimple<Proyectil> listaProyectil = new ListaSimple<Proyectil>();
	public ListaSimple<Proyectil> listaProyectilEnemigos = new ListaSimple<Proyectil>();
	protected Game game;
	
	/**
	 * Constructor
	 * @param game Juego
	 */
	public ControlDisparo(Game game) {
		this.game = game;
	}
	/**
	 * Añade una bala a la lista de balas
	 * @param x Posicion en el eje x
	 * @param y Posicion en el eje y
	 */
	public void addProyectil( float x, float y) {
		listaProyectil.add(new Proyectil(game, x, y,true));
	} 
	public void addProyectilEnemigos( float x, float y) {
		listaProyectilEnemigos.add(new Proyectil(game, x, y,false));
	} 
	/**
	 * Borra la bala de la lista.
	 * @param bullet Bala a borrar.
	 */
	public void removeProyectil(Proyectil proyectil) {
		listaProyectil.delete(proyectil);
	}
	public void removeProyectilEnemigos(Proyectil proyectil) {
		listaProyectilEnemigos.delete(proyectil);
	}
	/**
	 * Renderiza y actualiza variables de todas las balas
	 * @param g Componente grafico
	 */
	public void update(Graphics g) {
		for(Nodo<Proyectil> p = listaProyectil.getPrimero();p != null;p = p.getSiguiente() ) {
			p.getValor().update();
			p.getValor().render(g);
		}
		for(Nodo<Proyectil> p = listaProyectilEnemigos.getPrimero();p != null;p = p.getSiguiente() ) {
			p.getValor().update();
			p.getValor().render(g);
		}
	}
}