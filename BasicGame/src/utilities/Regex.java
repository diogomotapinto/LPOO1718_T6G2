package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Regex {

	public Regex() {
	}

	public boolean checkOgreNumber(String ogreNumber) {
		Pattern pattern = Pattern.compile("^([1-5]{1})\\b$");
		Matcher matcher = pattern.matcher(ogreNumber);
		return matcher.matches();
	}
}
