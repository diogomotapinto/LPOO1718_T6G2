package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Regex {

	/**
	 * Class constructor
	 */
	public Regex() {
	}
	
	/**
	 * Checks with a regular expression if the number of ogres is acceptable
	 * @param ogreNumber number of ogres
	 * @return true is the number of ogres is accepted by the regular expression and false otherwise
	 */
	public boolean checkOgreNumber(String ogreNumber) {
		Pattern pattern = Pattern.compile("^([1-5]{1})\\b$");
		Matcher matcher = pattern.matcher(ogreNumber);
		return matcher.matches();
	}
}
