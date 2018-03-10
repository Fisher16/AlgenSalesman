package pl.edu.pw.fizyka.AlgenSalesman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class SimulationPanel extends JPanel {

	private static final long serialVersionUID = 626413797225770786L;
	public int num=13;
	
	public NodeList nList=new NodeList(200,200,num);
	
	public SimulationPanel(){
		this.setBackground(Color.darkGray);
		this.setMinimumSize(new Dimension(200,200));
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		//node
        Graphics2D graph = (Graphics2D)g;
        graph.clearRect(0, 0, getWidth(), getHeight());            
        
        for(Node n: nList.list){
        //graph.setColor(new Color(ColorSpace.TYPE_HLS,1.58f,(float)(b.vx*b.vx/(b.vx*b.vx+b.vy*b.vy)),(float)(b.vy*b.vy/(b.vx*b.vx+b.vy*b.vy))));
        //graph.setColor(Color.getHSBColor((float)(2*3.1416*minV/maxV), 1f, (float)(b.speed/maxV)));
        graph.fillOval(n.x-n.r, n.y-n.r, 2*n.r,2*n.r);   
        }
	}
	

}
