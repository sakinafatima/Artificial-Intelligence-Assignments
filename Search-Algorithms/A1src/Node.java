//Node class
public class Node {
	Node north;
	Node south;
	Node west;
	Node east;
	Node parent;
	double pathcost;
	int d;
	int angle;
	int direction;
	Double h;
	//setting the constructor
	Node(int d, int angle, Node p)
	{
		this.parent=p;
		this.d=d;

		if(angle<0) {
			this.angle = 315;
		}
		else {
			this.angle=angle % 360;			
		}

		this.north=null; 
		this.south=null;
		this.west=null;
		this.east=null;
		this.h = null;

	}

	public void setDistance(Double pathcost) {
		this.pathcost=pathcost;
	}

	public Double  getDistance() {
		return this.pathcost;
	}

	//setting heuristic value of node
	public void setH(Double h) {
		this.h = h;
	}

	public Double  getH() {
		return this.h;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int  getDirection() {
		return this.direction;
	}



	//compareto method for priority queue comparator
	public int compareTo(Node o2) {
		if(this.equals(o2)){
			return 0;
		}else if(this.getH() < o2.getH()){
			return 1;
		}else return -1;
	}


}
