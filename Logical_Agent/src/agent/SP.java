
package agent;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SP{
	static char[][] Mymap;
	static char[][] orignal;
	static int tornedoNum;
	static int countSteps;

	SP(World world) {
		SP.orignal = world.map;

		this.tornedoNum=world.map.length;

		Mymap = new char[world.map.length][world.map.length];

		for (int i = 0; i < world.map.length; i++) {
			for (int j = 0; j < world.map.length; j++) {
				Mymap[i][j] = '*';
			}
		}
		Board agentmap = new Board(Mymap);	
	}

	public char[][] getMymap() {
		return this.Mymap;
	}

	@SuppressWarnings("static-access")
	public static void start(World world) {
		//starting the game
		Cell midCell = new Cell(world.map.length/2, world.map.length/2, world.map[world.map.length/2][world.map.length/2] );
		Cell top = new Cell(0, 0, world.map[0][0]);

		A2main.agentBoard.printBoard();// printing initial board
		System.out.println("starting the probing now-------- with 0,0 and 2,2");
		KnowledgeBase.tobeProbe.remove(midCell);
		KnowledgeBase.tobeProbe.remove(top);

		KnowledgeBase.alreadyProbed.add(midCell);
		KnowledgeBase.alreadyProbed.add(top);
		//probing the middle cell
		check_valueEqualsZero(midCell);
		countSteps++;
		//probing the first cell
		check_valueEqualsZero(top);
		countSteps++;
		A2main.agentBoard.printBoard();//printing board after 1st and middle cells probed
		next_move();

	}
	//selecting next move after top and mid points probed
	@SuppressWarnings("static-access")
	public static void next_move() {

		List<Cell> copyofprobeList = new ArrayList<>();
		copyofprobeList.addAll(KnowledgeBase.tobeProbe);
		//ensuring that neighbour cells of xero cells are all probed
		for (int i = 0; i < copyofprobeList.size(); i++)
		{
			List<Cell> copyOfZero = new ArrayList<>();
			copyOfZero.addAll(KnowledgeBase.zeros);

			if (KnowledgeBase.zeros.size() != 0) {

				for (int k = 0; k < copyOfZero.size(); k++) {

					check_valueEqualsZero(copyOfZero.get(k));

					if (KnowledgeBase.validateProbeList(copyOfZero.get(k)) == false) {

						KnowledgeBase.alreadyProbed.add(copyOfZero.get(k));
						KnowledgeBase.tobeProbe.remove(k);
					}
					countSteps++;//just a counter to calculate the number of steps taken by agent
					KnowledgeBase.zerosExpanded.add(copyOfZero.get(k));
					KnowledgeBase.zeros.poll();

				}
				A2main.agentBoard.printBoard();

			}

			//using strategy now to find the next move

			else if (KnowledgeBase.MarkedDanger.size()!=tornedoNum)
			{

				if (Mymap[KnowledgeBase.tobeProbe.get(i).x ][KnowledgeBase.tobeProbe.get(i).y]=='*')
				{
					Cell c= new Cell(KnowledgeBase.tobeProbe.get(i).x, KnowledgeBase.tobeProbe.get(i).y, KnowledgeBase.tobeProbe.get(i).value);


					System.out.println("* found at location "+c.x+c.y+KnowledgeBase.tobeProbe.size());
					checkNeighbours(c);
					countSteps++;

				}
				CheckLoopAgain();// starting moving on the board again after agent reaches at the end of board to find the covered cells
			}

		}	    
		if (KnowledgeBase.MarkedDanger.size()==tornedoNum)
		{
			System.out.print("Game WON!");
			A2main.end = Instant.now();
			System.out.println("\n\nExecution time " + Duration.between(A2main.start, A2main.end).toMillis()+ " ms");
			System.out.print("Steps Taken:"+ countSteps);
			System.exit(0);
		}
		else
		 //-------------------------------randomly selecting the value now with RPX now-----------------------------------------
			
			{
			System.out.print("Game is in halt state and need RPX to decide which action to take next");
			

			if (KnowledgeBase.tobeProbe.size() != tornedoNum) {
				for (int j=0;j<KnowledgeBase.tobeProbe.size();j++)
				{
					Cell randomCell = KnowledgeBase.tobeProbe.get(new Random().nextInt(KnowledgeBase.tobeProbe.size()));
					while (KnowledgeBase.validateProbeList(randomCell) == true) {
						KnowledgeBase.tobeProbe.remove(randomCell);
						randomCell = KnowledgeBase.tobeProbe.get(new Random().nextInt(KnowledgeBase.tobeProbe.size()));
					}
					countSteps++;
					System.out.println("Randomly probing is being performed now with x: ----------" + randomCell.x
							+ ",y: " + randomCell.y + " and the value of this move is: " + randomCell.value);

					if (randomCell.value == 't') {
						System.out.println("\n\n-------you have probed a tornado, hence Game Over!------");

						SP.Mymap[randomCell.x][randomCell.y] = randomCell.value;
						A2main.agentBoard.printBoard();

						A2main.end = Instant.now();
						System.out.println("\n\nExecution time " + Duration.between(A2main.start, A2main.end).toMillis()+ " ms");
						System.out.println("Total number of steps taken by the game: "+ countSteps);
						System.exit(0);
					}

					else if (randomCell.value == '0' || randomCell.value > 0) {
						check_valueEqualsZero(randomCell);
					}


				}
			}
			else if ((KnowledgeBase.tobeProbe.size() == tornedoNum)) {

				System.out.print("Game Won!");
				A2main.end = Instant.now();
				System.out.println("\n\nExecution time " + Duration.between(A2main.start, A2main.end).toMillis()+ " ms");
				System.out.println("Total number of steps taken by the game : "+ countSteps);
			}
		}
	}
//--------------------------validating values of cells as zero or non zero---------------------------------------
	@SuppressWarnings("static-access")
	public static void check_valueEqualsZero(Cell cell1) {
		char value1 = '*';
		char value2 = '*';
		char value3 = '*';
		char value4 = '*';
		char value5 = '*';
		char value6 = '*';
//---------------creating six neighbours of each cell----------
		
		if (cell1.value == '0') {
			if (cell1.x < SP.Mymap.length - 1) {
				value1 = SP.orignal[cell1.x + 1][cell1.y];
				Cell c = new Cell(cell1.x + 1, cell1.y, value1);
				//validating a cell not already probed
				if (KnowledgeBase.validateProbeList(c) == false) {
					KnowledgeBase.tobeProbe.remove(c);
					KnowledgeBase.alreadyProbed.add(c);
				}
				//validating a zero cell is not already expanded
				if (c.value == '0' && KnowledgeBase.validatezeroList(c) == false
						&& KnowledgeBase.validateZero(c) == false) {

					KnowledgeBase.zeros.add(c);

				}

			}
			if (cell1.x > 0) {
				value2 = SP.orignal[cell1.x - 1][cell1.y];
				Cell c = new Cell(cell1.x - 1, cell1.y, value2);

				if (KnowledgeBase.validateProbeList(c) == false) {
					KnowledgeBase.tobeProbe.remove(c);
					KnowledgeBase.alreadyProbed.add(c);
				}
				if (c.value == '0' && KnowledgeBase.validatezeroList(c) == false
						&& KnowledgeBase.validateZero(c) == false) {

					KnowledgeBase.zeros.add(c);
				}

			}
			if (cell1.y < SP.Mymap.length - 1) {
				value3 = SP.orignal[cell1.x][cell1.y + 1];
				Cell c = new Cell(cell1.x, cell1.y + 1, value3);
				if (KnowledgeBase.validateProbeList(c) == false) {
					KnowledgeBase.tobeProbe.remove(c);
					KnowledgeBase.alreadyProbed.add(c);
				}
				if (c.value == '0' && KnowledgeBase.validatezeroList(c) == false
						&& KnowledgeBase.validateZero(c) == false) {
					KnowledgeBase.zeros.add(c);
				}

			}
			if (cell1.y > 0) {
				value4 = SP.orignal[cell1.x][cell1.y - 1];
				Cell c = new Cell(cell1.x, cell1.y - 1, value4);
				if (KnowledgeBase.validateProbeList(c) == false)
				{
					KnowledgeBase.tobeProbe.remove(c);
					KnowledgeBase.alreadyProbed.add(c);
				}

				if (c.value == '0' && KnowledgeBase.validatezeroList(c) == false
						&& KnowledgeBase.validateZero(c) == false)
				{
					KnowledgeBase.zeros.add(c);
				}

			}
			if (cell1.x < SP.Mymap.length - 1 && cell1.y < SP.Mymap.length - 1) {
				value5 = SP.orignal[cell1.x + 1][cell1.y + 1];
				Cell c = new Cell(cell1.x + 1, cell1.y + 1, value5);
				if (KnowledgeBase.validateProbeList(c) == false) 
				{
					KnowledgeBase.tobeProbe.remove(c);
					KnowledgeBase.alreadyProbed.add(c);
				}

				if (c.value == '0' && KnowledgeBase.validatezeroList(c) == false
						&& KnowledgeBase.validateZero(c) == false) 
				{
					KnowledgeBase.zeros.add(c);
				}

			}
			if (cell1.x > 0 && cell1.y > 0) {
				value6 = SP.orignal[cell1.x - 1][cell1.y - 1];
				Cell c = new Cell(cell1.x - 1, cell1.y - 1, value6);
				if (KnowledgeBase.validateProbeList(c) == false) 
				{
					KnowledgeBase.tobeProbe.remove(c);
					KnowledgeBase.alreadyProbed.add(c);
				}

				if (c.value == '0' && KnowledgeBase.validatezeroList(c) == false
						&& KnowledgeBase.validateZero(c) == false) 
				{
					KnowledgeBase.zeros.add(c);

				}
			}
//----------------------showing the value from original board to agent's board-------------------
			SP.Mymap[cell1.x][cell1.y] = cell1.value;
			if (cell1.x < SP.Mymap.length - 1) {
				SP.Mymap[cell1.x + 1][cell1.y] = value1;
			}
			if (cell1.x > 0) {
				SP.Mymap[cell1.x - 1][cell1.y] = value2;
			}
			if (cell1.y < SP.Mymap.length - 1) {
				SP.Mymap[cell1.x][cell1.y + 1] = value3;
			}
			if (cell1.y > 0) {
				SP.Mymap[cell1.x][cell1.y - 1] = value4;
			}
			if (cell1.x < SP.Mymap.length - 1 && cell1.y < SP.Mymap.length - 1) {
				SP.Mymap[cell1.x + 1][cell1.y + 1] = value5;
			}
			if (cell1.x > 0 && cell1.y > 0) {
				SP.Mymap[cell1.x - 1][cell1.y - 1] = value6;
			}

		} else if (cell1.value > 0) {

			SP.Mymap[cell1.x][cell1.y] = cell1.value;
			if (KnowledgeBase.validateProbeList(cell1) == false) {
				KnowledgeBase.tobeProbe.remove(cell1);
				KnowledgeBase.alreadyProbed.add(cell1);
				A2main.agentBoard.printBoard();
			}
		}

	}
	//----------------------------------Checking Neighbours for clues for Dangers and probing-------------------------
	@SuppressWarnings("static-access")
	public static void checkNeighbours(Cell cell)
	{
		int clue;
		Cell origin=cell;// keeping the original state of the cell
	
		createNeighbours(cell);
		
		for (int i=0;i<cell.neigbours.size();i++)
		{
			if (cell.neigbours.get(i).value=='*' || cell.neigbours.get(i).value=='D')
			{
				//leaving the cells if value is not greater than zero
				System.out.println("---------no clue found so continuing to next----------at position: "+cell.neigbours.get(i).x+cell.neigbours.get(i).y);
             
			}
			else if (cell.neigbours.get(i).value>0)
			{
				createNeighbours(cell.neigbours.get(i));
				clue= cell.neigbours.get(i).value-'0';
			   
				if (clue==calculateMarked(cell.neigbours.get(i)))
				{

					if (SP.Mymap[cell.x][cell.y]!='D' && (KnowledgeBase.validateProbeList(cell)==false) )
					{
						System.out.println("------Probbing a Value now at---------"+ cell.x+cell.y);
						SP.Mymap[cell.x][cell.y]=cell.value;
						KnowledgeBase.alreadyProbed.add(cell);
						KnowledgeBase.tobeProbe.remove(cell);
						A2main.agentBoard.printBoard();

						break;
					}
				}
				else if (calculateCovered(cell.neigbours.get(i))==clue-calculateMarked(cell.neigbours.get(i)) && (KnowledgeBase.validateProbeList(cell)==false) )
				{ 

					System.out.println("------Marking a Value as Danger now---------");
					SP.Mymap[cell.x][cell.y]='D';
					KnowledgeBase.MarkedDanger.add(origin);
					A2main.agentBoard.printBoard();
					break;
				}

			}

		}

	}
//-----------------------creating neighbours cells of neighbours to find clues--------------------------------
	
	public static void createNeighbours(Cell cell)
	{
		if (cell.x<SP.Mymap.length-1)
		{
			Cell c=new Cell(cell.x+1,cell.y,SP.Mymap[cell.x+1][cell.y]);
			cell.neigbours.add(c);

		}
		if (cell.x>0)
		{

			Cell c=new Cell(cell.x-1,cell.y,SP.Mymap[cell.x-1][cell.y]);
			cell.neigbours.add(c);
		}
		if (cell.y<SP.Mymap.length-1)
		{

			Cell c=new Cell(cell.x,cell.y+1,SP.Mymap[cell.x][cell.y+1]);
			cell.neigbours.add(c);
		}
		if (cell.y>0)
		{
			Cell c=new Cell(cell.x,cell.y-1,SP.Mymap[cell.x][cell.y-1]);
			cell.neigbours.add(c);
		}
		if (cell.x<SP.Mymap.length-1 && cell.y<SP.Mymap.length-1)
		{

			Cell c=new Cell(cell.x+1,cell.y+1,SP.Mymap[cell.x+1][cell.y+1]);
			cell.neigbours.add(c);
		}
		if (cell.x>0 && cell.y>0)
		{
			Cell c=new Cell(cell.x-1,cell.y-1,SP.Mymap[cell.x-1][cell.y-1]);
			cell.neigbours.add(c);
		}

	}

	public static int calculateCovered(Cell cell)
	{
		//returns number of covered cells present in the neighbours
		
		int countCovered=0;
        
		for(Cell s: cell.neigbours) {
			if(s.value=='*')
			{
				countCovered++;
			}
		}

		return countCovered;
	}

	public static int calculateMarked(Cell cell)
	{
		//returns number of marked cells present in the neighbours
		int countMarked=0;
		for(Cell s: cell.neigbours) {

			if(s.value=='D')
			{
				countMarked++;	
				System.out.println("counterMarked value;   "+ s.value+s.x+s.y);
			}
		}
		return countMarked;
	}

	public static void CheckLoopAgain()
    //this function starts the startegy again after agent reaches the end of the board to look for covered cells
	{
		for (int j=0;j<KnowledgeBase.tobeProbe.size();j++)
		{
			if (Mymap[KnowledgeBase.tobeProbe.get(j).x ][KnowledgeBase.tobeProbe.get(j).y]=='*')
			{

				Cell c= new Cell(KnowledgeBase.tobeProbe.get(j).x, KnowledgeBase.tobeProbe.get(j).y, KnowledgeBase.tobeProbe.get(j).value);

				checkNeighbours(c);
				countSteps++;

			}
		}
	}

}

