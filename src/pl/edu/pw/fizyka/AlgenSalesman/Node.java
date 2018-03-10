package pl.edu.pw.fizyka.AlgenSalesman;

import java.util.Random;

import javax.swing.JPanel;



public class Node {
	public int n;
	public int x,y;
	//graphic par
	public int r=5;
	
	public Node(JPanel Panel,int N) {
		x=(new Random()).nextInt(Panel.getWidth())-2*r;
		y=(new Random()).nextInt(Panel.getHeight())-2*r;
		n=N;
	}
	
	public Node(int maxX, int maxY, int N) {
		//position
		x=(new Random()).nextInt(maxX)+2*r;
		y=(new Random()).nextInt(maxY)+2*r;
		n=N;
	}
	
	public double dist(Node sec){
		return Math.sqrt((this.x-sec.x)*(this.x-sec.x)+(this.y-sec.y)*(this.y-sec.y));
	}
}
