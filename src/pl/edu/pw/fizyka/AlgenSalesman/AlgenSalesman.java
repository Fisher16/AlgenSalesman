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
	public int num = 30;
	public int popSize = 500;
	public int numOfGenerations = 500;
	public NodeList nList=new NodeList(500,400,num);
	SimulationPanel sPanel=new SimulationPanel(nList,num,1,1,numOfGenerations, popSize); // 1 i 2 to odpowiednio tryb Koszi i Szlupi
	SimulationPanel sPanel2=new SimulationPanel(nList,num,2,2,numOfGenerations, popSize);
	
	
	public AlgenSalesman(){
		setTitle("Salesman problem - genetic algorithms");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700,500));
		JTabbedPane tPane = new JTabbedPane();
		sPanel.repaint();
		tPane.addTab("Koszi",sPanel);
		tPane.addTab("Szlupi",sPanel2);
		
		/*
		double suma = 0;
		for(int ii=0;ii<sPanel2.pop.population.size();ii++)
		{
			System.out.println(ii + " wsp-  " + sPanel.pop.population.get(ii).matingProb );
			suma+=sPanel.pop.population.get(ii).matingProb;
		}
		System.out.println(" suma-  " + suma);
		*/

		System.out.println("best koszi - " ); sPanel.pop.printBest();
		System.out.println("best szlupi - " ); sPanel2.pop.printBest();
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
