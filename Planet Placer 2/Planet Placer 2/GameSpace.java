

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * This panel will contain everything to the right of the sidebar.
 * Timer should be implemented here to redraw the panel and all of the planets in planetList. 
 */
public class GameSpace extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Planet> planetList;
	public static final int sunMass = 2000; // Some arbitrary scalar to affect how much the direction of the planet's movement changes.
	private Image sun = new ImageIcon("C:\\Users\\Oscar Rojas\\Desktop\\Programming\\Java\\Eclipse\\Workspace\\Planet Placer 2-1\\Planet Placer 2\\Planet Placer 2\\sun.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
	public static final Point center = new Point(425,425);
	
	
	Timer timer;
	GameOver gameOver;

	public GameSpace() {
		planetList = new ArrayList<Planet>();

		// THIS UPDATES THE POSTION OF THE PLANETS IM CHANGING THE X & Y 
		// BY A CONSTANT OF 10, INSTEAD WE NEED THE EQUATION OF AN OVAL SO 
		// THAT IT AUTOMATICALLY DOES IT, TIME UPDATES EVERY 200 MILLISECONDS.

		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Planet p: planetList) {
					p.updatePosition();
					for (Planet check: planetList) {
						if(p.isInside(check) && !p.equals(check)) {
							repaint();
							endGame();
						}
					}
				}
				repaint();
			}
		});
		timer.start();

		this.setLayout(null);
		this.setBackground(new Color(76, 65, 102)); //40, 36, 50
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(sun, 375, 375, null);

		// DRAWS ALL THE PLANETS
		for (Planet p: planetList) {
			g2D.drawImage(p.getImage(), p.getX(), p.getY(), p.getSize(), p.getSize(), null);
		}
	}

	// This method will be called from the GameView class whenever the 'place'
	// button is pressed.
	public void addPlanet(int planet, int size, double speed, int xPosition, int yPosition) {
		planetList.add(new Planet(planet, size, speed, xPosition, yPosition));
	}

	public int centerWidth() {
		return center.x + (sun.getWidth(null) / 2); // offsets the center position by half the width to provide more space between sun & planet when placing
	}

	public int centerHeight() {
		return center.y + (sun.getHeight(null) / 2); // offsets the center position by half the height to provide more space between sun & planet when placing
	}

	public void endGame() {
		timer.stop();
		gameOver = new GameOver();
	}
}
