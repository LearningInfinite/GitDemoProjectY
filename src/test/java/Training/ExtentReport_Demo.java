package Training;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReport_Demo {
	ExtentReports extent;
	@BeforeTest
	public void config()
	{
		//ExtentReports , ExtentSparkReporter
		
		
/* Provide the path to store our report. Let say we want to create report by creating one new folder inside this project itself. 
So to create the path dynamically through we can say system.getProperty and user.dir- this will give the project path dynamically
 no matter in which system we are working so using "user.dir" project path will be generated automatically which is 
 "D:\Automation\ExtentReport" So under this path we have to create one more path */
				String path =System.getProperty("user.dir")+ "\\reports\\index.html"; 
				
/*From this a reports folder will be created under project in that we will see the index file.This class expects a path where 
 * report should be created so, this is responsible for creating report. So basically ExtentSparkReporter reporter is responsible
 *  to create one html file and do some configuration */
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				
// Next we will do some configurations and reporter object will be responsible for all configuration in our report
				 reporter.config().setReportName("Web Automation Results");  // This will set the report name
				 reporter.config().setDocumentTitle("Test Results");
				
/*Now we have to create another object of ExtentReports class and this will be our main class. This is the one which is responsible
 to drive all our reporting execution. As this is the main class we have to attach report whatever we have created using 
 ExtentSparkReporter because this main class is responsible to create and consolidate all our test execution */
				 extent = new ExtentReports();
				 extent.attachReporter(reporter);
				 
// Here we have to pass the object of ExtentSparkReporter so automatically our main class will have knowledge of object
				 
			     extent.setSystemInfo("Tester", "Sonam");
			    
//Now how to use main class(Extent) object in below class i.e. how our test case report will be attached to this main class variable 
			}
			

		    @Test
			public void initialDemo()
			{
		    	
 /*Now how to use main class(Extent) object in below class i.e. how our test case report will be attached to this main class
  variable. As the extent object is local to the config method and we can not directly access this object outside this method and
  to make this happen we have to declare ExtentReports variable as global level so, now we will be able to access extent object in
  subsequent methods. Here we have to clearly specify that when this test case is being started executing then we need to create a
  test using extent object.WE have to explicitly say to create one new test in the reporting file. Once we create test then
  automatically extent variable will keep on monitoring the result status of the test. If test case pass then content will go back
  and mark it as pass and similarly for fail we need not worry about we just need to create test so that extent object is aware
  that there is one test case which is being executed so that it can create one report. so below step is mandatory in every test */	
	
/* When we create test then automatically one object is created for our complete test method so we can catch that object using
 below code. So EObj1 is the object comes from extent class so whenever we create test extent report will create one object which 
 is unique to our test method. So this EObj1 object will be responsible to listen and report all the happenings back to the extent
 report.  Now we can use this EObj1 object in our further steps if we want to tweak the reporting for that specific test case   */		    	
		    	
		    	ExtentTest testObj1 =extent.createTest("initialDemo");
				System.setProperty("webdriver.chrome.driver", "C://Work//chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				//WebDriver driver = new ChromeDriver();
				driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
				System.out.println(driver.getTitle());
				System.out.println("Data1");
				System.out.println("Data2");
				System.out.println("Data3");
				System.out.println("Data4");
				driver.close();
				//this will automatically attach the screenshot to the specific test method 
				//testObj1.addScreenCaptureFromBase64String(s);
			
				//Below method is used to fail your test case as we are explicitly failing our test
				testObj1.fail("Result do nor match");
				
				
				
				
//once our test is done at the end we have to specify extent.flush as it will specify test is done and it will stop monitoring
//and this is mandatory to specify. If we have 5 test cases then we have to specify after 5th test case. If we don't
// specify it will still be in listening mode and after flush it will update the status with pass or fail
				extent.flush();
			}
		}
