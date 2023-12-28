package com.intelehealth.pages;

import java.util.Locale;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Page class representing the Add New Patient screen. Contains elements,
 * actions, and verifications specific to this screen.
 */
public class AddNewPatientPage extends BaseTest {

	@AndroidFindBy(accessibility = "Add Patients")
	private WebElement addNewPatientButton;
	@AndroidFindBy(accessibility = "Privacy Policy Accept Button")
	private WebElement acceptButton;
	@AndroidFindBy(accessibility = "Identification Activity Title TextView")
	private WebElement addNewPatientTitle;

	@AndroidFindBy(accessibility = "Identification Activity Personal Icon ImageView")
	private WebElement personal;
	@AndroidFindBy(accessibility = "Identification Activity Personal Title TextView")
	private WebElement personalText;
	@AndroidFindBy(accessibility = "Privacy Policy Decline Button")
	private WebElement declineButton;

	@AndroidFindBy(accessibility = "Custom Toolbar Location Name TextView")
	private WebElement location;
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter first name']")
	// accessibility = "Identification First Screen First Name EditText")
	private WebElement firstName;
	@AndroidFindBy(accessibility = "Identification First Screen Last Name EditText")
	private WebElement lastName;

	@AndroidFindBy(accessibility = "Identification First Screen Gender Male RadioButton")
	private WebElement male;
	@AndroidFindBy(accessibility = "Identification First Screen Gender Female RadioButton")
	private WebElement female;
	@AndroidFindBy(accessibility = "Identification First Screen DOB EditText")
	private WebElement dobIcon;

	@AndroidFindBy(accessibility = "Custom CalendarView Dialog Month Spinner")
	private WebElement monthSpinner;
	@AndroidFindBy(accessibility = "Custom CalendarView Dialog Year Spinner")
	private WebElement yearSpinner;
	@AndroidFindBy(accessibility = "Custom CalendarView Dialog Okay Button")
	private WebElement okayButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='March']")
	private WebElement march;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1982']")
	private WebElement year;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='6']")
	private WebElement date;
	@AndroidFindBy(accessibility = "Identification First Screen Age EditText")
	private WebElement ageTextBox;

	@AndroidFindBy(accessibility = "Identification First Screen Next Button")
	private WebElement nextButton1;
	@AndroidFindBy(accessibility = "Identification Second Screen Postal Code Title TextView")
	private WebElement postalCodeTitle;
	@AndroidFindBy(accessibility = "Identification Second Screen Postal Code EditText")
	private WebElement postalCode;
	@AndroidFindBy(accessibility = "Identification Second Screen State Spinner")
	private WebElement stateSpinner;
	@AndroidFindBy(accessibility = "Identification Second Screen District Spinner")
	private WebElement districtSpinner;

	@AndroidFindBy(accessibility = "Identification Second Screen Village EditText")
	private WebElement villageTextBox;
	@AndroidFindBy(accessibility = "Identification Second Screen Corresponding Address1 EditText")
	private WebElement correspondingAddress1;
	@AndroidFindBy(accessibility = "Identification Second Screen Corresponding Address2 EditText")
	private WebElement correspondingAddress2;

	@AndroidFindBy(accessibility = "Identification Second Screen Next Button")
	private WebElement nextButton2;
	@AndroidFindBy(xpath = "Identification First Screen Phone Num Title LinearLayout")
	private WebElement phoneNumberTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Karnataka']")
	private WebElement state;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Uttara Kannada (Karwar)']")
	private WebElement district;

	@AndroidFindBy(accessibility = "Identification Third Screen National ID EditText")
	private WebElement nationalIDTextBox;

	@AndroidFindBy(accessibility = "Identification Third Screen Occupation EditText")
	private WebElement occupationTextBox;
	// @AndroidFindBy(accessibility = "Identification Third Screen Caste Spinner")
	@AndroidFindBy(xpath = "//android.widget.Spinner[@content-desc=\"Identification Third Screen Caste Spinner \"]/android.widget.TextView")

	private WebElement categorySpinner;
	@AndroidFindBy(accessibility = "Identification Third Screen Education Spinner")
	private WebElement educationSpinner;
//	@AndroidFindBy(xpath = "Identification Third Screen Economic Spinner")
	@AndroidFindBy(xpath = "//android.widget.Spinner[@content-desc=\"Identification Third Screen Economic Spinner\"]/android.widget.TextView")
	private WebElement economicSpinner;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='OBC']")
	private WebElement obc;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Primary']")
	private WebElement primary;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='BPL']")
	private WebElement bPL;
	@AndroidFindBy(accessibility = "Identification Third Screen Next Button")
	private WebElement nextButton3;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details Edit Icon ImageView")
	private WebElement personalDetailsChangeIcon;

	@AndroidFindBy(accessibility = "Identification Activity Title TextView")
	private WebElement updatePatientScreenTitle;
	@AndroidFindBy(accessibility = "Patient Details Start Visit Button")
	private WebElement startVisitButton;
	@AndroidFindBy(accessibility = "Patient Registration Dialog Title TextView")
	private WebElement patientRegistrationDialogTitle;
	@AndroidFindBy(accessibility = "Patient Registration Dialog Subtitle TextView")
	private WebElement patientRegistrationSubTitle;

	@AndroidFindBy(accessibility = "Patient Registration Dialog Negative (No) Button")
	private WebElement cancelButton;
	@AndroidFindBy(accessibility = "Patient Registration Dialog Positive (Yes) Button")
	private WebElement continueButton;
	@AndroidFindBy(accessibility = "Visit Creation Subtitle TextView-vitals")
	private WebElement visitCreationVitalsTitle;
	@AndroidFindBy(accessibility = "Find Patient List Item Prescription Received TextView")
	private WebElement prescriptionReceived;
	@AndroidFindBy(accessibility = "Patient Details Patient Name TextView")
	private WebElement patientName;
	@AndroidFindBy(accessibility = "Patient Details Patient OpenMRS ID TextView")
	private WebElement patientID;
	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details Header Title TextView")
	private WebElement personalDetails;
	@AndroidFindBy(accessibility = "Patient Details Title TextView")
	private WebElement patientDetails;
	@AndroidFindBy(accessibility = "Visit Summary Title TextView")
	private WebElement visitSummaryTitle;
	@AndroidFindBy(accessibility = " Summary Item Card Vitals Title TextView")
	private WebElement vitalsTitle;
	@AndroidFindBy(accessibility = "Visit Summary Item Card Visit Reason Title TextView")
	private WebElement visitReason;
	@AndroidFindBy(accessibility = "Visit Summary Item Card Physical Exam Title TextView")
	private WebElement physicalExamination;
	@AndroidFindBy(accessibility = "Visit Summary Item Card Medical History Title TextView")
	private WebElement medicalHistory;

	@AndroidFindBy(accessibility = "Visit Summary Add Documents Title TextView")
	private WebElement additionalDocument;
	@AndroidFindBy(accessibility = "Visit Summary Speciality Title TextView")
	private WebElement doctorsSpeciality;
	@AndroidFindBy(accessibility = "Visit Summary Priority Checkbox SwitchMaterial")
	private WebElement priorityToggle;
	@AndroidFindBy(accessibility = "Visit Summary Print Button")
	private WebElement printButton;
	@AndroidFindBy(accessibility = "Visit Summary Share Button")
	private WebElement shareButton;
	@AndroidFindBy(accessibility = "Visit Summary Chat Fab")
	private WebElement chatIcon;

	@AndroidFindBy(accessibility = "Past Visit List Item Forward Arrow ImageButton")
	private WebElement arrow;

	@AndroidFindBy(accessibility = "Find Patient List Item Prescription Pending TextView")
	private WebElement prescriptionPending;

	@AndroidFindBy(accessibility = "Patient Details Open Visits Title TextView")
	private WebElement openVisits;

	@AndroidFindBy(accessibility = "Visit Summary Appointment Button")
	private WebElement appointmentButton;
	@AndroidFindBy(accessibility = "Visit Summary Send Visit Button")
	private WebElement sendVisitButton;

	@AndroidFindBy(accessibility = "Visit Summary Add Documents Edit ImageButton")
	private WebElement addDocumentsButton;
//	@AndroidFindBy(accessibility  = "Identification First Screen Next Button")
//	private WebElement  saveButton;
//	@AndroidFindBy(accessibility  = "Identification First Screen Next Button")
//	private WebElement  saveButton;
//	
	@AndroidFindBy(accessibility = "Patient Details Screen Address Details Edit Icon ImageView")
	private WebElement addressDetailsChangeIcon;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Name' Value TextView")
	private WebElement updatedName;

	@AndroidFindBy(accessibility = "Patient Details Screen Other Details Edit Icon ImageView")
	private WebElement otherDetailsChangeIcon;
	@AndroidFindBy(accessibility = "Patient Details Screen Other Details Header Title TextView")
	private WebElement otherDetailsTitle;

	@AndroidFindBy(accessibility = "Identification Third Screen National ID Title TextView")
	private WebElement nationalIdLabel;
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Save']")
	private WebElement saveButton;
	
	@AndroidFindBy(accessibility  = "Patient Details Refresh ImageView")
	private WebElement refreshButton;
	@AndroidFindBy(accessibility  = "Custom Toolbar App Sync Time TextView")
	private WebElement appSyncTime;
	@AndroidFindBy(accessibility  = "Identification First Screen Camera ImageView")
	private WebElement addPicture;
	@AndroidFindBy(xpath =  "//android.widget.ImageView[@resource-id='org.intelehealth.app:id/camera_switch_iv']")
	private WebElement camera;
	@AndroidFindBy(xpath =  "//android.widget.ImageView[@resource-id='org.intelehealth.app:id/utils_take_picture']")
	private WebElement captureButton;
	
	@AndroidFindBy(accessibility= "Identification First Screen Profile ImageView")
	private WebElement   image;

	// Actions for interacting with page elements

	
	// Click on the "Add Patients" button to initiate the patient addition process
	public void clickOnAddPatients() {
	    click(addNewPatientButton);
	}


	// Click on the "Accept" button to confirm or acknowledge an action
	public void clickOnAcceptButton() {
	    click(acceptButton);
	}


	
	// Click on the "Decline" button to reject or decline an action
	public void clickOnDeclineButton() {
	    click(declineButton, "Clicked on the 'Decline' button to reject or decline an action");
	}


	
	// Click on the month spinner to interact with the month selection dropdown
	public void clickOnMonthSpinner() {
	    click(monthSpinner);
	}


	
	// Click on the year spinner to interact with the year selection dropdown
	public void clickOnYearSpinner() {
	    click(yearSpinner);
	}


	
	// Click on the state spinner to interact with the state selection dropdown
	public void clickOnStateSpinner() {
	    click(stateSpinner);
	}


	
	// Click on the district spinner to interact with the district selection dropdown
	public void clickOnDistrictSpinner() {
	    click(districtSpinner);
	}

	// Click on the date of birth (DOB) icon to initiate date selection
	public void clickOnDobIcon() {
	    click(dobIcon);
	}


	

	// Select the month, e.g., March, from the dropdown
	public void selectMonth() {
	    click(march);
	}

	// Select the year from the dropdown
	public void selectYear() {
	    click(year);
	}


	// Select a date by interacting with the date element
	public void selectDate() {
	    click(date);
	}

	// Click on the "Okay" button to confirm or finalize an action
	public void clickOnOkayButton() {
	    click(okayButton);
	}


	// Click on the Next button  to proceed to the next screen
	public void clickOnNextButton1() {
	    click(nextButton1);
	}

	// Verify the elements on the Add New Patient screen
	public void verifyAddNewPatientScreen() {
	    isDisplayed(addNewPatientTitle, "Add New Patient title is displayed on the screen");
	    personal.isEnabled(); // Assuming `personal` is an element, you might want to check its state
	}


	
	

	
	// Click on the Next button  to proceed to the next screen
	public void clickOnNextButton2() {
	    click(nextButton2);
	}


	
	// Click on the Next button (Step 3) to proceed to the next screen
	public void clickOnNextButton3() {
	    click(nextButton3);
	}

	// Enter the first name in the corresponding field
	public void enterFirstName(String txt) {
	    int maxAttempts = 5;
	    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
	        try {
	         
	            // Clear the first name field
	            clear(firstName);

	            // Enter the provided text into the first name field
	            sendKeys(firstName, txt);
	            
	            // Additional actions if needed, such as clicking another element or submitting the form
	            // For example: click(submitButton);
	            
	            break;  // Break the loop if the actions are successful
	        } catch (StaleElementReferenceException ex) {
	            System.out.println("StaleElementReferenceException caught. Retrying enterFirstName attempt " + attempt);
	            // Add a small delay before retrying (customize based on your needs)
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	}



	
	// Enter a randomly generated last name into the corresponding field
//	public void enterLastName() {
//	    Faker faker = new Faker(new Locale("en-IND"));
//	    String text = faker.name().lastName();
//	    sendKeys(lastName, text);
//	}
	public void enterLastName() {
	    int maxAttempts = 3; // Maximum number of attempts to handle StaleElementReferenceException
	    int attempt = 0;
	    
	    while (attempt < maxAttempts) {
	        try {
	            Faker faker = new Faker(new Locale("en-IND"));
	            String text = faker.name().lastName();
	            sendKeys(lastName, text);
	            break; // Break out of the loop if the operation is successful
	        } catch (StaleElementReferenceException e) {
	            // Handle StaleElementReferenceException by refreshing the element or waiting
	            // You may customize this part based on your application's behavior
	            attempt++;
	            System.out.println("StaleElementReferenceException occurred. Retrying... (Attempt " + attempt + ")");
	        }
	    }
	    
	    if (attempt == maxAttempts) {
	        // Log or throw an exception if the maximum attempts are reached
	        System.out.println("Maximum attempts reached. Unable to handle StaleElementReferenceException.");
	        // Alternatively, you can throw a custom exception or log the error as needed
	    }
	}

	// Enter a specific last name value into the corresponding field
	public void enterLastNameValue(String txt) {
	    sendKeys(lastName, txt, "Entered the last name: " + txt);
	}

	
	// Select the gender, e.g., Female, from the available options
	public void selectGender() {
	    click(female);
	}

	// Select the state from the available options
	public void selectState() {
	    click(state);
	}


	
	// Select the district from the available options
	public void selectDistrict() {
	    click(district);
	}

	// Click on the "Change" icon in the personal details section
	public void clickOnChange() {
	    click(personalDetailsChangeIcon, "Clicked on the 'Change' icon in the personal details section");
	}


	
	// Click on the Save button to save the entered details
	public void clickOnSaveButton() {
	    click(saveButton);
	}

	// Verify if the displayed age matches the expected age
	public void verifyAge(String expectedAge) {
	    // Get the current age value from the ageTextBox
	    String age = ageTextBox.getText();

	    // Compare the displayed age with the expected age
	    if (Integer.parseInt(age) == Integer.parseInt(expectedAge)) {
	        System.out.println("Age verification successful!");
	    } else {
	        System.out.println("Age verification failed. Please enter the correct age.");
	    }
	}


	
	// Scroll to the element related to the phone number in the Identification First Screen
	public WebElement scrollToPhoneNumber() {
	    return getDriver().findElement(AppiumBy.androidUIAutomator(
	            "new UiScrollable(new UiSelector()"
	                    + ".scrollable(true)).scrollIntoView("
	                    + "new UiSelector().description(\"Identification First Screen Phone Num Title LinearLayout\"));"));
	}

	// Scroll to view the element related to the year, e.g., "1981"
	public WebElement scrollToViewYear() {
	    return getDriver().findElement(AppiumBy.androidUIAutomator(
	            "new UiScrollable(new UiSelector()).scrollIntoView(text(\"1981\"));"));
	}


	
	// Scroll to view the element related to the state, e.g., "Kerala"
	public WebElement scrollToViewState() {
	    return getDriver().findElement(AppiumBy.androidUIAutomator(
	            "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Kerala\"));"));
	}

	// Scroll to view the element related to the district, e.g., "Vijayapura (Bijapur)"
	public WebElement scrollToViewDistrict() {
	    return getDriver().findElement(AppiumBy.androidUIAutomator(
	            "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Vijayapura (Bijapur)\"));"));
	}


	// Scroll to view the element related to other details on the Patient Details screen
	public WebElement scrollToViewOtherDetails() {
	    return getDriver().findElement(AppiumBy.androidUIAutomator(
	            "new UiScrollable(new UiSelector()"
	                    + ".scrollable(true)).scrollIntoView("
	                    + "new UiSelector().description(\"Patient Details Screen Other Details Header Title TextView\"));"));
	}

	// Scroll to view the element related to past visits on the Patient Details screen
	public WebElement scrollToViewPastVisits() {
	    return getDriver().findElement(AppiumBy.androidUIAutomator(
	            "new UiScrollable(new UiSelector()"
	                    + ".scrollable(true)).scrollIntoView("
	                    + "new UiSelector().description(\"Patient Details Past Visits Title TextView\"));"));
	}


	// Scroll to view the element related to priority visits in the Visit Summary
	public WebElement scrollToViewPriorityVisits() {
	    return getDriver().findElement(AppiumBy.androidUIAutomator(
	            "new UiScrollable(new UiSelector()"
	                    + ".scrollable(true)).scrollIntoView("
	                    + "new UiSelector().description(\"Visit Summary Priority Title TextView\"));"));
	}

	// Scroll to view the element related to prescription received in the Patient List
	public WebElement scrollToViewPrescriptionReceived() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Prescription received\").instance(0))"));
	}


	// Scroll to view the element related to open visits in the Patient Details
	public WebElement scrollToViewOpenVisits() {
	    return getDriver().findElement(AppiumBy.androidUIAutomator(
	            "new UiScrollable(new UiSelector()"
	                    + ".scrollable(true)).scrollIntoView("
	                    + "new UiSelector().description(\"Patient Details Open Visits Title TextView\"));"));
	}

	// Scroll to view the element related to prescription pending in the Patient List
	public WebElement scrollToViewPrescriptionPending() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Prescription pending\").instance(0))"));

	}
	


	// Scroll to view the element related to the Send Visit Button in the Visit Summary
	public WebElement scrollToViewSendVisitButton() {
	    return getDriver().findElement(AppiumBy.androidUIAutomator(
	            "new UiScrollable(new UiSelector()"
	                    + ".scrollable(true)).scrollIntoView("
	                    + "new UiSelector().description(\"Visit Summary Send Visit Button\"));"));
	}

	// Verify that the Address Screen is displayed by checking the presence of the postal code title
	public void verifyAddressScreenIsDisplayed() {
	    isDisplayed(postalCodeTitle, "Address Screen is displayed by checking the presence of the postal code title");
	}


	
	// Enter the postal code into the corresponding field
	public void enterPostalCode(String txt) {
	    clear(postalCode);
	    sendKeys(postalCode, txt, "Entered the postal code: " + txt);
	}

	// Enter patient address details, including postal code, village, and address lines
	public void enterPatientAddressDetails(String code, String village, String address1, String address2) {
	    sendKeys(postalCode, code);
	    sendKeys(villageTextBox, village);
	    sendKeys(correspondingAddress1, address1);
	    sendKeys(correspondingAddress2, address2);
	}


	
	 
	// Enter other details including national ID, occupation, category, education, and economic status
	public void enterOtherDetails(String id, String occupation) {
	    sendKeys(nationalIDTextBox, id);
	    sendKeys(occupationTextBox, occupation);

	    click(categorySpinner);
	    click(obc);
	    click(educationSpinner);
	    click(primary);
	    click(economicSpinner);
	    click(bPL);
	}

	// Enter the national ID into the corresponding field
	public void enterNationalID(String id) {
	    clear(nationalIDTextBox);
	    sendKeys(nationalIDTextBox, id, "Entered national ID: " + id);
	}


	
	// Enter the occupation into the corresponding field
	public void enterOccupation(String occupation) {
	    sendKeys(occupationTextBox, occupation, "Entered occupation: " + occupation);
	}

	// Click on the "Change" icon in the personal details section
	public void clickOnPersonalDetailsChangeIcon() {
	    click(personalDetailsChangeIcon, "Clicked on the 'Change' icon in the personal details section");
	}

	// Verify that the Update Patient Screen is visible by checking the presence of the title
	public void verfyUpdatePatientScreenIsVisible() {
	    isDisplayed(updatePatientScreenTitle, "Update Patient Screen is visible by checking the presence of the title");
	}


	
	// Verify if the last name update is successful by comparing the actual and expected text
	public boolean verifyLastNameUpdateIsSuccessful(String expectedText) {
	    try {
	        String actualText = updatedName.getAttribute("value");
	        return actualText.equals(expectedText);
	    } catch (Exception e) {
	        // Handle any exceptions here (e.g., element not found)
	        e.printStackTrace();
	        return false;
	    }
	}

	// Click on the "Change" icon in the address details section
	public void clickOnAddressDetailsChangeIcon() {
	    click(addressDetailsChangeIcon, "Clicked on the 'Change' icon in the address details section");
	}


	
	// Verify if the postal code update is successful by comparing the actual and expected text
	public boolean verifyPostalCodeUpdateIsSuccessful1(String expectedText) {
	    try {
	        String actualText = postalCode.getAttribute("value");
	        System.out.println(actualText);
	        return actualText.equals(expectedText);
	    } catch (Exception e) {
	        // Handle any exceptions here (e.g., element not found)
	        e.printStackTrace();
	        return false;
	    }
	}

	// Verify if the national ID update is successful by comparing the actual and expected text
	public boolean verifyNationalIDUpdateIsSuccessful(String expectedText) {
	    try {
	        String actualText = updatedName.getAttribute("value");
	        return actualText.equals(expectedText);
	    } catch (Exception e) {
	        // Handle any exceptions here (e.g., element not found)
	        e.printStackTrace();
	        return false;
	    }
	}


	
	// Update the postal code with the provided code
	public void updatePostalCode(String code) {
	    sendKeys(postalCode, code, "Updated the postal code to: " + code);
	}

	// Verify if the postal code update is successful by comparing the actual and expected text
	public boolean verifyPostalCodeUpdateIsSuccessful(String expectedText) {
	    try {
	        String actualText = postalCode.getAttribute("value");
	        return actualText.equals(expectedText);
	    } catch (Exception e) {
	        // Handle any exceptions here (e.g., element not found)
	        e.printStackTrace();
	        return false;
	    }
	}


	// Click on the "Change" icon in the other details section
	public void clickOnOtherDetailsChangeIcon() {
	    click(otherDetailsChangeIcon, "Clicked on the 'Change' icon in the other details section");
	}

	// Verify that the Update Other Details Screen is visible by checking the presence of the national ID label
	public void verifyUpdateOtherDetailsScreenIsVisible() {
	    isDisplayed(nationalIdLabel, "Update Other Details Screen is visible by checking the presence of the national ID label");
	}

	
	// Click on the "Start Visit" button
	public void clickOnStartVisitButton() {
	    click(startVisitButton);
	}

	// Verify that the Patient Registered Popup is displayed by checking the presence of its elements
	public void verifyPatientRegisteredPopupIsDisplayed() {
	    isDisplayed(patientRegistrationDialogTitle, "Patient Registered Popup is displayed - Title");
	    isDisplayed(patientRegistrationSubTitle, "Patient Registered Popup is displayed - Subtitle");
	    isDisplayed(cancelButton, "Patient Registered Popup is displayed - Cancel Button");
	    isDisplayed(continueButton, "Patient Registered Popup is displayed - Continue Button");
	}


	
	// Click on the "Continue" button in the Patient Registered Popup
	public void clickOnContinueButton() {
	    click(continueButton);
	}

	// Verify that the Vitals Screen is displayed by checking the presence of its title
	public void verifyVitalsScreenIsDisplayed() {
	    isDisplayed(visitCreationVitalsTitle, "Vitals Screen is displayed - Title");
	}

	// Click on the "Prescription Received" checkbox
	public void clickOnPrescriptionReceived() {
	    click(prescriptionReceived, "Clicked on the 'Prescription Received' checkbox");
	}

	// Click on the arrow (presumably for navigation)
	public void clickOnArrow() {
	    click(arrow, "Clicked on the 'Arrow' for navigation");
	}


	
	// Verify elements on the Past Visit Summary Screen
	public void verifyPastVisitSummaryScreen() {
	    isDisplayed(visitSummaryTitle, "Past Visit Summary Screen - Title is Displayed");
	    isDisplayed(patientName, "Past Visit Summary Screen - Patient Name is Displayed");
	    isDisplayed(patientID, "Past Visit Summary Screen - Patient ID is Dsiplayed");
	    isDisplayed(visitCreationVitalsTitle, "Past Visit Summary Screen - Visit Creation Vitals Title  is Dsiplayed");
	    isDisplayed(vitalsTitle, "Past Visit Summary Screen - Vitals Title  is Dsiplayed");
	    isDisplayed(visitReason, "Past Visit Summary Screen - Visit Reason  is Dsiplayed");
	    isDisplayed(physicalExamination, "Past Visit Summary Screen - Physical Examination  is Dsiplayed");
	    isDisplayed(medicalHistory, "Past Visit Summary Screen - Medical History  is Dsiplayed");
	    isDisplayed(additionalDocument, "Past Visit Summary Screen - Additional Document  is Dsiplayed");
	    isDisplayed(doctorsSpeciality, "Past Visit Summary Screen - Doctor's Speciality  is Dsiplayed");
	    isDisplayed(priorityToggle, "Past Visit Summary Screen - Priority Toggle  is Dsiplayed");
	    isDisplayed(printButton, "Past Visit Summary Screen - Print Button  is Dsiplayed");
	    isDisplayed(shareButton, "Past Visit Summary Screen - Share Button  is Dsiplayed");
	    isDisplayed(chatIcon, "Past Visit Summary Screen - Chat Icon  is Dsiplayed");
	}


	
	// Click on the "Prescription Pending" checkbox
	public void clickOnPrescriptionPending() {
	    click(prescriptionPending, "Clicked on the 'Prescription Pending' checkbox");
	}



	// Verify elements on the Open Visit Summary Screen
	public void verifyOpenVisitSummaryScreen() {
	    isDisplayed(visitSummaryTitle, "Open Visit Summary Screen - Title  is Dsiplayed");
	    isDisplayed(patientName, "Open Visit Summary Screen - Patient Name  is Dsiplayed");
	    isDisplayed(patientID, "Open Visit Summary Screen - Patient ID  is Dsiplayed");
	    isDisplayed(visitCreationVitalsTitle, "Open Visit Summary Screen - Visit Creation Vitals Title  is Dsiplayed");
	    isDisplayed(vitalsTitle, "Open Visit Summary Screen - Vitals Title  is Dsiplayed");
	    isDisplayed(visitReason, "Open Visit Summary Screen - Visit Reason  is Dsiplayed");
	    isDisplayed(physicalExamination, "Open Visit Summary Screen - Physical Examination  is Dsiplayed");
	    isDisplayed(medicalHistory, "Open Visit Summary Screen - Medical History  is Dsiplayed");
	    isDisplayed(additionalDocument, "Open Visit Summary Screen - Additional Document  is Dsiplayed");
	    isDisplayed(doctorsSpeciality, "Open Visit Summary Screen - Doctor's Speciality  is Dsiplayed");
	    isDisplayed(priorityToggle, "Open Visit Summary Screen - Priority Toggle  is Dsiplayed");
	    isDisplayed(appointmentButton, "Open Visit Summary Screen - Appointment Button  is Dsiplayed");
	    isDisplayed(sendVisitButton, "Open Visit Summary Screen - Send Visit Button  is Dsiplayed");
	}


	
	public void registerAPatient(String txt, String code, String village, String address1, String address2, String id,
			String occupation) throws InterruptedException {
		// Click on the 'Add Patients' button to initiate the registration process
		clickOnAddPatients();

		// Click on the 'Accept' button to agree to the registration terms
		clickOnAcceptButton();
		Thread.sleep(8000);


//		// Enter the first name of the patient
	
		
			 enterFirstName(txt);
		// Enter the last name of the patient (assuming it's handled within the method)
		enterLastName();
		// Select the gender of the patient
		selectGender();
		// Scroll to an element on the page (assuming it's a required action)
		scrollToPhoneNumber();
		// Click on the 'Date of Birth' icon to set the patient's birthdate
		clickOnDobIcon();

		// Click on the month spinner to select the birth month
		clickOnMonthSpinner();
		selectMonth();

		// Click on the year spinner to select the birth year
		clickOnYearSpinner();

		// Scroll to view and select the birth year
		scrollToViewYear();
		selectYear();

		// Select the birth date
		selectDate();

		// Click on the 'Okay' button to confirm the selected date
		clickOnOkayButton();

		// Move to the next registration step
		clickOnNextButton1();

		// Select the state of the patient's address
		clickOnStateSpinner();
		scrollToViewState();
		selectState();

		// Select the district of the patient's address
		clickOnDistrictSpinner();
		scrollToViewDistrict();
		selectDistrict();

		// Enter the patient's address details
		enterPatientAddressDetails(code, village, address1, address2);

		// Move to the next registration step
		clickOnNextButton2();

		// Enter additional details such as national ID and occupation
		enterOtherDetails(id, occupation);

		// Move to the next registration step
		clickOnNextButton3();

		// Click on the 'Start Visit' button to initiate the patient visit
		clickOnStartVisitButton();

		// Click on the 'Continue' button to proceed with the registration process
		clickOnContinueButton();
		
	}
	public void verifySyncFunctionality(String txt, String code, String village, String address1, String address2, String id,
			String occupation) {
		String syncTime = appSyncTime.getText();
		System.out.println(syncTime);
		// Click on the 'Add Patients' button to initiate the registration process
		clickOnAddPatients();

		// Click on the 'Accept' button to agree to the registration terms
		clickOnAcceptButton();
	


//		// Enter the first name of the patient
	
		
			 enterFirstName(txt);
		// Enter the last name of the patient (assuming it's handled within the method)
		enterLastName();
		// Select the gender of the patient
		selectGender();
		// Scroll to an element on the page (assuming it's a required action)
		scrollToPhoneNumber();
		// Click on the 'Date of Birth' icon to set the patient's birthdate
		clickOnDobIcon();

		// Click on the month spinner to select the birth month
		clickOnMonthSpinner();
		selectMonth();

		// Click on the year spinner to select the birth year
		clickOnYearSpinner();

		// Scroll to view and select the birth year
		scrollToViewYear();
		selectYear();

		// Select the birth date
		selectDate();

		// Click on the 'Okay' button to confirm the selected date
		clickOnOkayButton();

		// Move to the next registration step
		clickOnNextButton1();

		// Select the state of the patient's address
		clickOnStateSpinner();
		scrollToViewState();
		selectState();

		// Select the district of the patient's address
		clickOnDistrictSpinner();
		scrollToViewDistrict();
		selectDistrict();

		// Enter the patient's address details
		enterPatientAddressDetails(code, village, address1, address2);

		// Move to the next registration step
		clickOnNextButton2();

		// Enter additional details such as national ID and occupation
		enterOtherDetails(id, occupation);

		// Move to the next registration step
		clickOnNextButton3();
		click(refreshButton, "Clicked on Sync");
		getDriver().navigate().back();
		String currentSyncTime = appSyncTime.getText();
		System.out.println(currentSyncTime);
		if(syncTime.equals(currentSyncTime)) {
			ExtentReport.getTest().log(Status.INFO, "Data not Synced");
			System.out.println("Data Sync Failed");
		}
		else {
			ExtentReport.getTest().log(Status.INFO, "Verification of Data sync is success");
			System.out.println("Data synced Successfully");
		}
		
	}
	// Add a profile picture and verify the process
	public void addProfilePictureAndVerify() {
	    click(addNewPatientButton, "Clicked on 'Add Patients'");
	    click(acceptButton, "Accepted the Privacy Policy");
	    click(addPicture, "Clicked on 'Add Picture'");
	    click(camera, "Clicked on 'Camera'");
	    click(captureButton, "Captured an Image");
	    isDisplayed(image, "Captured Image is Displayed");
	}


	public void verifyDashboardScreenIsVisible() {
		isDisplayed(location);
		
	}


	


}