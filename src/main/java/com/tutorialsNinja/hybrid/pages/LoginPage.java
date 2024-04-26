package com.tutorialsNinja.hybrid.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	

	WebDriver driver;
	Properties props;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-email")
	private WebElement EnterEmailtext;
	
	public void EnterTheUserEmailid(String UserName) {
		
		EnterEmailtext.sendKeys(UserName);
	}
	
	@FindBy(id = "input-password")
	private WebElement EnterPasswordText;
	
	public void EnterThePassWord(String Password) {
		EnterPasswordText.sendKeys(Password);
	}
	
	@FindBy(xpath =  "//input[@value='Login']")
	private WebElement ClickOnLoginButton;
	
	public AccountPage ClickingOnLoginButton() {
		ClickOnLoginButton.click();
		
		return new AccountPage(driver);
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement AssertTheSuccessfullLogin;
	
	public boolean AssertingtheSuccessfullLogin() {
	boolean assertstatment =	AssertTheSuccessfullLogin.isDisplayed();
		return assertstatment;
	}
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement WarningAssertion;
	public String WarningMessageAssertion(){
		
		String warningMessage=WarningAssertion.getText();
		return warningMessage;
	}
}
