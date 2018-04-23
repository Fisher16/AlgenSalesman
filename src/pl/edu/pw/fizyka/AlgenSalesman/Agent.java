package pl.edu.pw.fizyka.AlgenSalesman;

import java.util.*;


public class Agent {
	
	public ArrayList<Integer> dna= new ArrayList<Integer>();
	public ArrayList<Node> dnaNode=new ArrayList<Node>();
	public double score = 100000;
	public double matingProb;
	public double probFloor;
	Random random = new Random();
	
	public Agent(int num){
		while(dna.size()<num){
			int n=random.nextInt(num);
			while(!(valid(n,dna)))n=random.nextInt(num);  //tablica z możliwościami
			dna.add(n);
//			dnaNode.add(new Node(num));
		}
	}
	public Agent(int num,int i){
		while(dna.size()<num){
			int n=i;
			dna.add(n);
		}
	}
	public Agent(int num, String botak){			//ten gdy greedy
		for(int i = 0; i<num;i++)dna.add(i);

	}
	public Agent(){
		int num=5;
		while(dna.size()<num){
			int n=random.nextInt(num);
			while(!(valid(n,dna)))n=random.nextInt(num);
			dna.add(n);
		}
	}
	public Boolean valid(int n,ArrayList<Integer> list){
		Boolean test=true;
		for(int j=1;j<list.size()+1;++j)if(n==list.get(j-1))test=false;
		return test;
	}
	
	public Boolean valid(int n,ArrayList<Integer> list,int a, int b){
		Boolean test=true;
		for(int j=a;j<=b;++j)if(n==list.get(j))test=false;
		return test;
	}
	public int IndexN(int n,ArrayList<Integer> list){
		int index= 0;
		for(int j=0;j<list.size();++j)if(n==list.get(j))index=j;  //uwaga uwaga
		return index;
	}
	
	public void fillDNA(ArrayList<Node> list){
		dnaNode.clear();
		for(int i: dna)dnaNode.add(list.get(i));
	}
	
	public double evaluate(){
		double s=0;
		for(int i=1;i<dnaNode.size();++i)
			s+=dnaNode.get(i).dist(dnaNode.get(i-1)); //powinien wracać 
		s+=dnaNode.get(0).dist(dnaNode.get(dnaNode.size()-1));
		score=s;
		return s;
	}
	
	public void greedySet(int start){
		double dist;
		double shortest = 10000;
		int first;
		int numberOfShortestNode = -1; 
		ArrayList<Integer> usedDna= new ArrayList<Integer>();
		score = 0;
		usedDna.add(start);
		for(int i=0;i<dnaNode.size()-1;++i)
		{
			first = 1;
			for(int a = 0; a<usedDna.size();a++)System.out.print(usedDna.get(a)+" ");
			for(int ii=0;ii<dnaNode.size();ii++)
			{
				
				//System.out.println("num2 "+ ii);
				
				if(valid(ii,usedDna)) 
				{
					//System.out.println("w if");
					dist=dnaNode.get(usedDna.get(i)).dist(dnaNode.get(ii));
					
					if(first==1) {
						first = 0;
						shortest=dist;
						numberOfShortestNode=ii;
					}
					else if(dist<shortest) {
						first = 0;
						shortest=dist;
						numberOfShortestNode=ii;
						//System.out.println("w 2if");
						}
					}
				
			}			
			usedDna.add(numberOfShortestNode);
			score += shortest;
		}
		score += dnaNode.get(usedDna.get(usedDna.size()-1)).dist(dnaNode.get(usedDna.get(0)));
		for(int iii=0;iii <dna.size();iii++) {dna.set(iii,usedDna.get(iii));}
		

	}
	
	public Agent crossover(Agent sec){
		Agent child=new Agent(dna.size(),-1);
		int n= random.nextInt(dna.size()-3); //losowanie początku i końca 
		int i;
		for(i=0;i<n;++i)child.dna.set(i, dna.get(i));
		int j=0;
		while(i<dna.size()){
			while(!(child.valid(sec.dna.get(j), child.dna))){
				++j;
				j%=dna.size();
			}
			child.dna.set(i,sec.dna.get(j));
			++i;
			
		}
			
		return child;
	}
	
	public Agent crossover2(Agent sec){
		Agent child=new Agent(dna.size(),-1);
		int rand1= random.nextInt(dna.size()-1); //mój
		int rand2= random.nextInt(dna.size()-1);  //gen pozostanie z tego ^^
		int a,b;
		if(rand1<rand2) {a = rand1; b = rand2;}
		else {b = rand1; a =rand2;}
		for(int ii=0; ii<dna.size(); ii++ )
		{
			if(a<=ii && ii<=b )
			{
				//System.out.println("1  to "+dna.get(ii)+"do tego "+ ii);
				child.dna.set(ii, dna.get(ii));
				//if(test==100) {test=ii; test2=dna.get(ii); }
				if(valid(sec.dna.get(ii),dna,a,b))
				{
					int index = ii;
					Boolean search = true;
					while(search)
					{
						for(int jj=0; jj<dna.size();jj++)
						{
							if(dna.get(index)==sec.dna.get(jj) && ( a<=jj && jj<=b ) ) 
							{
								//System.out.println("znianiam index z"+index+"na "+ jj);
								index=jj;
								}
							else if(dna.get(index)==sec.dna.get(jj) && !( a<=jj && jj<=b )) 
							{	
								//System.out.println("2  to "+sec.dna.get(ii)+"do tego "+ index);
								child.dna.set(jj,sec.dna.get(ii));
								search = false ;
								}
							else {}
							
						}
					}
				}
			}
			else if(valid(sec.dna.get(ii),dna,a,b)) {
				//System.out.println("3  to "+sec.dna.get(ii)+" do tego "+ ii);
				child.dna.set(ii, sec.dna.get(ii));
				}
			
		}
	/*	System.out.println(test2 + " to do  tego" + test  );
		System.out.println(a + "-a " + b + "-b " );
		for(int aa=0;aa<dna.size();aa++) {System.out.print(dna.get(aa)+" ");}
		System.out.println(" ");
		for(int aa=0;aa<dna.size();aa++) {System.out.print(sec.dna.get(aa)+" ");}     syff po debugowaniu
		System.out.println(" ");
		for(int aa=0;aa<dna.size();aa++) {System.out.print(child.dna.get(aa)+" ");}
		Boolean search = true;
		while(search) {}*/ 
		return child;
	}
	
	
	
	
	
	
	
	
	public void printDNA(){
		System.out.println("dna" + dna);
		for(Node n: dnaNode)System.out.println("NodeDNA " + n.x+"  "+n.y);
		
	}
	public void printScore(){
		System.out.print("dna" + dna);
		System.out.println(" score: " + score);
		
	}
	
	public void newDNA(){
		for(int i=0;i<dna.size();++i)dna.set(i,-1);
		for(int i=0;i<dna.size();++i){
			int n=random.nextInt(dna.size());
			while(!(valid(n,dna)))n=random.nextInt(dna.size());
			dna.set(i,n);
		}
	}
	
	
	
	
}
