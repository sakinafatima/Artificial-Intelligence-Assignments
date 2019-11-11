import java.time.Instant;


public class A1main {

	public static Instant start,end;

	public static void main(String[] args) throws InterruptedException {


		start = Instant.now();//starting timer for execution time
		int N=0;
		//taking input data from args
		String SearchName=args[0];
		try{
			N= Integer.parseInt(args[1]);
		}catch(NumberFormatException ex){ // handle your exception

		}

		String[] input = args[2].split(",");
		String[] goal=args[3].split(",");


		Node startNode=new Node(Integer.parseInt(input[0]),Integer.parseInt(input[1]), null);
		Node goalNode=new Node(Integer.parseInt(goal[0]),Integer.parseInt(goal[1]),null);

		ValidateUserInput.validate(startNode,goalNode, N);

		//function calling for A*/BestFirst/Uniform search here

		if ("AStar".equals(SearchName) || ("BestF".equals(SearchName)) || "Uniform".equals(SearchName))
		{
			Search.startInformedSearch(startNode, goalNode, N, SearchName);
		}
		//function calling for BFS/DFS search here

		else if ("BFS".equals(SearchName) || "DFS".equals(SearchName))
		{
			Search.startUnInfomredSearch(startNode, goalNode, N, SearchName);
		}
		//function calling for BiDirectional search here

		else if ("BiDirect".equals(SearchName) )
		{
			BiDirectional.startUnInfomredSearch(startNode, goalNode, N, SearchName);
		}
		else
		{
			//incase of invalid input data
			System.out.println("Invalid Name of search");
		}


	}

}



