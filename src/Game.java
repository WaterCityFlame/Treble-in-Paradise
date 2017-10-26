import java.io.File;
import java.awt.Font;
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

	private ArrayList<Note> tune;
	public ArrayList<ImagePanel> tunePanels;

	public JPanel notes;
	public JPanel blueBox;

	public String currentNote;

	public boolean stillPlaying;

	public int points;
	private int minPoints;
	
	
	public Game(int level) {
		
		tune = new ArrayList<Note>();
		tunePanels = new ArrayList<ImagePanel>();
		notes = new JPanel();
		blueBox = new JPanel();
		stillPlaying = true;
		currentNote = "x";
		points = 0;
		if (level == 1) {
			tune.add(new Note("b", 1));
			tune.add(new Note("a", 1));
			tune.add(new Note("g", 1));
			tune.add(new Note("g", 1));
			tune.add(new Note("b", 1));
			tune.add(new Note("a", 1));
			tune.add(new Note("g", 1));
			tune.add(new Note("g", 1));
			minPoints = 6;
		}
			
	};
	
	public void end() {
	
		for (int i = 0; i<tune.size(); i++) {
			ImagePanel currentNote = tune.get(i).noteImg;
			currentNote.setVisible(false);
			notes.remove(currentNote);
			tunePanels.remove(currentNote);
		}
		
	}

	public void display(JFrame background) {
		int x = 0;
		notes.setPreferredSize(new Dimension(5000, 877));
		notes.setSize(new Dimension(5000, 877));
		notes.setLocation(1,1);
		notes.setOpaque(false);
		notes.setLayout(null);

		blueBox.setSize(new Dimension(140, 360));
		blueBox.setLocation(140, 330);
		blueBox.setOpaque(false);

		for (int i = 0; i<tune.size(); i++) {
			ImagePanel currentNote = tune.get(i).noteImg;
			currentNote.setLocation(x, tune.get(i).height);
			currentNote.setVisible(true);
			notes.add(currentNote);
			tunePanels.add(currentNote);
			x += 220;
		}
		notes.setLocation(1240,1);
		for (int i = 0; i<tunePanels.size(); i++) {
			JPanel p = tunePanels.get(i);
			p.setLocation(p.getX()+1240, p.getY());
		}
		background.getContentPane().add(blueBox, 2);
		background.getContentPane().add(notes, 3);
	};

	public void play(JFrame background, JLabel help) {
		Timer songLength = new Timer();
		TimerTask move   = new MoveNotesTimerTask();
		TimerTask end    = new EndGameTimerTask(move, background, help);
		songLength.schedule(move, 0, 5);
		songLength.schedule(end, 15*1000);
	};

	public void moveNotes() {
		for (int i = 0; i<tunePanels.size(); i++) {
			JPanel p = tunePanels.get(i);
			p.setLocation(p.getX()-1, p.getY());
		}
	}

	public void checkCollision() {
		Rectangle noteBounds;
		Rectangle box = blueBox.getBounds();
		currentNote = "x";
		for (int i = 0; i<tunePanels.size(); i++) {
			JPanel p = tunePanels.get(i);
			noteBounds = p.getBounds();
			if (noteBounds.intersects(box)) {
				currentNote = tune.get(i).tone;
			}
		}
	}

	public void endGame(JFrame background,JLabel help) {
		stillPlaying = false;
		System.out.println("end of game");
		// JLabel popup = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"popupBase.png"));
		// popup.setLocation(250, 50);
		// popup.setVisible(true);



		// JButton continueButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"continueButton.png"));
		// continueButton.setBounds(18, 80, 209, 297);
		// continueButton.setLocation(200,500);
		// continueButton.setOpaque(false);
		// continueButton.setContentAreaFilled(false);
		// continueButton.setBorderPainted(false);


		if (points >= minPoints) {
			System.out.println("you win!");
			System.out.println(points);
			help.setText("You won with "+ points +"! Good job!");
			// JLabel winLabel = new JLabel("You Win!");
			// winLabel.setFont(new Font("Serif", Font.PLAIN, 40));
			// winLabel.setBounds(50, 100, 300, 80);
			// winLabel.setLocation(250, 300);

			// popup.add(winLabel);
		} else {
			System.out.println("you lose!");
			System.out.println(points);
			help.setText("You needed to get "+ minPoints + " but got " +points+". You'll get it next time!");
			// JLabel lossLabel = new JLabel("You lost :(");
			// lossLabel.setFont(new Font("Serif", Font.PLAIN, 40));
			// lossLabel.setBounds(100, 300, 300, 80);
			// lossLabel.setLocation(250, 300);
			// popup.add(lossLabel);
		}
		// background.getContentPane().removeAll();
		// background.repaint();
		// background.getContentPane().add(popup);
	}

	private class EndGameTimerTask extends TimerTask {
		TimerTask endThis;
		JFrame background;
		JLabel help;
		public EndGameTimerTask(TimerTask e, JFrame bg, JLabel he) {
			endThis = e;
			background = bg;
			help = he;
		}

		@Override
		public void run(){
			endGame(background,help);
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

			if (tone.equals("b")) {height = 485;}
			if (tone.equals("a")) {height = 515;}
			if (tone.equals("g")) {height = 545;}
		}
	}
};
