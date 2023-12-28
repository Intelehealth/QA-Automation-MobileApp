package com.intelehealth.pages;

import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class ChangeLanguageAndProtocolPage extends BaseTest {
	@AndroidFindBy(accessibility ="Language Protocol Change Language Spinner TextView" )
	private WebElement languageSpinner;
	@AndroidFindBy(xpath ="(//android.widget.TextView[@content-desc=\"Simple Dialog ListItem Title TextView\"])[2]" )
	private WebElement hindi;

	@AndroidFindBy(accessibility ="Patient Registration Dialog Positive (Yes) Button")
	private WebElement yesButton;
	@AndroidFindBy(accessibility ="Patient Registration Dialog Negative (No) Button")
	private WebElement noButton;

	@AndroidFindBy(accessibility ="Custom Toolbar Back Arrow ImageView" )
	private WebElement menu;
	@AndroidFindBy(accessibility ="Language Protocol Reset Language Button RelativeLayout" )
	private WebElement resetButton;
	
	//android.widget.CheckedTextView[@text='Change language & protocol']
//
	@AndroidFindBy(xpath= "//android.widget.CheckedTextView[@text='Change language & protocol']")
	private WebElement changeLangProtocol;
//
//	@AndroidFindBy(accessibility = )
//	private WebElement languageSpinner;

	public void verifyUserCanSelectLanguage() throws InterruptedException {
		click(menu,"Clicked on Hamburger Menu");
		Thread.sleep(20000);
		click(changeLangProtocol,"Clicked on Change Language & Protocol");
	
		
	}
}
