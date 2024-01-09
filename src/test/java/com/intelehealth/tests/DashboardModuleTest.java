package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Locale;

import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AddNewPatientPage;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.AppointmentsPage;
import com.intelehealth.pages.DashboardModulePage;
import com.intelehealth.pages.FindPatientPage;
import com.intelehealth.pages.StartVisit1And2StepsPage;
import com.intelehealth.pages.VisitSummaryPage;
import com.intelehealth.utils.TestUtils;

public class DashboardModuleTest extends BaseTest {

	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	// JSON object to store test data
	JSONObject appData;
	FindPatientPage findPatientPage;
	StartVisit1And2StepsPage  startVisit1And2StepsPage ;
	AddNewPatientPage addNewPatientPage;
	VisitSummaryPage visitSummaryPage;
	AppointmentsPage appointmentsPage;
	DashboardModulePage dashboardModulePage;

	Faker faker = new Faker(new Locale("en-IND"));
	String lastName = faker.name().lastName();

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, Throwable {
		// Log information about the test
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		// Launch the app and initialize necessary screens
		resetApp();
		launchApp();
		setImplicitWait();
		appSetupPage = new AppSetupPage();
		findPatientPage = new FindPatientPage();
		visitSummaryPage = new VisitSummaryPage(driver);
		appointmentsPage = new AppointmentsPage(driver);
		startVisit1And2StepsPage = new StartVisit1And2StepsPage();
		dashboardModulePage = new DashboardModulePage(driver);
		dashboardModulePage.setUp();
		

	}
 
	@Test(priority = 1, description = "Verify that Added Location is display on top of the page", enabled = true)
	public void IDA4_2507_verifyThatAddedLocationIsDisplayOnTopOfThePage() throws Throwable {
		dashboardModulePage.verifyThatAddedLocationIsDisplayOnTopOfThePage();
	}
		
	@Test(priority = 2, description = "Verify that Last synced Time and Date Display on top of the page", enabled = true)
	public void IDA4_2508_verifyThatLastSyncedTimeAndDateDisplayOnTopOfThePage() throws Throwable {
		dashboardModulePage.verifyThatLastSyncedTimeAndDateDisplayOnTopOfThePage();
	}
	
	@Test(priority = 3, description = "Verify that sync icon functionality", enabled = true)
	public void IDA4_2509_verifyThatSyncIconFunctionality() throws Throwable {
		dashboardModulePage.verifyThatSyncIconFunctionality();
	}
	
	@Test(priority = 4, description = "Verify that Notification icon navigate to Notification page", enabled = true)
	public void IDA4_2060_verifyThatNotificationIconNavigateToNotificationPage() throws Throwable {
		dashboardModulePage.verifyThatNotificationIconNavigateToNotificationPage();
	}
	
	@Test(priority = 5, description = "Verify that Find patient search box displayed and click functionality", enabled = true)
	public void IDA4_2061_verifyThatFindPatientSearchBoxDisplayedAndClickFunctionality() throws Throwable {
		dashboardModulePage.verifyThatFindPatientSearchBoxDisplayedAndClickFunctionality();
	}
	
	@Test(priority = 6, description = "Verify that Add patient section displayed with arrow icon", enabled = true)
	public void IDA4_2062_verifyThatAddPatientSectionDisplayedWithArrowIcon() throws Throwable {
		dashboardModulePage.verifyThatAddPatientSectionDisplayedWithArrowIcon();
	}
	
	@Test(priority = 7, description = "Verify that Prescription section should display the Received Prescriptions out of total Precriptions", enabled = true)
	public void IDA4_2064_verifyThatPrescriptionSectionShouldDisplayTheReceivedPrescriptionsOutOfTotalPrecriptions() throws Throwable {
		dashboardModulePage.verifyThatPrescriptionSectionShouldDisplayTheReceivedPrescriptionsOutOfTotalPrecriptions();
	}
	
	@Test(priority = 8, description = "Verify clicking on Prescriptions arrow", enabled = true)
	public void IDA4_2065_verifyClickingOnPrescriptionsArrow() throws Throwable {
		dashboardModulePage.verifyClickingOnPrescriptionsArrow();
	}
	
	@Test(priority = 9, description = "Verify that Close visits section display the number of unclosed visit", enabled = true)
	public void IDA4_2066_verifyThatCloseVisitsSectionDisplayTheNumberOfUnclosedVisit() throws Throwable {
		dashboardModulePage.verifyThatCloseVisitsSectionDisplayTheNumberOfUnclosedVisit();
	}
	
	@Test(priority = 10, description = "Verify that user can navigate to Close Visits page", enabled = true)
	public void IDA4_2067_verifyThatUserCanNavigateToCloseVisitsPage() throws Throwable {
		dashboardModulePage.verifyThatUserCanNavigateToCloseVisitsPage();
	}
	
	@Test(priority = 11, description = "Verify appointments section should display the upcoming appointments", enabled = true)
	public void IDA4_2068_verifyAppointmentsSectionShouldDisplayTheUpcomingAppointments() throws Throwable {
		dashboardModulePage.verifyAppointmentsSectionShouldDisplayTheUpcomingAppointments();
	}
	
	@Test(priority = 12, description = "Verify that user can navigate to My Appointments page", enabled = true)
	public void IDA4_2069_verifyThatUserCanNavigateToMyAppointmentsPage() throws Throwable {
		dashboardModulePage.verifyThatUserCanNavigateToMyAppointmentsPage();
	}
	
	@Test(priority = 13, description = "Verify that Follow up visits section display the number of follow up visits", enabled = true)
	public void IDA4_2070_verifyThatFollowUpVisitsSectionDisplayTheNumberOfFollowUpVisits() throws Throwable {
		dashboardModulePage.verifyThatFollowUpVisitsSectionDisplayTheNumberOfFollowUpVisits();
	}
	
	@Test(priority = 14, description = "Verify that follow up visits should navigate to Follow-up visits page", enabled = true)
	public void IDA4_2071_verifyThatFollowUpVisitsShouldNavigateToFollowUpVisitsPage() throws Throwable {
		dashboardModulePage.verifyThatFollowUpVisitsShouldNavigateToFollowUpVisitsPage();
	}
	
	@Test(priority = 15, description = "Verify that Achievements on Bottom navigate to My Achievements page", enabled = true)
	public void IDA4_2073_verifyThatAchievementsOnBottomNavigateToMyAchievementsPage() throws Throwable {
		dashboardModulePage.verifyThatAchievementsOnBottomNavigateToMyAchievementsPage();
	}
	
	@Test(priority = 16, description = "Verify that Help on Bottom navigate to Help center page", enabled = true)
	public void IDA4_2074_verifyThatHelpOnBottomNavigateToHelpCenterPage() throws Throwable {
		dashboardModulePage.verifyThatHelpOnBottomNavigateToHelpCenterPage();
	}
	
	@Test(priority = 17, description = "Verify that Add patient on bottom navigate to privacy policy page", enabled = true)
	public void IDA4_2075_verifyThatAddPatientOnBottomNavigateToPrivacyPolicyPage() throws Throwable {
		dashboardModulePage.verifyThatAddPatientOnBottomNavigateToPrivacyPolicyPage();
	}
	
	
	
	@AfterMethod
	public void afterMethod() {
		closeApp();
		System.gc();
	}
}
