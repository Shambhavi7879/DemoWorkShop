package com.tricentis.genericutility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tricentis.objectrepository.HomePage;
import com.tricentis.objectrepository.LoginPage;
import com.tricentis.objectrepository.WelcomePage;

public class BaseClass {
	public static ExtentReports report;
	public static ExtentTest test;
	public static WebDriver driver;
	public WebDriverWait wait;
	
	public JavaUtility jLib=new JavaUtility();
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	
	public WelcomePage wp;
	public LoginPage lp;
	public HomePage hp;
	
	@BeforeSuite(alwaysRun = true)
	public void configReports() {
		String TIME = jLib.getSystemTime();
		ExtentSparkReporter spark=new ExtentSparkReporter("./HTML_reprts/ExtentReport_"+TIME+".html");
		report=new ExtentReports();
		report.attachReporter(spark);
	}
	
	@Parameters("Browser")
	@BeforeClass(alwaysRun = true)
	public void browserSetup(@Optional("chrome") String browserName) throws IOException {
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		String URL = fLib.getDataFromPropertyFile("url");
		driver.get(URL);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void login(Method method) throws EncryptedDocumentException, IOException {
		test=report.createTest(method.getName());
		wp=new WelcomePage(driver);
		wp.getLoginLink().click();
		
		lp=new LoginPage(driver);
		String EMAIL = eLib.getDataFromExcel("Login", 1, 0);
		String PASSWORD = eLib.getDataFromExcel("Login", 1, 1);
		lp.getEmailTextField().sendKeys(EMAIL);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
	}
	
	@AfterMethod(alwaysRun = true)
	public void logout() {
		hp=new HomePage(driver);
		hp.getLogoutLink().click();
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
	
	@AfterSuite(alwaysRun = true)
	public void reportBackup() {
		report.flush();
	}
	
}
