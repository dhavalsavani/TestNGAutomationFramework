package utilities.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.DriverManager;
import enums.TestConstants;
import utilities.config.Configuration;

/**
 * Class for generic utilities for page classes, which helps pages to perform some behavior
 */
public class PageActions {
	
	/**
	 * Waits for specified page load timeout in config for page readyState to be complete 
	 * else throws timeout exception
	 */
	public static void waitForPageToBeLoaded() {
		new WebDriverWait(DriverManager.getDriver(), 
				Integer.parseInt(Configuration.getProperty(TestConstants.PAGE_LOAD_TIMEOUT.getValue())))
					.until(d -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * wait until element is visible within timeout limit or else throw exception
	 * 
	 * @param by
	 * @param timeout
	 * @return webelemnt object
	 */
	public static WebElement waitUntilVisisbilityOfElementLocatedBy(By by, int timeout) {
		return new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	/**
	 * wait until element is visible within default timeout limit specified config file or else throw exception
	 * 
	 * @param by
	 * @return webelemnt object
	 */
	public static WebElement waitUntilVisisbilityOfElementLocatedBy(By by) {
		return waitUntilVisisbilityOfElementLocatedBy(by, Integer.parseInt(Configuration.getProperty(TestConstants.EXPLICIT_WAIT.getValue())));
	}
	
	/**
	 * wait until element is invisible within timeout limit or else throw exception
	 * 
	 * @param by
	 * @param timeout
	 */
	public static boolean waitUntilInVisisbilityOfElementLocatedBy(By by, int timeout) {
		return new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	/**
	 * wait until element is invisible within default timeout limit specified config file or else throw exception
	 * 
	 * @param by
	 */
	public static boolean waitUntilInVisisbilityOfElementLocatedBy(By by) {
		return waitUntilInVisisbilityOfElementLocatedBy(by, Integer.parseInt(Configuration.getProperty(TestConstants.EXPLICIT_WAIT.getValue())));
	}
	
	/**
	 * wait until element is clickable within timeout limit or else throw exception
	 * 
	 * @param by
	 * @param timeout
	 * @return webelemnt object
	 */
	public static WebElement waitUntilElementLocatedByIsClickable(By by, int timeout) {
		return new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(by));
	}
	
	/**
	 * wait until element is clickable within timeout limit or else throw exception
	 * 
	 * @param by
	 * @return webelemnt object
	 */
	public static WebElement waitUntilElementLocatedByIsClickable(By by) {
		return waitUntilElementLocatedByIsClickable(by, Integer.parseInt(Configuration.getProperty(TestConstants.EXPLICIT_WAIT.getValue())));
	}
	
	/**
	 * performs moving action to the given webelement
	 * 
	 * @param element
	 */
	public static void moveToElement(WebElement element) {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).build().perform();
	}
	
	/**
	 * This method is used to run javascript during execution.
	 * 
	 * @param driver - WebDriver instance
	 * @param jsScript - Script to execute
	 * @param element - Elements to pass to script
	 * @return the returned object from script, if any
	 */
	public static Object executeJavascript(String jsScript, Object... element) {
		return ((JavascriptExecutor) DriverManager.getDriver()).executeScript(jsScript, element);
	}
	
	/**
	 * This method is used to scroll the UI to let the WebElement come into view of screen.
	 * 
	 * @param driver - WebDriver 
	 * @param element
	 */
	public static void scrollElementIntoView(WebElement element) {
		executeJavascript("arguments[0].scrollIntoView();", element);
	}
}