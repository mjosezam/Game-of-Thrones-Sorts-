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
		this.hitbox = new Rectangle((int)this.x,(int)this.y,this.ancho,this.alto);
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
		move();
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(sprites[this.contador],(int)x,(int)y,ancho,alto,null);
	}
	private void moverBot() {
		if (contadorIteracion%40==0) {
			if (subir) {
				movimientoY=velocidad;
				if (contador%100==0) {
					subir = false;
				}
			}
			else {
				movimientoY=-velocidad;
				if (contador%100==0) {
					subir = true;
				}
			}
		}
	}
}