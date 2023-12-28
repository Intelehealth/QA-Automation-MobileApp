package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Locale;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AddNewPatientPage;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.FindPatientPage;
import com.intelehealth.pages.StartVisit1And2StepsPage;
import com.intelehealth.pages.VisitSummaryPage;
import com.intelehealth.utils.TestUtils;

public class VisitSummaryTest extends BaseTest {

	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	// JSON object to store test data
	JSONObject appData;
	FindPatientPage findPatientPage;
	StartVisit1And2StepsPage startVisit1And2StepsPage;
	AddNewPatientPage addNewPatientPage;
	VisitSummaryPage visitSummaryPage;

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
		startVisit1And2StepsPage = new StartVisit1And2StepsPage();
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
		appSetupPage.handlePermissions();
		
      appSetupPage.completeSetup();
		

	}

	@Test(priority = 1, description = "Verify edit option functionality beside patient name", enabled = false)
	public void IDA4_2175_verifyEditOptionFunctionalityBesidePatientName() throws Throwable {
		visitSummaryPage.verifyEditOptionFunctionalityBesidePatientName();
	}
	
	@Test(priority = 2, description = "Verify the changes are saved when patient details are updated", enabled = false)
	public void IDA4_2176_verifyTheChangesAreSavedWhenPatientDetailsAreUpdated() throws Throwable {
		visitSummaryPage.verifyTheChangesAreSavedWhenPatientDetailsAreUpdated();
	}

	@Test(priority = 3, description = "Verify clicking on Add additional document", enabled = false)
	public void IDA4_2183_verifyClickingOnAddAdditionalDocument() throws Throwable {
		visitSummaryPage.verifyClickingOnAddAdditionalDocument();
	}

	@Test(priority = 4, description = "Verify user is able to click photo", enabled = false)
	public void IDA4_2185_verifyUserIsAbleToClickPhoto() throws Throwable {
		visitSummaryPage.verifyUserIsAbleToClickPhoto();
	}

	@Test(priority = 5, description = "Verify choose from gallery option to add document", enabled = false)
	public void IDA4_2186_verifyChooseFromGalleryOptionToAddDocument() throws Throwable {
		visitSummaryPage.verifyChooseFromGalleryOptionToAddDocument();
	}
	
	@Test(priority = 6, description = "Verify clicking on close button on the document uploaded", enabled = false)
	public void IDA4_2190_verifyClickingOnCloseButtonOnTheDocumentUploaded() throws Throwable {
		visitSummaryPage.verifyClickingOnCloseButtonOnTheDocumentUploaded();
	}

	@Test(priority = 7, description = "Verify Doctor's speciality drop down", enabled = false)
	public void IDA4_2192_verifyDoctorSpecialityDropdown() throws Throwable {
		visitSummaryPage.verifyDoctorSpecialityDropdown();
	}

	@Test(priority = 8, description = "Verify if priority visit is enabled ON", enabled = false)
	public void IDA4_2194_verifyIfPriorityVisitIsEnabledON() throws Throwable {
		visitSummaryPage.verifyIfPriorityVisitIsEnabledON();
	}

	@Test(priority = 9, description = "Verify behavior on clicking Yes in send visit popup", enabled = false)
	public void IDA4_2200_verifyBehaviorOnClickingYesInSendVisitPopup() throws Throwable {
		visitSummaryPage.addPatients();
		visitSummaryPage.verifyBehaviorOnClickingYesInSendVisitPopup();
	}

	@Test(priority = 10, description = "Verify clicking on Appointment button functionality after visit is sent to doctor", enabled = false)
	public void IDA4_2202_verifyClickingOnAppointmentButtonFunctionalityAfterVisitIsSentToDoctor() throws Throwable {
		visitSummaryPage.verifyClickingOnAppointmentButtonFunctionalityAfterVisitIsSentToDoctor();
	}
	
	@Test(priority = 11, description = "Verify if booked appointment is reflecting in appointments", enabled = false)
	public void IDA4_2203_verifyIfBookedAppointmentIsReflectingInAppointments() throws Throwable {
		visitSummaryPage.verifyIfBookedAppointmentIsReflectingInAppointments();
	}

	@AfterMethod
	public void afterMethod() {
		closeApp();
		System.gc();
	}

}
