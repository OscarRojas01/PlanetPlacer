import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel header;
	private JLabel quitMessage;
	
	private JButton quitGame;
	
	private JPanel optionsPanel;
	
	public GameOver() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.gray);
		
		formatMenu();
		
		this.add(header, BorderLayout.NORTH);
		this.add(optionsPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private void formatMenu() {
		header = new JLabel("Game Over");
		quitMessage = new JLabel("Exit");
		
		quitGame = new JButton();
		
		optionsPanel = new JPanel();
		
		header.setFont(new Font("SansSerif", Font.BOLD, 45));
		header.setForeground(new Color(106, 23, 122));
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setPreferredSize(new Dimension(this.getWidth(), 100));
		
		quitMessage.setFont(new Font("SansSerif", Font.BOLD, 15));
		quitGame.add(quitMessage);
		quitGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 125));
		optionsPanel.setBackground(Color.gray);
		optionsPanel.add(quitGame);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		Graphics2D g2D = (Graphics2D)g;
		g2D.drawImage(Planet.purplePlanet.getScaledInstance(50, 50, Image.SCALE_SMOOTH), this.getWidth()/6, this.getHeight()/5, null);
		g2D.drawImage(Planet.bluePlanet.getScaledInstance(35, 35, Image.SCALE_SMOOTH), this.getWidth()*3/4, this.getHeight()/2, null);
		g2D.drawImage(Planet.brownBluePlanet.getScaledInstance(60, 60, Image.SCALE_SMOOTH), this.getWidth()/4, this.getHeight()*3/4, null);
	}
}






