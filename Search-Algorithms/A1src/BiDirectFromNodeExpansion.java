//adding child nodes on when bidirectional search starts from start node
public class BiDirectFromNodeExpansion {

	public static void expand(Node node)
	{
		if(node.east!=null)
		{

			Search.frontier.add(node.east);


		}
		if(node.west!=null)
		{

			Search.frontier.add(node.west);

		}
		if(node.south!=null)
		{

			Search.frontier.add(node.south);

		}
		if(node.north!=null)
		{

			Search.frontier.add(node.north);

		}
	}

}
