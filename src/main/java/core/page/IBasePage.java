package core.page;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import core.DriverManager;

/**
 * This interface is used to tell all pages to implement init() method for initialization of page elements.
 */
public interface IBasePage {
	
	String driverObjectName = "driver";
	Logger log = LogManager.getLogger(IBasePage.class);

	/**
	 * This default method is used to initialize webdriver instance and page elements. 
	 */
	default void init() {
		log.info("Initializing page elements for class: " + this.getClass());
		try {
			Field field = this.getClass().getDeclaredField(driverObjectName);
			//If page class has declared driver object then set it, else skip
			if(field != null) {
				//boolean accessible = declaredField.canAccess(this);	//Java 11+ function
				boolean accessible = field.isAccessible();	//Java 8 function
				field.setAccessible(true);
				field.set(this, DriverManager.getDriver());
				field.setAccessible(accessible);
			}
			AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(DriverManager.getDriver(), 20);
			PageFactory.initElements(factory, this);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}