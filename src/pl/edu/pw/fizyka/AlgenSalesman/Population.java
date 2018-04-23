package pl.edu.pw.fizyka.AlgenSalesman;

import java.util.*;

public class Population {

	private Random generator;
	public ArrayList<Agent> population = new ArrayList<Agent>();
	ArrayList<Integer> tmp2 = new ArrayList<>();
	double totalSum;
	double totalSumOfProb;
	double longest;
	private double totalMutation = 0.90;
	private double swapMutation = 0.75;
	private double twixMutation = 1;
	private int mode;
	private int t; //taki tam temp
	int quantity;
	int num;
	
	public Population(int gQuantity, int gNum,int grandMode) {
		mode = grandMode;
		quantity = gQuantity;
		num=gNum;
		for (int i = 0; i < quantity; ++i)
			population.add(new Agent(num));
		for(int ii=0;ii<num;ii++) {tmp2.add(1);}
		generator = new Random();
	}

	public void evaluate2() {
		totalSum = 0;
		for (Agent a : population)
			totalSum += a.evaluate();
		population.sort(new CustomComparator());
		longest=population.get(population.size()-1).score;
		totalSum= population.size()*longest - totalSum;
		
		totalSumOfProb = 0;		
		for ( int ii = population.size()-1; ii >= 0; ii--)
		{
			population.get(ii).matingProb=(longest -population.get(ii).score )/totalSum;
			population.get(ii).probFloor=totalSumOfProb;
			totalSumOfProb+=population.get(ii).matingProb;
		}
		population.sort(new CustomComparator());
	}
	
	public void evaluate() {
		totalSum = 0;
		for (Agent a : population)
			totalSum += a.evaluate();
		for (Agent a : population)
			a.matingProb = 1 - a.score / totalSum * population.size();
		population.sort(new CustomComparator());
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
			if(mode==1)population.set(i, population.get(j).crossover(population.get(k)));
			if(mode==2)population.set(i, population.get(j).crossover2(population.get(k)));
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
	
	
	public void reproduction2(){

	
		
		//couple reproduction
		ArrayList<Agent> tmp = new ArrayList<>();
		for (int ii = 0; ii < quantity; ++ii)
			tmp.add(population.get(ii));
		double rand,rand2;
		for(int i=0;i<population.size();i++){
			rand=generator.nextDouble();
			rand2=generator.nextDouble();
			int j=0;
			int k=0;
			while(tmp.get(j).probFloor>=rand  && !(j == quantity-1)) {j++;}
			while(tmp.get(k).probFloor>=rand2 && !(k == quantity-1)) {k++;}
			if(mode==1)population.set(i, tmp.get(j).crossover(tmp.get(k)));
			if(mode==2)population.set(i, tmp.get(j).crossover2(tmp.get(k)));
		}
		

		/*
		
		for(Agent a: population)
			if(generator.nextDouble()>swapMutation){
				int r1=generator.nextInt(a.dna.size()-1);
				int r2=generator.nextInt(a.dna.size()-1);
				while(r1==r2)r2=generator.nextInt(a.dna.size()-1);
				int n=a.dna.get(r1);
				a.dna.set(r1, a.dna.get(r2));
				a.dna.set(r2,n);
			}
	*/
			//twix
		for(Agent a: population)
			if(generator.nextDouble()>twixMutation){
				for (int ii: a.dna)
				{
					tmp2.set(ii,a.dna.get(ii));
				}
				int r1=generator.nextInt(a.dna.size()-1);
				int r2=generator.nextInt(a.dna.size()-1);
				if(r1>r2) {t= r1; r1 = r2; r2=t;}				
				for(int ii = 0; ii<=(r2-r1);ii++)
				{
					a.dna.set(r1+ii,tmp2.get(r2-ii));	
				}

			}
	/*	population.sort(new CustomComparator());
		ArrayList<Integer> bestDna=new ArrayList<Integer>();
		for(int i=0;i<population.get(0).dna.size();++i)bestDna.add(population.get(0).dna.get(i));
			
		
		population.get(population.size()-1).dna=bestDna;*/
		
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

	public void print() {
		for (Agent a : population)
			System.out.println(a.dna + "  " + a.score + "   " + a.matingProb);
		System.out.println("");
	}
	public void print(int nr) {
		Agent a = population.get(nr);
		System.out.println(a.dna + "   " + a.score + "   " + a.matingProb);
	}

	public void printBest() {
		Agent a = population.get(0);
		System.out.println(a.dna + "   " + a.score + "   " + a.matingProb);

	}
	public Agent returnBest(){
		return population.get(0);
	}
}
