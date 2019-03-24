import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class Operator {
	
	private String name;
	private ImageIcon icon;
	private Color color;
	private JCheckBox check;
	
	public Operator(String name, int height) throws IOException{
		this.name = name;
		String file = "D:/Java/workspace/Random6/src/operators/" + name + ".txt";
		
		BufferedReader read = new BufferedReader(new FileReader(new File(file)));
		icon = new ImageIcon(ImageIO.read(new File("D:/Java/workspace/Random6/src/pictures/" + read.readLine())).getScaledInstance(-1, height-200, Image.SCALE_SMOOTH));
		color = new Color(Integer.parseInt(read.readLine()));
		check = new JCheckBox(name,true);
	}
	
	public String name(){
		return name;
	}
	
	public ImageIcon icon(){
		return icon;
	}
	
	public Color color(){
		return color;
	}
	
	public JCheckBox checkBox(){
		return check;
	}
	
	public boolean selected(){
		return check.isSelected();
	}
	
}
