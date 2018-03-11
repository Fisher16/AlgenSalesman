package pl.edu.pw.fizyka.AlgenSalesman;

import java.util.*;


public class Agent {
	
	public ArrayList<Integer> dna= new ArrayList<Integer>();
	public ArrayList<Node> dnaNode=new ArrayList<Node>();
	public double score;
	public double matingProb;
	
	public Agent(int num){
		while(dna.size()<num){
			int n=(new Random()).nextInt(num);
			while(!(valid(n,dna)))n=(new Random()).nextInt(num);
			dna.add(n);
		}
	}
	public Agent(int num,int i){
		while(dna.size()<num){
			int n=i;
			dna.add(n);
		}
	}
	public Agent(){
		int num=5;
		while(dna.size()<num){
			int n=(new Random()).nextInt(num);
			while(!(valid(n,dna)))n=(new Random()).nextInt(num);
			dna.add(n);
		}
	}
	public Boolean valid(int n,ArrayList<Integer> list){
		Boolean test=true;
		for(int j=1;j<list.size()+1;++j)if(n==list.get(j-1))test=false;
		return test;
	}
	
	public void fillDNA(ArrayList<Node> list){
		for(int i: dna)dnaNode.add(list.get(i));
	}
	
	public double evaluate(){
		double s=0;
		for(int i=1;i<dnaNode.size();++i)
			s+=dnaNode.get(i).dist(dnaNode.get(i-1));
		score=s;
		return s;
	}
	
	public Agent crossover(Agent sec){
		Agent child=new Agent(dna.size(),-1);
		int n= (new Random()).nextInt(dna.size()-3);
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
	public void printDNA(){
		System.out.println(dna);
	}
	
	public void newDNA(){
		for(int i=0;i<dna.size();++i)dna.set(i,-1);
		for(int i=0;i<dna.size();++i){
			int n=(new Random()).nextInt(dna.size());
			while(!(valid(n,dna)))n=(new Random()).nextInt(dna.size());
			dna.set(i,n);
		}
	}
	
	
	
	
}
