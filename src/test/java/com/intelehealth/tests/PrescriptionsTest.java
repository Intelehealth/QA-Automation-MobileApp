package com.intelehealth.tests;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Locale;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.intelehealth.api.APIServices;
import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AddNewPatientPage;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.AppointmentsPage;
import com.intelehealth.pages.DashboardModulePage;
import com.intelehealth.pages.FindPatientPage;
import com.intelehealth.pages.PrescriptionsPage;
import com.intelehealth.pages.StartVisit1And2StepsPage;
import com.intelehealth.pages.VisitSummaryPage;
import com.intelehealth.utils.TestUtils;

public class PrescriptionsTest extends BaseTest {

	AppSetupPage appSetupPage;
	// JSON object to store test data
	JSONObject appData;
	FindPatientPage findPatientPage;
	StartVisit1And2StepsPage startVisit1And2StepsPage;
	AddNewPatientPage addNewPatientPage;
	VisitSummaryPage visitSummaryPage;
	AppointmentsPage appointmentsPage;
	DashboardModulePage dashboardModulePage;
	PrescriptionsPage prescriptionsPage;

	Faker faker = new Faker(new Locale("en-IND"));
	String lastName = faker.name().lastName();

	@BeforeMethod
	public void beforeMethod(Method m) throws Throwable {
		TestUtils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		resetApp();
		launchApp();
		appSetupPage = new AppSetupPage();
		findPatientPage = new FindPatientPage();
		prescriptionsPage = new PrescriptionsPage();
		InputStream datais = null;
		/**
		 * GOing forward we will be using these variables for authorization for CHW and
		 * Doctor
		 */
		hw_request = buildRequestWithNurseAuthorization();
		doctor_request = buildRequestWithDoctorAuthorization();
		try {
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
		// grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();

	}

	@Test(priority = 1, description = "Verify received prescriptions patients listed in recent visit", enabled = true)
	public void IDA4_2078_verifyReceivedPrescriptionsPatientsListedInRecentVisit() throws Throwable {
		/**
		 * GOing forward we will be using these methods for visit creation as CHW,
		 * starting the visit as doctor, sign and submit
		 */
		APIServices.createVisitUsingRestAssured(hw_request);
		APIServices.startVisitUsingRestAssured(doctor_request);
		APIServices.signAndSubmitUsingRestAssured(doctor_request);

		Assert.assertTrue(prescriptionsPage.verifyReceivedPrescriptionsPatientsListedInRecentVisit());
	}

	@Test(priority = 15, description = "Verify share functionality when clicked on any recent patient visit", enabled = true)
	public void IDA4_2079_verifyShareFunctionalityWhenClickedOnAnyRecentPatientVisit() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyShareFunctionalityWhenClickedOnAnyRecentPatientVisit());
	}

	@Test(priority = 3, description = "Verify if the prescription is shared on entering a valid number", enabled = true)
	public void IDA4_2080_verifyIfThePrescriptionIsSharedOnEnteringAValidNumber() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyIfThePrescriptionIsSharedOnEnteringAValidNumber());
	}

	@Test(priority = 4, description = "Verify the count of patients awaiting prescriptions are correct", enabled = true)
	public void IDA4_2085_verifyTheCountOfPatientsAwaitingPrescriptionsAreCorrect() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyTheCountOfPatientsAwaitingPrescriptionsAreCorrect());
	}

	@Test(priority = 5, description = "Verify that user is able to navigate to visit details page by clicking on any recent visit", enabled = true)
	public void IDA4_2086_verifyThatUserIsAbleToNavigateToVisitDetailsPageByClickingOnAnyRecentVisit()
			throws Throwable {
		Assert.assertTrue(
				prescriptionsPage.verifyThatUserIsAbleToNavigateToVisitDetailsPageByClickingOnAnyRecentVisit());
	}

	@Test(priority = 6, description = "Verify that user can call patient", enabled = true)
	public void IDA4_2089_verifyThatUserCanCallPatient() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyThatUserCanCallPatient());
	}

	@Test(priority = 7, description = "Verify that user can send whatsapp message to the patient", enabled = true)
	public void IDA4_2090_verifyThatUserCanSendWhatsappMessageToThePatient() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyThatUserCanSendWhatsappMessageToThePatient());
	}

	@Test(priority = 8, description = "Verify user is able to view visit summary page", enabled = true)
	public void IDA4_2092_verifyUserIsAbleToViewVisitSummaryPage() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyUserIsAbleToViewVisitSummaryPage());
	}

	@Test(priority = 9, description = "Verify that user can navigate to prescription page", enabled = true)
	public void IDA4_2094_verifythatUserCanNavigateToPrescriptionPage() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifythatUserCanNavigateToPrescriptionPage());
	}

	@Test(priority = 10, description = "Verify that user is able to download the prescription", enabled = true)
	public void IDA4_2099_verifyThatUserIsAbleToDownloadThePrescription() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyThatUserIsAbleToDownloadThePrescription());
	}

	@Test(priority = 11, description = "Verify user can print the prescription", enabled = true)
	public void IDA4_2100_verifyUserCanPrintThePrescription() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyUserCanPrintThePrescription());
	}

	@Test(priority = 12, description = "Verify that user can share the prescription to patient", enabled = true)
	public void IDA4_2101_verifyThatUserCanShareThePrescriptionToPatient() throws Throwable {
		// change method name to, mobile number pre filled
		prescriptionsPage.visitDetailsPrescriptionShare();
		Assert.assertTrue(prescriptionsPage.verifyThatUserCanShareThePrescriptionToPatient());
	}

	@Test(priority = 13, description = "Verify user can share through whatsapp", enabled = true)
	public void IDA4_2102_verifyUserCanShareThroughWhatsapp() throws Throwable {

		prescriptionsPage.visitDetailsPrescriptionShare();
		Assert.assertTrue(prescriptionsPage.verifyUserCanShareThroughWhatsapp());
	}

	@Test(priority = 14, description = "Verify Home navigates to home page", enabled = true)
	public void IDA4_2104_verifyHomeNavigatesToHomePage() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyHomeNavigatesToHomePage());
	}

	@Test(priority = 18, description = "Verify that user can End visit", enabled = true)
	public void IDA4_2105_verifyThatUserCanEndVisit() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyThatUserCanEndVisitFromPrescritionScreen());
	}

	@Test(priority = 19, description = "Verify user can end visit", enabled = true)
	public void IDA4_2106_verifyUserCanEndVisit() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyUserCanEndVisitFromVisitDetailsScreen());
	}

	@Test(priority = 2, description = "Verify user will get the follow up alert pop up when End the visit", enabled = true)
	public void IDA4_2107_verifyUserWillGetTheFollowUpAlertPopUpWhenEndTheVisit() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyUserWillGetTheFollowUpAlertPopUpWhenEndTheVisit());
	}

	@Test(priority = 16, description = "Verify the Awaiting prescription notification is displayed along with the count", enabled = true)
	public void IDA4_2109_verifyTheAwaitingPrescriptionNotificationIsDisplayedAlongWithTheCount() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyTheAwaitingPrescriptionNotificationIsDisplayedAlongWithTheCount());
	}

	@Test(priority = 17, description = "Verify that recent visits display in list", enabled = true)
	public void IDA4_2110_verifyThatRecentVisitsDisplayInList() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyThatRecentVisitsDisplayInList());
	}

	@Test(priority = 20, description = "Verify that user can end visit", enabled = true)
	public void IDA4_2113_verifyThatUserCanEndPendingVisit() throws Throwable {
		Assert.assertTrue(prescriptionsPage.verifyThatUserCanEndPendingVisit());
	}

	@AfterMethod
	public void afterMethod() {
		closeApp();
		System.gc();
	}

}
