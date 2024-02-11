package javaPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

class ContentVerification {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		String content = null;
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Shivam Agarwal\\Downloads\\Staging URLs to Noindex.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook =  new XSSFWorkbook(fis);
		SoftAssert softAssert = new SoftAssert();
 		XSSFSheet redirectSheet = workbook.getSheet("Sheet1");
		int rowCount = redirectSheet.getPhysicalNumberOfRows();
		System.out.println("Total Number of Rows in Sheet = "+rowCount);
		int Serial = 1;
		
		for(int i = 1; i<rowCount; i++)
		{
			Row row = redirectSheet.getRow(i); 
			String URL = row.getCell(0).getStringCellValue();
			try
			{
				driver.get(URL);
				content = driver.findElement(By.xpath("//meta[@name='robots']")).getAttribute("content");
			}
			catch(Exception e)
			{
				System.out.println(Serial+1+" "+ e.getMessage());
			}
//			softAssert.assertEquals(content, "noindex, nofollow");
			boolean flag = content.equalsIgnoreCase("noindex, nofollow");
			softAssert.assertTrue(flag);
			if (flag == false)
			{
				System.out.println(Serial+ 1 +" "+ URL);
			}
			
			Serial++;
		}
		
		driver.quit();
		softAssert.assertAll();
	}

}



//driver.get("https://staging.couchbase.com/blog/windows-10-and-couchbase-4-1/");
//String content = driver.findElement(By.xpath("//meta[@name='robots']")).getAttribute("content");
//softAssert.assertEquals(content, "noindex, nofollow");
