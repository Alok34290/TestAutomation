package com.test.alok.stepdef;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage {

	WebDriver driver;

	//// *[@id="header"]/div[2]/div/div/nav/div[1]/a

	By signIn_XPATH = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");

	By email_XPATH = By.xpath("//input[@id='email']");
	By pwd_XPATH = By.xpath("//input[@id='passwd']");
	By signIn_Btn = By.xpath("//span[contains(.,'Sign in')]");
	By MyInformation_XPATH = By.xpath("//span[contains(.,'My personal information')]");
	By firstName_XPATH = By.xpath("//input[@name='firstname']");
	By saveBtn_XPATH = By.xpath("//span[contains(.,'Save')]");

	public MyAccountPage(WebDriver driver) {
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

	public void enterUsername(String username) {

		driver.findElement(email_XPATH).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(pwd_XPATH).sendKeys(password);

	}


	public void enterFirstName(String username) {

		driver.findElement(firstName_XPATH).sendKeys(username);
	}
	
	public void clickSignIn() {

		driver.findElement(signIn_Btn).click();

	}
	
	public WebElement firstNamefieldLocator() {

		return driver.findElement(firstName_XPATH);

	} 


	public WebElement myInformationFieldLocator() {

		return driver.findElement(MyInformation_XPATH);

	} 

	
	public void clickMyInformationField() {

		driver.findElement(MyInformation_XPATH).click();

	}	
	
	
	public WebElement saveBtnLocator() {

		return driver.findElement(saveBtn_XPATH);

	} 
	
	public void clickSaveBtn() {

		driver.findElement(saveBtn_XPATH).click();

	}	
	
}
