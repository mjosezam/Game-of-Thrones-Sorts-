package graficos;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display extends JFrame {
	
	private Canvas canvas;//para dibujar las imagenes
	private JFrame frame;//ventana donde se muestra el juego
	private String titulo;
	private int ancho,alto;
	
	
	public Display(String titulo,int ancho,int alto) {
		this.titulo = titulo;
		this.ancho = ancho;
		this.alto= alto;
		crearVentana();
	}
	/**
	 * creacion de la ventana con las dimensiones y parametros
	 */
	private void crearVentana() {
		frame = new JFrame(titulo);
		frame.setSize(ancho, alto);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(ancho,alto));
		canvas.setMaximumSize(new Dimension(ancho,alto));
		canvas.setMinimumSize(new Dimension(ancho,alto));
		canvas.setFocusable(false);
		frame.add(canvas);//agregar el canvas a la ventana
		frame.pack();
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public JFrame getFrame() {
		return frame;
	}
	
}
