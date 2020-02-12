package machineLearning;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class A4Main {
	static double[] data=new double[9];
	static double[] Averageinput=new double[] {0.0,0.0,1.0,0.0,1.0,0.0,0.0,0.0,0.0};
	static ArrayList<String>ques=new ArrayList<String>();
	static double answer;
	static String responseNew;
	static String file="./Data/tickets.xlsx";
	static String file2="./Data/ticketsUpdated.xlsx";
	static double [] TestData=new double[]{0.0,1.0,1.0,0.0,0.0,0.0,1.0,0.0,0.0};//selected this data randomly from test data set

	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException
	{

		Network1 net =new Network1();

		System.out.println("Select Basic as Bas or Intermediate as Int");
		
		Scanner scanner11 = new Scanner(System.in);
	    String SearchName=scanner11.nextLine();
	    
		//String SearchName=args[0];
		if (SearchName.equalsIgnoreCase("Bas"))
		{   

			DataSet.readData(file);
			net.creationOfNetwork();
			net.training();
			net.saveNetwork();
			//this is best saved network and not being updated repeatedly
			net.loadAlreadySavedNetwork("./Data/Network1Saved.eg");
			net.testData(TestData);

		}

		if (SearchName.equalsIgnoreCase("Int"))
		{

			DataSet.readData(file2);
			//this is best saved network
			net.loadAlreadySavedNetwork("./Data/Network1Saved.eg");
			// Starting Interactions with user by asking different question.

			String[] quess = { "Is this a Request?","Is this an Incident?","Is this about Web Service?", "Is this about Login?","Is this something with Wireless?","Is this related to Printing?","Is this about Id Cards?","Is this about Staff?","Is this about Student?"};

			ques.addAll(Arrays.asList(quess));

			for(int x=0; x<quess.length;x++)
			{
				if (x<3)
				{
					System.out.println(quess[x]);
					Scanner scanner = new Scanner(System.in);
					answer = Double.parseDouble(scanner.nextLine());

					Averageinput[x]=answer;
				}

				if (x>=3)
				{
					net.testData(Averageinput);	
					System.out.println("Is this the Correct Answer? if no press 0 and if yes press 1!");

					Scanner scanner = new Scanner(System.in);
					if (scanner.nextLine().equals("0")) {
						System.out.println(quess[x]);
						answer = Double.parseDouble(scanner.nextLine());

						Averageinput[x]=answer;
						net.testData(Averageinput);	
					}
					else {
						System.out.println("Finishing the training as user is satsified with the result");
						System.exit(0);
					}
				}

			}
			//Asking user for confirmation about the allocated team, if they do not agree, the response is adding to the data set
			System.out.println("------Are you happy with the assigned response team now?, Press 0 for no and 1 for yes----- ");
			Scanner scanner = new Scanner(System.in);
			if (scanner.nextLine().equals("0")) {

				System.out.println(" Choose the team from the following:--Make sure you type as the name appears on the screen ");
				for (String s: DataSet.hmap.keySet()) 
				{
					System.out.println("\n"+s);
				}
				//taking user's response for Response team
				Scanner scanner1 = new Scanner(System.in);
				responseNew = scanner.nextLine();

			}
			//mapping user's selected team with our hashmap to find the 0,1 encoding of the output
			System.out.print("User's response is now-----------");
			for (double r : DataSet.hmap.get(responseNew))
			{
				System.out.print(r);
			}

			//Now inserting this input and output array in the training table
			DataSetUpdate.updatedata(Averageinput, responseNew);
			//retraining of network on new updated data
			net.creationOfNetwork();
			net.training();
			net.saveNetwork();
		}	

	}
}

