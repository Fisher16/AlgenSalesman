package pl.edu.pw.fizyka.AlgenSalesman;

import java.util.*;

import pl.edu.pw.fizyka.AlgenSalesman.Population.CustomComparator;

public class greedyPopulation {
	public ArrayList<Agent> population = new ArrayList<Agent>();

	double totalSum;
	double totalSumOfProb;
	double longest;

	int num;
	
	public greedyPopulation(int gNum) {
		num=gNum;
		for (int i = 0; i < num; ++i)
			population.add(new Agent(num,"botak"));

	}
	public void evaluate() {
		for(int ii = 0; ii < num; ii++) {population.get(ii).greedySet(ii);population.sort(new CustomComparator());}
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
			System.out.println(a.dna + "  " + a.score);
		System.out.println("");
	}
	public void print(int nr) {
		Agent a = population.get(nr);
		System.out.println(a.dna + "   " + a.score );
	}

	public void printBest() {
		Agent a = population.get(0);
		System.out.println(a.dna + "   " + a.score);
	}
	public Agent returnBest(){
		return population.get(0);
	}
}
