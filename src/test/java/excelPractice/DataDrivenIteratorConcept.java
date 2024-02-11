package excelPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenIteratorConcept {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fis = new FileInputStream("D:\\Resume\\DemoWorkbook.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		System.out.println(workbook.getNumberOfSheets());
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		System.out.println(sheet.getPhysicalNumberOfRows());
		Iterator<Row> row = sheet.iterator(); // Sheet is a collection of rows
		Row firstRow = row.next();
		Iterator<Cell> cellit = firstRow.cellIterator(); // Row is a collection of cells
		int k=0;
		int column = 0;
		while(cellit.hasNext()) 
			{
				Cell cell = cellit.next();
				String Value = cell.getStringCellValue();
				if (Value.equalsIgnoreCase("TestCases"))
				{
					column = k;
				}
				
				k++;
			}
		System.out.println(column);
		
		while(row.hasNext()) 
		{
			Row r = row.next();
			if (r.getCell(column).getStringCellValue().equalsIgnoreCase("Purchase"))
			{
				Iterator<Cell> cv = r.cellIterator();
				while(cv.hasNext()) 
					{
					    String Value = cv.next().getStringCellValue();
						System.out.println(Value);
			        }
	     	} 

		}
		
		System.out.println("TestCase column is column " + column);
		
	}
			
}
