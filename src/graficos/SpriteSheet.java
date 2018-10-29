package graficos;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage hoja;
	
	public SpriteSheet(BufferedImage hoja) {
		this.hoja = hoja;
	}
	/**
	 * @param x coordenada X de la imagen
	 * @param y coordenada Y de la imagen
	 * @param ancho de la imagen
	 * @param alto de la imagen
	 * @return devuelve la imagen deseada de la hoja de sprites
	 */
	public BufferedImage cortarImagen (int x,int y,int ancho,int alto) {
		return hoja.getSubimage(x, y, ancho, alto);
	}
}
