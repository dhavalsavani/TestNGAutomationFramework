package utilities.string;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utilities to work and manipulate strings.
 */
public class StringUtil {

	/**
	 * This method will replace all '?' character to the value(s) given.
	 * 
	 * @param str - String having replacement characters(words)
	 * @param strings - String(s) to replace
	 * @return replaced version of string
	 */
	public static String getString(String str, String... strings) {
		Pattern pattern = Pattern.compile("\\?");
		StringBuilder builder = new StringBuilder(str);
		Matcher matcher = pattern.matcher(builder);
		int index = 0;
		int counter = 0;
		while(matcher.find(index)) {
			if(counter >= strings.length) {
				throw new RuntimeException("Number of words to be replace and replacement word count does not match.");
			}
			String replacement = strings[counter++];
			builder.replace(matcher.start(), matcher.end(), replacement);
			index = matcher.start() + replacement.length();
		}
		return builder.toString();
	}
	
	/**
	 * This method will return comma separated string of each record of the given collection
	 * 
	 * @param collection - collection of records
	 * @return String in the following format:<br/>
	 * 			[&lt;comma-separated each record of collection&gt;]<br/>
	 * 	example: [a,b,c]
	 */
	public static String getPrintableStringOfCollection(Collection<? extends String> collection) {
		return collection.stream().collect(Collectors.joining(",", "[", "]"));
	}
	
	/**
	 * This method will extract part of given string which matches given regex
	 * 
	 * @param str - string from which part is to be extracted
	 * @param regex - pattern to match for extraction
	 * @return part of string which matches given regex, else null
	 */
	public static String extractString(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()) {
			String s = matcher.toMatchResult().group();
			return s;
		}
		return null;
	}
}