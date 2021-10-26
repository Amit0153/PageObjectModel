package com.crm.qa.testcases;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName= "Contacts";
	
	
	public ContactsPageTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password")); //As we have to work on 'HomePage', so we have to do login and its returntype is HomePage object.
		testUtil.switchToFrame();//in place of writhing this statement before every element of 'HomePage' where it requires to switch, write it one time one time here so that our code will be generic.No need to write multiple times. 
		contactsPage = new ContactsPage();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest(){
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts Label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectContactsByNameTest(){
		contactsPage.selectContactsByName("Amit Kumar");
	}
	
	// For knowledge purpose we are checking how we can do multiple select using the dynamic xpath we have created.
	@Test(priority=3)
	public void selectMultipleContactsByNameTest(){
		contactsPage.selectContactsByName("Amit Kumar");
		contactsPage.selectContactsByName("David Cris");
	}
	
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	/*@Test(priority=4)
	 one issue is observed. which is not in the case of Naveen. When i run this TC, it directly click on ContactsLink rather than mouse over. 
	This is because we have mentioned Click event on the @BeforeMethod section, So to exectute this TC we have to comment out that line as we dont have to jump to ContactsPage but we have to just mouse over and click on NewContactsPage
	public void validateCreateNewContactTest(){
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");*/
		
		@Test(priority=5, dataProvider="getCRMTestData")
		public void validateCreateNewContactTest(String title, String firstName, String lastName, String company){
			homePage.clickOnNewContactLink();
			contactsPage.createNewContact(title, firstName, lastName, company);
			//System.out.println(title+" , "+firstName+" , "+lastName+" , "+company);
		
	}
	
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
