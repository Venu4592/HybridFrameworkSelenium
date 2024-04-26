package com.tutorialsNinja.hybrid.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {

	WebDriver driver;

	public Homepage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement MyaccDropDownMenu;
	
	public void ClickOnMyAccountDropMenu() {
		MyaccDropDownMenu.click();
	}
	
	@FindBy(linkText = "Login")
	private WebElement ClickonLoginLink;
	
	public LoginPage ClickonUserLoginLink() {
		ClickonLoginLink.click();
		
		return new LoginPage(driver);
	}
}
