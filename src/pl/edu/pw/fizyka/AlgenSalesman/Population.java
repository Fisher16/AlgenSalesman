package pl.edu.pw.fizyka.AlgenSalesman;

import java.util.*;

public class Population {
	public ArrayList<Agent> population=new ArrayList<Agent>();
	double totalSum;
	private double totalMutation=0.85;
	private double swapMutation=0.8;
	
	public Population(int quantity,int num){
		for(int i=0;i<quantity;++i)population.add(new Agent(num));
	}
	
	public void evaluate(){
		totalSum=0;
		for(Agent a: population)totalSum+=a.evaluate();
		population.sort(new CustomComparator());
		for(Agent a: population)a.matingProb=1-a.score/totalSum*population.size();
	}
	
	//proliferation
	public void reproduction(){
		//copy best one
		ArrayList<Integer> bestDna=new ArrayList<Integer>();
		for(int i=0;i<population.get(0).dna.size();++i)bestDna.add(population.get(0).dna.get(i));
		
	//	System.out.println(bestDna);
		population.get(population.size()-1).dna=bestDna;
	
		
		//couple reproduction
		double rand1,rand2;
		for(int i=population.size()-2;i>population.size()/2;--i){
			//>1 to better preserve the best one
			rand1=1.618*population.get(0).matingProb*(new Random()).nextDouble();
			rand2=population.get(0).matingProb*(new Random()).nextDouble();
			int j=0,k=0;
			while(population.get(j).matingProb>rand1)j++;
			while(population.get(k).matingProb>rand2)k++;
			population.set(i, population.get(j).crossover(population.get(k)));
		}
		
		//fresh blood
		for(Agent a: population)
				if((new Random()).nextDouble()>totalMutation)a.newDNA();
		//
		for(Agent a: population)
			if((new Random()).nextDouble()>swapMutation){
				int r1=(new Random()).nextInt(a.dna.size()-1);
				int r2=(new Random()).nextInt(a.dna.size()-1);
				while(r1==r2)r2=(new Random()).nextInt(a.dna.size()-1);
				int n=a.dna.get(r1);
				a.dna.set(r1, a.dna.get(r2));
				a.dna.set(r2,n);
			}
		
		
	}
	
	public class CustomComparator implements Comparator<Agent> {
	    @Override
	    public int compare(Agent a1,Agent a2) {
	        int i=0; 
	        i=a1.score>a2.score?1:i;
	        i=a1.score<a2.score?-1:i;
	        return i;
	    }
	}
	
	public void fillDNA(ArrayList<Node> list){
		for(Agent a: population)a.fillDNA(list);
	}
	public void print(){
		for(Agent a:population)System.out.println(a.dna+"  "+a.score+"   "+a.matingProb);
//		for(Agent a:population)a.printDNA();
		System.out.println("");
	}
	public void printBest(){
		Agent a=population.get(0);
		System.out.println(a.dna+"   "+a.score+"   "+a.matingProb);
	}
}
