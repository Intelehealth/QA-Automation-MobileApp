/**
 * Author: Shweta Naik
 * Description: Test class for verifying App Setup functionalities.
 */
package com.intelehealth.tests;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.TestUtils;

public class AppSetupTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	JSONObject appData;

	
	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		resetApp();
		launchApp();

		appSetupPage = new AppSetupPage();
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
		Thread.sleep(2000);
//		// grant all the permissions
		appSetupPage.handlePermissions();
////           	appSetupPage.selectLanguage();
//		// clicks on next button
		appSetupPage.clickOnNextButton();

	}

	

	@Test(priority = 1, description = "Verify user can select any of the language and click Next", enabled = true)
	public void IDA4_1939_verifySelectLanguage() throws InterruptedException {
		launchApp();
		
		// Verify introductory screen elements are displayed
		appSetupPage.verifyIntroductoryScreen();
		// Verify Skip button is displayed
		appSetupPage.skipButtonIsDisplayed();

	}

	

	@Test(priority = 2, description = " Verify clicking on Skip on the introductory screens", enabled = false)
	public void IDA4_1940_verifySkipOnTheIntroductoryScreen() throws InterruptedException {

		appSetupPage.clickOnSkipButton();
		// Verify elements on Ayu screen are displayed
		appSetupPage.verifyAyuScreen();
	}

	@Test(priority = 3, description = "Verify that user is able to navigate to setup page when the terms & conditions box is checked", enabled = false)
	public void IDA4_1943_verifyTermsAndConditionCheckBox() throws InterruptedException {

		// Click on the "Skip" button to bypass initial setup
		appSetupPage.clickOnSkipButton();

		// Click on the checkbox to agree to terms and conditions
		appSetupPage.clickOnCheckBox();

		// Click on the "Setup" button to proceed with the application setup
		appSetupPage.clickOnSetupButton();

	}

	@Test(priority = 4, description = "Verify clicking on Terms and Conditions/Privacy Policy link on the checkbox text", enabled = false)
	public void IDA4_1941_verifyTermsAndPrivacyPolicyScreen() throws InterruptedException {

		// Click on the "Skip" button to bypass the initial setup
		appSetupPage.clickOnSkipButton();

		// Click on the link to view the Terms and Conditions
		appSetupPage.clickOnTermsAndCondition();

		// Verify that the Terms and Conditions screen is displayed
		appSetupPage.verifyTermsAndConditionScreen();

		// Navigate back from the Terms and Conditions screen
		appSetupPage.clickOnTermsAndConditionsBackArrow();

		// Click on the link to view the Privacy Policy
		appSetupPage.clickOnPrivacyPolicy();

		// Verify that the Privacy Policy screen is displayed
		appSetupPage.verifyPrivacyPolicyScreen();

	}

	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();
	}

}
