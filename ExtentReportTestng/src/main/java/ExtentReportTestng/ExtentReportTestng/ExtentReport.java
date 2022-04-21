package ExtentReportTestng.ExtentReportTestng;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class ExtentReport {
	

	
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	
	@BeforeTest
	public void setExtent()
	{
		extent= new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
		extent.addSystemInfo("Host Name","Windows");
		extent.addSystemInfo("User Name","TestNG");
		extent.addSystemInfo("Enviroment","QA");
		
		
	}
	@AfterTest
	public void endReport()
	{
		
		extent.flush();
		extent.close();
	}
	@BeforeMethod
	public void chromeLaunch()
	{
		
		System.setProperty("webdriver.chrome.driver", "F:\\selenium projects\\seleniumtestngprojects\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		
		
	}
	@Test(priority=1)
	public void launchFacebook() throws InterruptedException
	{
		extentTest=extent.startTest("Facebook launch Test and getURL");
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("facebook");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='gNO89b']")).click();
		Thread.sleep(2000);
		System.out.println(driver.getCurrentUrl());


	}
	
	@Test(priority=2)
	public void facebookTitleTest()
	{
    extentTest=extent.startTest("verify Faceook Title Test");
    driver.navigate().to("https://www.facebook.com/");
	String title=driver.getTitle();
	System.out.println(title);
	Assert.assertEquals(title, "Facebook – log in or sign up");

}
	@Test(priority=3)
	public void FacebookImageTest()
	{
		extentTest=extent.startTest("verify Faceook image display test");
		driver.navigate().to("https://www.facebook.com/");
		boolean b=driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']")).isDisplayed();
		Assert.assertTrue(b);
		
	}
	@Test(priority=4)
	public void loginButtonTest() throws InterruptedException
	{
		extentTest=extent.startTest("verify login Button is enabled");
		driver.navigate().to("https://www.instagram.com/?hl=en");
		Thread.sleep(3000);
		boolean b=driver.findElement(By.xpath("//button[contains(@class,'sqdOP  L3NKy   y3zKF     ')]")).isEnabled();
		Assert.assertTrue(b,"login not enabled");
	}
	@Test(priority=4)
	public void getTitle() throws InterruptedException
	{
		extentTest=extent.startTest("Get Title");
		driver.navigate().to("https://www.instagram.com/?hl=en");
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		throw new SkipException("skipping");
	
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL,"Testcase failed is "+ result.getName());
			extentTest.log(LogStatus.FAIL, "Testcase failed is "+ result.getThrowable());
}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			extentTest.log(LogStatus.PASS, "Testcase passed is "+ result.getName());
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			extentTest.log(LogStatus.SKIP, "Testcase skipped is "+ result.getName());
			
		}
		extent.endTest(extentTest);
		driver.quit();
	
	}
	
}
