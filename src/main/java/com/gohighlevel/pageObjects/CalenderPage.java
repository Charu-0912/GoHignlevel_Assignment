package com.gohighlevel.pageObjects;

import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.gohighlevel.base.TestBase;
import com.gohighlevel.helperClass.BrowserHelper;
import com.gohighlevel.helperClass.CommonActions;
import com.gohighlevel.helperClass.LoggerHelper;
import com.gohighlevel.util.RandomGeneratorUtility;

public class CalenderPage extends TestBase {

	private WebDriver driver;
	private CommonActions commonActions;
	private BrowserHelper browserHelper;
	private RandomGeneratorUtility randomGenerator = new RandomGeneratorUtility();
	Random random = new Random();

	Logger log = LoggerHelper.getLogger(CalenderPage.class);

	@FindBy(xpath = "//span[contains(text(),'Calendars')]")
	WebElement CalendersButton;

	@FindBy(xpath = "//button[@id='pg-calendar-v2__drpdwn--calendars-select__BV_toggle_']")
	WebElement MyCalenderButton;

	@FindBy(xpath = "//button[@id='pg-calendar-v2__drpdwn--calendars-filters__BV_toggle_']")
	WebElement AllButton;

	@FindBy(xpath = "//button[@id='pg-appt__btn--appt-add']")
	WebElement BookAppointmentButton;

	@FindBy(xpath = "//span[normalize-space()='Calendar Settings']")
	WebElement CalenderSettignsButton;

	@FindBy(xpath = "//span[normalize-space()='Calendar Settings']")
	WebElement CalenderFlag;

	@FindBy(xpath = "//div[@class='modal-header pb-0']//button[@aria-label='Close']")
	WebElement ClosePopupBbutton;

	@FindBy(xpath = "//input[@placeholder='Add Calendar Name']")
	WebElement CalenderNameTextBox;

	@FindBy(xpath = "//textArea[@placeholder='Add Calendar Description']")
	WebElement CalenderDescriptionTextBox;

	@FindBy(xpath = "//input[@placeholder='Enter Calendar Slug']")
	WebElement CalenderSlugTextBox;

	@FindBy(xpath = "//span[contains(text(),'Calendar URL')]")
	WebElement CalenderURLTag;

	@FindBy(xpath = "//div[@class='slug-unique tick-icon']")
	WebElement SlugCheckmark;

	@FindBy(xpath = "//button[@id='cmp-calmodal__button--save']")
	WebElement SaveAndContinueButton;

	@FindBy(xpath = "//button[@id='cmp-calmodal__button--save']")
	WebElement CompleteButton;

	@FindBy(xpath = "//textarea[@placeholder='Enter message you want to show in thank you page after the booking form submitted']")
	WebElement CustomMessageTextBox;

	@FindBy(xpath = "//input[@placeholder='Appointments Per   ']")
	WebElement AppointmentsPerDayTextBox;

	@FindBy(xpath = "//img[@class='object-contain agency-logo']")
	WebElement AgencyLogo;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/section[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[2]/div[2]/div[1]/div[4]/button[1]")
	WebElement BookAppointmentLink;

	@FindBy(xpath = "(//td[@class='vdpCell selectable'])[2]")
	WebElement NextDayButton;

	@FindBy(xpath = "//button[@class='btn btn-schedule']")
	WebElement ContinueButton;

	@FindBy(xpath = "//input[@id='first_name']")
	WebElement FirstNameField;

	@FindBy(xpath = "//input[@id='last_name']")
	WebElement LastNameField;

	@FindBy(xpath = "//input[@id='phone']")
	WebElement PhoneField;

	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement EmailField;

	@FindBy(xpath = "//button[@class='btn btn-schedule']")
	WebElement ScheduleMeetingButton;

	@FindBy(xpath = "//h5[@class='confirmation-message']")
	WebElement ConfirmationMessage;

	@FindBy(xpath = "//span[contains(text(), 'Add to Google Calendar')]")
	WebElement AddToGoogleCalenderButton;

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	WebElement SaveToGoogleCalenderButton;

	@FindBy(xpath = "(//span[contains(text(), 'Calendar')])[2]")
	WebElement GoogleCalenderTag;

	@FindBy(xpath = "//label[contains(text(), ' Team Member 1 ')]/..//select[@class='selectpicker form-control']")
	WebElement DropDownButton;

	@FindBy(xpath = "//label[contains(text(), ' Team Member 1 ')]/..//input[@class='form-control']")
	WebElement MeetingLocationTextBox;

	@FindBy(xpath = "//h5[contains(text(), 'QA') and @class['modal-title']]")
	WebElement CalenderTitleTag;

	@FindBy(xpath = "//div[@class='p-b booking-info--location']//div")
	WebElement MeetingLocationTag;

	public CalenderPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		commonActions = new CommonActions(driver, logger);
		browserHelper = new BrowserHelper(driver);
		PageFactory.initElements(driver, this);
	}

	public void addNewCalender() {

		commonActions.waitUntilElementIsVisible(driver, 60, CalendersButton);
		commonActions.click(CalendersButton);
		log.info("Clicked on the calenders button");

		commonActions.waitUntilElementIsVisible(driver, 60, CalenderSettignsButton);
		commonActions.click(CalenderSettignsButton);
		log.info("Clicked on the calenders settings button");

		List<WebElement> list = driver
				.findElements(By.xpath("//div[@class='p-3 card-header card-header--provider d-flex']/span"));

		for (int i = 0; i < list.size(); i++) {

			String title = list.get(i).getText();

			if (title.equalsIgnoreCase(prop.getProperty("Team"))) {

				commonActions.waitFor(2000);
				driver.findElements(By.xpath("//button[@class='mr-2 btn btn-success p1']")).get(i).click();
				log.info("Clicked on add button....");

				break;
			}

		}

		commonActions.waitUntilElementIsVisible(driver, 60, ClosePopupBbutton);
		commonActions.waitUntilElementIsVisible(driver, 60, CalenderNameTextBox);
		commonActions.enterData(CalenderNameTextBox, prop.getProperty("CalenderName"));

		commonActions.waitUntilElementIsVisible(driver, 60, CalenderDescriptionTextBox);
		commonActions.enterData(CalenderDescriptionTextBox, prop.getProperty("CalenderDescription"));

		commonActions.waitUntilElementIsVisible(driver, 60, CalenderSlugTextBox);
		commonActions.enterData(CalenderSlugTextBox, getRandomSlug());
		commonActions.click(CalenderURLTag);

		commonActions.waitUntilElementTobeClickable(driver, 60, SaveAndContinueButton);
		commonActions.waitUntilElementIsVisible(driver, 60, SlugCheckmark);
		commonActions.click(SaveAndContinueButton);
		log.info("Clicked on save and continue button");

		commonActions.waitUntilElementIsVisible(driver, 60, AppointmentsPerDayTextBox);
		commonActions.enterData(AppointmentsPerDayTextBox, prop.getProperty("AppointmentsPerDay"));
		commonActions.waitUntilElementTobeClickable(driver, 60, SaveAndContinueButton);
		commonActions.click(SaveAndContinueButton);
		log.info("Clicked on save and continue button");

		commonActions.waitFor(2000);
		commonActions.waitUntilElementIsVisible(driver, 60, CustomMessageTextBox);
		commonActions.clear(CustomMessageTextBox);
		commonActions.enterData(CustomMessageTextBox, prop.getProperty("CalenderName"));
		commonActions.waitUntilElementTobeClickable(driver, 60, CompleteButton);
		commonActions.click(CompleteButton);
		log.info("Clicked on complete button");

		commonActions.waitUntilElementIsVisible(driver, 60, AgencyLogo);
		log.info("Calender is added successfully");
		commonActions.waitFor(2000);

	}

	public void addNewCalenderUsingHighPriority() {

		commonActions.waitUntilElementIsVisible(driver, 60, CalendersButton);
		commonActions.click(CalendersButton);
		log.info("Clicked on the calenders button");

		commonActions.waitUntilElementIsVisible(driver, 60, CalenderSettignsButton);
		commonActions.click(CalenderSettignsButton);
		log.info("Clicked on the calenders settings button");

		List<WebElement> list = driver
				.findElements(By.xpath("//div[@class='p-3 card-header card-header--provider d-flex']/span"));

		for (int i = 0; i < list.size(); i++) {

			String title = list.get(i).getText();

			if (title.equalsIgnoreCase(prop.getProperty("Team"))) {

				commonActions.waitFor(2000);
				driver.findElements(By.xpath("//button[@class='mr-2 btn btn-success p1']")).get(i).click();
				log.info("Clicked on add button....");
				log.info("Clicked on add button....");

				break;
			}

		}

		commonActions.waitUntilElementIsVisible(driver, 60, ClosePopupBbutton);

		Select sel = new Select(DropDownButton);
		sel.selectByVisibleText("High Priority");
		commonActions.waitFor(2000);

		commonActions.waitUntilElementIsVisible(driver, 60, MeetingLocationTextBox);
		commonActions.enterData(MeetingLocationTextBox, prop.getProperty("Place"));

		commonActions.waitUntilElementIsVisible(driver, 60, CalenderNameTextBox);
		commonActions.enterData(CalenderNameTextBox, prop.getProperty("CalenderName"));

		commonActions.waitUntilElementIsVisible(driver, 60, CalenderDescriptionTextBox);

		commonActions.enterData(CalenderDescriptionTextBox, prop.getProperty("CalenderDescription"));

		commonActions.waitUntilElementIsVisible(driver, 60, CalenderSlugTextBox);
		commonActions.enterData(CalenderSlugTextBox, getRandomSlug());
		commonActions.click(CalenderURLTag);

		commonActions.waitUntilElementTobeClickable(driver, 60, SaveAndContinueButton);
		commonActions.waitUntilElementIsVisible(driver, 60, SlugCheckmark);
		commonActions.click(SaveAndContinueButton);
		log.info("Clicked on save and continue button");

		commonActions.waitUntilElementIsVisible(driver, 60, AppointmentsPerDayTextBox);
		commonActions.enterData(AppointmentsPerDayTextBox, prop.getProperty("AppointmentsPerDay"));
		commonActions.waitUntilElementTobeClickable(driver, 60, SaveAndContinueButton);
		commonActions.click(SaveAndContinueButton);
		log.info("Clicked on save and continue button");

		commonActions.waitFor(2000);
		commonActions.waitUntilElementIsVisible(driver, 60, CustomMessageTextBox);
		commonActions.clear(CustomMessageTextBox);
		commonActions.enterData(CustomMessageTextBox, prop.getProperty("CalenderName"));
		commonActions.waitUntilElementTobeClickable(driver, 60, CompleteButton);
		commonActions.click(CompleteButton);
		log.info("Clicked on complete button");

		commonActions.waitUntilElementIsVisible(driver, 60, AgencyLogo);
		log.info("Calender is added successfully");
		commonActions.waitFor(2000);

	}

	public void bookAnAppointment() {

		addNewCalender();

		commonActions.waitUntilElementTobeClickable(driver, 60, BookAppointmentLink);
		commonActions.click(BookAppointmentLink);
		browserHelper.SwitchToWindow(1);

		commonActions.waitUntilElementIsVisible(driver, 60, NextDayButton);
		commonActions.click(NextDayButton);

		commonActions.waitFor(2000);

		List<WebElement> timeSlots = driver.findElements(By.xpath("//li[@class='widgets-time-slot']/span"));

		for (int i = 0; i < timeSlots.size(); i++) {

			String time = timeSlots.get(i).getText();
			log.info(time);
			if (time.equalsIgnoreCase(prop.getProperty("SlotTime2"))) {

				timeSlots.get(i).click();
				break;
			}
		}

		commonActions.waitUntilElementTobeClickable(driver, 60, ContinueButton);
		commonActions.click(ContinueButton);
		commonActions.waitFor(2000);

		commonActions.waitUntilElementIsVisible(driver, 60, FirstNameField);
		commonActions.enterData(FirstNameField, prop.getProperty("FirstName"));

		commonActions.waitUntilElementIsVisible(driver, 60, LastNameField);
		commonActions.enterData(LastNameField, prop.getProperty("LastName"));

		commonActions.waitUntilElementIsVisible(driver, 60, PhoneField);
		commonActions.enterData(PhoneField, getRandomPhNumber());

		commonActions.waitUntilElementIsVisible(driver, 60, EmailField);
		commonActions.enterData(EmailField, prop.getProperty("TestEmail"));

		commonActions.waitUntilElementTobeClickable(driver, 60, ScheduleMeetingButton);
		commonActions.click(ScheduleMeetingButton);
		commonActions.waitFor(2000);

	}

	public void addToGoogleCalender() {
		bookAnAppointment();
		commonActions.waitUntilElementTobeClickable(driver, 60, AddToGoogleCalenderButton);
		commonActions.click(AddToGoogleCalenderButton);
		browserHelper.SwitchToWindow(2);
		commonActions.waitUntilElementTobeClickable(driver, 60, SaveToGoogleCalenderButton);
		commonActions.click(SaveToGoogleCalenderButton);
	}

	public void optimizedForAvailability() {
		addNewCalenderUsingHighPriority();
		commonActions.waitUntilElementTobeClickable(driver, 60, BookAppointmentLink);
		commonActions.click(BookAppointmentLink);
		browserHelper.SwitchToWindow(1);

		commonActions.waitUntilElementIsVisible(driver, 60, NextDayButton);
		commonActions.click(NextDayButton);

		commonActions.waitFor(2000);
		
		

		List<WebElement> timeSlots = driver.findElements(By.xpath("//li[@class='widgets-time-slot']/span"));

		for (int i = 0; i < timeSlots.size(); i++) {

			String time = timeSlots.get(i).getText();
			log.info(time);
			if (time.equalsIgnoreCase(prop.getProperty("SlotTime"))) {

				timeSlots.get(i).click();
				break;
			}
		}

		commonActions.waitUntilElementTobeClickable(driver, 60, ContinueButton);
		commonActions.click(ContinueButton);
		commonActions.waitFor(2000);

		commonActions.waitUntilElementIsVisible(driver, 60, FirstNameField);
		commonActions.enterData(FirstNameField, prop.getProperty("FirstName"));

		commonActions.waitUntilElementIsVisible(driver, 60, LastNameField);
		commonActions.enterData(LastNameField, prop.getProperty("LastName"));

		commonActions.waitUntilElementIsVisible(driver, 60, PhoneField);
		commonActions.enterData(PhoneField, getRandomPhNumber());

		commonActions.waitUntilElementIsVisible(driver, 60, EmailField);
		commonActions.enterData(EmailField, prop.getProperty("TestEmail"));

		commonActions.waitUntilElementTobeClickable(driver, 60, ScheduleMeetingButton);
		commonActions.click(ScheduleMeetingButton);
		commonActions.waitFor(2000);
		
	}

	public String getRandomSlug() {

		String slug = randomGenerator.randomAlphaNumericString(4);
		return slug;
	}

	public String getRandomPhNumber() {

		return randomGenerator.getRandomPhoneNumber();
	}
	
	public String getRandomTime() {

		return randomGenerator.getRandomPhoneNumber();
	}

	public boolean addCalenderFlag() {

		List<WebElement> list = driver.findElements(
				By.xpath("//div[@class='p-2 pl-3 card-header card-header--service']//span[@class='title']"));
		String newtitle = "";
		for (int i = 0; i < list.size(); i++) {

			String title = list.get(i).getText();

			if (title.equalsIgnoreCase(prop.getProperty("CalenderName"))) {

				newtitle = title;

			}
		}

		if (newtitle.equals(prop.getProperty("CalenderName"))) {

			return true;
		} else {

			return false;
		}
	}

	public boolean newAppointmentFlag() {

		commonActions.waitUntilElementIsVisible(driver, 60, ConfirmationMessage);
		if (ConfirmationMessage.getText().equalsIgnoreCase(prop.getProperty("ExpectedMsg"))) {

			return true;
		} else {
			return false;
		}
	}
	
	public boolean optimizedForAvailabilityFlag() {
		commonActions.waitUntilElementIsVisible(driver, 60, MeetingLocationTag);
		if (MeetingLocationTag.getText().equalsIgnoreCase(prop.getProperty("Place"))) {

			return true;
		} else {
			return false;
		}
		
	}

}
