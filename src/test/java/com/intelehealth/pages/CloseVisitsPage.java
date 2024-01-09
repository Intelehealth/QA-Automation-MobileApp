package com.intelehealth.pages;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CloseVisitsPage extends BaseTest {
	@AndroidFindBy(accessibility = "Home Fragment Closed Visits Title TextView")
	private WebElement closeVisits;
	@AndroidFindBy(accessibility = "move to previous screen")
	private WebElement backArrow;
	@AndroidFindBy(accessibility = "recent visits title")
	private WebElement recentVisitsTitle;
	@AndroidFindBy(xpath = "(//android.widget.Button[@content-desc=\"close visit button row item\"])[1]")
	private WebElement closeVisitsButton;
	@AndroidFindBy(accessibility = "Common Message Dialog Title TextView")
	private WebElement closeVisitPopupTitle;
	@AndroidFindBy(xpath = "(//androidx.recyclerview.widget.RecyclerView[@content-desc=\"older visits recycler data\"]/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout//android.widget.Button)")
	private WebElement olderVisitsCloseButton;
	@AndroidFindBy(accessibility = "Common Message Dialog Subtitle TextView")
	private WebElement closeVisitPopupSubTitle;
	@AndroidFindBy(accessibility = "Common Message Dialog Negative Button")
	private WebElement cancelButton;
	@AndroidFindBy(accessibility = "Common Message Dialog Positive Button")
	private WebElement confirmButton;
	@AndroidFindBy(accessibility = "Patient Survey Title TextView")
	private WebElement feedbackTitle;
	@AndroidFindBy(accessibility = "Patient Survey Screen Give Feedback EditText")
	private WebElement feedbackTextField;
	@AndroidFindBy(accessibility = "Patient Survey Screen Submit Button")
	private WebElement submitButton;
	@AndroidFindBy(accessibility = "Home Fragment Closed Visit Number TextView")
	private WebElement closedVisitsCount;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"patient name row item title\"])[1] ")
	private WebElement recentVisitsPatientName;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"patient date and time row item\"])[1]")
	private WebElement date;
	@AndroidFindBy(xpath = "(//android.widget.Button[@content-desc=\"close visit button row item\"])[1]")
	private WebElement recentVisitCloseButton;
	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc='older visits recycler data']//android.widget.TextView[@content-desc='patient date and time row item']")
	private List<WebElement> olderVisitsTimeStamp;
	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"recent visits recycler data\"]//android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout//android.widget.RelativeLayout//android.widget.TextView[@content-desc='patient date and time row item']")
	private List<WebElement> recentVisitsTimeStamp;

	
	// Verify the functionality of the Close Visit button
	public void verifyCloseVisitButtonFunctionality() throws InterruptedException {
	    click(closeVisits, "Clicked on 'Close Visits'");
	    click(backArrow, "Clicked on back arrow");

	    // Wait for some time to ensure the elements are loaded
	    Thread.sleep(10000);

	    // Click on Close Visits again
	    click(closeVisits, "Clicked on 'Close Visits'");
	    click(closeVisitsButton, "Clicked on 'Close Visits' Button");
	    isDisplayed(closeVisitPopupTitle, "Close Visit Popup is displayed - Title");
	    isDisplayed(closeVisitPopupSubTitle, "Close Visit Popup is displayed - Subtitle");
	    isDisplayed(cancelButton, "Close Visit Popup is displayed - Cancel Button");
	    isDisplayed(confirmButton, "Close Visit Popup is displayed - Confirm Button");
	}


	// Verify functionality of the 'Confirm' button in the close visit popup.
	
	public void verifyConfirmButtonFunctionality() throws InterruptedException {
	    click(closeVisits, "Clicked on 'Close Visits'");
	  
	    // Click on Close Visits again
	    click(closeVisits, "Clicked on 'Close Visits'");
	    click(closeVisitsButton, "Clicked on 'Close Visits' Button");
	    isDisplayed(closeVisitPopupTitle, "Close Visit Popup is displayed - Title");
	    isDisplayed(closeVisitPopupSubTitle, "Close Visit Popup is displayed - Subtitle");

	    click(confirmButton, "Clicked on 'Confirm' Button");
	    isDisplayed(feedbackTitle, "Feedback Screen is displayed - Title");
	}


	
	// Enter feedback and verify the process
	public void enterFeedbackAndverify() throws InterruptedException {
	    click(closeVisits, "Clicked on 'Close Visits'");
	  

	    // Click on Close Visits again
	    click(closeVisits, "Clicked on 'Close Visits'");
	    click(closeVisitsButton, "Clicked on 'Close Visits' Button");
	    isDisplayed(closeVisitPopupTitle, "Close Visit Popup is displayed - Title");
	    isDisplayed(closeVisitPopupSubTitle, "Close Visit Popup is displayed - Subtitle");

	    click(confirmButton, "Clicked on 'Confirm' Button");
	    isDisplayed(feedbackTitle, "Feedback Screen is displayed - Title");

	    sendKeys(feedbackTextField, "Testing", "Entered feedback: 'Testing'");
	    click(submitButton, "Clicked on 'Submit' Button");
	}


	// Scroll to the 'Older Visits' section and close visits in both recent and
	
	public WebElement scrollToOlderVisits() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Older Visits\").instance(0))"));
	}

	// Close visits in both the Recent and Older Visits sections
	public void closeVisitsInRecentAndOlderVisitsSection() throws InterruptedException {
	    // Close visits in the Recent Visits section
	    click(closeVisits, "Clicked on 'Close Visits'");
	    click(closeVisitsButton, "Clicked on 'Close Visits' Button");
	    isDisplayed(closeVisitPopupTitle, "Close Visit Popup is displayed - Title");
	    isDisplayed(closeVisitPopupSubTitle, "Close Visit Popup is displayed - Subtitle");

	    click(confirmButton, "Clicked on 'Confirm' Button");
	    isDisplayed(feedbackTitle, "Feedback Screen is displayed - Title");
	    sendKeys(feedbackTextField, "Testing", "Entered feedback: 'Testing'");
	    click(submitButton, "Clicked on 'Submit' Button");

	    // Close visits in the Older Visits section
	    click(closeVisits, "Clicked on 'Close Visits'");
	    scrollToOlderVisits();
	    click(olderVisitsCloseButton, "Clicked on 'Close' Button for Older Visits");
	    isDisplayed(closeVisitPopupTitle, "Close Visit Popup is displayed - Title");
	    isDisplayed(closeVisitPopupSubTitle, "Close Visit Popup is displayed - Subtitle");

	    click(confirmButton, "Clicked on 'Confirm' Button");
	    isDisplayed(feedbackTitle, "Feedback Screen is displayed - Title");
	    sendKeys(feedbackTextField, "Testing", "Entered feedback: 'Testing'");
	    click(submitButton, "Clicked on 'Submit' Button");
	}


	
	// Verify elements in the Recent Visits section
	public void verifyRecentVisitsSection() throws InterruptedException {
	    click(closeVisits, "Clicked on 'Close Visits'");
	    isDisplayed(recentVisitsTitle, "Recent Visits Section - Title");

	    isDisplayed(recentVisitsPatientName, "Recent Visits Section - Patient Name");
	    isDisplayed(date, "Recent Visits Section - Date");
	    isDisplayed(recentVisitCloseButton, "Recent Visits Section - Close Button");
	}


	// Verify that the count of unclosed visits decreases after closing a visit
	public void verifyCloseVisitsCountIsDecreased() throws InterruptedException {
	    // Wait for some time to ensure the elements are loaded
	    Thread.sleep(5000);

	    int decreasedClosedCount = 0;
	    int closedCount = 0;

	    // Display the initial closed visits count
	    isDisplayed(closedVisitsCount, "Closed Visits Count is displayed");
	    String inputString = closedVisitsCount.getText();

	    // Use a regular expression to match numeric values
	    Pattern pattern = Pattern.compile("\\d+");
	    Matcher matcher = pattern.matcher(inputString);

	    // Check if a numeric value is found
	    if (matcher.find()) {
	        // Extract and print the numeric value
	        String numericValue = matcher.group();
	        closedCount = Integer.parseInt(numericValue);
	        System.out.println("Number of unclosed visits: " + closedCount);
	    } else {
	        System.out.println("No numeric value found in the input string.");
	    }

	    // Close a visit
	    click(closeVisits, "Clicked on 'Close Visits'");
	    scrollToOlderVisits();
	    click(olderVisitsCloseButton, "Clicked on 'Close' Button for Older Visits");
	    isDisplayed(closeVisitPopupTitle, "Close Visit Popup is displayed - Title");
	    isDisplayed(closeVisitPopupSubTitle, "Close Visit Popup is displayed - Subtitle");

	    click(confirmButton, "Clicked on 'Confirm' Button");
	    isDisplayed(feedbackTitle, "Feedback Screen is displayed - Title");
	    sendKeys(feedbackTextField, "Testing", "Entered feedback: 'Testing'");
	    click(submitButton, "Clicked on 'Submit' Button");

	    // Wait for some time to ensure the elements are loaded
	    Thread.sleep(5000);

	    // Display the updated closed visits count
	    String dCount = closedVisitsCount.getText();

	    // Use a regular expression to match numeric values
	    Pattern pattern1 = Pattern.compile("\\d+");
	    Matcher matcher1 = pattern.matcher(dCount);

	    // Check if a numeric value is found
	    if (matcher1.find()) {
	        // Extract and print the numeric value
	        String numericValue = matcher1.group();
	        decreasedClosedCount = Integer.parseInt(numericValue);
	        System.out.println("Number of unclosed visits after closing a visit: " + decreasedClosedCount);
	    } else {
	        System.out.println("No numeric value found in the input string.");
	    }

	    // Verify if the closed visit count is decreased
	    if (decreasedClosedCount < closedCount) {
	        System.out.println("Closed visit count is decreased");
	    } else {
	        System.out.println("Closed visit count is not decreased");
	    }
	}


	// Verify that patients with visits older than seven days are displayed
	public void verifyAboveSevenDaysPatientsAreDisplayed() throws InterruptedException {
	    // Step 1: Navigate to the Close Visits section
	    click(closeVisits, "Clicked on 'Close Visits'");
	    click(backArrow, "Clicked on back arrow");
	    
	    // Wait for some time to ensure the elements are loaded
	    Thread.sleep(10000);
	    
	    // Click on Close Visits again
	    click(closeVisits, "Clicked on 'Close Visits'");
	    scrollToOlderVisits();

	    // Counter for the number of older visits
	    int olderVisitsCount = 0;

	    // Step 2: Verify each patient visit date
	    for (WebElement visitTimeStamp : olderVisitsTimeStamp) {
	        String visitDateText = visitTimeStamp.getText();
	        System.out.println("Visit Date Text: " + visitDateText);

	        // Parse the visit date using the specified format
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM HH:mm a", Locale.ENGLISH);

	        // Replace multiple spaces with a single space before parsing
	        visitDateText = visitDateText.replaceAll("\\s+", " ");

	        try {
	            LocalDateTime visitDateTime = LocalDateTime.parse(visitDateText, formatter);

	            // Extract the date part for comparison
	            LocalDate visitDate = visitDateTime.toLocalDate();

	            // Get the current date
	            LocalDate currentDate = LocalDate.now();

	            // Calculate the difference in days
	            long daysDifference = ChronoUnit.DAYS.between(visitDate, currentDate);

	            // Check if the visit is 7 days or more old
	            if (daysDifference >= 7) {
	                System.out.println("Older visit: " + visitDateText);
	                // You can add further verification or actions as needed

	                // Increment the counter
	                olderVisitsCount++;
	            }
	        } catch (DateTimeParseException e) {
	            System.out.println("Error parsing date: " + visitDateText);
	            // Handle the case where the date cannot be parsed
	        }
	    }

	    // Fail the test case if no older visits are found
	    assert olderVisitsCount > 0 : "No visits are above 7 days.";
	    System.out.println("Number of older visits: " + olderVisitsCount);
	}

	// Verify that patients with visits from the last seven days are displayed
	public void verifyLastSevenDaysPatientsAreDisplayed() throws InterruptedException {
	    // Step 1: Navigate to the Close Visits section
	    click(closeVisits, "Clicked on 'Close Visits'");
	    click(backArrow, "Clicked on back arrow");

	    // Wait for some time to ensure the elements are loaded
	    Thread.sleep(10000);

	    // Click on Close Visits again
	    click(closeVisits, "Clicked on 'Close Visits'");

	    // Counter for the number of recent visits
	    int recentVisitsCount = 0;

	    // Step 2: Verify each patient visit date in the "Recent Visits" section
	    for (WebElement visitTimeStamp : recentVisitsTimeStamp) {
	        String visitDateText = visitTimeStamp.getText();
	        System.out.println("Visit Date Text: " + visitDateText);

	        // Parse the visit date using the specified format
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM HH:mm a", Locale.ENGLISH);

	        // Replace multiple spaces with a single space before parsing
	        visitDateText = visitDateText.replaceAll("\\s+", " ");

	        try {
	            LocalDateTime visitDateTime = LocalDateTime.parse(visitDateText, formatter);

	            // Extract the date part for comparison
	            LocalDate visitDate = visitDateTime.toLocalDate();

	            // Get the current date
	            LocalDate currentDate = LocalDate.now();

	            // Calculate the difference in days
	            long daysDifference = ChronoUnit.DAYS.between(visitDate, currentDate);

	            // Check if the visit is within the last 7 days
	            if (daysDifference <= 7) {
	                System.out.println("Recent visit: " + visitDateText);
	                // You can add further verification or actions as needed

	                // Increment the counter
	                recentVisitsCount++;
	            }
	        } catch (DateTimeParseException e) {
	            System.out.println("Error parsing date: " + visitDateText);
	            // Handle the case where the date cannot be parsed
	        }
	    }

	    // Fail the test case if no recent visits are found
	    assert recentVisitsCount > 0 : "No visits are from the last 7 days.";
	    System.out.println("Number of recent visits: " + recentVisitsCount);
	}



	
}