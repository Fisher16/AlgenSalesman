package pl.edu.pw.fizyka.AlgenSalesman;

import java.util.ArrayList;


public class NodeList {
	public ArrayList<Node> list = new ArrayList<Node>();
	
	public NodeList(int maxX, int maxY, int number){
		for(int i=0;i<number;++i){
			Node n=new Node(maxX,maxY,i);
			list.add(n);
		}
	}
	public double listDist(){
		double s=0;
		for(int i=1;i<list.size();++i){
			s+=list.get(i).dist(list.get(i-1));
//			System.out.println(s+"  "+i);
		}
		return s;
	}
}
