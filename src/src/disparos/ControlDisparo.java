package disparos;

import java.awt.Graphics;


import Estructuras.ListaSimple;
import Estructuras.Nodo;
import Logica.Game;


public class ControlDisparo {
	/**
	 * creacion de las listas para manejo de misiles del jugador y los dragones
	 */
	public ListaSimple <Proyectil> listaProyectil = new ListaSimple<Proyectil>();
	public ListaSimple <Proyectil> listaProyectilEnemigos = new ListaSimple<Proyectil>();
	protected Game game;
	
	/**
	 * Constructor
	 * @param game
	 */
	public ControlDisparo(Game game) {
		this.game = game;
	}
	/**
	 * Crea un proyectil y lo añade a la lista de proyectiles del jugador
	 * @param x Posicion en el eje x
	 * @param y Posicion en el eje y
	 */
	public void addProyectil( float x, float y) {
		listaProyectil.add(new Proyectil(game, x, y,true));
	} 
	/**
	 * Crea los proyectiles de los dragones y los agrega a la lista
	 * @param x Posicion en X donde se crea el misil
	 * @param y Posicion en Y donde se crea el misil
	 */
	public void addProyectilEnemigos( float x, float y) {
		listaProyectilEnemigos.add(new Proyectil(game, x, y,false));
		
	} 
	/**
	 * Borra el proyectil de la lista del jugador
	 * @param proyectil a borrar.
	 */
	public void removeProyectil(Proyectil proyectil) {
		listaProyectil.delete(proyectil);
	}
	/**
	 * Borra el proyectil de la lista de proyectiles enemigos
	 * @param proyectil a eliminar
	 */
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