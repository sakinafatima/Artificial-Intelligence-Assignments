package machineLearning;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataSet {

	static ArrayList<String>data=new ArrayList<String>();

	static ArrayList<String>col=new ArrayList<String>();
	static List<String> items;


	public static double output[][];
	public static double input[][];
	static HashMap<String, double[]> hmap = new LinkedHashMap<String, double[]>();


	public static void readData(String file) throws IOException{	

		try {
            //setting the hashmap with different output values
			hmap.put("Emergencies",null);
			hmap.put("Credentials", null);
			hmap.put("Datawarehouse",null);
			hmap.put("Networking", null);
			hmap.put("Equipment",null);

			File excel = new File (file);
			FileInputStream fis = new FileInputStream(excel);
            //reading the excel file through Apache POI
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			String row;
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				Cell cell = currentRow.getCell(0);
				row = String.valueOf(cell);
				//System.out.print(cell.getStringCellValue() + "-");
				for (int j=1; j<10;j++)
				{

					Cell currentCell = currentRow.getCell(j);
					//System.out.print(currentCell.getStringCellValue() + "-");
					col.add(currentCell.getStringCellValue());
					row+=","+currentCell.getStringCellValue();
				}

				data.add(row);

			}
			input=new double[data.size()][data.get(0).split(",").length-1];
			for (int i=0;i<data.size();i++)
			{ 


				items = Arrays.asList(data.get(i).split(","));
				for (int k=0;k<items.size()-1;k++)
				{

					if (items.get(k).equals("Yes"))
					{
						input[i][k]=1;
					}
					else if (items.get(k).equals("No"))
					{
						input[i][k]=0;
					}
				}
			}
			

			List<String> keys = new ArrayList<String>(hmap.keySet());


			for (Map.Entry<String, double[]> entry : hmap.entrySet()) {

				int index = keys.indexOf(entry.getKey());

				ArrayList<Double> codeOfResult = new ArrayList<Double>();

				for (int i = 0; i < keys.size(); i++) {
					if (i == index) {
						codeOfResult.add(1.0);
					} else {

						codeOfResult.add(0.0);
					}
				}
				

				hmap.put(entry.getKey(), codeOfResult.stream().mapToDouble(i -> i).toArray());

			}
//Optional printing code for better understanding
//			for (String s : hmap.keySet()) {
//				System.out.println("Current key: " + s); 
//				for (double r : hmap.get(s)){
//					System.out.print(r);
//
//				}
//				System.out.println("");
//			}
			output = new double[data.size()][keys.size()];
			for (int m=0; m<data.size();m++) 
			{
				
				output[m] = hmap.get(Arrays.asList(data.get(m).split(",")).get(9));

			}
			//comment out the below line to see the data in the output array
			//printArray();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void  printArray()
	{
		if(output == null)
			System.out.println("its NULL");
		else{
			for (int i = 0; i < output.length; i++){ 
				for (int j = 0; j < output[0].length; j++){
					//System.out.print(output[i][j]+" "); 
				}
				//System.out.println();
			}
		}
	}
}

