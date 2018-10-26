package Entidades;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Logica.Game;
import disparos.ControlDisparo;
import java.awt.Rectangle;

public class Dragon extends Creature {
	private String ID,clase;
	private Game juego;
	private ControlDisparo controlDisp;
	private int contador,contadorIteracion,contadorDisparo,tipo,edad,velocidadDisp = 0;;
	private Rectangle hitbox;
	private BufferedImage[] sprites;
	private boolean subir = true;
	int cont = 0;

	
	public Dragon(Game juego,String ID,int tipo,ControlDisparo cd,float x, float y) {
		super(juego, x, y);
		this.controlDisp = cd;
		this.juego = juego;
		this.tipo = tipo;
		this.ID = ID;
		this.salud = 3;
		this.velocidad = 3;
		this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho(),getAlto()-30);
	}
	
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
	
	public int getTipo() {
		return tipo;
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
	public void updateHitbox() {
	this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho(),getAlto()-30);
	}
	public String getDatos() {
		String datos = "ID: "+getID()+"                             "+
				"Tipo: "+getTipo()+"\nSalud: "+getSalud()+"                          "
			+ ""+"Edad: "+getEdad()+"\nRecarga Disparo: "+getVelocidadDisp()+"\nClase: "+getClase();
		return datos;
	}
	
	public void disparoDragones() {
		if (cont == getContadorDisparo()) {
			controlDisp.addProyectilEnemigos(getX(), getY()+10);
			cont = 0;
		}
		else {
			cont++;
		}
	}
	

	public ControlDisparo getControlDisp() {
		return controlDisp;
	}

	@Override
	public void update() {
		this.contadorIteracion++;
		if(contadorIteracion%10==0) {//velocidad cambio de imagen jugador
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
	public void render(Graphics g) {
		g.drawImage(sprites[this.contador],(int)x,(int)y,getAncho(),getAlto(),null);
		g.drawRect((int)hitbox.getX(),(int)hitbox.getY(),(int)hitbox.getWidth(),(int)hitbox.getHeight());
	}
	private void moverBot() {
		movimientoX=-1;
		if (contadorIteracion%40==0) {
			if (subir) {
				movimientoY=1;
				if (contador%1000==0) {
					subir = false;
				}
			}
			else {
				movimientoY=-1;
				if (contador%1000==0) {
					subir = true;
				}
			}
		}
	}
}