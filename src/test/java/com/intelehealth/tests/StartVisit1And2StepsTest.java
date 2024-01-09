
package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AddNewPatientPage;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.StartVisit1And2StepsPage;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.TestUtils;

public class StartVisit1And2StepsTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	StartVisit1And2StepsPage startVisit1And2StepsPage;
	AddNewPatientPage addNewPatientPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws InterruptedException, IOException {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");

		resetApp();
		launchApp();

		appSetupPage = new AppSetupPage();
		addNewPatientPage = new AddNewPatientPage();
		startVisit1And2StepsPage = new StartVisit1And2StepsPage();
		InputStream datais = null;
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
		// Complete the setup process with a valid user's credentials
		appSetupPage.completeSetup();
		// Register a new patient using provided personal and address details
		addNewPatientPage.registerAPatient(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));

	}

	@Test(priority = 1, description = "Verify the UI of first page of Vitals (1/4 Vitals)", enabled = true)
	public void IDA4_2413_verifyVitalsScreenUi() throws InterruptedException, IOException {

// Verify UI elements of the First Vitals Screen on the Start Visit page
		startVisit1And2StepsPage.verifyUiOfFirstVitalsScreen();

	}

	@Test(priority = 2, description = "Verify the functionality of Next button on 1st vital screen", enabled = true)
	public void IDA4_2415_verifyNextButtonFunctionality() throws InterruptedException {

// Click on the "Next" button on the First Vitals Screen of the Start Visit page
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

// Verify that the Vitals Summary Screen is displayed after clicking the "Next" button
		startVisit1And2StepsPage.verifyVitailsSummaryScreenIsDisplayed();

	}

	@Test(priority = 3, description = "Verify the functionality of BM index(auto-calculated) textfield on 1st vital screen", enabled = true)
	public void IDA4_2419_verifyBMIValueIsAutoCalculated() throws JSONException, InterruptedException {
// Enter the patient's height as "160" on the Start Visit page
		startVisit1And2StepsPage.enterHeight(appData.getJSONObject("patientVitalsDetails").getString("height"));

// Enter the patient's weight as "60" on the Start Visit page
		startVisit1And2StepsPage.enterWeight(appData.getJSONObject("patientVitalsDetails").getString("weight"));

// Verify that BMI is automatically calculated and matches the expected BMI index value
		startVisit1And2StepsPage
				.verifyBmiIsAutoCalculated(appData.getJSONObject("patientVitalsDetails").getString("bMIIndexValue"));

	}

	@Test(priority = 4, description = "Verify the UI of Vital summary", enabled = true)
	public void IDA4_2429_verifyTheUiOfVitalSummary() throws InterruptedException {
// Click on the "Next" button on the First Vitals Screen of the Start Visit page
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

// Verify the UI elements on the Vitals Summary Screen after clicking the "Next" button
		startVisit1And2StepsPage.verifyVitalslSummaryUi();

	}

	@Test(priority = 5, description = "Verify the status of BMI index by changing different height & weight and if editable field", enabled = true)
	public void IDA4_2421_checkBMIStatusWithDifferentValues() throws JSONException, InterruptedException {
// Verify status for a patient with normal weight based on height  and weight 
		startVisit1And2StepsPage.verifyStatusForNormalWeightPatient(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("weightStatus").getString("expectedStatus1"));

// Verify status for an underweight patient based on height  and weight 
		startVisit1And2StepsPage.verifyStatusForUnderWeightPatient(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("weights").getString("weight1"),
				appData.getJSONObject("weightStatus").getString("expectedStatus2"));

// Verify status for a patient with moderate obesity (Class 1) based on height and weight 
		startVisit1And2StepsPage.verifyStatusModerateObesityWeightPatient(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("weights").getString("weight2"),
				appData.getJSONObject("weightStatus").getString("expectedStatus3"));

// Verify status for a patient with severe obesity (Class 2) based on height  and weight 
		startVisit1And2StepsPage.verifyStatusForSevereObesityWeightpatient(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("weights").getString("weight3"),
				appData.getJSONObject("weightStatus").getString("expectedStatus4"));

// Verify status for a patient with very severe (morbid) obesity (Class 3) based on height  and weight 
		startVisit1And2StepsPage.verifyStatusForVerySevereObesityWeightPatient(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("weights").getString("weight4"),
				appData.getJSONObject("weightStatus").getString("expectedStatus5"));

	}

	@Test(priority = 6, description = "Verify the details section on Vital summary", enabled = true)
	public void IDA4_2430_verifyDeatilsOnVitalSummaryScreen() {
// Enter patient vitals details using data from the appData object
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

// Click on the "Next" button on the First Vitals Screen of the Start Visit page
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

// Verify patient vitals details on the Vitals Summary Screen
		startVisit1And2StepsPage.verifyPatientVitalsDetails(
				appData.getJSONObject("vitalsSummaryDetails").getString("height"),
				appData.getJSONObject("vitalsSummaryDetails").getString("weight"),
				appData.getJSONObject("vitalsSummaryDetails").getString("bp"),
				appData.getJSONObject("vitalsSummaryDetails").getString("pulse"),
				appData.getJSONObject("vitalsSummaryDetails").getString("temperature"),
				appData.getJSONObject("vitalsSummaryDetails").getString("spo2"),
				appData.getJSONObject("vitalsSummaryDetails").getString("respiratoryRate"),
				appData.getJSONObject("vitalsSummaryDetails").getString("bMI"));

	}

	@Test(priority = 7, description = "Verify clicking on the change button of details section on Vital summary", enabled = true)
	public void IDA4_2432_VerifyChangeButtonOfVitalSummaryScreen() {
// Entering patient vitals details on the startVisit1And2StepsPage
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

// Checking if the entered vitals details are editable on the update screen
		startVisit1And2StepsPage.checkVitalsUpdateScreenDetailsEditable(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

	}

	@Test(priority = 8, description = "Verify that the updated 1/4 details are saved", enabled = true)
	public void IDA4_2433_VerifyVitalsdetailsAreSaved() {
// Entering patient vital details using the appData JSON object
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"), // Height
				appData.getJSONObject("patientVitalsDetails").getString("weight"), // Weight
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"), // Blood Pressure (Systolic)
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"), // Blood Pressure (Diastolic)
				appData.getJSONObject("patientVitalsDetails").getString("pulse"), // Pulse
				appData.getJSONObject("patientVitalsDetails").getString("temperature"), // Temperature
				appData.getJSONObject("patientVitalsDetails").getString("spo2"), // Oxygen Saturation (SpO2)
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate") // Respiratory Rate
		);

// Clicking on the next button after entering vital details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

// Verifying entered patient vital details against expected values
		startVisit1And2StepsPage.verifyPatientVitalsDetails("160 cm", // Expected Height
				"60 kg", // Expected Weight
				"120/70", // Expected Blood Pressure
				"72 bpm", // Expected Pulse
				"98.1", // Expected Temperature
				"98 %", // Expected Oxygen Saturation (SpO2)
				"72 breaths/min", // Expected Respiratory Rate
				"23.44 kg/m" // Expected BMI (Body Mass Index)
		);

	}

	@Test(priority = 9, description = "Verify the functionality of Confirm button on 1/4 vital summary screen", enabled = true)
	public void IDA4_2435_verifyConfirmButtonfuntionality() {
// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Verifying that the visit reason screen is displayed
		startVisit1And2StepsPage.verifyVisitReasonScreenIsDisplayed();
	}

	@Test(priority = 10, description = "Verify the functionality of Selected reason section", enabled = true)
	public void IDA4_2438_verifyFuntionalityOfSelectedReason() {
// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
//Clicks on the 1/4 vitals screen next button
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
//Clicks On confirm button
		startVisit1And2StepsPage.clickOnConfirmButton();
//Select the Abdominal pain reason
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
//Verify the abdominal pain is selected
		startVisit1And2StepsPage.verifyVisitReasonAbdominalPainIsSelected();

	}

	@Test(priority = 11, description = "Verify user can remove the selected reason", enabled = true)
	public void IDA4_2439_VerifyTheFunctionalityOfRemoveOption() {
// Start entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

// Click on the "Next" button after entering patient vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// click On confirm button
		startVisit1And2StepsPage.clickOnConfirmButton();
// Select the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

// Verify that the selected reason is removed
		startVisit1And2StepsPage.verifySelectedReasonIsRemoved();

	}

	@Test(priority = 12, description = "Verify whether all selected reasons from All reason section are getting reflected under Selected reason section", enabled = true)
	public void IDA4_2440_verifySelectedReasons() throws InterruptedException {
// Enter patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Click on the "Next" button after entering vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// click On confirm button
		startVisit1And2StepsPage.clickOnConfirmButton();
// Select all reasons on the page
		startVisit1And2StepsPage.verifyAllSelectedReasonsAreDisplayed();

	}

	@Test(priority = 13, description = "Verify the functionality of Back button after selecting a reason on 2/4 visit reason screen", enabled = true)
	public void IDA4_2452_verifySelectedReasons() {
		// Enter patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// Click on the "Next" button after entering vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// click On confirm button
		startVisit1And2StepsPage.clickOnConfirmButton();
		// Select visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		// click On back button
		startVisit1And2StepsPage.clickOnBackButton();
		// Verify VitalssummaryScreen Is displayed
		startVisit1And2StepsPage.verifyVitailsSummaryScreenIsDisplayed();

	}

	@Test(priority = 14, description = "Verify the functionality of Next button on 2/4 Visit reason screen", enabled = true)
	public void IDA4_2454_VerifyConfirmVisitPopup() {
		// Enter patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// Click on the "Next" button after entering vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// click On confirm button
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		// Select visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		// Click next button
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// verify confirmvisit popup is displayed
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
	}

	@Test(priority = 15, description = "Verify clicking on No button in Confirm visit reason popup", enabled = true)
	public void IDA4_2455_VerifyConfirmVisitPopupNoButtonFuntionality() {
		// Enter patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// Click on the next button after entering vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// Confirm the entered patient vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		// Select visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		// Click on the next button after selecting visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// Verify the confirm visit popup
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		// Click on the "No" button in the confirm visit popup
		startVisit1And2StepsPage.clickOnNoButton();
		// Verify that the visit reason screen is displayed
		startVisit1And2StepsPage.verifyVisitReasonScreenIsDisplayed();

	}

	@Test(priority = 16, description = "Verify clicking on Yes button in Confirm visit reason popup", enabled = true)
	public void IDA4_2456_VerifyConfirmVisitPopupYesButtonFuntionality() {
		// Enter patient vitals details using data from the appData object
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// Navigate to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// Confirm the entered patient vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		// Select the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		// Proceed to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// Verify and handle the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		// Verify that the first question related to abdominal pain is displayed
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();

	}

	@Test(priority = 17, description = "Verify the options displayed for the question", enabled = true)
	public void IDA4_2459_VerifyOptions() {
		// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// Clicking next after entering vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		// Confirming the entered vitals (1/4 steps)

		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		// Choosing 'Abdominal Pain' as the visit reason
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		// Moving to the next step
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		// Verifying the confirmation popup
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		// Confirming the visit
		startVisit1And2StepsPage.clickOnYesButton();
		// Verifying the display of the first abdominal question
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		// Verifying that all options are displayed

		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		// Choosing 'Upper Right Hypochondrium'
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		// Choosing 'All Over'
		startVisit1And2StepsPage.clickAllOver();

	}

	@Test(priority = 18, description = "Verify the functionality of Submit button on 1st question", enabled = true)
	public void IDA4_2462_verifySubmitButtonFuntionality() {
		// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		// Navigating to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Confirming the entered patient vitals and moving to the next step
		startVisit1And2StepsPage.clickOnConfirmButton();

		// Selecting the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// Proceeding to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();

		// Verifying the confirmation popup for the visit and accepting it
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();

		// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();

		// Verifying that all options for the question are displayed
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();

		// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();

		// Submitting the selected option and proceeding to the next step
		startVisit1And2StepsPage.clickOnSubmitButton();

		// Verifying the display of the second out of twelve questions
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();

	}

	@Test(priority = 19, description = "Verify whether user able to go to 3rd question without selecting option/answer for 2nd question", enabled = true)
	public void IDA4_2464_verifyUserCanGoTo3rdQuestionWithoutSelecting2ndQuestion() {
		// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		// Navigating to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Confirming the entered patient vitals and moving to the next step
		startVisit1And2StepsPage.clickOnConfirmButton();

		// Selecting the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// Proceeding to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();

		// Verifying the confirmation popup for the visit and accepting it
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();

		// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();

		// Verifying that all options for the question are displayed
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();

		// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();

		// Submitting the selected option and proceeding to the next step
		startVisit1And2StepsPage.clickOnSubmitButton();

		// Verifying the display of the second out of twelve questions
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();

		// Verifying that the third out of twelve questions is not present
		startVisit1And2StepsPage.threeOfTwelveQuestioNotPresent();

	}

	@Test(priority = 20, description = "Verify after selecting one option from 2nd question option list 'pain radiates to' option selected", enabled = true)
	public void IDA4_2465_selectPainRadiatesToOptionAndVerifyDescribeSectionDisplayed() throws InterruptedException {
		// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		// Navigating to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Confirming the entered patient vitals and moving to the next step
		startVisit1And2StepsPage.clickOnConfirmButton();

		// Selecting the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// Proceeding to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();

		// Verifying the confirmation popup for the visit and accepting it
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();

		// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();

		// Verifying that all options for the question are displayed
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();

		// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();

		// Submitting the selected option and proceeding to the next step
		startVisit1And2StepsPage.clickOnSubmitButton();

		// Verifying the display of the second out of twelve questions
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();

		// Selecting the option related to pain radiating
		startVisit1And2StepsPage.selectPainRadiatesOption();

		// startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();

		// Verifying that options related to pain radiation are displayed
		startVisit1And2StepsPage.verifyPainRadiatesToOptionsAreDisplayed();

	}

	@Test(priority = 21, description = "Verify the Describe section of 2 of 12 questions screen", enabled = true)
	public void IDA4_2466_VerifyDescribeSection() throws InterruptedException {
		// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		// Navigating to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Confirming the entered patient vitals and moving to the next step
		startVisit1And2StepsPage.clickOnConfirmButton();

		// Selecting the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// Proceeding to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();

		// Verifying the confirmation popup for the visit and accepting it
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();

		// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();

		// Verifying that all options for the question are displayed
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();

		// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();

		// Submitting the selected option and proceeding to the next step
		startVisit1And2StepsPage.clickOnSubmitButton();

		// Verifying the display of the second out of twelve questions
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();

		// Selecting the option related to pain radiating
		startVisit1And2StepsPage.selectPainRadiatesOption();

		// Introducing a delay for 1000 milliseconds (1 second) using Thread.sleep

		// Verifying that options related to pain radiation are displayed
		startVisit1And2StepsPage.verifyPainRadiatesToOptionsAreDisplayed();

		// Introducing a longer delay for 2000 milliseconds (2 seconds)

		// Performing a scroll to the end action on the screen
		startVisit1And2StepsPage.scrollToEndAction();

		// startVisit1And2StepsPage.scrollUpToSkipButtonOnPainRadiatesScreen();

		// Verifying the display of the submit button
		startVisit1And2StepsPage.verifySubmitButtonIsDisplayed();

	}

	@Test(priority = 22, description = "Verify by not selecting any options and click on submit button for describe section of 2 of 12", enabled = true)
	public void IDA4_2467_VerifyDescribeSection() {
		// Entering patient vitals details using data from the appData JSON object
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		// Navigating to the next step in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();

		// Confirming the entered patient vitals and moving to the next step
		startVisit1And2StepsPage.clickOnConfirmButton();

		// Selecting the visit reason as Abdominal Pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();

		// Proceeding to the next step after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();

		// Verifying the confirmation popup for the visit and accepting it
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();

		// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();

		// Verifying that all options for the question are displayed
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();

		// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();

		// Submitting the selected option and proceeding to the next step
		startVisit1And2StepsPage.clickOnSubmitButton();

		// Verifying the display of the second out of twelve questions
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();

		// Selecting the option related to pain radiating
		startVisit1And2StepsPage.selectPainRadiatesOption();

		// Clicking on the submit button for the pain radiates question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();

		// Verifying the display of the third out of twelve questions
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

	}

	@Test(priority = 23, description = "Verify selecting any one number in 3 of 12", enabled = true)
	public void IDA4_2470_selectNumberAndVerify() throws InterruptedException {
		// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		// Moving to the next steps in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4

		// Selecting visit reason as abdominal pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();

		// Handling abdominal pain questionnaire
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		// Handling number spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();

	}

	@Test(priority = 24, description = "Verify selecting any one number in 3 of 12", enabled = true)
	public void IDA4_2474_selectNumberAndVerify() throws InterruptedException {
		// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		// Moving to the next steps in the visit process
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4

		// Selecting visit reason as abdominal pain
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		// Handling number spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();

		// Handling duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();

	}

	@Test(priority = 25, description = "Verify clicking on submit button by selecting both Number and duration type in 3 of 12", enabled = true)
	public void IDA4_2476_verifySubmitButtonOfThreeOfElevenQuestion() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 26, description = "Verify whether user able to select any options in 4 of 12", enabled = true)
	public void IDA4_2478_selectOptionInFourOfTwelve() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
	}

	@Test(priority = 27, description = "Verify when user selects any option other than Others[Describe]in 4 of 12", enabled = true)
	public void IDA4_2479_verifyFiveOfTwelveQuestionIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
	}

	@Test(priority = 28, description = "Verify clicking on Others [Describe] section in 4 of 12", enabled = true)
	public void IDA4_2480_verifyClickingOnOthersOption() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnOtherOptionOfFourOfTwelveQuestions();
		startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton();

	}

	@Test(priority = 29, description = "Verify entering any data in describe field and click on submit button in 4 of 12", enabled = true)
	public void IDA4_2483_enterDataInDescribeFieldAndclickSubmitButton() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnOtherOptionOfFourOfTwelveQuestions();
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));

		startVisit1And2StepsPage.scrollToSubmitButtonOfFourOfTwelvequestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfFourOfTwelveQuestion();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
	}

	@Test(priority = 30, description = "Verify whether user able to select any options in 5 of 12", enabled = true)
	public void IDA4_2485_verifyUserAbleToSelectOptionOfFiveOfTwelveQuestions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
// startVisit1And2StepsPage.clickOnOtherOptionOfFourOfTwelveQuestions();
// startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));

		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 31, description = "Verify when user selects any option other than Others[Describe] in 5 of 12", enabled = true)
	public void IDA4_2486_verifyWhenUserSelectsOptionInFiveOfTwelveQuestions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 32, description = "Verify when user selects any option other than Others[Describe] in 5 of 12", enabled = true)
	public void IDA4_2487_verifyFiveOfTwelveQuestionsDescribeTextBoxIsDisplayedWithSubmitButton()
			throws InterruptedException {

		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectOtherOptionOfFiveOfTwelveQuestions();
		startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton();
	}

	@Test(priority = 33, description = "Verify entering any data in describe field and click on submit button in 5 of 12", enabled = true)
	public void IDA4_2489_verifySixOfTwelevQuestionIsAutoPopulated() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.clickOnSubmitButtonOfFourOfTwelveQuestion();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectOtherOptionOfFiveOfTwelveQuestions();
		startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton();
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));
		startVisit1And2StepsPage.clickOnFiveOfTwelveQuestionsSubmitButton();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 34, description = "Verify whether user able to select any options in 6 of 12", enabled = true)
	public void IDA4_2491_verifyUserAbleToSelectAnyOptionsOfSixOfTwelveQusetions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 35, description = "Verify clicking on Others [Describe] section in 6 of 12", enabled = true)
	public void IDA4_2493_verifyClickingOnOtherOptionDisplaysDescribeTextArea() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectOtherOptionOfSixOfTwelveQuestions();
		startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton();

	}

	@Test(priority = 36, description = "Verify entering any data in describe field or select any option and click on submit button in 6 of 12", enabled = true)
	public void IDA4_2496_verifySixOftwelveQuestionsSubmitButtonFuntionality() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 37, description = "Verify whether user able to select any options in 7 of 12", enabled = true)
	public void IDA4_2498_verifyUserAbleToSelectAnyOptionInSevenOfTwelveQuestions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
	}

	@Test(priority = 38, description = "Verify whether user able to select any options in 7 of 12", enabled = true)
	public void IDA4_2499_verifyEightOfTwelveQuetionsIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
	}

	@Test(priority = 39, description = "Verify whether user able to select any options in 8 of 12", enabled = true)
	public void IDA4_2501_verifyUserAbleToSelectAnyOptionInEightOfTwelveQuestions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnEightOfTwelveSubmitButton();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 40, description = "Verify clicking on Others [Describe] section in 8 of 12", enabled = true)
	public void IDA4_2503_verifyEightOfTwelveQuestionsDescribeTextBoxIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.clickOnOtherOptonOfEightOfTwelveQusetions();
		startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton();
	}

	@Test(priority = 41, description = "Verify entering any data in describe field or select any option and click on submit button in 8 of 12", enabled = true)
	public void IDA4_2507_enterValueInDescribeTextBoxOFEightOfTwelveQuestions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.clickOnOtherOptonOfEightOfTwelveQusetions();
		startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton();
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));
		startVisit1And2StepsPage.clickOnEightOfTwelveSubmitButton();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 42, description = "Verify whether user able to select one or more options in 9 of 12", enabled = true)
	public void IDA4_2509_selectOneOrMoreOptionInnineOfTwelveQuestions() throws InterruptedException {
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectMildOneThreeOption();
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnEightOfTwelveSubmitButton();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectfoodOption();
		startVisit1And2StepsPage.selectLeaningForward();
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 43, description = "Verify clicking on Others [Describe] section in 9 of 12", enabled = true)
	public void IDA4_2511_verifyDescribeTextAreaIsDisplayedInNineOfTwelveQuestions() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
//Clicking on gradual option
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnEightOfTwelveSubmitButton();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectOtherOption();
		startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton();
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 44, description = "Verify entering any data in describe field or select any option and click on submit button in 9 of 12", enabled = true)
	public void IDA4_2515_verifyTenOfTwelveQuestionsIsAutoPopulated() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectfoodOption();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
	}

	@Test(priority = 45, description = "Verify whether user able to select any options in 10 of 12", enabled = true)
	public void IDA4_2517_verifyUserAbleToSelectOptionInTenOftTwelveQuestions() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectfoodOption();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
// Select the option indicating that menstruation has not started
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();

// Verify that eleven out of twelve questions are displayed
		startVisit1And2StepsPage.verifyElevenOfTwelveQuestionsIsDisplayed();

	}

	@Test(priority = 46, description = "Verify when user selects ''Is menstruating'' option in 10 of 12", enabled = true)
	public void IDA4_2519_verifyIsMenstruatingOptionInTenOftTwelveQuestions() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectfoodOption();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
// Select the option indicating that the user is currently menstruating
		startVisit1And2StepsPage.selectIsMenstruatingOption();

// Verify that the age onset input field is displayed after selecting
// menstruation
		startVisit1And2StepsPage.verifyAgeAtOnSetIsDisplayed();

// Enter the age of 41 in the age onset input field
		startVisit1And2StepsPage.enterAgeInAgeOnSetFeild("41");

// Click on the submit button to proceed to the next set of questions
		startVisit1And2StepsPage.clickOnSubmitButtonOfTenOftwelveQuestions();

// Verify that information about the last menstruation period is displayed
		startVisit1And2StepsPage.verifyLastMenstruationPeriodIsDisplayed();

// Verify that a calendar interface for selecting dates is displayed
		startVisit1And2StepsPage.verifyCalenderIsDisplayed();

	}

	@Test(priority = 47, description = "Verify click on last menstruation period option in 10 of 12", enabled = true)
	public void IDA4_2520_verifyLastMenstruationPeriodOptionInTenOftTwelveQuestions() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectfoodOption();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
// Select the option indicating that the user is currently menstruating
		startVisit1And2StepsPage.selectIsMenstruatingOption();

// Verify that the age onset input field is displayed after selecting
// menstruation
		startVisit1And2StepsPage.verifyAgeAtOnSetIsDisplayed();

// Enter the age of 41 in the age onset input field
		startVisit1And2StepsPage.enterAgeInAgeOnSetFeild("41");

// Click on the submit button to proceed to the next set of questions
		startVisit1And2StepsPage.clickOnSubmitButtonOfTenOftwelveQuestions();

// Verify that information about the last menstruation period is displayed
		startVisit1And2StepsPage.verifyLastMenstruationPeriodIsDisplayed();

// Verify that a calendar interface for selecting dates is displayed
		startVisit1And2StepsPage.verifyCalenderIsDisplayed();

		startVisit1And2StepsPage.selectmenstruationDate();
		startVisit1And2StepsPage.verifySelectedMenstruationDateIsDisplayed("01/Oct/2023");
	}

	@Test(priority = 48, description = "Verify entering any data in describe field or select any option and click on submit button in 10 of 12", enabled = true)
	public void IDA4_2522_verifyElevenOftweOfTwelveQusetionsIsDisplayed() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectfoodOption();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
// Select the option indicating that the user has not started menstruation
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();

// Verify that the eleventh question of the twelve-question set is displayed
		startVisit1And2StepsPage.verifyElevenOfTwelveQuestionsIsDisplayed();

	}

	@Test(priority = 49, description = "Verify whether user able to select any options in 11 of 12", enabled = true)
	public void IDA4_2524_verifyUserAbleToSelectAnyOptionInelevenOfTwelveQuestions() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectfoodOption();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
// Select the option indicating that the user has not started menstruation
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();

// Verify that the eleventh question of the twelve-question set is displayed
		startVisit1And2StepsPage.verifyElevenOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.selectNoneOption();

	}

	@Test(priority = 50, description = "Verify clicking on Yes [Describe] section in 11 of 12", enabled = true)
	public void IDA4_2527_verifyDescribeTextAreaIsDisplayedInElevenOfTwelveQuestions() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectfoodOption();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
// Select the option indicating that the user has not started menstruation
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();

// Verify that the eleventh question of the twelve-question set is displayed
		startVisit1And2StepsPage.verifyElevenOfTwelveQuestionsIsDisplayed();
// Select the "None" option on the startVisit1And2StepsPage.
		startVisit1And2StepsPage.selectNoneOption();
// Select the "Yes, Describe" option on the startVisit1And2StepsPage.
		startVisit1And2StepsPage.selectYesDescribeOption();
// Verify that the describe block is displayed along with the submit button on
// the startVisit1And2StepsPage.
		startVisit1And2StepsPage.verifyDescribeBlockIsDisplayedWithSubmitButton();

	}

	@Test(priority = 51, description = "Verify entering any data in describe field or select any option and click on submit button in 11 of 12", enabled = true)
	public void IDA4_2530_verifyTwelveOfTwelveQuestionsIsDisplayed() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectfoodOption();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
// Select the option indicating that the user has not started menstruation
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();

// Verify that the eleventh question of the twelve-question set is displayed
		startVisit1And2StepsPage.verifyElevenOfTwelveQuestionsIsDisplayed();
// Select the "None" option on the startVisit1And2StepsPage.
		startVisit1And2StepsPage.selectNoneOption();
// Verify that all twelve questions are displayed
		startVisit1And2StepsPage.verifyTwelveOfTwelveQusetionsIsDisplayed();

	}

	@Test(priority = 52, description = "Verify clicking on Skip button in 12 of 12", enabled = true)
	public void IDA4_2533_verifyAssociatedSymptomsQuestionIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

// Verify that the title for associated symptoms is displayed
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();

// Verify that the "Do you have the following symptom" question is displayed on
// the startVisit1And2StepsPage
		startVisit1And2StepsPage.verifyDoYouHaveFollowingSymptomIsDisplayed();

	}

	@Test(priority = 53, description = "Verify entering any data in describe field and click on submit button in 12 of 12", enabled = true)
	public void IDA4_2534_enterDataInAssociatedTextFieldAndSubmit() throws InterruptedException {
// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
// Clicking on the next button after entering vitals details
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
// Confirming the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton(); // 1/4
// Selecting visit reason as "Abdominal Pain"
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
// Clicking on the next button after selecting the visit reason
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
// Verifying the confirmation popup for the visit
		startVisit1And2StepsPage.verifyConfirmVisitPopup();
// Confirming the visit by clicking on "Yes"
		startVisit1And2StepsPage.clickOnYesButton();
// Verifying the display of the first question related to abdominal pain
		startVisit1And2StepsPage.verifyAbdominalFirstQuestionIsDisplayed();
// Verifying the display of all available options for the question
		startVisit1And2StepsPage.verifyAllOptionsAreDisplayed();
// Selecting the option "Upper Right Hypochondrium"
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
// Clicking on the submit button after selecting the option
		startVisit1And2StepsPage.clickOnSubmitButton();
// Verifying the display of the second question
		startVisit1And2StepsPage.verifyTwoOfTwelveQuestionIsDisplayed();
// Selecting the option "Pain Radiates"
		startVisit1And2StepsPage.selectPainRadiatesOption();
// Clicking on the "Groin" option
		startVisit1And2StepsPage.clickOnGroin();
// Clicking on the submit button for the "Pain Radiates" question
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
// Verifying the display of the third question
		startVisit1And2StepsPage.verifyThreeOfTweleveQuestionsIsDisplayed();

// Selecting the number "2" from the spinner
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.verifyNumberTwoIsSelected();
// Selecting "Days" from the duration spinner
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.verifyDaysIsSelected();
// Clicking on the submit button for the third question
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
// Verifying the display of the fourth question
		startVisit1And2StepsPage.verifyFourOfTwelveQuestionsIsDisplayed();
		startVisit1And2StepsPage.clickOnGradualOption();
// Verifying the display of the fifth question
		startVisit1And2StepsPage.verifyFiveOfTwelveQuestonIsDisplayed();

		startVisit1And2StepsPage.selectNightOption();
// Verifying the display of the sixth question
		startVisit1And2StepsPage.verifySixOfTwelveQuestionsIsDisplayed();
// Clicking on the "Constant" option for the sixth question
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
// Clicking on the submit button for the sixth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
// Verifying the display of the seventh question
		startVisit1And2StepsPage.verifySevenOfTwelveQuestionsIsDisplayed();
// Selecting the "Mild (1-3)" option for the seventh question
		startVisit1And2StepsPage.selectMildOneThreeOption();
// Verifying the display of the eighth question
		startVisit1And2StepsPage.verifyEightOfTwelvequsetionsIsDisplayed();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.verifyNineOfTwelveQuestionsIsDisplayed();
// Selecting the "Food" option for the ninth question
		startVisit1And2StepsPage.selectfoodOption();
// Clicking on the submit button for the ninth question
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
// Verifying the display of the tenth question
		startVisit1And2StepsPage.verifyTenOfTwelveQuestionsIsDisplayed();
// Select the option indicating that the user has not started menstruation
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();

// Verify that the eleventh question of the twelve-question set is displayed
		startVisit1And2StepsPage.verifyElevenOfTwelveQuestionsIsDisplayed();
// Select the "None" option on the startVisit1And2StepsPage.
		startVisit1And2StepsPage.selectNoneOption();
// Verify that all twelve questions are displayed
		startVisit1And2StepsPage.verifyTwelveOfTwelveQusetionsIsDisplayed();

// Enter the value in the describe text box based on the value from the appData
// JSON object and submit
		startVisit1And2StepsPage.enterValueInDescribeTextBox(appData.getJSONObject("description").getString("value"));

// Verify that the title for associated symptoms is displayed
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();

// Verify that the "Do you have the following symptom" question is displayed
		startVisit1And2StepsPage.verifyDoYouHaveFollowingSymptomIsDisplayed();

	}

	@Test(priority = 54, description = "Verify when user selects yes option for '7. change in frequency of urination[Describe]' symptom", enabled = true)
	public void IDA4_2541_verifyDescribeTextAreaIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
//startVisit1And2StepsPage.clickSymptomsNoButton();
		// Click on the seventh "Yes" button associated with a specific action
		startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();

		// Verify that the text area for describing something is displayed
		startVisit1And2StepsPage.verifyDescribeTextAreaIsDisplayed();

	}

	@Test(priority = 55, description = "Verify when user selects yes option for 8th , 9th and 16th [Describe]'' symptom", enabled = false)
	public void IDA4_2543_verifyAssociatedSymptomsDescribeTextAreaIsDisplayed() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		// startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();
		// Click on the "Yes" button for the eighth associated symptom
//		startVisit1And2StepsPage.clickOnEightAssociatedSymptomYesButton();
		startVisit1And2StepsPage.clickSymptomsNoButton();
		// Verify that the text area for describing symptoms is displayed
		// startVisit1And2StepsPage.verifyDescribeTextAreaIsDisplayed();
		startVisit1And2StepsPage.clickOnEightAssociatedSymptomYesButton();
		startVisit1And2StepsPage.verifyDescribeTextAreaIsDisplayed();
		// Click on the "Yes" button for the ninth associated symptom
		startVisit1And2StepsPage.clickOnNineAssociatedSymptomYesButton();

		// Scroll to the sixteenth associated symptom on the startVisit1And2StepsPage
		startVisit1And2StepsPage.scrollToSixteenAssociatedSymptom();

		// Click on the "Yes" button for the sixteenth associated symptom
		startVisit1And2StepsPage.clickOnSixteenAssociatedSymptomYesButton();

		// Verify that the text area for describing symptoms is displayed again
		startVisit1And2StepsPage.verifyDescribeTextAreaIsDisplayed();

	}

	@Test(priority = 56, description = "Verify on clicking submit button after selecting yes or no option for symptoms", enabled = true)
	public void IDA4_2549_verifyAssociatedSymptomSubmitButton() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		startVisit1And2StepsPage.clickSymptomsNoButton();
		startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();
		startVisit1And2StepsPage.enterDataInDescribeTextFiled("Testing");
		startVisit1And2StepsPage.scrollToEndAction1();

		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();
		startVisit1And2StepsPage.verifyVisitReasonSummaryScreenIsDisplayed();

	}

	@Test(priority = 57, description = "Verify the functionality of change button of Abdominal pain(selected visit reason) on 2/4 Visit Reason summary page", enabled = true)
	public void IDA4_2551_verifyChangeButtonOfAbdominalPain() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		Thread.sleep(2000);
		startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();
		startVisit1And2StepsPage.enterDataInDescribeTextFiled("Testing");
		startVisit1And2StepsPage.scrollToEndAction1();
		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();
		startVisit1And2StepsPage.verifyVisitReasonSummaryScreenIsDisplayed();
		startVisit1And2StepsPage.clickOnAbdominalPainChangeIcon();
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();
		startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();
		startVisit1And2StepsPage.scrollToEightOfTwelveQuestions();
		startVisit1And2StepsPage.selectfoodOption();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.scrollToEndAction1();
		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();
		startVisit1And2StepsPage.verifySelectedFoodOptionIsDisplayed();
		startVisit1And2StepsPage.verifyVomitingIsDisplayed();

	}

	@Test(priority = 58, description = "Verify the functionality of Back button on 2/4 Visit Reason summary page ", enabled = true)
	public void IDA4_2555_verifyFunctionalityOfBackButtonOnVisitReasonSummaryScreen() throws InterruptedException {
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		Thread.sleep(2000);
		startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();
		startVisit1And2StepsPage.enterDataInDescribeTextFiled("Testing");
		startVisit1And2StepsPage.scrollToEndAction1();
		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();
		startVisit1And2StepsPage.verifyVisitReasonSummaryScreenIsDisplayed();
		startVisit1And2StepsPage.clickOnBackButtonOnVisitReasonSummaryScreen();
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();
	}

	@Test(priority = 59, description = "Verify the functionality of Confirm button on 2/4 Visit Reason summary page ", enabled = true)
	public void IDA4_2556_verifyFunctionalityOfConfirmButtonOnVisitReasonSummaryScreen() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));

		Thread.sleep(2000);
		// click On the seven associated symptoms yes button
		startVisit1And2StepsPage.clickOnSevenAssociatedSymptomYesButton();
		// enter data in describe text filed
		startVisit1And2StepsPage.enterDataInDescribeTextFiled("Testing");

		// click on the submit button
		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();
		// verify visit summary screen is displayed
		startVisit1And2StepsPage.verifyVisitReasonSummaryScreenIsDisplayed();
	}

	@Test(priority = 60, description = "Verify the functionality of Associated symptoms section on 2/4 Visit Reason summary page", enabled = false)
	public void IDA4_2553_verifyAssociatedSymptomSectionOnVisitSummaryPage() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// verify the associated symptoms section
		startVisit1And2StepsPage.verifyAssociatedSymptomsSectionOnSummaryPage();

	}

	@Test(priority = 61, description = "Verify whether user can select options for all 20 symptoms", enabled = true)
	public void IDA4_2538_selectAssociatedSymptomsTwentyOptionsAndVerify() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterVitalsAndCompleteVisit(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// verify the associated 20 options
		startVisit1And2StepsPage.selectAllTwentyOptionsForAssociatedSymptoms();

	}

	@Test(priority = 62, description = "Verify whether user able to select one or more options in 11 of 12", enabled = true)
	public void IDA4_2525_verifyUserAllowedSelectOnlyOneOptionInElevenOfTwelveQuestions() throws InterruptedException {
		// enter patient vitals and complete the ten questions

		startVisit1And2StepsPage.enterVitalsDetailsAndCompleteTenQuestions(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// verify user allowed select only one option
		startVisit1And2StepsPage.verifyUserAllowedToSelectOneOptionInElevenOfTwelveQuestion();

	}

	@Test(priority = 63, description = "Verify whether user able to select one or more options in 10 of 12", enabled = true)
	public void IDA4_2518_verifyUserAllowedSelectOnlyOneOptionInElevenOfTwelveQuestions() throws InterruptedException {

		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// verify user allowed to select one option
		startVisit1And2StepsPage.verifyUserAllowedToSelectOneOptionInTenOfTwelveQuestion();

	}

	@Test(priority = 64, description = "Verify selecting from search results", enabled = false)

	public void IDA4_2443_verifySelectingValueFromSearchResults() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// select value from serach results and verify
		startVisit1And2StepsPage.selectValueFromSearchResultsAndVerify();
	}

	@Test(priority = 65, description = "Verify the functionality of 'All reason' section", enabled = false)

	public void IDA4_2447_verifyAllReasonFunctionality() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// verify the all reason functionality
		startVisit1And2StepsPage.verifyAllReasonFuntionality();
	}

	@Test(priority = 66, description = "Verify all the reason in ''All reasons'' section", enabled = true)

	public void IDA4_2446_verifyAllReasonInAllReasonSection() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		// verify the all reason in all reason section
		startVisit1And2StepsPage.verifyAllReasonInAllReasonSection();
	}

	@Test(priority = 67, description = "Verify the UI of 2/4 Visit Reason page", enabled = true)

	public void IDA4_2437_verifyUIOfVisitReasonPage() throws InterruptedException {
		// enter patient vitals and complete the visit
		startVisit1And2StepsPage.enterPatientVitalsDetails(
				appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		startVisit1And2StepsPage.verifyUiOfVisitReasonPage();
	}

	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();

	}

}
