package Entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Logica.Game;
import Trees.Node;
import disparos.ControlDisparo;
import java.awt.Rectangle;

public class Dragon extends Creature {
	private String ID, clase;
	private Game juego;
	private ControlDisparo controlDisp;
	private int contador, contadorIteracion, contadorDisparo, tipo, edad, velocidadDisp = 0;
	private Rectangle hitbox;
	private BufferedImage[] sprites;
	private boolean subir = true;
	public Dragon next, prev, left, right, parent, son1, son2;
	public int height;
	int cont = 0;

	/**
	 * Constructor de cada dragon
	 * 
	 * @param juego
	 * @param       ID, ID especifico de cada dragon
	 * @param       tipo, tipo de dragon para crear
	 * @param       cd, control de disparo
	 * @param x     posicion en x
	 * @param y     posicion en y
	 * @param edad
	 */
	public Dragon(Game juego, String ID, String clase, ControlDisparo cd, Dragon parent, float x, float y, int edad) {
		super(juego, x, y);
		this.controlDisp = cd;
		this.juego = juego;
		this.ID = ID;
		this.son1=null;
		this.son2=null;
		this.velocidad = 3;
		this.edad = edad;
		height = 1;
		this.hitbox = new Rectangle((int) getX(), (int) getY(), getAncho(), getAlto() - 30);
		this.parent=parent;
		this.next=null;
		this.contadorDisparo = getContadorDisparo();
	}
	// gets y set

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getVelocidadDisp() {
		return velocidadDisp;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public int getContadorDisparo() {
		return contadorDisparo;
	}

	public void setContadorDisparo(int contadorDisparo) {
		this.contadorDisparo = contadorDisparo;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public int getSalud() {
		return salud;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public void setVelocidadDisp(int velocidadDisp) {
		this.velocidadDisp = velocidadDisp;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public Dragon getParent() {
		return parent;
	}

	public void setParent(Dragon parent) {
		this.parent = parent;
	}

	public Dragon getNext() {
		return next;
	}

	public void setNext(Dragon next) {
		this.next = next;
	}

	public Dragon getPrev() {
		return prev;
	}

	public void setPrev(Dragon prev) {
		this.prev = prev;
	}

	public Dragon getLeft() {
		return left;
	}

	public void setLeft(Dragon left) {
		this.left = left;
	}

	public Dragon getRight() {
		return right;
	}

	public void setRight(Dragon right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * actualiza el hitbox de acuerdo a la posicion del dragon
	 */
	public void updateHitbox() {
		this.hitbox = new Rectangle((int) getX(), (int) getY(), getAncho(), getAlto() - 30);
	}

	/**
	 * Obtiene los datos del dragon para desplegar en pantalla
	 * 
	 * @return datos, String con los datos del dragon concatenados y listos para
	 *         mostrar
	 */
	public String getDatos() {
		String datos = "ID: " + getID() + "                             " + "\nSalud: " + getSalud()
				+ "                          " + "" + "Edad: " + getEdad() + "\nRecarga Disparo: " + getVelocidadDisp()
				+ "\nClase: " + getClase();
		return datos;
	}

	public void disparoDragones() {
		if (cont == getContadorDisparo()) {
			if (this.x <= 1440) {
				controlDisp.addProyectilEnemigos(getX(), getY() + 10);
				cont = 0;
			}
		} else {
			cont++;
		}
	}

	public ControlDisparo getControlDisp() {
		return controlDisp;
	}

	@Override
	/**
	 * Actualiza el dragon y sus stats, lo mueve
	 */
	public void update() {
		this.contadorIteracion++;
		if (contadorIteracion % 10 == 0) {// velocidad cambio de imagen
			contador++;
			contadorIteracion = 0;
			if (contador == 5) {
				contador = 0;
			}
		}
		moverBot();
		updateHitbox();
		move();
	}

	@Override
	/**
	 * dibujo en pantalla del dragon
	 */
	public void render(Graphics g) {
		g.drawImage(sprites[this.contador], (int) x, (int) y, getAncho(), getAlto(), null);
		g.drawRect((int) hitbox.getX(), (int) hitbox.getY(), (int) hitbox.getWidth(), (int) hitbox.getHeight());
		//controlDisp.update(g);
	}

	/**
	 * movimiento hacia arriba y abajo del dragon
	 */
	private void moverBot() {
		movimientoX = -1;
		if (contadorIteracion % 40 == 0) {
			if (subir) {
				movimientoY = (float) 0.5;
				if (contador % 1000 == 0) {
					subir = false;
				}
			} else {
				movimientoY = (float) -0.5;
				if (contador % 1000 == 0) {
					subir = true;
				}
			}
		}
	}
}