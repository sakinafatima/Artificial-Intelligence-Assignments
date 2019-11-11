import java.time.Duration;
import java.time.Instant;

public class BiDirectional {

	public static void startUnInfomredSearch(Node node, Node goalNode, int N, String SearchName)
	{

		System.out.println("\nSearching Uninformed Search with "+ SearchName+ " Search");

		Node Start=node;

		Search.frontier.add(node);
		Search.frontier2.add(goalNode);

		System.out.println("\n Status of Frontier 1 in start: ");	

		for (Node expnodes : Search.frontier) {
			System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
		} 

		System.out.println("\n Status of Frontier 2 in start: ");	

		for (Node expnodes : Search.frontier2) {
			System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
		} 

		if (Search.frontier.size()>0 && Search.frontier2.size()>0) {



			while (!(node.d==goalNode.d && node.angle==goalNode.angle))
			{
				try {

					while(Search.validateExploredList(node)==true && Search.validateExploredListFromGoal(goalNode)==true)
					{


						System.out.println("\n This node <"+node.d+","+node.angle+"> is already Explored and Can not be explored again");
						Search.frontier.remove(node);
						Search.frontier2.remove(goalNode);

						if (Search.frontier.size()>0)
						{
							node=Search.frontier.get(0);
						}
						if (Search.frontier2.size()>0)
						{
							goalNode=Search.frontier2.get(0);
						}
					}
				}
				catch(ArrayIndexOutOfBoundsException exception) {
					System.out.print("Search Goes into Infinite loop and hence cannot be executed any further ");
				}

				if(node.d==goalNode.d && node.angle==goalNode.angle)
				{
					break;
				}
				else
				{   
					System.out.println(" \nCurrenlty Explored Nodes: "+"<"+ node.d+ ","+ node.angle+">, "+"<"+ goalNode.d+ ","+ goalNode.angle+">" );
					Search.exploredNodes.add(node);
					Search.exploredNodesFromGoal.add(goalNode);

					NodeFactory.makeNodes(node,N,SearchName,Start);

					NodeFactory.makeNodes(goalNode,N,SearchName,Start);

					Search.frontier.remove(node);
					Search.frontier2.remove(goalNode);

					System.out.println("\n Status of Explored Nodes from start: ");	

					for (Node expnodes : Search.exploredNodes) {
						System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
					} 
					
					System.out.println("\n Status of Explored Nodes from goal: ");	

					for (Node expnodes : Search.exploredNodes) {
						System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
					} 

					BiDirectFromNodeExpansion.expand(node);
					BidirectionalFromGoalExpansion.expand(goalNode);

					System.out.println("\n Status of Frontier 1 After Expansion ");	

					for (Node expnodes : Search.frontier) {
						System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
					} 

					System.out.println("\n Status of Frontier 2 After Expansion ");	

					for (Node expnodes : Search.frontier2) {
						System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
					} 

					node=Search.frontier.get(0);
					goalNode=Search.frontier2.get(0);

					Search.frontier.remove(node);
					Search.frontier2.remove(goalNode);

					System.out.println("\n----------------------------------------------------------------------------- ");						
				}
			}

			if (node.d==goalNode.d && node.angle==goalNode.angle)
			{
				System.out.println("Solution Found!---");

				A1main.end = Instant.now();
				System.out.println("Execution time " + Duration.between(A1main.start, A1main.end).toMillis()+ " ms");
				Search.frontier.remove(node);
				Search.frontier2.remove(goalNode);
				Search.exploredNodes.add(node);
				Search.exploredNodesFromGoal.add(goalNode);

				System.out.println("\n\nNode from start <"+node.d+","+node.angle+">\nNode from goal:< "+goalNode.d+","+goalNode.angle+">"); 

				System.out.println("Intersection Node from two Sides <"+node.d+","+node.angle+">");

				System.out.println("Total number of Nodes Created by BiDirectional: "+ NodeFactory.NodeCounter);

				System.out.println("Total number of Nodes Expanded by BiDirectional Search: "+ Search.exploredNodes.size());
           
			
			}
		}
		else
		{

			System.out.print("-------Frontier is Empty,This is an Error-----");
		}
	}

}
