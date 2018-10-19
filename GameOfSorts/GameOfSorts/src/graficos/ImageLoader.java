package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ImageLoader {
	/**
	 * 
	 * @param caminoDeImagen localizacion de la imagen que se quiere cargar
	 * @return carga la imagen
	 */
	public static BufferedImage cargarImagen(String caminoDeImagen) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(caminoDeImagen));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);//Si no logra cargar la imagen cierra el programa
		}
		return null;
	}
}
