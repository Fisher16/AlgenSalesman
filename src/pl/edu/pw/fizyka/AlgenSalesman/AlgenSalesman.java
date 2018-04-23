package pl.edu.pw.fizyka.AlgenSalesman;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Main class, container for rest. 
 * 
 * @author MK
 *
 */
public class AlgenSalesman extends JFrame {

	
	private static final long serialVersionUID = 8438792764310234144L;
	public static int num = 5; // cities
	public static int popSize = 1000;
	public static int numOfGenerations = 1000;
	public static NodeList nList=new NodeList(700,500,num);
		static SimulationPanel sPanel=new SimulationPanel(nList,num,1,1,numOfGenerations, popSize); // 1 i 2 to odpowiednio tryb Koszi i Szlupi
		static SimulationPanel sPanel2=new SimulationPanel(nList,num,2,2,numOfGenerations, popSize);
		static greedySimulationPanel greedyPanel=new greedySimulationPanel(nList,num);
	public SliderTexted sTime;
	
	public AlgenSalesman(){
		setTitle("Salesman problem - genetic algorithms");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700,500));
		JTabbedPane tPane = new JTabbedPane();
		tPane.addTab("Koszi",sPanel);
		tPane.addTab("Szlupi",sPanel2);
		tPane.addTab("Greedy",greedyPanel);
        sTime=new SliderTexted(0,numOfGenerations/10,"Time");
        this.setLayout(new BorderLayout());
        this.add(sTime,BorderLayout.SOUTH);
        sTime.slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
            	sPanel.indexDrawn=sTime.slider.getValue();
            	sPanel.repaint();
            	sPanel2.indexDrawn=sTime.slider.getValue();
            	sPanel2.repaint();
            }
        });
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
		System.out.println("best greed - " ); greedyPanel.pop.printBest();

        this.add(tPane,BorderLayout.CENTER);

	}
	public void testXY(){
		for(Node n: sPanel.nList.list)System.out.println(n.n+"  "+n.x+"  "+n.y);
	}
	public void testDist(){
		System.out.println(sPanel.nList.list.size());
		System.out.println(sPanel.nList.listDist());
	}

	public static void main(String[] args) {
		
		for(int i=10;i<=100;i+=10){
			num=i;
			nList=new NodeList(700,500,num);
			sPanel=new SimulationPanel(nList,num,1,1,numOfGenerations, popSize);
			sPanel2=new SimulationPanel(nList,num,2,2,numOfGenerations, popSize);
			greedyPanel=new greedySimulationPanel(nList,num);
			//AlgenSalesman window = new AlgenSalesman();
			//window.setVisible(true);

		}
		
//		window.testXY();
//		window.testDist();
		
		
	}
	
	
	Timer timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	sPanel.getToolkit().sync();
        	sPanel.repaint();
        	sPanel2.repaint();
        }

        
    });
	
}
