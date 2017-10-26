import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.*;
import javax.swing.JLayeredPane;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TrebleInParadise {

	private JFrame frame = new JFrame();
	private JTextField userField;
	private JTextField passField;
	private JButton startBtn;
	
	//Dummy user and password 
	private String username = "";
	private String password = "";
	private JButton level1;
	private JButton level2;
	
	private ImagePanel loginPanel;
	private ImagePanel levelMenuPanel;
	private	ImagePanel levelLinePanel;
	private	ImagePanel levelCompletePanel;
	private ImagePanel levelCurrentPanel;
	private ImagePanel levelLockedPanel;
	private ImagePanel levelLockedPanel1;
	private ImagePanel levelLockedBossPanel;
	private ImagePanel level1Panel;
	private ImagePanel levelBackgroundPanel;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void login() {
		
		loginPanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"loginScreenBackdrop.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(loginPanel);
		frame.pack();
		frame.setSize(1240,877);
		frame.setVisible(true);
	    
        userField=new JTextField();
        userField.setBounds(5, 5, 280, 50);
        userField.setHorizontalAlignment(JTextField.CENTER);
        userField.setLocation(482,300);
        loginPanel.add(userField);
        
        passField=new JTextField();
        passField.setBounds(5, 5, 280, 50);
        passField.setHorizontalAlignment(JTextField.CENTER);
        passField.setLocation(482,400);
        loginPanel.add(passField);
        
        ImageIcon imgIconPlayButton = new ImageIcon("assets"+File.separator+"img"+File.separator+"loginScreenPlayButton.png");
        Image imgPlayButton = imgIconPlayButton.getImage();
        startBtn=new JButton(new ImageIcon(imgPlayButton.getScaledInstance(275,70,java.awt.Image.SCALE_SMOOTH)));
        startBtn.setBounds(5, 5, 280, 75);
        startBtn.setLocation(482,500);
        loginPanel.add(startBtn);
        
		
		//checks username and password when start button pressed
		//and goes to main level screen if successful
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inUser = userField.getText();
				String inpass = passField.getText();
				if(inUser.equals(username) && inpass.equals(password)) {
					loginPanel.setVisible(false);
					levelScreen();
				}
			}
		});
		
	}
	
	private void levelScreen() {

		//Created ImagePanels to be inserted into a LayeredPanel when necessary
		levelLinePanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelLine.png").getImage());
		levelBackgroundPanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelSelectBackground.png").getImage());
		//levelCompletePanel = new ImagePanel(new ImageIcon("assets\\img\\completedLevelButton.png").getImage());
		//levelCurrentPanel = new ImagePanel(new ImageIcon("assets\\img\\currentLevelButton.png").getImage());
		levelLockedPanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedLevelButton.png").getImage());
		levelLockedPanel1 = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedLevelButton.png").getImage());
		levelLockedBossPanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedBossLevelButton.png").getImage());

		JLayeredPane levelMenuPanel = new JLayeredPane();
		levelMenuPanel.setBounds(0,0,1240,877);
		frame.add(levelMenuPanel);

		//Level 1 label
		JLabel level1Label = new JLabel("1");
		level1Label.setFont(new Font("Serif", Font.PLAIN, 40));
		level1Label.setBounds(50, 50, 50, 50);
		level1Label.setLocation(100,200);
		level1Label.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(level1Label);
		
		//Level 1 button
		level1 = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"currentLevelButton.png"));	
		level1.setBounds(18, 80, 209, 297);
		level1.setLocation(5,50);
		level1.setOpaque(false);
		level1.setContentAreaFilled(false);
		level1.setBorderPainted(false);
		levelMenuPanel.add(level1);
		
		//Level 1 label
		JLabel level2Label = new JLabel("2");
		level2Label.setFont(new Font("Serif", Font.PLAIN, 40));
		level2Label.setBounds(50, 50, 50, 50);
		level2Label.setLocation(265,380);
		level2Label.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(level2Label);
		
		//Level 2 button
		level2 = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"lockedLevelButton.png"));
		level2.setBounds(18, 80, 209, 297);
		level2.setLocation(170,260);
		level2.setOpaque(false);
		level2.setContentAreaFilled(false);
		level2.setBorderPainted(false);
		levelMenuPanel.add(level2);
		
		//level 3 label
		JLabel level3Label = new JLabel("3");
		level3Label.setFont(new Font("Serif", Font.PLAIN, 40));
		level3Label.setBounds(50, 50, 50, 50);
		level3Label.setLocation(405,605);
		level3Label.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(level3Label);
		
		//Boss level label
		JLabel bossLabel = new JLabel("4");
		bossLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		bossLabel.setBounds(50, 50, 50, 50);
		bossLabel.setLocation(665,620);
		bossLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		levelMenuPanel.add(bossLabel);
		
		//Log out button
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(50, 100, 100, 30);
		logoutBtn.setLocation(1050,60);
		levelMenuPanel.add(logoutBtn); 
		
		//Label with user's name
		JLabel userLabel = new JLabel(username);
		userLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		userLabel.setBounds(50, 100, 100, 30);
		userLabel.setLocation(1050,20);
		levelMenuPanel.add(userLabel); 
		
		//Inserting ImagePanels into LayeredPanel
		levelMenuPanel.add(levelLockedPanel);
		levelMenuPanel.add(levelLockedBossPanel);
		levelMenuPanel.add(levelLinePanel);
		levelMenuPanel.add(levelBackgroundPanel);
		
		//Repositioning images
		levelLockedPanel.setLocation(350,575);
		levelLockedBossPanel.setLocation(600,565);
		levelBackgroundPanel.setLocation(0,0);
		

		frame.pack();
		frame.setSize(1240,877);
		
		//level2 = new JButton("2");
		//level2.setBounds(158, 98, 73, 57);
		//levelMenuPanel.add(level2); 
		
		levelMenuPanel.setVisible(true);
		
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelMenuPanel.setVisible(false);
				login();
					
				
			}
		});
		
		level1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
					levelMenuPanel.setVisible(false);
					level1();
				
			}
		});
	}
	
	private void level1() {
		
		ImagePanel level1Panel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelBackgroundGreen.png").getImage());
		frame.getContentPane().add(level1Panel);
		
		//Back Button
        JButton backButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"backButton.png"));	
        backButton.setBounds(0, 80, 209, 297);
        backButton.setLocation(1050, 2);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
		level1Panel.add(backButton);
		
		//Restart Button
		 JButton restartButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"restartButton.png"));	
		 restartButton.setBounds(0, 80, 209, 297);
		 restartButton.setLocation(960, 2);
		 restartButton.setOpaque(false);
		 restartButton.setContentAreaFilled(false);
		 restartButton.setBorderPainted(false);
	     level1Panel.add(restartButton);
	     
	     //Pause Button
	     JButton pauseButton = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"pauseButton.png"));	
	     pauseButton.setBounds(0, 80, 209, 297);
	     pauseButton.setLocation(880, 2);
	     pauseButton.setOpaque(false);
	     pauseButton.setContentAreaFilled(false);
	     pauseButton.setBorderPainted(false);
	     level1Panel.add(pauseButton);
	     
	     //Score Text
	     JLabel scoreText = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"scoreText.png"));
	     scoreText.setBounds(0, 80, 209, 297);
	     scoreText.setLocation(0, 2);
	     scoreText.setOpaque(false);
	     level1Panel.add(scoreText);
	     
	     //Level Text
	     JLabel levelText = new JLabel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelText.png"));
	     levelText.setBounds(0, 80, 209, 297);
	     levelText.setLocation(430, 2);
	     levelText.setOpaque(false);
	     level1Panel.add(levelText);
		
		level1Panel.setVisible(true);
		Game currentRound = new Game(1);
		currentRound.display(frame);
		currentRound.play();
		
		//Label with user's name
		JLabel userLabel = new JLabel(username);
		userLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		userLabel.setBounds(50, 100, 100, 30);
		userLabel.setLocation(1050,20);
		level1Panel.add(userLabel); 
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					level1Panel.setVisible(false);
					levelScreen();
				
			}
		});
		
	}
	
	
}
