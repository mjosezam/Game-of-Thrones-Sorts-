/*
 * Partes de este codigo fueron tomadas de
 *-ForeignGuyMike. “Java 2D Game Programming Platformer Tutorial - Part 1 - The Game State Manager.” 
 * YouTube, YouTube, 13 Apr. 2013, www.youtube.com/watch?v=9dzhgsVaiSo&list=PL-2t7SM0vDfcIedoMIghzzgQqZq45jYGv.
 * -CodeNMore. “4 - Threads & Game Loop - New Beginner 2D Game Programming.” 
 * YouTube, YouTube, 11 Dec. 2014, www.youtube.com/watch?v=vFRuEgEdO9Q&index=4&list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ.
 */
package Logica;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

import bitacora.*;
import Entidades.Dragon;
import Entidades.FabricaDragones;
import Estados.Estado;
import Estados.Estado_GameOver;
import Estados.Estado_Juego;
import Estados.Estado_Menu;
import Estructuras.ListaSimple;
import Estructuras.Nodo;
import disparos.Proyectil;
import graficos.Assets;
import graficos.Display;

public class Game implements Runnable {
	private Display display;
	
	public String titulo;
	private int ancho;
	private int alto;
	
	private boolean ejecutando = false;
	private Thread hilo;
	
	private BufferStrategy bs;//sirve para evitar el parpadeo de las imagenes
	private Graphics g;

	
	//Estados del juego
	private Estado estadoJuego;
	private Estado estadoMenu;
	private Estado estadoGameOver;

	
	//Input
	private Controles manejoControles;
	private MouseManager mouseManager;
	/**
	 * Constructor del juego
	 * @param titulo, titulo del juego
	 * @param ancho, ancho de la pantalla
	 * @param alto, alto de la pantalla
	 */
	public Game(String titulo,int ancho,int alto) {
		this.ancho = ancho;
		this.alto = alto;
		this.titulo = titulo;
		manejoControles = new Controles();	
		mouseManager= new MouseManager();
	}
	/**
	 * incializacion de los componentes del display, instanciacion de los estados.
	 */
	private void init() {
		display = new Display(titulo,ancho,alto);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getFrame().addKeyListener(manejoControles);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getCanvas().addKeyListener(manejoControles);
		display.getCanvas().addMouseListener(mouseManager);
		Assets.init();//inicializa todas las imagenes 1 vez
		/*
		 * Message mensaje = new Message();
		 * ListaSimple lista = mensaje.generate();
		 * Enviar y recibir la lista vacia a Mariana 
		 */
		estadoJuego = new Estado_Juego(this);
		estadoMenu = new Estado_Menu(this);
		estadoGameOver = new Estado_GameOver(this);
		Estado.setEstado(estadoMenu);//se establece el estado que se quiere

	}
	
	/**
	 * actualiza el juego segun el estado en el que se encuentre
	 */
	private void update() {//actualiza en el estado en el que se encuentre
		manejoControles.update();//actualiza al jugador de acuerdo a sus controles
		if (Estado.getEstado()!=null) {
			Estado.getEstado().update();
		}
	}
	/**
	 * dibuja en pantalla
	 */
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, ancho, alto); //limpia todo lo que hay en pantalla
		if (Estado.getEstado()!=null) { //Se dibuja lo que hay en la pantalla de acuerdo al estado
			Estado.getEstado().render(g);
		}
		bs.show();
		g.dispose();
	}
	public void run() {//se ejecuta el hilo
		
		init();
		int fps = 60;
		double timePerTick = 1000000000/fps;//cuantas veces se quiere que se actualice en 1 segundo
		double delta = 0;//tiempo que queda antes de actualizar nuevamente
		long now;//tiempo actual de la computadora en el que se llama
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(ejecutando) {
			
			now = System.nanoTime();//tiempo actual
			
			/*tiempo que paso desde la ultima vez que se asigno un valor a now
			sirve para determinar cada cuanto actualizar la pantalla*/
			delta += (now - lastTime)/timePerTick;
			timer += now - lastTime; 
			lastTime = now;
			
			if(delta >=1 ) {
			update();
			render();
			ticks++;
			delta--;
			}
			if (timer >= 1000000000) {
				System.out.println(ticks);
				ticks = 0;
				timer = 0;
			}
		}
		parar();
	}
	
	public Controles getControles() {
		return manejoControles;
	}
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public synchronized void iniciar() {
		if (ejecutando){
			return;
		}
		else {
		ejecutando = true;
		hilo = new Thread(this);//se inicializa esta clase con el hilo
		hilo.start();//inicio del hilo
		}
	}
	public synchronized void parar() {
		if (!ejecutando) {
			return;
		}
		else {
		ejecutando = false;
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
}
