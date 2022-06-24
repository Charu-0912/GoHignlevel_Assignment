package com.gohighlevel.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.gohighlevel.base.TestBase;
import com.gohighlevel.helperClass.CommonActions;
import com.gohighlevel.helperClass.LoggerHelper;
import com.gohighlevel.pageObjects.LoginPage;

public class LoginPageTest extends TestBase {
	
	private CommonActions commonActions;
	private LoginPage loginPage;
	
	
	Logger log = LoggerHelper.getLogger(LoginPageTest.class);
	
	public LoginPageTest() {
		super();
	}
	
	
	
	@Test
	public void verifyLogin() {
		
		commonActions = new CommonActions(driver,logger);
		loginPage = new LoginPage(driver, logger);
		loginPage.loginToGoHighLevel(prop.getProperty("email"), prop.getProperty("password"));
		commonActions.verifyAssertTrue(loginPage.getLoginFlag(), 
				"User is able to login successfully.", 
				"User is not able to login");
		
	}
	
	
}
