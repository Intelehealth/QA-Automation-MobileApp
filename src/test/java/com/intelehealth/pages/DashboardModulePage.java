package com.intelehealth.pages;

import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.tests.AddNewPatientTest;
import com.intelehealth.tests.VisitSummaryTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DashboardModulePage extends BaseTest {

	// JSON object to store test data
	JSONObject appData;

	AddNewPatientTest addNewPatientTest;

	VisitSummaryTest visitSummaryTest;
	AddNewPatientPage addNewPatientPage;
	AppSetupPage appSetupPage;

	public DashboardModulePage(ThreadLocal<AppiumDriver> driver) throws Throwable {
		addNewPatientTest = new AddNewPatientTest();

		addNewPatientPage = new AddNewPatientPage();
		appSetupPage = new AppSetupPage();
		InputStream datais = null;
		try {
			// Load test data from JSON file
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

	@AndroidFindBy(accessibility = "Setup Screen Location ACTextView")
	private WebElement location;

	@AndroidFindBy(accessibility = "Setup Screen Username Edittext")
	private WebElement username;

	@AndroidFindBy(accessibility = "Setup Screen Password Edittext")
	private WebElement password;

	@AndroidFindBy(accessibility = "Show dropdown menu")
	private WebElement dropdown;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc='Setup Screen Parent RelativeLayout']/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]")
	private WebElement selectlocation;

	@AndroidFindBy(accessibility = "Setup Screen Setup Button")
	private WebElement setupScreeenSetupButton;

	@AndroidFindBy(accessibility = "Custom Toolbar Location Name TextView")
	private WebElement locationName;

	@AndroidFindBy(accessibility = "Custom Toolbar Location Name TextView")
	private WebElement addedLocation;

	@AndroidFindBy(accessibility = "Custom Toolbar App Sync Time TextView")
	private WebElement appSyncTime;

	@AndroidFindBy(id = "org.intelehealth.app:id/ivInternetCustomToolbar")
	private WebElement homeScreenRefreshButton;

	@AndroidFindBy(accessibility = "Home Fragment Closed Visit Number TextView")
	private WebElement numberOfUnclosedVisits;

	@AndroidFindBy(accessibility = "Home Fragment Prescription Number TextView")
	private WebElement numberOfPrescriptions;

	@AndroidFindBy(accessibility = "Home Fragment Appointment Number TextView")
	private WebElement numberOfAppointments;

	@AndroidFindBy(accessibility = "Home Fragment Follow Up Number TextView")
	private WebElement numberOfFollowUps;

	@AndroidFindBy(id = "org.intelehealth.app:id/ivNotificationCustomToolbar")
	private WebElement notificationIcon;

	@AndroidFindBy(accessibility = "Notifications Title TextView")
	private WebElement notificationPageNotificationTitle;

	@AndroidFindBy(accessibility = "Find Patient Screen Search Bar EditText")
	private WebElement findPatientTextfield;

	@AndroidFindBy(accessibility = "Home Fragment Find Patient TextView")
	private WebElement searchPatientTextfield;

	@AndroidFindBy(accessibility = "Find Patients 'All Patients' TextView")
	private WebElement allPatientsPageText;

	@AndroidFindBy(accessibility = "Home Fragment Add Patient Icon ImageView")
	private WebElement addPatientIcon;

	@AndroidFindBy(accessibility = "Home Fragment Add Patient Title TextView")
	private WebElement addPatientsText;

	@AndroidFindBy(accessibility = "Home Fragment Add Patient Arrow Icon ImageView")
	private WebElement addPatientArrowIcon;

	@AndroidFindBy(accessibility = "Home Fragment Prescription Title TextView")
	private WebElement homePagePrescriptionText;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'out of')]")
	private WebElement prescriptionOutOf;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'received')]")
	private WebElement prescriptionReceieved;

	@AndroidFindBy(accessibility = "Home Fragment Prescription Arrow Icon ImageView")
	private WebElement homeScreenPrescriptionArrowIcon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Prescriptions']")
	private WebElement prescriptionPageText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Received\"]")
	private WebElement receivedTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Pending\"]")
	private WebElement pendingTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'unclosed visits')]")
	private WebElement countOfUnclosedVisits;

	@AndroidFindBy(accessibility = "close visits screen title")
	private WebElement closeVisitScreenTitle;

	@AndroidFindBy(accessibility = "upcoming count textview in Todays Appointments")
	private WebElement upcomingTabCount;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Home Fragment Appointment CardView\"]/android.view.ViewGroup")
	private WebElement openAppointments;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='My Appointments']")
	private WebElement myAppointmentsPageText;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Upcoming')]")
	private WebElement homeScreenNoOfUpcomingAppointments;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Pending')]")
	private WebElement homeScreenNoOfPendingFollowUps;

	@AndroidFindBy(accessibility = "Follow Up Screen Title TextView")
	private WebElement followUpPageTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Achievements']")
	private WebElement homeScreenAchievements;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='My Achievements']")
	private WebElement myAchievementsPageTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Help']")
	private WebElement homeScreenHelp;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Help center']")
	private WebElement helpCenterPageTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/navigation_bar_item_small_label_view\" and @text=\"Add Patients\"]")
	private WebElement homeScreenAddPatients;

	@AndroidFindBy(accessibility = "Privacy Policy Title TextView")
	private WebElement privacyPolicyPageTitle;

	@AndroidFindBy(accessibility = "Permission Required Dialog Okay Button")
	private WebElement permissionRequiredPopupOkayButton;

	@AndroidFindBy(accessibility = "Intelehealth")
	private WebElement intelehealthUsageAccess;

	@AndroidFindBy(id = "android:id/switch_widget")
	private WebElement permitUsageAccessToggleButton;

	@AndroidFindBy(accessibility = "My Achievements 'Level' TextView")
	private WebElement myAchievementsLevelText;

	By todaysUpcomingAppointmentPatientName = By.xpath(
			"//android.widget.TextView[@content-desc=\"upcoming title textview in Todays Appointments\"]//..//android.widget.FrameLayout[@content-desc=\"Today Appointment Item Parent CardView\"]");

	By byPermissionRequiredPopupOkayButton = By
			.xpath("//android.widget.Button[@content-desc=\"Permission Required Dialog Okay Button\"]");

	// Perform login
	public void login(String un, String pw) {
		// Click on the dropdown to open the menu
		click(dropdown);

		// Click on the location element to select a location
		click(selectlocation);

		// Enter the username into the username field
		sendKeys(username, un);

		// Enter the password into the password field
		sendKeys(password, pw);
		// Click on the setup screen setup button to complete the login process
		click(setupScreeenSetupButton);
		isDisplayed(locationName);
	}

	public void performLogin() {
		String originalUserName = appData.getJSONObject("validUser").getString("username");
		String originalPassword = appData.getJSONObject("validUser").getString("password");

		System.out.println(originalPassword);
		System.out.println(originalUserName);
		// Encrypt the password
		String encryptedUserName = encrypt(originalUserName);
		System.out.println("Encrypted UserName: " + encryptedUserName);
		String decryptedUserName = decrypt(encryptedUserName);
		System.out.println("Decrypted UserName: " + decryptedUserName);
		String encryptedPassword = encrypt(originalPassword);
		System.out.println("Encrypted Password: " + encryptedPassword);
		String decryptedPassword = decrypt(encryptedPassword);
		System.out.println("Decrypted Password: " + decryptedPassword);
		login(decryptedUserName, decryptedPassword);
		appSetupPage.locationIsDisplayed();
	}

	public void setUp() {
		appSetupPage.handlePermissions();
		// Click on the "Next" button on the app setup page
		appSetupPage.clickOnNextButton();

		// Click on the "Skip" button on the app setup page
		appSetupPage.clickOnSkipButton();

		// Click on the checkbox on the app setup page
		appSetupPage.clickOnCheckBox();

		// Click on the "Setup" button on the app setup page
		appSetupPage.clickOnSetupButton();

	}

	// Verifies that the added location is displayed on the page
	public void verifyThatAddedLocationIsDisplayOnTopOfThePage() throws Throwable {
		click(dropdown, "Clicking on dropdown");
		click(selectlocation, "Selecting location from the dropdown");
		String originalUserName = appData.getJSONObject("validUser").getString("username");
		String originalPassword = appData.getJSONObject("validUser").getString("password");
		String encryptedUserName = encrypt(originalUserName);
		String decryptedUserName = decrypt(encryptedUserName);
		String encryptedPassword = encrypt(originalPassword);
		String decryptedPassword = decrypt(encryptedPassword);
		sendKeys(username, decryptedUserName);
		sendKeys(password, decryptedPassword);
		waitForVisibility(location);
		String SelectedLocation = location.getText();
		click(setupScreeenSetupButton, "Clicking on setup button");
		waitForVisibility(addedLocation);
		String LocationAdded = addedLocation.getText();
		if (LocationAdded.equals(SelectedLocation)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether added Location is displayed on top of the page");
			System.out.println("Added Location is displayed on top of the page");
		} else {
			throw new Exception("Added Location is not displayed on top of the page");
		}
	}

	// Verifies that the last synced time and date display on the page
	public void verifyThatLastSyncedTimeAndDateDisplayOnTopOfThePage() {
		performLogin();
		ExtentReport.getTest().log(Status.INFO,
				"Verifying whether app synced time and date is displayed on top of the page");
		isDisplayed(appSyncTime);
	}

	// Verifies the functionality of the sync icon
	public void verifyThatSyncIconFunctionality() throws Throwable {
		performLogin();
		waitForVisibility(appSyncTime);
		String beforeSyncTime = appSyncTime.getText();
		waitForVisibility(numberOfUnclosedVisits);
		String beforeSyncUnclosedVisits = numberOfUnclosedVisits.getText();
		waitForVisibility(numberOfPrescriptions);
		String beforeSyncPrescriptions = numberOfPrescriptions.getText();
		waitForVisibility(numberOfAppointments);
		String beforeSyncNumberOfAppointments = numberOfAppointments.getText();
		waitForVisibility(numberOfFollowUps);
		String beforeSyncNumberOfFollowUps = numberOfFollowUps.getText();
		int maxAttempts = 3;
		int attempt = 0;
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(20000);
				click(homeScreenRefreshButton, "Clicking on the sync icon");
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		waitForVisibility(appSyncTime);
		String afterSyncTime = appSyncTime.getText();
		waitForVisibility(numberOfUnclosedVisits);
		String afterSyncUnclosedVisits = numberOfUnclosedVisits.getText();
		waitForVisibility(numberOfPrescriptions);
		String afterSyncPrescriptions = numberOfPrescriptions.getText();
		waitForVisibility(numberOfAppointments);
		String afterSyncNumberOfAppointments = numberOfAppointments.getText();
		waitForVisibility(numberOfFollowUps);
		String afterSyncNumberOfFollowUps = numberOfFollowUps.getText();
		if (!beforeSyncTime.equals(afterSyncTime)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether app is synced");
			System.out.println("App is synced");
		}
		if (!beforeSyncUnclosedVisits.equals(afterSyncUnclosedVisits)
				|| !beforeSyncPrescriptions.equals(afterSyncPrescriptions)
				|| !beforeSyncNumberOfAppointments.equals(afterSyncNumberOfAppointments)
				|| !beforeSyncNumberOfFollowUps.equals(afterSyncNumberOfFollowUps)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether app data is synced");
			System.out.println("App data is synced");
		} else {
			throw new Exception("App data is not synced");
		}

	}

	// Verifies that clicking the notification icon navigates to the notification
	// page
	public void verifyThatNotificationIconNavigateToNotificationPage() throws Throwable {
		performLogin();
		click(notificationIcon, "Clicking on notification icon");
		if (isDisplayed(notificationPageNotificationTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to notification page");
			System.out.println("User is navigated to notification page");
		}
	}

	// Verifies the display and click functionality of the Find Patient search box
	public void verifyThatFindPatientSearchBoxDisplayedAndClickFunctionality() throws Throwable {
		performLogin();
		int maxAttempts = 3;
		int attempt = 0;
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(20000);
				click(homeScreenRefreshButton);
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		click(searchPatientTextfield, "Clicking on search patient textfield");
		if (isDisplayed(findPatientTextfield) && isDisplayed(allPatientsPageText)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether the user is navigated to 'All patients' page along with Search textfield");
			System.out.println("The user is navigated to 'All patients' page along with Search texfield");
		}
	}

	// Verifies the display of the Add Patient section along with the arrow icon
	public void verifyThatAddPatientSectionDisplayedWithArrowIcon() throws Throwable {
		performLogin();
		if (isDisplayed(addPatientIcon) && isDisplayed(addPatientsText) && isDisplayed(addPatientArrowIcon)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether add patient section is displayed on the page with arrow icon");
			System.out.println("Add patient section is displayed on the page with arrow icon");
		}
	}

	// Verifies that the Prescription section displays the count of received
	// prescriptions out of total prescriptions
	public void verifyThatPrescriptionSectionShouldDisplayTheReceivedPrescriptionsOutOfTotalPrecriptions()
			throws Throwable {
		performLogin();
		isDisplayed(homePagePrescriptionText);
		if (isDisplayed(numberOfPrescriptions) && isDisplayed(prescriptionOutOf)
				&& isDisplayed(prescriptionReceieved)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether received prescription out of total prescription is displayed");
			System.out.println("Received prescription out of total prescription is displayed");
		}
	}

	// Verifies the functionality when clicking on the Prescriptions arrow navigates
	// to prescriptions page
	public void verifyClickingOnPrescriptionsArrow() throws Throwable {
		performLogin();
		click(homeScreenPrescriptionArrowIcon, "Clicking on prescriptions arrow icon");
		if (isDisplayed(prescriptionPageText) && isDisplayed(receivedTab) && isDisplayed(pendingTab)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to prescriptions page");
			System.out.println("User is navigated to prescriptions page");
		}
	}

	// Verifies that the Close Visits section displays the number of unclosed visits
	public void verifyThatCloseVisitsSectionDisplayTheNumberOfUnclosedVisit() throws Throwable {
		performLogin();
		int maxAttempts = 3;
		int attempt = 0;
		while (attempt < maxAttempts) {
			try {
				Thread.sleep(20000);
				click(homeScreenRefreshButton);
				break; // Break out of the loop if successful
			} catch (WebDriverException e) {
				// Log or handle the exception
				attempt++;
			}
		}
		if (isDisplayed(numberOfUnclosedVisits) && isDisplayed(countOfUnclosedVisits)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the count of unclosed visits is displayed");
			System.out.println("The count of unclosed visits is displayed");
		}
	}

	// Verifies that the user can navigate to the Close Visits page
	public void verifyThatUserCanNavigateToCloseVisitsPage() throws Throwable {
		performLogin();
		click(numberOfUnclosedVisits, "Clicking on close visits");
		if (isDisplayed(closeVisitScreenTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to close visits page");
			System.out.println("User is navigated to close visits page");
		}
	}

	// Verifies that the Appointments section displays the upcoming appointments
	public void verifyAppointmentsSectionShouldDisplayTheUpcomingAppointments() throws Throwable {
		performLogin();
		if (isDisplayed(numberOfAppointments) && isDisplayed(homeScreenNoOfUpcomingAppointments)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether number of upcoming appointments are displayed");
			System.out.println("Number of upcoming appointments are displayed");
		}
	}

	// Verifies that the user can navigate to the My Appointments page
	public void verifyThatUserCanNavigateToMyAppointmentsPage() throws Throwable {
		performLogin();
		click(openAppointments, "Clicking on appointments");
		if (isDisplayed(myAppointmentsPageText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to 'My appointments' page");
			System.out.println("User is navigated to 'My appointments' page");
		}
	}

	// Verifies that the Follow-Up Visits section displays the number of follow-up
	// visits
	public void verifyThatFollowUpVisitsSectionDisplayTheNumberOfFollowUpVisits() throws Throwable {
		performLogin();
		if (isDisplayed(numberOfFollowUps) && isDisplayed(homeScreenNoOfPendingFollowUps)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether number of pending follow up visits are displayed");
			System.out.println("Number of pending follow up visits are displayed");
		}
	}

	// Verifies that selecting Follow-Up Visits navigates to the Follow-Up Visits
	// page
	public void verifyThatFollowUpVisitsShouldNavigateToFollowUpVisitsPage() throws Throwable {
		performLogin();
		click(numberOfFollowUps, "Clicking on number of follow ups");
		if (isDisplayed(followUpPageTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to 'Follow-up visits' page");
			System.out.println("User is navigated to 'Follow-up visits' page");
		}
	}

	// Verifies that selecting Achievements on the bottom navigation navigates to My
	// Achievements page
	public void verifyThatAchievementsOnBottomNavigateToMyAchievementsPage() throws Throwable {
		performLogin();
		click(homeScreenAchievements, "Clicking on achievements");
		boolean permissionPopup = isDisplayed2(byPermissionRequiredPopupOkayButton);
		if (permissionPopup == true) {
			click(permissionRequiredPopupOkayButton, "Clicking on okay button in the permission required popup");
			scrollToElementByText("Intelehealth");
			click(intelehealthUsageAccess, "Clicking on intelehealth in usage access screen");
			click(permitUsageAccessToggleButton, "Clicking on permit usage access toggle button");
			for (int i = 0; i < 2; i++) {
				ExtentReport.getTest().log(Status.INFO, "Clicking on navigate back button");
				getDriver().navigate().back();
			}
			if (isDisplayed(myAchievementsLevelText) || myAchievementsPageTitle.isDisplayed()) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether user is navigated to 'My Achievements' page");
				System.out.println("User is navigated to 'My Achievements' page");
			}
		}
	}

	private void scrollToElementByText(String string) {
		// TODO Auto-generated method stub

	}

	// Verifies that selecting Help on the bottom navigation navigates to the Help
	// Center page
	public void verifyThatHelpOnBottomNavigateToHelpCenterPage() throws Throwable {
		performLogin();
		click(homeScreenHelp, "Clicking on help");
		if (isDisplayed(helpCenterPageTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether User is navigated to 'Help center' page");
			System.out.println("User is navigated to 'Help center' page");
		}
	}

	// Verifies that selecting Add Patient on the bottom navigation navigates to the
	// Privacy Policy page
	public void verifyThatAddPatientOnBottomNavigateToPrivacyPolicyPage() throws Throwable {
		performLogin();
		click(homeScreenAddPatients, "Clicking on add patients icon");
		if (isDisplayed(privacyPolicyPageTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to 'Privacy Policy' page");
			System.out.println("User is navigated to 'Privacy Policy' page");
		}
	}
}
