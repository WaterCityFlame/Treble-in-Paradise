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
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class Game {

	private ArrayList<Note> Tune;
	public ArrayList<ImagePanel> TunePanels;
	public JPanel Notes;
	public JPanel blueBox;
	public boolean win;
	public boolean stillPlaying;

	public Game(int level) {
		Tune = new ArrayList<Note>();
		TunePanels = new ArrayList<ImagePanel>();
		Notes = new JPanel();
		blueBox = new JPanel();
		win = false;
		stillPlaying = true;
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
		int x = 0;
		Notes.setPreferredSize(new Dimension(5000, 877));
		Notes.setSize(new Dimension(5000, 877));
		Notes.setLocation(1,1);
		Notes.setOpaque(false);
		Notes.setLayout(null);

		blueBox.setSize(new Dimension(140, 360));
		blueBox.setLocation(140, 330);
		blueBox.setOpaque(false);

		for (int i = 0; i<Tune.size(); i++) {
			ImagePanel currentNote = Tune.get(i).noteImg;
			currentNote.setLocation(x, Tune.get(i).height);
			currentNote.setVisible(true);
			Notes.add(currentNote);
			TunePanels.add(currentNote);
			x += 220;
		}
		Notes.setLocation(1240,1);
		for (int i = 0; i<TunePanels.size(); i++) {
			JPanel p = TunePanels.get(i);
			p.setLocation(p.getX()+1240, p.getY());
		}
		background.getContentPane().add(blueBox, 2);
		background.getContentPane().add(Notes, 3);

	};

	public void play() {
		Timer songLength = new Timer();	
		TimerTask move   = new MoveNotesTimerTask();
		TimerTask end    = new EndGameTimerTask(move);
		songLength.schedule(move, 0, 5);
		songLength.schedule(end, 15*1000);
	};
	
	public void moveNotes() {
		for (int i = 0; i<TunePanels.size(); i++) {
			JPanel p = TunePanels.get(i);
			p.setLocation(p.getX()-1, p.getY());
		}
	}

	public void checkCollision() {
		Rectangle noteBounds;
		Rectangle box = blueBox.getBounds();
		for (int i = 0; i<TunePanels.size(); i++) {
			JPanel p = TunePanels.get(i);
			noteBounds = p.getBounds();
			if (noteBounds.intersects(box)) {
				System.out.println(Tune.get(i).tone);
			}
		}
			
	}

	public void endGame(boolean won) {
		System.out.println("end of game");
		stillPlaying = false;
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
			checkCollision();
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
