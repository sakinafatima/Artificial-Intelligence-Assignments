package machineLearning;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import org.apache.poi.EncryptedDocumentException;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;


	public class DataSetUpdate {

		static FileOutputStream fileOut = null;

		public static void  updatedata(double[] Averageinput, String response) throws EncryptedDocumentException, IOException
		{
			File excel = new File ("./Data/ticketsUpdated.xlsx");
			FileInputStream fis = new FileInputStream(excel);

			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			//inserting a new row after last row in the data
			Row row = sheet.createRow(sheet.getLastRowNum()+1);

			for (int x=0; x<Averageinput.length;x++)
			{
				if (Averageinput[x]==0.0)
				{
					row.createCell(x).setCellValue("No");
				}
				if (Averageinput[x]==1.0)
				{
					row.createCell(x).setCellValue("Yes");
				}

			}
			//adding the name of user defined response team in the last column of the data
			row.createCell(Averageinput.length).setCellValue(response);
			// Write the output to a file
			FileOutputStream out = new FileOutputStream("./Data/ticketsUpdated.xlsx");
			wb.write(out);
			out.close();
		}

	}
