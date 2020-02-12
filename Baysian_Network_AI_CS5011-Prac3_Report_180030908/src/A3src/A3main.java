package A3src;

import java.util.Collections;
import java.util.Scanner;

public class A3main {


	public static void main(String args[])
	{
		System.out.println("----------------There are three Networks given in this program---------------------");
		System.out.println("In the 'VariableElimination' function, give query X variable and evidence along with their boolean values----------------");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Select from A,B,C,D if you want to use Simple Chain Network");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Select from Fire,Alarm,Smoke,leaving,report if you want to use Fire Alarm Example");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Select from PeakTime,PredictionForTrafficCongestion,Alert if you want to use Traffic Congestion Example");
		//giving query to find probability of X, given evidence=True or False;
		//giving query to find probability of X, given evidence=True or False;
		//starting variable Elimination process by passing a query to the function that contains Query Variable X and Evidence 
		
		VariableElimination(BaysianNetwork.Alert,false,null,true);


	}
	public static void VariableElimination(Node node,boolean s, Node evidence, boolean e)
	{   
		Node child = null;
		//adding query variable in list as well so that it can be explored at the end
		BaysianNetwork.Baysianlist.add(node);
		//executing code if evidence is provided by the user
		if (evidence!=null)
		{
			//if evidence is true, setting false values of evidence to zero
			if (e==true)
			{
				if (evidence.prob.length>2)
				{
					evidence.prob[0]=evidence.prob[0]+evidence.prob[2];
				}
				evidence.prob[1]=0;
			}
			//if evidence is false, setting true value of the evidence to zero
			if (e==false)
			{
				if (evidence.prob.length>2)
				{
					evidence.prob[1]=evidence.prob[1]+evidence.prob[3];
				}
				evidence.prob[0]=0;
			}
			while(node.parent!=null && node.parent!=evidence.parent)
			{ 
				//adding nodes to be executed in a list
				BaysianNetwork.Baysianlist.add(node.parent);
				node=node.parent;
			}
		}
		//executing code without evidence
		else 
		{
			while(node.parent!=null)
			{
				//adding nodes to be executed in a list
				BaysianNetwork.Baysianlist.add(node.parent);
				node=node.parent;
			}
		}

		System.out.println("--------Values in the network list before reversal----------");

		for (Node node1: BaysianNetwork.Baysianlist)
		{
			System.out.println(node1.name);
		}
		//reversal of the list to start operation from the top node
		Collections.reverse(BaysianNetwork.Baysianlist);

		System.out.println("----------Values in the network list after reversal----------");

		for (Node node1: BaysianNetwork.Baysianlist)
		{
			System.out.println(node1.name);
		}
		//tranversing through the list to explore all nodes that eventually lead to final probability of query variable
		for(int i=0; i<BaysianNetwork.Baysianlist.size()-1;i++)
		{
			node=BaysianNetwork.Baysianlist.get(i);
			System.out.println("The first node to be eliminated now: "+ node.name);
			child=BaysianNetwork.Baysianlist.get(i+1);
			calculateSumOut(node,child);

			//----------------------------normalization of values--------------------------
			double tot= child.prob[0]+child.prob[1];
			child.prob[0]=child.prob[0]/tot;
			child.prob[1]=child.prob[1]/tot;
			System.out.println(" New probabilities of the "+child.name+" node after normalization at true and false states are:  \n"+ child.prob[0]+" , "+child.prob[1]);

			if (s==true)
			{
				System.out.println("Probability of Query Varaible "+child.name+" at given true state is: "+child.prob[0]); 
			}
			else
			{
				System.out.println("Probability of Query Varaible "+child.name+" at given false state is: "+child.prob[1]);
			}
		}
	}
	//-----------------------joining and marginalisation of input tables (algorithm taken from Lecture Slides 14)----------------------------
	public static void calculateSumOut(Node node, Node child)
	{
		System.out.println("value at T: "+node.prob[0]);
		System.out.println("value at F: "+ node.prob[1]);
		System.out.println("Values at child :"+child.name+":---------------");
		System.out.println("\n"+ child.prob[0]);
		System.out.println("\n"+ child.prob[1]);
		System.out.println("\n"+ child.prob[2]);
		System.out.println("\n"+ child.prob[3]);
		double TT=node.prob[0]*child.prob[0];//multiplication of child nodes probability with parent probability
		double TF=node.prob[0]*child.prob[1];
		double FT=node.prob[1]*child.prob[2];
		double FF=node.prob[1]*child.prob[3];
		System.out.println("After multiplication with parent node state, child values become: TT: "+TT+" ,TF: "+TF+" ,FT: "+FT+" ,FF "+FF+ "\n");
		double T=TT+FT;//addition of child nodes probability with parent probability
		double F=FF+TF;
		System.out.println("After addition child values become at T, F: "+T+" , "+F);
		child.prob = new double[child.prob.length];//clearing the child with all other values before assigning the final values of probability
		child.prob[0]=T;
		child.prob[1]=F;



	}


}
