package enums;

public enum TestConstants {

	BROWSER("browser"),
	PAGE_LOAD_TIMEOUT("pageLoadTimeout"),
	IMPLICIT_WAIT("implicit.wait"),
	EXPLICIT_WAIT("explicit.wait")
	;
	
	private String value;

	private TestConstants(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}