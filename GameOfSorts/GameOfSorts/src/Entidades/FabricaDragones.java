package Entidades;
import java.awt.Graphics;

import Estructuras.ListaSimple;
import Estructuras.Nodo;
import Logica.Game;
import disparos.Proyectil;
import graficos.Assets;
import Entidades.Dragon;


public class FabricaDragones {

	private Game game;
	public ListaSimple<Dragon> lista = new ListaSimple<Dragon>();
	
	public FabricaDragones(Game game) {
		this.game = game;
	}
	public Dragon nuevoDragon (int tipo,String id, float x,float y) {
		Dragon dragon = new Dragon(game, id, tipo, x, y);
		if (tipo == 0) {
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(3);
			dragon.setSprites(Assets.dragon1);
			lista.add(dragon);
			return dragon;
		}
		else if (tipo == 2) {
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(2);
			dragon.setSprites(Assets.dragon2);
			lista.add(dragon);
			return dragon;
		}
		else if (tipo == 1) {
			dragon.setAlto(100);
			dragon.setAncho(100);
			dragon.setSalud(1);
			dragon.setSprites(Assets.dragon3);
			lista.add(dragon);
			return dragon;
		}
		return null;
	}
	
	public void removeDragon(Dragon dragon) {
		lista.delete(dragon);
	} 

}