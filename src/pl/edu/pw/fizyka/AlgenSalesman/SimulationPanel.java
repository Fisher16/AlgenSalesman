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



public class SimulationPanel extends JPanel {

	private static final long serialVersionUID = 626413797225770786L;
	public int num;
	public int crossMode;
	public int repMode;
	public int indexDrawn=0;
	
	public NodeList nList;
	public int numOfPop;
	public int popSize;
	
	
	Population pop;
	ArrayList<Agent> timeline=new ArrayList<Agent>();;
	int div=10;
	
	public SimulationPanel(){
		this.setBackground(Color.darkGray);
		this.setMinimumSize(new Dimension(200,200));
		nList=new NodeList(500,400,num);
		numOfPop = 1;
		crossMode=2;
		repMode=2;
		num=10;
		popSize=1;
		pop=new Population(popSize, num,crossMode);
        pop.fillDNA(nList.list);
        pop.evaluate2();
        //do wrzucenia w timer
        for(int i=0;i<numOfPop;++i){

	       // pop.print();
			pop.reproduction2();
	        pop.fillDNA(nList.list);
	        pop.evaluate2();
        }
        
       
	}
	
	public SimulationPanel(NodeList grandList, int grandNum, int grandCrossMode,int grandRepMode, int grandNumOfPop, int grandPopSize){
		this.setBackground(Color.darkGray);
		this.setMinimumSize(new Dimension(200,200));
		crossMode=grandCrossMode;
		repMode = grandRepMode;
		num=grandNum;
		nList=grandList;
		popSize=grandPopSize;
		numOfPop = grandNumOfPop;
		int iter=3;
		double[] sum=new double[numOfPop/iter];		
		for(int x=0;x<iter;x++){
			System.out.println(x);
	try {
		PrintWriter writer = new PrintWriter(x+"_"+num+"_"+nList.sparse+"_"+repMode+"_1kdane17.txt", "UTF-8");
		pop=new Population(1000, num,crossMode);
        pop.fillDNA(nList.list);
        if(repMode==1) {pop.evaluate();}
	    else if(repMode==2) {pop.evaluate2();}
        
        int j=0;
        for(int i=0;i<numOfPop;++i){
        	
        
	       if(repMode==1) {pop.reproduction();}
	       else if(repMode==2) {pop.reproduction2();}
	       
	        pop.fillDNA(nList.list);
	        if(repMode==1) {pop.evaluate();}
		    else if(repMode==2) {pop.evaluate2();}
	        
	        if(i%div==0){
	        	sum[i/iter]+=pop.population.get(0).score;
	        	writer.println(i+","+pop.population.get(0).score);
        		timeline.add(j, pop.population.get(0));
        		j++;
        	}
        }
        timeline.add(j,pop.population.get(0));
//        for(Agent a: timeline)a.printScore();
        
        writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		try {
			PrintWriter writer2 = new PrintWriter(num+"_"+nList.sparse+"_"+repMode+"_1kavg17.txt", "UTF-8");
			for(int i=0;i<numOfPop/iter;++i)writer2.println(i+","+sum[i]/iter);
			
			writer2.close();
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
		this.drawPath(g, timeline.get(indexDrawn).dnaNode);
		 
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
		for(int i=1;i<list.size();++i)
			g.drawLine(list.get(i-1).x,list.get(i-1).y,list.get(i).x,list.get(i).y);
		g.drawLine(list.get(0).x,list.get(0).y,list.get(list.size()-1).x,list.get(list.size()-1).y);
	}
	


}
