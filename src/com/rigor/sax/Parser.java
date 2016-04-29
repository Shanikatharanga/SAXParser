package com.rigor.sax;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

	XMLReader reader;
	ArrayList<String> startTags = new ArrayList<String>();
	ArrayList<String> endTags = new ArrayList<String>();

	public void parse(String path) {
		reader = XMLReader.getInstance();

		try {
			String content = reader.readFile(path);
			
			content = content.replaceAll("</", "close");
			content = content.replaceAll("<", "open");
			System.out.println(content);

			char[] c = content.toCharArray();

		    Matcher matcher1 = Pattern.compile("open(.*?)>").matcher(content);
		    while (matcher1.find()) {
		        System.out.println(matcher1.group(1));
		    }
		    
		    System.out.println("+++++++++++++++++++++++++++++++++");
		    
		    Matcher matcher2 = Pattern.compile("close(.*?)>").matcher(content);
		    while (matcher2.find()) {
		        System.out.println(matcher2.group(1));
		    }

			// Pattern p = Pattern.compile("\\</.*?\\>");
			// Matcher m = p.matcher(content);
			//
			// if (m.find())
			// endTags.add((String)m.group().subSequence(1, m.group().length() -
			// 1));
			// System.out.println(endTags);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Parser parse = new Parser();
		parse.parse("C:\\Users\\shtharanga\\Desktop\\sample.xml");
	}

}
