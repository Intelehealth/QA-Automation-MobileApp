package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class AppSetupPage extends BaseTest {
	// Elements on the App Setup Page
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='While using the app']")
	    public WebElement whileUsingAppButton;

	    @AndroidFindBy(xpath = "//android.widget.Button[@text='Allow']")
	    public WebElement allowButton;
	    @AndroidFindBy(xpath = "//android.widget.Button[@text='ALLOW']")
	    public WebElement pDAllowButton;
	    @AndroidFindBy(id= "android:id/checkbox")
	    public WebElement toggle;
	    @AndroidFindBy(accessibility  = "Back")
	    public WebElement back;
	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to take pictures and record video?']")
	    public WebElement takePicturesTextView;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to access your contacts?']")
	    public WebElement accessContactsTextView;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to make and manage phone calls?']")
	    public WebElement managePhoneCallsTextView;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to access photos and media on your device?']")
	    public WebElement accessPhotosTextView;
	// Language Selection Elements
	@AndroidFindBy(accessibility = "Splash Screen Bottom Language Panel Save Selected Language Button")
	private WebElement nextButton;
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='English']")
	private WebElement english;
	// Introductory Screen Elements
	@AndroidFindBy(accessibility = "Intro Screen First Title TextView")
	private WebElement whoWeAre;
	@AndroidFindBy(accessibility = "Intro Screen Second Title TextView")
	private WebElement takePatientVisits;
	@AndroidFindBy(accessibility = "Intro Screen Third Title TextView")
	private WebElement providePrescriptions;
	@AndroidFindBy(accessibility = "Intro Screen Skip Screen Button")
	private WebElement skipButton;

	// Ayu Intro Screen Elements

	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Hello TextView")
	private WebElement helloIamAyu;
	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Subtitle TextView")
	private WebElement ayuIntroScreenSubtitle;
	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Accept TnC And PP CheckBox")
	private WebElement checkBox;
	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Setup Button")
	private WebElement setupButton;
	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Accept TnC And PP TextView 2")
	private WebElement termsAndCondition;
	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Accept TnC And PP TextView 4")
	private WebElement privacyPolicy;
	// Terms and Conditions Screen Elements
	@AndroidFindBy(accessibility = "Terms and Conditions Title TextView")
	private WebElement termsAndonditionTitle;
	@AndroidFindBy(accessibility = "Terms and Conditions Content TextView")
	private WebElement termsAndConditionDescription;
	@AndroidFindBy(accessibility = "Terms and Conditions Back Arrow ImageView")
	private WebElement termsAndConditionsBackArrow;
	@AndroidFindBy(accessibility = "Terms and Conditions Decline Button")
	private WebElement termsAndConditionDeclineButton;
	@AndroidFindBy(accessibility = "Terms and Conditions Accept Button")
	private WebElement termsAndConditionAcceptButton;
	// Privacy Policy Screen Elements
	@AndroidFindBy(accessibility = "Privacy Policy Title TextView")
	private WebElement privacyPolicyTitle;

	@AndroidFindBy(accessibility = "Privacy Policy Content TextView")
	private WebElement privacyPolicyContent;

	@AndroidFindBy(accessibility = "Privacy Policy Back Arrow ImageView")
	private WebElement privacyPolicyBackArrow;

	@AndroidFindBy(accessibility = "Privacy Policy Decline Button")
	private WebElement privacyPolicyDeclineButton;
	@AndroidFindBy(accessibility = "Privacy Policy Accept Button")
	private WebElement privacyPolicyAcceptButton;
	@AndroidFindBy(accessibility = "Setup Screen Username Edittext")
	// Setup Screen Elements
	private WebElement username;
	@AndroidFindBy(accessibility = "Setup Screen Password Edittext")
	private WebElement password;
	@AndroidFindBy(accessibility = "Show dropdown menu")
	private WebElement dropdown;
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc='Setup Screen Parent RelativeLayout']/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]")

	private WebElement location;
	@AndroidFindBy(accessibility = "Setup Screen Setup Button")
	private WebElement setupScreeenSetupButton;
	@AndroidFindBy(accessibility = "Custom Toolbar Location Name TextView")
	private WebElement locationName;
	@AndroidFindBy(id = "org.intelehealth.app:id/ivInternetCustomToolbar")
	private WebElement refreshButton;
	
	JSONObject appData;
public  AppSetupPage() throws IOException {
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
	}

//	@AndroidFindBy(accessibility = "")
//	private WebElement 

	// Methods for Page Actions
	
	
	// Select language
	public void selectLanguage() {
		isSelected(english);

	}
	public void handlePermissions() {
	    try {
	        // Assuming these methods are defined in your class
	        if (isDisplayed(pDAllowButton)) {
	            // Click pDAllowButton four times (verify if this is the intended behavior)
	            for (int i = 0; i < 4; i++) {
	                click(pDAllowButton);
	            }
	        } else   {
	        	isDisplayed(whileUsingAppButton);
	            click(whileUsingAppButton);
	            click(allowButton);
	            click(allowButton);
	            click(allowButton);
	        } 
	    } catch (Exception e) {
	        // Handle the exception or print an error message
	        System.err.println("An error occurred while handling permissions: " + e.getMessage());
	        // You can choose to proceed with the next steps here if needed
	    }
	}



	
	

	// Click on Next button
	public void clickOnNextButton() throws InterruptedException {
		click(nextButton);
			//	"Clicked on Next Button");
		
	}

	// Click on Skip button
	public void clickOnSkipButton() throws InterruptedException {
		click(skipButton);
	}

	// Click on CheckBox
	public void clickOnCheckBox() throws InterruptedException {
		click(checkBox);
	}

	
	// Click on Setup button
	public void clickOnSetupButton() throws InterruptedException {
	    click(setupButton);
	}

	// Click on Terms and Conditions Back Arrow
	public void clickOnTermsAndConditionsBackArrow() throws InterruptedException {
	    click(termsAndConditionsBackArrow, "Clicked on Terms and Conditions Back Arrow");
	}

	// Click on Terms and Conditions
	public void clickOnTermsAndCondition() throws InterruptedException {
	    click(termsAndCondition, "Clicked on Terms and Conditions");
	}

	// Click on Privacy Policy
	public void clickOnPrivacyPolicy() throws InterruptedException {
	    click(privacyPolicy, "Clicked on Privacy Policy");
	}

	// Click on Privacy Policy Back Arrow
	public void clickOnPrivacyPolicyBackArrow() throws InterruptedException {
	    click(privacyPolicyBackArrow, "Clicked on Privacy Policy Back Arrow");
	}

	// Verify elements on Introductory Screen
	// Verify elements on Introductory Screen
	public void verifyIntroductoryScreen() throws InterruptedException {
	    isDisplayed(whoWeAre, "Who We Are is displayed on the Introductory Screen");
	    
	    isDisplayed(takePatientVisits, "Take Patient Visits is displayed on the Introductory Screen");
	    isDisplayed(providePrescriptions, "Provide Prescriptions is displayed on the Introductory Screen");
	}


	// Verify Skip button is displayed
	// Verify Skip button is displayed
	public void skipButtonIsDisplayed() throws InterruptedException {
	    isDisplayed(skipButton, "Skip button is displayed");
	}


	
	// Verify elements on Ayu Intro Screen
	public void verifyAyuScreen() throws InterruptedException {
	    isDisplayed(helloIamAyu, "Hello, I am Ayu text is displayed");
	    isDisplayed(ayuIntroScreenSubtitle, "Ayu Intro Screen Subtitle is displayed");
	    isDisplayed(checkBox, "Checkbox is displayed");
	    isDisplayed(setupButton, "Setup Button is displayed");
	}


	
	// Verify elements on Terms and Conditions Screen
	public void verifyTermsAndConditionScreen() throws InterruptedException {
	    isDisplayed(termsAndonditionTitle, "Terms and Condition Title is displayed");
	    isDisplayed(termsAndConditionDescription, "Terms and Condition Description is displayed");
	    isDisplayed(termsAndConditionDeclineButton, "Decline Button is displayed");
	    isDisplayed(termsAndConditionAcceptButton, "Accept Button is displayed");
	}

	// Verify elements on Privacy Policy Screen
	// Verify elements on Privacy Policy Screen
	public void verifyPrivacyPolicyScreen() throws InterruptedException {
		
	    isDisplayed(privacyPolicyTitle, "Privacy Policy Title is displayed");
	    isDisplayed(privacyPolicyContent, "Privacy Policy Content is displayed");
	    isDisplayed(privacyPolicyDeclineButton, "Privacy Policy Decline Button is displayed");
	    isDisplayed(privacyPolicyAcceptButton, "Privacy Policy Accept Button is displayed");
	}

	// Perform login
	// Perform login
	public void login(String un, String pw) throws InterruptedException {
	    // Click on the dropdown to open the menu
	  
	    click(dropdown);
	    // Click on the location element to select a location
	  
	    click(location);
	    // Enter the username into the username field
	  
	    sendKeys(username, un);
	    // Enter the password into the password field
	   
	    sendKeys(password, pw);
	    // Click on the setup screen setup button to complete the login process
	    click(setupScreeenSetupButton);
	    click(toggle);
	    click(back);
	    isDisplayed(locationName);
	    click(refreshButton);
	    Thread.sleep(20000);;
	}


	// Verify Location is displayed
	// Verify if the location name is displayed
	public void locationIsDisplayed() throws InterruptedException {
	    isDisplayed(locationName);
	}

	// Check if the setup language screen is displayed
	public boolean setupLanguageScreenIsDisplayed() throws InterruptedException {
	    return isDisplayed(english, "Setup Language Screen is displayed");
	}

	// Perform a series of actions to complete the setup process
	public void completeSetup() throws InterruptedException {
	    // Click the "Next" button
	    clickOnNextButton();

	    // Click the "Skip" button
	    clickOnSkipButton();

	    // Click the checkbox
	    clickOnCheckBox();

	    // Click the "Setup" button
	    clickOnSetupButton();

	    // Perform login with the provided username and password
	    
	    
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
	  
	}

}
