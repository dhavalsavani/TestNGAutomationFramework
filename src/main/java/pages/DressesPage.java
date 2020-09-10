package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.page.PageActions;
import utilities.string.StringUtil;

public class DressesPage {

	private String productImage = "//img[@title='?']";
	private By addToCartLink = By.xpath("//li[contains(@class, 'hovered')]//a[@title='Add to cart']");
	private By continueShoppingButton = By.xpath("//span[@title='Continue shopping']");
	private By cartElement = By.xpath("//a[@title='View my shopping cart']");
	
	public void hoverTheProduct(String productName) {
		PageActions.waitForPageToBeLoaded();
		WebElement element = PageActions.waitUntilVisisbilityOfElementLocatedBy(
											By.xpath(StringUtil.getString(this.productImage, productName)), 30);
		PageActions.moveToElement(element);
	}
	
	public void clickOnAddToCartLink() {
		PageActions.waitUntilElementLocatedByIsClickable(addToCartLink).click();
	}
	
	public void clickOnContinueShoppingButton() {
		PageActions.waitUntilVisisbilityOfElementLocatedBy(continueShoppingButton).click();
		PageActions.waitUntilInVisisbilityOfElementLocatedBy(continueShoppingButton);
	}
	
	public String getCartText() {
		return PageActions.waitUntilVisisbilityOfElementLocatedBy(cartElement).getText().trim();
	}
}