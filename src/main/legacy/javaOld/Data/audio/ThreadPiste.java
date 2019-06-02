package Data.audio;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ThreadPiste extends Thread{

	private String cheminFichierAudio;
	private boolean lectureTerminee;
	private int loopNumber;
	
	
	public ThreadPiste(String cheminFichierAudio, int loopNumber){
		this.cheminFichierAudio = cheminFichierAudio;
		this.lectureTerminee = false;
		this.loopNumber = loopNumber;
	}
	
	public void run(){
		try {
			URL url = Thread.currentThread().getContextClassLoader().getResource(this.cheminFichierAudio);
			System.out.println(url);
			System.out.println(this.cheminFichierAudio);
			File audioFile = new File(url.getPath());

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);

			AudioFormat format = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);

			Clip audioClip = (Clip) AudioSystem.getLine(info);

			audioClip.open(audioStream);

			if (this.loopNumber == -1){
				audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				audioClip.loop(loopNumber);
			}

			while (!lectureTerminee) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			audioClip.close();
			
		} catch (UnsupportedAudioFileException ex1) {
			System.out.println("Format du fichier Data.audio non supporté.");
		} catch (LineUnavailableException ex2) {
			System.out.println("Accès à la progression de la lecture impossible.");
		} catch (IOException ex3) {
			System.out.println("Erreur lors de la lecture du fichier Data.audio : " + cheminFichierAudio);
		}
		return;
	}
	
	public void stopLecture(){
		this.lectureTerminee = true;
	}

	public String getNomPiste(){
		return this.cheminFichierAudio;
	}
}
