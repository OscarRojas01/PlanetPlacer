

import java.awt.Font;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* 
 * Each SideBarSubPanel is created using the same styling, such as border, font, a prompt on what to do with that 
 * subpanel's slider placed above and a reading of what the slider's current value is.
 */
public class SideBarSubPanel extends JPanel implements ChangeListener {
	private static final long serialVersionUID = 1L;
	
	private Border subPanelBorder = BorderFactory.createEtchedBorder();
	
	private SideBarLabel Prompt; // Tells the user what this subpanel's purpose is
	private JSlider slider;
	private SideBarLabel Label; // Display's a reading of this subpanel's settings via slider
	
	private String prompt;
	private String label;
	private int ticks;
	private int min;
	private int max;
	
	private Hashtable<Integer, JLabel> sliderDecimals; // Hashtable will store new tick labels at their respective slider values (see setPrecison method)
	private boolean isPrecise = false;
	private int decimalPlaces;
	
	public SideBarSubPanel(String P, String L, int slideMin, int slideMax) {
	prompt = P;
	label = L;
	min = slideMin;
	max = slideMax;
	ticks = ticks();
	
	Prompt = new SideBarLabel(prompt, 10);
	
	int sliderSize = max-min;
	slider = new JSlider(min,max, min + (max-min)/2);
	slider.addChangeListener(this);
	slider.setPaintTicks(true);
	slider.setMajorTickSpacing(sliderSize/ticks);
	slider.setPaintLabels(true);
	slider.setFont(new Font("SansSerif", Font.PLAIN, 7));
	
	Label = new SideBarLabel(label + slider.getValue(), 10);
	
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.add(Prompt);
	this.add(slider);
	this.add(Label);
	this.setBorder(subPanelBorder);

	}
	
  // Constructor to create a SubPanel with a slide incrementing by decimals (Will decrease the slide max&min by a factor of the value of decimals. see setPrecision method) 
	public SideBarSubPanel(String P, String L, int slideMin, int slideMax, int decimals) {
	prompt = P;
	label = L;
	min = slideMin;
	max = slideMax;
	decimalPlaces = decimals;
	ticks = ticks();
	
	Prompt = new SideBarLabel(prompt, 10);
	
	int sliderSize = max-min;
	slider = new JSlider(min,max, min + (max-min)/2);
	slider.addChangeListener(this);
	slider.setPaintTicks(true);
	slider.setMajorTickSpacing(sliderSize/ticks);
	slider.setPaintLabels(true);
	slider.setFont(new Font("SansSerif", Font.PLAIN, 7));
	setPrecision(decimals);
	isPrecise = true;
	
	Label = new SideBarLabel(label + slider.getValue()/Math.pow(10, decimals), 10);
	
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.add(Prompt);
	this.add(slider);
	this.add(Label);
	this.setBorder(subPanelBorder);

	}
	
	
	private int ticks() {
		int range = max-min; 
		while(range>10) {
			range /= 10;
		}
		return (range<5)?5:range;
	}
	
	public void setPrecision(int I) {
		sliderDecimals = new Hashtable<Integer, JLabel>();
		double decimal;
		String decimalTick;
		for (int i=max; i>=min; i-=10) {
			decimal = i/Math.pow(10, I);
			decimalTick = Double.toString(decimal);
			
		  // Creates a new label for each tick and formats to match the rest of the sliders ticks
			JLabel label = new JLabel(decimalTick);
			label.setFont(new Font("SansSerif", Font.PLAIN, 7));
			sliderDecimals.put(i, label);
		}
		slider.setLabelTable(sliderDecimals);
		slider.setPaintLabels(true);
	}
	
	public double getValue() {
		return slider.getValue();
	}
	
	public void resetValue() {
		slider.setValue(min + (max-min)/2);
	}
	
	public void stateChanged(ChangeEvent e) {
		if(isPrecise) {
			Label.setText(label + slider.getValue()/Math.pow(10, decimalPlaces));
		}
		else {
			Label.setText(label + slider.getValue());
		}
	}
}