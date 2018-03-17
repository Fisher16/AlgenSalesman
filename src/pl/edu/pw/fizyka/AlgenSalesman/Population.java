package pl.edu.pw.fizyka.AlgenSalesman;

import java.util.*;

public class Population {
	private Random generator;
	public ArrayList<Agent> population = new ArrayList<Agent>();
	double totalSum;
	double longest;
	private double totalMutation = 0.98;
	private double swapMutation = 0.92;
	private int mode;
	int quantity;
	int num;
	
	public Population(int gQuantity, int gNum,int grandMode) {
		mode = grandMode;
		quantity = gQuantity;
		num=gNum;
		for (int i = 0; i < quantity; ++i)
			population.add(new Agent(num));
		generator = new Random();
	}

	public void evaluate2() {
		totalSum = 0;
		for (Agent a : population)
			totalSum += a.evaluate();
		population.sort(new CustomComparator());
		longest=population.get(population.size()-1).score;
		totalSum= population.size()*longest - totalSum;

		for (Agent a : population)
			a.matingProb = (longest -a.score )/ totalSum ;
	}
	
	public void evaluate() {
		totalSum = 0;
		for (Agent a : population)
			totalSum += a.evaluate();
		population.sort(new CustomComparator());
		for (Agent a : population)
			a.matingProb = 1 - a.score / totalSum * population.size();
	}

	// proliferation
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
			rand1=1.618*population.get(0).matingProb*generator.nextDouble();
			rand2=population.get(0).matingProb*generator.nextDouble();
			int j=0,k=0;
			while(population.get(j).matingProb>rand1)j++;
			while(population.get(k).matingProb>rand2)k++;
			if(mode==1)population.set(i, population.get(j).crossover(population.get(k)));
			if(mode==2)population.set(i, population.get(j).crossover2(population.get(k)));
		}
		
		//fresh blood
		for(Agent a: population)
				if(generator.nextDouble()>totalMutation)a.newDNA();
		 //
		for(Agent a: population)
			if(generator.nextDouble()>swapMutation){
				int r1=generator.nextInt(a.dna.size()-1);
				int r2=generator.nextInt(a.dna.size()-1);
				while(r1==r2)r2=generator.nextInt(a.dna.size()-1);
				int n=a.dna.get(r1);
				a.dna.set(r1, a.dna.get(r2));
				a.dna.set(r2,n);
			}
			//twix
	/*	for(Agent a: population)
			if(generator.nextDouble()>swapMutation){
				int r1=generator.nextInt(a.dna.size()-1);
				int r2=generator.nextInt(a.dna.size()-1);
				while(r1==r2)r2=generator.nextInt(a.dna.size()-1);
				Agent tmp =a;
				for(int ii = r1; ii <=r2;ii++) {
					a.dna.set(r1, a.dna.get(r2));
					}
				}
		*/
		
	}
	
	
	public void reproduction2(){

	
		
		//couple reproduction
		ArrayList<Agent> tmp = new ArrayList<>();
		for (int ii = 0; ii < quantity; ++ii)
			tmp.add(population.get(ii));
		double rand;
		for(int i=0;i<population.size();i++){
			rand=generator.nextDouble();
			int j=generator.nextInt(population.size()-1);
			int k=generator.nextInt(population.size()-1);
			while(tmp.get(j).matingProb<rand) {j=generator.nextInt(population.size()-1);rand=generator.nextDouble();	System.out.println("a "+i);
}
			while(tmp.get(k).matingProb<rand) {k=generator.nextInt(population.size()-1);rand=generator.nextDouble();	System.out.println("b "+i);
}
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
	/*	for(Agent a: population)
			if(generator.nextDouble()>swapMutation){
				int r1=generator.nextInt(a.dna.size()-1);
				int r2=generator.nextInt(a.dna.size()-1);
				while(r1==r2)r2=generator.nextInt(a.dna.size()-1);
				Agent tmp =a;
				for(int ii = r1; ii <=r2;ii++) {
					a.dna.set(r1, a.dna.get(r2));
					}
				}
		*/
	/*	population.sort(new CustomComparator());
		ArrayList<Integer> bestDna=new ArrayList<Integer>();
		for(int i=0;i<population.get(0).dna.size();++i)bestDna.add(population.get(0).dna.get(i));
			
		
		population.get(population.size()-1).dna=bestDna;*/
		
	}

	public class CustomComparator implements Comparator<Agent> {
		@Override
		public int compare(Agent a1, Agent a2) {
			int i = 0;
			i = a1.score > a2.score ? 1 : i;
			i = a1.score < a2.score ? -1 : i;
			return i;
		}
	}

	public void fillDNA(ArrayList<Node> list) {
		for (Agent a : population)
			a.fillDNA(list);
	}

	public void print() {
		for (Agent a : population)
			System.out.println(a.dna + "  " + a.score + "   " + a.matingProb);
		// for(Agent a:population)a.printDNA();
		System.out.println("");
	}

	public void printBest() {
		Agent a = population.get(0);
		System.out.println(a.dna + "   " + a.score + "   " + a.matingProb);
	}
}
