package seleniumdemo.seleniumdemo;





import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.page.model.Frame;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

public class differentTestTypes {
static WebDriver driver;
static JavascriptExecutor js;
Frame f;
static Alert alert;
Action seriesOfActions;
static ExtentReports extent;
@BeforeTest
public void SetExtent()
{
	
		extent= new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport1.html",true);
		extent.addSystemInfo("Host Name","shashi window");
		extent.addSystemInfo("User Name","shashi TestNG");
		extent.addSystemInfo("Enviroment","testing");
		
}
@AfterTest
public void endreport()
{
	extent.flush();
	extent.close();
}
	@BeforeMethod
	public void chromeStart()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\selenium projects\\seleniumtestngprojects\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Dimension size=driver.manage().window().getSize();
		System.out.println(size);
		driver.get("https://www.google.com/");
	}
	@Test(priority=1)
	public void googleTitleTest()
	{
		js=((JavascriptExecutor)driver);
		driver.navigate().refresh();
		String title=js.executeScript("return document.title;").toString();
		System.out.println(title);
		
		
	}
	@Test(priority=2)
	public void facebookTest() throws InterruptedException
	{
		js=((JavascriptExecutor)driver);
		WebElement element=driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
		WebElement element1=driver.findElement(By.xpath("//input[@class='gNO89b']"));
		js.executeScript("arguments[0].value='facebook';", element);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", element1);
	}
	
	@Test(priority=3)
	public void websitePopups() throws InterruptedException
	{
		
		driver.navigate().to("https://www.adobe.com/acrobat/pdf-reader.html");
		WebElement header= driver.findElement(By.tagName("div"));
		String headerValue= header.getText();
		System.out.println("Header of pop up is :"+headerValue);
		WebElement element3=driver.findElement(By.linkText("Continue to India"));
		js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();",element3);
		Thread.sleep(5000);
		
		
		
	}
	@Test(priority=4)
	public void windowScroll() throws InterruptedException
	{
		js=((JavascriptExecutor)driver);
		driver.navigate().to("https://www.figma.com/");
		Thread.sleep(2000);
		WebElement element4=driver.findElement(By.xpath("//a[contains(text(),'Meet FigJam →')]"));
		js.executeScript("arguments[0].scrollIntoView(true);",element4);
		System.out.println(element4.getText());
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.visibilityOf(element4)).click();
	    Thread.sleep(5000);
	}
	
	@Test(priority=5)
	public void loginnotenabled() throws InterruptedException
	{
		driver.navigate().to("https://www.instagram.com/?hl=en");
		Thread.sleep(3000);
		boolean b=driver.findElement(By.xpath("//button[contains(@class,'sqdOP  L3NKy   y3zKF     ')]")).isEnabled();
		Assert.assertFalse(b,"login not enabled");
	}
	@Test(priority=6)
	public void loginenabled() throws InterruptedException
	{
		driver.navigate().to("https://www.instagram.com/?hl=en");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[contains(@name,'username')]")).sendKeys("abcddfse");
		driver.findElement(By.xpath("//input[contains(@name,'password')]")).sendKeys("bkbksdfbkdjs");
		boolean b=driver.findElement(By.xpath("//button[contains(@class,'sqdOP  L3NKy   y3zKF     ')]")).isEnabled();
		Assert.assertTrue(b);
	}
	
	@Test(priority=7)
	public void screenshot() throws IOException
	{
		File scr=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File("C:\\Users\\shashidhar\\Documents\\Corel\\scrnshot.jpg"));
	}
	@Test(priority=8)
	public  void mouseCursorTest() throws InterruptedException
	{
		Thread.sleep(2000);
		WebElement element=driver.findElement(By.xpath("//a[@class='gb_d']"));
		
		Actions act1 =new Actions(driver);
		act1.moveToElement(element).click().build().perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign in")).click();
		
		Thread.sleep(2000);
			
			WebElement element1=driver.findElement(By.xpath("//input[@name='identifier']"));
			Actions act = new Actions(driver);
	    try {
			Action seriesOfActions = act
			         .moveToElement(element1)
			         .click()
			         .sendKeys(element1, "jashashidhar@gmail.com")
			     	 .contextClick()
			         .build();
			seriesOfActions.perform();
			Thread.sleep(5000);
	    }
	    catch(StaleElementReferenceException e)
	    {
	    	element1=driver.findElement(By.xpath("//input[@name='identifier']"));
	    
	    }
		
		
	} 
	@Test(priority=9)
	public void explicitewait()
	{
		driver.navigate().to("https://en-gb.facebook.com/business/ads");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Create an Ad")));
	    driver.findElement(By.linkText("Create an Ad")).click();
	    
	}
	@Test(priority=10)
	public void elementHighlight() throws InterruptedException
	{
		js=((JavascriptExecutor)driver);
		WebElement ele=driver.findElement(By.linkText("Sign in"));
		WebElement ele1=driver.findElement(By.linkText("Gmail"));
		js.executeScript("arguments[0].style.border='5px solid black'", ele);
		Thread.sleep(3000);
		js.executeScript("arguments[0].style.background='red'", ele1);
		
	}
	@Test(priority=11)
	public void dropdrowntest() throws InterruptedException
	{
		driver.navigate().to("http://seleniumpractise.blogspot.in/2016/08/bootstrap-dropdown-example-for-selenium.html");
		driver.findElement(By.id("menu1")).click();
		Thread.sleep(2000);
		List<WebElement> list=driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li//a"));
		js=((JavascriptExecutor)driver);
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getSize());
			if(list.get(i).getText().equals("HTML"))
			{
				js.executeScript("arguments[0].style.background='red'",list.get(i));
			break;
			}
		
		}
		
		
	}
	@AfterMethod
	public void close()
	{
		driver.quit();
	}
	
}
