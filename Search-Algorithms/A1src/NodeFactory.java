//this class is generating children nodes of a current node
public class NodeFactory {

	public static int NodeCounter=0;
	public static void makeNodes(Node currentNode,int N, String search, Node start)
	{
		//on the basis of the search, node is added to respective data structure
		if("BFS".equals(search) || "DFS".equals(search) || "BiDirect".equals(search))
		{
			if (Search.frontier.get(0)!=start)
			{

				Search.frontier.add(currentNode);
			}
		}
		if("AStar".equals(search) || "BestF".equals(search) || "Uniform".equals(search))
		{

			if (Search.priorityQueue.peek()!=start)
			{

				Search.priorityQueue.add(currentNode);
			}


		}
		//to avoid exception, parent of starting node is set to itself
		if (currentNode.parent==null)
		{
			currentNode.parent=currentNode;
		}
		//adding four nodes on basis of given rules
		if (currentNode.d>1)
		{
			Node first= new Node (currentNode.d-1, currentNode.angle,currentNode);
			currentNode.south=first;
			currentNode.south.setDistance(Heuristics.findEucladianDistance(currentNode,currentNode.south));
			currentNode.south.setDirection(180);
			NodeCounter=NodeCounter+1;
		} 

		if (currentNode.d<N-1)
		{
			Node second=new Node(currentNode.d+1,currentNode.angle,currentNode);
			currentNode.north=second;
			currentNode.north.setDistance(Heuristics.findEucladianDistance(currentNode,currentNode.north));
			currentNode.north.setDirection(360);
			NodeCounter=NodeCounter+1;
		}

		Node third=new Node(currentNode.d, currentNode.angle+45,currentNode);
		currentNode.east=third;
		currentNode.east.setDistance(Heuristics.findEucladianDistance(currentNode,currentNode.east));
		currentNode.east.setDirection(90);
		NodeCounter=NodeCounter+1;

		Node fourth=new Node(currentNode.d,currentNode.angle-45,currentNode);
		currentNode.west=fourth;
		currentNode.west.setDistance(Heuristics.findEucladianDistance(currentNode,currentNode.west));
		currentNode.west.setDirection(270);
		NodeCounter=NodeCounter+1;
	}


}
