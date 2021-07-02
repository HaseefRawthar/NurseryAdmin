package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.AddGroupObject;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.GroupListObject;
import com.nurseryadministrator.objectrepository.LogInObject;
import com.nurseryadministrator.objectrepository.ViewGroupObject;
import com.nurseryadministrator.testdata.AddGroupData;
import com.nurseryadministrator.testdata.TestDataImport;

public class GroupList extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddGroupObject addgroupObj;
	AddGroupData addgrpdataObj;
	GroupListObject grouplistObj;
	ViewGroupObject viewgroupObj;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			tdImport = new TestDataImport();
			addgrpdataObj = new AddGroupData();
			addgroupObj = new AddGroupObject(driver);
			grouplistObj = new GroupListObject(driver);
			viewgroupObj = new ViewGroupObject(driver);
			
			tdImport.readSheet("AddGroup");
		
			
			loginObj.logIn(userEmail, userPassword);
			dashboardObj.selectNursery(nurseryName);
			
			waitForElementToBeClickable(dashboardObj.adminImage);
			dashboardObj.changeLanguage(language);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	@Test(priority=1)
	public void listAndViewGroupDetails()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered listAndViewGroupDetails test");	
			eTest = eReports.createTest("listAndViewGroupDetails");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			String[] details = addgrpdataObj.getFilterData();
			expectedArraylist.add(details[0]);
			expectedArraylist.add(details[1]);
			grouplistObj.filterGroups(details[0], "", "", "");
			Thread.sleep(1500);
			grouplistObj.viewDetails.click();
			waitForElementToLoad(viewgroupObj.grupName);
			actualArraylist.add(viewgroupObj.grupName.getText());
			actualArraylist.add(viewgroupObj.grupType.getText());
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion Passed");
	}
	
	@Test(priority=4)
	public void editAndViewGroupDetails()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered listAndViewGroupDetails test");	
			eTest = eReports.createTest("listAndViewGroupDetails");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			String editedName;
			String[] details = addgrpdataObj.getFilterEditData();
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث معلومات الصف الدراسي بنجاح");
				editedName = details[0]+"__تم تحريره";
				expectedArraylist.add(editedName);
			}
			else
			{
				expectedArraylist.add("Group Updated Successfully");
				editedName = details[0]+"__edited";
				expectedArraylist.add(editedName);
			}
			
			expectedArraylist.add(details[1]);
			grouplistObj.filterGroups(details[0], "", "", "");
			Thread.sleep(1500);
			grouplistObj.editDetails.click();
			waitForElementToLoad(addgroupObj.groupName);
			addgroupObj.groupName.sendKeys(language.equals("Arabic")?"__تم تحريره":"__edited");
			grouplistObj.saveButton.click();
			waitForElementToLoad(grouplistObj.updateSucessMsg);
			actualArraylist.add(grouplistObj.updateSucessMsg.getText());
			waitForElementToLoad(grouplistObj.groupName);
			grouplistObj.groupName.clear();
			grouplistObj.filterGroups(editedName, "", "", "");
			Thread.sleep(1500);
			grouplistObj.viewDetails.click();
			waitForElementToLoad(viewgroupObj.grupName);
			actualArraylist.add(viewgroupObj.grupName.getText());
			actualArraylist.add(viewgroupObj.grupType.getText());
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion Passed");
	}
	
	@Test(priority=2)
	public void deactivateGroup()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered deactivateGroup test");	
			eTest = eReports.createTest("deactivateGroup");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث الحالة بنجاح");
				expectedArraylist.add("تفعيل");
				expectedArraylist.add("غير مفعل");
			}
			else
			{
				expectedArraylist.add("Status Updated Successfully");
				expectedArraylist.add("ACTIVATE");
				expectedArraylist.add("Inactive");
			}
			
			String[] details = addgrpdataObj.getFilterData();
			grouplistObj.filterGroups(details[0], "", "", "");
			Thread.sleep(1500);
			autoScrollandClick(grouplistObj.viewDetails);
			waitForElementToLoad(viewgroupObj.deactivateButton);
			viewgroupObj.deactivateButton.click();
			waitForElementToLoad(viewgroupObj.yes);
			viewgroupObj.yes.click();
			waitForElementToLoad(viewgroupObj.statUpdateSucessMsg);
			actualArraylist.add(viewgroupObj.statUpdateSucessMsg.getText());
			waitForElementToLoad(viewgroupObj.activateButton);
			actualArraylist.add(viewgroupObj.activateButton.getText());
			actualArraylist.add(viewgroupObj.status.getText());
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion Passed");
	}
	
	@Test(priority=3)
	public void activateGroup()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered activateGroup test");	
			eTest = eReports.createTest("activateGroup");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث الحالة بنجاح");
				expectedArraylist.add("تعطيل");
				expectedArraylist.add("مفعل");
			}
			else
			{
				expectedArraylist.add("Status Updated Successfully");
				expectedArraylist.add("DEACTIVATE");
				expectedArraylist.add("Active");
			}
			
			String[] details = addgrpdataObj.getFilterData();
			grouplistObj.filterGroups(details[0], "", "", "");
			Thread.sleep(1500);
			autoScrollandClick(grouplistObj.viewDetails);
			waitForElementToLoad(viewgroupObj.activateButton);
			viewgroupObj.activateButton.click();
			waitForElementToLoad(viewgroupObj.yes);
			viewgroupObj.yes.click();
			waitForElementToLoad(viewgroupObj.statUpdateSucessMsg);
			actualArraylist.add(viewgroupObj.statUpdateSucessMsg.getText());
			waitForElementToLoad(viewgroupObj.deactivateButton);
			actualArraylist.add(viewgroupObj.deactivateButton.getText());
			actualArraylist.add(viewgroupObj.status.getText());
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion Passed");
	}
	
	@Test(priority=5)
	public void filterByStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered activateGroup test");	
			eTest = eReports.createTest("filterByStatus");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			String stat =(language.equals("Arabic"))?"غير مفعل":"Inactive";

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.status);
			grouplistObj.status.sendKeys(stat);
			grouplistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedBoolArray);
		System.out.println(actualBoolArray);
		Assert.assertEquals(actualBoolArray, expectedBoolArray);
		log.info("Assertion passed");
	}
	
	@Test(priority=6)
	public void filterByGroupType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupType test");
			eTest = eReports.createTest("filterByGroupType");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			String typ =(language.equals("Arabic"))?"رضع":"Babies";

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.groupType);
			grouplistObj.groupType.sendKeys(typ);
			autoScrollandClick(grouplistObj.filterButton);
			//grouplistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+typ+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
			}
			
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedBoolArray);
		System.out.println(actualBoolArray);
		Assert.assertEquals(actualBoolArray, expectedBoolArray);
		log.info("Assertion passed");
	}
	
	@Test(priority=7)
	public void filterByGroupName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupName test");
			eTest = eReports.createTest("filterByGroupName");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			String nam ="orange35nmw";

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.status);
			grouplistObj.groupName.sendKeys(nam);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+nam+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedBoolArray);
		System.out.println(actualBoolArray);
		Assert.assertEquals(actualBoolArray, expectedBoolArray);
		log.info("Assertion passed");
	}
	
	@Test(priority=8)
	public void verifySortGrpName()
	{
		try
		{
			log.info("Entered verifySortGrpName test");	
			eTest = eReports.createTest("verifySortGrpName");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			expectedMsg = verifySortButton(grouplistObj.grupNameSortBtn, grouplistObj.firstGrpName);
			actualMsg = grouplistObj.firstGrpName.getText();
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	@Test(priority=9)
	public void verifySortForGroupType()
	{
		try
		{
			log.info("Entered verifySortForGroupType test");	
			eTest = eReports.createTest("verifySortForGroupType");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			expectedMsg = verifySortButton(grouplistObj.grupTypSortBtn, grouplistObj.firstGrpTyp);
			actualMsg = grouplistObj.firstGrpTyp.getText();
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	@Test(priority=10)
	public void verifySortForStatus()
	{
		try
		{
			log.info("Entered verifySortForStatus test");	
			eTest = eReports.createTest("verifySortForStatus");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			expectedMsg = verifySortButton(grouplistObj.statusSortBtn, grouplistObj.firstStatus);
			actualMsg = grouplistObj.firstStatus.getText();
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	@Test(priority=11)
	public void verifySortForTeacher()
	{
		try
		{
			log.info("Entered verifySortForTeacher test");	
			eTest = eReports.createTest("verifySortForTeacher");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			expectedMsg = verifySortButton(grouplistObj.teacherSortBtn, grouplistObj.firstTeacher);
			actualMsg = grouplistObj.firstTeacher.getText();
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	@AfterClass
	public void signOut()
	{
		dashboardObj.logOut();
	}
}
