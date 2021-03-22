package com.test.alok.stepdef;

import java.util.concurrent.TimeUnit;


import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.test.alok.Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import junit.framework.Assert;

public class SignInCheck {

	WebDriver driver = null;
	String projectPath = System.getProperty("user.dir");
	LoginPage login;

	
	@Before
	public void setup() {
		System.out.println("Inside setup****");
		
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	
	@Given("^User is on home page$")
	public void user_is_on_home_page() {

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("User is on home page");
		driver.navigate().to("http://automationpractice.com/");

	}

	@When("^User click sign in$")
	public void user_click_sign_in() throws InterruptedException {
		System.out.println("Inside user click signin**");
		login = new LoginPage(driver);
		// System.out.println("xpath:::" + login.signIn_XPATH);
		if (login.signInLocator().isDisplayed()) {
			System.out.println("Inside Sing Btn");
			driver.findElement(login.signIn_XPATH).click();
			Thread.sleep(5000);
			org.junit.Assert.assertEquals(true, login.signInLocator().isDisplayed());
		}

	}

	@Then("^User sees login screen & enters username password$")
	public void user_sees_login_screen() throws InterruptedException {

		System.out.println("User sees Login Screen***");

		if (login.emailLocator().isDisplayed() && login.pwdLocator().isDisplayed()) {
			System.out.println("Inside user sees login screen**");
			org.junit.Assert.assertEquals(true, login.emailLocator().isDisplayed());
			org.junit.Assert.assertEquals(true, login.pwdLocator().isDisplayed());

			login.enterUsername(Utils.username);
			login.enterPassword(Utils.password);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(2000);
			
		}
	}

	@When("^User click sign in button$")
	public void user_clicks_sign_in_button() throws InterruptedException {
		if (login.signInBtnLocator().isDisplayed()) {
			org.junit.Assert.assertEquals(true, login.signInBtnLocator().isDisplayed());
			login.clickSignIn();
			
			Thread.sleep(2000);

		}
	}

	//

	@Then("^User lands on my account page$")
	public void user_lands_on_my_account_page() {

		if (login.tShirtLocator().isDisplayed()) {
			System.out.println("Inside tshirtlocator*****");
			org.junit.Assert.assertEquals(true, login.tShirtLocator().isDisplayed());

		} else {
			org.junit.Assert.assertEquals(false, login.tShirtLocator().isDisplayed());
		}
	}

	@When("^User clicks on Tshirts$")
	public void user_clicks_on_tshirts() {

		if (login.tShirtLocator().isDisplayed()) {
			org.junit.Assert.assertEquals(true, login.tShirtLocator().isDisplayed());
			login.clickTShirts();
			

		}
	}

	@Then("^User selects Tshirts$")
	public void user_selects_tshirts() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)", "");
		Thread.sleep(2000);

		if (login.tShirtItemLocator().isDisplayed()) {
			System.out.println("Inside User selects Tshirts");
			Actions action = new Actions(driver);
			action.moveToElement(login.tShirtItemLocator()).perform();
			if (login.addToCartLocator().isDisplayed()) {
				org.junit.Assert.assertEquals(true, login.addToCartLocator().isDisplayed());
				login.clickAddToCart();
				

			}

		}

	}

	@And("^User checks his cart$")
	public void user_checks_his_cart() {

		org.junit.Assert.assertEquals(true, login.addToCartLocator().isDisplayed());
	}
	
	
	@After
	public void cleanup() {
		
		driver.close();
		driver.quit();
	}
	
	
}
