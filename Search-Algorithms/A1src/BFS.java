public class BFS
{

	//adding children to BFS Search
	public static void expand(Node node)
	{
		if(node.east!=null)
		{
			System.out.print(" \n child node on east added <"+ node.east.d+","+node.east.angle+">");

			Search.frontier.add(node.east);
		}
		if(node.west!=null)
		{
			System.out.print(" \n child node on west added <"+ node.west.d+","+node.west.angle+">");
			Search.frontier.add(node.west);
		}
		if(node.south!=null)
		{
			System.out.print(" \n child node on south added <"+ node.south.d+","+node.south.angle+">");
			Search.frontier.add(node.south);
		}
		if(node.north!=null)
		{
			System.out.print(" \n child node on north added <"+ node.north.d+","+node.north.angle+">");
			Search.frontier.add(node.north);
		}
		if(node.north==null && node.south==null &&node.east==null&&node.west==null)
		{
			System.out.print("no child node found, moving to next node in frontier");
		}
	}

}
