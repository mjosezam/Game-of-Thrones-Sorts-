package graficos;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display extends JFrame {
	
	private Canvas canvas;//para dibujar las imagenes
	private JFrame frame;//ventana del juego
	private String titulo;
	private int ancho,alto;
	
	/**
	 * Constructor de la ventana
	 * @param titulo, titulo que se muestra en la ventana
	 * @param ancho, ancho de la ventana
	 * @param alto, alto de la ventana en pixeles
	 */
	public Display(String titulo,int ancho,int alto) {
		this.titulo = titulo;
		this.ancho = ancho;
		this.alto= alto;
		crearVentana();
	}
	/**
	 * creacion de la ventana con las dimensiones y parametros fijos
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
