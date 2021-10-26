package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	
	//Page Factory - OR:
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement LoginBtn;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement SignUpBtn;
	
	@FindBy(xpath="//img[@src='https://classic.freecrm.com/img/logo.png']")
	WebElement crmLogo;
	
	/*Need to initialize the Page Factory, so create a constructor for the class 'LoginPage' and use PageFactory class method(initElements()) for initialization. 
	Using the same driver from 'TestBase' class. 'This' means "for current class objects". This points to the current class objects.*/
	//Initializing the Page Objects:
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean validateCRMImage(){
		return crmLogo.isDisplayed();
	}
	
	/*After login, it will land to the HomePage so the return type will be the object of HomePage,
	and in place of void returntype will be the class 'Homepage'*/   
	public HomePage login(String ur, String pass){   
		username.sendKeys(ur);
		password.sendKeys(pass);
		LoginBtn.click();
		return new HomePage();
	}
	
	
	
	
	
	
	
	
	
	
}
