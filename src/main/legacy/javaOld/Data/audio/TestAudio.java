package Data.audio;

public class TestAudio {

	public static void main(String[] args) {
        //String audioFilePath = ResourcesPaths.SONGS_ROOT_PATH + "song.wav";
        String audioFilePath = "song.wav";
        SoundSystem player = new SoundSystem();
        int piste1 = player.ajouterPiste(0,audioFilePath);
        player.afficherPistes();
        
        try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
        
        try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
        player.retirerPiste(piste1);
        player.afficherPistes();
        try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
        player.afficherPistes();
        piste1 = player.ajouterPiste(1, audioFilePath);
        player.afficherPistes();
    }
}
