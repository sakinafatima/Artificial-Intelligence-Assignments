package A3src;

import java.util.ArrayList;
import java.util.List;

public class BaysianNetwork {
	
	public static List<Node> Baysianlist=new ArrayList<>();
	
	
	//considering First node has probability distribution as:T and F
			//all other nodes have probability distribution as TT,TF,FT,FF
			//---------------------------------data for simple chain example from slides----------------------------
			static Node A=new Node("A",  new double [] {0.05, 0.95},null);

			static Node B=new Node("B",  new double []{0.05,0.95,0.80,0.20}, A);

			static Node C=new Node("C", new double []{0.10,0.90,0.30,0.70}, B);

			static Node D=new Node("D", new double [] {0.40,0.60, 0.60,0.40}, C);

			//------------------Data for fire alarm example now:-------------------------------------------------

			static Node Fire=new Node("Fire", new double[] {0.01,0.99},null);
			static Node Alarm=new Node("Alarm", new double[] {0.5,0.5,0.85,0.15},Fire);
			static Node Smoke=new Node("Smoke", new double[] {0.9,0.1,0.01,0.99},Fire);
			static Node leaving=new Node("Leaving", new double[] {0.88,0.12,0.0,1.0},Alarm);
			static Node report=new Node("Report", new double[] {0.75,0.25,0.01,0.99},leaving);

			//------------------Data from BN1-Traffic Congestion-------------------------------------------------

			static Node PeakTime=new Node("PeakTime", new double[] {0.37,0.63},null);
			static Node PredictionForTrafficCongestion=new Node("PredictionForTrafficCongestion", new double[] {1.0,0.0,0.7,0.3},PeakTime);
			static Node Alert=new Node("Alert", new double[] {0.9,0.1,0.01,0.99},PredictionForTrafficCongestion);


}
