package pl.edu.pw.fizyka.AlgenSalesman;

import java.awt.Dimension;

import javax.swing.*;


/**
 * Main class, container for rest. 
 * 
 * @author MK
 *
 */
public class AlgenSalesman extends JFrame {

	private static final long serialVersionUID = 8438792764310234144L;
	SimulationPanel sPanel=new SimulationPanel();
	
	
	public AlgenSalesman(){
		setTitle("Salesman problem - genetic algorithms");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700,500));
		JTabbedPane tPane = new JTabbedPane();
		sPanel.repaint();
		tPane.addTab("Sim",sPanel);
        this.add(tPane);

	}
	public void testXY(){
		for(Node n: sPanel.nList.list)System.out.println(n.n+"  "+n.x+"  "+n.y);
	}
	public void testDist(){
		System.out.println(sPanel.nList.list.size());
		System.out.println(sPanel.nList.listDist());
	}

	public static void main(String[] args) {
		AlgenSalesman window = new AlgenSalesman();
		window.setVisible(true);
//		window.testXY();
//		window.testDist();
		
		
	}
}
