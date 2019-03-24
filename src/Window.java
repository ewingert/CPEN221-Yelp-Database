import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Window {
	
	private int height = 1300;
	private int width = 1200;
	private JFrame frame;
	private JPanel select = new JPanel();
	private JPanel atkCheckPanel = new JPanel();
	private JPanel defCheckPanel = new JPanel();
	private JPanel panel = new JPanel();	
	private JPanel opPanel;
	private JPanel buttonPanel;
	private JLabel pic;
	private ImageIcon icon;
	private String lastOp = "";
	ArrayList<Operator> opsAtk = new ArrayList<Operator>();
	ArrayList<Operator> opsDef = new ArrayList<Operator>();
	ArrayList<JCheckBox> atkChecks = new ArrayList<JCheckBox>();
	ArrayList<JCheckBox> defChecks = new ArrayList<JCheckBox>();
	private final String[] ATTACKERS = {"Ash","Blackbeard","Blitz","Buck","Capitao","Dokkaebi","Finka","Fuze","Glaz","Hibana","IQ","Jackal","Lion","Montagne","Sledge","Thatcher","Thermite","Twitch","Ying","Zofia"};
	private final String[] DEFENDERS = {"Alibi","Bandit","Castle","Caveira","Doc","Echo","Ela","Frost","Jaeger","Kapkan","Lesion","Maestro","Mira","Mute","Pulse","Rook","Smoke","Tachanka","Valkyrie","Vigil"};
	
	public Window() throws IOException{
		fillOpLists();
		String op = "Blitz";
		AbstractAction atkButton = new AbstractAction()
	    {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					runAtk();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
	    };
	    AbstractAction defButton = new AbstractAction()
	    {
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					runDef();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
	    };
	
		frame = new JFrame("Random6");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    opPanel = new JPanel();
	    panel.setPreferredSize(new Dimension(width,height));
	    panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));

	    BufferedImage image = ImageIO.read(new File("D:/Java/workspace/Random6/src/pictures/download.jpg"));
	    icon = new ImageIcon(image.getScaledInstance(-1, height-200, image.SCALE_SMOOTH));
	    
	    pic = new JLabel(icon);
	    
	    opPanel.add(pic);
	    panel.add(opPanel);
	    
	    JButton buttonATK = new JButton("Randomize Attackers");
	    buttonATK.setPreferredSize(new Dimension(175,75));
	    buttonATK.addActionListener(atkButton);
	    
	    buttonATK.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
	    	put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_9,0), "Attacker Button");
	    buttonATK.getActionMap().put("Attacker Button", atkButton);
	    
	    JButton buttonDEF = new JButton("Randomize Defenders");
	    buttonDEF.setPreferredSize(new Dimension(175,75));
	    buttonDEF.addActionListener(defButton);
	    
	    buttonDEF.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
    		put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_0,0), "Defender Button");
	    buttonDEF.getActionMap().put("Defender Button", defButton);
	    
	    	    
	    buttonPanel = new JPanel();
	    buttonPanel.add(buttonATK);
	    buttonPanel.add(buttonDEF);
	    panel.add(buttonPanel);
	    
	    select.setLayout(new GridLayout(3,1));
	    
	    atkCheckPanel.setLayout(new GridLayout(ATTACKERS.length/3+1,3));
	    for(int i = 0; i < opsAtk.size(); i++){
	    	atkChecks.add(opsAtk.get(i).checkBox());
	    }
	    for(int i = 0; i < atkChecks.size(); i++){
	    	atkCheckPanel.add(atkChecks.get(i));
	    }

	    
	    defCheckPanel.setLayout(new GridLayout(DEFENDERS.length/3+1,3));
	    for(int i = 0; i < opsDef.size(); i++){
	    	defChecks.add(opsDef.get(i).checkBox());
	    }
	    for(int i = 0; i < defChecks.size(); i++){
	    	defCheckPanel.add(defChecks.get(i));
	    }
	    
	    select.add(atkCheckPanel);
	    select.add(new JTextField(""));
	    select.add(defCheckPanel);
	    
	    frame.getContentPane().add(panel, BorderLayout.WEST);
	    frame.getContentPane().add(select, BorderLayout.EAST);
	    this.setBackground(0000);

	    frame.pack();
	    frame.setVisible(true);
	}
	
	private void fillOpLists() throws IOException{
		for(int i = 0; i < ATTACKERS.length; i++){
			opsAtk.add(new Operator(ATTACKERS[i],height));
		}
		for(int i = 0; i < DEFENDERS.length; i++){
			opsDef.add(new Operator(DEFENDERS[i],height));
		}
	}
	
	public void runAtk() throws IOException{
		String op = "";
		Operator picked;
		int safety = 0;
		do {
			Random r = new Random();
			int index = (int)(r.nextDouble()*opsAtk.size());
			picked = opsAtk.get(index);
			op = picked.name();
			safety++;
		} while((op.equals(lastOp) && safety < 50) || !picked.selected());
		
		//BufferedImage image = ImageIO.read(new File("C:/Users/Evan/Pictures/R6/" + op + ".png"));
		icon = picked.icon();
		lastOp=op;
	    
	    pic.setIcon(icon);
	    this.setBackground(picked.color());

	}
	
	public void runDef() throws IOException{
		String op = "";
		Operator picked;
		int safety = 0;
		do {
			Random r = new Random();
			int index = (int)(r.nextDouble()*opsDef.size());
			picked = opsDef.get(index);
			op = picked.name();
			safety++;
		} while((op.equals(lastOp) && safety < 50) || !picked.selected());
		
		//BufferedImage image = ImageIO.read(new File("C:/Users/Evan/Pictures/R6/" + op + ".png"));
		icon = picked.icon();
	    lastOp=op;
		
	    pic.setIcon(icon);
	    this.setBackground(picked.color());

	}
	
	public void setBackground(int color){
		opPanel.setBackground(new Color(color));
		buttonPanel.setBackground(new Color(color));

	}
	
	public void setBackground(Color color){
		opPanel.setBackground(color);
		buttonPanel.setBackground(color);

	}

}
