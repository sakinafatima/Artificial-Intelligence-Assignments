import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Search {


	public static List<Node> frontier= new ArrayList<>();
	public static List<Node> frontier2= new ArrayList<>();
	public static List<Node> exploredNodes= new ArrayList<>();
	public static List<Node> exploredNodesFromGoal= new ArrayList<>();
	static Comparator<Node> comparator = new MyComparator();
	public static PriorityQueue<Node> priorityQueue=new PriorityQueue<Node>(new MyComparator());
	public static Node startNode;


	//---------------------------------------------------------------UNINFORMED SEARCH--------------------------------------------------------------------------

	public static void startUnInfomredSearch(Node node, Node goalNode, int N, String SearchName)
	{

		System.out.println("\nSearching Uninformed Search with "+ SearchName+ " Search");

		startNode=node;

		frontier.add(node);// adding first node to frontier

		System.out.println("\nFrontier After First Node is Added: ");	

		for (Node expnodes : frontier) {
			System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
		} 

		System.out.println("\nNode Explored now: <"+node.d+","+node.angle+">");

		//checking size of frontier
		if (frontier.size()>0) {


			//checking current node is not goal node
			while (!(node.d==goalNode.d && node.angle==goalNode.angle))
			{
				while(validateExploredList(node)==true)
				{
					System.out.print("\n This node <"+node.d+","+node.angle+"> is already Explored and Can not be explored again \n");
					frontier.remove(node);// removing already explored node from frontier
					node=frontier.get(0);//getting the next value from frontier
				}
				// this if block is to ensure that if goal node is found after coming from above block, search is ended
				if(node.d==goalNode.d && node.angle==goalNode.angle)
				{
					break;
				}
				else
				{   
					exploredNodes.add(node);//adding current node to explored node

					NodeFactory.makeNodes(node,N,SearchName,startNode);// creating child nodes
					frontier.remove(node);//removing from frontier after exploring

					System.out.println("\n Status of Explored Nodes: ");	

					for (Node expnodes : exploredNodes) {
						System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
					} 


					//-----------------------------Performing search based on user's input-----------------------------------------
					// adding child nodes to frontier
					if ("BFS".equals(SearchName))
					{
						BFS.expand(node);
					}
					if ("DFS".equals(SearchName))
					{
						DFS.expand(node);
					}

					System.out.print("\n Status of frontier after a node is expanded: ");	

					for (Node expnodes : frontier) {
						System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
					}

					node=frontier.get(0);
					//this is for start node to show its direction in the path followed 
					if (startNode.getDirection()==0)
					{ 
						startNode.setDirection(node.getDirection());
					}

					System.out.println("\n-------------------------------------------------------------------------------------");




					System.out.print("\n Node Explored now: <"+node.d+","+node.angle+">");
					frontier.remove(node);
				}
			}

			if (node.d==goalNode.d && node.angle==goalNode.angle)
			{


				System.out.println("\nGoal state "+ "<"+ node.d+ ","+ node.angle+"> is found");

				frontier.remove(node);//when goal found remove it from frontier and add to explored list so that it can be displayed in output

				exploredNodes.add(node);

				A1main.end = Instant.now();
				System.out.println("Execution time " + Duration.between(A1main.start, A1main.end).toMillis()+ " ms");

				System.out.println("Total number of Nodes Created: "+ NodeFactory.NodeCounter);

				System.out.println("Total number of Nodes Expanded by Search: "+ exploredNodes.size());


				Path.printPath(node, startNode);//printing path followed 

				System.out.println("\n\nName of all States that have been Explored: ");	

				for (Node expnodes : exploredNodes) {
					System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
				} 



			}
		}
		else
		{

			System.out.print("-------Frontier is Empty,This is an Error-----");
		}
	}
	//---------------------------------------------------------------INFORMED SEARCH------------------------------------------------------------------------------------------


	public static void startInformedSearch(Node node, Node goal, int N, String search)
	{

		Node startNode=node;

		System.out.print("Searching Informed Search with "+ search+ " Search");

		node.setH(Heuristics.findheuristics(node,goal,search));//find distance of start node

		priorityQueue.add(node);// adding to priority queue

		System.out.println("\n Frontier After First Node is Added: ");	

		for (Node expnodes : priorityQueue) {
			System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+"> "+ expnodes.getH());
		} 

		System.out.println("\nNode Explored now: <"+node.d+","+node.angle+">");

		if (priorityQueue.size()>0)//checking queue is not empty
		{

			while (!(node.d==goal.d && node.angle==goal.angle))
			{
				while(validateExploredList(node)==true)//validating node is not already explored
				{
					System.out.print("\n This node <"+node.d+","+node.angle+"> is already Explored and cannot be explored again");
					node= priorityQueue.poll();
				}

				if(node.d==goal.d && node.angle==goal.angle)
				{
					break;
				}
				else
				{

					exploredNodes.add(node);// adding node to explored list
					NodeFactory.makeNodes(node,N,search,startNode);
					priorityQueue.remove(node);// removing node from queue

					System.out.println("\n Status of explored nodes: ");	

					for (Node explorednode : exploredNodes) {
						System.out.print("<"+ explorednode.d+ ","+ explorednode.angle+">");
					}       


					InformedSearch.expand(node,goal,search);


					System.out.print("\n Status of frontier after a node is expanded: ");	

					for (Node expnodes : priorityQueue) {
						System.out.print(" <"+ expnodes.d+ ","+ expnodes.angle+"> "+ expnodes.getH());
					}


					node=priorityQueue.peek();// getting first node in queue

					if (startNode.getDirection()==0)
					{ 
						startNode.setDirection(node.getDirection());
					}

					System.out.println("\n-------------------------------------------------------------------------------------");




					System.out.print("\n Node Explored now: <"+node.d+","+node.angle+">");
					priorityQueue.remove(node);

				}

			}

			if (node.d==goal.d && node.angle==goal.angle)
			{
				priorityQueue.poll();//removing first node from queue
				exploredNodes.add(node);
				System.out.print("\n Node Explored: <"+node.d+","+node.angle+">"); 

				System.out.print("\n Goal state "+ "<"+ node.d+ ","+ node.angle+"> is found");

				A1main.end = Instant.now();// ending timer
				System.out.println("\n\nExecution time " + Duration.between(A1main.start, A1main.end).toMillis()+ " ms");

				System.out.println("Total number of Nodes Created: "+ NodeFactory.NodeCounter);

				System.out.println("Total number of Nodes Expanded by Search: "+ exploredNodes.size());

				Path.printPath(node, startNode);

				System.out.println("\n Name of all States that have been Explored: ");	

				for (Node expnodes : exploredNodes) {
					System.out.print("<"+ expnodes.d+ ","+ expnodes.angle+">");
				} 

			}
		}
		else
		{
			System.out.print("-----------------Frontier is Empty,This is an Error--------------------");	

		}
	}
	//-------------------------------------------------------VALIDATING A NODE WITHIN AN EXPLORED LIST----------------------------------------------------------------------------

	//validing a node within explored nodes list
	public static boolean validateExploredList(Node node)
	{
		for (Node node1: exploredNodes) {

			if (node.d==node1.d && node.angle==node1.angle)
			{
				return true;
			}

		}
		return false;
	}
	//a new validation for exploredlist from Goal node in case Bidirectional Search
	public static boolean validateExploredListFromGoal(Node node)
	{
		for (Node node1: exploredNodes) {

			if (node.d==node1.d && node.angle==node1.angle)
			{
				return true;
			}

		}
		return false;
	}

}
