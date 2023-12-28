package com.intelehealth.pages;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class EditProfilePage extends BaseTest {
	
@AndroidFindBy(accessibility = "Navigation Header Home 'Edit Profile' TextView")
private WebElement editProfile;
@AndroidFindBy(accessibility = "Custom Toolbar Back Arrow ImageView")
private WebElement menu;
@AndroidFindBy(accessibility = "Common Toolbar Title TextView")
private WebElement myProfileTitle;
@AndroidFindBy(accessibility = "My Profile Screen Change Photo TextView")
private WebElement changePhoto;

@AndroidFindBy(xpath = "//android.widget.TextView[@text='Take Photo']")
private WebElement takePhoto;
@AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose from Gallery']")
private WebElement chooseFromGallery;
@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='org.intelehealth.app:id/utils_take_picture']")
private WebElement photoCaptureButton;
@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='org.intelehealth.app:id/camera_switch_iv']")
private WebElement camera;
@AndroidFindBy(accessibility  = "Shutter")
private WebElement shutterButton;
@AndroidFindBy( xpath =  "//android.view.View[@resource-id='com.google.android.apps.photos:id/image']")
private WebElement photos;
@AndroidFindBy( xpath =  "(//android.view.ViewGroup)[2]")
private WebElement image;

@AndroidFindBy(accessibility  = "My Profile Screen Profile Image ImageView")
private WebElement profilePhoto;
@AndroidFindBy(accessibility = "My Profile Screen Change Password Title TextView")
private WebElement changePassword;
@AndroidFindBy(accessibility = "My Profile Screen FingerPrint Lock Title TextView")
private WebElement fingerPrintLock;
@AndroidFindBy(accessibility = "My Profile Screen Username Heading TextView")
private WebElement userNameLabel;
@AndroidFindBy(accessibility = "My Profile Screen Username EditText")
private WebElement userNameTextField;

@AndroidFindBy(accessibility = "My Profile Screen First Name Heading TextView")
private WebElement firstNameLabel;
@AndroidFindBy(accessibility = "My Profile Screen First Name EditText")
private WebElement firstNameTextField;
@AndroidFindBy(accessibility = "My Profile Screen Middle Name Heading TextView")
private WebElement middleNameLabel;
@AndroidFindBy(accessibility = "My Profile Screen Middle Name Edittext")
private WebElement middleNameTextField;
@AndroidFindBy(accessibility = "My Profile Screen Last Name Heading TextView")
private WebElement lastNameLabel;
@AndroidFindBy(accessibility = "My Profile Screen Last Name Edittext")
private WebElement lastNameTextField;
@AndroidFindBy(accessibility = "My Profile Screen Female RadioButton")
private WebElement gender;
@AndroidFindBy(accessibility = "My Profile Screen DOB TextView")
private WebElement dob;
@AndroidFindBy(accessibility = "My Profile Screen Email EditText")
private WebElement email;
@AndroidFindBy(accessibility = "My Profile Screen Save Button")
private WebElement save;
@AndroidFindBy(accessibility = "Custom CalendarView Dialog Month Spinner")
private WebElement monthSpinner;
@AndroidFindBy(xpath = "//android.widget.TextView[@text='February']")
private WebElement month;
@AndroidFindBy(accessibility = "Custom CalendarView Dialog Year Spinner")
private WebElement yearSpinner;
@AndroidFindBy(xpath = "//android.widget.TextView[@text='1999']")
private WebElement year;
@AndroidFindBy(xpath ="//android.widget.TextView[@text='1']")
private WebElement date;
@AndroidFindBy(accessibility = "Custom CalendarView Dialog Okay Button")
private WebElement okayButton;
@AndroidFindBy(xpath ="//android.widget.TextView[@text='Feb 01, 1999']")
private WebElement selectedDOB;
@AndroidFindBy(accessibility = "My Profile Screen Phone Num EditText")
private WebElement phoneNumberTextField;
@AndroidFindBy(xpath ="//android.widget.EditText[@text='9632580410']")
private WebElement phoneNumber;
@AndroidFindBy(xpath ="//android.widget.EditText[@text='test123@gmail.com']")
private WebElement emailID;
@AndroidFindBy(xpath ="//android.widget.EditText[@text='Enter current password']")
private WebElement currentPwdTextBox;
@AndroidFindBy(xpath ="//android.widget.EditText[@text='Enter new password']")
private WebElement newPwdTextBox;
@AndroidFindBy(xpath ="//android.widget.EditText[@text='Re-enter new password']")
private WebElement reEnterNewPwdTextBox;
@AndroidFindBy(xpath ="//android.widget.Button[@text='Save']")
private WebElement changePasswordSaveButton;
private LoginMenuPage loginMenuPage;
String generatedPassword ;
public EditProfilePage() throws IOException {
	loginMenuPage=new LoginMenuPage();
}
public WebElement scrollToYear() {
	return getDriver().findElement(AppiumBy.androidUIAutomator(
			"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"2000\").instance(0))"));

}
//Scroll to the element with the text "Date of birth"
	public WebElement scrollToDateOfBirth() {
		return getDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Date of birth\").instance(0))"));

	}
	// Verifies the elements on the My Profile page
public void verifyMyProfilePage() throws InterruptedException {
	isDisplayed(editProfile, "Edit Profile is Displayed");
	Thread.sleep(5000);
	click(editProfile,"Clicked on Edit Profile");
    isDisplayed(myProfileTitle, "My Profile Title Displayed");
    isDisplayed(changePhoto, "Change Photo Displayed");
    isDisplayed(changePassword, "Change Password Displayed");
    isDisplayed(fingerPrintLock, "FingerPrint Lock displayed");
    isDisplayed(userNameLabel, "Username Label Displayed");
    isDisplayed(userNameTextField, "Username Text Field is Displayed");
    isDisplayed(firstNameLabel, "First Name Label Displayed");
    isDisplayed(firstNameTextField, "First Name Text Field Displayed");
    isDisplayed(middleNameLabel, "Middle Name Label Displayed");
    isDisplayed(middleNameTextField, "Middle Name Text Field Displayed");
    isDisplayed(lastNameLabel, "Last Name Label Displayed");
    isDisplayed(lastNameTextField, "Last Name Text Field Displayed");
    scrollToDateOfBirth();
    isDisplayed(gender, "Gender Displayed");
    isDisplayed(dob, "DOB Displayed");
    isDisplayed(email, "Email Displayed");
    isDisplayed(save, "Save Button Displayed");
	
}
//Changes the profile photo by capturing a new photo
public void changeProfilePhotoByCapturingPhoto() throws InterruptedException {
	click(editProfile, "Clicked on edit profile");
	Thread.sleep(5000);
	click(changePhoto, "Clicked on change photo");
	click(takePhoto, "Clicked on Take photo");
	click(camera, "Clicked on Camera");
	click(photoCaptureButton, "Capture the photo");
	isDisplayed(profilePhoto,"Profile photo is displayed");
}
//Uploads a profile photo and verifies its display
public void uploadProfilePhotoAndVerify() throws InterruptedException{
	launchCamera();
	click(shutterButton, "Clicked on capture button");
	activateIntelehealth();
	click(editProfile, "Clicked on edit profile");
	Thread.sleep(5000);
	click(changePhoto, "Clicked on change photo");
	click(chooseFromGallery, "Clicked on Choose from gallery");
	click(photos, "Clicked on photos");
	click(image, "Seplects a image");	
	isDisplayed(profilePhoto, "profile photo is displayed");
}
//Verifies that the user can save the updated details
public void verifyUserCanSaveTheUpdatedDetails() throws InterruptedException {
	click(editProfile, "Clicked on edit profile");
	Thread.sleep(5000);
	scrollToDateOfBirth();
	click(dob, "Clicked on DOB Field");
	click(monthSpinner, "Clicked on month spinner");
	click(month, "Selects a month");
	click(yearSpinner, "Clicked on year spinner");
	scrollToYear();
	click(year, "Selects a year");
	click(date, "Selects a date");
	click(okayButton, "Clicked on okay button");
	clear(phoneNumberTextField);
	sendKeys(phoneNumberTextField, "9632580410", "Entered a phone number");
	clear(email);
	sendKeys(email, "test123@gmail.com", "Entered email");
	click(save, "Clicked On save button ");
	click(menu, "Clicked on menu");
	click(editProfile, "Clicked on edit profile");
	Thread.sleep(5000);
	scrollToDateOfBirth();
	isDisplayed(selectedDOB, "Updated DOB is Displayed");
	isDisplayed(phoneNumber, "Updated Phone Number is Displayed");
	isDisplayed(emailID, "Updated Email ID is Displayed");
}
//Verifies that the user is able to login with the new password

public void verifyUserAbleToLoginWithNewPassword() throws InterruptedException {
    // Generate and store a 7-character password
 generatedPassword = generatePassword(); // Corrected: initialize generatedPassword
    System.out.println("Generated Password: " + generatedPassword);
    isDisplayed(editProfile, "Edit Profile is Displayed");
	Thread.sleep(5000);
	click(editProfile,"Clicked on Edit Profile");
    click(changePassword, "Clicked on Change Password");
    sendKeys(currentPwdTextBox, "Nurse123", "Entered Current Password");
    sendKeys(newPwdTextBox, generatedPassword, "Entered New Password"); 
    sendKeys(reEnterNewPwdTextBox, generatedPassword, "Re-Entered New Password");
    click(changePasswordSaveButton, "Clicked on Save Button");
    loginMenuPage.login();
    
    
   
}
//Resets the password to the old password
public void resetThePasswordToOldPassword() throws InterruptedException {
	loginMenuPage.clickOnHamburgerMenu();
	isDisplayed(editProfile, "Edit Profile is Displayed");
	Thread.sleep(5000);
	click(editProfile,"Clicked on Edit Profile");
	click(changePassword, "Clicked on Change Password");
	  sendKeys(currentPwdTextBox, generatedPassword, "Entered Current Password");
	  sendKeys(newPwdTextBox,  "Nurse123", "Entered New Password"); 
	    sendKeys(reEnterNewPwdTextBox,  "Nurse123", "Re-Entered New Password");
	    click(changePasswordSaveButton, "Clicked on Save Button");
	    loginMenuPage.login();
}

//Generates a password with a specific pattern

public static String generatePassword() {
    StringBuilder password = new StringBuilder();
    Random random = new Random();

    // Ensure the first letter is an uppercase letter
    char firstChar = (char) (random.nextInt(26) + 'A');
    password.append(firstChar);

    // Generate the remaining characters (letters and at least one digit)
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // Append 6 characters (length - 1) without the first character
    for (int i = 1; i < 7; i++) {
        char randomChar = characters.charAt(random.nextInt(characters.length()));
        password.append(randomChar);
    }

    // Append at least one digit
    password.append(characters.charAt(random.nextInt(10) + 52)); // Digits start at index 52

    return password.toString();
}
}
