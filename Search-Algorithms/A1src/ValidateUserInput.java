
public class ValidateUserInput {
	// to ensure that agent is not allowed to take off and land on (0,0) and 0 parallel
	public static void validate(Node current, Node goal, int N)
	{
		if ( ((current.d<=0 || current.d>=N) && (current.angle<=0 || current.angle>=315))||((goal.d<=0 || goal.d>=N)&& (goal.angle<=0 || goal.angle>=315)) || (goal.d<=0))
		{
			System.out.print("Plane cannot fly on these coordinates"); 
			System.exit(0);

		}

	}
}
