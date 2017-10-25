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
import java.awt.Image;
import java.io.*;
import javax.swing.JLayeredPane;

public class TrebleInParadise {

	private JFrame frame = new JFrame();
	private JTextField userField;
	private JTextField passField;
	private JButton startBtn;
	
	//Dummy user and password
	private String username = "test1";
	private String password = "pass";
	private JButton level1;
	private JButton level2;
	
	private ImagePanel loginPanel;
	private ImagePanel levelMenuPanel;
	private ImagePanel level1Panel;

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
		frame.setVisible(true);
	        
        userField=new JTextField();
        userField.setBounds(5, 5, 280, 50);
        userField.setHorizontalAlignment(JTextField.CENTER);
        userField.setLocation(550,300);
        loginPanel.add(userField);
        
        passField=new JTextField();
        passField.setBounds(5, 5, 280, 50);
        passField.setHorizontalAlignment(JTextField.CENTER);
        passField.setLocation(550,400);
        loginPanel.add(passField);
        
        startBtn=new JButton("START");
        startBtn.setBounds(5, 5, 280, 50);
        startBtn.setLocation(550,500);
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
		
		ImagePanel levelMenuPanel = new ImagePanel(new ImageIcon("assets"+File.separator+"img"+File.separator+"levelSelectBackground.png").getImage());
		frame.getContentPane().add(levelMenuPanel);
		
		level1 = new JButton("1");
		level1.setBounds(33, 98, 73, 57);
		levelMenuPanel.add(level1);
		
		level2 = new JButton("2");
		level2.setBounds(158, 98, 73, 57);
		levelMenuPanel.add(level2); 
		
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
		
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				level1Panel.setVisible(false);
				login();
					
			}
		});
		
	}
	
	
}
