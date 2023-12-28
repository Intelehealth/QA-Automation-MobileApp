package com.intelehealth.pages;

import java.awt.Robot;
//import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.xpath.XPath;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.tests.AddNewPatientTest;
import com.intelehealth.tests.VisitSummaryTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

/**
 * Page class representing the Visit summary screen. Contains elements, actions,
 * and verifications specific to this screen.
 */

public class VisitSummaryPage extends BaseTest {

	// JSON object to store test data
	JSONObject appData;

	AddNewPatientTest addNewPatientTest;
	StartVisit1And2StepsPage startVisit1And2StepsPage;
	VisitSummaryTest visitSummaryTest;
	AddNewPatientPage addNewPatientPage;
	AppSetupPage appSetupPage;
	// JSON object to store test data

	@AndroidFindBy(accessibility = "editext for height input in Vital Collection Fragment")
	private WebElement heightEditText;

	@AndroidFindBy(accessibility = "edittext for weight in Vital Collection Fragment")
	private WebElement weightEditText;

	@AndroidFindBy(accessibility = "bp systolic edittext input in Vital Collection Fragment")
	private WebElement bpSystolicEditText;

	@AndroidFindBy(accessibility = "bp diastolic edittext input in Vital Collection Fragment")
	private WebElement bpDiastolicEditText;

	@AndroidFindBy(accessibility = "pulse input edittext in Vital Collection Fragment")
	private WebElement pulseEditText;

	@AndroidFindBy(accessibility = "temperature input edittext in Vital Collection Fragment")
	private WebElement temperatureEditText;

	@AndroidFindBy(accessibility = "spo2 input edittext in Vital Collection Fragment")
	private WebElement spo2EditText;

	@AndroidFindBy(accessibility = "Respiratory Rate input edittext in Vital Collection Fragment")
	private WebElement respiratoryRateEditText;

	@AndroidFindBy(accessibility = "Associated Symptoms Questionnaire Main View Submit Button")
	private WebElement associatedSymptomsSubmit;

	@AndroidFindBy(accessibility = "Common Message Dialog Positive Button")
	private WebElement washHandsOkayButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No']")
	private WebElement jaundiceNoButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Normal']")
	private WebElement pallorNormalButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Slow']")
	private WebElement pinchSkinSlowButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clubbing']")
	private WebElement clubbingButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Nails are pale']")
	private WebElement nailsArePaleButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No oedema']")
	private WebElement noOedemaButton;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='No'])[last()]")
	private WebElement lastNoButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Redness']")
	private WebElement rednessButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No']")
	private WebElement noButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No tenderness']")
	private WebElement noTendernessButton;

	@AndroidFindBy(accessibility = "Visit Reason Summary Fragment Submit Button")
	private WebElement visitReasonSummaryScreenConfirmButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No known allergies']")
	private WebElement noKnownAllergiesButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Denied to Answer']")
	private WebElement deniedToAnswerButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Denied']")
	private WebElement deniedButton;

	@AndroidFindBy(xpath = "(//android.widget.Button[@text='Skip'])[last()]")
	private WebElement lastSkipButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='None of the above']")
	private WebElement noneOfTheAboveButton;

	@AndroidFindBy(accessibility = "Question Node Submit Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Submit']")
	private WebElement familyHistorySubmit;

	@AndroidFindBy(accessibility = "Visit Summary Patient Details Edit ImageView")
	private WebElement editDetailsIcon;

	@AndroidFindBy(accessibility = "Identification Activity Title TextView")
	private WebElement updatePatientTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Update Patient']")
	private WebElement updatePatientText;

	@AndroidFindBy(accessibility = "Visit Summary Add Documents Edit ImageButton")
	private WebElement addAdditionalDocumentsButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Add Additional Document!']")
	private WebElement addAdditionalDocumentText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Take Photo']")
	private WebElement addAdditionalDocumentTakePhoto;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose from Gallery']")
	private WebElement addAdditionalDocumentChooseFromGallery;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel']")
	private WebElement addAdditionalDocumentCancel;

	@AndroidFindBy(id = "org.intelehealth.app:id/utils_take_picture")
	private WebElement clickPicture;

	@AndroidFindBy(accessibility = "Physical Examination Summary Fragment Submit Button")
	private WebElement physicalExaminationConfirmButton;

	@AndroidFindBy(accessibility = "Medical History Summary Fragment Submit Button")
	private WebElement medicalHistoryConfirmButton;

	@AndroidFindBy(accessibility = "Visit Summary Speciality Spinner")
	private WebElement doctorsSpecialityDropdown;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Pediatrician']")
	private WebElement pediatrician;

	@AndroidFindBy(accessibility = "Visit Summary Send Visit Button")
	private WebElement sendVisitButton;

	@AndroidFindBy(accessibility = "Patient Registration Dialog Positive (Yes) Button")
	private WebElement sendVisitYesButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Visit successfully sent!']")
	private WebElement visitSentSuccessfullyText;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'visit has been successfully sent to the doctor')]")
	private WebElement visitSentSuccessfullyToDoctorText;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Okay']")
	private WebElement okayButton;

	@AndroidFindBy(accessibility = "Visit Summary Appointment Button")
	private WebElement appointmentButton;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='org.intelehealth.app:id/tvTime_new'])[1]")
	private WebElement appointmentFirstTime;

	@AndroidFindBy(accessibility = "Schedule Appointment 'Book Appointment' Button")
	private WebElement bookAppointmentButton;

	@AndroidFindBy(accessibility = "Book Appointment Dialog Positive (Yes) Button")
	private WebElement appointmentYesButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PHOTO']")
	private WebElement photoText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Take image']")
	private WebElement takeImageText;

	@AndroidFindBy(id = "org.intelehealth.app:id/utils_take_picture")
	private WebElement clickPictureButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/camera_switch_iv")
	private WebElement switchCamera;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel']")
	private WebElement cameraCancel;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select a photo']")
	private WebElement selectAPhotoText;

	@AndroidFindBy(accessibility = "Visit Summary Priority Checkbox SwitchMaterial")
	private WebElement priorityVisitToggleButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Schedule appointment']")
	private WebElement scheduleAppointmentText;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Today Appointment Item Patient Name TextView\"])[last()]")
	private WebElement addedAppointmentPatientName;

	@AndroidFindBy(accessibility = "Visit Summary Patient Name TextView")
	private WebElement visitSummaryPatientName;

	@AndroidFindBy(accessibility = "Home Fragment Closed Visit CardView")
	private WebElement openCloseVisits;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Share\"])[1]")
	private WebElement firstShareIcon;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[contains(@resource-id,\"org.intelehealth.app:id/frame\")]")
	private WebElement navigateToCloseVisitScreen;

	@AndroidFindBy(accessibility = "move to previous screen")
	private WebElement moveToPreviousScreen;

	@AndroidFindBy(id = "org.intelehealth.app:id/ivInternetCustomToolbar")
	private WebElement homeScreenRefreshButton;

	@AndroidFindBy(accessibility = "Visit Details Patient OpenMRS ID TextView")
	private WebElement visitDetailsPatientId;

	@AndroidFindBy(accessibility = "Visit Details 'End Visit' Button")
	private WebElement endVisitButton;

	@AndroidFindBy(accessibility = "Patient Survey Screen Give Feedback EditText")
	private WebElement feedbackTextArea;

	@AndroidFindBy(accessibility = "Patient Survey Screen Submit Button")
	private WebElement patientSurveySubmitButton;

	@AndroidFindBy(accessibility = "Home Fragment Find Patient TextView")
	private WebElement findPatientTextfield;

	@AndroidFindBy(accessibility = "Find Patient Screen Search Bar EditText")
	private WebElement searchPatientTextfield;

	@AndroidFindBy(accessibility = "Common Message Dialog Positive Button")
	private WebElement alertMessageOKButton;

	@AndroidFindBy(accessibility = "Patient Details Past Visits Title TextView")
	private WebElement pastVisitTitle;

	@AndroidFindBy(accessibility = "Find Patient List Item Name TextView")
	private WebElement foundPatientName;

	@AndroidFindBy(accessibility = "Past Visit List Item Parent RelativeLayout")
	private WebElement navigateToPastVisit;

	@AndroidFindBy(accessibility = "Visit Summary Print Button")
	private WebElement visitSummaryPrintButton;

	@AndroidFindBy(id = "com.android.printspooler:id/destination_spinner")
	private WebElement selectAPrinter;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Save as PDF']")
	private WebElement saveAsPDF;

	@AndroidFindBy(accessibility = "Files")
	private WebElement files;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.google.android.documentsui:id/icon_thumb\"])[1]")
	private WebElement openDownloadedPDF;

	@AndroidFindBy(xpath = "(//android.view.ViewGroup[@text=''])[1]")
	private WebElement downloadedPDF;

	@AndroidFindBy(accessibility = "Save to PDF")
	private WebElement saveToPDF;

	@AndroidFindBy(accessibility = "Visit Summary Share Button")
	private WebElement visitSummaryShareButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='SAVE']")
	private WebElement saveToFileButton;

	@AndroidFindBy(accessibility = "Share Prescription Dialog Message TextView")
	private WebElement mobileNoToWhichYouWantToSharePrescriptionAccessibilityID;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter the mobile number to which you want to share the prescription.']")
	private WebElement mobileNoToWhichYouWantToSharePrescriptionXpath;

	@AndroidFindBy(accessibility = "Share Prescription Dialog Mobile Num EditText")
	private WebElement prescriptionToMobileNoShareButton;

	@AndroidFindBy(accessibility = "Share Prescription Dialog 'Share' Button")
	private WebElement mobileNumberTextField;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='No thanks']")
	private WebElement turnOnSyncNoThanks;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Add account']")
	private WebElement turnOnSyncAddAccount;

	@AndroidFindBy(accessibility = "Identification First Screen First Name EditText")
	private WebElement updateDetailsFirstNameTextField;

	@AndroidFindBy(accessibility = "Identification First Screen Next Button")
	private WebElement updateDetailsSaveButton;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Name' Value TextView")
	private WebElement updatedFirstName;

	@AndroidFindBy(accessibility = "Identification First Screen Last Name EditText")
	private WebElement updateDetailsLastNameTextField;

	@AndroidFindBy(accessibility = "Find Patient Screen Search ImageView")
	private WebElement findPatientSearchIcon;

	@AndroidFindBy(accessibility = "Identification First Screen Phone Num EditText")
	private WebElement addNewPatientPhoneNo;

	@AndroidFindBy(accessibility = "Home Fragment Prescription Arrow Icon ImageView")
	private WebElement homeScreenPrescriptionArrowIcon;

	@AndroidFindBy(accessibility = "Visit Summary Item Card Patient History Details TextView")
	private WebElement medicalHistoryDetails;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Name' Value TextView")
	private WebElement patientDetailsScreenPersonalDetailsNameValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details Edit Icon ImageView")
	private WebElement patientDetailsScreenPersonalDetailsEditIcon;

	@AndroidFindBy(accessibility = "Identification First Screen First Name EditText")
	private WebElement firstName;

	@AndroidFindBy(accessibility = "Identification First Screen Next Button")
	private WebElement updatePatientSaveButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/camera_switch_iv")
	private WebElement cameraSwitchButton;

	@AndroidFindBy(accessibility = "Additional Docs List Item Parent RelativeLayout")
	private WebElement addedAdditionalDocument;
	
	@AndroidFindBy(accessibility  = "Shutter")
	private WebElement shutterButton;
	
	@AndroidFindBy( xpath =  "//android.view.View[@resource-id='com.google.android.apps.photos:id/image']")
	private WebElement photos;
	
	@AndroidFindBy( xpath =  "(//android.view.ViewGroup)[2]")
	private WebElement image;
	
	@AndroidFindBy(accessibility  = "Additional Docs List Item Document Delete ImageButton")
	private WebElement documentDeleteIcon;
	
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id=\"org.intelehealth.app:id/frame_10014\"]")
	private WebElement prescriptionPatients;

	@AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Picture taken\"]")
	private WebElement byPictureTaken;
	
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc=\"Additional Docs List Item Parent RelativeLayout\"]")
	private WebElement byAddedAdditionalDocument;
	
	public VisitSummaryPage(ThreadLocal<AppiumDriver> driver) throws Throwable {
		this.driver = driver;
		addNewPatientTest = new AddNewPatientTest();
		startVisit1And2StepsPage = new StartVisit1And2StepsPage();
		addNewPatientPage = new AddNewPatientPage();
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


	// Adds phone number
	public void phoneNumber() {
		click(addNewPatientPhoneNo, "Clicking on phone number");
		sendKeys(addNewPatientPhoneNo, "8765985376", "Entering the phone number");
	}

	// Adds patients
	public void addPatients() throws Throwable {
		addNewPatientPage.clickOnAddPatients();
		Thread.sleep(3000);
		addNewPatientPage.clickOnAcceptButton();
//		addNewPatientPage.clickRefreshButton();
		Thread.sleep(3000);
		addNewPatientPage.enterFirstName(appData.getJSONObject("personalDetails").getString("firstName"));
		Thread.sleep(2000);
		addNewPatientPage.enterLastName();
		addNewPatientPage.selectGender();
		scrollToElement();
		addNewPatientPage.clickOnDobIcon();
		addNewPatientPage.clickOnMonthSpinner();
		addNewPatientPage.selectMonth();
		addNewPatientPage.clickOnYearSpinner();
		addNewPatientPage.scrollToViewYear();
		addNewPatientPage.selectYear();
		addNewPatientPage.selectDate();
		addNewPatientPage.clickOnOkayButton();
		phoneNumber();
		addNewPatientPage.clickOnNextButton1();
		addNewPatientPage.clickOnStateSpinner();
		addNewPatientPage.scrollToViewState();
		addNewPatientPage.selectState();
		addNewPatientPage.clickOnDistrictSpinner();
		addNewPatientPage.scrollToViewDistrict();
		addNewPatientPage.selectDistrict();
		addNewPatientPage.enterPatientAddressDetails(appData.getJSONObject("patientAddress").getString("pincode"),
				appData.getJSONObject("patientAddress").getString("village"),
				appData.getJSONObject("patientAddress").getString("address1"),
				appData.getJSONObject("patientAddress").getString("address2"));

		addNewPatientPage.clickOnNextButton2();
		addNewPatientPage.enterOtherDetails(appData.getJSONObject("personalDetails").getString("nationalId"),
				appData.getJSONObject("personalDetails").getString("occupation"));
		addNewPatientPage.clickOnNextButton3();
		Thread.sleep(6000);
		String nameBeforeChanging = patientDetailsScreenPersonalDetailsNameValue.getText();
		click(patientDetailsScreenPersonalDetailsEditIcon, "Clicking on edit icon");
		click(firstName, "Clicking on first name textfield");
		sendKeys(firstName, "Automate", "Entering the name");
		click(updatePatientSaveButton, "Clicking on save button");
		String nameAfterChanging = patientDetailsScreenPersonalDetailsNameValue.getText();
		if (!nameBeforeChanging.contains("Automate") && nameAfterChanging.contains("Automate")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the details have been updated successfully");
		}
		addNewPatientPage.clickOnStartVisitButton();
		addNewPatientPage.clickOnContinueButton();
	}

	// Completes all four steps of a process
	public void completing4OutOf4Steps() throws Throwable {
		// Enter 1/4 vitals
		sendKeys(heightEditText, "165", "Entering the height");
		sendKeys(weightEditText, "68", "Entering the weight");
		sendKeys(bpSystolicEditText, "120", "Entering the bp Systolic");
		sendKeys(bpDiastolicEditText, "72", "Entering the bp Diastolic");
		sendKeys(pulseEditText, "72", "Entering the pulse");
		sendKeys(temperatureEditText, "98", "Entering the temperature");
		startVisit1And2StepsPage.scrollToViewRespiratoryRate();
		sendKeys(spo2EditText, "98", "Entering the spo2");
		sendKeys(respiratoryRateEditText, "72");
		startVisit1And2StepsPage.clickOnFirstVitalsNextButton();
		startVisit1And2StepsPage.clickOnConfirmButton();
		startVisit1And2StepsPage.selectVisitReasonAsAbdominalPain();
		startVisit1And2StepsPage.clickOnNextButtonOfVisitReason();
		startVisit1And2StepsPage.clickOnYesButton();
		startVisit1And2StepsPage.clickUpperRightHypochondrium();
		startVisit1And2StepsPage.clickOnSubmitButton();
		startVisit1And2StepsPage.selectPainRadiatesOption();
		startVisit1And2StepsPage.clickOnGroin();
		startVisit1And2StepsPage.clickOnPainRadiatesSubmitButton();
		startVisit1And2StepsPage.clickOnNumberSpinner();
		startVisit1And2StepsPage.selectTwo();
		startVisit1And2StepsPage.clickOnDurationSpinner();
		startVisit1And2StepsPage.selectDays();
		startVisit1And2StepsPage.clickOnThreeOfTwelveQuestionSubmitButton();
		startVisit1And2StepsPage.clickOnGradualOption();
		startVisit1And2StepsPage.selectNightOption();
		startVisit1And2StepsPage.clickOnConstantOptionOfSixOfTweleveQuestions();
		startVisit1And2StepsPage.clickOnSubmitButtonOfSixOfTwelveQusetions();
		startVisit1And2StepsPage.selectMildOneThreeOption();
		startVisit1And2StepsPage.selectCoughingOption();
		startVisit1And2StepsPage.clickOnEightOfTwelveSubmitButton();
		startVisit1And2StepsPage.selectfoodOption();
		startVisit1And2StepsPage.selectLeaningForward();
		startVisit1And2StepsPage.clickOnSubmitButtonOfNineOfTwelveQuestions();
		startVisit1And2StepsPage.selectHasNotStartedMenstruationOption();
		Thread.sleep(2000);
		startVisit1And2StepsPage.selectNoneOption();
		startVisit1And2StepsPage.clickOnTwelevOfTwelveSkipButton();
		for (int i = 0; i < 10; i++) {
			click(lastNoButton, "Clicking on no button");
		}
		click(associatedSymptomsSubmit, "Clicking on associated symptoms submit button");
		click(visitReasonSummaryScreenConfirmButton, "Clicking on visit reason summary screen confirm button");
		click(washHandsOkayButton, "Clicking on wash hands okay button");
		click(jaundiceNoButton, "Clicking on jaundice no button");
		click(pallorNormalButton, "Clicking on pallor normal button");
		click(pinchSkinSlowButton, "Clicking on pinch skin slow button");
		click(clubbingButton, "Clicking on clubbing button");
		Thread.sleep(2000);
		click(nailsArePaleButton, "Clicking on nails are pale button");
		click(noOedemaButton, "Clicking on no oedema button");
		click(noButton, "Clicking on no button");
		click(lastNoButton, "Clicking on no button");
		click(noTendernessButton, "Clicking on no tenderness button");
		click(lastNoButton, "Clicking on no button");
		click(physicalExaminationConfirmButton, "Clicking on physical examination confirm button");
		click(jaundiceNoButton, "Clicking on no button");
		click(noKnownAllergiesButton, "Clicking on no known allergies button");
		click(deniedToAnswerButton, "Clicking on denied to answer button");
		click(deniedButton, "Clicking on denied button");
		click(lastSkipButton, "Clicking on skip button");
		click(lastSkipButton, "Clicking on skip button");
		click(noneOfTheAboveButton, "Clicking on none of the above button");
		click(familyHistorySubmit, "Clicking on family history submit button");
		click(medicalHistoryConfirmButton, "Clicking on medical history confirm button");
	}

	// Verifies the functionality of the edit option beside the patient's name
	public void verifyEditOptionFunctionalityBesidePatientName() throws Throwable {
		addPatients();
		completing4OutOf4Steps();
		click(editDetailsIcon, "Clicking on edit details icon");
		boolean Title = isDisplayed(updatePatientTitle);
		boolean Text = isDisplayed(updatePatientText);
		if (Title == true && Text == true) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is navigated to update patient details screen");
			System.out.println("User is navigated to update patient details screen");
		}
		if (Title == false || Text == false) {
			throw new Exception("Update patient details screen is not displayed");
		}
	}

	// Verifies that changes are saved when patient details are updated
	public void verifyTheChangesAreSavedWhenPatientDetailsAreUpdated() throws Throwable {
		addPatients();
		completing4OutOf4Steps();
		String beforeChangingPatientName = visitSummaryPatientName.getText();
		click(editDetailsIcon, "Clicking on edit details icon");
		clear(updateDetailsFirstNameTextField, "Clearing the update details first name textfield");
		sendKeys(updateDetailsFirstNameTextField, "Update", "Entering the first name");
		sendKeys(updateDetailsLastNameTextField, "Testing", "Entering the last name");
		click(updateDetailsSaveButton, "Clicking on update details save button");
		String afterChangingPatientName = updatedFirstName.getText();
		if (!beforeChangingPatientName.equals(afterChangingPatientName)
				&& afterChangingPatientName.equals("Update Testing")) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether the changes made in personal screen is reflected in Visit summary screen");
			System.out.println("The changes made in personal screen is reflected in Visit summary screen");
		}
	}

	// Clicking on add additional documents button
	public void clickOnAddAdditionalDocument() throws Throwable {
		click(addAdditionalDocumentsButton, "Clicking on add additional documents button");
	}

	// Verifies the functionality of clicking on "Add Additional Document" button
	public void verifyClickingOnAddAdditionalDocument() throws Throwable {
		addPatients();
		completing4OutOf4Steps();
		scrollToElementByDescription("Visit Summary Add Documents Edit ImageButton");
		clickOnAddAdditionalDocument();
		if (isDisplayed(addAdditionalDocumentText) && isDisplayed(addAdditionalDocumentTakePhoto)
				&& isDisplayed(addAdditionalDocumentChooseFromGallery) && isDisplayed(addAdditionalDocumentCancel)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether the popup is displayed");
			System.out.println("Popup is displayed");
		} else {
			throw new Exception("Popup is not displayed");
		}
	}

	// Verifies that the user is able to click a photo
	public void verifyUserIsAbleToClickPhoto() throws Throwable {
		verifyClickingOnAddAdditionalDocument();
		click(addAdditionalDocumentTakePhoto, "Clicking on take photo");
		click(clickPicture, "Clicking on click picture icon");
		if (isDisplayed(photoText) && isDisplayed(takeImageText) && isDisplayed(clickPictureButton)
				&& isDisplayed(switchCamera) && isDisplayed(cameraCancel)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether camera is opened");
			System.out.println("Camera is opened");
		}
		Thread.sleep(5000);
		click(cameraSwitchButton, "Switching the camera");
		click(clickPictureButton, "Clicking on take picture button");
		if (isDisplayed2(byPictureTaken)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether 'Picture taken' message is displayed");
		}
		if (isDisplayed(addedAdditionalDocument)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether added document is displayed");
		}
	}

	// Verifies the functionality of choosing from the gallery to add a document
	public void verifyChooseFromGalleryOptionToAddDocument() throws Throwable {
		launchCamera();
		click(shutterButton, "Clicked on capture button");
		activateIntelehealth();
		verifyClickingOnAddAdditionalDocument();
		click(addAdditionalDocumentChooseFromGallery, "Clicking choose from gallery");
		if (isDisplayed(selectAPhotoText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether gallery is opened");
			System.out.println("Gallery is opened");
		}
		click(photos, "Clicked on photos");
		click(image, "Selecteing the image");
		if (isDisplayed(addedAdditionalDocument)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether added document is displayed");
			System.out.println("Added document is displayed");
		}

	}
	
	// Verifies the functionality of clicking on the close button for the uploaded document
	public void verifyClickingOnCloseButtonOnTheDocumentUploaded() throws Throwable {
		verifyChooseFromGalleryOptionToAddDocument();
		click(documentDeleteIcon,"Clicking on document delete icon");
		boolean addedDocument = isDisplayed2(byAddedAdditionalDocument);
		if(addedDocument == false) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether added document is deleted");
			System.out.println("Added document is deleted");
		}else {
			throw new Exception("Added document is not deleted");
		}
	}

	// Clicking on doctors speciality dropdown
	public void clickDoctorSpeciality() {
		click(doctorsSpecialityDropdown, "Clicking on doctors speciality dropdown");
	}

	// Verifies the functionality of the doctor's specialty dropdown
	public void verifyDoctorSpecialityDropdown() throws Throwable {
		addPatients();
		completing4OutOf4Steps();
		scrollToElementByDescription("Visit Summary Speciality Spinner");
		clickDoctorSpeciality();
		click(pediatrician, "Selecting pediatrician from the dropdown");
		String speciality = pediatrician.getText();
		String selectedSpeciality = doctorsSpecialityDropdown.getText();
		if (selectedSpeciality.equals(speciality)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is able to select the specialization from the dropdown");
			System.out.println("User is able to select the specialization from the dropdown");
		}

	}

	// Clicking on priority visit toggle button
	public void priorityVisitEnable() {
		click(priorityVisitToggleButton, "Clicking on priority visit toggle button");
	}

	// Verifies if the priority visit feature is enabled
	public void verifyIfPriorityVisitIsEnabledON() throws Throwable {
		addPatients();
		completing4OutOf4Steps();
		scrollToElementByDescription("Visit Summary Speciality Spinner");
		clickDoctorSpeciality();
		click(pediatrician, "Selecting pediatrician from the dropdown");
		scrollToElementByDescription("Visit Summary Priority Checkbox SwitchMaterial");
		String patientMedicalHistory = medicalHistoryDetails.getText();
		if (patientMedicalHistory.contains("Not pregnant") && patientMedicalHistory.contains("No known allergies")
				&& patientMedicalHistory.contains("Denied to Answer") && patientMedicalHistory.contains("Denied")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying the medical history summary");
			System.out.println("Medical history summary verified");
		}
		priorityVisitEnable();
		click(sendVisitButton, "Clicking on send visit button");
		click(sendVisitYesButton, "Clicking on yes button");
		if (isDisplayed(visitSentSuccessfullyText) && isDisplayed(visitSentSuccessfullyToDoctorText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether visit sent successfully");
			System.out.println("Visit sent successfully");
			click(okayButton, "Clicking on okay button");
		}
		boolean PriorityVisit = priorityVisitToggleButton.isEnabled();
		if (PriorityVisit == true) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether priority visit is sent");
			System.out.println("Priority visit is sent");
		}
		if (PriorityVisit == false) {
			throw new Exception("Priority visit is not sent");
		}
	}

	// Verifies the behavior when clicking "Yes" in the send visit popup
	public void verifyBehaviorOnClickingYesInSendVisitPopup() throws Throwable {
		completing4OutOf4Steps();
		scrollToElementByDescription("Visit Summary Speciality Spinner");
		clickDoctorSpeciality();
		click(pediatrician, "Selecting pediatrician from the dropdown");
		click(sendVisitButton, "Clicking on send visit button");
		click(sendVisitYesButton, "Clicking on yes button");
		if (isDisplayed(visitSentSuccessfullyText) && isDisplayed(visitSentSuccessfullyToDoctorText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether visit sent successfully");
			System.out.println("Visit sent successfully");
		}
	}

	// Verifies the functionality of clicking on the appointment button after the visit is sent to the doctor
	public void verifyClickingOnAppointmentButtonFunctionalityAfterVisitIsSentToDoctor() throws Throwable {
		addPatients();
		verifyBehaviorOnClickingYesInSendVisitPopup();
		click(okayButton, "Clicking on okay button");
		click(appointmentButton, "Clicking on appointment button");
		Thread.sleep(3000);
		boolean scheduleAppointment = isDisplayed(scheduleAppointmentText);
		boolean bookAppointment = isDisplayed(bookAppointmentButton);
		if (scheduleAppointment == true && bookAppointment == true) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is navigated to schedule appointment screen");
			System.out.println("User is navigated to schedule appointment screen");
		}
	}

	// Verifies if the booked appointment is reflecting correctly in the Appointments section
	public void verifyIfBookedAppointmentIsReflectingInAppointments() throws Throwable {
		addPatients();
		completing4OutOf4Steps();
		String patientName = visitSummaryPatientName.getText();
		scrollToElementByDescription("Visit Summary Speciality Spinner");
		clickDoctorSpeciality();
		click(pediatrician, "Selecting pediatrician from the dropdown");
		click(sendVisitButton, "Clicking on send visit button");
		click(sendVisitYesButton, "Clicking on yes button");
		click(okayButton, "Clicking on okay button");
		click(appointmentButton, "Clicking on appointment button");
		click(appointmentFirstTime, "Selecting the time slot");
		click(bookAppointmentButton, "Clicking on book appointment button");
		click(appointmentYesButton, "Clicking on yes button");
		String appointmentPatientName = addedAppointmentPatientName.getText();
		if (patientName.equals(appointmentPatientName)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether the booked appointment is shown in Upcoming section of Appointment");
			System.out.println("The booked appointment is shown in Upcoming section of Appointment");
		}
	}

}
