package com.rigor.sax;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rigor.exceptions.NotValidXMLException;
import com.rigor.exceptions.NotXMLFileException;

public class Parser {

	XMLReader reader;
	XMLValidator validater;
	ArrayList<String> startTags = new ArrayList<String>();
	ArrayList<String> endTags = new ArrayList<String>();

	// Regex Patterns
	public static final String OPENNING_TAGS = "<[a-zA-Z](.*?[^?])?>";
	public static final String CLOSSING_TAGS = "<\\s*\\/\\s*\\w\\s*.*?>|<\\s*br\\s*>";
	public static final String GET_ATTRIBUTES = "(\\w+)=(\"[^<>\"]*\"|'[^<>']*'|\\w+)";

	public void parse(String path) {
		reader = XMLReader.getInstance();
		validater = XMLValidator.getInstance();

		// Get All Opening Tags with Attributes
		Pattern p1 = Pattern.compile(OPENNING_TAGS);
		Matcher m1 = p1.matcher(reader.readFile(path));

		while (m1.find()) {
			startTags.add(m1.group().replaceAll(GET_ATTRIBUTES, ""));
		}

		// Get All Closing Tags
		Pattern p2 = Pattern.compile(CLOSSING_TAGS);
		Matcher m2 = p2.matcher(reader.readFile(path));

		while (m2.find()) {
			endTags.add(m2.group());
		}

		try {

			if (validater.pathValidate(path) && validater.xmlValidate(startTags,endTags)) {
				
				
				

			}
		} catch (NotXMLFileException e) {
			e.printStackTrace();
			System.out.println("Give the valid XML file path...");
		} catch (NotValidXMLException e) {
			e.printStackTrace();
			System.out.println("XML File is not valid...");
		}

	}

	public static void main(String args[]) {
		Parser parse = new Parser();
		parse.parse("C:\\Users\\shtharanga\\Desktop\\sample.xml");
	}

}
