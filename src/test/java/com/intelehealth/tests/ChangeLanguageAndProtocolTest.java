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
import com.intelehealth.pages.ChangeLanguageAndProtocolPage;
import com.intelehealth.pages.LoginMenuPage;
import com.intelehealth.utils.TestUtils;

public class ChangeLanguageAndProtocolTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	ChangeLanguageAndProtocolPage changeLanguageAndProtocolPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, InterruptedException {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		resetApp();

		launchApp();
		System.gc();
		appSetupPage = new AppSetupPage();
		changeLanguageAndProtocolPage = new ChangeLanguageAndProtocolPage();
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
		// grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();
		Thread.sleep(4000);

	}

	@Test(priority = 1, description = "Verify that User can select the language from the dropdown", enabled = true)
	public void IDA4_2032_verifyChangeLanguage() throws InterruptedException {
		// verify that the user can select a language on the Change Language and
		// Protocol page
		changeLanguageAndProtocolPage.verifyUserCanSelectLanguage();
	}

}