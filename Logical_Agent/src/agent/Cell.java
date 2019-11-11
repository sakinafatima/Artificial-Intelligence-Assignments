package agent;

import java.util.ArrayList;
//cell class that stores all the information of the cell
public class Cell {
	
	int x;
	int y;
	char value;
	char flag='F';
	
	public ArrayList<Cell> neigbours  = new ArrayList<>();
	
	Cell(int x, int y, char val)
	{
		this.x=x;
		this.y=y;
		this.value=val;
	}
}
