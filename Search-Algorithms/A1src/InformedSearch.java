
public class InformedSearch {
	//adding children to BestF/A*/Uniform Search
	public static void expand(Node node,Node goal, String search)
	{



		if(node.east!=null)
		{
			System.out.print(" \n child node on east added <"+ node.east.d+","+node.east.angle+">" );
			node.east.setH(Heuristics.findheuristics(node.east,goal,search));
			Search.priorityQueue.offer(node.east);
		}
		if(node.west!=null)
		{
			System.out.print(" \n child node on west added <"+ node.west.d+","+node.west.angle+">");
			node.west.setH(Heuristics.findheuristics(node.west,goal,search));
			Search.priorityQueue.add(node.west);


		}
		if(node.south!=null)
		{
			System.out.print(" \n child node on south added <"+ node.south.d+","+node.south.angle+">");

			node.south.setH(Heuristics.findheuristics(node.south,goal,search));
			Search.priorityQueue.add(node.south);
		}
		if(node.north!=null)
		{
			System.out.print(" \n child node on north added <"+ node.north.d+","+node.north.angle+">");

			node.north.setH(Heuristics.findheuristics(node.north,goal,search));
			Search.priorityQueue.add(node.north);
		}
		if(node.north==null && node.south==null &&node.east==null&&node.west==null)
		{
			System.out.print("no child node found, moving to next node in frontier");
		}
	}





}
