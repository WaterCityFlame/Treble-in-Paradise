import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Game {

	private ArrayList<Note> Tune;

	public Game(int level) {
		Tune = new ArrayList<Note>();
		if (level == 1) {
			Tune.add(new Note("b", 1));
			Tune.add(new Note("a", 1));
			Tune.add(new Note("g", 1));
			Tune.add(new Note("g", 1));
			Tune.add(new Note("b", 1));
			Tune.add(new Note("a", 1));
			Tune.add(new Note("g", 1));
			Tune.add(new Note("g", 1));
		}
	};

	public void display(ImagePanel background) {
		int x = 100;

		for (int i = 0; i<Tune.size(); i++) {
			ImagePanel currentNote = Tune.get(i).noteImg;
			currentNote.setLocation(x, Tune.get(i).height);
			currentNote.setVisible(true);
			background.add(currentNote);
			x += 200;
		}

	};

	private void play() {};
	
	//class for each note image flying by.
	private class Note {
		public String tone;
		int beats;
		int height;
		public ImagePanel noteImg;
		public Note(String t, int b) {
			tone = t;
			beats = b;
			if (beats == 1){
				noteImg = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"quarterNote.png").getImage());
				noteImg.setVisible(false);
			} else if (beats == 2) {
				noteImg = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"halfNote.png").getImage());
				noteImg.setVisible(false);
			}

			if (tone.equals("b")) {height = 385;}
			if (tone.equals("a")) {height = 415;}
			if (tone.equals("g")) {height = 445;}
		}
	}

};
