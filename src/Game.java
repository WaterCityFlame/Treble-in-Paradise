import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class Game {

	private ArrayList<Note> Tune;
	public ArrayList<ImagePanel> TunePanels;
	public JPanel Notes;
	public boolean win;

	public Game(int level) {
		Tune = new ArrayList<Note>();
		TunePanels = new ArrayList<ImagePanel>();
		Notes = new JPanel();
		win = false;
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

	public void display(JFrame background) {
		int x = 100;
		Notes.setPreferredSize(new Dimension(5000, 877));
		Notes.setSize(new Dimension(5000, 877));
		Notes.setLocation(1,1);
		Notes.setOpaque(false);
		Notes.setLayout(null);

		for (int i = 0; i<Tune.size(); i++) {
			ImagePanel currentNote = Tune.get(i).noteImg;
			currentNote.setLocation(x, Tune.get(i).height);
			currentNote.setVisible(true);
			Notes.add(currentNote);
			TunePanels.add(currentNote);
			x += 200;
		}
		background.getContentPane().add(Notes, 2);

	};

	public void play() {
		Timer songLength = new Timer();	
		TimerTask move   = new MoveNotesTimerTask();
		TimerTask end    = new EndGameTimerTask(move);
		songLength.schedule(move, 0, 5);
		songLength.schedule(end, 15*1000);
	};
	
	public void moveNotes() {
		int x = Notes.getX();
		int y = Notes.getY();
		Notes.setLocation(x-1, y);
	}

	public void endGame(boolean won) {
		System.out.println("end of game");
	}

	private class EndGameTimerTask extends TimerTask {
		TimerTask endThis;
		public EndGameTimerTask(TimerTask e) {
			endThis = e;
		}

		@Override
		public void run(){
			endGame(win);
			endThis.cancel();
		}
	}

	private class MoveNotesTimerTask extends TimerTask {
		public MoveNotesTimerTask() {}

		@Override
		public void run(){
			moveNotes();
		}
	}
	
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
