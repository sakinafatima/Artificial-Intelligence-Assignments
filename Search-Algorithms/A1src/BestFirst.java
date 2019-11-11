//public class BestFirst {
//
//	public static void expand(Node node,Node goal)
//	{
//		
//		
//		
//		if(node.east!=null)
//		{node.east.setH(findheuristics(node.east,goal));
//			System.out.print(" \n child node on east added <"+ node.east.d+","+node.east.angle+"> with f(n)== "+node.east.getH());
//			
//			System.out.println(" \n size of pqueue "+Main.priorityQueue.size());
//			Main.priorityQueue.offer(node.east);
//		}
//		if(node.west!=null)
//		{node.west.setH(findheuristics(node.west,goal));
//			System.out.print(" \n child node on west added <"+ node.west.d+","+node.west.angle+"> with f(n)== "+node.west.getH());
//
//			
//			Main.priorityQueue.add(node.west);
//
//
//		}
//		if(node.south!=null)
//		{node.south.setH(findheuristics(node.south,goal));
//			System.out.print(" \n child node on south added <"+ node.south.d+","+node.south.angle+"> with f(n)== "+node.south.getH());
//
//			
//			Main.priorityQueue.add(node.south);
//		}
//		if(node.north!=null)
//		{node.north.setH(findheuristics(node.north,goal));
//			System.out.print(" \n child node on north added <"+ node.north.d+","+node.north.angle+"> with f(n)== "+node.north.getH());
//
//			
//			Main.priorityQueue.add(node.north);
//		}
//		if(node.north==null && node.south==null &&node.east==null&&node.west==null)
//		{
//			System.out.print("no child node found, moving to next node in frontier");
//		}
//	}
//
//	public static double findheuristics(Node node,Node goal)
//	{
//
//		double result=Astar.eucladianDistance(node, goal);
//
//		return result;
//
//	}
//
//
//
//
//
//}
//
