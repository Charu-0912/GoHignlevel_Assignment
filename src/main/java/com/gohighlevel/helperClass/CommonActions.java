package com.gohighlevel.helperClass;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;



public class CommonActions {
	
	private WebDriverWait webDriverWait;
	private WebDriver driver;
	private ExtentTest logger;
	
	Logger log = LoggerHelper.getLogger(CommonActions.class);
	
	public CommonActions(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}
	
	public WebDriverWait waitUntilElementIsVisible(WebDriver driver , int sec , WebElement element) {
		webDriverWait = new WebDriverWait(driver, sec);
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
		return webDriverWait;
	}
	
	public WebDriverWait waitUntilVisibilityOfElementLocated(WebDriver driver , By by, int sec) {
		webDriverWait = new WebDriverWait(driver, sec);
		webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		return webDriverWait;
	}
	
	public WebDriverWait waitUntilElementTobeClickable(WebDriver driver , int sec, WebElement element)
	{
		webDriverWait = new WebDriverWait(driver, sec);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
		return webDriverWait;
	}
	
	public void waitFor(int sec) {
		try {
			Thread.sleep(sec);
		}catch (InterruptedException e) {
			log.info("Timeout exception occured" + e.getMessage());
		}
	}
	
	public boolean isElementPresent(WebElement element) {
		try {
			boolean flag = element.isDisplayed();
			System.out.println("Element"+ element.toString() + "Present on UI is:\t" + flag);
			return flag;		
		}catch (Exception e) {
			log.info("UI Element is not present and exception is occured " + e);
			return false;
		}
	}
	
	public void isElementEnabled(WebElement element) {
		element.isEnabled();
	}
	
	public void isElementSelected(WebElement element) {
		element.isEnabled();
	}
	
	public Boolean isElementPresent(WebDriver driver, int sec, By by) {
		try{
			waitUntilVisibilityOfElementLocated(driver, by, sec);
			WebElement element = driver.findElement(by);
			Boolean flag = element.isDisplayed();
			log.info("Element" + element.toString() +"Present on the UI is :/t" + flag);
			logger.info("Element" + element.toString() +"Present on the UI is :/t" + flag);
			return flag;
		}catch (Exception e) {
			log.info("Element is not present and exception is occured "+ e);
			logger.info("Element is not present and exception is occured "+ e);
			return false;
		}
	}
	
	public void click(WebElement element) {
			if(isElementPresent(element)) {
				element.click();
			}else {
				log.info("Element is not present and click on element is not performed");
				logger.info("Element is not present and click on element is not performed");
			}
	}
	
	public void RightClick(WebElement element) {
		  Actions actions = new Actions(driver);
		 
		  if(isElementPresent(element)) {
			  actions.contextClick(element).perform();
			}else {
				log.info("Element is not present and click on element is not performed");
				logger.info("Element is not present and click on element is not performed");
			}
	}
	
	
	public void clickbymouseHover(WebElement element) {
        try {
            if (isElementPresent(element)) {
                Actions builder = new Actions(driver);
                builder.moveToElement(element).build().perform();
                element.click();
                log.info("Button clicked for location by hovering on it=>" + element);
                logger.info("Button clicked for location by hovering on it=>" + element);
            } else {
                assertFail("Location doesn't found. Loc-" + element);
            }
        } catch (Exception e) {
            if (isElementPresent(element)) {
            	 element.click();
            } else {
                assertFail("Location doesn't found. Loc-" + element);
            }
        }
    }

	public void clickOnElementUsingJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		if (isElementPresent(element)) {
			executor.executeScript("arguments[0].click();", element);
		} else {
			log.info("Element is not present and click on element is not perform.");
			logger.info("Element is not present and click on element is not perform.");
		}
	}

	public void enterData(WebElement element, String data) {
		if (isElementPresent(element)) {
			element.sendKeys(data);
		} else {
			log.info("Element is not present and unable to send the data to element.");
			logger.info("Element is not present and unable to send the data to element.");
		}
	}
	
	public void enterDataByClearingTextBox(WebElement element, String data) {
		if (isElementPresent(element)) {
			element.clear();
			element.sendKeys(data);
		} else {
			log.info("Element is not present and unable to clear the element data." + element.toString());
			logger.info("Element is not present and unable to clear the element data." + element.toString());
		}
	}

	public void clear(WebElement element) {
		if (isElementPresent(element)) {
			element.clear();
		} else {
			log.info("Element is not present and unable to clear the element data." + element.toString());
			logger.info("Element is not present and unable to clear the element data." + element.toString());
		}
	}

	public String getText(WebElement element) {
		String text = "";
		if (isElementPresent(element)) {
			text = element.getText();
			log.info("Text is:\t" + text);
		} else {
			log.info("Element is not present and unable to get the text from element.");
			logger.info("Element is not present and unable to get the text from element.");
		}
		return text;
	}

	public String getTextFromElementUsingAttribute(WebElement element, String attribute, WebDriver driver) {
		if (isElementPresent(element)) {
			String text = element.getAttribute(attribute);
			return text;
		} else {
			assertFail("Unable to get text from element. -" + element);
			return null;
		}
	}

	public String getTitle() {
		String title = "";
		title = driver.getTitle();
		log.info("Title is:\t" + title);
		return title;
	}
	
	
	// Assertion Methods ................  
	public String passMsg(String msg) {
		return "PASS :" + msg;
	}

	public String failMsg(String msg) {
		return "FAIL :" + msg;
	}

	public void pass(String msg) {
		logger.info(passMsg(msg));
	}

	public void fail(String msg) {
		logger.info(failMsg (msg));
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void assertFail(String failMsg) {
		fail(failMsg);
		Assert.fail(failMsg);
	}

	public void verifyAssertEqual(String actual, String expected, String passMsg, String failMsg) {
		try {
			Assert.assertEquals(actual, expected, failMsg);
			System.out.println(passMsg(passMsg));
			pass(passMsg);
		} catch (Error e) {
			verifyAsserFailWithException(failMsg, e);
		}
	}


	public void scrollUP(JavascriptExecutor js) {
		js.executeScript("scroll(0, -250);");
	}

	public void scrollDown(JavascriptExecutor js) {
		js.executeScript("scroll(0, 250);");
	}

	public void scrollByLine(JavascriptExecutor js) {
		js.executeScript("window.scrollByLines(2)");
	}

	public void scrollDownToElementDisplayed(JavascriptExecutor js, WebElement element, WebDriver driver) {
		int count = 1;
		while (count <= 10) {
			if (!element.isDisplayed()) {
				scrollDown(js);
				waitFor(1000);
			}
			count++;
		}
	}

	public void scrollUpToElementDisplayed(JavascriptExecutor js, WebElement element, WebDriver driver) {
		int count = 1;
		while (count <= 10) {
			if (!element.isDisplayed()) {
				scrollUP(js);
				waitFor(1000);
			}
			count++;
		}
	}


	public void verifyAsserFailWithException(String msg, Error e) {
		System.out.println(failMsg(msg));
		 log.info(e.getMessage());
		fail(e.getMessage());
		Assert.fail(failMsg(msg));
	}

	public void verifyAssertFalse(boolean condition, String failMsg) {
		try {
			Assert.assertFalse(condition, failMsg);
		} catch (Error e) {
			verifyAsserFailWithException(failMsg, e);
		}
	}

	public void verifyAssertTrue(boolean condition, String passMsg, String failMsg) {
		try {
			Assert.assertTrue(condition, failMsg(failMsg));
			log.info(passMsg(passMsg));
		} catch (Error e) {
			verifyAsserFailWithException(failMsg, e);
		}

	}



}
