package com.rigor.sax;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rigor.exceptions.NotXMLFileException;

public class XMLValidator {
	
	private XMLReader reader = XMLReader.getInstance();
	private static XMLValidator validater = new XMLValidator();

	private XMLValidator() {

	}

	public static XMLValidator getInstance() {
		// Return XMLReader Object
		return validater;
	}

	public boolean pathValidate(String path) throws NotXMLFileException {

		Matcher matcher = Pattern.compile("([^\\s]+(\\.(?i)(xml))$)").matcher(path);

		if (!matcher.matches()) {
			throw new NotXMLFileException();
		} else {
			return matcher.matches();
		}

	}

	public boolean xmlValidate(String path) throws NotXMLFileException {

		Pattern p = Pattern.compile("<(.|\\n)*?>");
		Matcher m = p.matcher(reader.readFile(path));

		List<String> tags = reader.getTags(path);

		while (m.find()) {
			tags.add(m.group().split(" ")[0].replaceAll("[\\<\\>]", ""));
		}

		do {
			for (int i = 0; i < tags.size(); i++) {
				String close_tag = String.valueOf(tags.get(i).charAt(0));

				if (close_tag.matches("[\\/]")) {
					String tag2 = tags.get(i).replaceAll("[\\/]", "");
					if (tag2.equalsIgnoreCase(tags.get(i - 1))) {
						tags.remove(i);
						tags.remove(i - 1);
						//System.out.println(tags);
					}
				}
			}
		} while (tags.size() != 0);

		if (tags.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
