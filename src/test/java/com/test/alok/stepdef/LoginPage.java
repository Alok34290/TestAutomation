package com.test.alok.stepdef;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver driver;

	//// *[@id="header"]/div[2]/div/div/nav/div[1]/a

	By signIn_XPATH = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");

	By email_XPATH = By.xpath("//input[@id='email']");
	By pwd_XPATH = By.xpath("//input[@id='passwd']");
	By signIn_Btn = By.xpath("//span[contains(.,'Sign in')]");
	By tShirt_XPATH = By.xpath("(//a[contains(@title,'T-shirts')])[2]");
	By tShirtItems_XPATH = By.xpath("//img[@alt='Faded Short Sleeve T-shirts']");

	By addToCart_XPATH = By.xpath("//span[contains(.,'Add to cart')]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement signInLocator() {

		return driver.findElement(signIn_XPATH);

	}

	public WebElement emailLocator() {

		return driver.findElement(email_XPATH);

	}

	public WebElement pwdLocator() {

		return driver.findElement(pwd_XPATH);

	}

	public WebElement signInBtnLocator() {

		return driver.findElement(signIn_Btn);

	}

	public WebElement tShirtLocator() {

		return driver.findElement(tShirt_XPATH);

	}

	public WebElement tShirtItemLocator() {

		return driver.findElement(tShirtItems_XPATH);

	}

	public WebElement addToCartLocator() {

		return driver.findElement(addToCart_XPATH);

	}

	public void clickAddToCart() {

		driver.findElement(addToCart_XPATH).click();

	}

	public void enterUsername(String username) {

		driver.findElement(email_XPATH).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(pwd_XPATH).sendKeys(password);

	}

	public void clickSignIn() {

		driver.findElement(signIn_Btn).click();

	}

	public void clickTShirts() {

		driver.findElement(tShirt_XPATH).click();

	}

}
