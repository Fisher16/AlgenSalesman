package pl.edu.pw.fizyka.AlgenSalesman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class SimulationPanel extends JPanel {

	private static final long serialVersionUID = 626413797225770786L;
	public int num=50;
	
	public NodeList nList=new NodeList(500,400,num);
	
	public SimulationPanel(){
		this.setBackground(Color.darkGray);
		this.setMinimumSize(new Dimension(200,200));
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
        g.setColor(Color.RED);
		this.drawPath(g, nList.list);
		
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
	}
	
	protected void drawPath(Graphics g,ArrayList<Node> list){
		for(int i=1;i<list.size();++i)
			g.drawLine(list.get(i-1).x,list.get(i-1).y,list.get(i).x,list.get(i).y);
	}

}
