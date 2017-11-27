import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class TrebleInParadise {

	private JFrame frame = new JFrame();
	private ImagePanel loginPanel;
	private JLayeredPane levelMenuPanel;
	private Sound soundPlayer = new Sound();
	private int currentLevel = 1;

	//Array of completed levels - initially no complete levels
	private boolean [] levelComplete = {false,false,false,false};

	private JLabel tutorial = new JLabel("Tutorial");

	//Level Selection Screen
	private JButton level1Button;
	private JButton level2Button;
	private JButton level3Button;

	//Dummy credentials
	private String username = "";
	private String password = "";


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrebleInParadise window = new TrebleInParadise();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TrebleInParadise() {
		login();
	}


	//Login screen
	public void login() {

		loginInit();

		/***********************************************************
		* Username label and text field
		**********************************************************/
		JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Serif", Font.BOLD, 20));
        userLabel.setBounds(0, 0, 100, 25);
        userLabel.setLocation(235,236);
        userLabel.setVisible(true);
        loginPanel.add(userLabel);

        JTextField userField=new JTextField();
        userField.setBounds(5, 5, 280, 50);
        userField.setHorizontalAlignment(JTextField.CENTER);
        userField.setLocation(330,225);
        loginPanel.add(userField);

        /***********************************************************
    	 * Password label and text field
    	 **********************************************************/
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Serif", Font.BOLD, 20));
        passLabel.setBounds(0, 0, 100, 25);
        passLabel.setLocation(235,311);
        passLabel.setVisible(true);
        loginPanel.add(passLabel);

        JTextField passField=new JTextField();
        passField.setBounds(5, 5, 280, 50);
        passField.setHorizontalAlignment(JTextField.CENTER);
        passField.setLocation(330,300);
        loginPanel.add(passField);

        /***********************************************************
    	 * Message that appears if user enters wrong information
    	 **********************************************************/
        JLabel wrongInfo = new JLabel("Whoops! Looks like you entered the wrong " +
        "username or password! Please try again.");
        wrongInfo.setFont(new Font("Serif", Font.PLAIN, 20));
        wrongInfo.setForeground(Color.red);
        wrongInfo.setBounds(500, 500, 1000, 50);
        wrongInfo.setLocation(135,338);
        wrongInfo.setVisible(false);
        loginPanel.add(wrongInfo);

        /***********************************************************
    	 * Creates the start button
    	 **********************************************************/
        ImageIcon imgIconPlayButton = new ImageIcon("assets"+File.separator+"img"+File.separator+"loginScreenPlayButton.png");
        Image imgPlayButton = imgIconPlayButton.getImage();
        JButton startBtn=new JButton(new ImageIcon(imgPlayButton.getScaledInstance(275,70,java.awt.Image.SCALE_SMOOTH)));
        startBtn.setBounds(5, 5, 280, 75);
        startBtn.setLocation(330,375);
        loginPanel.add(startBtn);


        /***********************************************************
    	 * Action Listener - Checks if user has correct credentials
    	 * If user has correct credentials, they're taken to the
    	 * introduction screen, otherwise displays wrong information
    	 * notice
    	 **********************************************************/
  		startBtn.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				String inUser = userField.getText();
  				String inpass = passField.getText();
  				if(inUser.equals(username) && inpass.equals(password)) {
  					loginPanel.setVisible(false);
  					loginPanel.setEnabled(false); //So action listeners not clickable in other panels
  					introScreen();
  					//levelScreen();
  				}
  				else {
  					wrongInfo.setVisible(true);
  				}
  			}
  		});

  		//removes wrong information message after 10 seconds
  				int delay = 10000; //milliseconds
  				   ActionListener taskPerformer = new ActionListener() {
  				       public void actionPerformed(ActionEvent e) {
  				           wrongInfo.setVisible(false);
  				       }
  				   };
  				   new Timer(delay, taskPerformer).start();


	}

	/****************************************************************
	 * Initializes the background and panel settings
	 ***************************************************************/
	public void loginInit() {

		loginPanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"loginScreenBackdrop.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(loginPanel);
		frame.pack();
		frame.setSize(930,658);
		frame.setVisible(true);

	}

	public void introScreen() {
		JLayeredPane levelMenuPanel = new JLayeredPane();
		levelMenuPanel.setBounds(0,0,930,658);
		frame.add(levelMenuPanel);

		//Set Background
		ImagePanel levelBackgroundPanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelSelectBackground.png").getImage());

		//Set Level Connecting Line
		ImagePanel levelLinePanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelLine.png").getImage());

		//Level 1 label
		JLabel level1ButtonLabel = new JLabel("1");
		level1ButtonLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		level1ButtonLabel.setBounds(50, 50, 50, 50);
		level1ButtonLabel.setLocation(90,180);
		level1ButtonLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(level1ButtonLabel);

		//Level 1 button
		level1Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"currentLevelButton.png"));
		level1Button.setBounds(18, 80, 209, 297);
		level1Button.setLocation(4,38);
		level1Button.setOpaque(false);
		level1Button.setContentAreaFilled(false);
		level1Button.setBorderPainted(false);
		levelMenuPanel.add(level1Button);

		//Level 2 label
		JLabel level2ButtonLabel = new JLabel("2");
		level2ButtonLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		level2ButtonLabel.setBounds(50, 50, 50, 50);
		level2ButtonLabel.setLocation(220,310);
		level2ButtonLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(level2ButtonLabel);

		//Level 2 button
		level2Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedLevelButton.png"));
		level2Button.setBounds(18, 80, 209, 297);
		level2Button.setLocation(128,195);
		level2Button.setOpaque(false);
		level2Button.setContentAreaFilled(false);
		level2Button.setBorderPainted(false);
		levelMenuPanel.add(level2Button);

		//level 3 label
		JLabel level3Label = new JLabel("3");
		level3Label.setFont(new Font("Serif", Font.PLAIN, 40));
		level3Label.setBounds(50, 50, 50, 50);
		level3Label.setLocation(325,475);
		level3Label.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(level3Label);

		//Level 3 button
		level3Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedLevelButton.png"));
		level3Button.setBounds(18, 80, 209, 297);
		level3Button.setLocation(233,360);
		level3Button.setOpaque(false);
		level3Button.setContentAreaFilled(false);
		level3Button.setBorderPainted(false);
		levelMenuPanel.add(level3Button);

		//Boss level label
		JLabel bossLabel = new JLabel("4");
		bossLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		bossLabel.setBounds(50, 50, 50, 50);
		bossLabel.setLocation(540,450);
		bossLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(bossLabel);

		//Boss level button
		JButton bossButton;
		bossButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedBossLevelButton.png"));
		bossButton.setBounds(18, 80, 152, 148);
		bossButton.setLocation(475,405);
		bossButton.setOpaque(false);
		bossButton.setContentAreaFilled(false);
		bossButton.setBorderPainted(false);
		levelMenuPanel.add(bossButton);

		//Log out button
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(50, 100, 200, 30);
		logoutBtn.setLocation(760,45);
		logoutBtn.setFont(new Font("Serif", Font.PLAIN, 30));
		logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorderPainted(false);
		levelMenuPanel.add(logoutBtn);

		//Label with user name
		JLabel userLabel = new JLabel(username);
		userLabel.setFont(new Font("Serif", Font.BOLD, 40));
		userLabel.setBounds(50, 100, 100, 30);
		userLabel.setLocation(788,15);
		levelMenuPanel.add(userLabel);

		/****************************************************************
		* Intro messages
		***************************************************************/
		JLabel m1 = new JLabel("Welcome to Treble in Paradise.", SwingConstants.CENTER);
		m1.setFont(new Font("Serif", Font.PLAIN, 40));
		m1.setOpaque(true);
		m1.setBackground(Color.green);
		m1.setBounds(50, 50, 600, 100);
		m1.setLocation(300,150);
		levelMenuPanel.add(m1);

		JLabel m2 = new JLabel("This is the level menu screen.", SwingConstants.CENTER);
		m2.setFont(new Font("Serif", Font.PLAIN, 40));
		m2.setOpaque(true);
		m2.setBackground(Color.green);
		m2.setBounds(50, 50, 600, 100);
		m2.setLocation(300,150);
		m2.setVisible(false);
		levelMenuPanel.add(m2);

		JLabel m3 = new JLabel("You can choose a level to play from", SwingConstants.CENTER);
		m3.setFont(new Font("Serif", Font.PLAIN, 40));
		m3.setOpaque(true);
		m3.setBackground(Color.green);
		m3.setBounds(50, 50, 600, 100);
		m3.setLocation(300,150);
		m3.setVisible(false);
		levelMenuPanel.add(m3);

		JLabel m3p2 = new JLabel("this screen", SwingConstants.CENTER);
		m3p2.setFont(new Font("Serif", Font.PLAIN, 40));
		m3p2.setOpaque(true);
		m3p2.setBackground(Color.green);
		m3p2.setBounds(50, 50, 600, 100);
		m3p2.setLocation(300,225);
		m3p2.setVisible(false);
		levelMenuPanel.add(m3p2);


		JLabel m4 = new JLabel("Click on level 1 to start playing.", SwingConstants.CENTER);
		m4.setFont(new Font("Serif", Font.PLAIN, 40));
		m4.setOpaque(true);
		m4.setBackground(Color.green);
		m4.setBounds(50, 50, 600, 100);
		m4.setLocation(300,150);
		m4.setVisible(false);
		levelMenuPanel.add(m4);

		JButton next = new JButton("Next");
		next.setFont(new Font("Serif", Font.PLAIN, 40));
		next.setOpaque(true);
		next.setBackground(Color.green);
		next.setBounds(50, 50, 600, 100);
		next.setLocation(300,225);
		levelMenuPanel.add(next);

		JButton next1 = new JButton("Next");
		next1.setFont(new Font("Serif", Font.PLAIN, 40));
		next1.setOpaque(true);
		next1.setBackground(Color.green);
		next1.setBounds(50, 50, 600, 100);
		next1.setLocation(300,225);
		next1.setVisible(false);
		levelMenuPanel.add(next1);

		JButton next2 = new JButton("Next");
		next2.setFont(new Font("Serif", Font.PLAIN, 40));
		next2.setOpaque(true);
		next2.setBackground(Color.green);
		next2.setBounds(50, 50, 600, 100);
		next2.setLocation(300,300);
		next2.setVisible(false);
		levelMenuPanel.add(next2);

		JButton next3 = new JButton("Next");
		next3.setFont(new Font("Serif", Font.PLAIN, 40));
		next3.setOpaque(true);
		next3.setBackground(Color.green);
		next3.setBounds(50, 50, 600, 100);
		next3.setLocation(300,225);
		next3.setVisible(false);
		levelMenuPanel.add(next3);

		//Inserting ImagePanels into LayeredPanel
		levelMenuPanel.add(levelLinePanel);
		levelMenuPanel.add(levelBackgroundPanel);
		levelMenuPanel.setVisible(true);


		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				m1.setVisible(false);
				m2.setVisible(true);
				next.setVisible(false);
				next1.setVisible(true);

			}
		});

		next1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				m2.setVisible(false);
				m3.setVisible(true);
				m3p2.setVisible(true);
				next1.setVisible(false);
				next2.setVisible(true);

			}
		});

		next2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				m3.setVisible(false);
				m3p2.setVisible(false);
				m4.setVisible(true);
				next2.setVisible(false);

			}
		});

		level1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				levelMenuPanel.setVisible(false);
				level1Tutorial();

			}
		});

		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelMenuPanel.setVisible(false);
				login();
			}
		});

	}

	public void level1Tutorial() {

		ImagePanel level1TutPanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelBackgroundGreen.png").getImage());
		frame.getContentPane().add(level1TutPanel);

		//Label that tells user the level is a tutorial
		tutorial.setFont(new Font("Serif", Font.BOLD, 65));
		tutorial.setBounds(1000, 100, 800, 50);
		tutorial.setLocation(375,90);
		tutorial.setVisible(true);
		level1TutPanel.add(tutorial);

		/****************************************************************
		 * Score
		 ***************************************************************/
	    JLabel scoreText = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"scoreText.png"));
	    scoreText.setBounds(0, 60, 209, 260);
	    scoreText.setLocation(0, 2);
	    scoreText.setOpaque(false);
	    level1TutPanel.add(scoreText);

	    JLabel scoreValue = new JLabel("0");
	    scoreValue.setFont(new Font("Serif", Font.PLAIN, 80));
	    scoreValue.setBounds(50, 50, 120, 120);
	    scoreValue.setLocation(215,65);
	    scoreValue.setOpaque(false);
	    level1TutPanel.add(scoreValue);

	    JLabel endMessage = new JLabel("");
		endMessage.setFont(new Font("Serif", Font.PLAIN, 35));
		endMessage.setBounds(1000, 100, 1000, 70);
		endMessage.setLocation(113,146);
		endMessage.setVisible(true);
		level1TutPanel.add(endMessage);

		//Back Button
        JButton backButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"backButton.png"));
        backButton.setBounds(0, 80, 84, 43);
        backButton.setLocation(833, 94);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
		level1TutPanel.add(backButton);

		//Restart Button
		JButton restartButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"restartButton.png"));
		restartButton.setBounds(0, 80, 100, 100);
		restartButton.setLocation(740,70);
		restartButton.setOpaque(false);
		restartButton.setContentAreaFilled(false);
		restartButton.setBorderPainted(false);
	    level1TutPanel.add(restartButton);

	    /****************************************************************
		 * Tutorials
		 ***************************************************************/
		JLabel intro = new JLabel("This is the in game screen.");
		intro.setFont(new Font("Serif", Font.PLAIN, 25));
		intro.setBounds(100, 100, 1000, 70);
		intro.setLocation(130,160);
		intro.setVisible(true);
		level1TutPanel.add(intro);

		JLabel intro2 = new JLabel("In music, these lines are called the staff. Notes appear here.");
		intro2.setFont(new Font("Serif", Font.PLAIN, 25));
		intro2.setBounds(100, 100, 1000, 70);
		intro2.setLocation(130,160);
		intro2.setVisible(false);
		level1TutPanel.add(intro2);

		JLabel intro3 = new JLabel("Notes sound different depending on where they are on the staff.");
		intro3.setFont(new Font("Serif", Font.PLAIN, 25));
		intro3.setBounds(100, 100, 1000, 70);
		intro3.setLocation(130,160);
		intro3.setVisible(false);
		level1TutPanel.add(intro3);

		JLabel intro4 = new JLabel("This note is A.");
		intro4.setFont(new Font("Serif", Font.PLAIN, 25));
		intro4.setBounds(100, 100, 1000, 70);
		intro4.setLocation(130,160);
		intro4.setVisible(false);
		level1TutPanel.add(intro4);

		JLabel intro5 = new JLabel("This note is B.");
		intro5.setFont(new Font("Serif", Font.PLAIN, 25));
		intro5.setBounds(100, 100, 1000, 70);
		intro5.setLocation(130,160);
		intro5.setVisible(false);
		level1TutPanel.add(intro5);

		JLabel intro6 = new JLabel("This note is C. And so on.");
		intro6.setFont(new Font("Serif", Font.PLAIN, 25));
		intro6.setBounds(100, 100, 1000, 70);
		intro6.setLocation(130,160);
		intro6.setVisible(false);
		level1TutPanel.add(intro6);

		JLabel intro7 = new JLabel("These buttons represent the note being played.");
		intro7.setFont(new Font("Serif", Font.PLAIN, 25));
		intro7.setBounds(100, 100, 1000, 70);
		intro7.setLocation(130,160);
		intro7.setVisible(false);
		level1TutPanel.add(intro7);

		JLabel intro8 = new JLabel("When a note gets to this box, press its matching button.");
		intro8.setFont(new Font("Serif", Font.PLAIN, 25));
		intro8.setBounds(100, 100, 1000, 70);
		intro8.setLocation(130,160);
		intro8.setVisible(false);
		level1TutPanel.add(intro8);

		JLabel intro9 = new JLabel("Let's try it with note A. Hit A when it gets to the box.");
		intro9.setFont(new Font("Serif", Font.PLAIN, 25));
		intro9.setBounds(100, 100, 1000, 70);
		intro9.setLocation(130,160);
		intro9.setVisible(false);
		level1TutPanel.add(intro9);

		JLabel intro10 = new JLabel("You are now ready to play the game.");
		intro10.setFont(new Font("Serif", Font.PLAIN, 25));
		intro10.setBounds(100, 100, 1000, 70);
		intro10.setLocation(130,160);
		intro10.setVisible(false);
		level1TutPanel.add(intro10);


		/****************************************************************
		 * Outlines that highlight the staff, note, and blue box during
		 * the tutorial
		 ***************************************************************/
		JLabel outline = new JLabel();
		Border border = BorderFactory.createLineBorder(Color.YELLOW, 20);
		outline.setBorder(border);
		outline.setBounds(1000, 1000, 700, 225);
		outline.setLocation(210,240);
		outline.setVisible(false);
		level1TutPanel.add(outline);

		//Note button outline
		JLabel outline2 = new JLabel();
		outline2.setBorder(border);
		outline2.setBounds(1000, 1000, 915, 150);
		outline2.setLocation(0,465);
		outline2.setVisible(false);
		level1TutPanel.add(outline2);

		//Blue box outline
		JLabel outline3 = new JLabel();
		outline3.setBorder(border);
		outline3.setBounds(100, 200, 150, 225);
		outline3.setLocation(80,240);
		outline3.setVisible(false);
		level1TutPanel.add(outline3);

		/****************************************************************
		 * Initializes notes used in the tutorial
		 ***************************************************************/
		JLabel noteA = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"quarterNote.png"));
		noteA.setBounds(0, 80, 209, 297);
		noteA.setLocation(310, 360);
		noteA.setVisible(false);
		noteA.setOpaque(false);
		level1TutPanel.add(noteA);
		//noteA.setContentAreaFilled(false);
		//noteA.setBorderPainted(false);

		JLabel noteB = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"quarterNote.png"));
		noteB.setBounds(0, 80, 209, 297);
		noteB.setLocation(510, 330);
		noteB.setVisible(false);
		noteB.setOpaque(false);
		level1TutPanel.add(noteB);

		JLabel noteC = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"quarterNote.png"));
		noteC.setBounds(0, 80, 209, 297);
		noteC.setLocation(710, 300);
		noteC.setVisible(false);
		noteC.setOpaque(false);
		level1TutPanel.add(noteC);

		/****************************************************************
		 * Next buttons to continue in the tutorial. (Couldn't find out
		 * how to reuse buttons more than once. Incredibly wasteful,
		 * just like the intro text labels...
		 ***************************************************************/
		JButton next = new JButton("Next");
		next.setFont(new Font("Serif", Font.PLAIN, 40));
		next.setOpaque(true);
		next.setBounds(50, 50, 150, 50);
		next.setLocation(750,170);
		level1TutPanel.add(next);

		JButton next2 = new JButton("Next");
		next2.setFont(new Font("Serif", Font.PLAIN, 40));
		next2.setOpaque(true);
		next2.setVisible(false);
		next2.setBounds(50, 50, 150, 50);
		next2.setLocation(750,170);
		level1TutPanel.add(next2);

		JButton next3 = new JButton("Next");
		next3.setFont(new Font("Serif", Font.PLAIN, 40));
		next3.setOpaque(true);
		next3.setVisible(false);
		next3.setBounds(50, 50, 150, 50);
		next3.setLocation(750,170);
		level1TutPanel.add(next3);

		JButton next4 = new JButton("Next");
		next4.setFont(new Font("Serif", Font.PLAIN, 40));
		next4.setOpaque(true);
		next4.setVisible(false);
		next4.setBounds(50, 50, 150, 50);
		next4.setLocation(750,170);
		level1TutPanel.add(next4);

		JButton next5 = new JButton("Next");
		next5.setFont(new Font("Serif", Font.PLAIN, 40));
		next5.setOpaque(true);
		next5.setVisible(false);
		next5.setBounds(50, 50, 150, 50);
		next5.setLocation(750,170);
		level1TutPanel.add(next5);

		JButton next6 = new JButton("Next");
		next6.setFont(new Font("Serif", Font.PLAIN, 40));
		next6.setOpaque(true);
		next6.setVisible(false);
		next6.setBounds(50, 50, 150, 50);
		next6.setLocation(750,170);
		level1TutPanel.add(next6);

		JButton next7 = new JButton("Next");
		next7.setFont(new Font("Serif", Font.PLAIN, 40));
		next7.setOpaque(true);
		next7.setVisible(false);
		next7.setBounds(50, 50, 150, 50);
		next7.setLocation(750,170);
		level1TutPanel.add(next7);

		JButton next8 = new JButton("Next");
		next8.setFont(new Font("Serif", Font.PLAIN, 40));
		next8.setOpaque(true);
		next8.setVisible(false);
		next8.setBounds(50, 50, 150, 50);
		next8.setLocation(750,170);
		level1TutPanel.add(next8);

		JButton next9 = new JButton("Level1");
		next9.setFont(new Font("Serif", Font.PLAIN, 40));
		next9.setOpaque(true);
		next9.setVisible(false);
		next9.setBounds(50, 50, 150, 50);
		next9.setLocation(750,170);
		level1TutPanel.add(next9);

		/***********************************************************
    	 * Initializes Note Buttons
    	 **********************************************************/
		JButton aButton = new JButton("A");
		aButton.setBounds(0,0,113,113);
		aButton.setLocation(19,490);
		aButton.setFont(new Font("Serif", Font.PLAIN, 100));
		aButton.setVisible(false);
		level1TutPanel.add(aButton);

		JButton bButton = new JButton("B");
		bButton.setBounds(0,0,113,113);
		bButton.setLocation(141,490);
		bButton.setFont(new Font("Serif", Font.PLAIN, 100));
		bButton.setVisible(false);
		level1TutPanel.add(bButton);

		JButton cButton = new JButton("C");
		cButton.setBounds(0,0,113,113);
		cButton.setLocation(283,490);
		cButton.setFont(new Font("Serif", Font.PLAIN, 100));
		cButton.setVisible(false);
		level1TutPanel.add(cButton);

		JButton dButton = new JButton("D");
		dButton.setBounds(0,0,113,113);
		dButton.setLocation(424,490);
		dButton.setFont(new Font("Serif", Font.PLAIN, 100));
		dButton.setVisible(false);
		level1TutPanel.add(dButton);

		JButton eButton = new JButton("E");
		eButton.setBounds(0,0,113,113);
		eButton.setLocation(555,490);
		eButton.setFont(new Font("Serif", Font.PLAIN, 100));
		eButton.setVisible(false);
		level1TutPanel.add(eButton);

		JButton fButton = new JButton("F");
		fButton.setBounds(0,0,113,113);
		fButton.setLocation(675,490);
		fButton.setFont(new Font("Serif", Font.PLAIN, 100));
		fButton.setVisible(false);
		level1TutPanel.add(fButton);

		JButton gButton = new JButton("G");
		gButton.setBounds(0,0,113,113);
		gButton.setLocation(795,490);
		gButton.setFont(new Font("Serif", Font.PLAIN, 100));
		gButton.setVisible(false);
		level1TutPanel.add(gButton);

		level1TutPanel.setVisible(true);

		Game currentRound = new Game(0);

		/****************************************************************
		 * All the next action listeners... very ugly I know
		 ***************************************************************/
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outline.setVisible(true);
				intro.setVisible(false);
				intro2.setVisible(true);
				next.setVisible(false);
				next2.setVisible(true);
			}
		});

		next2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outline.setVisible(false);
				intro2.setVisible(false);
				intro3.setVisible(true);
				next2.setVisible(false);
				next3.setVisible(true);
			}
		});

		next3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intro3.setVisible(false);
				intro4.setVisible(true);
				noteA.setVisible(true);
				soundPlayer.noteSound("a");
				next3.setVisible(false);
				next4.setVisible(true);
			}
		});

		next4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intro4.setVisible(false);
				intro5.setVisible(true);
				noteB.setVisible(true);
				soundPlayer.noteSound("b");
				next4.setVisible(false);
				next5.setVisible(true);
			}
		});

		next5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intro5.setVisible(false);
				intro6.setVisible(true);
				noteC.setVisible(true);
				soundPlayer.noteSound("c");
				next5.setVisible(false);
				next6.setVisible(true);
			}
		});

		next6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				noteA.setVisible(false);
				noteB.setVisible(false);
				noteC.setVisible(false);
				intro6.setVisible(false);
				intro7.setVisible(true);
				outline2.setVisible(true);
				next6.setVisible(false);
				next7.setVisible(true);
				aButton.setVisible(true);
				bButton.setVisible(true);
				cButton.setVisible(true);
				dButton.setVisible(true);
				eButton.setVisible(true);
				fButton.setVisible(true);
				gButton.setVisible(true);
			}
		});

		next7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				intro7.setVisible(false);
				intro8.setVisible(true);
				outline2.setVisible(false);
				outline3.setVisible(true);
				next7.setVisible(false);
				next8.setVisible(true);

			}
		});

		next8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				intro8.setVisible(false);
				intro9.setVisible(true);
				outline3.setVisible(false);
				next8.setVisible(false);
				next9.setVisible(true);

				currentRound.display(frame);
				currentRound.play(frame,endMessage);
				//currentRound.play(frame, endMessage);
				//int score = currentRound.points;
				//currentRound.endTutorial(frame, endMessage);
		/*		while(currentRound.points == 0) {
					currentRound.display(frame);
					currentRound.play(frame, endMessage);
					currentRound.endTutorial(frame, endMessage);
				}  */
			}
		});

		//removes help message after 10 seconds
		int delay = 8000; //milliseconds
		   ActionListener taskPerformer = new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   intro9.setVisible(false);
		       }
		   };
		   new Timer(delay, taskPerformer).start();

		next9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				level1TutPanel.setVisible(false);
				currentLevel = 1;
				level1();
			}
		});

		//currentRound.play(frame,endMessage);

		/****************************************************************
		 * Note button action listeners
		 ***************************************************************/
		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("a")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
					soundPlayer.noteSound("a");
					}
		});
		bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("b")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("b");
			}
		});

		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("c")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("c");
			}
		});

		dButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("d")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("d");
			}
		});

		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("e")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("e");
			}
		});

		fButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("f")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("f");
			}
		});

		gButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("g")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("g");
			}
		});

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					level1TutPanel.setVisible(false);
					levelScreen();
					currentRound.end();

			}
		});

		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					currentRound.end();
					currentRound.points = 0;
					scoreValue.setText("" + currentRound.points);
					currentRound.display(frame);

			}
		});
	}

	public void level2Tutorial() {

		ImagePanel level2TutPanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelBackgroundGreen.png").getImage());
		frame.getContentPane().add(level2TutPanel);

		//Label that tells user the level is a tutorial
		tutorial.setFont(new Font("Serif", Font.BOLD, 65));
		tutorial.setBounds(1000, 100, 800, 50);
		tutorial.setLocation(375,90);
		tutorial.setVisible(true);
		level2TutPanel.add(tutorial);

		/****************************************************************
		 * Score
		 ***************************************************************/
	    JLabel scoreText = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"scoreText.png"));
	    scoreText.setBounds(0, 80, 209, 260);
	    scoreText.setLocation(0, 2);
	    scoreText.setOpaque(false);
	    level2TutPanel.add(scoreText);

	    JLabel scoreValue = new JLabel("0");
	    scoreValue.setFont(new Font("Serif", Font.PLAIN, 80));
	    scoreValue.setBounds(50, 50, 120, 120);
	    scoreValue.setLocation(215,65);
	    scoreValue.setOpaque(false);
	    level2TutPanel.add(scoreValue);

	    JLabel endMessage = new JLabel("");
		endMessage.setFont(new Font("Serif", Font.PLAIN, 35));
		endMessage.setBounds(1000, 100, 1000, 70);
		endMessage.setLocation(130,160);
		endMessage.setVisible(true);
		level2TutPanel.add(endMessage);

		//Back Button
        JButton backButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"backButton.png"));
        backButton.setBounds(0, 80, 84, 43);
        backButton.setLocation(833,94);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
		level2TutPanel.add(backButton);

		//Restart Button
		JButton restartButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"restartButton.png"));
		restartButton.setBounds(0, 80, 100,100);
		restartButton.setLocation(740,70);
		restartButton.setOpaque(false);
		restartButton.setContentAreaFilled(false);
		restartButton.setBorderPainted(false);
	    level2TutPanel.add(restartButton);

	    /****************************************************************
		 * Tutorials
		 ***************************************************************/
		JLabel intro = new JLabel("You made it to level 2!");
		intro.setFont(new Font("Serif", Font.PLAIN, 35));
		intro.setBounds(100, 100, 1000, 70);
		intro.setLocation(130,160);
		intro.setVisible(true);
		level2TutPanel.add(intro);

		JLabel intro2 = new JLabel("This is note B.");
		intro2.setFont(new Font("Serif", Font.PLAIN, 35));
		intro2.setBounds(100, 100, 1000, 70);
		intro2.setLocation(130,160);
		intro2.setVisible(false);
		level2TutPanel.add(intro2);

		JLabel intro3 = new JLabel("Hit B at the right moment.");
		intro3.setFont(new Font("Serif", Font.PLAIN, 35));
		intro3.setBounds(100, 100, 1000, 70);
		intro3.setLocation(130,160);
		intro3.setVisible(false);
		level2TutPanel.add(intro3);

		JLabel intro4 = new JLabel("Good job! This level will have both notes A and B.");
		intro4.setFont(new Font("Serif", Font.PLAIN, 35));
		intro4.setBounds(100, 100, 1000, 70);
		intro4.setLocation(130,160);
		intro4.setVisible(false);
		level2TutPanel.add(intro4);

		JLabel intro5 = new JLabel("If you forget what a note is, click on the treble clef for a hint.");
		intro4.setFont(new Font("Serif", Font.PLAIN, 35));
		intro4.setBounds(100, 100, 1000, 70);
		intro4.setLocation(130,160);
		intro4.setVisible(false);
		level2TutPanel.add(intro4);

		JLabel intro6 = new JLabel("Let's play level 2!");
		intro4.setFont(new Font("Serif", Font.PLAIN, 35));
		intro4.setBounds(100, 100, 1000, 70);
		intro4.setLocation(130,160);
		intro4.setVisible(false);
		level2TutPanel.add(intro4);

		/****************************************************************
		 * Initializes note used in the tutorial
		 ****************************************************************/

		JLabel noteB = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"quarterNote.png"));
		noteB.setBounds(0, 80, 209, 297);
		noteB.setLocation(550, 330);
		noteB.setVisible(false);
		noteB.setOpaque(false);
		level2TutPanel.add(noteB);

		/****************************************************************
		 * Next buttons to continue in the tutorial. (Couldn't find out
		 * how to reuse buttons more than once. Incredibly wasteful,
		 * just like the intro text labels...
		 ***************************************************************/
		JButton next = new JButton("Next");
		next.setFont(new Font("Serif", Font.PLAIN, 40));
		next.setOpaque(true);
		next.setBounds(50, 50, 150, 50);
		next.setLocation(750,170);
		level2TutPanel.add(next);

		JButton next2 = new JButton("Next");
		next2.setFont(new Font("Serif", Font.PLAIN, 40));
		next2.setOpaque(true);
		next2.setVisible(false);
		next2.setBounds(50, 50, 150, 50);
		next2.setLocation(750,170);
		level2TutPanel.add(next2);

		JButton next3 = new JButton("Next");
		next3.setFont(new Font("Serif", Font.PLAIN, 40));
		next3.setOpaque(true);
		next3.setVisible(false);
		next3.setBounds(50, 50, 150, 50);
		next3.setLocation(750,170);
		level2TutPanel.add(next3);

		JButton next4 = new JButton("Next");
		next4.setFont(new Font("Serif", Font.PLAIN, 40));
		next4.setOpaque(true);
		next4.setVisible(false);
		next4.setBounds(50, 50, 150, 50);
		next4.setLocation(750,170);
		level2TutPanel.add(next4);

		JButton next5 = new JButton("Next");
		next5.setFont(new Font("Serif", Font.PLAIN, 40));
		next5.setOpaque(true);
		next5.setVisible(false);
		next5.setBounds(50, 50, 150, 50);
		next5.setLocation(750,170);
		level2TutPanel.add(next5);

		JButton next6 = new JButton("Level 2");
		next6.setFont(new Font("Serif", Font.PLAIN, 40));
		next6.setOpaque(true);
		next6.setVisible(false);
		next6.setBounds(50, 50, 150, 50);
		next6.setLocation(750,170);
		level2TutPanel.add(next6);

		/***********************************************************
    	 * Initializes Note Buttons
    	 **********************************************************/
		JButton aButton = new JButton("A");
		aButton.setBounds(0,0,113,113);
		aButton.setLocation(19,490);
		aButton.setFont(new Font("Serif", Font.PLAIN, 100));
		aButton.setVisible(true);
		level2TutPanel.add(aButton);

		JButton bButton = new JButton("B");
		bButton.setBounds(0,0,113,113);
		bButton.setLocation(141,490);
		bButton.setFont(new Font("Serif", Font.PLAIN, 100));
		bButton.setVisible(true);
		level2TutPanel.add(bButton);

		JButton cButton = new JButton("C");
		cButton.setBounds(0,0,113,113);
		cButton.setLocation(283,490);
		cButton.setFont(new Font("Serif", Font.PLAIN, 100));
		cButton.setVisible(true);
		level2TutPanel.add(cButton);

		JButton dButton = new JButton("D");
		dButton.setBounds(0,0,113,113);
		dButton.setLocation(424,490);
		dButton.setFont(new Font("Serif", Font.PLAIN, 100));
		dButton.setVisible(true);
		level2TutPanel.add(dButton);

		JButton eButton = new JButton("E");
		eButton.setBounds(0,0,113,113);
		eButton.setLocation(555,490);
		eButton.setFont(new Font("Serif", Font.PLAIN, 100));
		eButton.setVisible(true);
		level2TutPanel.add(eButton);

		JButton fButton = new JButton("F");
		fButton.setBounds(0,0,113,113);
		fButton.setLocation(675,490);
		fButton.setFont(new Font("Serif", Font.PLAIN, 100));
		fButton.setVisible(true);
		level2TutPanel.add(fButton);

		JButton gButton = new JButton("G");
		gButton.setBounds(0,0,113,113);
		gButton.setLocation(795,490);
		gButton.setFont(new Font("Serif", Font.PLAIN, 100));
		gButton.setVisible(true);
		level2TutPanel.add(gButton);

		level2TutPanel.setVisible(true);
		Game currentRound = new Game(-2);

		/****************************************************************
		 * All the next action listeners... very ugly I know
		 ***************************************************************/
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intro.setVisible(false);
				intro2.setVisible(true);
				noteB.setVisible(true);
				soundPlayer.noteSound("b");
				next.setVisible(false);
				next2.setVisible(true);
			}
		});

		next2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				intro2.setVisible(false);
				noteB.setVisible(false);
				intro3.setVisible(true);
				next2.setVisible(false);
				next3.setVisible(true);

				currentRound.display(frame);
				currentRound.play(frame,endMessage);
				//currentRound.play(frame, endMessage);
				//int score = currentRound.points;
				//currentRound.endTutorial(frame, endMessage);
		/*		while(currentRound.points == 0) {
					currentRound.display(frame);
					currentRound.play(frame, endMessage);
					currentRound.endTutorial(frame, endMessage);
				}  */
			}
		});

		//removes help message after 10 seconds
				int delay = 8000; //milliseconds
				   ActionListener taskPerformer = new ActionListener() {
				       public void actionPerformed(ActionEvent e) {
				           intro3.setText("");
				       }
				   };
				   new Timer(delay, taskPerformer).start();

		next3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intro3.setVisible(false);
				intro4.setVisible(true);
				next3.setVisible(false);
				next4.setVisible(true);
			}
		});

		next4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intro4.setVisible(false);
				intro5.setVisible(true);
				next4.setVisible(false);
				next5.setVisible(true);
			}
		});

		next5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intro5.setVisible(false);
				intro6.setVisible(true);
				next5.setVisible(false);
				next6.setVisible(true);
			}
		});

		next6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				level2TutPanel.setVisible(false);
				currentLevel = 2;
				level2();
			}
		});


		//currentRound.play(frame,endMessage);

		/****************************************************************
		 * Note button action listeners
		 ***************************************************************/
		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("a")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
					soundPlayer.noteSound("a");
					}
		});
		bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("b")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("b");
			}
		});

		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("c")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("c");
			}
		});

		dButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("d")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("d");
			}
		});

		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("e")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("e");
			}
		});

		fButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("f")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("f");
			}
		});

		gButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("g")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("g");
			}
		});

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					level2TutPanel.setVisible(false);
					levelScreen();
					currentRound.end();

			}
		});

		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					currentRound.end();
					currentRound.points = 0;
					scoreValue.setText("" + currentRound.points);
					currentRound.display(frame);
					currentRound.play(frame, endMessage);

			}
		});
	}

	public void levelScreen() {

		JLayeredPane levelMenuPanel = new JLayeredPane();
		levelMenuPanel.setBounds(0,0,930,658);
		frame.add(levelMenuPanel);

		//Set Background
		ImagePanel levelBackgroundPanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelSelectBackground.png").getImage());

		//Set Level Connecting Line
		ImagePanel levelLinePanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelLine.png").getImage());

		//Level 1 label
		JLabel level1ButtonLabel = new JLabel("1");
		level1ButtonLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		level1ButtonLabel.setBounds(50, 50, 50, 50);
		level1ButtonLabel.setLocation(100,160);
		level1ButtonLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(level1ButtonLabel);

		//Level 1 button
		JButton level1Button;
		if(currentLevel < 1) {
			level1Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedLevelButton.png"));

		}
		else if(currentLevel == 1) {
			level1Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"currentLevelButton.png"));

		}
		else {
			level1Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"completedLevelButton.png"));

		}
		level1Button.setBounds(18, 80, 209, 297);
		level1Button.setLocation(4,38);
		level1Button.setOpaque(false);
		level1Button.setContentAreaFilled(false);
		level1Button.setBorderPainted(false);
		levelMenuPanel.add(level1Button);

		//Level 2 label
		JLabel level2ButtonLabel = new JLabel("2");
		level2ButtonLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		level2ButtonLabel.setBounds(50, 50, 50, 50);
		level2ButtonLabel.setLocation(220,340);
		level2ButtonLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(level2ButtonLabel);

		//Level 2 button
		JButton level2Button;
		if(currentLevel < 2) {
			level2Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedLevelButton.png"));

		}
		else if(currentLevel == 2) {
			level2Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"currentLevelButton.png"));

		}
		else {
			level2Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"completedLevelButton.png"));

		}
		level2Button.setBounds(18, 80, 209, 297);
		level2Button.setLocation(128,195);
		level2Button.setOpaque(false);
		level2Button.setContentAreaFilled(false);
		level2Button.setBorderPainted(false);
		levelMenuPanel.add(level2Button);

		//level 3 label
		JLabel level3Label = new JLabel("3");
		level3Label.setFont(new Font("Serif", Font.PLAIN, 40));
		level3Label.setBounds(50, 50, 50, 50);
		level3Label.setLocation(325,475);
		level3Label.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(level3Label);

		//Level 3 button
		JButton level3Button;
		if(currentLevel < 3) {
			level3Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedLevelButton.png"));
		}
		else if(currentLevel == 3) {
			level3Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"currentLevelButton.png"));
		}
		else {
			level3Button = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"completedLevelButton.png"));
		}
		level3Button.setBounds(18, 80, 209, 297);
		level3Button.setLocation(233,360);
		level3Button.setOpaque(false);
		level3Button.setContentAreaFilled(false);
		level3Button.setBorderPainted(false);
		levelMenuPanel.add(level3Button);

		//Boss level label
		JLabel bossLabel = new JLabel("4");
		bossLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		bossLabel.setBounds(50, 50, 50, 50);
		bossLabel.setLocation(540,450);
		bossLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(bossLabel);

		//Boss level button
		JButton bossButton;
		bossButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedBossLevelButton.png"));
		bossButton.setBounds(18, 80, 152, 148);
		bossButton.setLocation(475,405);
		bossButton.setOpaque(false);
		bossButton.setContentAreaFilled(false);
		bossButton.setBorderPainted(false);
		levelMenuPanel.add(bossButton);

		//Log out button
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(50, 100, 200, 30);
		logoutBtn.setLocation(760,45);
		logoutBtn.setFont(new Font("Serif", Font.PLAIN, 30));
		logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorderPainted(false);
		levelMenuPanel.add(logoutBtn);

		//Label with user name
		JLabel userLabel = new JLabel(username);
		userLabel.setFont(new Font("Serif", Font.BOLD, 40));
		userLabel.setBounds(50, 100, 100, 30);
		userLabel.setLocation(788,15);
		levelMenuPanel.add(userLabel);

		//Inserting ImagePanels into LayeredPanel
		levelMenuPanel.add(levelLinePanel);
		levelMenuPanel.add(levelBackgroundPanel);

		levelMenuPanel.setVisible(true);

		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelMenuPanel.setVisible(false);
				login();
			}
		});

		level1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					levelMenuPanel.setVisible(false);
					level1();
			}
		});

		level2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					levelMenuPanel.setVisible(false);
					level2Tutorial();
			}
		});

		// level3Button.addActionListener(new ActionListener() {
		// 	public void actionPerformed(ActionEvent e) {
		// 			levelMenuPanel.setVisible(false);
		// 			level3Tutorial();
		// 	}
		// });
	}

public void level1() {

		ImagePanel level1Panel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelBackgroundGreen.png").getImage());
		frame.getContentPane().add(level1Panel);

		level1Panel.setVisible(true);
		Game currentRound = new Game(currentLevel);
		currentRound.display(frame);

		//Back Button
        JButton backButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"backButton.png"));
        backButton.setBounds(0, 80, 84, 43);
        backButton.setLocation(833,94);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
		level1Panel.add(backButton);

		//Restart Button
		JButton restartButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"restartButton.png"));
		restartButton.setBounds(0, 80, 100,100);
		restartButton.setLocation(740,70);
		restartButton.setOpaque(false);
		restartButton.setContentAreaFilled(false);
		restartButton.setBorderPainted(false);
	    level1Panel.add(restartButton);

	    //Score Text
	    JLabel scoreText = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"scoreText.png"));
	    scoreText.setBounds(0, 80, 209, 260);
	    scoreText.setLocation(0, 2);
	    scoreText.setOpaque(false);
	    level1Panel.add(scoreText);

	    //Score Value
	    JLabel scoreValue = new JLabel("0");
	    scoreValue.setFont(new Font("Serif", Font.PLAIN, 80));
	    scoreValue.setBounds(50, 50, 120, 120);
	    scoreValue.setLocation(215,65);
	    scoreValue.setOpaque(false);
	    level1Panel.add(scoreValue);

	    //Level Text
	    JLabel levelText = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelText.png"));
	    levelText.setBounds(0, 80, 209, 260);
	    levelText.setLocation(430, 2);
	    levelText.setOpaque(false);
	    level1Panel.add(levelText);

	    //Level Number
	    JLabel levelNum = new JLabel("1");
	    levelNum.setFont(new Font("Serif", Font.PLAIN, 80));
	    levelNum.setBounds(50, 50, 80, 80);
	    levelNum.setLocation(640,80);
	    levelNum.setOpaque(false);
	    level1Panel.add(levelNum);

	    //Treble clef help button
		JButton helpButton = new JButton("");
		helpButton.setBounds(70, 220, 60, 100);
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);
		level1Panel.add(helpButton);

		//Treble clef help message
		// JLabel helpMessage = new JLabel("Click on the button that matches the note when" +
		// " it gets to the box.");
		// helpMessage.setFont(new Font("Serif", Font.PLAIN, 35));
		// helpMessage.setBounds(50, 50, 50, 50);
		// helpMessage.setLocation(1050,500);
		// helpMessage.setVisible(false);
		// level1Panel.add(helpMessage);

		//Treble clef help message
		JLabel help = new JLabel("Click the treble clef (to the left) for help.");
		help.setFont(new Font("Serif", Font.PLAIN, 25));
		help.setBounds(1000, 100, 1000, 50);
		help.setLocation(150,150);
		help.setVisible(true);
		level1Panel.add(help);

		//A Button
		JButton aButton = new JButton("A");
		aButton.setBounds(0,0,113,113);
		aButton.setLocation(19,490);
		aButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level1Panel.add(aButton);
		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("a")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("a");
			}
		});

		//B Button
		JButton bButton = new JButton("B");
		bButton.setBounds(0,0,113,113);
		bButton.setLocation(141,490);
		level1Panel.add(bButton);
		bButton.setFont(new Font("Serif", Font.PLAIN, 100));
		bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("b")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("b");
			}
		});

		//C Button
		JButton cButton = new JButton("C");
		cButton.setBounds(0,0,113,113);
		cButton.setLocation(283,490);
		cButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level1Panel.add(cButton);
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("c")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("c");
			}
		});

		//D Button
		JButton dButton = new JButton("D");
		dButton.setBounds(0,0,113,113);
		dButton.setLocation(424,490);
		dButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level1Panel.add(dButton);
		dButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("d")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("d");
			}
		});

		//E Button
		JButton eButton = new JButton("E");
		eButton.setBounds(0,0,113,113);
		eButton.setLocation(555,490);
		eButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level1Panel.add(eButton);
		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("e")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("e");
			}
		});

		//F Button
		JButton fButton = new JButton("F");
		fButton.setBounds(0,0,113,113);
		fButton.setLocation(675,490);
		fButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level1Panel.add(fButton);
		fButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("f")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("f");
			}
		});

		//G Button
		JButton gButton = new JButton("G");
		gButton.setBounds(0,0,113,113);
		gButton.setLocation(795,490);
		gButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level1Panel.add(gButton);
		gButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("g")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("g");
			}
		});

		//Label with user's name
		JLabel userLabel = new JLabel(username);
		userLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		userLabel.setBounds(50, 100, 100, 30);
		userLabel.setLocation(788,15);
		level1Panel.add(userLabel);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					level1Panel.setVisible(false);
					levelScreen();
					currentRound.end();

			}
		});

		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					currentRound.end();
					currentRound.points = 0;
					scoreValue.setText("" + currentRound.points);
					currentRound.display(frame);

			}
		});

		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help.setText("Click on the button that matches the note when it gets to the box");
			}
		});

		//removes help message after 10 seconds
		int delay = 10000; //milliseconds
		   ActionListener taskPerformer = new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		           help.setText("");
		       }
		   };
		   new Timer(delay, taskPerformer).start();

		currentRound.play(frame,help);
		currentLevel++;
	}

private void level2() {

		ImagePanel level2Panel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelBackgroundGreen.png").getImage());
		frame.getContentPane().add(level2Panel);

		level2Panel.setVisible(true);
		Game currentRound = new Game(2);
		currentRound.display(frame);

		//Back Button
        JButton backButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"backButton.png"));
        backButton.setBounds(0, 80, 84, 43);
        backButton.setLocation(833,94);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        level2Panel.add(backButton);

		//Restart Button
		JButton restartButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"restartButton.png"));
		restartButton.setBounds(0, 80, 100,100);
		restartButton.setLocation(740,70);
		restartButton.setOpaque(false);
		restartButton.setContentAreaFilled(false);
		restartButton.setBorderPainted(false);
		level2Panel.add(restartButton);

	    //Score Text
	    JLabel scoreText = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"scoreText.png"));
	    scoreText.setBounds(0, 80, 209, 260);
	    scoreText.setLocation(0, 2);
	    scoreText.setOpaque(false);
	    level2Panel.add(scoreText);

	    //Score Value
	    JLabel scoreValue = new JLabel("0");
	    scoreValue.setFont(new Font("Serif", Font.PLAIN, 80));
	    scoreValue.setBounds(50, 50, 120, 120);
	    scoreValue.setLocation(215,65);
	    scoreValue.setOpaque(false);
	    level2Panel.add(scoreValue);

	    //Level Text
	    JLabel levelText = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelText.png"));
	    levelText.setBounds(0, 80, 209, 260);
	    levelText.setLocation(430, 2);
	    levelText.setOpaque(false);
	    level2Panel.add(levelText);

	    //Level Number
	    JLabel levelNum = new JLabel("2");
	    levelNum.setFont(new Font("Serif", Font.PLAIN, 80));
	    levelNum.setBounds(50, 50, 80, 80);
	    levelNum.setLocation(640,80);
	    levelNum.setOpaque(false);
	    level2Panel.add(levelNum);

	    //Treble clef help button
		JButton helpButton = new JButton("");
		helpButton.setBounds(70, 220, 60, 100);
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);
		level2Panel.add(helpButton);

		//Treble clef help message
		// JLabel helpMessage = new JLabel("Click on the button that matches the note when" +
		// " it gets to the box.");
		// helpMessage.setFont(new Font("Serif", Font.PLAIN, 35));
		// helpMessage.setBounds(50, 50, 50, 50);
		// helpMessage.setLocation(1050,500);
		// helpMessage.setVisible(false);
		// level1Panel.add(helpMessage);

		//Treble clef help message
		JLabel help = new JLabel("Click the treble clef (to the left) for help.");
		help.setFont(new Font("Serif", Font.PLAIN, 30));
		help.setBounds(1000, 100, 1000, 50);
		help.setLocation(150,150);
		help.setVisible(true);
		level2Panel.add(help);

		//A Button
		JButton aButton = new JButton("A");
		aButton.setBounds(0,0,113,113);
		aButton.setLocation(19,490);
		aButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level2Panel.add(aButton);
		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("a")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("a");
			}
		});

		//B Button
		JButton bButton = new JButton("B");
		bButton.setBounds(0,0,113,113);
		bButton.setLocation(141,490);
		level2Panel.add(bButton);
		bButton.setFont(new Font("Serif", Font.PLAIN, 100));
		bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("b")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("b");
			}
		});

		//C Button
		JButton cButton = new JButton("C");
		cButton.setBounds(0,0,113,113);
		cButton.setLocation(283,490);
		cButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level2Panel.add(cButton);
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("c")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("c");
			}
		});

		//D Button
		JButton dButton = new JButton("D");
		dButton.setBounds(0,0,113,113);
		dButton.setLocation(424,490);
		dButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level2Panel.add(dButton);
		dButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("d")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("d");
			}
		});

		//E Button
		JButton eButton = new JButton("E");
		eButton.setBounds(0,0,113,113);
		eButton.setLocation(555,490);
		eButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level2Panel.add(eButton);
		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("e")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("e");
			}
		});

		//F Button
		JButton fButton = new JButton("F");
		fButton.setBounds(0,0,113,113);
		fButton.setLocation(675,490);
		fButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level2Panel.add(fButton);
		fButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("f")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("f");
			}
		});

		//G Button
		JButton gButton = new JButton("G");
		gButton.setBounds(0,0,113,113);
		gButton.setLocation(795,490);
		gButton.setFont(new Font("Serif", Font.PLAIN, 100));
		level2Panel.add(gButton);
		gButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRound.currentNote.equals("g")) {
					currentRound.points++;
					scoreValue.setText("" + currentRound.points);
				}
				soundPlayer.noteSound("g");
			}
		});

		//Label with user's name
		JLabel userLabel = new JLabel(username);
		userLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		userLabel.setBounds(50, 100, 100, 30);
		userLabel.setLocation(788,15);
		level2Panel.add(userLabel);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					level2Panel.setVisible(false);
					levelScreen();
					currentRound.end();

			}
		});

		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					currentRound.end();
					currentRound.points = 0;
					scoreValue.setText("" + currentRound.points);
					currentRound.display(frame);

			}
		});

		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help.setText("Click on the button that matches the note when it gets to the box");
			}
		});

		//removes help message after 10 seconds
		int delay = 10000; //milliseconds
		   ActionListener taskPerformer = new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		           help.setText("");
		       }
		   };
		   new Timer(delay, taskPerformer).start();

		currentRound.play(frame,help);
		currentLevel++;
	}

public void incrementCurrentLevel(int currentLevel) {
	levelComplete[currentLevel] = true;
	if(currentLevel < 4)
		currentLevel++;
}
}
