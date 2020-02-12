package machineLearning;

import java.io.File;
import java.util.Arrays;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.persist.EncogDirectoryPersistence;

public class Network1 {

	public int inputUnits;
	public double[][] input_features;
	public double[][] simpleOutput;
	public int hiddenUnits;
	public int outputUnits;
	public double Momentum;
	public double LearningRate;
	public BasicNetwork basicNetwork;
	public BasicNetwork savedNetwork;
	public BasicNetwork loadedNet;
	public MLDataSet trainingSet;
	double value;
	int index;
	public Network1() {

		//creating the basic network

		basicNetwork = new BasicNetwork();
		//Learning rate and momentum are set after running network with different values and choosing the most accurate one
		LearningRate = 0.1;
		Momentum = 0.3;
	}


	public void creationOfNetwork()
	{
		input_features = DataSet.input; //its always the same as number of input data's features
		inputUnits=input_features[0].length;
		simpleOutput = DataSet.output; //its always equal to total number of output features in the data
		outputUnits=simpleOutput[0].length;
		hiddenUnits =9;
		basicNetwork.addLayer(new BasicLayer(null,false,inputUnits));
		basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(),true,hiddenUnits));
		basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(),false,outputUnits));
		basicNetwork.getStructure().finalizeStructure();
		//resetting input weight for next run
		basicNetwork.reset();
	}

	public void training()
	{
		trainingSet = new BasicMLDataSet(DataSet.input, DataSet.output);

		//Backpropagation(ContainsFlat network, MLDataSet training, double theLearnRate,double theMomentum)
		Backpropagation train=new Backpropagation (basicNetwork, trainingSet,LearningRate,Momentum);
		int epoch = 1;
		do {
			train.iteration();
			//System.out.println("Epoch #" + epoch + " Error:" + train.getError());
			epoch++;
		} while(train.getError()>0.01);
		{
			System.out.println("-------Trained succesfully--------");
			train.finishTraining();
		}
	}

	public void saveNetwork()
	{
		System.out.println("-------Saving network-------");
		EncogDirectoryPersistence.saveObject(new File("./Data/Network1.eg"), basicNetwork);
	}
	public void loadAlreadySavedNetwork(String file)
	{
		System.out.println("------Loading network-------");
		loadedNet = (BasicNetwork) EncogDirectoryPersistence.loadObject(
				new File (file) ) ;
	}


	public void testData(double[] inputData) 
	{
		double []outputData=new double [] {0.0,1.0,0.0,0.0,0.0};

		//actual output:0.0 1.0 0.0 0.0 0.0 
		MLData data=new BasicMLData(inputData);//creating data set of our input data
		//giving test data as input to our loaded network
		MLData output = loadedNet.compute(data);

		System.out.print("Network1 Input = ");
		for (int i = 0; i < inputData.length; i++) {
			System.out.print(data.getData(i)+ ", ");

		}
		System.out.println();

		System.out.print("Actual Output = ");
		double[] ActualOutput=new double[outputData.length];
		for (int i = 0; i < outputData.length; i++) {
			ActualOutput[i]=output.getData(i);
			System.out.print(output.getData(i) + ", ");
		}
		System.out.println();

		//changing actual output back to 0 and 1

		double maxValue = ActualOutput[0];
		for (int k = 1; k < ActualOutput.length; k++) {
			if (ActualOutput[k] > maxValue) {
				maxValue = ActualOutput[k];
				index=k;
			}
		}	
		//based on max value, changing the value of array to o and 1 (threshold as mentioned in lecture slides) 
		for (int k = 0; k < ActualOutput.length; k++) {
			if (k==index) 
			{
				ActualOutput[k]= 1;
			}
			else
			{
				ActualOutput[k]= 0;
			}
		}
		//optional commented print statements below for better understanding
		//System.out.println("The highest value in the array along with index is : " + maxValue+ ","+ index);	

		System.out.print("Actual Output after 0 and 1 encoding = ");
		for (int i = 0; i < outputData.length; i++) {
			System.out.print(ActualOutput[i] + ", ");
		}
		System.out.println();

		//on the basis of the above encoding, showing the user the name of class from which this answer belongs to: 
		for (String s : DataSet.hmap.keySet()) {

			double[] arr= DataSet.hmap.get(s);

			if (Arrays.equals(arr, ActualOutput))
			{

				//Displaying user the team from which its response belongs to	
				System.out.println("Your Response will be in Team: "+s);	
			}

		}
		System.out.println("");		

	}


}		




