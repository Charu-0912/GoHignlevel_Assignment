package com.gohighlevel.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.gohighlevel.helperClass.CommonActions;
import com.gohighlevel.helperClass.LoggerHelper;

public class LoginPage {

	private WebDriver driver;
	private CommonActions commonActions;
	private ExtentTest logger;

	Logger log = LoggerHelper.getLogger(LoginPage.class);

	@FindBy(xpath = "//input[@id='email']")
	WebElement EmailField;

	@FindBy(xpath = "//input[@id='password']")
	WebElement PasswordField;

	@FindBy(xpath = "//button[contains(text(),'Sign in')]")
	WebElement SignInButton;
	
	@FindBy(xpath = "//div[@class='avatar_img']")
	WebElement UserIcon;
	
	@FindBy(xpath = "//img[@class='object-contain agency-logo']")
	WebElement AgencyLogo;

	public LoginPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		commonActions = new CommonActions(driver, logger);
		PageFactory.initElements(driver, this);
	}

	public CalenderPage loginToGoHighLevel(String email, String password) {
		
		commonActions.waitUntilElementIsVisible(driver, 60, EmailField);
		commonActions.enterData(EmailField, email);
		log.info("Entered Email : " + email);
		
		commonActions.waitUntilElementIsVisible(driver, 60, PasswordField);
		commonActions.enterData(PasswordField, password);
		log.info("Entered Password : " + password);
		
		commonActions.waitUntilElementIsVisible(driver, 60, SignInButton);
		commonActions.click(SignInButton);
		log.info("Clicked on the Sign in button");
		return new CalenderPage(driver, logger);

	}
	
	public void enterDetails(String email, String password) {
		
		commonActions.waitUntilElementIsVisible(driver, 60, EmailField);
		commonActions.enterData(EmailField, email);
		log.info("Entered Email : " + email);
		
		commonActions.waitUntilElementIsVisible(driver, 60, PasswordField);
		commonActions.enterData(PasswordField, password);
		log.info("Entered Password : " + password);
	}
	
	public CalenderPage clickOnSignInButton() {
		
		commonActions.waitUntilElementIsVisible(driver, 60, SignInButton);
		commonActions.click(SignInButton);
		log.info("Clicked on the Sign in button");
		return new CalenderPage(driver, logger);

	}
	

	public boolean getLoginFlag() {
			commonActions.waitUntilElementIsVisible(driver, 60, UserIcon);
		if (commonActions.isElementPresent(UserIcon)) {
			return true;
		} else {
			log.info("Expand-collapse button on app manager is not present: " + UserIcon.toString());
			logger.info("Expand-collapse on app manager is not present: " + UserIcon.toString());
			return false;
		}
	}

}
