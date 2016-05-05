package com.rigor.sax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rigor.exceptions.NotXMLFileException;

public class XMLValidator {

	private boolean pathValid;

	public static XMLValidator validater = new XMLValidator();

	private XMLValidator() {

	}

	public static XMLValidator getInstance() {
		// Return XMLReader Object
		return validater;
	}

	public boolean pathValidate(String path) throws NotXMLFileException{

		Matcher matcher = Pattern.compile("([^\\s]+(\\.(?i)(xml))$)").matcher(path);
		pathValid = matcher.matches();
		return pathValid;

	}

}
