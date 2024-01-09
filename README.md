# Intelehealth-Mobile -Automation 

Appium mobile test automation framework with Page Object Model design using Java + Maven + TestNG.
Framework follows many of the industry best practices and supports Android.

##Project overview
 - Total Testcases - 
 - Regression Testcases(Automatable) - 
 - High Priority Regression Testcases (Automatable) - 

##Introduction on intelehealth app
  - Intelehealth is an Medical domain Non Profitable Organization Intelehealth leverages open-source technology for 
    Governments, NGO’s and Hospitals to seamlessly connect hard to reach population with high quality primary healthcare! 
    
Technologies/Tools used in building the framework
=================================================
- Eclipse - IDE
- Appium - Mobile Automation library
- Maven - Build automation tool
- Java - Programming language
- TestNG - Test Management library
- Log4J - Logging framework
- Extent Reports - Reporting framework
- JSON - Test Data
- XML - Static text


   
Project Structure
===================================================== 

 Intelehealth-Mobile-Automation/
|-- src/
|   |-- main/
|   |   |-- resources/
|   |       |-- config.properties
|   |       |-- log4j.properties
|   |       
|   |          
|   |           
|   |               
|   |-- test/
|       |-- java/
|       |   |-- base/
|       |   |   |-- BaseTest.java
|       |   |-- test/
|       |   |   |-- AppSetupTest.java
                |-- ... (more test classes)          
|       |   |-- utils/
|       |   |   |-- TestUtils.java
|       |   
|       |   |-- report/
|       |   |   |-- ExtentReport.java
|       |   |-- pages/
|       |   |   |-- AppSetupPage.java
|       |       |-- ... (more page classes)
|       |   |-- listeners/
|       |       |-- TestListener.java
|       |-- resources/
|           |-- app/
|           |   |-- Intelehealth.apk
|           |-- data/
|               |-- appData.json
|-- target/
|-- test-output/
|-- pom.xml
|-- testng.xml
|-- README.md
|-- .gitignore

## Setup

1. Clone the repository:
   git clone https://github.com/Intelehealth/QA-Automation-MobileApp
2. Update the Appium installation path and Node installation path
3. Update the device details in XML file.


##Framework Details
 - Takes screenshots on test failures
 - Record the videos of Test execution
 - Extent reports is used for Reporting
 
 ##Test Script Design:
 - All the Regression testcases with High priority are selected for Automation.
 - All the Locators are stored in Page File as per the Module using @AndroidFindBy and By   class with variable name
 - Methods are created as per the Testcase and the Modules.
 - Test scripts are stored in a separate class files based on Modules.
 - Each testcases are stored in TestNG annotations @Test methods.
 - @BeforeMethod and @AfterMethod will store the line of code for prerequisite and postrequsite.
 
## Test Data Management

- The `data` folder contains a JSON file (`appData.json`) that includes the required test data.
- The framework utilizes an API for test data generation to ensure the availability of up-to-date and relevant data for testing purposes.

 
## Test Execution Plan

1. **Run Appium Server Manually:**
   - Start the Appium server manually (in case the appium server fails to run programmatically).

2. **Execute Test Cases Using XML File:**
   - Utilize the XML file (`testng.xml`) to execute all the test cases. Ensure that the necessary configurations are set in the XML file.

3. **Selective Test Execution:**
   - Modify the XML file to run specific test scripts based on modules.
     - For example, to run only Login & Dashboard module test cases, adjust the configurations in the XML file accordingly.

   
##Reporting:
 - Extent Report is  used in this framework.
 - Report contains the screenshots of each testcase(fail).
 - Also display the reason for failure testcases.
   
   
