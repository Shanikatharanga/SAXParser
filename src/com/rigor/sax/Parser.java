package com.rigor.sax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	XMLReader reader;
	XMLValidator validater;
	ArrayList<String> startTags = new ArrayList<String>();
	ArrayList<String> endTags = new ArrayList<String>();

	public void parse(String path) {
		reader = XMLReader.getInstance();
		validater = XMLValidator.getInstance();

		try {
			String content = reader.readFile(path);

			System.out.println(content);
			
			//Get All Opening and Closing Tags
			Pattern p = Pattern.compile("<(.|\\n)*?>");
			Matcher m = p.matcher(content);
			
			while(m.find()){
				System.out.println("test : " + m.group());
				//System.out.println(content.replace(m.group(), ""));
			}
			
			//System.out.println(startTags);
			
			System.out.println("+++++++++++++++++++++++++++++++++");
			//Get All Opening Tags with Attributes
			Pattern p1 = Pattern.compile("<\\s*\\w.*?>");
			Matcher m1 = p1.matcher(content);
			
			while(m1.find()){
				//System.out.println("test1 : " + m1.group());
				startTags.add(m1.group());
				System.out.println(startTags);
			}
			
			System.out.println("+++++++++++++++++++++++++++++++++");
			//Get All Closing Tags
			Pattern p2 = Pattern.compile("<\\s*\\/\\s*\\w\\s*.*?>|<\\s*br\\s*>");
			Matcher m2 = p2.matcher(content);
			
			while(m2.find()){
				//System.out.println("test2 : " + m2.group());
				endTags.add(m2.group());
				System.out.println(endTags);
			}
			
			
			
			

			char[] c = content.toCharArray();

			Matcher matcher1 = Pattern.compile("<(.*?)>").matcher(content);
			while (matcher1.find()) {
				startTags.add(matcher1.group(1));

			}

			System.out.println("+++++++++++++++++++++++++++++++++");

			Matcher matcher2 = Pattern.compile("</(.*?)>").matcher(content);
			while (matcher2.find()) {
				endTags.add(matcher2.group(1));

			}

			System.out.println(startTags);
			// Collections.reverse(endTags);
			System.out.println(endTags);

			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Parser parse = new Parser();
		parse.parse("C:\\Users\\shtharanga\\Desktop\\sample.xml");
	}

}
