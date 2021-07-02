package com.nurseryadministrator.testdata;

import com.nurseryadministrator.baseclass.SetUp;

public class AddNurseryAdminData extends SetUp {

	TestDataImport tdImport = new TestDataImport();
	String[] testData;
	
	
	public String[] getAdminData()
	{
		testData = new String[9];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 0);
			testData[1] = tdImport.getCellData(2, 1);
			testData[2] = tdImport.getCellData(2, 2);
			testData[3] = tdImport.getCellData(2, 3);
			testData[4] = tdImport.getCellData(2, 4);
			testData[5] = tdImport.getCellData(2, 5);
			testData[6] = tdImport.getCellData(2, 6);
			testData[7] = tdImport.getCellData(2, 7);
			testData[8] = tdImport.getCellData(2, 8);
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 0);
			testData[1] = tdImport.getCellData(1, 1);
			testData[2] = tdImport.getCellData(1, 2);
			testData[3] = tdImport.getCellData(1, 3);
			testData[4] = tdImport.getCellData(1, 4);
			testData[5] = tdImport.getCellData(1, 5);
			testData[6] = tdImport.getCellData(1, 6);
			testData[7] = tdImport.getCellData(1, 7);
			testData[8] = tdImport.getCellData(1, 8);
		}
		
		return testData;
	}
	
	public String[] getEditAdminData()
	{
		testData = new String[9];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 0)+"__تم تحريره";
			testData[1] = tdImport.getCellData(2, 1);
			testData[2] = tdImport.getCellData(2, 2);
			testData[3] = tdImport.getCellData(2, 3);
			testData[4] = tdImport.getCellData(2, 4);
			testData[5] = tdImport.getCellData(2, 5);
			testData[6] = tdImport.getCellData(2, 6);
			testData[7] = tdImport.getCellData(2, 7)+"__تم تحريره";
			testData[8] = tdImport.getCellData(2, 8)+"__تم تحريره";
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 0)+"__edited";
			testData[1] = tdImport.getCellData(1, 1);
			testData[2] = tdImport.getCellData(1, 2);
			testData[3] = tdImport.getCellData(1, 3);
			testData[4] = tdImport.getCellData(1, 4);
			testData[5] = tdImport.getCellData(1, 5);
			testData[6] = tdImport.getCellData(1, 6);
			testData[7] = tdImport.getCellData(1, 7)+"__edited";
			testData[8] = tdImport.getCellData(1, 8)+"__edited";
		}
		
		return testData;
	}
}
