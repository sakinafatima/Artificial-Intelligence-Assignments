package agent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KnowledgeBase {
	
	
	public static List<Cell> tobeProbe= new ArrayList<>();
	public static List<Cell> alreadyProbed= new ArrayList<>();
	public static List<Cell> neigbours= new ArrayList<>();
	public static Queue<Cell> zeros = new LinkedList<Cell>();
	public static List<Cell> zerosExpanded= new ArrayList<>();
	public static List<Cell> MarkedDanger= new ArrayList<>();
	
public static void addCells(World world)
{
	
	// adding all cells of the given world into the 'to be probe' list.
	for(int j=0;j<world.map.length;j++)
	{
		for(int c=0;c<world.map.length;c++)
		{
			char val=world.map[j][c];

			Cell cell=new Cell(j,c,val);
			KnowledgeBase.tobeProbe.add(cell);

		}
	}
	}
	///------------------------------------------------Validating Already probed cells----------------
	public static boolean validateProbeList(Cell cell)
	{
		for (Cell cell1: alreadyProbed) {

			if (cell.x==cell1.x && cell.y==cell1.y)
			{
				return true;
			}

		}
		
		return false;
		
	}
	
	
	///-----------------------------------------Validating Zeros Found in Neighbours------------------	
	
	public static boolean validatezeroList(Cell cell)
	{
		for (Cell cell1: zeros) {

			if (cell.x==cell1.x && cell.y==cell1.y)
			{
				return true;
			}

		}
		
		return false;
		
	}
	
	///-----------------------------------------Validating Zeros ALready Expanded------------------
		public static boolean validateZero(Cell cell)
		{
			for (Cell cell1: zerosExpanded) {

				if (cell.x==cell1.x && cell.y==cell1.y)
				{
					return true;
				}

			}
			
			return false;
			
		}
}
