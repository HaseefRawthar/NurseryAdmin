package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.LogInObject;
import com.nurseryadministrator.objectrepository.PaymentInfoObject;
import com.nurseryadministrator.objectrepository.PaymentRequestListObject;
import com.nurseryadministrator.objectrepository.RequestPaymentObject;
import com.nurseryadministrator.testdata.RequestPaymentData;
import com.nurseryadministrator.testdata.TestDataImport;

public class PaymentRequestList extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	RequestPaymentObject reqpaymentObj;
	RequestPaymentData reqpaydataObj;
	PaymentRequestListObject payreqlistObj;
	PaymentInfoObject paymentinfoObj;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			tdImport = new TestDataImport();
			reqpaymentObj = new RequestPaymentObject(driver);
			reqpaydataObj = new RequestPaymentData();
			payreqlistObj = new PaymentRequestListObject(driver);
			paymentinfoObj = new PaymentInfoObject(driver);
			
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
	public void filterByStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStatus test");	
			eTest = eReports.createTest("filterByStatus");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String stat =(language.equals("Arabic"))?"بالإنتظار":"Pending";

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.status.sendKeys(stat);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]//span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=2)
	public void filterByPaymentName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentName test");	
			eTest = eReports.createTest("filterByPaymentName");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title =reqpaydataObj.getRequestPaymentData()[0];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=3)
	public void filterByStudentName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStudentName test");	
			eTest = eReports.createTest("filterByStudentName");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String student = reqpaydataObj.getStudentsName()[0]+" "+reqpaydataObj.getStudentsName()[0]+" "+reqpaydataObj.getStudentsName()[0]+" "+reqpaydataObj.getStudentlastName()[0];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//body[1]/app-root[1]/app-private-layout[1]/div[1]/div[1]/div[1]/app-payment-list[1]/div[3]/div[1]/div[1]/div[1]/ngx-datatable[1]/div[1]/datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	
	@Test(priority=4)
	public void filterByDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByDate test");	
			eTest = eReports.createTest("filterByDate");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String date = "02/06/2021";

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.dueDateFrom);
			payreqlistObj.dueDateFrom.sendKeys(date);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(date);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[contains(text(),'"+date+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void verifySortTitle()
	{
		try
		{
			
			log.info("Entered verifySortTitle test");	
			eTest = eReports.createTest("verifySortTitle");
			eTest.assignCategory("Request Payment List");
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			expectedMsg = verifySortButton(payreqlistObj.titleSortButton, payreqlistObj.sortedTitle);
			actualMsg = payreqlistObj.sortedTitle.getText();
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion passed");
	}
	
	@Test(priority=5)
	public void filterByGroupName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupName test");	
			eTest = eReports.createTest("filterByGroupName");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String groupName = "lime26rhe";

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(groupName);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/div[1]/i[1]")).click();
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value = paymentinfoObj.groupName.getText().equals(groupName);
						actualBoolArray.add(value);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void verifySortStudentName()
	{
		try
		{
			
			log.info("Entered verifySortStudentName test");	
			eTest = eReports.createTest("verifySortStudentName");
			eTest.assignCategory("Request Payment List");
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			expectedMsg = verifySortButton(payreqlistObj.studentSortButton, payreqlistObj.sortedStudent);
			actualMsg = payreqlistObj.sortedStudent.getText();
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion passed");
	}
	
	@Test(priority=8)
	public void verifySortDueDate()
	{
		try
		{
			
			log.info("Entered verifySortDueDate test");	
			eTest = eReports.createTest("verifySortDueDate");
			eTest.assignCategory("Request Payment List");
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			
			scrollToTheElement(payreqlistObj.studentSortButton);
			expectedMsg = verifySortButton(payreqlistObj.studentSortButton, payreqlistObj.sortedStudent);
			actualMsg = payreqlistObj.sortedStudent.getText();
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion passed");
	}
	
	@Test(priority=9)
	public void verifySortStatus()
	{
		try
		{
			
			log.info("Entered verifySortStatus test");	
			eTest = eReports.createTest("verifySortStatus");
			eTest.assignCategory("Request Payment List");
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			
			scrollToTheElement(payreqlistObj.statusSortButton);
			expectedMsg = verifySortButton(payreqlistObj.statusSortButton, payreqlistObj.sortedStatus);
			actualMsg = payreqlistObj.sortedStatus.getText();
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion passed");
	}
	
	@AfterClass
	public void signOut()
	{
		dashboardObj.logOut();
	}
}
