package tests;

import java.util.Arrays;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.DriverManager;
import enums.Browsers;
import enums.TestConstants;
import pages.HomePage;

public class Sample {
	
	private HomePage homePage;
	
	@BeforeSuite
	public void checkBrowserProperty() {
		String browser = System.getProperty(TestConstants.BROWSER.getValue());
		if(browser == null) {
			System.setProperty(TestConstants.BROWSER.getValue(), Browsers.CHROME.getBrowserName());
		}
	}
	
	@BeforeClass
	public void initPages() {
		homePage = new HomePage();
	}

	@DataProvider
	public Iterator<Object[]> getWebsites() {
		return Arrays.asList(
				new Object[] {"http://www.automationpractice.com/", "My Store"}, 
				new Object[] {"https://www.phptravels.com/demo/", "Demo Script Test drive - PHPTRAVELS"})
				.iterator();
	}
	
	@Test(dataProvider = "getWebsites")
	public void validatePracticeWebsiteTitle(String url, String title) {
		DriverManager.getDriver().get(url);
		Assert.assertEquals(homePage.getTitle(), title, "Ooops landed on different page!");
	}
	
	@AfterTest
	public void quitBrowser() {
		DriverManager.quitDriver();
	}
}