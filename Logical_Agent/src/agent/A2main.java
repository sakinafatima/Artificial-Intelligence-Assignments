package agent;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class A2main {
	public static Instant start,end;
	
	static Board agentBoard;
	
	public static void main(String[] args) throws InterruptedException {
		
		//adding all cells to KB in to be probed list of cells
		
		start = Instant.now();//for calculating time
		String strategy=args[0];
		String mapName=args[1];
		World world=World.valueOf(mapName);
		
		
		KnowledgeBase.addCells(world);
		
		if ("RPX".equals(strategy))
		{
			RPX agent=new RPX(world);
			agentBoard = new Board (agent.getMymap());
			
			//starting the game
			agent.start(world);
		}
		
	    if ("SPX".equals(strategy))
	    {
	    	SP agent=new SP(world);
	    	agentBoard = new Board (agent.getMymap());
	    	
	    	//starting the game
			agent.start(world);
	    }
	    
	    else
	    {
	    	
	    	System.out.print("Invalid Selection, Please try again");
	    }
	}
}

