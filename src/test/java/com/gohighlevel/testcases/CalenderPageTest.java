package com.gohighlevel.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.gohighlevel.base.TestBase;
import com.gohighlevel.helperClass.CommonActions;
import com.gohighlevel.helperClass.LoggerHelper;
import com.gohighlevel.pageObjects.CalenderPage;
import com.gohighlevel.pageObjects.LoginPage;

public class CalenderPageTest extends TestBase {
	
	private CommonActions commonActions;
	private LoginPage loginPage;
	private CalenderPage calenderPage;
	
	
	Logger log = LoggerHelper.getLogger(CalenderPageTest.class);
	
	public CalenderPageTest() {
		super();
	}
	
	
	public void loginToGoHignLevel(){
		commonActions = new CommonActions(driver,logger);
		loginPage = new LoginPage(driver, logger);
		calenderPage = new CalenderPage(driver, logger);
		loginPage.enterDetails(prop.getProperty("email"), prop.getProperty("password"));
		calenderPage = loginPage.clickOnSignInButton();
		
	}
	
	
	@Test
	public void verifyAddNewCalender() {
		
		log.info("*********** Starting test case execution : Verify wheather user is able to add a new calender **********");
		this.loginToGoHignLevel();
		calenderPage.addNewCalender();
		commonActions.verifyAssertTrue(calenderPage.addCalenderFlag(), 
				"User is able to add the calendar", 
				"User is not able to add the calendar");
	}

	
	@Test
	public void verifyBookAppointment() {
		
		log.info("*********** Starting test case execution : Verify wheather user is able to book a new appointment **********");
		this.loginToGoHignLevel();
		calenderPage.bookAnAppointment();
		commonActions.verifyAssertTrue(calenderPage.newAppointmentFlag(), 
				"User is able to book a new appointment", 
				"User is unable to book a new appointment");
		
	}
	
	@Test(enabled = false)
	public void verifyAddAppointmentToGoogleCalender() {
		
		log.info("*********** Starting test case execution : Verify wheather user is able to add a appointment in google calender **********");
		this.loginToGoHignLevel();
		calenderPage.addToGoogleCalender();
		
	}
	
	@Test
	public void verifyOptimizedForAvailability() {
		log.info("*********** Starting test case execution : Verify wheather add appointment using optimize for availability  **********");
		this.loginToGoHignLevel();
		calenderPage.optimizedForAvailability();
		commonActions.verifyAssertTrue(calenderPage.optimizedForAvailabilityFlag(), 
				"Appointment is getting booked for High Priority member", 
				"Unable to book appointment for high priority member");
		
	}
	
}
