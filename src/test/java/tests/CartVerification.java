package tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import core.DriverManager;
import enums.Browsers;
import enums.TestConstants;
import pages.DressesPage;
import pages.HomePage;

public class CartVerification {

	private HomePage homePage;
	private DressesPage dressesPage;
	
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
		dressesPage = new DressesPage();
	}

	@Test
	public void verifyAddToCartFeature() {
		DriverManager.getDriver().get("http://www.automationpractice.com/");
		homePage.clickOnDressesLink();
		List<String> dresses = Arrays.asList("Printed Dress");
		for (String d : dresses) {
			dressesPage.hoverTheProduct(d);
			dressesPage.clickOnAddToCartLink();
			dressesPage.clickOnContinueShoppingButton();
		}
		Assert.assertTrue(dressesPage.getCartText().contains(String.valueOf(dresses.size())), "Cart item count mismatch!!");
	}

	@AfterTest
	public void quitBrowser() {
		DriverManager.quitDriver();
	}
}