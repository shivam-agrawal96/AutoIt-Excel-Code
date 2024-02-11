package excelPractice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenRowsColumnConcept {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fis = new FileInputStream("D:\\Resume\\DemoWorkbook.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet demoSheet = wb.getSheetAt(0);
		int rowCount = demoSheet.getPhysicalNumberOfRows();
		System.out.println(rowCount);
		int columnCount = demoSheet.getRow(0).getLastCellNum();
		System.out.println(columnCount);
		for (int i = 0; i <rowCount; i++)
		{
			Row row = demoSheet.getRow(i);
			if (row.getCell(0).getStringCellValue().equalsIgnoreCase("Purchase"))
			{
				for (int j=0;j<columnCount; j++)
				{
					String Value = row.getCell(j).getStringCellValue();
					System.out.println(Value);
				}
			}
			
		}
		
		
	}

}
