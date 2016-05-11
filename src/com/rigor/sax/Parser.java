package com.rigor.sax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rigor.exceptions.NotValidXMLException;
import com.rigor.exceptions.NotXMLFileException;
import com.rigor.handler.ParserHandler;

public class Parser implements ParserHandler{

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
			startTags.add(m1.group());
		}

		// Get All Closing Tags
		Pattern p2 = Pattern.compile(CLOSSING_TAGS);
		Matcher m2 = p2.matcher(reader.readFile(path));

		while (m2.find()) {
			endTags.add(m2.group());
		}
		
		try {

			if (validater.pathValidate(path) && validater.xmlValidate(startTags, endTags)) {
				
				//COde
				for(int i=0;i<startTags.size();i++){
					System.out.println(Tag_Lable_Name(startTags.get(i).replaceAll("[\\<\\>]", "")));
				}

			}
		} catch (NotXMLFileException e) {
			e.printStackTrace();
			System.out.println("Give the valid XML file path...");
		} catch (NotValidXMLException e) {
			e.printStackTrace();
			System.out.println("XML File is not valid...");
		}

	}

	public Map<String, String> AttributrList(String atts) {

		Map<String, String> hash = new HashMap<String, String>();
		for (int k = 0; k < atts.split("\"").length - 1; k++) {
			if ((k == 0 || (k % 2) == 0)) {
				String Key = atts.split("\"")[k].substring(0, atts.split("\"")[k].length() - 1);
				String Value = atts.split("\"")[k + 1];
				hash.put(Key, Value);
			}
		}
		return hash;
	}

	public ArrayList<String> Tag_Lable_Name(String Tag) {
		ArrayList<String> list = new ArrayList<String>();
		String Label = null;
		String atts = null;
		String AttbList = null;

		Label = Tag.split(" ")[0];

		if (Tag.split(" ").length > 1) {
			atts = Tag.substring(Tag.indexOf(" ")).replaceAll(" ", "");
			AttbList = AttributrList(atts).toString();
		} else if (Tag.split(" ").length == 1) {
			// System.out.println("No attributes found!!!");
		}
		list.add(Label);
		list.add(AttbList);

		return list;
	}

	@Override
	public void startElement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endElement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void characters() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) {
		Parser parse = new Parser();
		parse.parse("C:\\Users\\shtharanga\\Desktop\\sample.xml");
	}

}
