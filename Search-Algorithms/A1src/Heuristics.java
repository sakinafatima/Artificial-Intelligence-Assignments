
public class Heuristics {

	public static double findheuristics(Node node,Node goal, String search)


	{ 
		double AstarResult;

		double heuristics=findEucladianDistance(node,goal);

		if (node.parent==null)
		{// to avoid null pointer exception in case of first node whose parent is set to null
			node.parent=node;
		}

		double pathCost=findEucladianDistance(node.parent,node)+node.getDistance();

		AstarResult= pathCost+heuristics;
       
		if ("AStar".equals(search))
		{

			return AstarResult;
		}
		//returns only distance from start to goal
		if ("BestF".equals(search))

		{
			return heuristics;
		}
		//in case of uniform search
		return pathCost;

	}


	public static double findEucladianDistance(Node one,Node two)
	////using formula given in lecture slides to calculate this distance
	{
		double result=Math.sqrt((((one.d*one.d)+(two.d*two.d))-(2*(one.d*two.d*(Math.cos(two.angle-one.angle))))));

		return result;

	}

}
