package autoIT;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AutoItPractice {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String downloadPath = System.getProperty("user.dir");
		
		HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadPath);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("safebrowsing.enabled", true);
      
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
		WebDriver driver = new ChromeDriver(options);
		
		driver.get("https://www.ilovepdf.com/compress_pdf");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.id("pickfiles"))).click().build().perform();
		Thread.sleep(1000);
		Runtime.getRuntime().exec(downloadPath+"//AutoIT_Practice_PDF.exe");
		Thread.sleep(3000);
		a.moveToElement(driver.findElement(By.xpath("//button/span[1]"))).click().build().perform();
//		a.moveToElement(driver.findElement(By.cssSelector("#pickfiles"))).click().build().perform();
		Thread.sleep(5000);
		File file = new File(downloadPath + "//file-sample_150kB_compressed.pdf");
		Assert.assertTrue(file.exists());
		if (file.exists())
		{
			System.out.println("File Found");
			file.delete();
			System.out.println("File Deleted");
		}
		
		driver.quit();
	}

}
