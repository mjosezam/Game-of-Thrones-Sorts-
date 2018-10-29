package Logica;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controles implements KeyListener{
	PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
	String coordenadas;
	int x,y,boton;
	boolean flag = true;
	static final String puerto = "COM3";
	SerialPortEventListener listener = new SerialPortEventListener() {

		@Override
		public void serialEvent(SerialPortEvent arg0) {
			try {
				if (ino.isMessageAvailable()) {
					coordenadas = ino.printMessage();
					String[] coordenada2 = coordenadas.split(",");
					x = Integer.parseInt(coordenada2[0]);
					y = Integer.parseInt(coordenada2[1]);
					boton = Integer.parseInt(coordenada2[2]);
					//System.out.println(coordenadas);
				}
			} catch (SerialPortException | ArduinoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	};
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
		if (flag){
			flag = false;	
			joyStick();	
		}
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
	public void joyStick() {
		try {
			ino.arduinoRX(puerto, 9600, listener);
		} catch (ArduinoException | SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int xJoyStick() {
		return x;
	}
	
	public int yJoyStick() {
		return y;
	}
	
	public int botJoyStick() {
		return boton;
	}
	
}
