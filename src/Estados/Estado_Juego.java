package Estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Servlet.Method;
import Trees.LinkedlistIS;
import Trees.ServerDragons;
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

public class Estado_Juego extends Estado {
	public final static Logger LOGGER = Logger.getLogger("bitacora.Bitacora");
	Method method = new Method();
	private Jugador jugador;
	private ControlDisparo cd;
	private ServerDragons servDrag;
	private int contadorFondo, xFondo = 0, ordenamiento;
	private boolean choque = true, flag = true;
	private Color colorLetras, colorPuntaje;
	private Font fuenteLetras, fuentePuntaje;
	private String infoDragon;
	private Estado gameOverState;
	LoggerJugador loggerJugador = new LoggerJugador();

	/**
	 * Constructor del estado de juego
	 * 
	 * @param juego
	 */
	public Estado_Juego(Game juego) {

		super(juego);
		colorLetras = Color.WHITE;
		fuenteLetras = new Font("Century Gothic", Font.PLAIN, 19);
		colorPuntaje = Color.RED;
		fuentePuntaje = new Font("Century Gothic", Font.BOLD, 50);

		this.cd = new ControlDisparo(juego);
		this.jugador = new Jugador(juego, 100, 100, cd);
		this.servDrag = new ServerDragons(juego);
		/**
		 * Preguntar: Eliminar Obtener lista para ser iterada (Cast nodo) Cambiar x, y
		 * 
		 */
		if (flag) {
			flag = false;
			servDrag.generate();
			oleadaLista(servDrag.dragons);
		}
	}
    public void oleadaLista (LinkedlistIS lista){
        method.method(servDrag.dragons);
        if (method.x < 3){
            int x = 1400,y = 50;
            for (Dragon d = servDrag.dragons.head; d != null; d = d.getNext()) {
                d.setX(x);
                d.setY(y);
                x+=150;
                if (x >= 2900){
                    y += 125;
                    x = 1400;
                }

            /*for (int i = 0; i < 1501; i += 150) {
                for (int j = 0; j < 10; j++) {
                    d.setX(1400 + i);
                    d.setY(j * 100);
                }
            }*/
            }


        }
        else{
            dibujarArbol(servDrag.dragons);
        }

    }

	public void dibujarArbol(LinkedlistIS lista){
        for (Dragon d = lista.head; d != null; d = d.getNext()) {
            if(d.getParent()==null){
                d.setX(1400);
                d.setY(500);
            }
            else if (d.getParent()!= null){
                Dragon padre = d.getParent();
                if (d == padre.son1) {//hijo 1
                    if(d.getParent().getY()<=100){//cuando los dragones se salen de la pantalla los dibujan en fila
                        d.setX(d.getParent().getX() + 150);
                        d.setY(d.getParent().getY());
                    }
                    else {//dibuja los dragones derechos hacia abajo
                        d.setX(d.getParent().getX() + 150);
                        d.setY(d.getParent().getY() + 100);
                    }
                }
                else{//hijo 2
                    if(d.getParent().getY()<=900){//cuando los dragones se salen de la pantalla los dibujan en fila
                        d.setX(d.getParent().getX() + 150);
                        d.setY(d.getParent().getY());
                    }
                    else {//dibuja los dragones izquierdos hacia arriba
                        d.setX(d.getParent().getX() + 100);
                        d.setY(d.getParent().getY() - 100);
                    }
                }
            }
        }
	}

	/**
	 * Verifica colisiones con los bordes de la pantalla y entre proyectiles
	 */
	public void colisionProyectil() {
		for (Nodo<Proyectil> pe = cd.listaProyectilEnemigos.getPrimero(); pe != null; pe = pe.getSiguiente()) {
			Rectangle hitboxProyectilEnemigo = pe.getValor().getHitbox();
			if (pe.getValor().getX() <= 0) {
				cd.removeProyectilEnemigos(pe.getValor());
			}

			for (Nodo<Proyectil> p = cd.listaProyectil.getPrimero(); p != null; p = p.getSiguiente()) {
				Rectangle hitboxProyectil = p.getValor().getHitbox();
				if (hitboxProyectil.intersects(hitboxProyectilEnemigo)) {
					cd.removeProyectil(p.getValor());
					cd.removeProyectilEnemigos(pe.getValor());
				}

			}
		}

		for (Nodo<Proyectil> p = cd.listaProyectil.getPrimero(); p != null; p = p.getSiguiente()) {
			if (p.getValor().getX() > 1460) {
				cd.removeProyectil(p.getValor());
			}
		}
	}

	/**
	 * verifica las colisiones entre el jugador y los dragones
	 */
	public void colisionJugador() {
		int contador = 0;
		for (Dragon d = servDrag.dragons.head; d != null; d = d.next) {
			Rectangle hitboxDragon = d.getHitbox();
			Rectangle hitboxJugador = jugador.getHitbox();
			if (hitboxJugador.intersects(hitboxDragon) && choque) {
				choque = false;
				jugador.setSalud(jugador.getSalud() - 1);
			}
			if (d.getX() <= 0) {
				contador++;
				if (contador == 1) {
					servDrag.dragons.deleteNode(d);
					jugador.setSalud(jugador.getSalud() - 1);
				} else {
					gameOverState = new Estado_GameOver(juego);
					loggerJugador.Muerte();
					Estado.setEstado(gameOverState);
				}
				update();
				// Enviar lista a Mariana
			}
			for (Nodo<Proyectil> pe = cd.listaProyectilEnemigos.getPrimero(); pe != null; pe = pe.getSiguiente()) {
				Rectangle hitboxProyectilEnemigo = pe.getValor().getHitbox();
				if (hitboxJugador.intersects(hitboxProyectilEnemigo)) {
					jugador.setSalud(jugador.getSalud() - 1);
					cd.removeProyectilEnemigos(pe.getValor());
				}
			}
		}
	}

	/**
	 * Hace que los dragones disparen
	 */
	public void disparoDragones() {
		for (Dragon d = servDrag.dragons.head; d != null; d = d.getNext()) {
			d.disparoDragones();
		}
	}

	/**
	 * Revisa las colisiones entre misiles y dragones
	 */

	public void colision() {
		for (Nodo<Proyectil> p = cd.listaProyectil.getPrimero(); p != null; p = p.getSiguiente()) {
			for (Dragon d = servDrag.dragons.head; d != null; d = d.getNext()) {
				Rectangle hitboxDragon = d.getHitbox();
				Rectangle hitboxProyectil = p.getValor().getHitbox();
				if (hitboxProyectil.intersects(hitboxDragon)) {
					d.setSalud(d.getSalud() - 1);
					cd.removeProyectil(p.getValor());
				}
				if (d.getSalud() <= 0) {
                    oleadaLista(servDrag.dragons);
                    servDrag.dragons.deleteNode(d);
					update();
					// Enviar lista a Mariana
					jugador.setPuntaje(jugador.getPuntaje() + 1);
				}
			}
		}
	}

	/**
	 * Mueve el fondo
	 * 
	 * @param g indica donde dibujar
	 */
	public void updateFondo(Graphics g) {
		if (contadorFondo % 1 == 0) {
			xFondo -= 2;
		}
		if (xFondo <= -1920) {
			xFondo = 0;
		}
	}

	/**
	 * Dibuja los corazones en pantalla segun la vida del jugador
	 * 
	 * @param g indica donde dibujar
	 */
	public void updateVida(Graphics g) {
		int j = 200;
		if (jugador.getSalud() == 2) {
			j = 150;
		}
		if (jugador.getSalud() == 1) {
			j = 75;
		}
		if (jugador.getSalud() <= 0) {
			j = 0;
			gameOverState = new Estado_GameOver(juego);
			loggerJugador.Muerte();
			Estado.setEstado(gameOverState);
		}
		for (int i = 0; i < j; i += 75) {
			g.drawImage(Assets.heart, i, 0, null);
		}
	}

	@Override
	/**
	 * Actualiza los dragones y verifica colisiones Revisa si se hace click en un
	 * dragon y muestra la informacion
	 */
	public void update() {
		colisionProyectil();
		colisionJugador();
		colision();
		disparoDragones();
		// colisionProyectiles();
		for (Dragon d = servDrag.dragons.head; d != null; d = d.getNext()) {
			Rectangle hitboxDragon = d.getHitbox();
			d.update();
			Point punto = new Point(juego.getMouseManager().getMouseX(), juego.getMouseManager().getMouseY());
			if (juego.getMouseManager().isLeftPressed()) {
				if (hitboxDragon.contains(punto)) {
					infoDragon = d.getDatos();
				}
			}
		}
		jugador.update();
		contadorFondo++;
		if (contadorFondo % 150 == 0) {
			choque = true;
		}
	}

	/**
	 * Funcion que separa el string cada /n. Tomado de
	 * https://stackoverflow.com/questions/4413132/problems-with-newline-in-graphics2d-drawstring
	 */
	void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	@Override
	/**
	 * dibuja en pantalla
	 */
	public void render(Graphics g) {
		updateFondo(g);
		g.drawImage(Assets.cielo, xFondo, 0, null);
		g.drawImage(Assets.Hud, 1480, 0, null);
		g.drawImage(Assets.puntaje, 250, 0, null);
		g.setColor(colorPuntaje);
		g.setFont(fuentePuntaje);
		g.drawString(String.valueOf(jugador.getPuntaje()), 450, 50);
		g.setColor(colorLetras);
		g.setFont(fuenteLetras);
		if (infoDragon != null) {
			drawString(g, infoDragon, 1490, 130);

			// System.out.println(jugador.getPuntaje());
		}
		updateVida(g);
		for (Dragon d = servDrag.dragons.head; d != null; d = d.getNext()) {
			if (d.getX() <= 1440) {
				d.render(g);
				// d.getValor().getControlDisp().update(g);
			}
		}
		jugador.render(g);
		cd.update(g);
	}
}
