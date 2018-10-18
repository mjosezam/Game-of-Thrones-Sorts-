package Logica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controles implements KeyListener{
	
	private boolean[]keys;
	public boolean arriba,abajo,izq,der,space;
	public Controles() {
		keys = new boolean [256];	
	}
	/**
	 * asigna los valores a las teclas de movimiento
	 */
	public void update() {
		arriba = keys[KeyEvent.VK_W];
		abajo = keys[KeyEvent.VK_S];
		izq = keys[KeyEvent.VK_A];
		der = keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
	}
	/**
	 * asigna a las teclas un valor dependiendo de si esta siendo presionado o no
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
