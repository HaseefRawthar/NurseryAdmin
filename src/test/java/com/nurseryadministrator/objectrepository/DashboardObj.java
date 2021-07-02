package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class DashboardObj extends SetUp {

	WebDriver driver;
	
	public DashboardObj(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//p[contains(text(),'Happy Trails Daycare, Nursery Admin')]")
	public WebElement selectHappyTrailsDaycareNurseryAdmin;
	@FindBy(xpath="//p[contains(text(),'Nursery-Edge-test, Nursery Admin')]")
	public WebElement selectNurseryEdgetestNurseryAdmin;
	
	@FindBy(xpath="//button[contains(text(),'Select')]")
	public WebElement selectButton;
	@FindBy(xpath="//span[@class='m-10 font-weight-bold text-white']")
	public WebElement currentRoleSelected;
	
	@FindBy(xpath="//h4[contains(text(),'Students')]")
	public WebElement Students;
	@FindBy(xpath="//h4[contains(text(),'Parents')]")
	public WebElement Parents;
	@FindBy(xpath="//h4[contains(text(),'Employees')]")
	public WebElement Employees;
	
	@FindBy(xpath="//a[@id='3']")
	public WebElement switchNurseryDrop;
	@FindBy(xpath="//h5[contains(text(),'Nursery-Edge-test, Nursery Admin')]")
	public WebElement NurseryEdgetestAdmin;
	@FindBy(xpath="//h5[contains(text(),'Nursery-Edge-test, Nursery Manager')]")
	public WebElement NurseryEdgetestManager;
	@FindBy(xpath="//h5[contains(text(),'Oliver, Nursery Manager')]")
	public WebElement oliverNursery;
	@FindBy(xpath="//h5[contains(text(),'Happy Trails Daycare, Nursery Admin')]")
	public WebElement happyTrailsDaycareAdmin;
	@FindBy(xpath="//h5[contains(text(),'Happy Trails Daycare, Nursery Manager')]")
	public WebElement happyTrailsDaycareManager;
	@FindBy(xpath="//h5[contains(text(),'Experion, Nursery Manager')]")
	public WebElement experionNursery;
	
	@FindBy(xpath="//span[contains(text(),'Groups')]")
	public WebElement groups;
	@FindBy(xpath="//span[contains(text(),'Add Group')]")
	public WebElement addGroup;
	@FindBy(xpath="//span[contains(text(),'Group List')]")
	public WebElement groupList;
	
	@FindBy(xpath="//span[contains(text(),'Students')]")
	public WebElement students;
	@FindBy(xpath="//span[contains(text(),'Add Student')]")
	public WebElement addStudent;
	@FindBy(xpath="//span[contains(text(),'Student List')]")
	public WebElement studentList;
	
	@FindBy(xpath="//span[contains(text(),'Employees')]")
	public WebElement employees;
	@FindBy(xpath="//span[contains(text(),'Add Employee')]")
	public WebElement addEmployee;
	@FindBy(xpath="//span[contains(text(),'Teacher List')]")
	public WebElement teacherList;
	@FindBy(xpath="//span[contains(text(),'Nurse List')]")
	public WebElement nurseList;
	@FindBy(xpath="//span[contains(text(),'Assistant List')]")
	public WebElement assistantList;
	
	@FindBy(xpath="//span[normalize-space()='Payment Request']")
	public WebElement paymentRequest;
	@FindBy(xpath="//span[normalize-space()='Request Payment']")
	public WebElement requestPayment;
	@FindBy(xpath="//span[contains(text(),'Payment Request List')]")
	public WebElement paymentRequestList;
	
	@FindBy(xpath="//span[contains(text(),'Grant Temporary Admin Access')]")
	public WebElement grantTemporaryAdminAccess;
	
	@FindBy(xpath="//span[contains(text(),'Nursery Admin')]")
	public WebElement nurseryAdmin;
	@FindBy(xpath="//span[contains(text(),'Add Admin')]")
	public WebElement addAdmin;
	@FindBy(xpath="//span[contains(text(),'Admin List')]")
	public WebElement adminList;
	
	@FindBy(xpath="//ul[2]/li[2]/a[1]")
	public WebElement userAdmin;
	@FindBy(xpath="//button[@id='logout-link']")
	public WebElement logoutButton;
	@FindBy(xpath="//button[contains(text(),'Edit Profile')]")
	public WebElement editProfile;
	
	public void logOut()
	{
		waitIfElementClickIsIntercepted(userAdmin, "click", "");
		 waitForElementToLoad(logoutButton);
		 logoutButton.click();
		 log.info("logged out successfully");
	}
	
	public void selectNursery()
	{
		if(isElementPresent(selectButton))
		{
			waitForElementToLoad(selectButton);
			autoScrollandClick(selectNurseryEdgetestNurseryAdmin);
			selectButton.click();
		}
		
	}
}
