//adding child nodes on when bidirectional search starts from goal node
public class BidirectionalFromGoalExpansion {


	public static void expand(Node node)
	{
		if(node.east!=null)

		{

			Search.frontier2.add(node.east);

		}
		if(node.west!=null)
		{


			Search.frontier2.add(node.west);
		}
		if(node.south!=null)
		{


			Search.frontier2.add(node.south);
		}
		if(node.north!=null)
		{


			Search.frontier2.add(node.north);
		}
	}

}

