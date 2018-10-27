package Estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entidades.Jugador;
import Estructuras.ListaSimple;
import Estructuras.Nodo;
import Logica.Game;
import bitacora.subnivel.LoggerDragon;
import bitacora.subnivel.LoggerJugador;
import disparos.ControlDisparo;
import disparos.Proyectil;
import graficos.Assets;
import Entidades.Dragon;
import Entidades.FabricaDragones;

public class Estado_Juego extends Estado {
	public final static Logger LOGGER = Logger.getLogger("bitacora.Bitacora");
	private Jugador jugador;
	private ControlDisparo cd;
	private FabricaDragones fabDrag;
	private int contadorFondo,xFondo=0;
	private boolean choque = true;
	private Color colorLetras,colorPuntaje;
	private Font fuenteLetras,fuentePuntaje;
	private String infoDragon;
	private Estado gameOverState;
	LoggerJugador loggerJugador = new LoggerJugador();

	public Estado_Juego(Game juego) {
		super(juego);

		colorLetras = Color.WHITE;
		fuenteLetras = new Font ("Century Gothic",Font.PLAIN,19);
		colorPuntaje = Color.RED;
		fuentePuntaje = new Font("Century Gothic",Font.BOLD,50);

		this.cd = new ControlDisparo(juego);
		this.jugador = new Jugador(juego,100,100, cd);
		this.fabDrag = new FabricaDragones(juego);

		/**
		 * for nodo in lista de maria, d.getTipo(), d.FabDrag.nuevoDrag(d.getTipo());
		 **/
		for (int i=500;i<1000;i+=200) {
			for (int j=200; j<601;j+=100) {

				fabDrag.nuevoDragon(j%3, "A",cd, i+200,j );
			}
		}

	}
	/**
	 * Verifica colisiones con los bordes de la pantalla y entre proyectiles
	 */
	public void colisionProyectil() {
		for (Nodo <Proyectil>pe = cd.listaProyectilEnemigos.getPrimero(); pe != null; pe = pe.getSiguiente()) {
			Rectangle hitboxProyectilEnemigo = pe.getValor().getHitbox();
			if (pe.getValor().getX()<=0) {
				cd.removeProyectilEnemigos(pe.getValor());
			}

			for(Nodo<Proyectil> p = cd.listaProyectil.getPrimero(); p != null; p = p.getSiguiente()) {
				Rectangle hitboxProyectil = p.getValor().getHitbox();
				if(hitboxProyectil.intersects(hitboxProyectilEnemigo)) {
					cd.removeProyectil(p.getValor());
					cd.removeProyectilEnemigos(pe.getValor());
				}

			}
		}

		for(Nodo<Proyectil> p = cd.listaProyectil.getPrimero(); p != null; p = p.getSiguiente()) {
			if(p.getValor().getX()>1460) {
				cd.removeProyectil(p.getValor());
			}
		}
	}

	public void colisionJugador() {
		int contador = 0;
		for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
			Rectangle hitboxDragon = d.getValor().getHitbox();
			Rectangle hitboxJugador = jugador.getHitbox();
			if(hitboxJugador.intersects(hitboxDragon)&&choque) {
				choque=false;
				jugador.setSalud(jugador.getSalud()-1);
			}
			if(d.getValor().getX()<=0) {
				contador++;
				System.out.println(d);
				if (contador==1) {
					fabDrag.removeDragon(d.getValor());
					jugador.setSalud(jugador.getSalud()-1);
				}
				else {
					gameOverState = new Estado_GameOver(juego);
					loggerJugador.Muerte();
					Estado.setEstado(gameOverState);
				}
				update();
				//Enviar lista a Mariana
			}
			for (Nodo <Proyectil>pe = cd.listaProyectilEnemigos.getPrimero(); pe != null; pe = pe.getSiguiente()) {
				Rectangle hitboxProyectilEnemigo = pe.getValor().getHitbox();
				if (hitboxJugador.intersects(hitboxProyectilEnemigo)) {
					jugador.setSalud(jugador.getSalud()-1);
					cd.removeProyectilEnemigos(pe.getValor());
				}
			}
		}
	}

	public void disparoDragones() {
		for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
			d.getValor().disparoDragones();
		}
	}

	/**
	 * Revisa las colisiones entre misiles y dragones 
	 */
	public void colision() {
		for (Nodo <Proyectil>p = cd.listaProyectil.getPrimero(); p != null; p = p.getSiguiente()){
			for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
				Rectangle hitboxDragon = d.getValor().getHitbox();
				Rectangle hitboxProyectil = p.getValor().getHitbox();
				if(hitboxProyectil.intersects(hitboxDragon)) {
					d.getValor().setSalud(d.getValor().getSalud()-1);
					cd.removeProyectil(p.getValor());
				}
				if (d.getValor().getSalud()<=0) {
					fabDrag.removeDragon(d.getValor());
					update();
					//Enviar lista a Mariana
					jugador.setPuntaje(jugador.getPuntaje()+1);
				}
			}
		}
	}
	/**
	 * Mueve el fondo
	 * @param g indica donde dibujar
	 */
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
		if (jugador.getSalud()<=0) {
			j = 0;
			gameOverState = new Estado_GameOver(juego);
			loggerJugador.Muerte();
			Estado.setEstado(gameOverState);
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
		disparoDragones();
		for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
			Rectangle hitboxDragon = d.getValor().getHitbox();
			d.getValor().update();
			Point punto = new Point(juego.getMouseManager().getMouseX(),juego.getMouseManager().getMouseY());
			if(juego.getMouseManager().isLeftPressed()) {
				if (hitboxDragon.contains(punto)) {
					infoDragon = d.getValor().getDatos();
					System.out.println(infoDragon);
				}
			}
		}
		jugador.update();
		contadorFondo++;
		if (contadorFondo % 150==0) {
			choque = true;
		}
	}
	/*
	 * Funcion que separa el string cada /n. Tomado de https://stackoverflow.com/questions/4413132/problems-with-newline-in-graphics2d-drawstring
	 */
	void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	@Override // dibuja las imagenes en pantalla
	public void render(Graphics g) {
		updateFondo(g);
		g.drawImage(Assets.cielo,xFondo,0,null);
		g.drawImage(Assets.Hud,1480,0,null);
		g.drawImage(Assets.puntaje,250,0,null);
		g.setColor(colorPuntaje);
		g.setFont(fuentePuntaje);
		g.drawString(String.valueOf(jugador.getPuntaje()),450,50);
		g.setColor(colorLetras);
		g.setFont(fuenteLetras);
		if(infoDragon !=null) {
			drawString(g,infoDragon, 1490, 130);

			System.out.println(jugador.getPuntaje());
		}

		updateVida(g);
		for (Nodo <Dragon> d = fabDrag.lista.getPrimero(); d != null; d = d.getSiguiente()) {
			d.getValor().render(g);
			//d.getValor().getControlDisp().update(g);
		}
		jugador.render(g);
		cd.update(g);
	}
}