import java.util.Collections;
import java.util.ArrayList;


public class Path {

	@SuppressWarnings("rawtypes")
	public static ArrayList directionLabels = new ArrayList();
	
	
	@SuppressWarnings("unchecked")
	public static void printPath(Node node, Node StartNode)
	{
		
		while(node.parent!=StartNode)
		{
        //getting parent node of each node to print the path taken by agent to find the goal
		directionLabels.add("H"+node.parent.getDirection());
	    node=node.parent;
		}
		directionLabels.add("H"+node.parent.getDirection());// adding the parent reference of first node to the path
		Collections.reverse(directionLabels);// reversing the list to show path from start to gaol
		System.out.println("\nLength/number of steps of the flight route: "+ directionLabels.size());
		
		System.out.println("\nPath Followed by Search from Start to Goal: "+directionLabels.toString().replace("[", "").replace("]", ""));
		
	}
	}
