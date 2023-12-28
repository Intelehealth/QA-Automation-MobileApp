package com.intelehealth.pages;

import java.awt.Robot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class PrescriptionsPage extends BaseTest {

	Robot robot;

	@AndroidFindBy(accessibility = "Home Fragment Prescription Arrow Icon ImageView")
	private WebElement homeScreenPrescriptionArrowIcon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/you_can_add\"]//..//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.intelehealth.app:id/recycler_recent\"]//android.widget.TextView[@content-desc=\"patient name row item title\"]")
	private WebElement recentVisitPatientName;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/you_can_add\"]//..//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.intelehealth.app:id/recycler_recent\"]//android.widget.TextView[@content-desc=\"patient date and time row item\"]")
	private WebElement recentVisitDateAndTime;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/you_can_add\"]//..//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.intelehealth.app:id/recycler_recent\"]//android.widget.TextView[@text=\"Share\"]")
	private WebElement recentVisitShareIcon;

	@AndroidFindBy(id = "org.intelehealth.app:id/ivInternetCustomToolbar")
	private WebElement homeScreenRefreshButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter the mobile number to which you want to share the prescription.']")
	private WebElement enterTheMobileNoPopupText;

	@AndroidFindBy(accessibility = "Share Prescription Dialog 'Share' Button")
	private WebElement sharePrescriptionPopupShareButton;

	@AndroidFindBy(accessibility = "Share Prescription Dialog Mobile Num EditText")
	private WebElement sharePrescriptionPopupMobileNumTextfield;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='No thanks']")
	private WebElement turnOnSyncNoThanks;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Add account']")
	private WebElement turnOnSyncAddAccount;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Share\"])[last()]")
	private WebElement lastShare;

	@AndroidFindBy(accessibility = "Visit Details Title TextView")
	private WebElement visitDetailsPageTitle;

	@AndroidFindBy(accessibility = "Visit Details Patient Name TextView")
	private WebElement visitDetailsPatientName;

	@AndroidFindBy(accessibility = "Visit Details Patient Gender Age TextView")
	private WebElement visitDetailsPatientGenderAge;

	@AndroidFindBy(accessibility = "Visit Details Patient OpenMRS ID TextView")
	private WebElement visitDetailsPatientID;

	@AndroidFindBy(accessibility = "Visit Details Patient Call ImageButton")
	private WebElement visitDetailsPatientCallIcon;

	@AndroidFindBy(accessibility = "Visit Details Patient Chat ImageButton")
	private WebElement visitDetailsPatientWhatsappIcon;

	@AndroidFindBy(accessibility = "Visit Details Chief Complaint TextView")
	private WebElement visitDetailsReasonForVisit;

	@AndroidFindBy(accessibility = "Visit Details Visit ID TextView")
	private WebElement visitDetailsVisitID;

	@AndroidFindBy(accessibility = "Visit Details Start Date TextView")
	private WebElement visitDetailsDate;

	@AndroidFindBy(accessibility = "Visit Details Start Time TextView")
	private WebElement visitDetailsTime;

	@AndroidFindBy(accessibility = "Visit Details Doctor Speciality Value TextView")
	private WebElement visitDetailsDoctorSpeciality;

	@AndroidFindBy(accessibility = "Visit Details Visit Summary Card RelativeLayout")
	private WebElement visitDetailsVisitSummary;

	@AndroidFindBy(accessibility = "Visit Details Prescription Child RelativeLayout")
	private WebElement visitDetailsPrescription;

	@AndroidFindBy(accessibility = "Visit Details Follow Up Parent RelativeLayout")
	private WebElement visitDetailsFollowUp;

	@AndroidFindBy(accessibility = "Visit Details 'End Visit' Button")
	private WebElement visitDetailsEndVisitButton;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id=\"org.intelehealth.app:id/frame_10014\"]")
	private WebElement prescriptionPatients;

	@AndroidFindBy(accessibility = "More options")
	private WebElement callMoreOptions;

	@AndroidFindBy(id = "com.android.dialer:id/digits")
	private WebElement phoneNumberDigits;

	@AndroidFindBy(accessibility = "backspace")
	private WebElement callBackSpace;

	@AndroidFindBy(id = "org.intelehealth.app:id/tvChiefComplaintHeader")
	private WebElement visitSummaryVisitReasonComplaint;

	@AndroidFindBy(accessibility = "Visit Summary Patient Name TextView")
	private WebElement visitSummaryPatientName;

	@AndroidFindBy(accessibility = "Visit Summary Patient Gender Age TextView")
	private WebElement visitSummaryPatientGender;

	@AndroidFindBy(accessibility = "Visit Summary Patient OpenMRS ID TextView")
	private WebElement visitSummaryPatientOpenMRSID;

	@AndroidFindBy(accessibility = "Visit Summary Speciality Header RelativeLayout")
	private WebElement doctorsSpecialityDropdown;

	@AndroidFindBy(accessibility = "Visit Summary Speciality Value TextView")
	private WebElement doctorsSpecialityValue;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Visit Summary Item Card Vitals CardView\"]/android.widget.RelativeLayout")
	private WebElement visitSummaryVitals;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Visit Summary Item Card Visit Reason CardView\"]/android.widget.RelativeLayout")
	private WebElement visitSummaryReasonForVisit;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Visit Summary Item Card Medical History CardView\"]/android.widget.RelativeLayout")
	private WebElement visitSummaryMedicalHistory;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Visit Summary Item Card Physical Examination CardView\"]/android.widget.RelativeLayout")
	private WebElement visitSummaryPhysicalExaminaton;

	@AndroidFindBy(accessibility = "Visit Summary Add Documents Title TextView")
	private WebElement visitSummaryAddDocumentsTitle;

	@AndroidFindBy(accessibility = "Visit Summary Priority Checkbox SwitchMaterial")
	private WebElement priorityVisitToggleButton;

	@AndroidFindBy(accessibility = "Visit Summary Chat Fab")
	private WebElement visitSummaryChat;

	@AndroidFindBy(accessibility = "Visit Summary Print Button")
	private WebElement visitSummaryPrintButton;

	@AndroidFindBy(accessibility = "Visit Summary Share Button")
	private WebElement visitSummaryShareButton;

	@AndroidFindBy(accessibility = "Prescription Screen Title TextView")
	private WebElement prescriptionScreenTitle;

	@AndroidFindBy(id = "org.intelehealth.app:id/tvPatientNamePresc")
	private WebElement prescriptionPatientName;

	@AndroidFindBy(id = "org.intelehealth.app:id/tvPatientIDPresc")
	private WebElement prescriptionPatientId;

	@AndroidFindBy(id = "org.intelehealth.app:id/tvPatientAgePresc")
	private WebElement prescriptionPatientAgeGender;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"org.intelehealth.app:id/cvDocDetailsPresc\"]/android.widget.RelativeLayout")
	private WebElement prescriptionConsultedDoctorDetails;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"org.intelehealth.app:id/cvDiagnosisPresc\"]/android.widget.RelativeLayout")
	private WebElement prescriptionDiagnosis;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"org.intelehealth.app:id/cvMedicationPresc\"]/android.widget.RelativeLayout")
	private WebElement prescriptionPrescribedMedications;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"org.intelehealth.app:id/cvAdvicePresc\"]/android.widget.RelativeLayout")
	private WebElement prescriptionAdvice;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"org.intelehealth.app:id/cvTestsPresc\"]/android.widget.RelativeLayout")
	private WebElement prescriptionTest;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"org.intelehealth.app:id/cvRefSpecialistPresc\"]/android.widget.RelativeLayout")
	private WebElement prescriptionReferredSpecialist;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"org.intelehealth.app:id/cvFollowUpPresc\"]/android.widget.RelativeLayout")
	private WebElement prescriptionFollowUp;

	@AndroidFindBy(id = "org.intelehealth.app:id/btnPrintPresc")
	private WebElement prescriptionPrintButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/btnSharePresc")
	private WebElement prescriptionShareButton;

	@AndroidFindBy(accessibility = "Prescription Screen Download ImageButton")
	private WebElement prescriptionScreenDownloadIcon;

	@AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Downloaded to: /storage/emulated/0/Documents/Intelehealth_PDF/Automation_A_XXXX2cdc.pdf\"]")
	private WebElement prescriptionFileDownloadedMessage;

	@AndroidFindBy(id = "com.android.printspooler:id/title")
	private WebElement printerPageTitle;

	@AndroidFindBy(id = "com.android.printspooler:id/page_content")
	private WebElement printedFile;

	@AndroidFindBy(accessibility = "Share Prescription Dialog Message TextView")
	private WebElement sharePrescriptionPopupText;

	@AndroidFindBy(accessibility = "Share Prescription Dialog Mobile Num EditText")
	private WebElement sharePrescriptionPopupMobileNumText;

	@AndroidFindBy(accessibility = "Prescription Screen Filter ImageButton")
	private WebElement prescriptionScreenKebabMenu;

	@AndroidFindBy(accessibility = "Notification Filter Reminder TextView")
	private WebElement prescriptionScreenKebabMenuHome;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Home']")
	private WebElement homeScreenHome;

	@AndroidFindBy(accessibility = "Notification Filter Incomplete TextView")
	private WebElement PrescriptionScreenKebabMenuEndVisit;

	@AndroidFindBy(accessibility = "close visits screen title")
	private WebElement closeVisitsScreenTitle;

	@AndroidFindBy(accessibility = "Patient Survey Title TextView")
	private WebElement feedbackPageTitle;

	@AndroidFindBy(accessibility = "Patient Survey Screen Give Feedback EditText")
	private WebElement feedbackPageGiveFeedbackTextArea;

	@AndroidFindBy(accessibility = "Common Message Dialog Positive Button")
	private WebElement followupReminderAlertPopupOkButton;
	
	@AndroidFindBy(accessibility = "displays the count of awaiting prescriptions textview for Received Prescriptions")
	private WebElement patientsAwaitingTheirPrescriptions;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Please remind the patient of their follow up date:')]")
	private WebElement followupReminderAlertPopupText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Received\"]")
	private WebElement receivedTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Pending\"]")
	private WebElement pendingTab;

	@AndroidFindBy(accessibility = "textview to display count of total awaiting prescriptions in pending prescriptions.")
	private WebElement pendingPrescriptionCountWithMessage;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"recent visits title textview\"]//(//android.widget.FrameLayout[@resource-id=\"org.intelehealth.app:id/fu_cardview_item\"])")
	private WebElement pendingRecentVisits;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"recent visits title textview\"]//(//android.widget.FrameLayout[@resource-id=\"org.intelehealth.app:id/fu_cardview_item\"])//(//android.widget.TextView[@content-desc=\"patient name row item title\"])")
	private WebElement pendingRecentVisitsPatientName;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"recent visits title textview\"]//(//android.widget.FrameLayout[@resource-id=\"org.intelehealth.app:id/fu_cardview_item\"])//android.widget.TextView[@content-desc=\"patient date and time row item\"]")
	private WebElement pendingRecentVisitsDate;
	
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id=\"org.intelehealth.app:id/frame_10014\"]")
	private WebElement pendingVisitPatients;

	@AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Mobile number not provided.\"]")
	private WebElement byMobileNumNotProvided;
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"More options\"]")
	private WebElement byCallMore;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"com.android.dialer:id/digits\"]")
	private WebElement byPhoneNumberDigits;
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"backspace\"]")
	private WebElement byCallBackSpace;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Add account']")
	private WebElement byTurnOnSyncAddAccount;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='No thanks']")
	private WebElement byTurnOnSyncNoThanks;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/btnPrintPresc")
	private WebElement byPrescriptionPrintButton;
	
	@AndroidFindBy(id = "org.intelehealth.app:id/btnSharePresc")
	private WebElement byPrescriptionShareButton;

	@AndroidFindBy(xpath = 
			"//android.widget.TextView[@resource-id=\"org.intelehealth.app:id/you_can_add\"]//..//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.intelehealth.app:id/recycler_recent\"]//android.widget.TextView[@content-desc=\"patient name row item title\"]")
	private WebElement recentVisitPatients;
	
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc=\"Visit Details Follow Up Parent RelativeLayout\"]")
	private WebElement followUp;
	
	// Refreshes the prescriptions
	public void refreshPrescriptions() throws Throwable {
		robot = new Robot();
		Thread.sleep(20000);
		click(homeScreenRefreshButton, "Clicking on app sync icon");
		click(homeScreenPrescriptionArrowIcon, "Clicking on prescriptions");
	}

	//Verifies that patients with received prescriptions are listed in the recent visit section
	public void verifyReceivedPrescriptionsPatientsListedInRecentVisit() throws Throwable {
		restAssured sendPrescription = new restAssured();
		sendPrescription.createPatientAndSharePrescription();
		refreshPrescriptions();
		boolean recentPatients = isDisplayed2(recentVisitPatients);
		if (recentPatients == true) {
			if (isDisplayed(recentVisitPatientName) && isDisplayed(recentVisitDateAndTime)
					&& isDisplayed(recentVisitShareIcon)) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether recent visit patients list is displayed along with patient name,date and time,share icon");
				System.out.println(
						"Recent visit patients list is displayed along with patient name,date and time,share icon");
			}
		}
		if (recentPatients == false) {
			System.err.println("There are no recent visits");
			throw new Exception("There are no recent visits");
		}
	}

	//Verifies the share functionality when clicked on any recent patient visit
	public void verifyShareFunctionalityWhenClickedOnAnyRecentPatientVisit() throws Throwable {
		restAssured sendPrescription = new restAssured();
		sendPrescription.createPatientAndSharePrescription();
		refreshPrescriptions();
		boolean recentPatients = isDisplayed2(recentVisitPatients);
		if (recentPatients == true) {
			click(recentVisitShareIcon, "Clicking on recent visit share icon");
			if (isDisplayed(enterTheMobileNoPopupText) && isDisplayed(sharePrescriptionPopupShareButton)) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether popup is displayed with proper text and share button");
				System.out.println("Popup is displayed with proper text and share button");
			}
		}
		if (recentPatients == false) {
			System.err.println("There are no recent visits");
			throw new Exception("There are no recent visits");
		}
	}

	//Verifies if the prescription is shared upon entering a valid number
	public void verifyIfThePrescriptionIsSharedOnEnteringAValidNumber() throws Throwable {
		verifyShareFunctionalityWhenClickedOnAnyRecentPatientVisit();
		click(sharePrescriptionPopupMobileNumTextfield, "Clicking on mobile number textfield");
		sendKeys(sharePrescriptionPopupMobileNumTextfield, "7892450759", "Entering the mobile number");
		click(sharePrescriptionPopupShareButton, "Clicking on share button");
		if (isDisplayed(turnOnSyncNoThanks) && isDisplayed(turnOnSyncAddAccount)) {
			ExtentReport.getTest().log(Status.INFO, "There is no whatsapp application in the device");
			System.out.println("There is no whatsapp application in the device");
		}
	}
	
	//Verifies that the count of patients awaiting prescriptions is correct
	public void verifyTheCountOfPatientsAwaitingPrescriptionsAreCorrect() throws Throwable {
		refreshPrescriptions();
		if(isDisplayed(patientsAwaitingTheirPrescriptions)) {
			ExtentReport.getTest().log(Status.INFO,"Verifying whether '<No>Patients are awaiting their prescriptions' text is displayed properly");
			System.out.println("<No>Patients are awaiting their prescriptions text is displayed properly");
		}
		String awaitingText = patientsAwaitingTheirPrescriptions.getText();
		ExtentReport.getTest().log(Status.INFO, "Getting the number of patients awaiting for their prescriptions");
		String patientsCount = extractBefore(awaitingText, "patients are awaiting their prescriptions.");
		click(pendingTab, "Clicking on pending tab");
		String pendingCountAndMessage = pendingPrescriptionCountWithMessage.getText();
		ExtentReport.getTest().log(Status.INFO, "Getting the count of pending prescriptions");
		String awaitingPatientsCount = extractBetween(pendingCountAndMessage, "Doctor is yet to send the prescriptions for",
				"patients, you can remind the doctor.");
		if(patientsCount.equals(awaitingPatientsCount)) {
			ExtentReport.getTest().log(Status.INFO,"Verifying whether the count of patients is correctly displayed");
			System.out.println("The count of patients is correctly displayed");
		}else {
			throw new Exception("The count is not displayed correctly");
		}
		
	}

	//Verifies that the user is able to navigate to the visit details page by clicking on any recent visit
	public void verifyThatUserIsAbleToNavigateToVisitDetailsPageByClickingOnAnyRecentVisit() throws Throwable {
		refreshPrescriptions();
		boolean recentPatients = isDisplayed2(recentVisitPatients);
		if (recentPatients == true) {
			click(recentVisitPatientName, "Clicking on patient from the list of recent visit");
			if (isDisplayed(visitDetailsPageTitle) && isDisplayed(visitDetailsPatientName)
					&& isDisplayed(visitDetailsPatientGenderAge) && isDisplayed(visitDetailsPatientID)
					&& isDisplayed(visitDetailsPatientCallIcon) && isDisplayed(visitDetailsPatientWhatsappIcon)
					&& isDisplayed(visitDetailsReasonForVisit) && isDisplayed(visitDetailsVisitID)
					&& isDisplayed(visitDetailsDate) && isDisplayed(visitDetailsTime)
					&& isDisplayed(visitDetailsDoctorSpeciality) && isDisplayed(visitDetailsVisitSummary)
					&& isDisplayed(visitDetailsPrescription) && isDisplayed(visitDetailsEndVisitButton)) {
				boolean ifThere = isDisplayed2(followUp);
				if (ifThere == true) {
					ExtentReport.getTest().log(Status.INFO, "Verifying whether Follow up details is displayed");
					System.out.println("Follow up details is displayed");
				}
				if (ifThere == false) {
					ExtentReport.getTest().log(Status.INFO, "There is no Follow up for the patient");
					System.out.println("There is no Follow up for the patient");
				}
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whethwe user is navigated to Visit details pagePatient Name and Gender, Age,Patient Id,Call Icon,Whatsapp icon,Reason for visit,Visit Id,Date,Time,Doctor category,Visit summary detail,Prescription detail,End visit button are displayed");
				System.out.println(
						"User is navigated to Visit details page and Patient Name and Gender, Age,Patient Id,Call Icon,Whatsapp icon,Reason for visit,Visit Id,Date,Time,Doctor category,Visit summary detail,Prescription detail,End visit button are displayed");
			}

		}

		if (recentPatients == false) {
			System.err.println("There are no recent visits");
			throw new Exception("There are no recent visits");
		}
	}

	//Verifies that the user can initiate a call to the patient
	public void verifyThatUserCanCallPatient() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPatientCallIcon, "Clicking on call icon");
		try {
			if (!isDisplayed2(byCallMore) && !isDisplayed2(byPhoneNumberDigits) && !isDisplayed2(byCallBackSpace)) {
				ExtentReport.getTest().log(Status.INFO, "Verifying whether the mobile number is not provided");
				System.out.println("Mobile number is not provided");
			} else {
				if (isDisplayed(callMoreOptions) && isDisplayed(phoneNumberDigits) && isDisplayed(callBackSpace)) {
					ExtentReport.getTest().log(Status.INFO,
							"Verifying whether the user is able to open the call list page and patient's number auto filled in dialer pad");
					System.out.println("Dial pad is opened");
				}
			}
		} catch (Exception e) {

		}

	}

	//Verifies that the user can send a WhatsApp message to the patient
	public void verifyThatUserCanSendWhatsappMessageToThePatient() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPatientWhatsappIcon, "Clicking on whatsapp icon");
		try {
			if (!isDisplayed2(byTurnOnSyncNoThanks) && !isDisplayed2(byTurnOnSyncAddAccount)) {
				ExtentReport.getTest().log(Status.INFO, "Verifying whether the mobile number is not provided");
				System.out.println("Mobile number is not provided");
			} else {
				if (isDisplayed(turnOnSyncNoThanks) && isDisplayed(turnOnSyncAddAccount)) {
					ExtentReport.getTest().log(Status.INFO,
							"Verifying whether the device is not having whatsapp installed");
					System.out.println("There is no whatsapp in the device");
				}
			}
		} catch (Exception e) {

		}

	}

	// Clicking on doctor speciality dropdown
	public void clickDoctorSpeciality() {
		click(doctorsSpecialityDropdown, "Clicking on doctors speciality dropdown");
	}

	//Verifies that the user is able to view the visit summary page
	public void verifyUserIsAbleToViewVisitSummaryPage() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		String patientNameVisitDetails = visitDetailsPatientName.getText();
		String genderAgeVisitDetails = visitDetailsPatientGenderAge.getText();
		String patientIdVisitDetails = visitDetailsPatientID.getText();
		String reasonForVisitVisitDetails = visitDetailsReasonForVisit.getText();
		String doctorSpecialityVisitDetails = visitDetailsDoctorSpeciality.getText();
		click(visitDetailsVisitSummary, "Clicking on visit summary");
		String patientNameVisitSummary = visitSummaryPatientName.getText();
		isDisplayed(visitSummaryPatientGender);
		String patientGenderVisitSummary = visitSummaryPatientGender.getText();
		String patientIdVisitSummary = visitSummaryPatientOpenMRSID.getText();
		boolean vitals = isDisplayed(visitSummaryVitals);
		boolean reasonForVisit = isDisplayed(visitSummaryReasonForVisit);
		scrollToTextContains_Android("Physical Examination");
		boolean physicalExaminaton = isDisplayed(visitSummaryPhysicalExaminaton);
		scrollToElementByDescription("Visit Summary Item Card Details 2 TextView");
		String reasonForVisitVisitSummary = visitSummaryVisitReasonComplaint.getText();
		scrollToElementByDescription("Visit Summary Speciality Header RelativeLayout");
		boolean addAdditionalDocuments = isDisplayed(visitSummaryAddDocumentsTitle);
		clickDoctorSpeciality();
		String doctorSpecialityVisitSummary = doctorsSpecialityValue.getText();
		boolean MedicalHistory = isDisplayed(visitSummaryMedicalHistory);
		click(doctorsSpecialityDropdown);
		if (patientNameVisitDetails.equals(patientNameVisitSummary)
				&& patientIdVisitDetails.equals(patientIdVisitSummary)
				&& reasonForVisitVisitDetails.equals(reasonForVisitVisitSummary)
				&& doctorSpecialityVisitDetails.equals(doctorSpecialityVisitSummary) && vitals == true
				&& reasonForVisit == true && physicalExaminaton == true && MedicalHistory == true
				&& addAdditionalDocuments == true && isDisplayed(priorityVisitToggleButton)
				&& isDisplayed(visitSummaryChat) && isDisplayed(visitSummaryShareButton)
				|| genderAgeVisitDetails.contains(patientGenderVisitSummary)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether 'Visit summary' screen is displayed with all the details entered in the previous screens Patient Name,Gender,Patient ID,Vitals,Reason for Visit,Physical examination,Medical history,Add additional document,Doctor speciality selection dropdown,Priority Visit toggle,Chat option with CTA buttons Print,Share");
			System.out.println(
					"'Visit summary' screen is displayed with all the details entered in the previous screens Patient Name,Gender,Patient ID,Vitals,Reason for Visit,Physical examination,Medical history,Add additional document,Doctor speciality selection dropdown,Priority Visit toggle,Chat option with CTA buttons Print,Share");
		} else {
			throw new Exception("Details are not displayed properly");
		}

	}

	//Verifies that the user can successfully navigate to the prescription page
	public void verifythatUserCanNavigateToPrescriptionPage() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		String patientNameVisitDetails = visitDetailsPatientName.getText();
		String genderAgeVisitDetails = visitDetailsPatientGenderAge.getText();
		String patientIdVisitDetails = visitDetailsPatientID.getText();
		click(visitDetailsPrescription, "Clicking on prescription");
		String patientNamePrescription = prescriptionPatientName.getText();
		String patientAgeGenderPrescription = prescriptionPatientAgeGender.getText();
		String patientIdPrescription = prescriptionPatientId.getText();
		isDisplayed(prescriptionConsultedDoctorDetails);
		isDisplayed(prescriptionDiagnosis);
		isDisplayed(prescriptionPrescribedMedications);
		scrollToTextContains_Android("Follow Up");
		isDisplayed(prescriptionAdvice);
		isDisplayed(prescriptionTest);
		isDisplayed(prescriptionReferredSpecialist);
		isDisplayed(prescriptionFollowUp);
		isDisplayed(prescriptionPrintButton);
		isDisplayed(prescriptionShareButton);
		if (isDisplayed(prescriptionScreenTitle) && patientNameVisitDetails.equals(patientNamePrescription)
				&& genderAgeVisitDetails.equals(patientAgeGenderPrescription)
				&& patientIdVisitDetails.equals(patientIdPrescription)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is navigated to prescription page with Patient name,Gender and Age,Patient id,Consulted doctor details,Diagnosis,Prescribed medication,Advice,Test,Referred specialist,Follow up,Print button,Share button displayed");
			System.out.println(
					" User is navigated to prescription page with Patient name,Gender and Age,Patient id,Consulted doctor details,Diagnosis,Prescribed medication,Advice,Test,Referred specialist,Follow up,Print button,Share button displayed");
		} else {
			throw new Exception("Details are not displayed properly");
		}
	}

	//Verifies that the user is able to download the prescription
	public void verifyThatUserIsAbleToDownloadThePrescription() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPrescription, "Clicking on prescription");
		click(prescriptionScreenDownloadIcon, "Clicking on download icon");
		try {
			isDisplayedWithoutWaits(prescriptionFileDownloadedMessage);
		} catch (Exception e) {
		}
		if (isDisplayed2(byPrescriptionPrintButton) && isDisplayed2(byPrescriptionShareButton)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is able to download the prescription");
			System.out.println("Able to download");
		} else {
			if (isDisplayed(visitDetailsPageTitle)) {
				ExtentReport.getTest().log(Status.INFO, "Verifying whether file is already downloaded");
				System.out.println("File is already downloaded");
			}
		}
	}

	//Verifies that the user can successfully print the prescription
	public void verifyUserCanPrintThePrescription() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPrescription, "Clicking on prescription");
		click(prescriptionPrintButton, "Clicking on print button");
		if (isDisplayed(printedFile) && isDisplayed(printerPageTitle)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether user is able to print the prescription and navigated to select a printer page");
			System.out.println("Able to print the prescription");
		}
	}
	
	//Handles the functionality to share the prescription from visit details
	public void visitDetailsPrescriptionShare() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPrescription, "Clicking on prescription");
		click(prescriptionShareButton, "Clicking on share button");
	}
	
    //Verifies that the user can successfully share the prescription with the patient
	public void verifyThatUserCanShareThePrescriptionToPatient() throws Throwable {
		if (isDisplayed(sharePrescriptionPopupText) && isDisplayed(sharePrescriptionPopupMobileNumText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether popup is opened");
			System.out.println("Popup is opened");
		}
		String ifEmpty = sharePrescriptionPopupMobileNumText.getText();
		if (ifEmpty.contentEquals("Enter mobile number")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether patient has not given the mobile number");
			System.out.println("Patient has not given mobile number");
		}
		if (!ifEmpty.contentEquals("Enter mobile number")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether popup is opened with patient number");
			System.out.println("Popup is opened with patient number");

		}
	}

	//Verifies that the user can successfully share the prescription through WhatsApp
	public void verifyUserCanShareThroughWhatsapp() throws Throwable {
		String ifEmpty = sharePrescriptionPopupMobileNumText.getText();
		if (ifEmpty.contentEquals("Enter mobile number")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether patient has not given the mobile number");
			System.out.println("Patient has not given mobile number");
			click(sharePrescriptionPopupMobileNumText, "Clicking on mobile number textfield");
			sendKeys(sharePrescriptionPopupMobileNumText, "7892450759", "Entering the mobile number");
			click(sharePrescriptionPopupShareButton, "Clicking on share prescription button in the share popup");
			if (isDisplayed(turnOnSyncNoThanks) && isDisplayed(turnOnSyncAddAccount)) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether the device is not having whatsapp installed");
				System.out.println("There is no whatsapp in the device");
			}
		}
		if (!ifEmpty.contentEquals("Enter mobile number")) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether popup is opened with patient number");
			System.out.println("Popup is opened with patient number");
			click(sharePrescriptionPopupShareButton, "Clicking on share prescription button in the share popup");
			if (isDisplayed(turnOnSyncNoThanks) && isDisplayed(turnOnSyncAddAccount)) {
				ExtentReport.getTest().log(Status.INFO,
						"Verifying whether the device is not having whatsapp installed");
				System.out.println("There is no whatsapp in the device");
			}
		}
	}

	//Verifies that clicking on the "Home" button navigates the user to the home page
	public void verifyHomeNavigatesToHomePage() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPrescription, "Clicking on prescription");
		click(prescriptionScreenKebabMenu, "Clicking on kebab menu");
		click(prescriptionScreenKebabMenuHome, "Clicking on Home");
		if (isDisplayed(homeScreenHome)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to Home page");
			System.out.println("User is navigated to Home page");
		}

	}

	//Verifies that the user can successfully end a visit and navigated to Close visits page
	public void verifyThatUserCanEndVisit() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsPrescription, "Clicking on prescription");
		click(prescriptionScreenKebabMenu, "Clicking on kebab menu");
		click(PrescriptionScreenKebabMenuEndVisit, "Clicking on end visit");
		if (isDisplayed(closeVisitsScreenTitle)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether user is navigated to Close visits page");
			System.out.println("User is navigated to Close visits page");
		}
	}

	//Verifies that the user can successfully end a visit and feedback form is opened
	public void verifyUserCanEndVisit() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsEndVisitButton, "Clicking on end visit");
		try {
			followupReminderAlertPopupOkButton.click();
		} catch (Exception e) {
		}
		if (isDisplayed(feedbackPageTitle) && isDisplayed(feedbackPageGiveFeedbackTextArea)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether feedback form is opened");
			System.out.println("Feedback form is opened");
		}
	}

	// Verifies that the user receives a follow-up alert popup when ending a visit
	public void verifyUserWillGetTheFollowUpAlertPopUpWhenEndTheVisit() throws Throwable {
		refreshPrescriptions();
		click(prescriptionPatients, "Clicking on patient from the list");
		click(visitDetailsEndVisitButton, "Clicking on end visit");
		if (isDisplayed(followupReminderAlertPopupText)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether alert is opened and proper text is displayed");
			System.out.println("Alert is opened and proper text is displayed");
		}
		click(followupReminderAlertPopupOkButton, "Clicking on okay button in the alert popup");
		if (isDisplayed(feedbackPageTitle) && isDisplayed(feedbackPageGiveFeedbackTextArea)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether feedback form is opened");
			System.out.println("Feedback form is opened");
		}
	}

	//Verifies that the awaiting prescription notification is displayed along with the count
	public void verifyTheAwaitingPrescriptionNotificationIsDisplayedAlongWithTheCount() throws Throwable {
		refreshPrescriptions();
		click(pendingTab, "Clicking on pending tab");
		String pendingCountAndMessage = pendingPrescriptionCountWithMessage.getText();
		String onlyCount = extractBetween(pendingCountAndMessage, "Doctor is yet to send the prescriptions for",
				"patients, you can remind the doctor.");
		if (onlyCount.matches("\\d+") && isDisplayed(pendingPrescriptionCountWithMessage)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether notification is displayed with pending prescription count.'Doctor is yet to send the prescriptions for <no> patients,you can remind the doctor' text is displayed");
			System.out.println(
					"Notification is displayed with pending prescription number.'Doctor is yet to send the prescriptions for <no> patients,you can remind the doctor' text is displayed");
		}else {
			throw new Exception("Count is not displayed");
		}
	}

	//Verifies that recent visits are displayed in the list
	public void verifyThatRecentVisitsDisplayInList() throws Throwable {
		refreshPrescriptions();
		click(pendingTab, "Clicking on pending tab");
		if (isDisplayed(pendingRecentVisits) && isDisplayed(pendingRecentVisitsPatientName)
				&& isDisplayed(pendingRecentVisitsDate)) {
			ExtentReport.getTest().log(Status.INFO,
					"Verifying whether recent visits are displayed along with Patient name and date");
			System.out.println("Recent visits are displayed along with Patient name and date");
		}
	}
	
	//Verifies that the user can successfully end a pending visit
	public void verifyThatUserCanEndPendingVisit() throws Throwable {
		refreshPrescriptions();
		click(pendingTab, "Clicking on pending tab");
		click(pendingVisitPatients,"Clicking on pending visit patient from the list");
		click(visitDetailsEndVisitButton,"Clicking on end visit button");
		if (isDisplayed(feedbackPageTitle) && isDisplayed(feedbackPageGiveFeedbackTextArea)) {
			ExtentReport.getTest().log(Status.INFO, "Verifying whether visit is ended and feedback form is opened");
			System.out.println("Visit is ended and feedback form is opened");
		}
	}

}
