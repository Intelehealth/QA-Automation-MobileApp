package com.intelehealth.pages;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class StartVisit1And2StepsPage extends BaseTest {
	@AndroidFindBy(accessibility = "Patient Details Patient Name TextView")
	private WebElement patientName;
	@AndroidFindBy(accessibility = "Patient Details Patient OpenMRS ID TextView")
	private WebElement patientId;
	@AndroidFindBy(accessibility = "Visit Creation Title TextView")
	private WebElement visitCreatedPatientName;

	@AndroidFindBy(accessibility = "Visit Creation Title Description TextView")
	private WebElement visitCreatedPatientAge;

	@AndroidFindBy(accessibility = "Visit Creation Refresh ImageButton")
	private WebElement visitCreationRefreshImageButton;

	@AndroidFindBy(accessibility = "Visit Creation Subtitle TextView")
	private WebElement visitCreationSubtitle;

	@AndroidFindBy(accessibility = "Visit Creation Step 1 ProgressBar")
	private WebElement visitCreationStep1ProgressBar;

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
	@AndroidFindBy(accessibility = "next button in Vital Collection Fragment")
	private WebElement nextButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter the patient’s vitals']")
	private WebElement enterthePatientsVitalsLabel;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter patient’s body measurement details']")
	private WebElement enterPatientsBodyMeasurementDetailsLabel;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='BMI index(auto-calculated)']")
	private WebElement bMIIndex;

	@AndroidFindBy(accessibility = "bmi value textview in Vital Collection Fragment")
	private WebElement bMIvalue;
	@AndroidFindBy(accessibility = "bmi status textview in Vital Collection Fragment")
	private WebElement bMIStatus;
	@AndroidFindBy(accessibility = "bmi status textview in Vital Collection Fragment")
	private WebElement normalWeight;
	@AndroidFindBy(accessibility = "Vitals Summary Vitals Subtitle TextView")
	private WebElement vitalSummaryTitle;

// 1/4 vital summary screen elements
	@AndroidFindBy(accessibility = "Vitals Summary Vitals Icon ImageView")
	private WebElement thermoMeter;

	@AndroidFindBy(accessibility = "Vitals Summary Details Label TextView")
	private WebElement vitalsDetailsLabel;

	@AndroidFindBy(accessibility = "Vitals Summary Edit TextView")
	private WebElement vitalsTitle;

	@AndroidFindBy(accessibility = "Vitals Summary Height Title TextView")
	private WebElement heightTitle;

	@AndroidFindBy(accessibility = "Vitals Summary Height Value TextView")
	private WebElement heightValue;

	@AndroidFindBy(accessibility = "Vitals Summary Weight Title TextView")
	private WebElement weightTitle;

	@AndroidFindBy(accessibility = "Vitals Summary Weight Value TextView")
	private WebElement weightValue;

	@AndroidFindBy(accessibility = "Vitals Summary BMI Title TextView")
	private WebElement bmiTitle;

	@AndroidFindBy(accessibility = "Vitals Summary BMI Value TextView")
	private WebElement bmiValue;

	@AndroidFindBy(accessibility = "Vitals Summary BP Title TextView")
	private WebElement bpTitle;

	@AndroidFindBy(accessibility = "Vitals Summary BP Value TextView")
	private WebElement bpValue;

	@AndroidFindBy(accessibility = "Vitals Summary Pulse Title TextView")
	private WebElement pulseTitle;

	@AndroidFindBy(accessibility = "Vitals Summary Pulse Value TextView")
	private WebElement pulseValue;

	@AndroidFindBy(accessibility = "Vitals Summary Temp Title TextView")
	private WebElement tempTitle;

	@AndroidFindBy(accessibility = "Vitals Summary Temp Value TextView")
	private WebElement tempValue;

	@AndroidFindBy(accessibility = "Vitals Summary SpO2 Title TextView")
	private WebElement spo2Title;

	@AndroidFindBy(accessibility = "Vitals Summary SpO2 Value TextView")
	private WebElement spo2Value;

	@AndroidFindBy(accessibility = "Vitals Summary RR Title TextView")
	private WebElement rrTitle;

	@AndroidFindBy(accessibility = "Vitals Summary RR Value TextView")
	private WebElement rrValue;

	@AndroidFindBy(accessibility = "Vitals Summary Cancel Button")
	private WebElement backButton;

	@AndroidFindBy(accessibility = "Vitals Summary Submit Button")
	private WebElement confirmButton;
// 2/4 visit reason
	@AndroidFindBy(accessibility = "Visit Creation Subtitle TextView")
	private WebElement visitReasonTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='What is the reason for this visit?']")
	private WebElement whatIsTheReasonForVisit;
	@AndroidFindBy(accessibility = "AutoComplete TextView for search feature in Visit Reason Capture Fragment")
	private WebElement searchReasonTextBox;
	@AndroidFindBy(accessibility = "next button for visit reason capture fragment")
	private WebElement visitReasonNextButton;
	@AndroidFindBy(accessibility = "back button to previous screen visit reason capture fragment")
	private WebElement visitReasononBackButton;
	@AndroidFindBy(accessibility = "selected reasons textview in Visit Reason Capture Fragment")
	
	private WebElement selectedReasonLabel;
	@AndroidFindBy(accessibility = "all reasons textview in Visit Reason Capture Fragment")
	private WebElement allResaonsection;
	@AndroidFindBy(accessibility = "Visit Creation Title Description TextView")
	private WebElement patientAgeAndGender;
	@AndroidFindBy(accessibility = "Visit Creation Title TextView")
	private WebElement visitReasonPatientname;
	@AndroidFindBy(xpath = "//android.widget.ProgressBar[@text='100.0']")
	private WebElement progressBar1;
	@AndroidFindBy(xpath = "//android.widget.ProgressBar[@text='20.0']")
	private WebElement progressBar2;

	@AndroidFindBy(accessibility = "Selected Chip Item View Name TextView")
	private WebElement selectedReason;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Abdominal Pain']")
	private WebElement visitReasonAbdominalPain;
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Chips for Reason Item TextView']")
	private WebElement chipReason;
	@AndroidFindBy(xpath  = "//android.widget.TextView[@text='Diarrhea']")
	private WebElement diarrhea;

	@AndroidFindBy(xpath =  " //android.widget.TextView[@text='Fever']")
	private WebElement fever;

	@AndroidFindBy(xpath =  "//android.widget.TextView[@text='Hypertension']")
	private WebElement hypertension;
	@AndroidFindBy(accessibility = "Selected Chip Item View Remove ImageView")
	private WebElement remove;
	@AndroidFindBy(xpath= "(//android.widget.TextView[@content-desc='Selected Chip Item View Name TextView'])")
	private List<WebElement> allSelectedReasons;
// confirm visit popup

	@AndroidFindBy(accessibility = "Common Message Dialog With Chips Title TextView")
	private WebElement confirmVisitPopupTitle;
	@AndroidFindBy(accessibility = "Common Message Dialog With Chips Subtitle TextView")
	private WebElement confirmVisitPopupSubTitle;
	@AndroidFindBy(accessibility = "Selected Chip Preview Item View Name TextView")
	private WebElement reason;
	@AndroidFindBy(accessibility = "Common Message Dialog With Chips Negative Button")
	private WebElement noButton;
	@AndroidFindBy(accessibility = "Common Message Dialog With Chips Positive Button")
	private WebElement yesButton;

// 2/4 Visit reason : Abdominal Pain
	@AndroidFindBy(accessibility = "Visit Creation Subtitle TextView")
	private WebElement visitCreationTitle;

	@AndroidFindBy(accessibility = "Question Node Question Count TextView")
	private WebElement OneofElevenQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Which part of the abdomen do you feel pain?']")
	private WebElement addominalPainReasonQuestion1;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Upper (R) - Right Hypochondrium']")
	private WebElement upperRightHypochondrium;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Upper (C) - Epigastric']")
	private WebElement upperEpigastric;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Upper (L) - Left Hypochondrium']")
	private WebElement upperLeftHypochondrium;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Middle (R) - Right Lumbar']")
	private WebElement middleRightLumbar;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Middle (C) - Umbilical']")
	private WebElement middleUmbilical;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Middle (L) - Left Lumbar']")
	private WebElement middleLeftLumbar;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Lower (R) - Right Illiac Fossa']")
	private WebElement lowerRightIliacFossa;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Lower (C) - Hypogastric/Suprapubic']")
	private WebElement lowerHypogastric;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Lower (R) - Left Illiac Fossa']")
	private WebElement lowerLeftIliacFossa;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All over']")
	private WebElement allOver;
	@AndroidFindBy(accessibility = "Question Node Submit Button")
	private WebElement submitButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='2 of 12 questions']")
	private WebElement twoOfElevenQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3 of 12 questions']")
	private WebElement threeofTwelveQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Pain radiates']")
	private WebElement painRadiates;
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Nested Question Item View Question TextView\"]")
	private WebElement painRadiatesTo;
	// android.widget.TextView[@text='Pain radiates to']
	//// android.widget.TextView[@content-desc="Nested Question Item View Question
	// TextView"]
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Right shoulder']")
	private WebElement rightShoulder;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Right scapula']")
	private WebElement rightScapula;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Groin']")
	private WebElement groin;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sacral region']")
	private WebElement sacralRegion;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Right flank']")
	private WebElement rightFlank;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Left flank']")
	private WebElement leftFlank;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Right groin']")
	private WebElement rightGroin;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Chips for Reason Item TextView\"])[19]")
	private WebElement leftGroin;
	// (//android.widget.TextView[@content-desc="Chips for Reason Item
	// TextView"])[19]
	// android.widget.TextView[@text='Left groin']
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Chest']")
	private WebElement chest;
	@AndroidFindBy(accessibility = "Nested Question Item View Submit Button")
	private WebElement painRadiatesSubmitButton;
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Skip']")
	private WebElement painRadiatesSkipButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3 of 12 questions']")
	private WebElement threeOfElevenQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='2']")
	private WebElement two;

	@AndroidFindBy(accessibility = "Visit Reason Time Range Number Picker AppCompatSpinner")
	private WebElement numberSpinner;
	@AndroidFindBy(accessibility = "Visit Reason Time Range Duration Type AppCompatSpinner")
	private WebElement durationSpinner;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Days']")
	private WebElement days;
	@AndroidFindBy(accessibility = "Visit Reason Time Range Submit Button")
	private WebElement threeOfElevenQuestionSubmitButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='4 of 12 questions']")
	private WebElement fourOfElevenQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Gradual']")
	private WebElement gradualOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='5 of 12 questions']")
	private WebElement fiveOfTwelveQuestions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Other [describe]']")
	private WebElement othersOptionOfFourOfTwelveQuestions;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Describe...']")
	private WebElement describeTextField;
	@AndroidFindBy(accessibility = "Visit Reason Input Text Enter Value EditText")
	private WebElement describeField;
	@AndroidFindBy(accessibility = "Visit Reason Input Text Submit Button")
	private WebElement submitButtonOfFourOfTwelveQuestion;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Night']")
	private WebElement night;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='6 of 12 questions']")
	private WebElement sixOfTwelveQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Other [Describe]']")
	private WebElement othersOptionOfFiveOfTwelveQuestions;
	@AndroidFindBy(xpath = "(//android.widget.Button[@content-desc=\"Visit Reason Input Text Submit Button\"])[last()]")
	private WebElement fiveOfTwelveQuestionsSubmitButton;
// 6 of 12 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Constant']")
	private WebElement constantOptionOfSixOfTwelveQuetions;
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Submit']")
	private WebElement sixOfTwelveQuestionsSubmitButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='7 of 12 questions']")
	private WebElement sevenOfTwelveQuestions;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='Other [describe]'])")
	private WebElement othersOptionOfSixOfTwelveQuestions;
// 7 of 12 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mild, 1-3']")
	private WebElement mildOneThreeOption;

// 8 of 12 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='8 of 12 questions']")
	private WebElement eightOfTwelveQusetions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Coughing']")
	private WebElement coughingOption;
	@AndroidFindBy(accessibility = "Question Node Submit Button")
	private WebElement eightOfTwelveQuestionsSubmitButton;
	@AndroidFindBy(accessibility = "Visit Reason Input Text Submit Button")
	private WebElement eightOfTwelveSubmitButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Other [describe]']")
	private WebElement otherOptionOfEightOfTwelveQuestions;

// 9 of 12 questions
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='9 of 12 questions']")
	private WebElement nineOfTwelveQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Food']")
	private WebElement foodOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Leaning forward']")
	private WebElement leaningForwardOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Other']")
	private WebElement otherOption;
	@AndroidFindBy(xpath = "(//android.widget.Button[@content-desc=\"Question Node Submit Button\"])[last()]")
	private WebElement nineOfTwelveQusetionsSubmitButton;

// 10 of 12
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Has not started menstruation']")
	private WebElement hasNotStartedmenstruationOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='10 of 12 questions']")
	private WebElement tenOfTwelveQusetions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Is menstruating']")
	private WebElement isMenstruatingOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Age at onset']")
	private WebElement ageAtOnset;
	@AndroidFindBy(accessibility = "Visit Reason Input Text Enter Value EditText")
	private WebElement ageAtOnSetInputFiled;
	@AndroidFindBy(accessibility = "Visit Reason Input Text Submit Button")
	private WebElement ageOnSetInputFeildSubmitButton;
	@AndroidFindBy(accessibility = "Visit Reason Input Text Skip Button")
	private WebElement ageOnSetSkipButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Last menstruation period']")
	private WebElement lastMenstruationPeriod;
	@AndroidFindBy(xpath = "//android.widget.CalendarView[@content-desc='Visit Reason Date Picker CalendarView']")
	private WebElement calender;
	@AndroidFindBy(xpath = "//android.view.View[@text='1']")
	private WebElement menstruationDate;
	@AndroidFindBy(accessibility = "Visit Reason Date Picker Selected Date TextView")
	private WebElement selectedMenstruationDate;
	@AndroidFindBy(accessibility = "Visit Reason Date Picker Submit Button")
	private WebElement calenderSubmitButton;
//11 of 12 
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='11 of 12 questions']")
	private WebElement elevenOfTwelveQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='None']")
	private WebElement noneOption;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Yes [Describe]']")
	private WebElement yesDescribeOption;
//12 of 12 
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='12 of 12 questions']")
	private WebElement twelveOfTweleveQusetions;
	@AndroidFindBy(accessibility = "Visit Reason Input Text Skip Button")
	private WebElement twelveOfTwelveSkipButton;
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Visit Reason Input Text Submit Button']")
	private WebElement twelveOfTwelveSubmitButton;
//Associated symptoms
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='2/4 Visit reason : Associated symptoms']")
	private WebElement associatedSymptomsTitle;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Do you have the following symptom(s)?']")
	private WebElement doYouHaveFollowingSymptom;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc='Associated Symptom List Item 'Yes' TextView'])[6]")
	private WebElement changeInApetiteYesButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1 of 1 questions']")
	private WebElement oneOfOneQuestions;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='What is the change?']")
	private WebElement whatIsTheChange;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Increased']")
	private WebElement increased;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Decreased']")
	private WebElement decreased;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Associated Symptom List Item 'Yes' TextView\"])[7]")
	private WebElement sevenYesButton;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Associated Symptom List Item 'Yes' TextView\"])[8]")
	private WebElement eightYesButton;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Associated Symptom List Item 'Yes' TextView\"])[4]")
	private WebElement nineYesButton;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Associated Symptom List Item 'Yes' TextView\"])[16]")
	private WebElement sixteenYesButton;
	@AndroidFindBy(accessibility = "Visit Reason Input Text Enter Value EditText")
	private WebElement describeTextArea;
	@AndroidFindBy(accessibility = "Associated Symptoms Questionnaire Main View Submit Button")
	private WebElement associatedSymptomSubmitButton;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Associated Symptom List Item 'Yes' TextView\"])[1]")
	private WebElement firstYesButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='8. Color change in stool [describe]']")
	private WebElement eighthAssociatedSymptom;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='20. Vomiting']")
	private WebElement vomiting;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='No'])[last()]")
	private WebElement lastNoButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No']")
	private List<WebElement> asSymptomNoButton;
	// 2/4 visit reason summary screen
	@AndroidFindBy(accessibility = "Visit Reason Summary Fragment Subtitle TextView")
	private WebElement visitReasonSummaryTitle;

	@AndroidFindBy(accessibility = "Main Row Item Complaint Edit TextView")
	private WebElement abdominalPainChangeButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Food']")
	private WebElement food;
	@AndroidFindBy(accessibility = "Visit Reason Summary Fragment Cancel Button")
	private WebElement VisitReasonSummaryScreenBackButton;

	@AndroidFindBy(accessibility = "Visit Reason Summary Fragment Submit Button")
	private WebElement VisitReasonSummaryScreenConfirmButton;
	@AndroidFindBy(accessibility = "Common Message Dialog Title TextView")
	private WebElement pleaseWashOrSanitizeYourHands;
	@AndroidFindBy(accessibility = "Common Message Dialog Positive Button")
	private WebElement okayButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3/4 Physical Examination']")
	private WebElement physicalExaminationScreenTitle;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Patient reports']")
	private WebElement patientReports;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Patient denies']")
	private WebElement patientDenies;
	@AndroidFindBy(accessibility = "Visit Reason Summary Fragment Associated Symptoms Edit TextView")
	private WebElement associatedSymptomChangeButton;

// Patient Details Screen
	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details Header Title TextView")
	private WebElement personalDetailsHeader;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Name' Value TextView")
	private WebElement nameValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Gender' Value TextView")
	private WebElement genderValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'DOB' Value TextView")
	private WebElement dobValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Personal Details 'Age' Value TextView")
	private WebElement ageValue;

// Address Details Section
	@AndroidFindBy(accessibility = "Patient Details Screen Address Details Header Title TextView")
	private WebElement addressDetailsHeader;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Postal Code' Value Textview")
	private WebElement postalCodeValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Country' Value TextView")
	private WebElement countryValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'State' Value TextView")
	private WebElement stateValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'District' Value TextView")
	private WebElement districtValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Village' Value TextView")
	private WebElement villageValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Corresponding Address 1' TableRow")
	private WebElement correspondingAddress1Row;

	@AndroidFindBy(accessibility = "Patient Details Screen Address Details 'Corresponding Address 2' Value TextView")
	private WebElement correspondingAddress2Value;

// Other Details Section
	@AndroidFindBy(accessibility = "Patient Details Screen Other Details 'National ID' Value TextView")
	private WebElement nationalIdValue;
	@AndroidFindBy(accessibility = "Patient Details Screen Other Details Header Title TextView")
	private WebElement otherDetailsHeader;

	@AndroidFindBy(accessibility = "Patient Details Screen Other Details 'Occupation' Value TextView")
	private WebElement occupationValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Other Details 'Caste' Value TextView")
	private WebElement casteValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Other Details 'Education' Value TextView")
	private WebElement educationValue;

	@AndroidFindBy(accessibility = "Patient Details Screen Other Details 'Economic' Value TextView")
	private WebElement economicValue;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Associated Symptom List Item 'No' TextView\"])[5]")
	private WebElement asymNoButton;


//scroll


	
	
//
// public WebElement scrollToSkipButtonOnPainRadiatesScreen() {
// return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Chest\").instance(0))"));
// }

	//
// ("new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
// + "new UiSelector().description(\"//android.widget.TextView[@text='Chest']\"));"));

	
	public WebElement scrollUpToSkipButtonOnPainRadiatesScreen() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"//android.widget.Button[@content-desc=\"Nested Question Item View Skip Button\"]\").instance(0));"));
	}

	
	public WebElement scrollToSubmitButtonOfFourOfTwelvequestions() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"Visit Reason Input Text Submit Button\").instance(0));"));
	}

	public WebElement scrollToSubmitButtonOfFiveOfTwelvequestions() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"(//android.widget.Button[@content-desc=\\\"Visit Reason Input Text Submit Button\\\"])[2]\").instance(0));"));
	}

	public WebElement scrollToWhatIsTheChangeQuestion() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"decreased\").instance(0));"));
	}

//android.widget.Button[@content-desc=\"Visit Reason Input Text Submit Button\"])[2]
	//Clicks on the 'Next' button for the Visit Reason section. (Step 2 out of 4)
	
	public void clickOnNextButtonOfVisitReason() {// 2/4
		click(visitReasonNextButton);
	}

	
	 //Clicks on the 'Next' button for the first Vitals section. (Step 1 out of 4)
	 
	public void clickOnFirstVitalsNextButton() {// 1/4
		click(nextButton);
	}

	
	//  Verifies that the Vitals Summary screen is displayed.
	
	public void verifyVitailsSummaryScreenIsDisplayed() throws InterruptedException {
		isDisplayed(vitalSummaryTitle);
	}

	
	// Verifies the UI elements of the first Vitals screen.
	  public void verifyUiOfFirstVitalsScreen() throws InterruptedException {
		  

		    // Check if the following UI elements are displayed on the screen
		    isDisplayed(enterthePatientsVitalsLabel, "Enter the Patient's Vitals label is displayed");
		    isDisplayed(visitCreatedPatientName, "Created Patient Name is displayed");
		    isDisplayed(visitCreatedPatientAge, "Created Patient Age is displayed");
		    isDisplayed(visitCreationRefreshImageButton, "Visit Creation Refresh Image Button is displayed");
		    isDisplayed(visitCreationSubtitle, "Visit Creation Subtitle is displayed");
		    isDisplayed(visitCreationStep1ProgressBar, "Visit Creation Step 1 ProgressBar is displayed");
		    isDisplayed(heightEditText, "Height EditText is displayed");
		    isDisplayed(weightEditText, "Weight EditText is displayed");
		    isDisplayed(bMIIndex, " BMI Index is displayed");
		    isDisplayed(bpSystolicEditText, "Blood Pressure Systolic EditText is displayed");
		    isDisplayed(bpDiastolicEditText, "Blood Pressure Diastolic EditText is displayed");
		    isDisplayed(pulseEditText, "Pulse EditText is displayed");
		    isDisplayed(temperatureEditText, "Temperature EditText is displayed");

		    
		    // Scroll to view the 'Respiratory Rate' field
		    scrollToViewRespiratoryRate();

		    
		    isDisplayed(spo2EditText, "SpO2 EditText is displayed");
		    isDisplayed(respiratoryRateEditText, " Respiratory Rate EditText is displayed");
		}

		
	
	 // Retrieves and prints the name of the patient from the UI.
	 
	public void getPatientName() {
		String text = visitCreatedPatientName.getText();
		System.out.println(text);
	}

	 // Scrolls to view the Respiratory Rate input field in the Vital Collection Fragment.
	
	public WebElement scrollToViewRespiratoryRate() {

		return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).scrollIntoView("
				+ "new UiSelector().description(\"Respiratory Rate input edittext in Vital Collection Fragment\"));"));

	}

	
	 // Enters the specified text into the Height input field.
	 
	 
	public void enterHeight(String txt) throws InterruptedException {
		sendKeys(heightEditText, txt,"Entered Height");
	}

	
	 //Enters the specified text into the Weight input field.
	  
	 

	public void enterWeight(String txt) throws InterruptedException {
		sendKeys(weightEditText, txt,"Entered weight");
	}

	
	

	public void verifyBmiIsAutoCalculated(String expectedBmiValue) {
// Get the current BMI value from the UI
		String val = bMIvalue.getText();
// Compare the actual and expected BMI values
		if (val.equals(expectedBmiValue)) {
			System.out.println("BMI value is autocalaculated");
			ExtentReport.getTest().log(Status.INFO, "BMI value is autocalaculated");
		} else {
			System.out.println("BMI value is autocalculation is failed");
			ExtentReport.getTest().log(Status.INFO, "BMI value is autocalculation is failed");
		}
	}

// 
// public void writeToPatientJsonFile() throws IOException {
// // Get patient information
// String name = (patientName != null) ? patientName.getText() : "Unknown";
// String id = (patientId != null) ? patientId.getText() : "Unknown";
//
// // Create JSON object for patient data
// JSONObject patientData = createPatientObject(name, id);
//
// // Specify the file path where you want to write the JSON data
// String filePath = "src/test/resources/data/patient.json";
//
// // Read existing data from the file, if it exists
// JSONArray existingData;
// if (new File(filePath).exists()) {
// String fileContent = readFile(filePath);
// existingData = new JSONArray(fileContent);
// } else {
// // File doesn't exist, create a new JSON array
// existingData = new JSONArray();
// }
//
// // Add the new data to the existing JSON array
// existingData.put(patientData);
//
// // Write to JSON file
// try (FileWriter fileWriter = new FileWriter(filePath)) {
// fileWriter.write(existingData.toString(4)); // The '4' is for indentation (optional)
// System.out.println("Data has been written to " + filePath);
// }
// }
//
// private JSONObject createPatientObject(String name, String id) {
// JSONObject patientData = new JSONObject();
// patientData.put("Name", name);
// patientData.put("Id", id);
// return patientData;
// }
//
// private String readFile(String filePath) throws IOException {
// StringBuilder contentBuilder = new StringBuilder();
// try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
// String line;
// while ((line = br.readLine()) != null) {
// contentBuilder.append(line).append('\n');
// }
// }
// return contentBuilder.toString();
// }
// 

// public void writeToJsonfile() {
// String name = patientName.getText();
// System.out.println(name);
// String id = patientId.getText();
// JSONArray jsonArray = new JSONArray();
//
// // Create the first data set as a JSON object
// JSONObject data1 = new JSONObject();
// data1.put("Name", name);
// data1.put("Id", id);
//
//// // Create the second data set as another JSON object
//// JSONObject data2 = new JSONObject();
//// data2.put("nationalId", "123457");
//// data2.put("occupation", "Doctor");
//
// // Add the data sets to the JSON array
// jsonArray.put(data1);
// // jsonArray.put(data2);
//
// // Specify the file path where you want to write the JSON data
// String filePath = "src/test/resources/data/patient.json";
//
// try (FileWriter fileWriter = new FileWriter(filePath)) {
// // Write the JSON array to the file
// fileWriter.write(jsonArray.toString(4)); // The '4' is for indentation (optional)
// System.out.println("Data has been written to " + filePath);
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
	
	 // Verifies the UI elements on the Vitals Summary screen.
	 
	public void verifyVitalslSummaryUi() throws InterruptedException {
// Check if the following UI elements are displayed on the Vitals Summary screen
		isDisplayed(vitalsDetailsLabel, "Vitals Details Label is Displayed");
		isDisplayed(vitalsTitle, "Vitals Title is Displayed");
		isDisplayed(heightTitle, "Height Title is Displayed");
		isDisplayed(heightValue, "Height Value is Displayed");
		isDisplayed(weightTitle, "Weight Title is Displayed");
		isDisplayed(weightValue, "Weight Value is Displayed");
		isDisplayed(bmiTitle, "BMI Title is Displayed");
		isDisplayed(bmiValue, "BMI Value is Displayed");
		isDisplayed(pulseTitle, "Pulse Title is Displayed");
		isDisplayed(pulseValue, "Pulse Value is Displayed");
		isDisplayed(bpTitle, "BP Title is Displayed");
		isDisplayed(bpValue, "BP Value is Displayed");
		isDisplayed(pulseTitle, "Pulse Title is Displayed");
		isDisplayed(pulseValue, "Pulse Value is Displayed");
		isDisplayed(tempTitle, "Temp Title is Displayed");
		isDisplayed(tempValue, "Temp Value is Displayed");
		isDisplayed(spo2Title, "SpO2 Title is Displayed");
		isDisplayed(spo2Value, "SpO2 Value is Displayed");
		isDisplayed(rrTitle, "RR Title is Displayed");
		isDisplayed(rrValue, "RR Value is Displayed");
		isDisplayed(backButton, "Back Button is Displayed");
		isDisplayed(confirmButton, "Confirm Button is Displayed");


	}

	
//	  Verifies the BMI status for a patient with normal weight based on the
//	  provided height and weight. Compares the actual status with the expected
//	  status and prints the result of the verification.
	
	public void verifyStatusForNormalWeightPatient(String height, String weight, String expectedStatus) throws InterruptedException {
// Enter the provided height and weight
		sendKeys(heightEditText, height);
		sendKeys(weightEditText, weight);
// Get the actual status
		String actualStatus = bMIStatus.getText();

// Compare the actual status with the expected status
		if (actualStatus.equals(expectedStatus)) {
			ExtentReport.getTest().log(Status.INFO, "Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
			System.out.println(
					"Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

			System.out.println(
					"Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
		}

	}

	// Verifies the status for an underweight patient based on the provided height and weight.
	public void verifyStatusForUnderWeightPatient(String height, String weight, String expectedStatus) throws InterruptedException {
	    // Enter the provided height into the height field
	    sendKeys(heightEditText, height);

	    // Enter the provided weight into the weight field
	    sendKeys(weightEditText, weight);

	    // Get the actual BMI status from the UI
	    String actualStatus = bMIStatus.getText();

	    // Compare the actual status with the expected status
	    if (actualStatus.equals(expectedStatus)) {
	        // Print a message indicating that the verification passed
			ExtentReport.getTest().log(Status.INFO, "Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

	        System.out.println(
	                "Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
	    } else {
	        // Print a message indicating that the verification failed
			ExtentReport.getTest().log(Status.INFO, "Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

	        System.out.println(
	                "Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
	    }
	}


	// Verifies the BMI status for a patient with severe obesity based on provided height and weight.
	public void verifyStatusForSevereObesityWeightpatient(String height, String weight, String expectedStatus) throws InterruptedException {
	    // Enter the provided height into the height field
	    sendKeys(heightEditText, height,"Entered Height");

	    // Enter the provided weight into the weight field
	    sendKeys(weightEditText, weight,"Entered Weight");

	    // Get the actual BMI status from the UI
	    String actualStatus = bMIStatus.getText();

	    // Compare the actual status with the expected status
	    if (actualStatus.equals(expectedStatus)) {
	        // Print a message indicating that the verification passed
			ExtentReport.getTest().log(Status.INFO, "Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

	        System.out.println(
	                "Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
	    } else {
	        // Print a message indicating that the verification failed
			ExtentReport.getTest().log(Status.INFO, "Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

	        System.out.println(
	                "Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
	    }
	}


	// Verifies the BMI status for a patient with moderate obesity based on provided height and weight.
	public void verifyStatusModerateObesityWeightPatient(String height, String weight, String expectedStatus) throws InterruptedException {
	    // Enter the provided height into the height field
	    sendKeys(heightEditText, height,"Entered Height");

	    // Enter the provided weight into the weight field
	    sendKeys(weightEditText, weight,"Entered Weight");

	    // Get the actual BMI status from the UI
	    String actualStatus = bMIStatus.getText();

	    // Compare the actual status with the expected status
	    if (actualStatus.equals(expectedStatus)) {
	        // Print a message indicating that the verification passed
			ExtentReport.getTest().log(Status.INFO, "Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

	        System.out.println(
	                "Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
	    } else {
	        // Print a message indicating that the verification failed
			ExtentReport.getTest().log(Status.INFO, "Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

	        System.out.println(
	                "Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
	    }
	}


	// Verifies the BMI status for a patient with very severe obesity based on provided height and weight.
	public void verifyStatusForVerySevereObesityWeightPatient(String height, String weight, String expectedStatus) throws InterruptedException {
	    // Enter the provided height into the height field
	    sendKeys(heightEditText, height,"Entered Height");

	    // Enter the provided weight into the weight field
	    sendKeys(weightEditText, weight,"Entered Weight");

	    // Get the actual BMI status from the UI
	    String actualStatus = bMIStatus.getText();

	    // Compare the actual status with the expected status
	    if (actualStatus.equals(expectedStatus)) {
	        // Print a message indicating that the verification passed
			ExtentReport.getTest().log(Status.INFO, "Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

	        System.out.println(
	                "Verification Passed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
	    } else {
	        // Print a message indicating that the verification failed
			ExtentReport.getTest().log(Status.INFO, "Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);

	        System.out.println(
	                "Verification Failed! Expected Status: " + expectedStatus + ", Actual Status: " + actualStatus);
	    }
	}


	// Enters the vital details of a patient, including height, weight, blood pressure, pulse, temperature, spo2, and respiratory rate.
	public void enterPatientVitalsDetails(String height, String weight, String bpSystolicValue, String bpDiastolicValue,
	                                      String pulse, String temperature, String spo2, String respiratoryRate) throws InterruptedException {
		// Enter the provided height into the height field
		sendKeys(heightEditText, height);

		// Enter the provided weight into the weight field
		sendKeys(weightEditText, weight);

		// Enter the provided systolic blood pressure value
		sendKeys(bpSystolicEditText, bpSystolicValue);

		// Enter the provided diastolic blood pressure value
		sendKeys(bpDiastolicEditText, bpDiastolicValue);

		// Enter the provided pulse rate
		sendKeys(pulseEditText, pulse);

		// Enter the provided body temperature
		sendKeys(temperatureEditText, temperature);

		// Scroll to view the respiratory rate field
		scrollToViewRespiratoryRate();

		// Enter the provided spo2 (oxygen saturation) level
		sendKeys(spo2EditText, spo2);

		// Enter the provided respiratory rate
		sendKeys(respiratoryRateEditText, respiratoryRate);

	}


	public void verifyPatientVitalsDetails(String height, String weight, String bpVal, String pulse, String temperature,
			String spo2, String respiratoryRate, String bmiValue) {
		String h = heightValue.getText();
		if (h.equals(height)) {
			ExtentReport.getTest().log(Status.INFO, "Verification passed");
			System.out.println("Verification passed");
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification failed ");

			System.out.println("Verification failed");
		}
		String w = weightValue.getText();
		if (w.equals(weight)) {
			ExtentReport.getTest().log(Status.INFO, "Verification passed");
			System.out.println("Verification passed");
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification failed ");

			System.out.println("Verification failed");
		}
		String bp = bpValue.getText();
		if (bp.equals(bpVal)) {
			ExtentReport.getTest().log(Status.INFO, "Verification passed");
			System.out.println("Verification passed");
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification failed ");

			System.out.println("Verification failed");
		}
		String p = pulseValue.getText();
		if (p.equals(pulse)) {
			ExtentReport.getTest().log(Status.INFO, "Verification passed");
			System.out.println("Verification passed");
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification failed ");

			System.out.println("Verification failed");
		}
		String t = tempValue.getText();
		if (t.equals(temperature)) {
			ExtentReport.getTest().log(Status.INFO, "Verification passed");
			System.out.println("Verification passed");
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification failed ");

			System.out.println("Verification failed");
		}
		String sp = spo2Value.getText();
		if (sp.equals(spo2)) {
			ExtentReport.getTest().log(Status.INFO, "Verification passed");
			System.out.println("Verification passed");
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification failed ");

			System.out.println("Verification failed");
		}
		String rr = rrValue.getText();
		if (rr.equals(respiratoryRate)) {
			ExtentReport.getTest().log(Status.INFO, "Verification passed");
			System.out.println("Verification passed");
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification failed ");

			System.out.println("Verification failed");
		}
		String bmi = bMIvalue.getText();
		if (bmi.equals(bmiValue)) {
			ExtentReport.getTest().log(Status.INFO, "Verification passed");
			System.out.println("Verification passed");
		} else {
			ExtentReport.getTest().log(Status.INFO, "Verification failed ");
			System.out.println("Verification failed");
		}
	}

	public void checkVitalsUpdateScreenDetailsEditable(String height, String weight, String bpSystolicValue,
			String bpDiastolicValue, String pulse, String temperature, String spo2, String respiratoryRate) {
		try {
			clear(heightEditText);
			sendKeys(heightEditText, height,"Entered Height");
			clear(weightEditText);
			sendKeys(weightEditText, weight,"Enterrd Weight");
			clear(bpSystolicEditText);
			sendKeys(bpSystolicEditText, bpSystolicValue,"Entered BPSystolicValue ");
			clear(bpDiastolicEditText);
			sendKeys(bpDiastolicEditText, bpDiastolicValue,"Entered BPDiastolicValue");
			clear(pulseValue);
			sendKeys(pulseEditText, pulse,"Entered Pulse");
			clear(temperatureEditText);
			sendKeys(temperatureEditText, temperature,"Entered Temperature");
			clear(spo2EditText);
			sendKeys(spo2EditText, spo2,"Entered spo2");
			clear(respiratoryRateEditText);
			sendKeys(respiratoryRateEditText, respiratoryRate,"Entered  RespiratoryRate");
		} catch (Exception e) {
// Print any exception details if an error occurs
			e.printStackTrace();
		}
	}

	public void verifyVisitReasonScreenIsDisplayed() throws InterruptedException {
		isDisplayed(visitReasonTitle);
	}

	public void clickOnConfirmButton() {
		click(confirmButton);

	}

// 2/4visit reason
	public void selectVisitReasonAsAbdominalPain() {
		click(visitReasonAbdominalPain);
	}

	public void verifyVisitReasonAbdominalPainIsSelected() throws InterruptedException {
		isSelected(visitReasonAbdominalPain);
		isDisplayed(selectedReason);
	}

// public void verifyReasonfilledWithBlueColor() {
// visitReasonAbdominalPain.getAttribute(getDeviceName())
// }
	public void removeSelectedReason() {
		click(remove);
	}

	public boolean verifySelectedReasonIsRemoved() {
		try {
			return selectedReason.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void selectAllReasons() throws InterruptedException {
		click(visitReasonAbdominalPain);
		scrollToDiarreha();

		click(diarrhea);
		
		scrollToFever();
		
		click(fever);
		
		scrollToHyperTension();
		
		click(hypertension);
	}

	public void verifyAllSelectedReasonsAreDisplayed() {
		click(visitReasonAbdominalPain);
		scrollToDiarreha();

		click(diarrhea);
		
		scrollToFever();
		
		click(fever);
		
		scrollToHyperTension();
		
		click(hypertension);
		scrollToWhatIsTheReasonForVisit();
		   // Define the predefined list of reasons
        List<String> predefinedReasons = Arrays.asList("Abdominal Pain", "Diarrhea",  "Fever","Hypertension");

      
        // Verify whether all predefined reasons are reflected under Selected Reason
        boolean verificationPassed = true;

        for (String predefinedReason : predefinedReasons) {
            // Check if the reason is reflected under Selected Reason
            boolean isReflected = false;

            for (WebElement selectedReasonElement : allSelectedReasons) {
                if (selectedReasonElement.getText().equals(predefinedReason)) {
                    isReflected = true;
                    break;
                }
            }

            if (!isReflected) {
                System.out.println("Verification Failed: Reason '" + predefinedReason + "' is not reflected under Selected Reason.");
                verificationPassed = false;
            }
        }

        if (verificationPassed) {
            System.out.println("Verification Passed: All predefined reasons are reflected under Selected Reason.");
        }

        
    
		
	}

	public void clickOnBackButton() {
		click(visitReasononBackButton);
	}

	public void verifyConfirmVisitPopup() throws InterruptedException {
		isDisplayed(confirmVisitPopupTitle);
		isDisplayed(confirmVisitPopupSubTitle);
		isDisplayed(reason);
		isDisplayed(yesButton);
		isDisplayed(noButton);

	}

	public void clickOnNoButton() {
		click(noButton);
	}

	public void clickOnYesButton() {
		click(yesButton);
	}
// 2/4 Visit reason : Abdominal Pain

	public void verifyVisitReasonAbdominalPainTitleIsDisplayed() throws InterruptedException {
		isDisplayed(visitCreationTitle);
	}

	public void verifyOneOfTwelveQuestionIsDisplayed() throws InterruptedException {
		isDisplayed(OneofElevenQuestion);
	}

	public void verifyAbdominalFirstQuestionIsDisplayed() throws InterruptedException {
		isDisplayed(addominalPainReasonQuestion1);
	}

	public void verifyAllOptionsAreDisplayed() {
		List<WebElement> options = Arrays.asList(upperRightHypochondrium, upperEpigastric, upperLeftHypochondrium,
				middleRightLumbar, middleUmbilical, middleLeftLumbar, lowerRightIliacFossa, lowerHypogastric,
				lowerLeftIliacFossa, allOver);

		for (WebElement option : options) {

			if (!option.isDisplayed()) {
				throw new AssertionError("Element is not displayed: " + option.getText());
			}
		}
	}

	public void clickUpperRightHypochondrium() {
		click(upperRightHypochondrium);

	}

	public void clickAllOver() {
		click(allOver);

	}

	public void clickOnGroin() {
		click(groin);
	}

	public void clickOnSubmitButton() {
		click(submitButton);
	}

	public void verifyTwoOfTwelveQuestionIsDisplayed() throws InterruptedException {
		isDisplayed(twoOfElevenQuestion);
	}

	public void threeOfTwelveQuestioNotPresent() {

		try {
			if (isDisplayed(threeofTwelveQuestion)) {
				System.out.println("User can go to the 3rd question without selecting option/answer for 2nd question");
			} else {
				System.out.println(
						"User is not able to go to 3rd question without selecting option/answer for 2nd question");
			}
		} catch (Exception e) {
			// Handle or log any exception that occurs during the execution of the test
			// method
			System.out.println("An exception occurred: " + e.getMessage());
		}
	}

	public void selectPainRadiatesOption() {
		click(painRadiates);
	}

	public void scrollToEndAction() throws InterruptedException {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("mobile: scrollGesture",
					ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "up", "percent",
							3.0

					));
			Thread.sleep(1000);
		} while (canScrollMore);
	}

	public void verifyPainRadiatesToOptionsAreDisplayed() throws InterruptedException {
		isDisplayed(painRadiatesTo);
		isDisplayed(upperRightHypochondrium);
		isDisplayed(upperEpigastric);
		isDisplayed(upperLeftHypochondrium);
		isDisplayed(middleRightLumbar);
		isDisplayed(middleUmbilical);
		isDisplayed(middleLeftLumbar);
		isDisplayed(lowerRightIliacFossa);
		isDisplayed(lowerHypogastric);
		isDisplayed(lowerLeftIliacFossa);
		isDisplayed(rightShoulder);
		isDisplayed(rightScapula);
		isDisplayed(groin);

		isDisplayed(sacralRegion);
		isDisplayed(rightFlank);
		isDisplayed(leftFlank);
		isDisplayed(rightGroin);

		isDisplayed(leftGroin);
		isDisplayed(chest);

	}

	public void verifySubmitButtonIsDisplayed() throws InterruptedException {
		isDisplayed(painRadiatesSubmitButton);
	}

	public void clickOnPainRadiatesSubmitButton() {
		click(painRadiatesSubmitButton);
	}

	public void verifyThreeOfTweleveQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(threeOfElevenQuestions);
	}

	public void clickOnNumberSpinner() {
		click(numberSpinner);
	}

	public void selectTwo() {
		click(two);
	}

	public void verifyNumberTwoIsSelected() throws InterruptedException {
		isDisplayed(two);
	}

	public void clickOnDurationSpinner() {
		click(durationSpinner);
	}

	public void selectDays() {
		click(days);
	}

	public void verifyDaysIsSelected() throws InterruptedException {
		isDisplayed(days);
	}

	public void clickOnThreeOfTwelveQuestionSubmitButton() {
		click(threeOfElevenQuestionSubmitButton);
	}

	public void verifyFourOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(fourOfElevenQuestion);
	}

	public void clickOnGradualOption() {
		click(gradualOption);
	}

	public void verifyFiveOfTwelveQuestonIsDisplayed() throws InterruptedException {
		isDisplayed(fiveOfTwelveQuestions);
	}

	public void clickOnOtherOptionOfFourOfTwelveQuestions() {
		click(othersOptionOfFourOfTwelveQuestions);
	}

	public void verifyDescribeBlockIsDisplayedWithSubmitButton() throws InterruptedException {
		isDisplayed(describeTextField);
		isDisplayed(submitButtonOfFourOfTwelveQuestion);
	}

	public void clickOnFourOfTwelveSubmitButton() {
		click(submitButtonOfFourOfTwelveQuestion);
	}

	
	public void enterValueInDescribeTextBox(String txt) throws InterruptedException {
        int attempts = 0;
        while (attempts < 2) {
            try {
                // Try interacting with the element
                sendKeys(describeTextField, txt);
                // If successful, break out of the loop
                break;
            } catch (StaleElementReferenceException e) {
                // If StaleElementReferenceException occurs, catch it and try again
                attempts++;
            }
        }
    }

	public void clickOnSubmitButtonOfFourOfTwelveQuestion() {
		click(submitButtonOfFourOfTwelveQuestion);
	}

	public void selectNightOption() {
		click(night);
	}

	public void verifySixOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(sixOfTwelveQuestions);
	}

	public void selectOtherOptionOfFiveOfTwelveQuestions() {
		click(othersOptionOfFiveOfTwelveQuestions);
	}

	public void clickOnFiveOfTwelveQuestionsSubmitButton() {
		click(fiveOfTwelveQuestionsSubmitButton);
	}

	public void clickOnConstantOptionOfSixOfTweleveQuestions() {
		click(constantOptionOfSixOfTwelveQuetions);
	}

	public void clickOnSubmitButtonOfSixOfTwelveQusetions() {
		click(sixOfTwelveQuestionsSubmitButton);
	}

	public void verifySevenOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(sevenOfTwelveQuestions);
	}

	public void selectOtherOptionOfSixOfTwelveQuestions() {
		click(othersOptionOfSixOfTwelveQuestions);
	}

	public void selectMildOneThreeOption() {
		click(mildOneThreeOption);
	}

	public void verifyEightOfTwelvequsetionsIsDisplayed() throws InterruptedException {
		isDisplayed(eightOfTwelveQusetions);
	}

// 8 of 12 questions
	public void selectCoughingOption() {
		click(coughingOption);
	}

	public void clickOnEightOfTwelveSubmitButton() {
		click(eightOfTwelveSubmitButton);
	}

	public void verifyNineOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(nineOfTwelveQuestions);
	}

	public void clickOnOtherOptonOfEightOfTwelveQusetions() {
		click(otherOptionOfEightOfTwelveQuestions);
	}

// 9 of 12
	public void selectfoodOption() {
		click(foodOption);
	}

	public void selectLeaningForward() {
		click(leaningForwardOption);
	}

	public void clickOnSubmitButtonOfNineOfTwelveQuestions() {
		click(nineOfTwelveQusetionsSubmitButton);
	}

// 10 of 12
	public void verifyTenOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(tenOfTwelveQusetions);
	}

// Click on the option indicating that menstruation has not started
	public void selectHasNotStartedMenstruationOption() {
		click(hasNotStartedmenstruationOption);
	}

//Click on the option Ismenstruating
	public void selectIsMenstruatingOption() {
		click(isMenstruatingOption);
	}

//Verify that age at onset option is displayed
	public void verifyAgeAtOnSetIsDisplayed() throws InterruptedException {
		isDisplayed(ageAtOnset);
	}

// enter age in ageonset Input feild
	public void enterAgeInAgeOnSetFeild(String txt) throws InterruptedException {
		sendKeys(ageAtOnSetInputFiled, txt);
	}

//Click On submit button
	public void clickOnSubmitButtonOfTenOftwelveQuestions() {
		click(ageOnSetInputFeildSubmitButton);
	}

//verify calender is displayed
	public void verifyCalenderIsDisplayed() throws InterruptedException {
		isDisplayed(calender);
	}

//verify that LastMenstruationPeriod Is Displayed
	public void verifyLastMenstruationPeriodIsDisplayed() throws InterruptedException {
		isDisplayed(lastMenstruationPeriod);
	}

//selects a date
	public void selectmenstruationDate() {
		click(menstruationDate);
	}

	public void verifySelectedMenstruationDateIsDisplayed(String expectedMenstruationDate) {
		String displayedMenstruationDate = menstruationDate.getText();
		{
			if (displayedMenstruationDate.equals(expectedMenstruationDate)) {
// The selected menstruation date is displayed and matches the expected value
				System.out.println("Selected menstruation date is displayed and matches the expected value.");
			} else {
// The selected menstruation date is either not displayed or doesn't match the expected value
				System.out.println("Selected menstruation date is not displayed or does not match the expected value.");
			}
		}
	}

// Click on the 'Other' option
	public void selectOtherOption() {
		click(otherOption);
	}

//11 of 12 
// Verify that the eleventh question out of twelve is displayed
	public void verifyElevenOfTwelveQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(elevenOfTwelveQuestions);
	}

//click On yes describe
	public void selectYesDescribeOption() {
		click(yesDescribeOption);
	}

// Click on the 'None' option element to select it
	public void selectNoneOption() {
		click(noneOption);
	}

//12 of 12 
// Check if the element representing twelve questions is displayed
	public void verifyTwelveOfTwelveQusetionsIsDisplayed() throws InterruptedException {
		isDisplayed(twelveOfTweleveQusetions);
	}

// Click on the 'Skip' button for twelve questions
	public void clickOnTwelevOfTwelveSkipButton() {
		click(twelveOfTwelveSkipButton);
	}

	// Click On the submit button for twelve questions
	public void clickOnTwelevOfTwelveSubmitButton() {
		click(twelveOfTwelveSubmitButton);
	}

// Check if the element representing the title for associated symptoms is displayed
	public void verifyAssociatedSymptomsTitleIsDisplayed() throws InterruptedException {
		isDisplayed(associatedSymptomsTitle);
	}

// Check if the question doYouHaveFollowingSymptom is displayed
	public void verifyDoYouHaveFollowingSymptomIsDisplayed() throws InterruptedException {
		isDisplayed(doYouHaveFollowingSymptom);
	}

//click on change in apetite yes button
	public void clickOnChangeInApetiteYesButton() {
		click(changeInApetiteYesButton);
	}

	public void verifyWhatIsTheChangeIsDisplayed() throws InterruptedException {
		isDisplayed(whatIsTheChange);
	}

	public void verifyInreasedOptionIsDisplayed() throws InterruptedException {
		isDisplayed(increased);
	}

	public void verifyDecreasedOptionIsDisplayed() throws InterruptedException {
		isDisplayed(decreased);
	}

	public void verifyOneOfOneQuestionsIsDisplayed() throws InterruptedException {
		isDisplayed(oneOfOneQuestions);
	}

	public WebElement scrollToCorrespondingAddress2() {

		return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).scrollIntoView("
				+ "new UiSelector().description(\"Patient Details Screen Address Details 'Corresponding Address 2' Value TextView\"));"));

	}

//Method to verify personal details
	public boolean verifyPersonalDetails(String expectedGender, String expectedDOB, String expectedAge) throws InterruptedException {
		return isDisplayed(personalDetailsHeader)
				&& getText(nameValue, "Name: ").equals(getText(patientName, "PatientName:"))
				&& getText(genderValue, "Gender: ").equals(expectedGender)
				&& getText(dobValue, "DOB: ").equals(expectedDOB) && getText(ageValue, "Age: ").equals(expectedAge);
	}

// Method to verify address details
	public boolean verifyAddressDetails(String expectedPostalCode, String expectedCountry, String expectedState,
			String expectedDistrict, String expectedVillage) throws InterruptedException {
		return isDisplayed(addressDetailsHeader) && getText(postalCodeValue, "Postal Code: ").equals(expectedPostalCode)
				&& getText(countryValue, "Country: ").equals(expectedCountry)
				&& getText(stateValue, "State: ").equals(expectedState)
				&& getText(districtValue, "District: ").equals(expectedDistrict)
				&& getText(villageValue, "Village: ").equals(expectedVillage);
	}

// Method to verify other details
	public boolean verifyOtherDetails(String expectedNationalID, String expectedOccupation, String expectedCaste,
			String expectedEducation, String expectedEconomic) throws InterruptedException {
		return isDisplayed(otherDetailsHeader) && getText(nationalIdValue, "nationalId:").equals(expectedNationalID)
				&& getText(occupationValue, "Occupation: ").equals(expectedOccupation)
				&& getText(casteValue, "Caste: ").equals(expectedCaste)
				&& getText(educationValue, "Education: ").equals(expectedEducation)
				&& getText(economicValue, "Economic: ").equals(expectedEconomic);
	}

//Associated Sysmptoms
	// Clicks on the "Yes" button for the associated symptom seven.
	public void clickOnSevenAssociatedSymptomYesButton() {
		click(sevenYesButton);
	}
	// Clicks on the "Yes" button for the associated symptom eight.

	public void clickOnEightAssociatedSymptomYesButton() {
		click(sevenYesButton);
	}

	// Clicks on the "Yes" button for the associated symptom nine.
	public void clickOnNineAssociatedSymptomYesButton() {
		click(nineYesButton);
	}

	// Clicks on the "Yes" button for the associated symptom sixteen.
	public void clickOnSixteenAssociatedSymptomYesButton() {
		click(sevenYesButton);
	}

	// Verifies that the text area for describing symptoms is displayed.
	public void verifyDescribeTextAreaIsDisplayed() throws InterruptedException {
		isDisplayed(describeTextArea);
	}

	// Scrolls to the eighth associated symptom
	public WebElement scrollToEighthAssociatedSymptom() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"//android.widget.TextView[@text='15. Nausea']\")):"));
	}

	// Scrolls to the sixteen associated symptom
	public WebElement scrollToSixteenAssociatedSymptom() {
//		return getDriver().findElement(
//				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
//						+ "new UiSelector().description(\"//android.widget.TextView[@text='16. Other [describe]']\"));"));
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"16. Other [describe]\").instance(0))"));

	}

	public void enterDataInDescribeTextFiled(String txt) throws InterruptedException {
		sendKeys(describeTextArea, txt);
	}

	public void scrollToAssociatedSymptomSubmiButton() {
//		return getDriver().findElement(
//				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
//+ "new UiSelector().description(\"Associated Symptoms Questionnaire Main View Submit Button\")):"));

	}

	public void clickOnAssociatedSymptomMainSubmitButton() {
		click(associatedSymptomSubmitButton);
	}

	
	// visit reason summary screen
	public void verifyVisitReasonSummaryScreenIsDisplayed() throws InterruptedException {
		isDisplayed(visitReasonSummaryTitle);
	}

	public void clickOnAbdominalPainChangeIcon() {
		click(abdominalPainChangeButton);
	}

	public WebElement scrollToEightOfTwelveQuestions() {
		return getDriver().findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"//android.widget.TextView[@text='8 of 12 questions']\"));"));

	}

	/**
	 * Verifies if the selected food option is displayed on the screen.
	 * @throws InterruptedException 
	 */
	public void verifySelectedFoodOptionIsDisplayed() throws InterruptedException {
		isDisplayed(food);
	}

	/**
	 * Verifies if vomiting information is displayed on the screen.
	 * @throws InterruptedException 
	 */
	public void verifyVomitingIsDisplayed() throws InterruptedException {
		isDisplayed(vomiting);
	}

	/**
	 * Clicks on the back button on the Visit Reason Summary screen.
	 */
	public void clickOnBackButtonOnVisitReasonSummaryScreen() {
		click(VisitReasonSummaryScreenBackButton);
	}

	/**
	 * Clicks on the confirm button on the Visit Reason Summary screen.
	 */
	public void clickOnConfirmButtonOnVisitReasonSummaryScreen() {
		click(VisitReasonSummaryScreenConfirmButton);
	}

	/**
	 * Verifies if the "Please Wash or Sanitize Your Hands" popup is displayed.
	 * Also, checks for the presence of the "Okay" button.
	 * @throws InterruptedException 
	 */
	public void verifyPleaseWashOrSanitizeYourHandsPopupIsDisplayed() throws InterruptedException {
		isDisplayed(pleaseWashOrSanitizeYourHands);
		isDisplayed(okayButton);
	}

	/**
	 * Clicks on the "Okay" button.
	 */
	public void clickOnOkayButton() {
		click(okayButton);
	}

	/**
	 * Clicks on the first "Yes" button.
	 */
	public void clickOnFirstYesButton() {
		click(firstYesButton);
	}

	/**
	 * Enters patient vitals and completes the visit by navigating through various
	 * screens.
	 *
	 * @param height           The height of the patient.
	 * @param weight           The weight of the patient.
	 * @param bpSystolicValue  The systolic blood pressure value of the patient.
	 * @param bpDiastolicValue The diastolic blood pressure value of the patient.
	 * @param pulse            The pulse rate of the patient.
	 * @param temperature      The body temperature of the patient.
	 * @param spo2             The oxygen saturation level of the patient.
	 * @param respiratoryRate  The respiratory rate of the patient.
	 * @throws InterruptedException 
	 */
	public void enterVitalsAndCompleteVisit(String height, String weight, String bpSystolicValue,
			String bpDiastolicValue, String pulse, String temperature, String spo2, String respiratoryRate) throws InterruptedException {
		// Enter patient vitals details
		enterPatientVitalsDetails(height, weight, bpSystolicValue, bpDiastolicValue, pulse, temperature, spo2,
				respiratoryRate);

		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();

		// Select visit reason as abdominal pain
		selectVisitReasonAsAbdominalPain();

		// Navigate to the next screen after selecting visit reason
		clickOnNextButtonOfVisitReason();

		// Confirm the visit and proceed
		verifyConfirmVisitPopup();
		clickOnYesButton();

		// Answer questions related to abdominal pain
		verifyAbdominalFirstQuestionIsDisplayed();
		verifyAllOptionsAreDisplayed();
		clickUpperRightHypochondrium();
		clickOnSubmitButton();

		// Answer additional questions related to abdominal pain
		verifyTwoOfTwelveQuestionIsDisplayed();
		selectPainRadiatesOption();
		clickOnGroin();
		clickOnPainRadiatesSubmitButton();

		// Answer more questions related to abdominal pain
		verifyThreeOfTweleveQuestionsIsDisplayed();
		clickOnNumberSpinner();
		selectTwo();
		verifyNumberTwoIsSelected();
		clickOnDurationSpinner();
		selectDays();
		verifyDaysIsSelected();
		clickOnThreeOfTwelveQuestionSubmitButton();

		// Answer further questions related to abdominal pain
		verifyFourOfTwelveQuestionsIsDisplayed();
		clickOnGradualOption();

		// Answer more questions related to abdominal pain
		verifyFiveOfTwelveQuestonIsDisplayed();
		selectNightOption();

		// Answer additional questions related to abdominal pain
		verifySixOfTwelveQuestionsIsDisplayed();
		clickOnConstantOptionOfSixOfTweleveQuestions();
		clickOnSubmitButtonOfSixOfTwelveQusetions();

		// Answer more questions related to abdominal pain
		verifySevenOfTwelveQuestionsIsDisplayed();
		selectMildOneThreeOption();

		// Answer additional questions related to abdominal pain
		verifyEightOfTwelvequsetionsIsDisplayed();
		selectCoughingOption();
		clickOnSubmitButton();

		// Answer more questions related to abdominal pain
		verifyNineOfTwelveQuestionsIsDisplayed();
		selectLeaningForward();
		clickOnSubmitButtonOfNineOfTwelveQuestions();

		// Answer remaining questions related to abdominal pain
		verifyTenOfTwelveQuestionsIsDisplayed();
		selectHasNotStartedMenstruationOption();
		verifyElevenOfTwelveQuestionsIsDisplayed();
		selectNoneOption();
		verifyTwelveOfTwelveQusetionsIsDisplayed();

		// Skip the last question
		clickOnTwelevOfTwelveSkipButton();
	}

	public void enterVitalsDetailsAndCompleteTenQuestions(String height, String weight, String bpSystolicValue,
			String bpDiastolicValue, String pulse, String temperature, String spo2, String respiratoryRate) throws InterruptedException {
		// Enter patient vitals details
		enterPatientVitalsDetails(height, weight, bpSystolicValue, bpDiastolicValue, pulse, temperature, spo2,
				respiratoryRate);

		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();

		// Select visit reason as abdominal pain
		selectVisitReasonAsAbdominalPain();

		// Navigate to the next screen after selecting visit reason
		clickOnNextButtonOfVisitReason();

		// Confirm the visit and proceed
		verifyConfirmVisitPopup();
		clickOnYesButton();

		// Answer questions related to abdominal pain
		verifyAbdominalFirstQuestionIsDisplayed();
		verifyAllOptionsAreDisplayed();
		clickUpperRightHypochondrium();
		clickOnSubmitButton();

		// Answer additional questions related to abdominal pain
		verifyTwoOfTwelveQuestionIsDisplayed();
		selectPainRadiatesOption();
		clickOnGroin();
		clickOnPainRadiatesSubmitButton();

		// Answer more questions related to abdominal pain
		verifyThreeOfTweleveQuestionsIsDisplayed();
		clickOnNumberSpinner();
		selectTwo();
		verifyNumberTwoIsSelected();
		clickOnDurationSpinner();
		selectDays();
		verifyDaysIsSelected();
		clickOnThreeOfTwelveQuestionSubmitButton();

		// Answer further questions related to abdominal pain
		verifyFourOfTwelveQuestionsIsDisplayed();
		clickOnGradualOption();

		// Answer more questions related to abdominal pain
		verifyFiveOfTwelveQuestonIsDisplayed();
		selectNightOption();

		// Answer additional questions related to abdominal pain
		verifySixOfTwelveQuestionsIsDisplayed();
		clickOnConstantOptionOfSixOfTweleveQuestions();
		clickOnSubmitButtonOfSixOfTwelveQusetions();

		// Answer more questions related to abdominal pain
		verifySevenOfTwelveQuestionsIsDisplayed();
		selectMildOneThreeOption();

		// Answer additional questions related to abdominal pain
		verifyEightOfTwelvequsetionsIsDisplayed();
		selectCoughingOption();
		clickOnSubmitButton();

		// Answer more questions related to abdominal pain
		verifyNineOfTwelveQuestionsIsDisplayed();
		selectLeaningForward();
		clickOnSubmitButtonOfNineOfTwelveQuestions();

		// Answer remaining questions related to abdominal pain
		verifyTenOfTwelveQuestionsIsDisplayed();
		selectHasNotStartedMenstruationOption();
		verifyElevenOfTwelveQuestionsIsDisplayed();
	}

	public void verifyUserAllowedToSelectOneOptionInTenOfTwelveQuestion() throws InterruptedException {
		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();

		// Select visit reason as abdominal pain
		selectVisitReasonAsAbdominalPain();

		// Navigate to the next screen after selecting visit reason
		clickOnNextButtonOfVisitReason();

		// Confirm the visit and proceed
		verifyConfirmVisitPopup();
		clickOnYesButton();

		// Answer questions related to abdominal pain
		verifyAbdominalFirstQuestionIsDisplayed();
		verifyAllOptionsAreDisplayed();
		clickUpperRightHypochondrium();
		clickOnSubmitButton();

		// Answer additional questions related to abdominal pain
		verifyTwoOfTwelveQuestionIsDisplayed();
		selectPainRadiatesOption();
		clickOnGroin();
		clickOnPainRadiatesSubmitButton();

		// Answer more questions related to abdominal pain
		verifyThreeOfTweleveQuestionsIsDisplayed();
		clickOnNumberSpinner();
		selectTwo();
		verifyNumberTwoIsSelected();
		clickOnDurationSpinner();
		selectDays();
		verifyDaysIsSelected();
		clickOnThreeOfTwelveQuestionSubmitButton();

		// Answer further questions related to abdominal pain
		verifyFourOfTwelveQuestionsIsDisplayed();
		clickOnGradualOption();

		// Answer more questions related to abdominal pain
		verifyFiveOfTwelveQuestonIsDisplayed();
		selectNightOption();

		// Answer additional questions related to abdominal pain
		verifySixOfTwelveQuestionsIsDisplayed();
		clickOnConstantOptionOfSixOfTweleveQuestions();
		clickOnSubmitButtonOfSixOfTwelveQusetions();

		// Answer more questions related to abdominal pain
		verifySevenOfTwelveQuestionsIsDisplayed();
		selectMildOneThreeOption();

		// Answer additional questions related to abdominal pain
		verifyEightOfTwelvequsetionsIsDisplayed();
		selectCoughingOption();
		clickOnSubmitButton();

		// Answer more questions related to abdominal pain
		verifyNineOfTwelveQuestionsIsDisplayed();
		selectLeaningForward();
		clickOnSubmitButtonOfNineOfTwelveQuestions();

		// Answer remaining questions related to abdominal pain
		verifyTenOfTwelveQuestionsIsDisplayed();
		
		click(hasNotStartedmenstruationOption, "Clicked on hasNotStartedMenstruation");
		isDisplayed(elevenOfTwelveQuestions,"11 of 12 Questions is Displayed");

	}

	public void verifyUserAllowedToSelectOneOptionInElevenOfTwelveQuestion() throws InterruptedException {
		click(yesDescribeOption);
		isDisplayed(describeField);
		click(noneOption);
		isDisplayed(twelveOfTweleveQusetions);

	}

	public void clickSymptomsNoButton() {
		for (int i = 0; i < 4; i++) {
			click(lastNoButton);
		}
	}
	public void checkSymptomsNoButton() {
		for (int i = 0; i < 10; i++) {
			click(lastNoButton);
		}
	}

	public WebElement scrollToAssociatedSymptoms() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Do you have the following symptom(s)?\").instance(0))"));

	}

	public void verifyAssociatedSymptomsSectionOnSummaryPage() throws InterruptedException {
		isDisplayed(associatedSymptomsTitle);

		click(firstYesButton);
		int maxRetries = 2;
		for (int i = 0; i < maxRetries; i++) {
			try {
				for (int j = 0; j < 9; j++) {
					click(lastNoButton);
				}
			} catch (NoSuchElementException | StaleElementReferenceException e) {
				// Log or handle the exception
				e.printStackTrace();
			}
		}
//		for (int i = 0; i < 9; i++) {
//			click(LastNoButton);
//		}

		click(associatedSymptomSubmitButton);
		scrollToAssociatedSymptoms();
		isDisplayed(associatedSymptomChangeButton);
		isDisplayed(patientReports);
		isDisplayed(patientDenies);

	}

	public void selectAllTwentyOptionsForAssociatedSymptoms() throws InterruptedException {
		isDisplayed(associatedSymptomsTitle);

		for (WebElement element : asSymptomNoButton) {
			Thread.sleep(3000);
			element.click();

		}
	}

//method selects all twenty options for associated symptoms
	public void selectValueFromSearchResultsAndVerify() throws InterruptedException {
		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();
		clear(searchReasonTextBox, "Clicked on Search Reason Text Box");
		searchReasonTextBox.click();
		String textToEnter = "ab";
		String adbCommand = String.format("adb shell input text %s", textToEnter);
		executeCommand(adbCommand);
		((PressesKey) getDriver()).pressKey(new KeyEvent(AndroidKey.TAB));
		// click(selectedReasonLabel);
		isDisplayed(selectedReason,"Selected Resaon is Displayed");
		isDisplayed(chipReason,"Chip reason is Displayed");

	}

//Executes a system command and waits for its completion
	public static void executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Verifies the functionality of selecting and confirming a visit reason after entering vitals
	public void verifyAllReasonFuntionality() throws InterruptedException {
		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();
		click(visitReasonAbdominalPain);

		isDisplayed(selectedReason);

	}

//Verifies the functionality of selecting and confirming a specific visit reason in the "All Reasons" section 
	public void verifyAllReasonInAllReasonSection() throws InterruptedException {
		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();
		click(visitReasonAbdominalPain, "Clicked On abdominal pain");
		isDisplayed(selectedReason, "Selected reason is displayed");

	}

//Scroll to the element with the text "Hypertension"
	public WebElement scrollToHyperTension() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Hypertension\").instance(0))"));

	}

	// Scroll to the element with the text "Hypertension"
	public WebElement scrollToDiarreha() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Diarrhea\").instance(0))"));

	}

	// Scroll to the element with the text "Hypertension"
	public WebElement scrollToFever() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Fever\").instance(0))"));

	}
	

	// Scroll to the element with the text "Hypertension"
	public WebElement scrollToWhatIsTheReasonForVisit() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"What is the reason for this visit?\").instance(0))"));

	}
//method to verify 2/4 visit reason page
	public void verifyUiOfVisitReasonPage() throws InterruptedException {
		// Navigate to the next screen after entering vitals
		clickOnFirstVitalsNextButton();

		// Confirm the entered vitals
		clickOnConfirmButton();
		isDisplayed(visitCreatedPatientName, "Patient name is displayed");

		isDisplayed(patientAgeAndGender, "Patient age and gender is displayed");

		isDisplayed(visitReasonTitle, "2/4 visit screen is displayed");
		isDisplayed(progressBar1, "Progress bar 1 is filled");
		isDisplayed(progressBar2, "Progress bar 2  is half filled");
		isDisplayed(whatIsTheReasonForVisit, "What is the reason for visit is displayed");
		isDisplayed(searchReasonTextBox, "Serach reason text box is displayed");
		isDisplayed(selectedReasonLabel, "Selected reason section is displayed");
		isDisplayed(allResaonsection, "All reason section is displayed");
		isDisplayed(visitReasonAbdominalPain, "Abdominal pain is displayed");
		scrollToDiarreha();
		isDisplayed(diarrhea, "Diarreha is displayed");
		scrollToFever();
		isDisplayed(fever, "Fever is displayed");
		scrollToHyperTension();
		isDisplayed(hypertension, "Hypertension is displayed");
		isDisplayed(visitReasonNextButton, "Next button is displayed");

		isDisplayed(visitReasononBackButton, "Back button is displayed");

	}
}
 