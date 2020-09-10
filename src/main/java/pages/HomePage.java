package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.page.IBasePage;

public class HomePage implements IBasePage {

	@FindBy(xpath = "//div[@id='block_top_menu']/ul/li[2]/a")
	private WebElement dressesLink;
	
	private WebDriver driver;
	
	public HomePage() {
		init();
	}
	
	public void clickOnDressesLink() {
		dressesLink.click();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
}