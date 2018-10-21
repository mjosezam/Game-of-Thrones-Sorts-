package audio;
import javax.sound.sampled.*;

import Logica.Game;
public class AudioPlayer extends Thread {
	
	private Clip clip;
	private Game game;
	private String sound;
	private boolean ejecutando = false;
	
	public AudioPlayer(Game juego,String sonido) {
		this.game = juego;
		this.sound = sonido;
	}
	
	
	public void play() {
		if (clip == null) return;
			parar();
			clip.setFramePosition(0);
			clip.start();
	}
	
	public void parar() {
		if(clip.isRunning()) clip.stop();
	}
	
	public void close(){
		parar();
		clip.close();
	}
	
	@Override
	public void run() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(sound));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, 
					baseFormat.getChannels(), baseFormat.getChannels()*2,baseFormat.getSampleRate(),false);
			
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat,ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		while(ejecutando) {
			play();
		}
			parar();
		
	}
}
