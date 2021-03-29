

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class SideBarLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	
	public SideBarLabel(String s, int font) {
		this.setText(s);
		this.setFont(new Font("SansSerif", Font.BOLD, font));
		this.setForeground(new Color(157, 9, 173));
		this.setAlignmentX(CENTER_ALIGNMENT);
	}
	
}
