package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest()
	{
		super();
	}
	
	
	//testcase should be separated -- independent with each other
	//before each test case --launch the browser and login
	//@Test -- execute test case
	//after each test case -- close the browser
	@BeforeMethod
	public void setUp(){
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password")); //As we have to work on Hopme page, so we have to do login and its returntype is HomePage object.
		contactsPage = new ContactsPage();
	}
	
	@Test(priority=1)
		public void verifyHomePageTitle(){
		String Title = homePage.verifyHomePageTitle();
		Assert.assertEquals(Title, "CRMPRO","Home Page title not Matched."); //In assertEquals method we can pass the message at the end to print, if the condition got fail. It will nt=ot print if the condition got pass. Suppose if the condition fail then we can pass it doesnot match. etc.
	}
	
	@Test(priority=2)
	public void verifyUserNameTest(){
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest(){
		testUtil.switchToFrame();
		contactsPage  = homePage.clickOnContactsLink();
	}
	
	
	

	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	

}
