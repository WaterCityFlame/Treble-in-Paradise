import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//Credits to: https://github.com/pffy/wav-piano-sound for sound files
public class Sound{

	public Sound(){
	}

	//Takes a string with a single character denoting a note (a,b,c,etc.) and plays
	//the corresponding sound file
	public void noteSound(String note){
		String noteChosen = "";
		switch (note.toLowerCase()){
			case "a":
				noteChosen = "assets"+File.separator+"sound"+File.separator+"a1.wav";
				break;
			case "b":
				noteChosen = "assets"+File.separator+"sound"+File.separator+"b1.wav";
				break;
			case "g":
				noteChosen = "assets"+File.separator+"sound"+File.separator+"g1.wav";
				break;
			default:
				System.out.println("Invalid note passed to noteSound");
				break;
		}
		try{
			AudioInputStream input = AudioSystem.getAudioInputStream(new File(noteChosen));
			Clip noteFile = AudioSystem.getClip();
			noteFile.open(input);
			noteFile.start();
		}
		catch(Exception e){
			System.out.println("Error playing sound file");
			e.printStackTrace();
		}
	}
}