package com.TestNGDependency_dependsOnMethods;

import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.Track;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


public class Demo_DependsOnMethod_in_TestNG 
{
	WebDriver driver;
	
	
	
	
 @Test()
 public void AlertIsPresent()
 {
	 driver.get("http://only-testing-blog.blogspot.com/2014/01/new-testing.html");
	 driver.findElement(By.xpath("//input[@name='fname']")).sendKeys("My Name");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		try
		{
		
			wait.until(ExpectedConditions.alertIsPresent());
			if(driver.switchTo().alert()!=null)
			{
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
 }
	
  @Test(dependsOnMethods="Drag_and_Drop")
  public void Simple_Alert() 
  {
	  driver.get("https://www.toolsqa.com/handling-alerts-using-selenium-webdriver/");
	  driver.findElement(By.xpath("//div[@id='cookie-law-info-bar']//a[contains(text(),'ACCEPT')]")).click();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//button[contains(text(),'Simple Alert')]")).click();
	  WebDriverWait wait = new WebDriverWait(driver, 1000);
		try
		{
		
			wait.until(ExpectedConditions.alertIsPresent());
			if(driver.switchTo().alert()!=null)
			{
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				Thread.sleep(2000);
				
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
  }
  
  @Test()
  public void Drag_and_Drop()
  {
		driver.get("https://jqueryui.com/droppable/");
		driver.switchTo().frame(0);
		WebElement source=driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement destination=driver.findElement(By.xpath("//div[@id='droppable']"));
		Actions action=new Actions(driver);
		action.dragAndDrop(source, destination).build().perform();
  }
  
  @Test()
  public void Dynamically_Identify_Tables_Data()
  {
	  	driver.get("https://www.grocerycrud.com/documentation/tutorial_basic_methods/");
		driver.switchTo().frame(0);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		WebElement element=driver.findElement(By.xpath("//div[@class='switch-versions-container']//select[@id='switch-version-select']"));
		Select selectobj=new Select(element);
		selectobj.selectByValue("/demo/employees_example/datatables");
		
		
		String xpath1="//div[@class='dataTables_wrapper']/table//tr[";
		String xpath2="]/td[1]";
		
		
      
      	List<WebElement> rows=driver.findElements(By.xpath("//div[@class='dataTables_wrapper']/table/tbody/tr/td"));
      	System.out.println("No.Of Rows in a table:"+rows.size());
      	
		
     
			List<WebElement> col=driver.findElements(By.xpath("//div[@class='dataTables_wrapper']/table/thead/tr/th"));
		    System.out.println("No.Of Column in a table:"+col.size());
		   
		    
		    for(int i=1;i<=rows.size();i++)
			{
				String name = driver.findElement(By.xpath(xpath1+i+xpath2)).getText();
				System.out.println(name);
				if(name.contains("Fixter"))
				{
					for(int j=1;j<=col.size();j++)
					{
					
					String action=driver.findElement(By.xpath("//div[@class='dataTables_wrapper']/table//tr["+i+"]/td["+j+"]")).getText();
					System.out.println(action);
						if(action.contains("View"))
						{
							driver.findElement(By.xpath("//div[@class='dataTables_wrapper']/table//tr["+i+"]/td["+j+"]/a")).click();
						
						}
					}
					break;
				}	
				
			}

  }
  @BeforeTest()
  public void beforeTest()
  {
	  	System.out.println("hai this is @BeforeTest Annotation");
	  	System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments("---disable-notification---");
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
  }

  @AfterTest()
  public void afterTest()
  {
	driver.close();
  }

}
