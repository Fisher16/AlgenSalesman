package pl.edu.pw.fizyka.AlgenSalesman;

import java.util.ArrayList;
import java.util.Random;

public class Agent {
	
	public ArrayList<Integer> dna= new ArrayList<Integer>();
	public ArrayList<Node> dnaNode=new ArrayList<Node>();
	
	public Agent(int num){
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
		return s;
	}
	
	
}
