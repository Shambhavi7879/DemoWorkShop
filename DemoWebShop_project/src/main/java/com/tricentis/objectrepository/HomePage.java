package com.tricentis.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Log out")
	private WebElement logoutLink;
	
	@FindBy(xpath = "//p[@class='content']")
	private WebElement addTocartMsg;
	
	@FindBy(xpath="//div[contains(text(),'Cart is empty')]")
	private WebElement removeFromCart;
	
	public WebElement getRemoveFromCart() {
		return removeFromCart;
	}

	public WebElement getAddTocartMsg() {
		return addTocartMsg;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}
	
	
}
