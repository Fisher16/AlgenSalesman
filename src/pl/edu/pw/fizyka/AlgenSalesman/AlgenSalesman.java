package pl.edu.pw.fizyka.AlgenSalesman;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * Main class, container for rest. 
 * 
 * @author MK
 *
 */
public class AlgenSalesman extends JFrame {

	private static final long serialVersionUID = 8438792764310234144L;
	
	public AlgenSalesman(){
		setTitle("Salesman problem - genetic algorithms");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700,500));
		JTabbedPane tPane = new JTabbedPane();
		
        this.add(tPane);
        
		
	}

	public static void main(String[] args) {
		AlgenSalesman window = new AlgenSalesman();
		window.setVisible(true);

	}

}
