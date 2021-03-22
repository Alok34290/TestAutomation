package com.test.alok.stepdef;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.test.alok.Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyAccountPOM {

	WebDriver driver = null;
	String projectPath = System.getProperty("user.dir");
	MyAccountPage myAcc;

	@Before
	public void setup() {
		System.out.println("Inside setup****");

		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Given("^User is on homepage$")
	public void user_is_on_home_page() {

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("User is on home page");
		driver.navigate().to("http://automationpractice.com/");

	}

	@When("^User click signin$")
	public void user_click_sign_in() throws InterruptedException {
		System.out.println("Inside user click signin**");
		myAcc = new MyAccountPage(driver);
		// System.out.println("xpath:::" + login.signIn_XPATH);
		if (myAcc.signInLocator().isDisplayed()) {
			System.out.println("Inside Sing Btn");
			driver.findElement(myAcc.signIn_XPATH).click();
			Thread.sleep(5000);
			org.junit.Assert.assertEquals(true, myAcc.signInLocator().isDisplayed());
		}

	}

	@Then("^User sees login screen enters username password$")
	public void user_sees_login_screen() throws InterruptedException {

		System.out.println("User sees Login Screen***");

		if (myAcc.emailLocator().isDisplayed() && myAcc.pwdLocator().isDisplayed()) {
			System.out.println("Inside user sees login screen**");
			org.junit.Assert.assertEquals(true, myAcc.emailLocator().isDisplayed());
			org.junit.Assert.assertEquals(true, myAcc.pwdLocator().isDisplayed());

			myAcc.enterUsername(Utils.username);
			myAcc.enterPassword(Utils.password);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(2000);

		}
	}

	@When("^User click signin button$")
	public void user_clicks_sign_in_button() throws InterruptedException {
		if (myAcc.signInBtnLocator().isDisplayed()) {
			org.junit.Assert.assertEquals(true, myAcc.signInBtnLocator().isDisplayed());
			myAcc.clickSignIn();

			Thread.sleep(2000);

		}
	}

	@Then("User lands on myaccount page")
	public void user_lands_on_myaccount_page() {
		// Write code here that turns the phrase above into concrete actions
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");

		if (myAcc.myInformationFieldLocator().isDisplayed()) {
			org.junit.Assert.assertEquals(true, myAcc.myInformationFieldLocator().isDisplayed());
			myAcc.clickMyInformationField();
		}

	}

	// User clicks on save

	@Then("User clicks on save")
	public void user_clicks_on_savebtn() {
		// Write code here that turns the phrase above into concrete actions

		if (myAcc.firstNamefieldLocator().isDisplayed()) {
			org.junit.Assert.assertEquals(true, myAcc.firstNamefieldLocator().isDisplayed());
			myAcc.enterFirstName(Utils.username);
		}

		if (myAcc.saveBtnLocator().isDisplayed()) {
			org.junit.Assert.assertEquals(true, myAcc.saveBtnLocator().isDisplayed());
			myAcc.clickSaveBtn();
		}

	}

	@After
	public void cleanup() {
		driver.close();
		driver.quit();

	}

}
