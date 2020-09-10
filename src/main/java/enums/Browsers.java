package enums;

import org.openqa.selenium.InvalidArgumentException;

public enum Browsers {

	CHROME("chrome"),
	FIREFOX("firefox"),
	IE("ie", "internetexplorer"),
	EDGE("edge")
	;
	
	private String[] browser;
	private Browsers(String... browser) {
		this.browser = browser;
	}
	
	public static Browsers getBrowser(String browserName) {
		//if browser name is null, then throwing exception
		if(browserName == null) {
			throw new InvalidArgumentException("Browser name can't be null");
		}
		
		//else matching with names and returning with match
		for (Browsers b : values()) {
			for(String bn : b.getBrowserNames()) {
				if(browserName.equalsIgnoreCase(bn)) {
					return b;
				}
			}
		}
		
		//else throw exception for providing valid browser name
		throw new InvalidArgumentException("Please provide valid browser name: " + values());
	}
	
	public String getBrowserName() {
		return browser[0];
	}
	
	private String[] getBrowserNames() {
		return browser;
	}
	
	@Override
	public String toString() {
		return getBrowserNames().toString();
	}
}