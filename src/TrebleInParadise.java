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

		//Level 1 button
		level1 = new JButton(new ImageIcon("assets"+File.separator+"img"+File.separator+"currentLevelButton.png"));
		level1.setBounds(18, 80, 209, 297);
		level1.setLocation(5,50);
		level1.setOpaque(false);
		level1.setContentAreaFilled(false);
		level1.setBorderPainted(false);
		levelMenuPanel.add(level1);

		//Inserting ImagePanels into LayeredPanel
		levelMenuPanel.add(levelLockedPanel);
		levelMenuPanel.add(levelLockedPanel1);
		levelMenuPanel.add(levelLockedBossPanel);
		levelMenuPanel.add(levelLinePanel);
		levelMenuPanel.add(levelBackgroundPanel);
		
		//Repositioning images
		levelLockedPanel.setLocation(350,575);
		levelLockedPanel1.setLocation(205,340);
		levelLockedBossPanel.setLocation(600,565);
		levelBackgroundPanel.setLocation(0,0);

		frame.pack();
		frame.setSize(1240,877);
		
		//level2 = new JButton("2");
		//level2.setBounds(158, 98, 73, 57);
		//levelMenuPanel.add(level2); 
		
		levelMenuPanel.setVisible(true);
		
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

		
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(158, 98, 73, 57);
		level1Panel.add(logoutBtn); 
		
		level1Panel.setVisible(true);
		Game currentRound = new Game(1);
		currentRound.display(level1Panel);
		
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				level1Panel.setVisible(false);
				login();
					
				
			}
		});
		
	}
	
	
}
