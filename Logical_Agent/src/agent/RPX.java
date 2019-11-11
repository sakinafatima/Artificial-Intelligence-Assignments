package agent;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RPX {
	static char[][] Mymap;
	static char[][] orignal;
	static int tornedoNum;
    static int countSteps=0; //it will helps me in evaluation to count the number of steps taken by strategy
	RPX(World world) {
		RPX.orignal = world.map;

		this.tornedoNum=world.map.length;

		Mymap = new char[world.map.length][world.map.length];

		// creating my own world with covered cells '*' which is of same length of the given world.

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

	public static void start(World world) {
		//starting the game with coordinates of middle and top cells

		Cell midCell = new Cell(world.map.length/2, world.map.length/2, world.map[world.map.length/2][world.map.length/2] );
		countSteps++;
		Cell top = new Cell(0, 0, world.map[0][0]);
		countSteps++;
		A2main.agentBoard.printBoard();// printing initial board
		System.out.println("starting the probing now-------- with 0,0 and 2,2");

		//removing middle and top cells from 'to be probe' list and adding it to the 'probed' list.
		KnowledgeBase.tobeProbe.remove(midCell);
		KnowledgeBase.tobeProbe.remove(top);

		KnowledgeBase.alreadyProbed.add(midCell);
		KnowledgeBase.alreadyProbed.add(top);
		check_valueEqualsZero(midCell);
		check_valueEqualsZero(top);
		A2main.agentBoard.printBoard();//printing board after 1st and middle points probed
		next_move();

	}
	//selecting next move after top and mid points probed
	
	@SuppressWarnings("static-access")
	public static void next_move() {

		List<Cell> copyofprobeList = new ArrayList<>();
		copyofprobeList.addAll(KnowledgeBase.tobeProbe);

		for (int i = 0; i < copyofprobeList.size(); i++) {
			List<Cell> copyOfZero = new ArrayList<>();
			copyOfZero.addAll(KnowledgeBase.zeros);// creating copy of list for iterating
           
			// checking that all neighbours of zero cells are probed before moving to the random selection
			if (KnowledgeBase.zeros.size() != 0) {

				for (int k = 0; k < copyOfZero.size(); k++) {

					check_valueEqualsZero(copyOfZero.get(k));

					if (KnowledgeBase.validateProbeList(copyOfZero.get(k)) == false) {

						KnowledgeBase.alreadyProbed.add(copyOfZero.get(k));
						KnowledgeBase.tobeProbe.remove(k);
						
					}
					KnowledgeBase.zerosExpanded.add(copyOfZero.get(k));
					KnowledgeBase.zeros.poll();
					countSteps++;
				}
				A2main.agentBoard.printBoard();

			} else {
                    //randomly selecting the value now!
				if (KnowledgeBase.tobeProbe.size()!=tornedoNum)
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

						RPX.Mymap[randomCell.x][randomCell.y] = randomCell.value;
						A2main.agentBoard.printBoard();
						
						A2main.end = Instant.now();
						System.out.println("\n\nExecution time " + Duration.between(A2main.start, A2main.end).toMillis()+ " ms");
						System.out.println("Total number of steps taken by the game: "+ countSteps);
						System.exit(0);
					}

					else if (randomCell.value == '0' || randomCell.value > 0) {
						check_valueEqualsZero(randomCell);
					}
			
				else if ((KnowledgeBase.tobeProbe.size() == tornedoNum)) {

					System.out.print("Game Won!");
					A2main.end = Instant.now();
					System.out.println("\n\nExecution time " + Duration.between(A2main.start, A2main.end).toMillis()+ " ms");
					System.out.println("Total number of steps taken by the game : "+ countSteps);
				}
			}

		}
		}
	}

	private static boolean gameDone(Cell cell) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("static-access")
	public static void check_valueEqualsZero(Cell cell1) {
		char value1 = '*';
		char value2 = '*';
		char value3 = '*';
		char value4 = '*';
		char value5 = '*';
		char value6 = '*';

		if (cell1.value == '0') 
		{
			
			if (cell1.x < RPX.Mymap.length - 1) {
				value1 = RPX.orignal[cell1.x + 1][cell1.y];
				Cell c = new Cell(cell1.x + 1, cell1.y, value1);
				//ensuring that cells are not already probed
				if (KnowledgeBase.validateProbeList(c) == false) {
					KnowledgeBase.tobeProbe.remove(c);
					KnowledgeBase.alreadyProbed.add(c);
				}
				//ensuring zero cells are not already expanded
				if (c.value == '0' && KnowledgeBase.validatezeroList(c) == false
						&& KnowledgeBase.validateZero(c) == false) {

					KnowledgeBase.zeros.add(c);

				}

			}
			if (cell1.x > 0) {
				value2 = RPX.orignal[cell1.x - 1][cell1.y];
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
			if (cell1.y < RPX.Mymap.length - 1) {
				value3 = RPX.orignal[cell1.x][cell1.y + 1];
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
				value4 = RPX.orignal[cell1.x][cell1.y - 1];
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
			if (cell1.x < RPX.Mymap.length - 1 && cell1.y < RPX.Mymap.length - 1) {
				value5 = RPX.orignal[cell1.x + 1][cell1.y + 1];
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
				value6 = RPX.orignal[cell1.x - 1][cell1.y - 1];
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
            //showing the values of neigbours probed cells in our world only
			RPX.Mymap[cell1.x][cell1.y] = cell1.value;
			if (cell1.x < RPX.Mymap.length - 1) {
				RPX.Mymap[cell1.x + 1][cell1.y] = value1;
			}
			if (cell1.x > 0) {
				RPX.Mymap[cell1.x - 1][cell1.y] = value2;
			}
			if (cell1.y < RPX.Mymap.length - 1) {
				RPX.Mymap[cell1.x][cell1.y + 1] = value3;
			}
			if (cell1.y > 0) {
				RPX.Mymap[cell1.x][cell1.y - 1] = value4;
			}
			if (cell1.x < RPX.Mymap.length - 1 && cell1.y < RPX.Mymap.length - 1) {
				RPX.Mymap[cell1.x + 1][cell1.y + 1] = value5;
			}
			if (cell1.x > 0 && cell1.y > 0) {
				RPX.Mymap[cell1.x - 1][cell1.y - 1] = value6;
			}
            //showing only that probed cell on our world 
		} else if (cell1.value > 0) {

			RPX.Mymap[cell1.x][cell1.y] = cell1.value;
			if (KnowledgeBase.validateProbeList(cell1) == false) {
				KnowledgeBase.tobeProbe.remove(cell1);
				KnowledgeBase.alreadyProbed.add(cell1);
				A2main.agentBoard.printBoard();
			}
		}

	}
}