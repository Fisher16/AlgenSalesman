package pl.edu.pw.fizyka.AlgenSalesman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JPanel;



public class greedySimulationPanel extends JPanel {

	private static final long serialVersionUID = 626413797225770786L;
	public int num;
	public int crossMode;
	public int repMode;
	public int indexDrawn=0;
	
	public NodeList nList;
	public int numOfPop;
	public int popSize;
	
	
	greedyPopulation pop;
	ArrayList<Agent> timeline=new ArrayList<Agent>();
	int div=100;
	
	public greedySimulationPanel(){
		this.setBackground(Color.darkGray);
		this.setMinimumSize(new Dimension(200,200));
		nList=new NodeList(500,400,num);
		num=30;
		pop=new greedyPopulation( num);

        pop.fillDNA(nList.list);
        pop.evaluate();
        
       
	}
	
	public greedySimulationPanel(NodeList grandList, int grandNum){
		this.setBackground(Color.darkGray);
		this.setMinimumSize(new Dimension(200,200));
		num=grandNum;
		nList=grandList;
		pop=new greedyPopulation(num);
        pop.fillDNA(nList.list);
        pop.evaluate();
        pop.fillDNA(nList.list); 
        //System.out.println(pop.returnBest().score);
        try {
			PrintWriter writer = new PrintWriter(pop.returnBest().score+"greed.txt", "UTF-8");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
  
        	
        }
    

	
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		//node
        Graphics2D graph = (Graphics2D)g;
        graph.clearRect(0, 0, getWidth(), getHeight());            
        
        //draw nodes
        for(Node n: nList.list)
        	graph.fillOval(n.x-n.r, n.y-n.r, 2*n.r,2*n.r);   
        //draw lines
      /*  g.setColor(Color.LIGHT_GRAY);
		this.drawPath(g, nList.list);*/
		g.setColor(Color.RED);
		this.drawPath(g, pop.returnBest().dnaNode);
		 
	}
	
	public void testDrawingTwo(Graphics g){

		g.setColor(Color.BLUE);
		Agent agent1=new Agent(num);
		agent1.fillDNA(nList.list);
		this.drawPath(g, agent1.dnaNode);
		System.out.println(agent1.evaluate());
		
		g.setColor(Color.GREEN);
		Agent agent2=new Agent(num);
		agent2.fillDNA(nList.list);
		this.drawPath(g, agent2.dnaNode);
		System.out.println(agent2.evaluate());
		agent1.printDNA();
		agent2.printDNA();
		Agent agent3=agent1.crossover(agent2);
		agent3.printDNA();
	}
	
	
	protected void drawPath(Graphics g,ArrayList<Node> list){
		g.fillOval(list.get(0).x-2*list.get(0).r, list.get(0).y-2*list.get(0).r, 4*list.get(0).r,4*list.get(0).r);  
		for(int i=1;i<list.size();++i)
			g.drawLine(list.get(i-1).x,list.get(i-1).y,list.get(i).x,list.get(i).y);
		g.drawLine(list.get(0).x,list.get(0).y,list.get(list.size()-1).x,list.get(list.size()-1).y);
	}
	


}
