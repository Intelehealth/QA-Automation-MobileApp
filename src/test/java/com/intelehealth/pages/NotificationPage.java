package com.intelehealth.pages;

import static org.testng.Assert.fail;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class NotificationPage extends BaseTest {

	@AndroidFindBy(accessibility = "Notifications Header Title TextView")
	private WebElement notificationHeader;

	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc='Notifications Today RecyclerView']//android.widget.TextView[@content-desc='Notification List Item Name TextView']")
	private List<WebElement> patients;
	@AndroidFindBy(accessibility = "Prescription Screen Title TextView")
	private WebElement prescriptionTitle;
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Custom Toolbar Refresh Notification Linearlayout\"]/android.widget.ImageView[2]")
	private WebElement notificationIcon;
	@AndroidFindBy(id = "org.intelehealth.app:id/ivInternetCustomToolbar")
	private WebElement homeScreenRefreshButton;
	@AndroidFindBy(accessibility = "Notifications Refresh ImageButton")
	private WebElement notificationsRefreshButton;

	private StartVisit3And4StepsPage startVisit3And4StepsPage;
	private AddNewPatientPage addNewPatientPage;
	JSONObject appData;

	public NotificationPage() throws Exception {
		// Initialize AnotherPageClass object in the constructor
		startVisit3And4StepsPage = new StartVisit3And4StepsPage();
		addNewPatientPage = new AddNewPatientPage();
		InputStream datais = null;
		try {
			// Load test data from JSON fil
			String dataFileName = "data/appData.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			appData = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}

		}
	}

	By notificationPatients = By.xpath("//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Notifications Today RecyclerView\"]\r\n"
			+ "//android.widget.TextView[@content-desc=\"Notification List Item Name TextView\"]");
	
	
	public void verifyUserAbleToViewPrescription() throws InterruptedException {
		Thread.sleep(25000);
		startVisit3And4StepsPage.click(notificationIcon, "Clicked on Notification Icon");
        waitForVisibility(notificationHeader);
		String input = notificationHeader.getText();
		String count = null;

		// Define a pattern to match digits
		Pattern pattern = Pattern.compile("\\d+");

		// Create a matcher with the input string
		Matcher matcher = pattern.matcher(input);

		// Find the first match
		if (matcher.find()) {
			// Extract the matched digit
			count = matcher.group();
			System.out.println("Digit extracted: " + count);
		} else {
			System.out.println("No digit found in the input string.");
		}

		// Check if digit is not null
		if (count != null) {
			int digitValue = Integer.parseInt(count);

			// Check the value of digit
			if (digitValue > 0) {
				for (WebElement patient : patients) {
					click(patient, "Clicked On Patient");
					break;
				}
				isDisplayed(prescriptionTitle, "User able to view Prescription");
			} else {
				// Fail the test case and print a message
				System.out.println("Count value is 0. No prescription found.");
				fail("Count value is 0. No prescription found.");
			}
		}
	}

	public void verifyDataIsSyncedToWeb() throws JSONException, InterruptedException {
		Thread.sleep(25000);
		addNewPatientPage.registerAPatient(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));
		startVisit3And4StepsPage.sendVisit();
	}

	// Verifies that the user can view the most recent received prescription notification
	public void verifyThatUserCanViewTheRecentReceivedPrescriptionNotificationOnTop() throws Exception {
		click(notificationIcon);
		click(notificationsRefreshButton);
		getDriver().navigate().back();
		click(homeScreenRefreshButton, "Clicking on app sync icon");
		restAssured sendPrescription = new restAssured();
		ExtentReport.getTest().log(Status.INFO, "Sharing the prescription");
		click(notificationIcon, "Clicked on Notification Icon");
		sendPrescription.createPatientAndSharePrescription();
		click(notificationsRefreshButton, "Clicking on app sync icon");
		getDriver().navigate().back();
	    Thread.sleep(10000);
		click(notificationIcon, "Clicked on Notification Icon");
		click(notificationsRefreshButton, "Clicking on app sync icon");
		if (isDisplayed2(notificationPatients)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is able to view the recent prescription received notification");
			System.out.println("Able to view the recent prescription received notification");
		} else {
			throw new Exception("Not able to view the recent prescription received notification");
		}
		int numberOfNotifications = patients.size();
		String notifications = String.valueOf(numberOfNotifications);
		waitForVisibility(notificationHeader);
		String prescReceived = notificationHeader.getText();
		String count = extractBefore(prescReceived, "prescriptions received");
		if (notifications.equals(count)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the count of prescriptions received is correct");
			System.out.println("The count of prescriptions received is correct");
		} else {
			throw new Exception("Not displaying the correct count of prescription");
		}
	}
}
