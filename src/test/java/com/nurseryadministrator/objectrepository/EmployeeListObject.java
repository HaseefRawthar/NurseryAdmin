package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class EmployeeListObject extends SetUp{

	WebDriver driver;
	
	public EmployeeListObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//input[@formcontrolname='name']")
	public WebElement name;
	@FindBy(xpath="//select[@formcontrolname='groupType']")
	public WebElement groupType;
	@FindBy(xpath="//select[@formcontrolname='groupId']")
	public WebElement group;
	@FindBy(xpath="//button[contains(text(),'Filter')]")
	public WebElement filterButton;
	@FindBy(xpath="//button[contains(text(),'RESET')]")
	public WebElement resetButton;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/i[1]")
	public WebElement viewButton;
	
	public void filterTeacher(String nam, String grupTyp, String grupId)
	{
		waitForElementToLoad(filterButton);
		name.sendKeys(nam);
		groupType.sendKeys(grupTyp);
		group.sendKeys(grupId);
		filterButton.click();
	}
	
	public void filterNurse(String nam)
	{
		waitForElementToLoad(filterButton);
		name.sendKeys(nam);
		filterButton.click();
	}
	
	public void filterAssistant(String nam, String grupTyp, String grupId)
	{
		waitForElementToLoad(filterButton);
		name.sendKeys(nam);
		groupType.sendKeys(grupTyp);
		group.sendKeys(grupId);
		filterButton.click();
	}
}
