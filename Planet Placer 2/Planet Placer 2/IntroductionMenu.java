import java.awt.BorderLayout;
import java.awt.Color;
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

public class IntroductionMenu extends JFrame{
	private static final long serialVersionUID = 1L;
	JLabel introMessage;
	JLabel start;
	
	JButton startButton;
	
	JPanel buttonSpace;
	
	public IntroductionMenu() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.gray);
		
		formatMenu();
		this.add(introMessage, BorderLayout.NORTH);
		this.add(buttonSpace, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	private void formatMenu() {
		introMessage = new JLabel("Planet Placer");
		start = new JLabel("Start");
		
		startButton = new JButton();
		
		buttonSpace = new JPanel();
		
		introMessage.setFont(new Font("SansSerif", Font.BOLD, 45));
		introMessage.setForeground(Color.magenta);
		introMessage.setHorizontalAlignment(JLabel.CENTER);
		introMessage.setSize(this.getWidth(), 100);
		
		start.setFont(new Font("SansSerif", Font.BOLD, 15));
		startButton.add(start);
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				test.runNewGame();
				// diposes of this jframe
			}
			
		});
		
		buttonSpace.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 125));
		buttonSpace.add(startButton);
		buttonSpace.setBackground(Color.gray);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		Graphics2D g2D = (Graphics2D)g;
		g2D.drawImage(Planet.purplePlanet.getScaledInstance(50, 50, Image.SCALE_SMOOTH), this.getWidth()/6, this.getHeight()/5, null);
		g2D.drawImage(Planet.bluePlanet.getScaledInstance(35, 35, Image.SCALE_SMOOTH), this.getWidth()*3/4, this.getHeight()/2, null);
		g2D.drawImage(Planet.brownBluePlanet.getScaledInstance(60, 60, Image.SCALE_SMOOTH), this.getWidth()/4, this.getHeight()*3/4, null);
	}
}








