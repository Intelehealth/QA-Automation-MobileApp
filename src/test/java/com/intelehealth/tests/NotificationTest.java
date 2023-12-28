package com.intelehealth.tests;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.EditProfilePage;
import com.intelehealth.pages.LoginMenuPage;
import com.intelehealth.pages.NotificationPage;
import com.intelehealth.utils.TestUtils;

public class NotificationTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	LoginMenuPage loginMenuPage;
	NotificationPage notificationPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		resetApp();
		
		launchApp();

		
		appSetupPage = new AppSetupPage();
		loginMenuPage = new LoginMenuPage();
		notificationPage=new NotificationPage();
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

		// Click on the hamburger menu in the login menu page
		
		Thread.sleep(5000);
	}
//	@Test(priority = 1, description = "Verify that user is able to view the prescription", enabled = true)
//	public void IDA4_2142_verifyUserAbleToViewPrescription() throws InterruptedException {
//		notificationPage.verifyUserAbleToViewPrescription();
//	}
//	@Test(priority = 2, description = "Verify once internet is available, the data should be synced to web portal automatically", enabled = true)
//	public void IDA4_2150_verifyDataIsSyncedToWebApp() throws InterruptedException {
//		notificationPage.verifyDataIsSyncedToWeb();
//	}
	
	@Test(priority = 3, description = "Verify that user can view the recent received prescription notification on top", enabled = true)
	public void IDA4_2140_verifyThatUserCanViewTheRecentReceivedPrescriptionNotificationOnTop() throws Exception {
		notificationPage.verifyThatUserCanViewTheRecentReceivedPrescriptionNotificationOnTop();
	}
	
}
