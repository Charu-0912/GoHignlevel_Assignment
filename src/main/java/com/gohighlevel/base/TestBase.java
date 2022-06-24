package com.gohighlevel.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.gohighlevel.util.ExtentReportUtility;

public class TestBase{
		
		public static WebDriver driver;
		public static Properties prop;
		public static ExtentReports extent;
		ExtentReportUtility extentReportUtility;;
		public ExtentTest logger;
		
		
		public TestBase() {
		}
		
		@BeforeClass
		public void beforeclass() {
			try {
				prop = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/gohighlevel/config/Config.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@BeforeMethod
		public static void initialization() {
			String browsername = prop.getProperty("browser");
			if(browsername.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:/Anup/chromedriver_win32/chromedriver.exe");
				
				
				ChromeOptions options = new ChromeOptions();
				HashMap<String, Integer> contentSettings = new HashMap<String, Integer>();
				HashMap<String, Object> profile = new HashMap<String, Object>();
				HashMap<String, Object> prefs = new HashMap<String, Object>();
				
				contentSettings.put("notifications", 1);
				profile.put("managed_default_content_settings", contentSettings);
				prefs.put("profile", profile);
				options.setExperimentalOption("prefs", prefs);
				
				driver = new ChromeDriver(options);
				
				
			}
			
			driver.manage().window().maximize();
			driver.get(prop.getProperty("URL"));
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		}
		
		@AfterMethod
		public void tearDown() {
			
			//driver.close();
			driver.quit();
		}

}
