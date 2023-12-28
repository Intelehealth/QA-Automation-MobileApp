package com.intelehealth.base;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.TestUtils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.apache.commons.codec.binary.Base64;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class BaseTest {
	protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

	protected static ThreadLocal<Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal<HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
	protected static ThreadLocal<String> platform = new ThreadLocal<String>();
	protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();
	protected static ThreadLocal<String> deviceName = new ThreadLocal<String>();
	private static AppiumDriverLocalService server;
	 private static final String SECRET_KEY = "YourSecretKey1234";
	 
	String appiumURL;
	TestUtils utils = new TestUtils();

	public AppiumDriver getDriver() {
		return driver.get();
	}

	public void setDriver(AppiumDriver driver2) {
		driver.set(driver2);
	}

	public Properties getProps() {
		return props.get();
	}

	public void setProps(Properties props2) {
		props.set(props2);
	}

	public HashMap<String, String> getStrings() {
		return strings.get();
	}

	public void setStrings(HashMap<String, String> strings2) {
		strings.set(strings2);
	}

	public String getPlatform() {
		return platform.get();
	}

	public void setPlatform(String platform2) {
		platform.set(platform2);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public String getDeviceName() {
		return deviceName.get();
	}

	public void setDeviceName(String deviceName2) {
		deviceName.set(deviceName2);
	}

	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	@BeforeMethod
	public void beforeMethod() {
		((CanRecordScreen) getDriver()).startRecordingScreen();
	}

	// stop video capturing and create *.mp4 file
	@AfterMethod
	public synchronized void afterMethod(ITestResult result) throws Exception {
		String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();

		Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
		String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
				+ File.separator + getDateTime() + File.separator
				+ result.getTestClass().getRealClass().getSimpleName();

		File videoDir = new File(dirPath);

		synchronized (videoDir) {
			if (!videoDir.exists()) {
				videoDir.mkdirs();
			}
		}
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
			stream.write(Base64.decodeBase64(media));
			stream.close();
			utils.log().info("video path: " + videoDir + File.separator + result.getName() + ".mp4");
		} catch (Exception e) {
			utils.log().error("error during video capture" + e.toString());
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	@BeforeSuite
	public void beforeSuite() throws Exception, Exception {
		System.out.println("===============================================================");
		ThreadContext.put("ROUTINGKEY", "ServerLogs");
		// server = getAppiumService(); // -> If using Mac, uncomment this statement and
		// comment below statement
		server = getAppiumServerDefault();
		// appiumURL = server.getUrl().toString();
		// -> If using Windows, uncomment this statement and comment above statement
		if (!checkIfAppiumServerIsRunnning(4723)) {
			server.start();
			// server.clearOutPutStreams(); // -> Comment this if you want to see server
			// logs in the console
			utils.log().info("Appium server started");
		} else {
			utils.log().info("Appium server already running");
		}
	}

	public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
		boolean isAppiumServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			System.out.println("1");
			isAppiumServerRunning = true;
		} finally {
			socket = null;
		}
		return isAppiumServerRunning;
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		if (server.isRunning()) {
			// server.stop();
			utils.log().info("Appium server stopped");
		}
	}

	// for Windows
	public AppiumDriverLocalService getAppiumServerDefault() {

		HashMap<String, String> environment = new HashMap();

		environment.put("PATH", "/Users/local/bin:" + System.getenv("PATH"));

		AppiumServiceBuilder builder = new AppiumServiceBuilder();

		builder

				.withAppiumJS(
						new File("C://Users//Shweta//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))

				.usingDriverExecutable(new File("C://Program Files//nodejs//node.exe"))

				.usingPort(4723)

				.withEnvironment(environment)

				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE);

				

		AppiumDriverLocalService server = AppiumDriverLocalService.buildService(builder);

		System.out.println("Server started at :" + server.getUrl());
		// return AppiumDriverLocalService.buildService(new AppiumServiceBuilder());
		// System.out.println("Server started at :" + server.getUrl());
		// return server.getUrl().toString();

		return server;

		// return AppiumDriverLocalService.buildDefaultService();
	}

	@BeforeTest

	@Parameters({ "emulator", "platformName", "udid", "deviceName", "systemPort" })

	public void beforeTest(@Optional("androidOnly") String emulator, String platformName, String udid,
			String deviceName, @Optional("androidOnly") String systemPort) throws Exception {
		setDateTime(utils.dateTime());
		setPlatform(platformName);
		setDeviceName(deviceName);
		URL url;
		InputStream inputStream = null;
		InputStream stringsis = null;
		Properties props = new Properties();
		AppiumDriver driver;

		String strFile = "logs" + File.separator + platformName + "_" + deviceName;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		// route logs to separate file for each thread
		ThreadContext.put("ROUTINGKEY", strFile);
		utils.log().info("log path: " + strFile);

		try {
			props = new Properties();
			String propFileName = "config.properties";

			utils.log().info("load " + propFileName);
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			props.load(inputStream);
			setProps(props);

			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("platformName", platformName);
			desiredCapabilities.setCapability("deviceName", deviceName);
			desiredCapabilities.setCapability("udid", udid);
			url = new URL(props.getProperty("appiumURL"));

			if (platformName.equals("Android")) {
				desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));
				// desiredCapabilities.setCapability("appPackage",
				// props.getProperty("androidAppPackage"));
				// desiredCapabilities.setCapability("appActivity",
				// props.getProperty("androidAppActivity"));

				if (emulator.equalsIgnoreCase("true")) {
					desiredCapabilities.setCapability("avd", deviceName);
					desiredCapabilities.setCapability("avdLaunchTimeout", 120000);

				}
				desiredCapabilities.setCapability("systemPort", systemPort);
				// desiredCapabilities.setCapability("autoGrantPermissions", true);
//	            String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
//						+ File.separator + "resources" + File.separator + "app" + File.separator + "Intelehealth.apk";
//		
//			utils.log().info("appUrl is" + androidAppUrl);
//			desiredCapabilities.setCapability("app", androidAppUrl);
				
				driver = new AndroidDriver(url, desiredCapabilities);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				// Check if the app is installed

			} else {
				throw new Exception("Invalid platform! - " + platformName);
			}
			setDriver(driver);
			utils.log().info("driver initialized: " + driver);
		} catch (Exception e) {
			utils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (stringsis != null) {
				stringsis.close();
			}
		}
	}

	
	public void waitForVisibility(WebElement e) {
	    int maxAttempts = 5;
	    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
	        try {
	            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TestUtils.WAIT));
	            wait.until(ExpectedConditions.visibilityOf(e));
	            break; // Exit the loop if visibility is successful
	        } catch (StaleElementReferenceException ex) {
	            System.out.println("StaleElementReferenceException caught. Retrying visibility attempt " + attempt);
	            // Add a small delay before retrying (customize based on your needs)
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	}
	

	public boolean isDisplayed(WebElement e, String msg) {
	    int maxAttempts = 5;
	    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
	        try {
	            waitForVisibility(e);
	            utils.log().info(msg);
	            ExtentReport.getTest().log(Status.INFO, msg);
	            return e.isDisplayed();
	        } catch (StaleElementReferenceException ex) {
	            System.out.println("StaleElementReferenceException caught. Retrying isDisplayed attempt " + attempt);
	            // Add a small delay before retrying (customize based on your needs)
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	    return false; // Return false if visibility is not successful after max attempts
	}
	public void click(WebElement e, String msg) {
	    int maxAttempts = 5;
	    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
	        try {
	            waitForVisibility(e);
	            utils.log().info(msg);
	            ExtentReport.getTest().log(Status.INFO, msg);
	            e.click();
	            break; // Exit the loop if click is successful
	        } catch (StaleElementReferenceException ex) {
	            System.out.println("StaleElementReferenceException caught. Retrying click attempt " + attempt);
	            // Add a small delay before retrying (customize based on your needs)
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	}
	public void sendKeys(WebElement e, String txt, String msg) {
	    int maxAttempts = 5;
	    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
	        try {
	            waitForVisibility(e);
	            utils.log().info(msg);
	            ExtentReport.getTest().log(Status.INFO, msg);
	            e.sendKeys(txt);
	            break; // Exit the loop if sendKeys is successful
	        } catch (StaleElementReferenceException ex) {
	            System.out.println("StaleElementReferenceException caught. Retrying sendKeys attempt " + attempt);
	            // Add a small delay before retrying (customize based on your needs)
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	}

//	public void waitForVisibility(WebElement e) {
//		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TestUtils.WAIT));
//		wait.until(ExpectedConditions.visibilityOf(e));
//	}
	
	public void setImplicitWait() {
		 getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	/*
	 * public void waitForVisibility(WebElement e){ Wait<WebDriver> wait = new
	 * FluentWait<WebDriver>(getDriver()) .withTimeout(Duration.ofSeconds(30))
	 * .pollingEvery(Duration.ofSeconds(5)) .ignoring(NoSuchElementException.class);
	 * wait.until(ExpectedConditions.visibilityOf(e)); }
	 */
//	public boolean isDisplayed(WebElement e, String msg) {
//		waitForVisibility(e);
//		utils.log().info(msg);
//		ExtentReport.getTest().log(Status.INFO, msg);
//		return e.isDisplayed();
//	}
	public boolean isDisplayed(WebElement e) {
		waitForVisibility(e);
		
		return e.isDisplayed();
	}
	public void clear(WebElement e) {
		waitForVisibility(e);
		e.clear();
	}

	public void click(WebElement e) {
		waitForVisibility(e);
		e.click();
	}

//	public void click(WebElement e, String msg) {
//		waitForVisibility(e);
//		utils.log().info(msg);
//		ExtentReport.getTest().log(Status.INFO, msg);
//		e.click();
//	}

	public void sendKeys(WebElement e, String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);

	}
	public void sendKeysObject(WebElement e, Object object) {
		waitForVisibility(e);
		e.sendKeys((CharSequence[]) object);

	}
//	public void sendKeys(WebElement e, String txt, String msg) {
//		waitForVisibility(e);
//		utils.log().info(msg);
//		ExtentReport.getTest().log(Status.INFO, msg);
//		e.sendKeys(txt);
//	}

	public String getAttribute(WebElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}

	public void isSelected(WebElement e) {
		e.isSelected();

	}
	public boolean isDisplayed2(By WebElement) {
		WebElement element = getElement(WebElement);
		if (element != null && element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	private WebElement getElement(By webElement) {
		WebElement element = null;
		try {
			element = getDriver().findElement(webElement);
		} catch (Exception e) {
			System.out.println("some exception occurred while creating the webelement : " + webElement);
		}
		return element;
	}
	public String doGetFormattedCurrentDDMonYYYY() {
		Date currentDate = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);
		String formattedDate = dateformat.format(currentDate);
		System.out.println("Current Date: " + formattedDate);
		return formattedDate;
	}
	public static void executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String extractBefore(String input, String splitString) {
		// Find the index of the split string
		int splitIndex = input.indexOf(splitString);

		// If the split string is found, extract the substring before that index
		if (splitIndex != -1) {
			return input.substring(0, splitIndex).trim();
		}

		// If the split string is not found, return the original string
		return input.trim();
	}
	public String extractBetween(String input, String startDelimiter, String endDelimiter) {
		// Find the index of the start delimiter
		int startIndex = input.indexOf(startDelimiter);

		// Find the index of the end delimiter
		int endIndex = input.indexOf(endDelimiter);

		// If both delimiters are found and the start comes before the end, extract the
		// substring between them
		if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
			return input.substring(startIndex + startDelimiter.length(), endIndex).trim();
		}

		// If the delimiters are not found or the order is incorrect, return an empty
		// string or handle accordingly
		return "";
	}
	
	public List<WebElement> getElements(By locator) {
		List<WebElement> element = null;
		try {
			element = getDriver().findElements(locator);
		} catch (Exception e) {
			System.out.println("some exception occurred while creating the webelement : " + locator);
		}
		return element;
	}
	public void clear(WebElement e, String msg) {
		waitForVisibility(e);
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		e.clear();
	}
	public boolean isDisplayedWithoutWaits(WebElement e) {
		return e.isDisplayed();
	}
	public WebElement scrollToElementByDescription(String description) {
		return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).scrollIntoView(" + "new UiSelector().description(\"" + description + "\"));"));
	}
	public WebElement scrollToElement() {
		return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).scrollIntoView("
				+ "new UiSelector().description(\"Identification First Screen Phone Num Title LinearLayout\"));"));
		// AppiumBy.androidUIAutomator("new UiScrollable(new
		// UiSelector()).scrollIntoView(text(\"Date of Birth\"));"));
	}

//  public WebElement scrollToElement( WebElement e) {
//	    
//	    return getDriver().findElement(AppiumBy.androidUIAutomator(
//	    		 "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
//						  + "new UiSelector().description(\""+ e +"\"));"));
//	}
	public String getText(WebElement e, String msg) {
		String txt = null;

		txt = getAttribute(e, "text");

		utils.log().info(msg + txt);
		ExtentReport.getTest().log(Status.INFO, msg + txt);
		return txt;
	}
	

	public void closeApp() {

		((InteractsWithApps) getDriver()).terminateApp(getProps().getProperty("androidAppPackage"));

	}

	public void launchApp() {
		((JavascriptExecutor) getDriver()).executeScript("mobile:startActivity", ImmutableMap.of("intent",
				getProps().getProperty("androidAppPackage") + "/" + getProps().getProperty("androidAppActivity")));
		
	}

	public void resetApp() {
		getDriver().executeScript("mobile:clearApp",
				ImmutableMap.of("appId", getProps().getProperty("androidAppPackage")));
	}
	public void launchCamera() {
		((JavascriptExecutor) getDriver()).executeScript("mobile:startActivity", ImmutableMap.of("intent",
				"com.android.camera2"+ "/" + "com.android.camera.CameraLauncher"));
		
	}
	public void activateIntelehealth() {
		((InteractsWithApps) getDriver()).activateApp(getProps().getProperty("androidAppPackage"));
	}

	
	public WebElement scrollToTextContains_Android(String text) {
        return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"));
	}
	
	   public static String encrypt(String password) {
	        try {
	            // Generate a fixed-size key based on the password
	            MessageDigest sha = MessageDigest.getInstance("SHA-256");
	            byte[] keyBytes = Arrays.copyOf(sha.digest(SECRET_KEY.getBytes()), 16);

	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
	            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
	            return java.util.Base64.getEncoder().encodeToString(encryptedBytes);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    public static String decrypt(String encryptedPassword) {
	        try {
	            // Generate a fixed-size key based on the password
	            MessageDigest sha = MessageDigest.getInstance("SHA-256");
	            byte[] keyBytes = Arrays.copyOf(sha.digest(SECRET_KEY.getBytes()), 16);

	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
	            cipher.init(Cipher.DECRYPT_MODE, secretKey);
	            byte[] decryptedBytes = cipher.doFinal(java.util.Base64.getDecoder().decode(encryptedPassword));
	            return new String(decryptedBytes);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    
	    public void pressEnter(WebElement element) {
	        element.click(); // Ensure the element is focused
	        ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
	    }
	    
	    
	    public boolean isDisplayed2(WebElement e) {
			WebElement element = e;
			try {
				if (element != null && element.isDisplayed()) {
					return true;
				}
			} catch (Exception e1) {

			}
			return false;
		}

		public boolean isDisplayedListofWebelemets(List<WebElement> webElements) {
			List<WebElement> elements = webElements;
			for (WebElement element : elements) {
				try {
					if (element != null && element.isDisplayed()) {
						return true;
					}
				} catch (Exception e1) {

				}
			}
			return false;
		}
	@AfterTest(alwaysRun = true)
	public void afterTest() {
		if (getDriver() != null) {
			getDriver().quit();

		}
	}
}