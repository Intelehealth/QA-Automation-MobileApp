package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.CloseVisitsPage;
import com.intelehealth.pages.FindPatientPage;
import com.intelehealth.utils.TestUtils;

public class CloseVisitsTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	CloseVisitsPage closeVisitsPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, InterruptedException {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		resetApp();
		launchApp();
		appSetupPage = new AppSetupPage();
		closeVisitsPage=new CloseVisitsPage();
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
		//grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
        appSetupPage.completeSetup();
        appSetupPage.refreshUIAndWait();
	}
	@Test(priority = 1, description = "Verify the functionality of Close visit button under Recent visit/Older Visits section", enabled = true)
	public void IDA4_2239_verifyCloseVisitButtonFunctionality() throws InterruptedException {
    	closeVisitsPage.verifyCloseVisitButtonFunctionality();
}
	@Test(priority = 2, description = "Verify clicking on Confirm button", enabled = true)
	public void IDA4_2241_verifyConfirmButton() throws InterruptedException {
    	closeVisitsPage.verifyConfirmButtonFunctionality();
}
	@Test(priority = 3, description = "Verify entering text in Give feedback area and click on submit button", enabled = true)
	public void IDA4_2245_enterTextInFeedbackAreaAndSubmit() throws InterruptedException {
    	closeVisitsPage.enterFeedbackAndverify();
}
        @Test(priority = 4, description = "Verify whether user able to close visits of both Recent and Older visits section", enabled = true)
    	public void IDA4_2249_verifyUserAbleToCloseVisitsFromRecentAndOlderSection() throws InterruptedException {
        	closeVisitsPage.closeVisitsInRecentAndOlderVisitsSection();
}
        
        
        
        
        @Test(priority = 5, description = "Verify the functionality of Recent Visits section", enabled = true)
    	public void IDA4_2236_verifyRecentVisitsSection() throws InterruptedException {
        	closeVisitsPage.verifyRecentVisitsSection();
}
        @Test(priority = 6, description = "Verify the count once user closed the visit", enabled = true)
    	public void IDA4_2247_verifyClosedCountIsDecreased() throws InterruptedException {
        	Thread.sleep(2000);
        	closeVisitsPage.verifyCloseVisitsCountIsDecreased();
}
        @Test(priority = 7, description = "Verify whether above 7 days visited Patient's are only reflecting under Older Visits section", enabled = true)
    	public void IDA4_2238_verifyAboveSevenDaysPatientsAreDisplayedInOlderVisits() throws InterruptedException {
        	
        	closeVisitsPage.verifyAboveSevenDaysPatientsAreDisplayed();
}
  
        @Test(priority = 8, description = "Verify whether last 7 days visited Patient's are only reflecting under Recent Visits section", enabled = true)
    	public void IDA4_2237_verifyAboveLastSevenDaysPatientsAreDisplayedInRecentVisits() throws InterruptedException {
        	
        	closeVisitsPage.verifyLastSevenDaysPatientsAreDisplayed();
}
}
