import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
/* What were these imports for? It returned errors for me
import baseGame.GameSpace;
import baseGame.Main;

/*
 * This class will store a planets designated image along with information such as the size, speed, and x/y location to be drawn at.
 */
public class Planet {

  // I added in public for abstraction and static to save memory since not every planet object needs to create a reference variable to every image.
	public static Image purplePlanet = new ImageIcon("C:\\\\Users\\\\Oscar Rojas\\\\Desktop\\\\Programming\\\\Java\\\\Eclipse\\\\Workspace\\\\Planet Placer 2-1\\\\Planet Placer 2\\\\Planet Placer 2\\\\darkPurplePlanet.png").getImage();
	public static Image brownBluePlanet = new ImageIcon("C:\\\\Users\\\\Oscar Rojas\\\\Desktop\\\\Programming\\\\Java\\\\Eclipse\\\\Workspace\\\\Planet Placer 2-1\\\\Planet Placer 2\\\\Planet Placer 2\\\\brownBluePlanet.png").getImage();
	public static Image bluePlanet = new ImageIcon("C:\\\\Users\\\\Oscar Rojas\\\\Desktop\\\\Programming\\\\Java\\\\Eclipse\\\\Workspace\\\\Planet Placer 2-1\\\\Planet Placer 2\\\\Planet Placer 2\\\\bluePlanet.png").getImage();
	private static List<Image> planetOptions = new ArrayList<Image>();
	
	private Image planet;
	private int size;
	private double initialXSpeed;
	private double finalXSpeed;
	private double initialYSpeed;
	private double finalYSpeed;
	
	private Point position;
	double xRadius;
	double yRadius;
	private double radius;
	private double xForceOfGravity;
	private double yForceOfGravity;
	private double radians;
	static boolean arraySet = false;

	public Planet(int planet, int size, double speed, int xPosition, int yPosition) {
		if(!arraySet){
			setPlanetOptions();
			arraySet=true;
		}
		
		this.size = size;
		this.planet = planetOptions.get(planet).getScaledInstance(this.size, this.size, Image.SCALE_SMOOTH);
		this.initialYSpeed = speed*.65;
		this.position = new Point(xPosition, yPosition);
	}
	
	private void setPlanetOptions() {
		planetOptions.add(purplePlanet);
		planetOptions.add(brownBluePlanet);
		planetOptions.add(bluePlanet);
	}
	
	public void updatePosition() {
		updateSpeed();
		// Delta s = 1/2 * (Vi + Vf) * t

		double deltaX = (initialXSpeed + finalXSpeed) * .1 * .5;
		double deltaY = (initialYSpeed + finalYSpeed) * .1 * .5;

		position.setLocation(position.x + deltaX, position.y + deltaY);

		initialXSpeed = finalXSpeed;
		initialYSpeed = finalYSpeed;
	}
	
	private void updateSpeed() {
		updateGravity();
	  // DeltaV = a * t; 
		finalXSpeed = initialXSpeed - (xForceOfGravity * .5); 
		finalYSpeed = initialYSpeed - (yForceOfGravity * .5);
	}
	
	private void updateGravity() {
	  // The radi's true value is increased to account for a seemingly greater distance though our screen size is not allowing for such.
		xRadius = Math.abs(GameSpace.center.x - position.x)*3; // The x distance from the sun
		yRadius = Math.abs(GameSpace.center.y - position.y)*3; // The y distance from the sun
		radius = Math.sqrt(Math.pow(xRadius, 2) + Math.pow(yRadius, 2)); // The distance from the sun
		radians = Math.asin(yRadius/radius); // The angle from the sun's horizontal axis, since gravity acts in the same direction as the radius this can be reused.
		double forceOfGravity = (GameSpace.sunMass * test.G)/Math.pow(radius, 2); // GM/r^2 = g; Fg = mg; M = sun's mass, m = planet's mass; Fnet = m * a;
		if(GameSpace.center.x > position.x) {
			xForceOfGravity = - Math.cos(radians) * forceOfGravity; // Fg-x = m * g-x; m * a-x = m * g-x; a-x = g-x;
		} else {
			xForceOfGravity = Math.cos(radians) * forceOfGravity;
		}
		if(GameSpace.center.y > position.y) {
			yForceOfGravity = -Math.sin(radians) * forceOfGravity; // Fg-y = m * g-y; m * a-y = m * g-x; a-y = g-y;
		} else {
			yForceOfGravity = Math.sin(radians) * forceOfGravity;
		}
	}

	public Image getImage(){
		return planet;
	}

	public int getX() {
		return position.x;
	}

	public int getY() {
		return position.y;
	}

	public int getSize(){
		return size;
	}
	
	public boolean isInside(Planet p) {
		Rectangle r1 = new Rectangle(position.x, position.y, size, size);
		Rectangle r2 = new Rectangle(p.getX(), p.getY(), p.getSize(), p.getSize());
		if(r1.intersects(r2)) {
			return true;
		} else {
			return false;
		}
	}
}
