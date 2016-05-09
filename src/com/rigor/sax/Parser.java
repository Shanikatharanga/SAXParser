package com.rigor.sax;

import java.util.ArrayList;

import com.rigor.exceptions.NotXMLFileException;

public class Parser {

	XMLReader reader;
	XMLValidator validater;
	ArrayList<String> startTags = new ArrayList<String>();
	ArrayList<String> endTags = new ArrayList<String>();

	public void parse(String path) {
		reader = XMLReader.getInstance();
		validater = XMLValidator.getInstance();

		try {
			
			if(validater.pathValidate(path)&&validater.xmlValidate(path)){
				System.out.println(reader.readFile(path));
			}
			

		} catch (NotXMLFileException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Parser parse = new Parser();
		parse.parse("C:\\Users\\shtharanga\\Desktop\\sample.xml");
	}

}
