package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AddNewPatientPage;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.StartVisit1And2StepsPage;
import com.intelehealth.pages.StartVisit3And4StepsPage;
import com.intelehealth.utils.TestUtils;
import com.microsoft.playwright.BrowserContext.GrantPermissionsOptions;

import freemarker.template.utility.CaptureOutput;
import io.appium.java_client.android.options.app.SupportsAutoGrantPermissionsOption;

public class StartVisit3And4StepsTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	StartVisit1And2StepsPage startVisit1And2StepsPage ;
	AddNewPatientPage addNewPatientPage;
	StartVisit3And4StepsPage startVisit3And4StepsPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws InterruptedException, IOException {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		// Reset and launch the app
		resetApp();
		launchApp();
		setImplicitWait();
		// Initialize pages and load test data
		appSetupPage = new AppSetupPage();
		addNewPatientPage = new AddNewPatientPage();
		startVisit1And2StepsPage = new StartVisit1And2StepsPage();
		startVisit3And4StepsPage = new StartVisit3And4StepsPage();
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

		// Handle app permissions and complete app setup
		appSetupPage.handlePermissions();

		appSetupPage.completeSetup();
		utils.log().info("Handling app permissions and completing app setup");
      Thread.sleep(4000);
		// Register a patient with provided details
		addNewPatientPage.registerAPatient(appData.getJSONObject("personalDetails").getString("firstName"),
				appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"),
				appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));
		utils.log().info("Registering a patient with provided details");

		// Entering patient vitals details
		startVisit1And2StepsPage.enterPatientVitalsDetails(appData.getJSONObject("patientVitalsDetails").getString("height"),
				appData.getJSONObject("patientVitalsDetails").getString("weight"),
				appData.getJSONObject("patientVitalsDetails").getString("bpSystolic"),
				appData.getJSONObject("patientVitalsDetails").getString("bpDiastolic"),
				appData.getJSONObject("patientVitalsDetails").getString("pulse"),
				appData.getJSONObject("patientVitalsDetails").getString("temperature"),
				appData.getJSONObject("patientVitalsDetails").getString("spo2"),
				appData.getJSONObject("patientVitalsDetails").getString("respiratoryRate"));
		utils.log().info("Entering patient vitals details");
		// Click on the 'Next' button after entering the first set of vitals
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		utils.log().info("Clicking on the 'Next' button after entering the first set of vitals");

		// Click on the 'Confirm' button to confirm the entered vitals
		startVisit1And2StepsPage.clickOnConfirmButton();
		utils.log().info("Clicking on the 'Confirm' button to confirm the entered vitals");

		// Scroll to the HyperTension section on the page
		startVisit3And4StepsPage.scrollToHyperTension();
		utils.log().info("Scrolling to the HyperTension section on the page");

		// Select and confirm the visit reason as Hypertension
		startVisit3And4StepsPage.selectAndConfirmVisitReason();
		utils.log().info("Selecting and confirming the visit reason as Hypertension");

		// Handle the Hypertension-related visit questions
		startVisit3And4StepsPage.handleHypertensionVisitQuestions();
		utils.log().info("Handling the Hypertension-related visit questions");
		// Verify that the 'Associated Symptoms' title is displayed on the page
		startVisit1And2StepsPage.verifyAssociatedSymptomsTitleIsDisplayed();
		utils.log().info("Verifying that the 'Associated Symptoms' title is displayed on the page");

		// Select 'No' for the first associated symptom
		startVisit3And4StepsPage.selectAssociatedSymptomFirstNoButton();
		utils.log().info("Selecting 'No' for the first associated symptom");

		// Click on the main 'Submit' button for associated symptoms
		startVisit1And2StepsPage.clickOnAssociatedSymptomMainSubmitButton();
		utils.log().info("Clicking on the main 'Submit' button for associated symptoms");

		// Click on the 'Confirm' button on the visit reason summary screen
		startVisit1And2StepsPage.clickOnConfirmButtonOnVisitReasonSummaryScreen();
		utils.log().info("Clicking on the 'Confirm' button on the visit reason summary screen");

		// Click on the 'Okay' button to proceed
		startVisit1And2StepsPage.clickOnOkayButton();
		utils.log().info("Clicking on the 'Okay' button to proceed");

	}

	@Test(priority = 1, description = "Verify 1 of 6 questions is shown to user", enabled = true)

	public void IDA4_2562_verifyOneOfSixQuestionsIsShownToUser() {
     // method to verify that 1 of 6 questions is displayed
		startVisit3And4StepsPage.verifyOneOfSixQuestions();

	}

	@Test(priority = 2, description = "Verify 1 of 6 questions is shown to user", enabled = true)

	public void IDA4_2563_verifyOneOfSixQuestionsOptions() throws InterruptedException {
     // method to verify 1 of 6 question options
		startVisit3And4StepsPage.verifyOneOfSixQuestionOption();
	//	method to verify 2 of 6 questions are displayed
		startVisit3And4StepsPage.verifyTwoOfSixQuestionsIsDisplayed();

	}

	@Test(priority = 3, description = "Verify when user selects Take a picture in 1 of 6", enabled = true)
	public void IDA4_2564_verifyTakeAPictureOption() {
		//method to verify that 1 of 6 questions is displayed
		startVisit3And4StepsPage.verifyOneOfSixQuestions();
	//	method to verify the take a picture option
		startVisit3And4StepsPage.verifyTakeAPictureOption();
	}
	@Test(priority = 4, description = "Verify when user clicks on upload icon in 1 of 6", enabled = true)
	public void IDA4_2565_verifyUploadIcon() {
	//	method to verify that 1 of 6 questions is displayed
		startVisit3And4StepsPage.verifyOneOfSixQuestions();
		//method to verify the upload icon
		startVisit3And4StepsPage.verifyUploadIcon();
		
	}
	@Test(priority = 5, description = "Verify 2 of 6 questions is shown to user", enabled = true)
	public void IDA4_2574_verifyTwoOfSixQuestionIsDisplayed() {
		//method to verify that 1 of 6 questions is displayed
		startVisit3And4StepsPage.verifyOneOfSixQuestions();
	//	method to verify 2 of 6 questions are displayed with options
		startVisit3And4StepsPage.verifyTwoOfSixQuestionIsDisplayedWithOptions();
	}
	@Test(priority = 6, description = "Verify when user clicks Normal/Pale option in 2 of 6", enabled = true)
public void IDA4_2575_verifyTwoOfSixQuestionNormalPaleOption() throws InterruptedException {
		//  method to verify 1 of 6 question options
				startVisit3And4StepsPage.verifyOneOfSixQuestionOption();
				// Verify that two out of six questions are displayed with options
				startVisit3And4StepsPage.verifyTwoOfSixQuestionIsDisplayedWithOptions();
			//	 Verify that two out of six questions have normal pale options
				startVisit3And4StepsPage.verifyTwoOfSixQuestionsNormalPaleOption();
	}
	@Test(priority = 7, description = "Verify 3 of 6 questions is shown to user", enabled = true)
	public void IDA4_2584_verifyThreeOfSixQuestionsIsShownToUser() throws InterruptedException {
	//	 method to verify 1 of 6 question options
		startVisit3And4StepsPage.verifyOneOfSixQuestionOption();
		// Verify that two out of six questions are displayed with options
		startVisit3And4StepsPage.verifyTwoOfSixQuestionIsDisplayedWithOptions();
		// Verify that three out of questions are displayed with options
		startVisit3And4StepsPage.verifyThreeOfQuestionsIsDisplayedWithOptions();
	}
	@Test(priority = 8, description = "Verify when user clicks Normal/Slow option in 3 of 6", enabled = true)
	public void IDA4_2585_verifyThreeOfSixQuestionsNormalSlowOption() throws InterruptedException {
		// method to verify 1 of 6 question options
		startVisit3And4StepsPage.verifyOneOfSixQuestionOption();
		// Verify that two out of six questions are displayed with options
		startVisit3And4StepsPage.verifyTwoOfSixQuestionIsDisplayedWithOptions();
		// Verify that three out of questions are displayed with options
		startVisit3And4StepsPage.verifyThreeOfQuestionsIsDisplayedWithOptions();
	//	 Verify that three out of six questions have normal slow options
		startVisit3And4StepsPage.verifyThreeOfSixQuestionsNormalSlowOption();
		
	}
	
	@Test(priority = 9, description = "Verify 4 of 6 questions is shown to user", enabled = true)
	public void IDA4_2586_verifyFourOfSixQuestionsIsShownToUser() throws InterruptedException {
		// method to verify 1 of 6 question options
		startVisit3And4StepsPage.verifyOneOfSixQuestionOption();
	//	 Verify that two out of six questions are displayed with options
		startVisit3And4StepsPage.verifyTwoOfSixQuestionIsDisplayedWithOptions();
		// Verify that three out of six questions are displayed with options
		startVisit3And4StepsPage.verifyThreeOfQuestionsIsDisplayedWithOptions();
	//	 Verify that three out of six questions have normal slow options
		startVisit3And4StepsPage.verifyThreeOfSixQuestionsNormalSlowOption();
	//	 Verify that four out of six questions are displayed with options
		startVisit3And4StepsPage.verifyFourOfSixQuestionIsDisplayedWithOptions();
		
	}
	@Test(priority = 10, description = "Verify when user clicks Nails are normal/Clubbing/Spoon nails/Discolored option in 4 of 6", enabled = true)
	public void IDA4_2587_verifyFourOfSixQuestionsOptions() throws InterruptedException {
		// method to verify 1 of 6 question options
		startVisit3And4StepsPage.verifyOneOfSixQuestionOption();
		// Verify that two out of six questions are displayed with options
		startVisit3And4StepsPage.verifyTwoOfSixQuestionIsDisplayedWithOptions();

	//	 Verify that three out of six questions are displayed with options
		startVisit3And4StepsPage.verifyThreeOfQuestionsIsDisplayedWithOptions();

		// Verify that three out of six questions have normal slow options
		startVisit3And4StepsPage.verifyThreeOfSixQuestionsNormalSlowOption();

	//	 Verify that four out of six questions are displayed with options
		startVisit3And4StepsPage.verifyFourOfSixQuestionIsDisplayedWithOptions();

	//	 Verify that four out of six questions have options
		startVisit3And4StepsPage.verifyFourOfSixQuestionsOptions();

		
	}
	@Test(priority = 11, description = "Verify 5 of 6 questions is shown to user ", enabled = true)
	public void IDA4_2596_verifyFiveOfSixQuestionsIsDisplayedWithOptions() {
		startVisit3And4StepsPage.verifyFiveOfSixQuestionsOptionsAreDisplayed();
		
	}
	@Test(priority = 12, description = "Verify when user clicks Nails are normal/Nails are pale option in 5 of 6", enabled = true)
	public void IDA4_2597_verifyFiveOfSixQuestionsIsDisplayedWithOptions() {
		startVisit3And4StepsPage.verifyFiveOfSixQuestionsNailsAreNormal_NailsArePaleOption();
		
		
	}
	@Test(priority = 13, description = "Verify when user clicks Nails are normal/Nails are pale option in 5 of 6", enabled = true)
	public void IDA4_2606_verifySixofSixQuestionsIsShownToUser() {
		startVisit3And4StepsPage.verifySixOfSixQuestionsIsDisplayedWithOptions();
	
	}
	@Test(priority = 14, description = "Verify when user clicks -No oedema/In left/In right/Both option in 6 of 6", enabled = true)
	public void IDA4_2607_verifySixofSixQuestionsOptions() {
		startVisit3And4StepsPage.verifySixOfSixQuestionsIsDisplayedWithOptions();
		startVisit3And4StepsPage.verifySixOfSixQuestionsOptions();
		
	}
	@Test(priority = 15, description = "Verify Change button functionality on Physical examination summary screen", enabled = true)
	public void IDA4_2621_verifyChangeButtonFunctionality() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.verifyPhysicalExaminationChangeButton();
	}
	
	@Test(priority = 16, description = "Verify Back button functionality on Physical examination summary screen", enabled = true)
	public void IDA4_2623_verifyBackButtonFunctionality() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.verifyBackButton();
	}
	@Test(priority = 17, description = "Verify Back button functionality on Physical examination summary screen", enabled = true)
	public void IDA4_2624_verifyConfirmButtonFunctionality() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.verifyConfirmButton();
	}
	@Test(priority = 18, description = "Verify the UI of Family history screen", enabled = true)
	public void IDA4_2641_verifyFamilyHistoryUi() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.verifyFamilyHistoryScreenUi();
	}
	@Test(priority = 19, description = "Verify one or more options can be selected for the question on Family history screen", enabled = true)
	public void IDA4_2644_verifyMultipleOptionsInFamilyHistoryScreen() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.validateMultipleOptionsInFamilyHistory();
	}
	@Test(priority = 20, description = "Verify if Other option is selected on Family history screen", enabled = true)
	public void IDA4_2645_verifyTextBoxIsDisplayed() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.verifyDescribeTextBoxIsDisplayed();
}
	
	@Test(priority = 21, description = "Verify the user is able to enter data into the textbox on Family history screen", enabled = true)
	public void IDA4_2646_verifyUserAbleToEnterDataInTextBox() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.enterDataInTextBoxAndSubmit();
	}
	@Test(priority = 22, description = "Verify confirm button functionality on Family history screen", enabled = true)
	public void IDA4_2648_verifyConfirmButtonFunctionality() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.enterDataInTextBoxAndSubmit();
		startVisit3And4StepsPage.verifyFamilyHistoryScreenConfirmButton();
		
		
	}
	@Test(priority = 23, description = "Verify Change button functionality for patient history on Medical history summary screen", enabled = true)
	public void IDA4_2652_verifyPatientHistoryScreenChangeButton() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.enterDataInTextBoxAndSubmit();
		startVisit3And4StepsPage.verifyFamilyHistoryScreenConfirmButton();
		startVisit3And4StepsPage.verifyPatientHistoryChangeButton();

	}

	@Test(priority = 24, description = "Verify Change button functionality for family history on Medical history summary screen", enabled = true)
	public void IDA4_2653_verifyFamilyHistoryScreenChangeButton() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.enterDataInTextBoxAndSubmit();
		startVisit3And4StepsPage.verifyFamilyHistoryScreenConfirmButton();
		startVisit3And4StepsPage.verifyFamilyHistoryChangeButton();

	}

	@Test(priority = 25, description = "Verify Back button functionality on Medical history summary screen", enabled = true)
	public void IDA4_2655_verifyBackButtonFunctionality() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.enterDataInTextBoxAndSubmit();
		startVisit3And4StepsPage.verifyFamilyHistoryScreenConfirmButton();
		startVisit3And4StepsPage.verifyMedicalHistorySummaryScreenBackButton();
	}

	@Test(priority = 26, description = "Verify Confirm button functionality on Medical history summary screen", enabled = true)
	public void IDA4_2656_verifyConfirmButtonFunctionality() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.enterDataInTextBoxAndSubmit();
		startVisit3And4StepsPage.verifyMedicalHistorySummaryScreenConfirmButton();
	}

	@Test(priority = 27, description = "Verify that user can select one or more options for the questions allowed on Patient history screen", enabled = true)
	public void IDA4_2628_verifyUserAbleToSelectOneOrMoreOption() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.verifyUserAbleToSelectOneOrMoreOption();

	}

	@Test(priority = 28, description = "Verify Skip functionality on Patient history screen", enabled = true)
	public void IDA4_2630_verifySkipButtonFunctionality() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.verifySkipButtonOnPatientScreen();

	}

	@Test(priority = 29, description = "Verify Submit functionality on Patient history screen", enabled = true)
	public void IDA4_2632_verifySubmitButtonFunctionality() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.verifyPatientHistorySubmitButtonFunctionality();

	}

	@Test(priority = 30, description = "Verify Other [Describe] textbox functionality on Patient history screen", enabled = true)
	public void IDA4_2633_verifyOtherDescribeFunctionality() {
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.verifyPatientHistoryOtherDescribeTextBox();

	}

	@Test(priority = 31, description = "Verify dropdowns for selecting durations on Patient history screen", enabled = true)
	public void IDA4_2635_verifyDropdowns() {
		// answers the physical examination questions
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();

		// Verify the dropdowns related to patient history
		startVisit3And4StepsPage.verifyPatientHistoryDropdowns();

	}

	@Test(priority = 32, description = "Verify calendar selection option on Patient history screen", enabled = true)
	public void IDA4_2636_verifyCalenderSelectionOption() {
		// answers the physical examination questions
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.calenderSelectionOption();
	}

	@Test(priority = 33, description = "Verify whether the selected date appears correctly on Patient history screen", enabled = true)
	public void IDA4_2637_verifySelectedDateAppearsOnPatientHistoryScreen() {
		// answers the physical examination questions
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.calenderSelectionOption();
	}

	@Test(priority = 34, description = "Verify whether the changes are updated in physical examination screen", enabled = true)
	public void IDA4_2622_verifyChangesUpdated() {
		// answers the physical examination questions
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.updatePhysicalExaminationQuestionsAndVerify();
	}

	@Test(priority = 35, description = "Verify whether the changes are updated in medical history summary screen", enabled = true)
	public void IDA4_2654_verifyChangesUpdatedInMedicalHistoryScreen() {
		// answers the physical examination questions
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.enterDataInTextBoxAndSubmit();
		startVisit3And4StepsPage.verifyChangesUpdatedInMedicalHistorySummaryScreen();
	}

	@Test(priority = 36, description = "Verify Describe section of the question on Family history screen", enabled = true)
	public void IDA4_2647_verifyFamilyHistoryDescribeSection() {
		// answers the physical examination questions
		startVisit3And4StepsPage.answerPhysicalExaminationQuestions();
		startVisit3And4StepsPage.answerMedicalHistoryQuestions();
		startVisit3And4StepsPage.verifyFamilyHistoryDescribeSection();
	}

	@Test(priority = 37, description = "Verify clicking photo in 1 of 6", enabled = true)
	public void IDA4_2567_verifyClickingOnPhotoInOneOfSix() {
		// answers the physical examination questions
		startVisit3And4StepsPage.verifyClickingPhotoInOneOfSixQuestions();
	}

	@Test(priority = 38, description = "Verify clicking photo in 1 of 6", enabled = true)
	public void IDA4_2579_verifyClickingOnPhotoInTwoOfSix() {
		// answers the physical examination questions
		startVisit3And4StepsPage.verifyClickingPhotoInTwoOfSixQuestions();
	}

	@Test(priority = 39, description = "Verify when user selects take photo option in 4 of 6", enabled = true)
	public void IDA4_2590_verifyClickingOnPhotoInFourOfSix() {
		// answers the physical examination questions
		startVisit3And4StepsPage.verifyClickingPhotoInFourOfSixQuestions();
	}

	@Test(priority = 40, description = "Verify when user selects take photo option in 5 of 6", enabled = true)
	public void IDA4_2601_verifyClickingOnPhotoInFiveOfSix() {
		startVisit3And4StepsPage.verifyClickingPhotoInFiveOfSixQuestions();
	}

	@Test(priority = 41, description = "Verify when user selects take photo option in 6 of 6", enabled = true)
	public void IDA4_2611_verifyClickingOnPhotoInSixOfSix() {
		startVisit3And4StepsPage.verifyClickingPhotoInSixOfSixQuestions();
	}

	@Test(priority = 42, description = "Verify picture is uploaded when selected from gallery/photos in 1 of 6", enabled = true)
	public void IDA4_2572_verifyPictureUploadedFromGalleryInOneOfSix() {

		startVisit3And4StepsPage.verifyPictureUploadedFromGalleryInOneOfSix();
	}

	@Test(priority = 43, description = "Verify picture is uploaded when selected from gallery/photos in 2 of 6", enabled = true)
	public void IDA4_2582_verifyPictureUploadedFromGalleryInTwoOfSix() {

		startVisit3And4StepsPage.verifyPictureUploadedFromGalleryInTwoOfSix();
	}

	@Test(priority = 44, description = "Verify picture is uploaded when selected from gallery/photos in 4 of 6", enabled = true)
	public void IDA4_2594_verifyPictureUploadedFromGalleryInFourOfSix() {

		startVisit3And4StepsPage.verifyPictureUploadedFromGalleryInFourOfSix();
	}

	@Test(priority = 45, description = "Verify picture is uploaded when selected from gallery/photos in 4 of 6", enabled = true)
	public void IDA4_2604_verifyPictureUploadedFromGalleryInFiveOfSix() {

		startVisit3And4StepsPage.verifyPictureUploadedFromGalleryInFiveOfSix();
	}

	@Test(priority = 46, description = "Verify picture is uploaded when selected from gallery/photos in 6 of 6", enabled = true)
	public void IDA4_2614_verifyPictureUploadedFromGalleryInSixOfSix() {

		startVisit3And4StepsPage.verifyPictureUploadedFromGalleryInSixOfSix();
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		System.gc();
		// Close the app after each test method
		closeApp();
     Thread.sleep(2000);
	}

}
