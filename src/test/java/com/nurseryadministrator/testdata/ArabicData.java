package com.nurseryadministrator.testdata;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class ArabicData {

	Locale local = new Locale("Locale.UK");
	Locale arabicLocale = new Locale.Builder().setLanguage("ar").setRegion("SA")
            .setExtension(Locale.UNICODE_LOCALE_EXTENSION, "nu-arab").build();
	Faker fake = new Faker(local);
	FakeValuesService fakeService = new FakeValuesService(local, new RandomService());
	TestDataImport tdImport = new TestDataImport();
	String[] testData;
	
	public void generateFakeStudentData()
	{
		String nam = fake.address().state();
		System.out.println(nam);
		
	}
	public static void main(String[] args) {
		ArabicData a = new ArabicData();
		a.generateFakeStudentData();
	}
	
}
