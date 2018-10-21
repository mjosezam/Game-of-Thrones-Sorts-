package Entidades;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Logica.Game;
import disparos.ControlDisparo;
import graficos.Assets;
import java.awt.Rectangle;
import graficos.Animacion;

public class Dragon extends Creature {
	public static final int velocidadDisp = 5;
	private String ID;
	private Game juego;
	private ControlDisparo controlDisp;
	private int contador,contadorIteracion;
	private Rectangle hitbox;
	private BufferedImage[] sprites;
	private boolean puedeDisparar = true;
	private boolean subir = true;

	
	public Dragon(Game juego,String ID,int tipo,float x, float y) {
		super(juego, x, y);
		this.juego = juego;
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

	public static int getVelocidaddisp() {
		return velocidadDisp;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	public void updateHitbox() {
	this.hitbox = new Rectangle((int)getX(),(int)getY(),getAncho(),getAlto()-30);
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