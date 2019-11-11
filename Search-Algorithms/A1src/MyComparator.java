import java.util.Comparator;

public class MyComparator implements Comparator<Node> {

	//overriding comparator of priority queue
	@Override
	public int compare(Node o1, Node o2) {
		return -o1.compareTo(o2);
	}


}