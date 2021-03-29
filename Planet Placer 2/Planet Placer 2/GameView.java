
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class GameView extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel sideBar = new JPanel();
	public static GameSpace gameSpace = new GameSpace();

	private Border sideBarBorder = BorderFactory.createLoweredBevelBorder();

	SideBarSubPanel sizePanel = new SideBarSubPanel("Adjust the size of the planet:", "Size in megameters: ", 20, 35);
	SideBarSubPanel distancePanel = new SideBarSubPanel("Adjust the planet's distance:", "Distance in megameters: ", 100, 300);
	SideBarSubPanel launchSpeedPanel = new SideBarSubPanel("Adjust the planets orbital speed:", "Speed in Km/s: ", 25, 125); 

	private JPanel optionsPanel = new JPanel();
	private ButtonGroup planetOptionsGroup = new ButtonGroup();
	private JRadioButton planetOption1 = new JRadioButton("Purple Planet");
	private JRadioButton planetOption2 = new JRadioButton("Brown Planet");
	private JRadioButton planetOption3 = new JRadioButton("Blue Planet");
	
	private JPanel placePanel = new JPanel();
	public JButton placeBtn = new JButton();

	public GameView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1075, 900);
		this.getContentPane().setBackground(new Color(40, 36, 50));
		this.setResizable(false);
		this.setLayout(null);

		createSideBar();

		gameSpace.setBounds(sideBar.getWidth(), 0, this.getWidth() - sideBar.getWidth(), this.getHeight());

		this.add(sideBar, BorderLayout.WEST);
		this.add(gameSpace, BorderLayout.LINE_END);
		this.setVisible(true);
	}
	
	public void createSideBar() {
		int spacing = 20;
		
		planetOption1.setBackground(Color.gray);
		planetOption1.setFont(new Font("SansSerif", Font.PLAIN, 17));
		planetOption1.setForeground(new Color(157, 9, 173));
		
		planetOption2.setBackground(Color.gray);
		planetOption2.setFont(new Font("SansSerif", Font.PLAIN, 17));
		planetOption2.setForeground(new Color(157, 9, 173));
		
		planetOption3.setBackground(Color.gray);
		planetOption3.setFont(new Font("SansSerif", Font.PLAIN, 17));
		planetOption3.setForeground(new Color(157, 9, 173));
		
		planetOptionsGroup.add(planetOption1);
		planetOptionsGroup.add(planetOption2);
		planetOptionsGroup.add(planetOption3);
		
		planetOption1.setSelected(true);
		
		optionsPanel.setBackground(Color.gray);
		optionsPanel.add(planetOption1);
		optionsPanel.add(planetOption2);
		optionsPanel.add(planetOption3);
		
		placeBtn.setFocusable(false);
		placeBtn.setText("Place");
		placePanel.add(placeBtn);
		placePanel.setBackground(Color.gray);
		placeBtn.addActionListener(this);
		
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		sideBar.setBorder(sideBarBorder);
		// Positioning & size of the sidebar
		sideBar.setBounds(0, 0, 175, 900);
		sideBar.setBackground(Color.gray);
		sideBar.setOpaque(true);

		sideBar.add(sizePanel);
		sideBar.add(Box.createRigidArea(new Dimension(sideBar.getWidth(), spacing)));
		sideBar.add(distancePanel);
		sideBar.add(Box.createRigidArea(new Dimension(sideBar.getWidth(), spacing)));
		sideBar.add(launchSpeedPanel);
		sideBar.add(Box.createRigidArea(new Dimension(sideBar.getWidth(), spacing)));
		sideBar.add(optionsPanel);
		sideBar.add(Box.createRigidArea(new Dimension(sideBar.getWidth(), spacing)));
		sideBar.add(placePanel);
		sideBar.add(Box.createRigidArea(new Dimension(sideBar.getWidth(), spacing)));
	}
	
	private void resetOptions() {
		sizePanel.resetValue();
		distancePanel.resetValue();
		launchSpeedPanel.resetValue();
		planetOption1.setSelected(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == placeBtn) {
			if(planetOption1.isSelected()) {
				gameSpace.addPlanet(0, (int) sizePanel.getValue(), launchSpeedPanel.getValue(), (int) (GameSpace.center.x + distancePanel.getValue()), GameSpace.center.y);
			} else if (planetOption2.isSelected()) {
				gameSpace.addPlanet(1, (int) sizePanel.getValue(), launchSpeedPanel.getValue(), (int) (GameSpace.center.x + distancePanel.getValue()), GameSpace.center.y);
			} else {
				gameSpace.addPlanet(2, (int) sizePanel.getValue(), launchSpeedPanel.getValue(), (int) (GameSpace.center.x + distancePanel.getValue()), GameSpace.center.y);
			}
			resetOptions();
		}
	}

}
