package pl.edu.pw.fizyka.AlgenSalesman;

import java.util.ArrayList;


public class NodeList {
	public ArrayList<Node> list = new ArrayList<Node>();
	double sparse =10;
	
	public NodeList(int maxX, int maxY, int number){
		Node nn=new Node(maxX,maxY,0);
		list.add(nn);
		for(int i=1;i<number;++i){
			Node n=new Node(maxX,maxY,i);
			while(this.tooClose(n))n=new Node(maxX,maxY,i);  //po co to?? //Nie b�d� za blisko kropki
			list.add(n);
		}
		System.out.println("Generated");
		
	}
	
	public Boolean tooClose(Node nTested){
		Boolean test=false;
		for(int j=1;j<list.size();++j)if(nTested.dist(list.get(j-1))<sparse)test=true;
		return test;
	}
	
	public double listDist(){
		double s=0;
		for(int i=1;i<list.size();++i)
			s+=list.get(i).dist(list.get(i-1));  //powinien wraca na poczatek (chyba) //jest git na palcach policzy�em odleg�o�ci 
		return s;
	}
	
	public void print(){
		for(Node n: list)System.out.println(n.x+"   "+n.y);
		}
}
